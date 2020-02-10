/**
 * 
 */
package org.waal70.utils.document.convenience;

import java.io.IOException;
import java.text.Normalizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.TargetUser;
import org.waal70.utils.document.gui.SplashScreen;

/**
 * @author awaal
 *
 */
public class Helper {
	
	private static Logger log = LogManager.getLogger(Helper.class);
	
	public static SplashScreen splash;
	
	public static TargetUser NOBODY = new TargetUser("Niemand", "Niemand");
	public static TargetUser ALL = new TargetUser("Allemaal", "Allemaal");
	
	public static void setSplash(SplashScreen splash) {
		Helper.splash = splash;
	}
	
	public static String toValidString(String input) {
		String int_result;
		
		int_result = input.replaceAll("[\\\\/:\"*?<>|]+", "");
		
		//Also transform spaces into underscores:
		
		int_result = int_result.replaceAll("\\s+", "_");
		
		int_result = Normalizer.normalize(int_result, Normalizer.Form.NFD);
	
		int_result = int_result.replaceAll("[^\\p{ASCII}]", "");
		log.debug("Normalized result is: " + int_result);
		return int_result;
	}
	
	public static void openExplorer() {
		log.debug("fileToOpen is: " + MainProperties.getInstance().getFilename());
		String fileToOpen = MainProperties.getInstance().getFilename();
		
		try {
		if (System.getProperty("os.name").startsWith("Windows"))
				Runtime.getRuntime().exec("explorer.exe /select," + fileToOpen);
		else
			Runtime.getRuntime().exec("open --reveal " + fileToOpen);
		}
		catch (IOException e)
		{
			log.error("Error " + e.getLocalizedMessage());
		}
		
	}

}
