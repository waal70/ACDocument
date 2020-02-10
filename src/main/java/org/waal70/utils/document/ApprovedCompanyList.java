/**
 * 
 */
package org.waal70.utils.document;

import java.util.Arrays;

/**
 * @author awaal
 *
 */
public class ApprovedCompanyList extends EntityList {
	

	public void addCompany(ApprovedCompany ac) {
		super.addEntity(ac);
	}
	
	public String[] getListforCombo() {
		
		String[] result = super.getListforCombo();
		Arrays.sort(result);
		return result;
	}
	
	public String getArchiveCodeforCompany(String company) {
		return super.getArchiveCodeforEntity(company);
			
	}
	

}
