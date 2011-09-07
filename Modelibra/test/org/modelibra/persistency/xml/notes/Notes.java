package org.modelibra.persistency.xml.notes;

import org.modelibra.IDomain;
import org.modelibra.DomainModel;
import org.modelibra.persistency.xml.notes.comment.Comments;

/**
 * Notes domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-23
 */
public class Notes extends DomainModel {

	private Comments comments;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Notes(IDomain domain) {
		super(domain);
		comments = new Comments(this);
	}

	/**
	 * Gets Comment entities.
	 * 
	 * @return Comment entities
	 */
	public Comments getComments() {
		return comments;
	}

}
