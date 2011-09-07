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
package education.library.book;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import education.library.category.Category;

/**
 * Book specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Book extends GenBook {

	private static final long serialVersionUID = 1234736431786L;

	private static Log log = LogFactory.getLog(Book.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs book within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Book(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs book within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public Book(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public void setName(String name) {
		super.setName(name);
		notifyObservers(getConceptConfig().getPropertyConfig("name"));
	}

	public void setWebLink(String webLink) {
		super.setWebLink(webLink);
		notifyObservers(getConceptConfig().getPropertyConfig("webLink"));
	}

	public void setDescription(String description) {
		super.setDescription(description);
		notifyObservers(getConceptConfig().getPropertyConfig("description"));
	}

	public void setCategoryOid(Long categoryOid) {
		super.setCategoryOid(categoryOid);
		notifyObservers(getConceptConfig().getPropertyConfig("categoryOid"));
	}

}