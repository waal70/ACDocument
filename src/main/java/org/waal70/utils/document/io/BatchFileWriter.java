/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.convenience.MainProperties;

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
	
	private File myFile;
	private FileWriter fw;
	
	private static final String moveCommandWIN = "MOVE /Y";
	private static final String moveCommandNIX = "mv -f";
	
	private void clearFile() {
		
		try {
			myFile = File.createTempFile("moveDocs", ".bat", null);
			log.info("Location of batchfile is: " + tmpFolder);
			fw = new FileWriter(myFile);
		} catch (IOException e) {
			log.error("Cannot create temp file " + e.getLocalizedMessage());
		}
		
		
	}
	
	public BatchFileWriter() {
		super();
		clearFile();
	}
	
	private void addEntry(ACDocument doc) {
		
		
		//Current filename is getTitle()
		// Target is getPath();
		log.info("Entry: " + MainProperties.getInstance().getSourcePath() + doc.getTitle() + "-->" + MainProperties.getInstance().getTargetBase() + doc.getPath() + File.separator + doc.getTargetFileName());
		String moveCommand = "";
		String strSource = MainProperties.getInstance().getSourcePath() + doc.getTitle();
		String strDestination = MainProperties.getInstance().getTargetBase() + doc.getPath() + File.separator + doc.getTargetFileName(); 
		if (System.getProperty("os.name").startsWith("Windows"))
			moveCommand = BatchFileWriter.moveCommandWIN;
		else
			moveCommand = BatchFileWriter.moveCommandNIX;
		
		
		String entryToWrite = moveCommand + " " + strSource + " " + strDestination;
		addToFile(entryToWrite);
		
		
	}
	public void processQueue(DocumentReadyList docQueue) {
		//process the queue
		log.info("Going to process " + docQueue.size() + " document(s).");
		writeHeader();
		Iterator<ACDocument> dqi = docQueue.iterator();
		dqi.forEachRemaining(document -> {
			addEntry(document);
        });
		writeFooter();
		closeAll();
		
	}
	
	private void closeAll() {
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeHeader() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm:ss");
		ZonedDateTime zdt = ZonedDateTime.now();
		String datumFormat = dtf.format(zdt);
		
		
			addToFile("REM " + datumFormat + " : file created===========");
	
	}
	
	private void writeFooter() {
		addToFile("REM End of File===================");
		
	}
	
	private void addToFile(String text) {
		if (fw!=null && myFile !=null)
		{
			try {
				fw.append(text);
				fw.append("\r\n");
			} catch (IOException e) {
				log.error("Cannot write to file ");
			}
		}
	}

}
