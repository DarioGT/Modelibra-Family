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
package dmeduc.weblink.category;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Category generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-07
 */
public abstract class GenCategories extends Entities<Category> {

	private static final long serialVersionUID = 1171894920504L;

	private static Log log = LogFactory.getLog(GenCategories.class);

	/* ======= internal parent neighbors ======= */

	private Category category;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs categories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCategories(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs categories for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public GenCategories(Category category) {
		this(category.getModel());
		// parent
		setCategory(category);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the category with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return category
	 */
	public Category getCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the category with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return category
	 */
	public Category getCategory(Long oidUniqueNumber) {
		return getCategory(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first category whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return category
	 */
	public Category getCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Retrieves the category in a reflexive hierarchy using a given oid unique
	 * number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return category
	 */
	public Category getReflexiveCategory(Long oidUniqueNumber) {
		Category category = getCategory(oidUniqueNumber);
		if (category == null) {
			for (Category currentCategory : this) {
				category = currentCategory.getCategories()
						.getReflexiveCategory(oidUniqueNumber);
				if (category != null) {
					break;
				}
			}
		}
		return category;
	}

	/**
	 * Selects categories whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return categories
	 */
	public Categories getCategories(String propertyCode, Object property) {
		return (Categories) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets categories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categories
	 */
	public Categories getCategories(String propertyCode, boolean ascending) {
		return (Categories) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets categories selected by a selector. Returns empty categories if there
	 * are no categories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected categories
	 */
	public Categories getCategories(ISelector selector) {
		return (Categories) selectBySelector(selector);
	}

	/**
	 * Gets categories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categories
	 */
	public Categories getCategories(Comparator comparator, boolean ascending) {
		return (Categories) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets name category.
	 * 
	 * @param name
	 *            name
	 * @return name category
	 */
	public Category getNameCategory(String name) {
		PropertySelector propertySelector = new PropertySelector("name");
		propertySelector.defineEqual(name);
		List<Category> list = getCategories(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets categories ordered by name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered categories
	 */
	public Categories getCategoriesOrderedByName(boolean ascending) {
		return getCategories("name", ascending);
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
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
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
	 * Creates category.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param name
	 *            name
	 * @return category
	 */
	public Category createCategory(Category categoryParent, String name) {
		Category category = new Category(getModel());
		category.setCategory(categoryParent);
		category.setName(name);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

}