/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package education.library.review;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

import education.library.book.Book;

/* ======= import external parent entity and entities classes ======= */

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * Review generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenReviews extends Entities<Review> {

	private static final long serialVersionUID = 1235857133372L;

	private static Log log = LogFactory.getLog(GenReviews.class);

	/* ======= internal parent neighbors ======= */

	private Book book;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs reviews within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenReviews(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs reviews for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public GenReviews(Book book) {
		this(book.getModel());
		// parent
		setBook(book);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the review with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return review
	 */
	public Review getReview(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the review with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return review
	 */
	public Review getReview(Long oidUniqueNumber) {
		return getReview(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first review whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return review
	 */
	public Review getReview(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects reviews whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return reviews
	 */
	public Reviews getReviews(String propertyCode, Object property) {
		return (Reviews) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets reviews ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered reviews
	 */
	public Reviews getReviews(String propertyCode, boolean ascending) {
		return (Reviews) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets reviews selected by a selector. Returns empty reviews if there are
	 * no reviews that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected reviews
	 */
	public Reviews getReviews(ISelector selector) {
		return (Reviews) selectBySelector(selector);
	}

	/**
	 * Gets reviews ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered reviews
	 */
	public Reviews getReviews(Comparator comparator, boolean ascending) {
		return (Reviews) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets reviews ordered by webLink.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered reviews
	 */
	public Reviews getReviewsOrderedByWebLink(boolean ascending) {
		return getReviews("webLink", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets book.
	 * 
	 * @param book
	 *            book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Gets book.
	 * 
	 * @return book
	 */
	public Book getBook() {
		return book;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates review.
	 * 
	 * @param bookParent
	 *            book parent
	 * @param webLink
	 *            webLink
	 * @return review
	 */
	public Review createReview(Book bookParent, String webLink) {
		Review review = new Review(getModel());
		review.setBook(bookParent);
		review.setWebLink(webLink);
		if (!add(review)) {
			review = null;
		}
		return review;
	}

}