/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import org.apache.log4j.Logger;

/**
 * @author awaal
 *
 */
public class ACDocumentControllerImpl implements ACDocumentController, ButtonObserver, ActionListener {
	
	
	private static Logger log = Logger.getLogger(ACDocumentControllerImpl.class);
	
	private ACDocumentModel model;
	private ACDocumentViewImpl view;
	

	public ACDocumentControllerImpl(ACDocumentModel model) {
        
		this.model = Objects.requireNonNull(model, "Model cannot be null");
		this.view = new ACDocumentViewImpl(this);
		initView();
    }
	
	private void initView() {
		///
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		log.info("intializing view");
		view.getFrame().setTitle("Changed from Controller!");
		view.addButtonObserver(this);
		view.getFrame().repaint();
	}
	
	public void initController() {
		
		//add action listeners here
	}
	

	@Override
	public void buttonWasChanged(ACDocumentView view) {
		
		log.info("Button things were done");
		// TODO Auto-generated method stub
		
	}

	@Override
	public ACDocumentView getView() {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public ACDocumentModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Action things were done: " + e.getActionCommand() + e.paramString());
		// TODO Auto-generated method stub
		
	}

}
