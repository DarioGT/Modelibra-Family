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

/* ======= import external parent entity and entities classes ======= */

/**
 * Category specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public class Category extends GenCategory {

	private static final long serialVersionUID = 1171894920505L;

	private static Log log = LogFactory.getLog(Category.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs category within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Category(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs category within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public Category(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}