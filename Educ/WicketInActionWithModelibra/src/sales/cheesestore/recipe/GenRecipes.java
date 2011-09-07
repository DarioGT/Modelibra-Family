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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Recipe generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-11
 */
public abstract class GenRecipes extends Entities<Recipe> {
	
	private static final long serialVersionUID = 1236779109374L;

	private static Log log = LogFactory.getLog(GenRecipes.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs recipes within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenRecipes(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the recipe with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return recipe
	 */
public Recipe getRecipe(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the recipe with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return recipe
	 */
	public Recipe getRecipe(Long oidUniqueNumber) {
		return getRecipe(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first recipe whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return recipe
	 */
	public Recipe getRecipe(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects recipes whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return recipes
	 */
	public Recipes getRecipes(String propertyCode, Object property) {
		return (Recipes) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets recipes ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered recipes
	 */
	public Recipes getRecipes(String propertyCode, boolean ascending) {
		return (Recipes) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets recipes selected by a selector. Returns empty recipes if there are no
	 * recipes that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected recipes
	 */
	public Recipes getRecipes(ISelector selector) {
		return (Recipes) selectBySelector(selector);
	}
	
	/**
	 * Gets recipes ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered recipes
	 */
	public Recipes getRecipes(Comparator comparator, boolean ascending) {
		return (Recipes) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets serves recipes.
		 * 
		 * @param serves 
		 *            serves
		 * @return serves recipes
		 */
		public Recipes getServesRecipes(String serves) {
			PropertySelector propertySelector = new PropertySelector("serves");
			propertySelector.defineEqual(serves);
			return getRecipes(propertySelector);
		}
		
	/**
		 * Gets ingredients recipes.
		 * 
		 * @param ingredients 
		 *            ingredients
		 * @return ingredients recipes
		 */
		public Recipes getIngredientsRecipes(String ingredients) {
			PropertySelector propertySelector = new PropertySelector("ingredients");
			propertySelector.defineEqual(ingredients);
			return getRecipes(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name recipe.
		 * 
		 * @param name 
		 *            name
		 * @return name recipe
		 */
		public Recipe getNameRecipe(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<Recipe> list = getRecipes(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets recipes ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered recipes
		 */
		public Recipes getRecipesOrderedByName(boolean ascending) {			
			return getRecipes("name", ascending);
		}
	
	/**
		 * Gets recipes ordered by serves.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered recipes
		 */
		public Recipes getRecipesOrderedByServes(boolean ascending) {			
			return getRecipes("serves", ascending);
		}
	
	/**
		 * Gets recipes ordered by ingredients.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered recipes
		 */
		public Recipes getRecipesOrderedByIngredients(boolean ascending) {			
			return getRecipes("ingredients", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates recipe.
	 *
		 * @param name name 
	 * @param serves serves 
	 * @param ingredients ingredients 
		 * @return recipe
	 */
	public Recipe createRecipe(
											String name,
											String serves,
											String ingredients 
				) {
		Recipe recipe = new Recipe(getModel());
						recipe.setName(name);
				recipe.setServes(serves);
				recipe.setIngredients(ingredients);
				if (!add(recipe)) {
			recipe = null;
		}
		return recipe;
	}

}