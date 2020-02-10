package org.waal70.utils.document.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.convenience.MainProperties;
import org.waal70.utils.document.metadata.Metadata;

public abstract class BatchFileWriter {
	
	private static Logger log = LogManager.getLogger(BatchFileWriter.class);

	protected File myFile;
	protected FileWriter fw;
	protected String tmpFolder = System.getProperty("java.io.tmpdir");
	
	protected String moveCommand = "should have been re-defined";
	
	protected Map <String, String> targetFolders = new HashMap<String, String>();

	public BatchFileWriter() {
		super();
	}

	protected void addEntry(ACDocument doc) {
	
		// Current filename is getTitle()
		// Target is getPath();
		//log.info(doc.toString());
		log.info("Entry: " + MainProperties.getInstance().getSourcePath() + doc.getMetadata().get(Metadata.TITLE) + "-->"
				+ MainProperties.getInstance().getTargetBase() + doc.getDoctype().getPath() + File.separator
				+ doc.getTargetFileName());
		String strSource = MainProperties.getInstance().getSourcePath() + doc.getMetadata().get(Metadata.TITLE);
		String strDestination = MainProperties.getInstance().getTargetBase() + doc.getDoctype().getPath() + File.separator
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
	
	protected String getLineEnd() {
		return "\n";
		
	}
	

	protected void addToFile(String text) {
		if (fw != null && myFile != null) {
			try {
				fw.append(text);
				fw.append(this.getLineEnd());
			} catch (IOException e) {
				log.error("Cannot write to file ");
			}
		}
	}
	
	protected void createTargetFolder(String folder) {
		log.error("This should have been overridden");
	}
	
	protected void createTargetDirs() {
		//loops through targetFolders
		for (String value : targetFolders.values()) {
			createTargetFolder(value);
		}
		
	}

	public void processQueue(DocumentReadyList docQueue) {
		// process the queue
		log.info("Going to process " + docQueue.size() + " document(s).");
		
		writeHeader();
		Iterator<ACDocument> dqi = docQueue.iterator();
		dqi.forEachRemaining(document -> {
			targetFolders.put(document.getDoctype().toString(), document.getDoctype().getPath());
			//addEntry(document);
		});
		createTargetDirs();
		//reset the iterator:
		dqi = docQueue.iterator();
		dqi.forEachRemaining(document -> {
			//targetFolders.put(document.getDoctype().toString(), document.getDoctype().getPath());
			addEntry(document);
		});
		writeFooter();
		log.info("Have processed queue: this many in queue: " + docQueue.size());
		closeAll();
	}

}