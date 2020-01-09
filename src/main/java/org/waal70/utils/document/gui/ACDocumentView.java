/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.util.Calendar;

/**
 * @author awaal
 *
 */
public interface ACDocumentView {
	
	public void setPDFPreview(BufferedImage preview);
	
	
	public void setScanFileName(String text);
	public String getScanFileName();
	
	public void setTargetFileName(String text);
	public String getTargetFileName();
	
	public void setRecipient(String text);
	public String getRecipient();
	
	public void setSenderCompany(String text);
	public String getSenderCompany();
	
	public void setScanDated(long date);
	public long getScanDated();
	
	public void setTargetDated(Calendar date);
	public Calendar getTargetDated();
		
    public void setText(String text);
    public String getText();
    
    public Frame getFrame();
  

}



