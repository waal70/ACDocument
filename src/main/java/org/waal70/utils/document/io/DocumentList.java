/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.ACDocument;
import org.waal70.utils.document.ACDocumentFactory;

/**
 * @author awaal
 *
 */
public class DocumentList extends ConcurrentLinkedQueue<ACDocument> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1149990794295294435L;

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
		log.info("DocumentList constructed.");
	}
	
	private void populateList() {
		
		//the main directory should be a path
		log.info("Going to construct a DocumentList...");
		List<Path> pathList = DirectoryFileLister.listFiles();
		//pathList now contains a list of filtered pdf files
		
		pathList.forEach(path -> {
			try {
				log.info("Now populating Document instance for: " + path);
				this.add(ACDocumentFactory.getACDocument(path));
			} catch (IOException e) {
				log.error("Unable to populate queue: " + e.getLocalizedMessage());
			}
		});
		log.info("End of populateList. Number of docs queued: " + this.size());
		
	}
	
	public ACDocument get() {
		//This method returns a Document
		log.info("ACDocument get requested.");
		
		return this.poll();
		
	}

}
