/**
 * 
 */
package org.waal70.utils.document.io;


/**
 * @author awaal
 *
 */
public class DocumentReadyList extends DocumentQueue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1215296038913732733L;

	private static class SingletonHelper{
        private static final DocumentReadyList INSTANCE = new DocumentReadyList();
    }
    public static DocumentReadyList getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
