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
 * @author awaal
 *
 */
public class BatchFileWriterNIX extends BatchFileWriter {
	static Logger log = LogManager.getLogger(BatchFileWriterWindows.class);

	private void clearFile() {

		try {
			myFile = File.createTempFile("moveDocs", ".sh", null);
			myFile.setExecutable(true);
			MainProperties.getInstance().setFilename(myFile.getCanonicalPath());
			log.info("Location of batchfile is: " + tmpFolder);
			fw = new FileWriter(myFile);
		} catch (IOException e) {
			log.error("Cannot create temp file " + e.getLocalizedMessage());
		}
	}

	public BatchFileWriterNIX() {
		super();
		moveCommand = "mvp";
		clearFile();
	}

	void addMVPFunction() {
		// This is a convenience function designed
		// to handle the creation of non-existing folders in the
		// destination structure
		// Courtesy of: https://stackoverflow.com/a/10326993
		StringBuilder sb = new StringBuilder();
		sb.append("function mvp ()");
		sb.append("\n");
		sb.append("{");
		sb.append("\n");
		sb.append("dir=\"$2\"");
		sb.append("\n");
		sb.append("tmp=\"$2\"; tmp=\"${tmp: -1}\"");
		sb.append("\n");
		sb.append("[ \"$tmp\" != \"/\" ] && dir=\"$(dirname \"$2\")\"");
		sb.append("\n");
		sb.append("[ -a \"$dir\" ] ||");
		sb.append("\n");
		sb.append("mkdir -p \"$dir\" && ");
		sb.append("\n");
		sb.append("mv -f \"$@\"");
		sb.append("\n");
		sb.append("}");
		sb.append("\n");
		addToFile(sb.toString());
	}

	@Override
	protected void writeHeader() {
		super.writeHeader();
		addMVPFunction();
	}

}
