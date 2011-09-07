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
package twoadw.website.product;

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
    
	import twoadw.website.productmanufacturer.ProductManufacturer;	
	import twoadw.website.productmanufacturer.ProductManufacturers;	
			import twoadw.website.manufacturer.Manufacturer;	
		import twoadw.website.productrebate.ProductRebate;	
	import twoadw.website.productrebate.ProductRebates;	
			import twoadw.website.rebate.Rebate;	
	
/**
 * Product generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProducts extends Entities<Product> {
	
	private static final long serialVersionUID = 1234213171466L;

	private static Log log = LogFactory.getLog(GenProducts.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs products within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProducts(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the product with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return product
	 */
public Product getProduct(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the product with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return product
	 */
	public Product getProduct(Long oidUniqueNumber) {
		return getProduct(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first product whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return product
	 */
	public Product getProduct(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects products whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return products
	 */
	public Products getProducts(String propertyCode, Object property) {
		return (Products) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets products ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered products
	 */
	public Products getProducts(String propertyCode, boolean ascending) {
		return (Products) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets products selected by a selector. Returns empty products if there are no
	 * products that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected products
	 */
	public Products getProducts(ISelector selector) {
		return (Products) selectBySelector(selector);
	}
	
	/**
	 * Gets products ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered products
	 */
	public Products getProducts(Comparator comparator, boolean ascending) {
		return (Products) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets name products.
		 * 
		 * @param name 
		 *            name
		 * @return name products
		 */
		public Products getNameProducts(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
			propertySelector.defineEqual(name);
			return getProducts(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets productNumber product.
		 * 
		 * @param productNumber 
		 *            productNumber
		 * @return productNumber product
		 */
		public Product getProductNumberProduct(String productNumber) {
			PropertySelector propertySelector = new PropertySelector("productNumber");
						propertySelector.defineEqual(productNumber);
			List<Product> list = getProducts(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets products ordered by productNumber.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered products
		 */
		public Products getProductsOrderedByProductNumber(boolean ascending) {			
			return getProducts("productNumber", ascending);
		}
	
	/**
		 * Gets products ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered products
		 */
		public Products getProductsOrderedByName(boolean ascending) {			
			return getProducts("name", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
						/**
			 * Gets manufacturer productManufacturers.
			 * 
			 * @return manufacturer productManufacturers
			 */
			public ProductManufacturers getManufacturerProductManufacturers(Manufacturer manufacturer) {
				ProductManufacturers productManufacturers = new ProductManufacturers(manufacturer);
				productManufacturers.setPersistent(false);
				for (Product product : this) {
					ProductManufacturer productManufacturer = product.getProductManufacturers()
						.getProductManufacturer(product, manufacturer);
					if (productManufacturer != null) {
						productManufacturers.add(productManufacturer);
					}
				}
				return productManufacturers;
			}
				
							/**
			 * Gets rebate productRebates.
			 * 
			 * @return rebate productRebates
			 */
			public ProductRebates getRebateProductRebates(Rebate rebate) {
				ProductRebates productRebates = new ProductRebates(rebate);
				productRebates.setPersistent(false);
				for (Product product : this) {
					ProductRebate productRebate = product.getProductRebates()
						.getProductRebate(product, rebate);
					if (productRebate != null) {
						productRebates.add(productRebate);
					}
				}
				return productRebates;
			}
				
		
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
	 * Creates product.
	 *
		 * @param productNumber productNumber 
	 * @param name name 
		 * @return product
	 */
	public Product createProduct(
											String productNumber,
											String name 
				) {
		Product product = new Product(getModel());
						product.setProductNumber(productNumber);
				product.setName(name);
				if (!add(product)) {
			product = null;
		}
		return product;
	}

}