/**
 * 
 */
package org.waal70.utils.document;

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


	
	private String title = "";
	//Start out with a default doctype of "divers":
	private DocumentType doctype = DocumentType.Divers;
	private String description = "";
	private String language = "";
	private Calendar lastModified;

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
		sb.append(", created=").append(created == null ? null : created.getTime());
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
}
