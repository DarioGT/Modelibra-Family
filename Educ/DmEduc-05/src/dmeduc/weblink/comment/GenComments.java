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
package dmeduc.weblink.comment;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Comment generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public abstract class GenComments extends Entities<Comment> {

	private static final long serialVersionUID = 1171894920499L;

	private static Log log = LogFactory.getLog(GenComments.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs comments within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenComments(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the comment with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return comment
	 */
	public Comment getComment(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the comment with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return comment
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

	/* ======= selector and order methods ======= */

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
	 * Gets comments ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered comments
	 */
	public Comments getComments(Comparator comparator, boolean ascending) {
		return (Comments) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets text comments.
	 * 
	 * @param text
	 *            text
	 * @return text comments
	 */
	public Comments getTextComments(String text) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineEqual(text);
		return getComments(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets comments ordered by text.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered comments
	 */
	public Comments getCommentsOrderedByText(boolean ascending) {
		return getComments("text", ascending);
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