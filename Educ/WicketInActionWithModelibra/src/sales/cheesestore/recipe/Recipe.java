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
package sales.cheesestore.recipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import sales.cheesestore.CheeseStore;

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Recipe specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-11
 */
public class Recipe extends GenRecipe {

	private static final long serialVersionUID = 1236779109375L;

	private static Log log = LogFactory.getLog(Recipe.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs recipe within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Recipe(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}