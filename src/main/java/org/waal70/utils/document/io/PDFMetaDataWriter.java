/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Path;
import java.util.Calendar;

import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAExtensionSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;
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
	 * @throws BadFieldValueException 
	 * @throws TransformerException 
	 */
	public static void persistMetadata (ACDocument document) throws XMPException, IOException, TransformerException, BadFieldValueException {
		
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
			PDMetadata altMetadata = tryout();
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			pcat.setMetadata( altMetadata );
			
	        PDDocumentInformation info = new PDDocumentInformation();
	        //PDStream stream = new PDStream(pdoc);
	        info.setCustomMetadataValue("random-metadata", "hallo");

	        pdoc.setDocumentInformation(info);
			
			pdoc.setAllSecurityToBeRemoved(true);
			pdoc.save(fDoc);
			log.info("Done setting metadata");
		}
			
		else
			log.error("No document supplied. Nothing to persist.");
		
		
		//required info: the PDF file
		// the ACDocument.Metadata
		
	}
	
	private static PDMetadata tryout() throws TransformerException, IOException, BadFieldValueException {
		COSStream cosStream = new COSStream();  	
		log.info("Setting XMP metadata (title, author, subject) for merged PDF");
	        XMPMetadata xmpMetadata = XMPMetadata.createXMPMetadata();

	        // PDF/A-1b properties
	        PDFAIdentificationSchema pdfaSchema = xmpMetadata.createAndAddPFAIdentificationSchema();
	        pdfaSchema.setPart(1);
	        pdfaSchema.setConformance("B");

	        // Dublin Core properties
	        DublinCoreSchema dublinCoreSchema = xmpMetadata.createAndAddDublinCoreSchema();
	        dublinCoreSchema.setTitle("testt");
	        dublinCoreSchema.addCreator("creator");
	        dublinCoreSchema.setDescription("description");
	        //dublinCoreSchema.setTextPropertyValue("pdf:customofniet", "wezullenwelzien");

	        PDFAExtensionSchema pdfaext = xmpMetadata.createAndAddPDFAExtensionSchemaWithDefaultNS();
	        AdobePDFSchema aps=  xmpMetadata.createAndAddAdobePDFSchema();
	        aps.setKeywords("dit zijn de keywordsssssss");
	        aps.setTextPropertyValueAsSimple("simple", "simpelllll");

	        // Let us save the new document somewhere...
	        
	        
	        // XMP Basic properties
	        XMPBasicSchema basicSchema = xmpMetadata.createAndAddXMPBasicSchema();
	        Calendar creationDate = Calendar.getInstance();
	        //basicSchema.setCreateDate(creationDate);
	        basicSchema.setModifyDate(creationDate);
	        basicSchema.setMetadataDate(creationDate);
	        basicSchema.setCreatorTool("AC Document Handler by Andre de Waal");
	        basicSchema.setAboutAsSimple("about");
	        basicSchema.setTextPropertyValue("pdf:docinfo:Keywords", "dit zijn de keywords");
	        //basicSchema.setTextPropertyValue("xmp:customxmpofniet", "ookhier");

	        // Create and return XMP data structure in XML format
	        try (ByteArrayOutputStream xmpOutputStream = new ByteArrayOutputStream();
	             OutputStream cosXMPStream = cosStream.createOutputStream())
	        {
	            new XmpSerializer().serialize(xmpMetadata, xmpOutputStream, true);
	            cosXMPStream.write(xmpOutputStream.toByteArray());
	            return new PDMetadata(cosStream);
	        }
	    }
	}

