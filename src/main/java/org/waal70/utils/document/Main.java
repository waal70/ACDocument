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
	// private static Logger log = Logger.getLogger(Main.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// TODO: Externalize settings or make it programmable
		// TODO: Third level on certain document types (pop-up?)
		// TODO: -> Algorithm to determine file name
		// TODO: Create batch file to move files or let the app do it
		// TODO: Run findbugs/spotbugs
		// TODO: Restructure packages/classes
		// TODO: ...
		log.info("Started the program. Handing over to GUI:");
		doGUI();
	}

	private static void doGUI() {
		// Hand-over to gui:
		SplashScreen splash = new SplashScreen();
		splash.setVisible(true);
		@SuppressWarnings("unused")
		ACDocumentController controller = new ACDocumentControllerImpl();
		splash.dispose();
	}

}
