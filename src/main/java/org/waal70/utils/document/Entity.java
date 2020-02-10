/**
 * 
 */
package org.waal70.utils.document;

/**
 * @author awaal
 *
 */
public class Entity {
	
	private String displayName;
	private String archiveCode;
	
	public void setDisplayName(String text) {
		this.displayName = text;
	}
	
	public void setArchiveCode(String text) {
		this.archiveCode = text;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getArchiveCode() {
		return archiveCode;
	}

}
