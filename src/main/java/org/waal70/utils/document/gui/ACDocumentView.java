/**
 * 
 */
package org.waal70.utils.document.gui;

import javax.swing.JFrame;

/**
 * @author awaal
 *
 */
public interface ACDocumentView {
    public void setText(String text);
    public String getText();
    
    public JFrame getFrame();
    
    public void addButtonObserver(ButtonObserver observer);
    public void removeButtonObserver(ButtonObserver observer);

}



