/**
 * 
 */
package org.waal70.utils.document;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.gui.ACDocumentController;
import org.waal70.utils.document.gui.ACDocumentControllerImpl;
import org.waal70.utils.document.gui.SplashScreen;
import org.waal70.utils.document.io.ReadCSV;

/**
 * @author awaal
 *
 */
public class Main {

	private static Logger log = LogManager.getLogger(Main.class);
	// private static Logger log = Logger.getLogger(Main.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		log.error("Hello, you started the main class, now doing document window");
		log.info("Irideos zakelijk has: " + Archive.DocumentType.Iri_zak.getChildren().length);
		log.info("ROOT has: " + Archive.DocumentType.ROOT.getChildren().length);
		log.info(Archive.DocumentType.Iri_zak.getPath());
		
		log.info("Combo list is: " + Arrays.toString(Archive.DocumentType.getCategoryForCombo()));
		
		log.info(Archive.DocumentType.valueOf("ALGEMEEN").getOnlyPath());
		
		//ReadCSV myCSV = new ReadCSV();
		//myCSV.populateCompanyList();
		
		doGUI();
		

		

	}
	
	private static void doGUI() {
		// Hand-over to gui:
				log.info("Before ControllerImpl");
				SplashScreen splash = new SplashScreen();
				splash.setVisible(true);
				@SuppressWarnings("unused")
				ACDocumentController controller = new ACDocumentControllerImpl();
				splash.dispose();
				log.info("After ControllerImpl");
		
	}

}
