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

public abstract class BatchFileWriter {
	
	private static Logger log = LogManager.getLogger(BatchFileWriter.class);

	protected File myFile;
	protected FileWriter fw;
	protected String tmpFolder = System.getProperty("java.io.tmpdir");
	
	protected String moveCommand = "should have been re-defined";

	public BatchFileWriter() {
		super();
	}

	protected void addEntry(ACDocument doc) {
	
		// Current filename is getTitle()
		// Target is getPath();
		log.info("Entry: " + MainProperties.getInstance().getSourcePath() + doc.getTitle() + "-->"
				+ MainProperties.getInstance().getTargetBase() + doc.getPath() + File.separator
				+ doc.getTargetFileName());
		String strSource = MainProperties.getInstance().getSourcePath() + doc.getTitle();
		String strDestination = MainProperties.getInstance().getTargetBase() + doc.getPath() + File.separator
				+ doc.getTargetFileName();
			
		String entryToWrite = moveCommand + " " + strSource + " " + strDestination;
		addToFile(entryToWrite);
	
	}

	protected void closeAll() {
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void writeHeader() {
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm:ss");
		ZonedDateTime zdt = ZonedDateTime.now();
		String datumFormat = dtf.format(zdt);
	
		addToFile("# " + datumFormat + " : file created===========");
	
	}

	protected void writeFooter() {
		addToFile("# End of File===================");
		
		
	
	}

	protected void addToFile(String text) {
		if (fw != null && myFile != null) {
			try {
				fw.append(text);
				fw.append("\n");
			} catch (IOException e) {
				log.error("Cannot write to file ");
			}
		}
	}

	public void processQueue(DocumentReadyList docQueue) {
		// process the queue
		log.info("Going to process " + docQueue.size() + " document(s).");
		writeHeader();
		Iterator<ACDocument> dqi = docQueue.iterator();
		dqi.forEachRemaining(document -> {
			addEntry(document);
		});
		writeFooter();
		closeAll();
	}

}