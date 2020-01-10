/**
 * 
 */
package org.waal70.utils.document;

import java.io.Serializable;

/**
 * @author awaal
 *
 */
public class GenericDocument implements Serializable {

	private static final long serialVersionUID = 1L;

	protected long created = 0;
	protected String path;
	protected String author;
	protected int permissions;
	protected String uuid;
	protected boolean subscribed;
	//protected Set<String> subscriptors = new HashSet<String>();
	//protected Set<String> keywords = new HashSet<String>();
	//protected Set<Folder> categories = new HashSet<Folder>();
	//protected List<Note> notes = new ArrayList<Note>();

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	//public Set<String> getSubscriptors() {
	//	return subscriptors;
	//}

	//public void setSubscriptors(Set<String> subscriptors) {
	//	this.subscriptors = subscriptors;
	//}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	//public List<Note> getNotes() {
	//	return notes;
	//}

	//public void setNotes(List<Note> notes) {
	//	this.notes = notes;
	//}

	//public Set<String> getKeywords() {
	//	return keywords;
	//}

	//public void setKeywords(Set<String> keywords) {
	//	this.keywords = keywords;
	//}

	//public Set<Folder> getCategories() {
	//	return categories;
	//}

	//public void setCategories(Set<Folder> categories) {
	//	this.categories = categories;
	//}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("path=").append(path);
		sb.append(", permissions=").append(permissions);
		sb.append(", created=").append(created);
		sb.append(", subscribed=").append(subscribed);
	//	sb.append(", subscriptors=").append(subscriptors);
		sb.append(", uuid=").append(uuid);
		//sb.append(", keywords=").append(keywords);
		//sb.append(", categories=").append(categories);
		//sb.append(", notes=").append(notes);
		sb.append("}");
		return sb.toString();
	}
}

