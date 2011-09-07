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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.PropertyComparator;
import org.modelibra.PropertySelector;
import org.modelibra.util.CaseInsensitiveStringComparator;

/* ======= import external parent entity and entities classes ======= */

/**
 * Category specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-05
 */
public class Categories extends GenCategories {

	private static final long serialVersionUID = 1171894920506L;

	private static Log log = LogFactory.getLog(Categories.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs categories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Categories(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs categories for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public Categories(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets the category by a name.
	 * 
	 * @param name
	 *            name
	 * @return category
	 */
	public Category getCategoryByName(String name) {
		return getReflexiveCategory("name", name);
	}

	/**
	 * Gets approved categories.
	 * 
	 * @return selected categories
	 */
	public Categories getApprovedCategories() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.TRUE);
		return getCategories(propertySelector);
	}

	/**
	 * Gets not approved categories.
	 * 
	 * @return selected categories
	 */
	public Categories getNotApprovedCategories() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.FALSE);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected categories
	 */
	public Categories getKeywordCategories(String keyword) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContain(keyword);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain some keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected categories
	 */
	public Categories getSomeKeywordCategories(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainSome(keywords);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain all keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected categories
	 */
	public Categories getAllKeywordCategories(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainAll(keywords);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories ordered by name.
	 * 
	 * @return ordered categories
	 */
	public Categories getCategoriesOrderedByName() {
		return getCategories("name", true);
	}

	/**
	 * Gets categories ordered by name by ignoring the case of the name.
	 * 
	 * @return ordered categories
	 */
	public Categories getCategoriesOrderedByNameIgnoringCase() {
		CaseInsensitiveStringComparator caseInsensitiveStringComparator = new CaseInsensitiveStringComparator();
		PropertyComparator<Category> propertyComparator = new PropertyComparator<Category>(
				"name", caseInsensitiveStringComparator);
		return getCategories(propertyComparator, true);
	}

	/**
	 * Retrieves the category in a reflexive hierarchy whose property with a
	 * property code is equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return category
	 */
	public Category getReflexiveCategory(String propertyCode, Object property) {
		Category category = getCategory(propertyCode, property);
		if (category == null) {
			for (Category currentCategory : this) {
				category = currentCategory.getCategories()
						.getReflexiveCategory(propertyCode, property);
				if (category != null) {
					break;
				}
			}
		}
		return category;
	}

	/**
	 * Creates a category.
	 * 
	 * @param name
	 *            name
	 * @return category
	 */
	public Category createCategory(String name) {
		Category category = new Category(getModel());
		category.setName(name);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

	/**
	 * Creates a category.
	 * 
	 * @param name
	 *            name param description description
	 * @param approved
	 *            approved
	 * @return category
	 */
	public Category createCategory(String name, String description,
			boolean approved) {
		Category category = new Category(getModel());
		category.setName(name);
		category.setDescription(description);
		category.setApproved(approved);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

	/**
	 * Creates a category.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param name
	 *            name param description description
	 * @param approved
	 *            approved
	 * @return category
	 */
	public Category createCategory(Category categoryParent, String name,
			String description, boolean approved) {
		Category category = new Category(getModel());
		category.setCategory(categoryParent);
		category.setName(name);
		category.setDescription(description);
		category.setApproved(approved);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

}