/**
 * 
 */
package org.waal70.utils.document;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.parse.PDFParser;
import org.xml.sax.SAXException;

import com.adobe.internal.xmp.XMPException;

/**
 * @author awaal
 *
 */
public class ACDocumentFactory {
	private static Logger log = LogManager.getLogger(ACDocumentFactory.class);

	public static ACDocument getACDocument(Path path) throws IOException, ParserConfigurationException, SAXException, XMPException {
		
		log.info("In Document Factory for " + path.toString());
		
		if (path.toFile().toString().toLowerCase().endsWith("pdf"))
		{
			PDFParser pp = new PDFParser(path);
			return pp.getDocument();
		}
		return null;
			
		

}
}
