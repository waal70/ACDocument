/**
 * 
 */
package org.waal70.utils.document;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.parse.PDFParser;

/**
 * @author awaal
 *
 */
public class ACDocumentFactory {
	private static Logger log = LogManager.getLogger(ACDocumentFactory.class);

	public static ACDocument getACDocument(Path path) {
		
		log.debug("In Document Factory for " + path.toString());
		
		if (path.toFile().toString().toLowerCase().endsWith("pdf"))
		{
			PDFParser pp = new PDFParser(path);
			return pp.getDocument();
		}
		return null;
			
		

}
}
