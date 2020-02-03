/**
 * 
 */
package org.waal70.utils.document.parse;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Property;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.convenience.DateUtils;
import org.waal70.utils.document.metadata.ACCoreProperties;
import org.waal70.utils.document.metadata.ACCoreProperties.EmbeddedResourceType;
import org.waal70.utils.document.metadata.Metadata;

/**
 * @author awaal Wraps PDDocument to parse ACDocument-like PDFs
 */
public class PDFParser {
	private static Logger log = LogManager.getLogger(PDFParser.class);
	
	private ACDocument newDoc = new ACDocument();

	/**
	 * @return the newDoc
	 */
	public ACDocument getDocument() {
		return newDoc;
	}

	public PDFParser(Path path) throws IOException {
		log.info("PDF Parser created");
		parse(path);
	}

	private void parse(Path path) throws IOException {

		Metadata md = new Metadata();
		PDDocument pdoc;
		pdoc = PDDocument.load(path.toFile());
	
		newDoc.setPreview(createPreviewImage(pdoc, true));
		md.set(ACCoreProperties.EMBEDDED_RESOURCE_TYPE, EmbeddedResourceType.ATTACHMENT.toString());
		md.set(ACCoreProperties.EMBEDDED_RESOURCE_TYPE_KEY, "preview");
		md.setPreview(newDoc.getPreview());
		
		newDoc.setTitle(path.toFile().getName());
		
		md.set(Metadata.DOC_INFO_TITLE, path.toFile().getName());
		md.set(ACCoreProperties.TITLE, path.toFile().getName());
		
		newDoc.setCreated(path.toFile().lastModified());
		Date ff = new Date(path.toFile().lastModified());
		md.set(ACCoreProperties.CREATED, DateUtils.formatDate(ff));
		
		newDoc.setTargetDated(new Date());
		md.set(ACCoreProperties.DATED, DateUtils.formatDate(new Date()));

		newDoc.setPdfVersion(String.valueOf(pdoc.getVersion()));
		md.set(Metadata.PDF_VERSION, String.valueOf(pdoc.getVersion()));
		
		
		newDoc.setNumPages(pdoc.getNumberOfPages());
		md.set(ACCoreProperties.DOC_INFO_PAGES, pdoc.getNumberOfPages() );

		
		newDoc.setFileSize(path.toFile().length() / (1024 * 1024) + "MB");
		md.set(ACCoreProperties.DOC_INFO_SIZE, path.toFile().length() / (1024 * 1024) + "MB");
		log.info("All recorded metadata: " + md.toString());
		
		newDoc.setMetadata(md);
		
		log.info("Processing a PDF with " + pdoc.getNumberOfPages() + " pages, according to " + pdoc.getVersion()
				+ " spec");

		if (pdoc.getVersion() < 2) {
			// get information via
			PDDocumentInformation pdi = pdoc.getDocumentInformation();
			log.info("Properties according to pdi: " + pdi.toString());
			log.info("Author: " + pdi.getAuthor());
			log.info("getCreator: " + pdi.getCreator());
			log.info("getKeywords: " + pdi.getKeywords());
			log.info("getProducer: " + pdi.getProducer());
			log.info("getSubject: " + pdi.getSubject());
			log.info("getTitle: " + pdi.getTitle());
			log.info("getTrapped: " + pdi.getTrapped());
			log.info("getCreationDate: " + pdi.getCreationDate().toString());
			log.info("getModificationDate: " + pdi.getModificationDate());
			Set<String> ss = pdi.getMetadataKeys();
			for (String entry : ss) {
				log.info("mdkeys: " + entry.toString());
			}

		} else {
			// get information via metadata
			PDDocumentCatalog pcat = pdoc.getDocumentCatalog();
			PDMetadata pmeta = pcat.getMetadata();
			log.info("stream length: " + pmeta.getDecodedStreamLength());
			log.info("Buffered reader");

			BufferedReader reader = new BufferedReader(new InputStreamReader(pmeta.createInputStream()));
			StringBuffer sb = new StringBuffer();
			String str = "";
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			log.info(sb.toString());

		}
		pdoc.close();
		// return newDoc;
	}

	private static BufferedImage createPreviewImage(PDDocument doc, boolean allPages) {
		// This method will create an image for all pages and
		// concat this to one big BufferedImage.
		// try and save the preview:
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

		// Calculate the final total height required:
		int heightTotal = 0;
		for (int j = 0; j < pageImages.length; j++) {
			heightTotal += pageImages[j].getHeight();
		}

		// Now, allocate a new BufferedImage that will contain all images:
		int heightCurr = 0;
		BufferedImage concatImage = new BufferedImage(maxWidth, heightTotal, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = concatImage.createGraphics();
		for (int j = 0; j < pageImages.length; j++) {
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
