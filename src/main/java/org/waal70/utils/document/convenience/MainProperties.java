/**
 * 
 */
package org.waal70.utils.document.convenience;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author awaal
 *
 */
public class MainProperties {
	
	private static Logger log = LogManager.getLogger(MainProperties.class);
	
	private static class SingletonHelper{
        private static final MainProperties INSTANCE = new MainProperties();
    }
    public static MainProperties getInstance(){
        return SingletonHelper.INSTANCE;
    }

	private InputStream inputStream;
    private Properties prop = new Properties();
	
    private MainProperties() {
    	loadProperties();
    }
    
    public String getSourcePath() {
    	log.debug("SourcePath: " + prop.get("SourcePath"));
		String dirname = (String) prop.get("SourcePath");
		File sourceDir = new File(dirname);
		if (sourceDir.exists())
			return dirname;
		else {
			if (System.getProperty("os.name").startsWith("Windows"))
				dirname = "Z:\\SCANS\\";
			else
				dirname = "/Users/awaal/TEMP/PDF/";
			return dirname;
		}
    }
    
    public String getTargetBase() {
    	
    	//Target base gets less rigorous checks, because
    	// the path that is lacking will be created anyway
    	log.debug("TargetBase: " + prop.get("TargetBase"));
		String dirname = (String) prop.get("TargetBase");
		//Do some minimum checking, there should be at least one
		// separator char in there:
		if (dirname.contains(File.separator))
			return dirname;
		if (System.getProperty("os.name").startsWith("Windows"))
			dirname = "Z:\\SCANS\\";
		else
			dirname = "/Users/awaal/TEMP/PDF/";
		return dirname;
		
    }
    
    public String getRecipientFilename() {
       	log.info("CSV: " + prop.get("TargetUsersCSV"));
    	//String csvName = "/Users/awaal/TEMP/PDF/result/ApprovedCompanies.csv";
		String csvName = (String) prop.get("TargetUsersCSV");
		File targetFile = null;
		try {
			targetFile = new File(csvName);
		}
		catch (NullPointerException e) {
			log.error("Supplied CSV does not exist.");
			return "ThisIsNotAFile";
		}
		if (targetFile.exists())
			return csvName;
		else {
			if (System.getProperty("os.name").startsWith("Windows"))
				csvName = "C:\\pdf\\TargetUsers.csv";
			else
				csvName = "/Users/awaal/TEMP/PDF/result/TargetUsers.csv";
			return csvName;
		}
    	
    }
    
    public String getCompanyFilename() {
    	log.info("CSV: " + prop.get("ApprovedCompaniesCSV"));
    	//String csvName = "/Users/awaal/TEMP/PDF/result/ApprovedCompanies.csv";
		String csvName = (String) prop.get("ApprovedCompaniesCSV");
		File targetFile = null;
		try {
			targetFile = new File(csvName);
		}
		catch (NullPointerException e) {
			log.error("Supplied CSV does not exist.");
			return "ThisIsNotAFile";
		}
		if (targetFile.exists())
			return csvName;
		else {
			if (System.getProperty("os.name").startsWith("Windows"))
				csvName = "C:\\pdf\\ApprovedCompanies.csv";
			else
				csvName = "/Users/awaal/TEMP/PDF/result/ApprovedCompanies.csv";
			return csvName;
		}
    	
    }
    
    public String getFilename() {
    	return prop.getProperty("batchfile") != null ? prop.getProperty("batchfile") : "No file found";
    }
    
    public void setFilename(String myFile) {
    	prop.setProperty("batchfile", myFile);
    	
    }
    
    private void loadProperties() {

    String propFileName = "acdocument.properties";
    if (System.getProperty("os.name").startsWith("Windows"))	
    	propFileName = "acdocument.win.properties";
    else
    	propFileName = "acdocument.nix.properties";

	//let's see if the user has put the property file with the .JAR file:
	File jarPath=new File(MainProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    String propertiesPath=jarPath.getParentFile().getAbsolutePath();
    
    log.debug("propertiesPath: " + propertiesPath);
	
    try {
		inputStream = new FileInputStream(propertiesPath+File.separator+propFileName);
	} catch (FileNotFoundException e1) {
		//Apparently, there is no user-specified properties file, revert to the one in the jar
		log.error("You did not specify your own properties. I will use mine.");
	}
	if (inputStream == null)
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

	if (inputStream != null) {
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			log.error("Cannot load property-file: " + propFileName);
		}
	} 

    }
}
