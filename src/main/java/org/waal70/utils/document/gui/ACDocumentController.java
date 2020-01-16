package org.waal70.utils.document.gui;

import java.awt.event.ActionEvent;
import java.util.Date;

import org.waal70.utils.document.ACDocument;

public interface ACDocumentController {
	
	 public ACDocumentView getView();
	 public ACDocument getModel();
	 
	 public void dateChanged(Date d);
	 public void docTypeChanged(ActionEvent e);

}
