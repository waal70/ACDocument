package org.waal70.utils.document.gui;

import java.awt.event.ActionEvent;
import java.util.Date;

public interface ACDocumentController {
	
	 public ACDocumentView getView();
	 //public ACDocument getModel();
	 
	 public void dateChanged(Date d);
	 
	 public void categoryChanged(ActionEvent e);
	 public void docTypeChanged(ActionEvent e);

}
