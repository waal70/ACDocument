package org.waal70.utils.document.gui;

import javax.swing.event.ChangeEvent;

import org.waal70.utils.document.ACDocument;

public interface ACDocumentController {
	
	 public ACDocumentView getView();
	 public ACDocument getModel();
	 
	 public void dateChanged(ChangeEvent e);

}
