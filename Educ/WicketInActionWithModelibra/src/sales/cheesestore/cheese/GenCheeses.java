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
package sales.cheesestore.cheese;

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
 * Cheese generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public abstract class GenCheeses extends Entities<Cheese> {
	
	private static final long serialVersionUID = 1231169456923L;

	private static Log log = LogFactory.getLog(GenCheeses.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs cheeses within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCheeses(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the cheese with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return cheese
	 */
public Cheese getCheese(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the cheese with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return cheese
	 */
	public Cheese getCheese(Long oidUniqueNumber) {
		return getCheese(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first cheese whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cheese
	 */
	public Cheese getCheese(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects cheeses whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cheeses
	 */
	public Cheeses getCheeses(String propertyCode, Object property) {
		return (Cheeses) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets cheeses ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cheeses
	 */
	public Cheeses getCheeses(String propertyCode, boolean ascending) {
		return (Cheeses) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets cheeses selected by a selector. Returns empty cheeses if there are no
	 * cheeses that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected cheeses
	 */
	public Cheeses getCheeses(ISelector selector) {
		return (Cheeses) selectBySelector(selector);
	}
	
	/**
	 * Gets cheeses ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cheeses
	 */
	public Cheeses getCheeses(Comparator comparator, boolean ascending) {
		return (Cheeses) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets description cheeses.
		 * 
		 * @param description 
		 *            description
		 * @return description cheeses
		 */
		public Cheeses getDescriptionCheeses(String description) {
			PropertySelector propertySelector = new PropertySelector("description");
			propertySelector.defineEqual(description);
			return getCheeses(propertySelector);
		}
		
	/**
		 * Gets price cheeses.
		 * 
		 * @param price 
		 *            price
		 * @return price cheeses
		 */
		public Cheeses getPriceCheeses(Double price) {
			PropertySelector propertySelector = new PropertySelector("price");
			propertySelector.defineEqual(price);
			return getCheeses(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name cheese.
		 * 
		 * @param name 
		 *            name
		 * @return name cheese
		 */
		public Cheese getNameCheese(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<Cheese> list = getCheeses(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets cheeses ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered cheeses
		 */
		public Cheeses getCheesesOrderedByName(boolean ascending) {			
			return getCheeses("name", ascending);
		}
	
	/**
		 * Gets cheeses ordered by description.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered cheeses
		 */
		public Cheeses getCheesesOrderedByDescription(boolean ascending) {			
			return getCheeses("description", ascending);
		}
	
	/**
		 * Gets cheeses ordered by price.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered cheeses
		 */
		public Cheeses getCheesesOrderedByPrice(boolean ascending) {			
			return getCheeses("price", ascending);
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
	 * Creates cheese.
	 *
		 * @param name name 
	 * @param description description 
	 * @param price price 
		 * @return cheese
	 */
	public Cheese createCheese(
											String name,
											String description,
											Double price 
				) {
		Cheese cheese = new Cheese(getModel());
						cheese.setName(name);
				cheese.setDescription(description);
				cheese.setPrice(price);
				if (!add(cheese)) {
			cheese = null;
		}
		return cheese;
	}

}