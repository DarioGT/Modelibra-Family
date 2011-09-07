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
package twoadw.website.specificationcategory;

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

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * SpecificationCategory generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenSpecificationCategories extends Entities<SpecificationCategory> {
	
	private static final long serialVersionUID = 1236796091292L;

	private static Log log = LogFactory.getLog(GenSpecificationCategories.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Product product;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs specificationCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSpecificationCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs specificationCategories for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenSpecificationCategories(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the specificationCategory with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return specificationCategory
	 */
public SpecificationCategory getSpecificationCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the specificationCategory with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return specificationCategory
	 */
	public SpecificationCategory getSpecificationCategory(Long oidUniqueNumber) {
		return getSpecificationCategory(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first specificationCategory whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return specificationCategory
	 */
	public SpecificationCategory getSpecificationCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects specificationCategories whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return specificationCategories
	 */
	public SpecificationCategories getSpecificationCategories(String propertyCode, Object property) {
		return (SpecificationCategories) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets specificationCategories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered specificationCategories
	 */
	public SpecificationCategories getSpecificationCategories(String propertyCode, boolean ascending) {
		return (SpecificationCategories) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets specificationCategories selected by a selector. Returns empty specificationCategories if there are no
	 * specificationCategories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected specificationCategories
	 */
	public SpecificationCategories getSpecificationCategories(ISelector selector) {
		return (SpecificationCategories) selectBySelector(selector);
	}
	
	/**
	 * Gets specificationCategories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered specificationCategories
	 */
	public SpecificationCategories getSpecificationCategories(Comparator comparator, boolean ascending) {
		return (SpecificationCategories) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name specificationCategory.
		 * 
		 * @param name 
		 *            name
		 * @return name specificationCategory
		 */
		public SpecificationCategory getNameSpecificationCategory(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<SpecificationCategory> list = getSpecificationCategories(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets specificationCategories ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered specificationCategories
		 */
		public SpecificationCategories getSpecificationCategoriesOrderedByName(boolean ascending) {			
			return getSpecificationCategories("name", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets product.
		 * 
		 * @param product
		 *            product
		 */
public void setProduct(Product product) {
			this.product = product;
		}

		/**
		 * Gets product.
		 * 
		 * @return product
		 */
		public Product getProduct() {
			return product;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates specificationCategory.
	 *
			 * @param productParent product parent
		 * @param name name 
		 * @return specificationCategory
	 */
	public SpecificationCategory createSpecificationCategory(
																	Product productParent,
																	String name 
				) {
		SpecificationCategory specificationCategory = new SpecificationCategory(getModel());
					specificationCategory.setProduct(productParent);
						specificationCategory.setName(name);
				if (!add(specificationCategory)) {
			specificationCategory = null;
		}
		return specificationCategory;
	}

}