/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.io.DocumentList;

/**
 * @author awaal
 *
 */
public class ACDocumentControllerImpl implements ACDocumentController, ActionListener {
	
	
	private static Logger log = LogManager.getLogger(ACDocumentControllerImpl.class);
	
	private ACDocument model;
	private ACDocumentViewImpl view;
	
	private ACDocument currentDocument; 
	
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
		//view.getFrame().setTitle("Changed from Controller!");
		//view.setPDFPreview(currentDocument.getPreview());
		//view.setText(currentDocument.getPath());
		//view.getFrame().repaint();
	}
	
	public void initController() {
		
		//add action listeners here
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
		
	}
	public void dateChanged(ChangeEvent e) {
		log.info("Datum gewijzigd!");
	}
	/**
	 * @return the currentDocument
	 */
	public ACDocument getCurrentDocument() {
		return currentDocument;
	}

}
