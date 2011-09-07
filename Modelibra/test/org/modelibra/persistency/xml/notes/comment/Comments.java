package org.modelibra.persistency.xml.notes.comment;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/**
 * Comment entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
public class Comments extends Entities<Comment> {

	/**
	 * Constructs comments within the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Comments(IDomainModel model) {
		super(model);
	}

	/**
	 * Retrieves the comment with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return url
	 */
	public Comment getComment(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the comment with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return url
	 */
	public Comment getComment(Long oidUniqueNumber) {
		return getComment(new Oid(oidUniqueNumber));
	}

	/**
	 * Selects comments whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return comments
	 */
	public Comments getComments(String propertyCode, Object property) {
		return (Comments) selectByProperty(propertyCode, property);
	}

	/**
	 * Creates comment.
	 * 
	 * @param text
	 *            text
	 * @return comment
	 */
	public Comment createComment(String text) {
		Comment comment = new Comment(getModel());
		comment.setText(text);
		if (!add(comment)) {
			comment = null;
		}
		return comment;
	}

}