/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.metadata.Metadata;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.properties.XMPProperty;

/**
 * @author awaal
 *
 */
public class PDFMetaDataWriter {
	private static Logger log = LogManager.getLogger(PDFMetaDataWriter.class);
	
	/**
	 * Takes a ACDocument object, extracts the associated Metadata
	 *  and persists it into the PDF.
	 * For now, it will ignore the (deprecated) PDI, but rather
	 * uses the XMP metadata model
	 * @param document
	 * @throws XMPException 
	 * @throws IOException 
	 */
	public static void persistMetadata (ACDocument document) throws XMPException, IOException {
		
		Metadata md = null;
		PDDocument pdoc = null;
		
		log.info("persistMetadata called");
		if (document != null) {
			md = document.getMetadata();
			
			XMPMeta xmp = XMPMetaFactory.create();
			XMPProperty xmpp;
			String[] cycleOptions = { DublinCoreSchema.CONTRIBUTOR, DublinCoreSchema.COVERAGE, DublinCoreSchema.CREATOR,
					DublinCoreSchema.DATE, DublinCoreSchema.DESCRIPTION, DublinCoreSchema.FORMAT,
					DublinCoreSchema.IDENTIFIER, DublinCoreSchema.LANGUAGE, DublinCoreSchema.PUBLISHER,
					DublinCoreSchema.RELATION, DublinCoreSchema.RIGHTS, DublinCoreSchema.SOURCE, DublinCoreSchema.SUBJECT,
					DublinCoreSchema.TITLE, DublinCoreSchema.TYPE };

			xmp.setProperty(XMPConst.NS_PDF, DublinCoreSchema.TITLE, "Andre this works!");

			File fDoc = new File(document.getSourceFileName());
			pdoc = PDDocument.load(fDoc);
			PipedInputStream in = new PipedInputStream();
			  PipedOutputStream out = new PipedOutputStream(in);
			  new Thread(
			    new Runnable(){
			      public void run(){
			       try {
					XMPMetaFactory.serialize(xmp, out);
					out.close();
				} catch (XMPException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      }
			    }
			  ).start();
			  
			PDMetadata newMetadata = new PDMetadata(pdoc, (InputStream) in);
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			pcat.setMetadata( newMetadata );
			pdoc.setAllSecurityToBeRemoved(true);
			pdoc.save(fDoc);
			log.info("Done setting metadata");
		}
			
		else
			log.error("No document supplied. Nothing to persist.");
		
		
		//required info: the PDF file
		// the ACDocument.Metadata
		
	}

}
