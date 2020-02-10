/**
 * 
 */
package org.waal70.utils.document;

/**
 * @author awaal
 *
 */
public class TargetUser extends Entity {
	

public TargetUser() {
	super();
}

 public TargetUser(String name, String displayAs) {
	 this.setArchiveCode(name);
	 this.setDisplayName(displayAs);
 }
}
