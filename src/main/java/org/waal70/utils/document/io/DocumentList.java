/**
 * 
 */
package org.waal70.utils.document.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
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
		// TODO Auto-generated method stub
		return super.add(e);
	}

	private static Logger log = Logger.getLogger(DocumentList.class);
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
		log.info("DocumentList constructed.");
	}
	
	public DocumentList(List <Path> pathList) {
		log.info("Received a list of Paths");
		
		pathList.forEach(path -> {
			try {
				log.info("Now working on: " + path);
				this.add(ACDocumentFactory.getACDocument(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
	public ACDocument get() {
		//This method returns a CanMessage
		log.debug("ACDocument get requested.");

		return this.poll();
		
	}

}
