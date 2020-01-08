/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * @author awaal
 *
 */
public class DirectoryLister {
	private static Logger log = Logger.getLogger(DirectoryLister.class);
	
	public static List<Path> listFiles() throws IOException
	{
		String dirName = "/Users/awaal/TEMP/PDF/";

        Files.list(new File(dirName).toPath())
                .limit(10)
                .forEach(path -> {
                    log.info(path);
                });
    
       Stream<Path> str = Files.list(new File(dirName).toPath())
    		   .filter(s -> s.toString().endsWith(".pdf"))
    		   .limit(10);
    		   
        
        List<Path> result = str.collect(Collectors.toList());
        str.close();
        return result;
     }
	}


