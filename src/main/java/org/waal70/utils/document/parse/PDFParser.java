/**
 * 
 */
package org.waal70.utils.document.parse;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.convenience.DateUtils;
import org.waal70.utils.document.metadata.ACCoreProperties.EmbeddedResourceType;
import org.waal70.utils.document.metadata.Metadata;
import org.waal70.utils.document.metadata.PDFMetadataExtracter;

/**
 * @author awaal Wraps PDDocument to parse ACDocument-like PDFs
 */
public class PDFParser {
	private static Logger log = LogManager.getLogger(PDFParser.class);

	private ACDocument newDoc = new ACDocument();
	private Metadata md = new Metadata();

	/**
	 * @return the newDoc
	 */
	public ACDocument getDocument() {
		return newDoc;
	}

	public PDFParser(Path path) {
		log.info("PDF Parser created");
		parse(path);
	}

	private void parse(Path path) {

		PDDocument pdoc = null;
		try {
			pdoc = PDDocument.load(path.toFile());
		} catch (IOException e) {
			log.error("Cannot load into PDDocument " + e.getLocalizedMessage());
		}
		if (pdoc != null) {
			newDoc.setPreview(createPreviewImage(pdoc, true));
			md.set(Metadata.EMBEDDED_RESOURCE_TYPE, EmbeddedResourceType.ATTACHMENT.toString());
			md.set(Metadata.EMBEDDED_RESOURCE_TYPE_KEY, "preview");
			md.setPreview(newDoc.getPreview());

			// newDoc.setTitle(path.toFile().getName());

			md.set(Metadata.DOC_INFO_TITLE, path.toFile().getName());
			md.set(Metadata.TITLE, path.toFile().getName());

			// newDoc.setTargetDated(path.toFile().lastModified());
			Date ff = new Date(path.toFile().lastModified());
			md.set(Metadata.CREATED, DateUtils.formatDate(ff));

			// newDoc.setTargetDated(new Date());
			md.set(Metadata.DATED, DateUtils.formatDate(new Date()));

			// newDoc.setPdfVersion(String.valueOf(pdoc.getVersion()));
			md.set(Metadata.PDF_VERSION, String.valueOf(pdoc.getVersion()));

			// newDoc.setNumPages(pdoc.getNumberOfPages());
			md.set(Metadata.DOC_INFO_PAGES, pdoc.getNumberOfPages());

			// newDoc.setFileSize(path.toFile().length() / (1024 * 1024) + "MB");
			md.set(Metadata.DOC_INFO_SIZE, path.toFile().length() / (1024 * 1024) + "MB");
			log.info("All recorded metadata ====================================================\n" + md.toString()
					+ "\n==========================================================================");
			newDoc.setMetadata(md);

			log.info("Processing a PDF with " + pdoc.getNumberOfPages() + " pages, according to " + pdoc.getVersion()
					+ " spec");

			PDFMetadataExtracter pme = new PDFMetadataExtracter();
			pme.doStuff(pdoc);
			try {
				pdoc.close();
			} catch (IOException e) {
				log.error("Cannot close PDDocument " + e.getLocalizedMessage());
			}
		}
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