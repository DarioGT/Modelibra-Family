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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Recipe generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-11
 */
public abstract class GenRecipe extends Entity<Recipe> {

	private static final long serialVersionUID = 1236779109373L;

	private static Log log = LogFactory.getLog(GenRecipe.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String serves;

	private String ingredients;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs recipe within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenRecipe(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets serves.
	 * 
	 * @param serves
	 *            serves
	 */
	public void setServes(String serves) {
		this.serves = serves;
	}

	/**
	 * Gets serves.
	 * 
	 * @return serves
	 */
	public String getServes() {
		return serves;
	}

	/**
	 * Sets ingredients.
	 * 
	 * @param ingredients
	 *            ingredients
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Gets ingredients.
	 * 
	 * @return ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}