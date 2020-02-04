/**
 * 
 */
package org.waal70.utils.document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author awaal
 *
 */
public class ApprovedCompanyList {
	
	//contains the list of read companies
	private List<ApprovedCompany> companies = new ArrayList<ApprovedCompany>();
	
	public void addCompany(ApprovedCompany ac) {
		this.companies.add(ac);
	}
	
	public String[] getListforCombo() {
		
		String[] result = new String[companies.size()];
		
		for (int i=0;i<companies.size();i++) 
			result[i] = companies.get(i).getDisplayName();
		//Perform an alphabetical sort, because it is prettier
		// in the combobox
		Arrays.sort(result);
		return result;
	}
	
	public String getArchiveCodeforCompany(String company) {
		String result = "";
		for (int i=0;i<companies.size();i++)
		{
			if (companies.get(i).getDisplayName().equals(company))
				result = companies.get(i).getArchiveCode();
		}	
		return result;
			
	}
	

}
