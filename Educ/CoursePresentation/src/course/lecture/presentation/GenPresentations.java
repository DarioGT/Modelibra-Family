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
package course.lecture.presentation;

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
 * Presentation generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenPresentations extends Entities<Presentation> {

	private static final long serialVersionUID = 1176413488811L;

	private static Log log = LogFactory.getLog(GenPresentations.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs presentations within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPresentations(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the presentation with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return presentation
	 */
	public Presentation getPresentation(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the presentation with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return presentation
	 */
	public Presentation getPresentation(Long oidUniqueNumber) {
		return getPresentation(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first presentation whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return presentation
	 */
	public Presentation getPresentation(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects presentations whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return presentations
	 */
	public Presentations getPresentations(String propertyCode, Object property) {
		return (Presentations) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets presentations ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered presentations
	 */
	public Presentations getPresentations(String propertyCode, boolean ascending) {
		return (Presentations) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets presentations selected by a selector. Returns empty presentations if
	 * there are no presentations that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected presentations
	 */
	public Presentations getPresentations(ISelector selector) {
		return (Presentations) selectBySelector(selector);
	}

	/**
	 * Gets presentations ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered presentations
	 */
	public Presentations getPresentations(Comparator comparator,
			boolean ascending) {
		return (Presentations) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets title presentations.
	 * 
	 * @param title
	 *            title
	 * @return title presentations
	 */
	public Presentations getTitlePresentations(String title) {
		PropertySelector propertySelector = new PropertySelector("title");
		propertySelector.defineEqual(title);
		return getPresentations(propertySelector);
	}

	/**
	 * Gets author presentations.
	 * 
	 * @param author
	 *            author
	 * @return author presentations
	 */
	public Presentations getAuthorPresentations(String author) {
		PropertySelector propertySelector = new PropertySelector("author");
		propertySelector.defineEqual(author);
		return getPresentations(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets presentations ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered presentations
	 */
	public Presentations getPresentationsOrderedByCode(boolean ascending) {
		return getPresentations("code", ascending);
	}

	/**
	 * Gets presentations ordered by title.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered presentations
	 */
	public Presentations getPresentationsOrderedByTitle(boolean ascending) {
		return getPresentations("title", ascending);
	}

	/**
	 * Gets presentations ordered by author.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered presentations
	 */
	public Presentations getPresentationsOrderedByAuthor(boolean ascending) {
		return getPresentations("author", ascending);
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
	 * Creates presentation.
	 * 
	 * @param code
	 *            code
	 * @param title
	 *            title
	 * @param author
	 *            author
	 * @return presentation
	 */
	public Presentation createPresentation(String code, String title,
			String author) {
		Presentation presentation = new Presentation(getModel());
		presentation.setCode(code);
		presentation.setTitle(title);
		presentation.setAuthor(author);
		if (!add(presentation)) {
			presentation = null;
		}
		return presentation;
	}

}