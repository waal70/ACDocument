/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.convenience.DateUtils;
import org.waal70.utils.document.metadata.ACCoreProperties;
import org.waal70.utils.document.metadata.DublinCore;
import org.waal70.utils.document.metadata.Metadata;
import org.waal70.utils.document.metadata.PDF;

import com.adobe.internal.xmp.XMPException;

/**
 * @author awaal
 * This class persists the metadata back into the PDF file.
 * Please note this is not to be used generically:
 * 
 * It assumes the creation of a ACDocument.Metadata object, that
 * contains all "old" metadata as well. 
 * This metadata class handles all updates and additions to the
 * metadata.
 * 
 * This class therefore does not preserve the "old" metadata, but
 * rather replaces it with the contents of the ACDocument.Metadata
 * 
 * It handles four main metadata categories:
 * PDI: for PDFs before the 2.0 spec
 * XMP: for PDFs after or on the 2.0 spec.
 * 
 * For XMP the subschemas are Dublin Core, PDF and XMP basic.
 * 
 */

public class PDFMetaDataWriter {
	private static Logger log = LogManager.getLogger(PDFMetaDataWriter.class);
	
	private static Metadata md;

	/**
	 * Takes a ACDocument object, extracts the associated Metadata and persists it
	 * into the PDF. For now, it will ignore the (deprecated) PDI, but rather uses
	 * the XMP metadata model
	 * 
	 * @param document
	 * @throws XMPException
	 * @throws IOException
	 * @throws BadFieldValueException
	 * @throws TransformerException
	 */
	public static void persistMetadata(ACDocument document)
			throws XMPException, IOException, TransformerException, BadFieldValueException {

		PDDocument pdoc = null;
		
		log.info("persistMetadata called");
		if (document != null) {
			md = document.getMetadata();
			File fDoc = new File(document.getSourceFileName());
			pdoc = PDDocument.load(fDoc);
			if (md.containsPrefix(ACCoreProperties.PDI_PREFIX)) {
				//There "was" PDI, so ensure there will be PDI again:
				log.info("PDI markers found");
				pdoc.setDocumentInformation(persistPDI());
			}
			
			XMPMetadata xmpMetadata = persistXMP();
			log.info("After persistXMP");
			XmpSerializer xs = new XmpSerializer();
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			xs.serialize(xmpMetadata, bos, true);
			
			File fDocT = new File(document.getSourceFileName() + "meta.pdf");
			
			PDMetadata pd_merged;
		
	
			pd_merged = new PDMetadata(pdoc);
			
			pd_merged.importXMPMetadata(bos.toByteArray());
			
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			pcat.setMetadata(pd_merged);
			
			pdoc.setAllSecurityToBeRemoved(true);
			pdoc.save(fDocT);
			log.info("Done setting metadata");
		}

		else
			log.error("No document supplied. Nothing to persist.");

		// required info: the PDF file
		// the ACDocument.Metadata

	}
	
	private static PDDocumentInformation persistPDI() {
		log.info("Persisting PDI (for all but two properties deprecated since 2.0!)");
		//For 2.0 and above, only set CreationDate and ModDate
		// For versions below, feel free to set more...
		// For now, loop through everything with a PDI prefix:
		
		PDDocumentInformation info = new PDDocumentInformation();
		//String[] loop = md.namesWithPrefix(ACCoreProperties.PDI_PREFIX);
		
		//2.0 properties:
		
		DateUtils du = new DateUtils();
		Calendar c = Calendar.getInstance();
		//CreationDate
		c.setTime(du.tryToParse(md.get(ACCoreProperties.PDI_PREFIX+"CreationDate")));
		info.setCreationDate(c);
		
		//ModDate:
		c.setTime(du.tryToParse(md.get(ACCoreProperties.PDI_PREFIX+"ModDate")));
		info.setModificationDate(c);
		
		//deprecated properties:
		info.setAuthor(md.get(ACCoreProperties.PDI_PREFIX+"Author"));
		info.setCreator(md.get(ACCoreProperties.PDI_PREFIX+"Creator"));
		info.setKeywords(md.get(ACCoreProperties.PDI_PREFIX+"Keywords"));
		info.setProducer(md.get(ACCoreProperties.PDI_PREFIX+"Producer"));
		info.setSubject(md.get(ACCoreProperties.PDI_PREFIX+"Subject"));
		info.setTitle(md.get(ACCoreProperties.PDI_PREFIX+"Title"));
		
		
		//custom properties:
		String[] customMD = new String[md.namesWithPrefix(ACCoreProperties.AC_META_PREFIX).length];
		customMD = md.namesWithPrefix(ACCoreProperties.AC_META_PREFIX);
		for (int i=0; i<customMD.length;i++)
		{
			info.setCustomMetadataValue(customMD[i], md.get(customMD[i]));
		}
		return info;
		
	}

	private static XMPMetadata persistXMP() throws TransformerException, IOException, BadFieldValueException {
		//COSStream cosStream = new COSStream();
		log.info("Setting XMP metadata (title, author, subject) for merged PDF");
		
		XMPMetadata xmpMetadata = XMPMetadata.createXMPMetadata();
		
		
		// PDF/A-1b properties
		PDFAIdentificationSchema pdfaSchema = xmpMetadata.createAndAddPFAIdentificationSchema();
		pdfaSchema.setPart(1);
		pdfaSchema.setConformance("B");

		// Dublin Core properties
		String localPrefix = DublinCore.PREFIX_DC + ACCoreProperties.NAMESPACE_PREFIX_DELIMITER;
		DublinCoreSchema dublinCoreSchema = xmpMetadata.createAndAddDublinCoreSchema();
		//Guaranteed values:
		dublinCoreSchema.setTitle(md.get(localPrefix + DublinCoreSchema.TITLE));
		dublinCoreSchema.addCreator(md.get(localPrefix + DublinCoreSchema.CREATOR));
		dublinCoreSchema.setDescription(md.get(localPrefix + DublinCoreSchema.DESCRIPTION));
		dublinCoreSchema.addSubject(md.get(localPrefix + DublinCoreSchema.SUBJECT) != null ? md.get(localPrefix + DublinCoreSchema.SUBJECT) : ""  );
		
		String curval = null;
		//Non guaranteed:
		curval = md.get(localPrefix + DublinCoreSchema.CONTRIBUTOR);
		if (curval != null)
			dublinCoreSchema.addContributor(md.get(localPrefix + DublinCoreSchema.CONTRIBUTOR));
		curval = md.get(localPrefix + DublinCoreSchema.COVERAGE);
		if (curval != null)
			dublinCoreSchema.setCoverage(curval);
		curval = md.get(localPrefix + DublinCoreSchema.RELATION);
		if (curval != null)
		dublinCoreSchema.addRelation(curval);
		curval = md.get(localPrefix + DublinCoreSchema.FORMAT);
		if (curval != null)
		dublinCoreSchema.setFormat(curval);
		curval = md.get(localPrefix + DublinCoreSchema.IDENTIFIER);
		if (curval != null)
		dublinCoreSchema.setIdentifier(curval);
		curval = md.get(localPrefix + DublinCoreSchema.SOURCE);
		if (curval != null)
		dublinCoreSchema.setSource(curval);
		curval = md.get(localPrefix + DublinCoreSchema.PUBLISHER);
		if (curval != null)
		dublinCoreSchema.addPublisher(curval);
		curval = md.get(localPrefix + DublinCoreSchema.LANGUAGE);
		if (curval != null) {
			dublinCoreSchema.addLanguage(curval);
			curval = md.get(localPrefix + DublinCoreSchema.RIGHTS);
			if (curval != null)
				dublinCoreSchema.addRights(md.get(localPrefix + DublinCoreSchema.LANGUAGE), curval);
		}
		
		
		AdobePDFSchema aps = xmpMetadata.createAndAddAdobePDFSchema();
		
		aps.setKeywords(md.get(PDF.DOC_INFO_KEY_WORDS) != null ? md.get(PDF.DOC_INFO_KEY_WORDS): dublinCoreSchema.getSubjects().get(0));
		aps.setPDFVersion(md.get(PDF.PDF_VERSION));
		aps.setProducer(md.get(PDF.DOC_INFO_CREATOR)!= null ? md.get(PDF.DOC_INFO_CREATOR): md.get(localPrefix + DublinCoreSchema.CREATOR));

		// Let us save the new document somewhere...

		// XMP Basic properties
		XMPBasicSchema basicSchema = xmpMetadata.createAndAddXMPBasicSchema();
		Calendar creationDate = Calendar.getInstance();
		// basicSchema.setCreateDate(creationDate);
		basicSchema.setModifyDate(creationDate);
		basicSchema.setMetadataDate(creationDate);
		basicSchema.setCreatorTool("AC Document Handler by Andre de Waal");
		basicSchema.setAboutAsSimple("about");
		// try
		
		// Create and return XMP data structure in XML format
		/*
		 * try (ByteArrayOutputStream xmpOutputStream = new ByteArrayOutputStream();
		 * OutputStream cosXMPStream = cosStream.createOutputStream()) { new
		 * XmpSerializer().serialize(xmpMetadata, xmpOutputStream, true);
		 * cosXMPStream.write(xmpOutputStream.toByteArray()); return new
		 * PDMetadata(cosStream); }
		 */
		return xmpMetadata;
	}
}
