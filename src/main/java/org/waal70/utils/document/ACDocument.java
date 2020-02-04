/**
 * 
 */
package org.waal70.utils.document;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.Archive.Recipient;
import org.waal70.utils.document.convenience.Helper;
import org.waal70.utils.document.metadata.Metadata;

/**
 * @author awaal
 * 
 *         References a default (PDF) document that was just scanned
 *
 */
public class ACDocument extends Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1333036846405153112L;
	private static Logger log = LogManager.getLogger(ACDocument.class);
	
	private static final String SEPARATOR = "_"; 
	private static final String EMPTY = "";
	
	private Metadata metadata;
	
	public void setMetadata(Metadata md) {
		metadata = md;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	
	private BufferedImage preview;


	public void setPreview(BufferedImage preview) {
		this.preview = preview;
	}
	public BufferedImage getPreview() {
		return preview;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
			sb.append(this.getMetadata().toString());
			sb.append("targetfilename=" + getTargetFileName() + "\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the doctype
	 */
	public DocumentType getDoctype() {
		return DocumentType.valueOf(this.getMetadata().get(Metadata.DOC_TYPE));
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
			return this.getMetadata().get(Metadata.TITLE);
		else
		{
			//Documents will follow the following naming guideline:
			//YYYYMMDD_SenderCompany_Subject_Target.PDF
			//convert String to LocalDate
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(this.getMetadata().get(Metadata.DATED),dtf);
			
			dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			fnDate = dtf.format(localDate);
	        log.debug("Date: " + fnDate);
	        //max = (a > b) ? a : b;  (a true, b false)
	        fnSender = this.getMetadata().get(Metadata.CREATOR) != null ? SEPARATOR + this.getMetadata().get(Metadata.CREATOR) : EMPTY;
	        if (fnSender.trim().startsWith("_@"))
	        	fnSender=EMPTY;
	        //
	        log.debug("Sender: " + fnSender);
	        
	        fnSubject = this.getMetadata().get(Metadata.DESCRIPTION) != "" ? SEPARATOR + this.getMetadata().get(Metadata.DESCRIPTION) : EMPTY;
	        if (fnSubject.trim().length()==1)
	        	fnSubject=EMPTY;
	        fnSubject = Helper.toValidString(fnSubject);
	        log.debug("Subject: " + fnSubject);
	        
	        fnTarget = this.getMetadata().get(Metadata.RECIPIENT) != null ? SEPARATOR + this.getMetadata().get(Metadata.RECIPIENT) : EMPTY;
	        if (fnTarget.equals(SEPARATOR + Recipient.NOBODY.getDisplayAs()) || fnTarget.contentEquals(SEPARATOR + Recipient.ALL.getDisplayAs()))
	        	fnTarget = EMPTY;
	        fnTarget = Helper.toValidString(fnTarget);
	        log.debug("Target: " + fnTarget);
	        fnTotal = fnDate + fnSender + fnSubject + fnTarget + ".pdf";
			log.debug("Path: " + this.getDoctype().getPath());
			log.info(this.getMetadata().get(Metadata.TITLE) + "->" + fnTotal);
		}
		return fnTotal;
	}
}
