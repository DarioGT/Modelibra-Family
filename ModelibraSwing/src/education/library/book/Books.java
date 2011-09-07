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
 * Book specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Books extends GenBooks {

	private static final long serialVersionUID = 1234736431787L;

	private static Log log = LogFactory.getLog(Books.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs books within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Books(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs books for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public Books(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}