/**
 * 
 */
package org.waal70.utils.document.convenience;

import java.text.Normalizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.gui.SplashScreen;

/**
 * @author awaal
 *
 */
public class Helper {
	
	private static Logger log = LogManager.getLogger(Helper.class);
	
	public static SplashScreen splash;
	
	public static void setSplash(SplashScreen splash) {
		Helper.splash = splash;
	}
	
	public static String toValidString(String input) {
		String int_result;
		
		int_result = input.replaceAll("[\\\\/:\"*?<>|]+", "");
		
		int_result = Normalizer.normalize(int_result, Normalizer.Form.NFD);
	
		int_result = int_result.replaceAll("[^\\p{ASCII}]", "");
		log.info("Normalized result is: " + int_result);
		return int_result;
	}

}
