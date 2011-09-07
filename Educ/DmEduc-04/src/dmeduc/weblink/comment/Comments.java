package dmeduc.weblink.comment;

import java.util.Comparator;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;
import org.modelibra.type.EasyDate;

/**
 * Comment entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-08-29
 */
@SuppressWarnings("serial")
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
	 * Retrieves the first comment whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return comment
	 */
	public Comment getComment(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
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
	 * Gets comments ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered comments
	 */
	public Comments getComments(String propertyCode, boolean ascending) {
		return (Comments) orderByProperty(propertyCode, ascending);
	}

	/**
	 * Gets comments selected by a selector. Returns empty comments if there are
	 * no comments that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected comments
	 */
	public Comments getComments(ISelector selector) {
		return (Comments) selectBySelector(selector);
	}

	/**
	 * Gets comments ordered by a comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered comments
	 */
	public Comments getComments(Comparator<Comment> comparator,
			boolean ascending) {
		return (Comments) orderByComparator(comparator, ascending);
	}

	/**
	 * Gets comments that have the same, given source.
	 * 
	 * @return selected comments
	 */
	public Comments getSourceComments(String source) {
		return getComments("source", source);
	}

	/**
	 * Gets comments without source.
	 * 
	 * @return selected comments
	 */
	public Comments getCommentsWithoutSource() {
		PropertySelector propertySelector = new PropertySelector("source");
		propertySelector.defineNull();
		return getComments(propertySelector);
	}

	/**
	 * Gets comments selected by a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected comments
	 */
	public Comments getKeywordComments(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContain(keyword);
		return getComments(propertySelector);
	}

	/**
	 * Gets comments that begin with a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected comments
	 */
	public Comments getBeginKeywordComments(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineBegin(keyword);
		return getComments(propertySelector);
	}

	/**
	 * Gets comments ordered by creation date.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered comments
	 */
	public Comments getCommentsOrderedByCreationDate(boolean ascending) {
		return getComments("creationDate", ascending);
	}

	/**
	 * Gets comments ordered by source.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered comments
	 */
	public Comments getCommentsOrderedBySource(boolean ascending) {
		return getComments("source", ascending);
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

	/**
	 * Creates comment.
	 * 
	 * @param text
	 *            text
	 * @param easyDate
	 *            easy date
	 * @return comment
	 */
	public Comment createComment(String text, EasyDate easyDate) {
		Comment comment = new Comment(getModel());
		comment.setText(text);
		comment.setCreationDate(easyDate.getDate());
		if (!add(comment)) {
			comment = null;
		}
		return comment;
	}

}