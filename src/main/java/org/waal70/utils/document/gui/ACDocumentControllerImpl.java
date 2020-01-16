/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.event.ChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.io.DocumentList;
import org.waal70.utils.document.io.ReadCSV;

/**
 * @author awaal
 *
 */
public class ACDocumentControllerImpl implements ACDocumentController, ActionListener {
	
	
	private static Logger log = LogManager.getLogger(ACDocumentControllerImpl.class);
	
	private ACDocument model;
	private ACDocumentViewImpl view;
	
	private ACDocument currentDocument; 
	
	private DocumentType currentType;
	
	private DocumentList docQueue = DocumentList.getInstance(); 
	

	public ACDocumentControllerImpl() {
        
		//this.model = Objects.requireNonNull(model, "Model cannot be null");
		log.info("docQueue size: " + docQueue.size());
		this.view = new ACDocumentViewImpl(this);
		initView();
    }
	
	private void initView() {
		///
		
		//First, get the first document from the queue:
		log.info("Initview about to munch on a list containing this amount of elements: " + docQueue.size());
		
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
		repopulate();
		view.getFrame().setTitle("The AC Archive Document Processor Thingy!");
		//view.setPDFPreview(currentDocument.getPreview());
		//view.setText(currentDocument.getPath());
		//view.getFrame().repaint();
	}
	
	@Override
	public ACDocumentView getView() {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public ACDocument getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	private void repopulate() {
		view.setPDFPreview(currentDocument.getPreview());
		
		//The read-only properties are:
		view.setScanFileName(currentDocument.getTitle());
		view.setScanDated(currentDocument.getCreated());
		view.setPDFVersion(currentDocument.getPdfVersion());
		view.setNumPages(String.valueOf(currentDocument.getNumPages()));
		view.setFileSize(currentDocument.getFileSize());
		
		view.setText(currentDocument.getPath());
		view.getFrame().repaint();
		
		populateCombo();
	}
	
	private void populateCombo() {
		
		//CompanyList:
		ReadCSV csv = new ReadCSV();
		view.setCompanyCombo(csv.populateCompanyList().getListforCombo());
		
		//Category:
		view.setCategoryCombo(DocumentType.getCategoryCombo());
		log.info("Category is: " + view.getCategoryCombo().getDisplayAs());
		
		//Type:
		populateDependent();

		
	}
	
	/**
	 * is called every time a change occurs on screen.
	 */
	private void populateDependent() {
		
		DocumentType currentCategory = view.getCategoryCombo();
		DocumentType[] cmbFill = DocumentType.getTypeForCombo(currentCategory);
		if (cmbFill.length == 0)
		{
			//Apparently, the document type is only a parent category
			view.setTypeCombo(new DocumentType[] {DocumentType.EMPTY});
			view.disableTypeCombo();
			categoryChanged();
		}
		else
		{
			//The document type is a subtype
			log.info("Setting new doctype: ");
			view.setTypeCombo(DocumentType.getTypeForCombo(currentCategory));
			view.enableTypeCombo();
			this.docTypeChanged();
		}
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Action things were done: " + e.getSource().toString() + ":" + e.getActionCommand() + e.paramString());
		if (e.getActionCommand() == "Next")
		{
			log.info("requesting new doc off of queue");
			currentDocument = docQueue.get();
			if (currentDocument == null)
				log.error("No more documents in queue");
			else
				repopulate();
		}
		populateDependent();
		
	}
	public void dateChanged(Date d) {
		log.info("Datum gewijzigd!" + d.toString());
		currentDocument.setTargetDated(d);
		log.info("Date according to GUI: " + view.getTargetDated().toString());
		log.info("Date according to document: " + currentDocument.getTargetDated().toString());
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
		log.info("Type changed: " + view.getTypeCombo().getOnlyPath());
		setCurrentType(view.getTypeCombo());
	}
	
	private void categoryChanged() {
		log.info("Category changed: " + view.getCategoryCombo().getOnlyPath());
		setCurrentType(view.getCategoryCombo());
	}

	/**
	 * @return the currentType
	 */
	public DocumentType getCurrentType() {
		return currentType;
	}

	/**
	 * @param currentType the currentType to set
	 */
	public void setCurrentType(DocumentType currentType) {
		//The currentType has been set, this means I also
		// have to reflect this change in the current document:
		this.currentType = currentType;
		this.currentDocument.setDoctype(currentType);
	}

}
