/**
 * 
 */
package org.waal70.utils.document.io;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.waal70.utils.document.ACDocument;

/**
 * @author awaal
 *
 */
public class DocumentQueue extends ConcurrentLinkedQueue<ACDocument>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2910161225593114869L;
	
    
	public DocumentQueue() {
		super();
	}
	
	public ACDocument get() {

		return this.poll();
		
	}
	
	@Override
	public boolean add(ACDocument e) {
		
		return super.add(e);
	}

}
