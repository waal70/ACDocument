/**
 * 
 */
package org.waal70.utils.document;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.convenience.Helper;
import org.waal70.utils.document.gui.ACDocumentControllerImpl;
import org.waal70.utils.document.gui.SplashScreen;
import org.waal70.utils.document.io.DirectoryFileLister;

/**
 * @author awaal
 *
 */
public class Main {

	private static Logger log = LogManager.getLogger(Main.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// TODO: Third level on certain document types (pop-up?)
		// TODO: Handle displayname and category name for companies and users
		// TODO: add default users (NOBODY and ALL)
		log.debug("Started the program. Handing over to GUI: "); //$NON-NLS-1$
		log.info("Version that Log4J is on: " + log.getClass().getPackage().getImplementationVersion());

		doGUI();
	}

	private static void doGUI() {

		// To enable the splashscreen, I need to know how many files I am going to process:
		List<Path> pathList = DirectoryFileLister.listFiles();
		SplashScreen splash = new SplashScreen(0, pathList.size()+1);
		splash.setVisible(true);
		Helper.setSplash(splash);

		new ACDocumentControllerImpl();
		splash.dispose();
	}

}
