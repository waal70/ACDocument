/**
 * 
 */
package org.waal70.utils.document.gui;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.util.Date;

import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.Archive.Recipient;

/**
 * @author awaal
 *
 */
public interface ACDocumentView {
	
	public void setPDFPreview(BufferedImage preview);
		
	public void setScanFileName(String text);
	public String getScanFileName();
	
	public void setPDFVersion(String text);
	public void setNumPages(String text);
	public void setFileSize(String text);
	
	public void setTargetFileName(String text);
	public String getTargetFileName();
	
	public void setTargetPath(String text);
	
	public void setRecipient(String text);
	public String getRecipient();
	
	public void setSenderCompany(String text);
	public String getSenderCompany();
	
	public void setScanDated(String date);
	public String getScanDated();
	
	public void setTargetDated(Date date);
	public Date getTargetDated();
		
    public void setCompanyCombo(String[] text);
    
    public void setCategoryCombo(DocumentType[] text);
    public void setTypeCombo(DocumentType[] text);
    public void setRecipientCombo(Recipient[] text);
    
    public void disableTypeCombo();
	public void enableTypeCombo();
    
    public DocumentType getTypeCombo();
    public DocumentType getCategoryCombo();
       
    public void disableButtons();
    
    public String getSubject();
    
    public Frame getFrame();




}



