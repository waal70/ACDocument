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
import org.waal70.utils.document.Entity;
import org.waal70.utils.document.TargetUser;
import org.waal70.utils.document.TargetUserList;
import org.waal70.utils.document.convenience.MainProperties;


/**
 * @author awaal
 *
 */
public class ReadCSV {
	
	private static Logger log = LogManager.getLogger(ReadCSV.class);
	
	private ApprovedCompanyList acl = new ApprovedCompanyList();
	private TargetUserList tul = new TargetUserList();
	
	public TargetUserList populateRecipientList() {
		String csvFile = MainProperties.getInstance().getRecipientFilename();
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // UserID;UserName;UserDisplay;DefaultUser
                String[] country = line.split(cvsSplitBy);
                Entity targetUser = new TargetUser();
                targetUser.setDisplayName(country[1]);
                targetUser.setArchiveCode(country[2]);
                tul.addTargetUser((TargetUser) targetUser);

                log.info("Recipient [code= " + country[1] + " , name=" + country[2] + "]");

            }

        } catch (IOException e) {
        	//There is no CSV file found, so just populate with a default company:
        	Entity newUser = new TargetUser();
        	newUser.setDisplayName("Standaard");
        	newUser.setArchiveCode("Standaard");
        	tul.addTargetUser((TargetUser) newUser);
            return tul;
        }
        return tul;
	}
	
	
	public ApprovedCompanyList populateCompanyList()
	{
		String csvFile = MainProperties.getInstance().getCompanyFilename() ;
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // CompanyID;CompanyName;CompanyDescription;DefaultUser
                String[] country = line.split(cvsSplitBy);
                Entity newCompany = new ApprovedCompany();
                newCompany.setDisplayName(country[1]);
                newCompany.setArchiveCode(country[2]);
                acl.addCompany((ApprovedCompany) newCompany);

                log.info("Companyname [code= " + country[1] + " , name=" + country[2] + "]");

            }

        } catch (IOException e) {
        	//There is no CSV file found, so just populate with a default company:
        	Entity newCompany = new ApprovedCompany();
        	newCompany.setDisplayName("Standaard");
        	newCompany.setArchiveCode("Standaard");
        	acl.addCompany((ApprovedCompany) newCompany);
            return acl;
        }
        return acl;
	}

}
