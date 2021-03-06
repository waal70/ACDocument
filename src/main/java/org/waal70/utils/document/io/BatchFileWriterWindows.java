/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.convenience.MainProperties;

/**
 * @author awaal This class is responsible for writing the batch file that will
 *         rename/move all PDFs into their final position For now, I will assume
 *         the best place to start writing this is the System's TEMP-folder
 */
public class BatchFileWriterWindows extends BatchFileWriter {

	static Logger log = LogManager.getLogger(BatchFileWriterWindows.class);

	private void clearFile() {

		try {
				myFile = File.createTempFile("moveDocs", ".bat", null);
				log.info("Location of batchfile is: " + tmpFolder);
				MainProperties.getInstance().setFilename(myFile.getCanonicalPath());
				fw = new FileWriter(myFile);
		} catch (IOException e) {
			log.error("Cannot create temp file " + e.getLocalizedMessage());
		}

	}
	@Override
	protected String getLineEnd() {
		return "\r\n";
	}
	
	@Override
	protected void createTargetFolder(String folder) {
		//Algorithm is: if not exist "C:\VTS\" mkdir C:\VTS
		String fullFoldername = MainProperties.getInstance().getTargetBase() + folder;
		String entry = "if not exist \"" + fullFoldername + "\" mkdir " + fullFoldername;
		log.info("Entry is: " + entry);
		addToFile(entry);
	}

	public BatchFileWriterWindows() {
		super();
		moveCommand = "MOVE /Y";
		clearFile();
	}

}
