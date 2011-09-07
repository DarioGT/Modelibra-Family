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
package twoadw.website.productcategory;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import twoadw.website.assignproductcategory.AssignProductCategories;
import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.product.Product;
	
/**
 * ProductCategory generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductCategories extends Entities<ProductCategory> {
	
	private static final long serialVersionUID = 1234725304095L;

	private static Log log = LogFactory.getLog(GenProductCategories.class);
	
	/* ======= internal parent neighbors ======= */
	
	private ProductCategory productCategory;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs productCategories for the productCategory parent.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
		public GenProductCategories(ProductCategory productCategory) {
			this(productCategory.getModel());
			// parent
			setProductCategory(productCategory);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the productCategory with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return productCategory
	 */
public ProductCategory getProductCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the productCategory with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return productCategory
	 */
	public ProductCategory getProductCategory(Long oidUniqueNumber) {
		return getProductCategory(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first productCategory whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productCategory
	 */
	public ProductCategory getProductCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
				/**
	 	 * Retrieves the productCategory in a reflexive hierarchy using a given oid unique
	 	 * number. Null if not found.
	 	 * 
	 	 * @param oidUniqueNumber
	 	 *            oid unique number
	 	 * @return productCategory
	 	 */
		public ProductCategory getReflexiveProductCategory(Long oidUniqueNumber) {
			ProductCategory productCategory = getProductCategory(oidUniqueNumber);
			if (productCategory == null) {
				for (ProductCategory currentProductCategory : this) {
					productCategory = currentProductCategory.getProductCategories()
							.getReflexiveProductCategory(oidUniqueNumber);
					if (productCategory != null) {
						break;
					}
				}
			}
			return productCategory;
		}
		
		/**
	 * Selects productCategories whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productCategories
	 */
	public ProductCategories getProductCategories(String propertyCode, Object property) {
		return (ProductCategories) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets productCategories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productCategories
	 */
	public ProductCategories getProductCategories(String propertyCode, boolean ascending) {
		return (ProductCategories) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets productCategories selected by a selector. Returns empty productCategories if there are no
	 * productCategories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected productCategories
	 */
	public ProductCategories getProductCategories(ISelector selector) {
		return (ProductCategories) selectBySelector(selector);
	}
	
	/**
	 * Gets productCategories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productCategories
	 */
	public ProductCategories getProductCategories(Comparator comparator, boolean ascending) {
		return (ProductCategories) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets categoryName productCategories.
		 * 
		 * @param categoryName 
		 *            categoryName
		 * @return categoryName productCategories
		 */
		public ProductCategories getCategoryNameProductCategories(String categoryName) {
			PropertySelector propertySelector = new PropertySelector("categoryName");
			propertySelector.defineEqual(categoryName);
			return getProductCategories(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets categoryNumber productCategory.
		 * 
		 * @param categoryNumber 
		 *            categoryNumber
		 * @return categoryNumber productCategory
		 */
		public ProductCategory getCategoryNumberProductCategory(String categoryNumber) {
			PropertySelector propertySelector = new PropertySelector("categoryNumber");
						propertySelector.defineEqual(categoryNumber);
			List<ProductCategory> list = getProductCategories(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets productCategories ordered by categoryNumber.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered productCategories
		 */
		public ProductCategories getProductCategoriesOrderedByCategoryNumber(boolean ascending) {			
			return getProductCategories("categoryNumber", ascending);
		}
	
	/**
		 * Gets productCategories ordered by categoryName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered productCategories
		 */
		public ProductCategories getProductCategoriesOrderedByCategoryName(boolean ascending) {			
			return getProductCategories("categoryName", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
						/**
			 * Gets product assignProductCategories.
			 * 
			 * @return product assignProductCategories
			 */
			public AssignProductCategories getProductAssignProductCategories(Product product) {
				AssignProductCategories assignProductCategories = new AssignProductCategories(product);
				assignProductCategories.setPersistent(false);
				for (ProductCategory productCategory : this) {
					AssignProductCategory assignProductCategory = productCategory.getAssignProductCategories()
						.getAssignProductCategory(productCategory, product);
					if (assignProductCategory != null) {
						assignProductCategories.add(assignProductCategory);
					}
				}
				return assignProductCategories;
			}
				
		
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets productCategory.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
public void setProductCategory(ProductCategory productCategory) {
			this.productCategory = productCategory;
		}

		/**
		 * Gets productCategory.
		 * 
		 * @return productCategory
		 */
		public ProductCategory getProductCategory() {
			return productCategory;
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
	 * Creates productCategory.
	 * 
	 * @param productCategoryParent
	 *            productCategory parent
	 * @param categoryNumber
	 *            categoryNumber
	 * @param categoryName
	 *            categoryName
	 * @return productCategory
	 */
	public ProductCategory createProductCategory(
			ProductCategory productCategoryParent, String categoryNumber,
			String categoryName) {
		ProductCategory productCategory = new ProductCategory(getModel());
		productCategory.setProductCategory(productCategoryParent);
		productCategory.setCategoryNumber(categoryNumber);
		productCategory.setCategoryName(categoryName);
		if (!add(productCategory)) {
			productCategory = null;
		}
		return productCategory;
	}

}