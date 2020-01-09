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

import org.apache.log4j.Logger;

/**
 * @author awaal
 *
 */
public class DirectoryFileLister {
	private static Logger log = Logger.getLogger(DirectoryFileLister.class);
	
	public static List<Path> listFiles()
	{
		String dirName = "/Users/awaal/TEMP/PDF/";

		/*
		 * Files.list(new File(dirName).toPath()) 
		 * 		.limit(10) 
		 * 		.forEach(path -> {
		 * 			log.info(path); });
		 */
		List<Path> result = new ArrayList<Path>();
		Stream<Path> str;
	try {
		str = Files.list(new File(dirName).toPath())
				   .filter(s -> s.toString().endsWith(".pdf"))
				   .limit(10);
        result = str.collect(Collectors.toList());
        str.close();
	} catch (IOException e) {
		log.error("Error building filelist: " + e.getLocalizedMessage());
		result.add(Paths.get(""));
		
	}
    		   
     

        return result;
     }
	}


