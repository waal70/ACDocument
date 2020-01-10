/**
 * 
 */
package org.waal70.utils.document;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.xmpbox.xml.XmpParsingException;

/**
 * @author awaal
 *
 */
public class ACDocumentFactory {
	private static Logger log = LogManager.getLogger(ACDocumentFactory.class);

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
		
		newDoc.setPreview(createPreviewImage(pdoc, true));
		newDoc.setTitle(path.toFile().getName());
		
		newDoc.setCreated(path.toFile().lastModified());
        //document.close();
		
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
		pdoc.close();
		return newDoc;
	}
	
	private static BufferedImage createPreviewImage(PDDocument doc, boolean allPages) {
		//This method will create an image for all pages and
		// concat this to one big BufferedImage.
		//try and save the preview:
		if (!allPages)
			return createPreviewFirstPage(doc);
		PDFRenderer renderer = new PDFRenderer(doc);
		BufferedImage[] pageImages = new BufferedImage[doc.getNumberOfPages()];
		
		int maxWidth = 0;
		
		for (int page = 0; page < doc.getNumberOfPages(); ++page)
			try {
				pageImages[page] = renderer.renderImageWithDPI(page, 120, ImageType.RGB);
				if (pageImages[page].getWidth() > maxWidth)
					maxWidth = pageImages[page].getWidth();
			} catch (IOException e) {
				log.error("Error while creating preview");
			}
        		
		//Calculate the final total height required:
		int heightTotal = 0;
        for(int j = 0; j < pageImages.length ; j++) {
            heightTotal += pageImages[j].getHeight();
        }
        
        //Now, allocate a new BufferedImage that will contain all images:
        int heightCurr = 0;
        BufferedImage concatImage = new BufferedImage(maxWidth, heightTotal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < pageImages.length; j++) {
            g2d.drawImage(pageImages[j], 0, heightCurr, null);
            heightCurr += pageImages[j].getHeight();
        }
        g2d.dispose();
		
		return concatImage;
		
	}

	private static BufferedImage createPreviewFirstPage(PDDocument doc) {
		PDFRenderer renderer = new PDFRenderer(doc);
		try {
			return renderer.renderImageWithDPI(0, 120, ImageType.RGB);
		} catch (IOException e) {
			log.error("Error while creating first page preview");
		}
		return null;
		
	}

}
