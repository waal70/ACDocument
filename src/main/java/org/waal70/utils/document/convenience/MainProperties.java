/**
 * 
 */
package org.waal70.utils.document.convenience;

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
		String dirname = "/Users/awaal/TEMP/PDF/";
		if (System.getProperty("os.name").startsWith("Windows"))
			dirname = "C:\\pdf\\";
		return dirname;
    }
    
    public String getTargetBase() {
    	
    	//Primarily, get the config'd property:
    	log.debug("TargetBase: " + prop.get("TargetBase"));
		String dirname = "/Users/awaal/TEMP/PDF/";
		if (System.getProperty("os.name").startsWith("Windows"))
			dirname = "C:\\pdf\\";
		return dirname;
    }
    
    public String getFilename() {
    	return prop.getProperty("batchfile") != null ? prop.getProperty("batchfile") : "No file found";
    }
    
    public void setFilename(String myFile) {
    	prop.setProperty("batchfile", myFile);
    	
    }
    
    private void loadProperties() {
	
	String propFileName = "acdocument.properties";

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
