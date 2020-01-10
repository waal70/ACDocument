/**
 * 
 */
package org.waal70.utils.document.convenience;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author awaal
 *
 */
public class Helper {
	
	private static Logger log = LogManager.getLogger(Helper.class);
	
	public static String toValidString(String input) {
		
		log.info("Asked to make a valid string from " + input);
		return input.replaceAll("[\\\\/:\"*?<>|]+", "");
	}

}
