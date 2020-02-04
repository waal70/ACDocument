/**
 * 
 */
package org.waal70.utils.document.metadata;

import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.properties.XMPProperty;

/**
 * @author awaal
 *
 */
public class PDFMetadataExtracter {
	private static Logger log = LogManager.getLogger(PDFMetadataExtracter.class);
	private Metadata md = null;
	
	
	public PDFMetadataExtracter (Metadata metadata) {
		this.md = metadata;
	}
	
  	
	public Metadata doStuff(PDDocument pdoc) {

		if (pdoc.getVersion() < 2f) {
			// get information via
			PDDocumentInformation pdi = pdoc.getDocumentInformation();
			Set<String> ss = pdi.getMetadataKeys();

			for (String entry : ss)
				md.add(entry, pdi.getCustomMetadataValue(entry));

		} else {
			// get information via metadata
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			PDMetadata pmeta = pcat.getMetadata();

			// Try with Adobe library:
			XMPMeta xmp = null;
			try {
				xmp = XMPMetaFactory.parse(pmeta.createInputStream());
			} catch (XMPException e) {
				log.error("Invalid XMP XML, " + e.getLocalizedMessage());
			} catch (IOException e) { 
				log.error("Cannot read stream " + e.getLocalizedMessage());
			}
			if (xmp != null) {
				// Try and get the Dublin Core Metadata:
				extractDublinCore(xmp);

				// Try and get the regular PDF metadata:
				extractPDFMetadata(xmp);

				// Try and get the XMP basic metadata:
				extractXMPMetadata(xmp);
			}
		}
		return md;
	}

	private void extractMetadata(XMPMeta xmp, String namespace, String prefix, String[] cycleOptions) {

		for (String optionName : cycleOptions) {

			XMPProperty xmpp;
			try {
				xmpp = xmp.getProperty(namespace, optionName);

				if (xmpp != null) {
					PropertyOptions propOptions = xmpp.getOptions();
					if (propOptions.isArray())
						md.add(prefix + optionName, xmp.getArrayItem(namespace, optionName, XMPConst.ARRAY_LAST_ITEM).getValue());
					else 
						md.add(prefix + optionName, xmp.getPropertyString(namespace, optionName));
				}
			} catch (XMPException e) {
				log.error("Cannot parse Metadata. " + e.getLocalizedMessage());
			}

		}
	}

	/**
	 * @param xmp Retrieves the XMP Basic metadata
	 * @throws XMPException
	 */
	private void extractXMPMetadata(XMPMeta xmp) {

		// xmp:CreateDate, xmp:CreatorTool, xmp:ModifyDate, xmp:metadataDate
		// Source:
		// https://wwwimages2.adobe.com/content/dam/acom/en/devnet/xmp/pdfs/XMP%20SDK%20Release%20cc-2016-08/XMPSpecificationPart1.pdf
		log.debug("Extracting XMP metadata.");
		String[] cycleOptions = { "CreateDate", "CreatorTool", "Identifier", "Label", "MetadataDate", "ModifyDate",
				"Rating" };
		extractMetadata(xmp, XMPConst.NS_XMP, ACCoreProperties.XMP_PREFIX, cycleOptions);

	}

	private void extractPDFMetadata(XMPMeta xmp) {

		log.debug("Extracting PDF metadata.");
		String[] cycleOptions = { AdobePDFSchema.KEYWORDS, AdobePDFSchema.PDF_VERSION, AdobePDFSchema.PRODUCER };

		extractMetadata(xmp, XMPConst.NS_PDF, PDF.PDF_PREFIX, cycleOptions);
	}

	private void extractDublinCore(XMPMeta xmp) {
		log.debug("Extracting Dublin Core Metadata.");
		String[] cycleOptions = { DublinCoreSchema.CONTRIBUTOR, DublinCoreSchema.COVERAGE, DublinCoreSchema.CREATOR,
				DublinCoreSchema.DATE, DublinCoreSchema.DESCRIPTION, DublinCoreSchema.FORMAT,
				DublinCoreSchema.IDENTIFIER, DublinCoreSchema.LANGUAGE, DublinCoreSchema.PUBLISHER,
				DublinCoreSchema.RELATION, DublinCoreSchema.RIGHTS, DublinCoreSchema.SOURCE, DublinCoreSchema.SUBJECT,
				DublinCoreSchema.TITLE, DublinCoreSchema.TYPE };

		extractMetadata(xmp, XMPConst.NS_DC, DublinCore.PREFIX_DC, cycleOptions);

	}
	
}
