/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author awaal
 *	This class is responsible for writing the batch file
 *  that will rename/move all PDFs into their final position
 *  For now, I will assume the best place to start writing this
 *  is the System's TEMP folder
 */
public class BatchFileWriter {
	
	private String tmpFolder = System.getProperty("java.io.tmpdir");
	private static Logger log = LogManager.getLogger(BatchFileWriter.class);
	
	private final String fileName = "moveDocs.bat";
	
	private static final String moveCommandWIN = "MOVE -Y";
	private static final String moveCommandNIX = "mv -y";
	
	public void clearFile() {
		
		File myFile;
		try {
			myFile = File.createTempFile("moveDocs", ".bat", null);
		} catch (IOException e) {
			log.error("Cannot create temp file " + e.getLocalizedMessage());
		}
		
		
	}
	
	public boolean addEntry() {
		
		String moveCommand = "";
		String entryToWrite = "";
		if (System.getProperty("os.name").startsWith("Windows"))
			moveCommand = BatchFileWriter.moveCommandWIN;
		else
			moveCommand = BatchFileWriter.moveCommandNIX;
		
		
		entryToWrite = moveCommand;
		
		return true;
	}

}
