/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.Archive.Recipient;
import org.waal70.utils.document.convenience.Messages;
import org.waal70.utils.document.io.BatchFileWriter;
import org.waal70.utils.document.io.DocumentList;
import org.waal70.utils.document.io.DocumentReadyList;
import org.waal70.utils.document.io.ReadCSV;

/**
 * @author awaal
 *
 */
public class ACDocumentControllerImpl implements ACDocumentController, ActionListener, FocusListener {
	
	
	private static Logger log = LogManager.getLogger(ACDocumentControllerImpl.class);
	
	private ACDocumentViewImpl view;
	
	private ACDocument currentDocument; 
	
	private DocumentList docQueue = DocumentList.getInstance(); 
	
	private DocumentReadyList finishedDocQueue = DocumentReadyList.getInstance();
	

	public ACDocumentControllerImpl() {
        
		//this.model = Objects.requireNonNull(model, "Model cannot be null");
		log.info("docQueue size: " + docQueue.size()); //$NON-NLS-1$
		this.view = new ACDocumentViewImpl(this);
		initView();
    }
	
	private void initView() {

		//First, get the first document from the queue:
		log.info("Initview about to munch on a list containing this amount of elements: " + docQueue.size()); //$NON-NLS-1$
		
		//retrieve the last item off the queue:
		currentDocument = docQueue.get();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		populate();
		view.getFrame().setTitle(Messages.getString("ACDocumentControllerImpl.2")); //$NON-NLS-1$
	}
	
	@Override
	public ACDocumentView getView() {
		return view;
	}

	private void populate() {
		// The assumption is there is a default document.
		// On initial load of a document, this method
		// populates all info and fillable fields
		
		//Read-only properties:
		view.setPDFPreview(currentDocument.getPreview());
		view.setScanFileName(currentDocument.getTitle());
		view.setScanDated(currentDocument.getCreated());
		view.setPDFVersion(currentDocument.getPdfVersion());
		view.setNumPages(String.valueOf(currentDocument.getNumPages()));
		view.setFileSize(currentDocument.getFileSize());
		
		//IMPORTANT: populate the combo's before setting the
		// calculated fields, as they depend on the combo's
		populateCombos();
		updateDocument();
		
	}
	
	private void updateDocument() {
		if (currentDocument == null) {
			//last document already off of queue, so show an option dialog to check what to do:
			//Also, disable the buttons
			
			confirmNextAction();
		}
		else {
		//Walk through all settings updating the document instance
		DocumentType newDocType = (view.getTypeCombo() != DocumentType.EMPTY)&&(view.getTypeCombo() != null) ? view.getTypeCombo() : view.getCategoryCombo();
		currentDocument.setDoctype(newDocType);
		currentDocument.setDescription(view.getSubject());
		currentDocument.setRecipient(view.getRecipient());
		currentDocument.setSenderCompany(view.getSenderCompany());
		currentDocument.setTargetFileName(currentDocument.getTargetFileName());
		currentDocument.setTitle(view.getName());
		view.setTargetFileName(currentDocument.getTargetFileName());
		view.setTargetPath(currentDocument.getDoctype().getPath());
		//log.info(this.currentDocument.toString());
		}
	}
	
	private void populateCombos() {
		
		//CompanyList:
		ReadCSV csv = new ReadCSV();
		view.setCompanyCombo(csv.populateCompanyList().getListforCombo());
		
		//Recipients:
		view.setRecipientCombo(Recipient.getRecipientCombo());
		
		//Category & Type:
		view.setCategoryCombo(DocumentType.getCategoryCombo());
		view.setTypeCombo(DocumentType.getTypeForCombo(view.getCategoryCombo()));
		//populateCategoryType();
	}
	
	/**
	 * is called every time a change occurs on screen.
	 */
	private void populateCategoryType() {
		
		DocumentType currentCategory = view.getCategoryCombo();
		log.info("currentCategory: " + currentCategory); //$NON-NLS-1$
		DocumentType[] cmbFill = DocumentType.getTypeForCombo(currentCategory);
		if (cmbFill.length == 0)
		{
			//Apparently, the document type is only a parent category
			view.setTypeCombo(new DocumentType[] {DocumentType.EMPTY});
			view.disableTypeCombo();
			//categoryChanged();
		}
		else
		{
			//The document type is a subtype
			log.info("Setting new doctype: "); //$NON-NLS-1$
			view.setTypeCombo(DocumentType.getTypeForCombo(currentCategory));
			view.enableTypeCombo();
			this.docTypeChanged();
		}
		this.updateDocument();
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Action things were done: " + e.getSource().toString() + ":" + e.getActionCommand() + e.paramString()); //$NON-NLS-1$ //$NON-NLS-2$
		log.info("actionCommand " + e.getActionCommand()); //$NON-NLS-1$
		if (e.getActionCommand().equals("Next")) //$NON-NLS-1$
		{
			//This means I am done with the current document,
			// so enqueue it on the ready queue
			// do one last update:
			this.updateDocument();
			finishedDocQueue.add(currentDocument);
			log.info("Requesting new doc off of queue"); //$NON-NLS-1$
			currentDocument = docQueue.get();
			if (currentDocument == null)
				log.error("No more documents in queue"); //$NON-NLS-1$
			else
				populate();
		}
		//Other than maybe next was pressed, maybe a field was updated, so save
		// these settings
		this.updateDocument();
		
	}
	public void dateChanged(Date d) {
		currentDocument.setTargetDated(d);
		this.updateDocument();
	}
	/**
	 * @return the currentDocument
	 */
	public ACDocument getCurrentDocument() {
		return currentDocument;
	}

	private void docTypeChanged() {
		//Courtesy method to call whenever the category changes
		this.docTypeChanged(new ActionEvent(this, 0,null));
	}
	@Override
	public void docTypeChanged(ActionEvent e) {
		// This means the subtype has changed, set the document type accordingly
		log.info("Type changed: " + view.getTypeCombo().getOnlyPath()); //$NON-NLS-1$
		setCurrentType(view.getTypeCombo());
		log.info("Effect on filename: "); //$NON-NLS-1$
		currentDocument.getTargetFileName();
		this.updateDocument();
	}
	@Override
	public void categoryChanged(ActionEvent e) {
		this.categoryChanged();
	}
	
	private void categoryChanged() {
		log.info("Category changed: " + view.getCategoryCombo().getOnlyPath()); //$NON-NLS-1$
		setCurrentType(view.getCategoryCombo());
		populateCategoryType();
		this.updateDocument();
	}

	/**
	 * @return the currentType
	 */
	public DocumentType getCurrentType() {
		return currentDocument.getDoctype();
	}

	/**
	 * @param currentType the currentType to set
	 */
	public void setCurrentType(DocumentType currentType) {
		//The currentType has been set, this means I also
		// have to reflect this change in the current document:
		//this.currentType = currentType;
		this.currentDocument.setDoctype(currentType);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		//courtesy method to reflect the change
		this.actionPerformed(new ActionEvent(this, 0,null));
		
	}
	
	private void confirmNextAction() {
		this.getView().disableButtons();
        Object[] options = {Messages.getString("ACDocumentControllerImpl.14"), Messages.getString("ACDocumentControllerImpl.15")}; //$NON-NLS-1$ //$NON-NLS-2$
        int n = JOptionPane.showOptionDialog(view.getFrame(),
                        Messages.getString("ACDocumentControllerImpl.16") + finishedDocQueue.size(), //$NON-NLS-1$
                        Messages.getString("ACDocumentControllerImpl.17"), //$NON-NLS-1$
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        if (n == JOptionPane.YES_OPTION) {
        	log.info("Yes chosen, processing batch file..."); //$NON-NLS-1$
        	BatchFileWriter bfw = new BatchFileWriter();
        	bfw.processQueue(finishedDocQueue);
        } else if (n == JOptionPane.NO_OPTION) {
            log.info("I don't like them, either."); //$NON-NLS-1$
        } else {
            log.info("Come on -- 'fess up!"); //$NON-NLS-1$
        }
	}

}
