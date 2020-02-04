/**
 * 
 */
package org.waal70.utils.document.io;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.ACDocumentFactory;
import org.waal70.utils.document.convenience.Helper;

/**
 * @author awaal
 *
 */
public class DocumentList extends DocumentQueue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1149990794295294435L;
	int i = 1;

	@Override
	public boolean add(ACDocument e) {
		
		return super.add(e);
	}

	private static Logger log = LogManager.getLogger(DocumentList.class);
	private static class SingletonHelper{
        private static final DocumentList INSTANCE = new DocumentList();
    }
    public static DocumentList getInstance(){
        return SingletonHelper.INSTANCE;
    }

	/**
	 * 
	 */
	private DocumentList() {
		super();
		populateList();
		log.debug("DocumentList constructed.");
	}
	
	private void populateList() {
		
		//the main directory should be a path
		log.debug("Going to construct a DocumentList...");
		List<Path> pathList = DirectoryFileLister.listFiles();
		//pathList now contains a list of filtered pdf files
		if (Helper.splash != null)
			Helper.splash.progress(i);
		
		pathList.forEach(path -> {
			try {
				log.info("Creating ACDocument-instance for: " + path);
				this.add(ACDocumentFactory.getACDocument(path));
				if (Helper.splash != null)
				{
					i++;
					Helper.splash.progress(i);
				}
			} catch (Exception e) {
				log.error("Unable to populate queue: " + e.getLocalizedMessage());
			}
		});
		log.info("Number of ACDocuments queued: " + this.size());
		
	}
	
	public ACDocument get() {
		//This method returns a Document
		log.debug("ACDocument get requested.");
		
		return this.poll();
		
	}

}
