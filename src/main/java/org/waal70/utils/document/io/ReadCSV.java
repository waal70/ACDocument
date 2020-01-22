/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ApprovedCompany;
import org.waal70.utils.document.ApprovedCompanyList;
import org.waal70.utils.document.Company;

/**
 * @author awaal
 *
 */
public class ReadCSV {
	
	private static Logger log = LogManager.getLogger(ReadCSV.class);
	
	private ApprovedCompanyList acl = new ApprovedCompanyList();
	
	public ApprovedCompanyList populateCompanyList()
	{
		String csvFile = "/Users/awaal/TEMP/PDF/result/ApprovedCompanies.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // CompanyID;CompanyName;CompanyDescription;DefaultUser
                String[] country = line.split(cvsSplitBy);
                Company newCompany = new ApprovedCompany();
                newCompany.setDisplayName(country[1]);
                newCompany.setArchiveCode(country[2]);
                acl.addCompany((ApprovedCompany) newCompany);

                log.info("Companyname [code= " + country[1] + " , name=" + country[2] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return acl;
	}

}
