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
package education.library;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;

import education.library.category.Categories;
import education.library.category.Category;

/**
 * Library specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Library extends GenLibrary {

	private static final long serialVersionUID = 1233251517084L;

	private static Log log = LogFactory.getLog(Library.class);

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Library(IDomain domain) {
		super(domain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Categories getReflexiveCategories(Categories categories) {
		Categories reflexiveCategories = categories;
		for (Category category : categories) {
			Categories subcategories = category.getCategories();
			if (subcategories.size() > 0) {
				Categories reflexiveSubcategories = getReflexiveCategories(subcategories);
				reflexiveCategories = (Categories) reflexiveCategories
						.union(reflexiveSubcategories);
			}
		}
		return reflexiveCategories;
	}

	public Categories getCategories() {
		return getReflexiveCategories(super.getCategories());
	}

}
