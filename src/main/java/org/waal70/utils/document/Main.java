/**
 * 
 */
package org.waal70.utils.document;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.gui.ACDocumentController;
import org.waal70.utils.document.gui.ACDocumentControllerImpl;
import org.waal70.utils.document.gui.SplashScreen;

/**
 * @author awaal
 *
 */
public class Main {
	
	private static Logger log = LogManager.getLogger(Main.class);
	//private static Logger log = Logger.getLogger(Main.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//System.setProperty("log4j.configurationFile","resources/log4j.properties");
		//System.out.println("Logfile is: " + System.getProperty("log4j.configurationFile"));
		log.error("Hello, you started the main class, now doing document window");
		log.info("Irideos zakelijk has: " + Archive.DocumentType.Iri_zak.getChildren().length);
		log.info("ROOT has: " + Archive.DocumentType.ROOT.getChildren().length);
		log.info(Archive.DocumentType.Iri_zak.getPath());
		
		//DocumentList dl = new DocumentList(DirectoryLister.listFiles());
		//DirectoryLister.listFiles();
		
		//Hand-over to gui:
		log.info("Before ControllerImpl");
		SplashScreen splash = new SplashScreen();
		splash.setVisible(true);
		@SuppressWarnings("unused")
		ACDocumentController controller = new ACDocumentControllerImpl();
		splash.dispose();
		log.info("After ControllerImpl");
		//ACDocumentView.main(args);
	

	}
	/*String filename = new String("/Users/awaal/TEMP/EDM_Other Service Lines.pdf");
	File PDF_Path = new File(filename);
	PDDocument inputPDF = PDDocument.load(PDF_Path);
	PDPageTree allPages = inputPDF.getPages();
	//inputPDF.close();
	PDPage testPage = (PDPage)allPages.get(0);
	
	PDFRenderer pdfr = new PDFRenderer(inputPDF);
	ImageIcon bi = new ImageIcon(pdfr.renderImage(0));
			
	JFrame testFrame = new JFrame();
	testFrame.setBounds(100, 100, 619, 444);
	testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.setBounds(18, 125, 61, 16);
	testFrame.add(lblNewLabel);
	
	lblNewLabel.setIcon(bi);
	
	//testFrame.setBounds(40, 40);
	testFrame.setVisible(true);
	*/

}
