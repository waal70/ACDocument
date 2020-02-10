/**
 * 
 */
package org.waal70.utils.document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author awaal
 *
 */
public class EntityList {
	//contains the list of read companies
	private List<Entity> entities = new ArrayList<Entity>();
	
	public void addEntity(Entity ac) {
		this.entities.add(ac);
	}
	
	public String[] getListforCombo() {
		
		String[] result = new String[entities.size()];
		
		for (int i=0;i<entities.size();i++) 
			result[i] = entities.get(i).getDisplayName();
		return result;
	}
	
	public String getArchiveCodeforEntity(String entity) {
		String result = "";
		for (int i=0;i<entities.size();i++)
		{
			if (entities.get(i).getDisplayName().equals(entity))
				result = entities.get(i).getArchiveCode();
		}	
		return result;
			
	}
	
}
