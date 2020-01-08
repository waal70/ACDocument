/**
 * 
 */
package org.waal70.utils.document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.xml.XmpParsingException;

/**
 * @author awaal
 *
 */
public class ACDocumentFactory {
	private static Logger log = Logger.getLogger(ACDocumentFactory.class);

	public static ACDocument getACDocument(Path path) throws IOException {
		
		log.info("In Document Factory");
		
		ACDocument newDoc = new ACDocument();
		
		String[] args = new String[] {path.toFile().toString()};
		//String[] args = [];
		
		
		try {
			ExtractMetadata.main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmpParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PDDocument pdoc = PDDocument.load(path.toFile());
		
		//in case of version being lower than 2.0, use documentinformation
		//After that, use metadata

		log.info("Processing a PDF with " + pdoc.getNumberOfPages() + " pages, according to " + pdoc.getVersion() + " spec");
		
		
		if (pdoc.getVersion() < 2) 
		{
			//get information via
			PDDocumentInformation pdi = pdoc.getDocumentInformation();
			log.info("Properties according to pdi: " + pdi.toString());
		}
		else
		{
			//get information via metadata
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			PDMetadata pmeta = pcat.getMetadata();
			log.info("stream length: " + pmeta.getDecodedStreamLength());
			log.info("Buffered reader");
			
		      BufferedReader reader = new BufferedReader(new InputStreamReader(pmeta.createInputStream()));
		      StringBuffer sb = new StringBuffer();
		      String str = "";
		      while((str = reader.readLine())!= null){
		         sb.append(str);
		      }
		      log.info(sb.toString());
			
		}
		

		
		

		
		//pdoc.get
		
		
		
		return newDoc;
	}

}
