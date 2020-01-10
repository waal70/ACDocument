/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author awaal
 *
 */
public class DirectoryFileLister {
	private static Logger log = LogManager.getLogger(DirectoryFileLister.class);

	public static List<Path> listFiles() {
		String dirname = "/Users/awaal/TEMP/PDF/";
		if (System.getProperty("os.name").startsWith("Windows"))
			dirname = "C:\\pdf\\";

		List<Path> result = new ArrayList<Path>();
		Stream<Path> str;
		try {
			str = Files.list(new File(dirname).toPath()).filter(s -> s.toString().endsWith(".pdf")).limit(10);
			result = str.collect(Collectors.toList());
			str.close();
		} catch (IOException e) {
			log.error("Error building filelist: " + e.getLocalizedMessage());
			result.add(Paths.get(""));

		}

		return result;
	}
}
