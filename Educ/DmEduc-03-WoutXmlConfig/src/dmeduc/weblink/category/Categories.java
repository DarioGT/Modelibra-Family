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

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Category specific entities.
 * 
 * @author unknown
 * @version 2008-12-02
 */
public class Categories extends GenCategories {

	private static final long serialVersionUID = 1228241321984L;

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

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Creates a category.
	 * 
	 * @param name
	 *            name
	 * @param description
	 *            description
	 * @return category
	 */
	public Category createCategory(String name, String description) {
		Category category = new Category(getModel());
		category.setName(name);
		category.setDescription(description);
		if (!add(category)) {
			category = null;
		}
		return category;
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

}