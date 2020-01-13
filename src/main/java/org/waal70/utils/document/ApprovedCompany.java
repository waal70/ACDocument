/**
 * 
 */
package org.waal70.utils.document;

/**
 * @author awaal
 *
 */
public class ApprovedCompany implements Company {
	
	private String displayName;
	private String archiveCode;
	
	public void setDisplayName(String text) {
		this.displayName = text;
	}
	
	public void setArchiveCode(String text) {
		this.archiveCode = text;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	@Override
	public String getArchiveCode() {
		// TODO Auto-generated method stub
		return archiveCode;
	}

}
