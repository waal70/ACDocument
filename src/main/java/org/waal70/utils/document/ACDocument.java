/**
 * 
 */
package org.waal70.utils.document;

import java.awt.image.BufferedImage;
import java.util.Calendar;

import org.waal70.utils.document.Archive.DocumentType;

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
	private Calendar targetDated;

	
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
		sb.append("path=").append(path);
		sb.append(", title=").append(title);
		sb.append(", description=").append(description);
		sb.append(", author=").append(author);
		sb.append(", permissions=").append(permissions);
		sb.append(", created=").append(created);
		sb.append(", lastModified=").append(lastModified == null ? null : lastModified.getTime());
		// sb.append(", keywords=").append(keywords);
		// sb.append(", categories=").append(categories);

		sb.append(", subscribed=").append(subscribed);

		sb.append(", uuid=").append(uuid);

		// sb.append(", notes=").append(notes);
		sb.append(", language=").append(language);
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
		return targetFileName;
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
	public Calendar getTargetDated() {
		return targetDated;
	}

	/**
	 * @param targetDated the targetDated to set
	 */
	public void setTargetDated(Calendar targetDated) {
		this.targetDated = targetDated;
	}
	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
