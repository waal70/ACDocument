/**
 * 
 */
package org.waal70.utils.document;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.convenience.Helper;

/**
 * @author awaal
 * 
 *         References a default (PDF) document that was just scanned
 *
 */
public class ACDocument extends GenericDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1333036846405153112L;

	//Just the filename. For scanFileName I expect IMG_001.pdf
	//This is GenericDocument.path
	//private String scanFileName;
	// for targetFileName, I will construct 20201231_<Company>_<Subject>_<Recipient>.pdf
	
	private static Logger log = LogManager.getLogger(ACDocument.class);
	
	private static final String SEPARATOR = "_"; 
	private static final String EMPTY = "";
	private String targetFileName;
	private String recipient;
	private String senderCompany;
	
	private String pdfVersion;
	private int numPages = 0;
	private String fileSize;
	
	public String getPdfVersion() {
		return pdfVersion;
	}

	public void setPdfVersion(String pdfVersion) {
		this.pdfVersion = pdfVersion;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	// This is GenericDocument.created
	// private Calendar scanDated; 
	private Date targetDated;

	
	private String title = "";
	//Start out with a default doctype of "divers":
	private DocumentType doctype = DocumentType.Divers;
	private String description = "";
	private String language = "";
	private Calendar lastModified;
	
	private BufferedImage preview;

	public Calendar getLastModified() {
		return lastModified;
	}

	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setPreview(BufferedImage preview) {
		this.preview = preview;
	}
	public BufferedImage getPreview() {
		return preview;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("recipient=").append(recipient);
		sb.append(", docType=").append(doctype.getOnlyPath());
		sb.append(", senderCompany=").append(senderCompany);
		sb.append(", description=").append(description);
		sb.append(", title=").append(title);

		sb.append(", path=").append(path);
		sb.append(", targetDate=").append(targetDated);
		sb.append(", targetFileName=").append(targetFileName);

		sb.append(", numPages").append(numPages);
		sb.append(", fileSize").append(fileSize);
		sb.append(", pdfVersion").append(pdfVersion);
		sb.append(", docType").append(doctype.getOnlyPath());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the doctype
	 */
	public DocumentType getDoctype() {
		return doctype;
	}

	/**
	 * @param doctype the doctype to set
	 */
	public void setDoctype(DocumentType doctype) {
		this.doctype = doctype;
	}

	/**
	 * @return the targetFileName
	 */
	public String getTargetFileName() {
		
		//This will implement the algorithm to return
		// the intended filename.
		String fnTotal, fnDate, fnSender, fnSubject, fnTarget;
		fnTotal = "";
		if (this.getDoctype() == DocumentType.Divers)
			return this.getTitle();
		else
		{
			//Documents will follow the following naming guideline:
			//YYYYMMDD_SenderCompany_Subject_Target.PDF
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			ZoneId zone = ZoneId.systemDefault();
			LocalDate localDate = this.getTargetDated().toInstant().atZone(zone).toLocalDate();
			fnDate = dtf.format(localDate);
	        log.info("Date: " + fnDate);
	        //max = (a > b) ? a : b;  (a true, b false)
	        fnSender = this.getSenderCompany() != null ? SEPARATOR + this.getSenderCompany() : EMPTY;
	        if (fnSender.trim().startsWith("_@"))
	        	fnSender=EMPTY;
	        //
	        log.info("Sender: " + fnSender);
	        
	        fnSubject = this.getDescription() != "" ? SEPARATOR + this.getDescription()  : EMPTY;
	        if (fnSubject.trim().length()==1)
	        	fnSubject=EMPTY;
	        fnSubject = Helper.toValidString(fnSubject);
	        log.info("Subject: " + fnSubject);
	        
	        fnTarget = this.getRecipient() != null ? SEPARATOR + this.getRecipient() : EMPTY;
	        fnTarget = Helper.toValidString(fnTarget);
	        log.info("Target: " + fnTarget);
	        fnTotal = fnDate + fnSender + fnSubject + fnTarget;
			log.info("Path: " + this.getDoctype().getPath());
			log.info("Total: " + fnTotal);

		}
		return fnTotal;
	}

	/**
	 * @param targetFileName the targetFileName to set
	 */
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	/**
	 * @return the senderCompany
	 */
	public String getSenderCompany() {
		return senderCompany;
	}

	/**
	 * @param senderCompany the senderCompany to set
	 */
	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the targetDated
	 */
	public Date getTargetDated() {
		return targetDated;
	}

	/**
	 * @param targetDated the targetDated to set
	 */
	public void setTargetDated(Date targetDated) {
		this.targetDated = targetDated;
	}
	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
