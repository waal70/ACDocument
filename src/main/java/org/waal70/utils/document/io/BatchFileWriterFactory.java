/**
 * 
 */
package org.waal70.utils.document.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author awaal
 *
 */
public class BatchFileWriterFactory {
	private static Logger log = LogManager.getLogger(BatchFileWriterFactory.class);
	
	public static BatchFileWriter getBatchFileWriter() {
		if (System.getProperty("os.name").startsWith("Windows"))
		{
			log.info("Instantiating a Windows file writer");
			return new BatchFileWriterWindows();
		}
		else
		{
			log.info("Instantiating a *NIX file writer");
			return new BatchFileWriterNIX();
		}
	
	}

}
