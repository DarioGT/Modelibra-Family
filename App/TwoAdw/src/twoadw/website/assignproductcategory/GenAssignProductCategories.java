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
package twoadw.website.assignproductcategory;

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

import twoadw.website.productcategory.ProductCategory;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;	
	import twoadw.website.product.Products;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * AssignProductCategory generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenAssignProductCategories extends Entities<AssignProductCategory> {
	
	private static final long serialVersionUID = 1237227206431L;

	private static Log log = LogFactory.getLog(GenAssignProductCategories.class);
	
	/* ======= internal parent neighbors ======= */
	
	private ProductCategory productCategory;	
    
/* ======= external parent neighbors ======= */
	
	private Product product;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs assignProductCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAssignProductCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs assignProductCategories for the productCategory parent.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
		public GenAssignProductCategories(ProductCategory productCategory) {
			this(productCategory.getModel());
			// parent
			setProductCategory(productCategory);
		}
	
    	/**
		 * Constructs assignProductCategories for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenAssignProductCategories(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the assignProductCategory with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return assignProductCategory
	 */
public AssignProductCategory getAssignProductCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the assignProductCategory with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return assignProductCategory
	 */
	public AssignProductCategory getAssignProductCategory(Long oidUniqueNumber) {
		return getAssignProductCategory(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first assignProductCategory whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return assignProductCategory
	 */
	public AssignProductCategory getAssignProductCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects assignProductCategories whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return assignProductCategories
	 */
	public AssignProductCategories getAssignProductCategories(String propertyCode, Object property) {
		return (AssignProductCategories) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets assignProductCategories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered assignProductCategories
	 */
	public AssignProductCategories getAssignProductCategories(String propertyCode, boolean ascending) {
		return (AssignProductCategories) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets assignProductCategories selected by a selector. Returns empty assignProductCategories if there are no
	 * assignProductCategories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected assignProductCategories
	 */
	public AssignProductCategories getAssignProductCategories(ISelector selector) {
		return (AssignProductCategories) selectBySelector(selector);
	}
	
	/**
	 * Gets assignProductCategories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered assignProductCategories
	 */
	public AssignProductCategories getAssignProductCategories(Comparator comparator, boolean ascending) {
		return (AssignProductCategories) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets assignProductCategory based on many-to-many parents.
		 * 
				 * @param ProductCategory productCategory
				 * @param Product product
			 */
		public AssignProductCategory getAssignProductCategory(
									ProductCategory productCategory,
										Product product  
						) {
			for (AssignProductCategory assignProductCategory : this) {
				if (
																	assignProductCategory.getProductCategory() == productCategory &&
																						assignProductCategory.getProduct() == product  
													) {
					return assignProductCategory;
				}
			}
			return null;
		}
		
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
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
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
				protected boolean postAdd(AssignProductCategory assignProductCategory) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(assignProductCategory)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											ProductCategory productCategory = getProductCategory();	
						if (productCategory == null) {
							ProductCategory assignProductCategoryProductCategory = assignProductCategory.getProductCategory();
							if (!assignProductCategoryProductCategory.getAssignProductCategories().contain(assignProductCategory)) {
								post = assignProductCategoryProductCategory.getAssignProductCategories().add(assignProductCategory);
							}
						}						
											Product product = getProduct();	
						if (product == null) {
							Product assignProductCategoryProduct = assignProductCategory.getProduct();
							if (!assignProductCategoryProduct.getAssignProductCategories().contain(assignProductCategory)) {
								post = assignProductCategoryProduct.getAssignProductCategories().add(assignProductCategory);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(AssignProductCategory assignProductCategory) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(assignProductCategory)) {		
									ProductCategory productCategory = getProductCategory();	
					if (productCategory == null) {
						ProductCategory assignProductCategoryProductCategory = assignProductCategory.getProductCategory();
						if (assignProductCategoryProductCategory.getAssignProductCategories().contain(assignProductCategory)) {
							post = assignProductCategoryProductCategory.getAssignProductCategories().remove(assignProductCategory);
						}
					}					
									Product product = getProduct();	
					if (product == null) {
						Product assignProductCategoryProduct = assignProductCategory.getProduct();
						if (assignProductCategoryProduct.getAssignProductCategories().contain(assignProductCategory)) {
							post = assignProductCategoryProduct.getAssignProductCategories().remove(assignProductCategory);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(AssignProductCategory beforeAssignProductCategory, AssignProductCategory afterAssignProductCategory) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeAssignProductCategory, afterAssignProductCategory)) {					
									ProductCategory beforeAssignProductCategoryProductCategory = beforeAssignProductCategory.getProductCategory();
					ProductCategory afterAssignProductCategoryProductCategory = afterAssignProductCategory.getProductCategory();
						
					if (beforeAssignProductCategoryProductCategory != afterAssignProductCategoryProductCategory) {
						post = beforeAssignProductCategoryProductCategory.getAssignProductCategories().remove(beforeAssignProductCategory);
						if (post) {
							post = afterAssignProductCategoryProductCategory.getAssignProductCategories().add(afterAssignProductCategory);
							if (!post) {
								beforeAssignProductCategoryProductCategory.getAssignProductCategories().add(beforeAssignProductCategory);
							}
						}
					}						
									Product beforeAssignProductCategoryProduct = beforeAssignProductCategory.getProduct();
					Product afterAssignProductCategoryProduct = afterAssignProductCategory.getProduct();
						
					if (beforeAssignProductCategoryProduct != afterAssignProductCategoryProduct) {
						post = beforeAssignProductCategoryProduct.getAssignProductCategories().remove(beforeAssignProductCategory);
						if (post) {
							post = afterAssignProductCategoryProduct.getAssignProductCategories().add(afterAssignProductCategory);
							if (!post) {
								beforeAssignProductCategoryProduct.getAssignProductCategories().add(beforeAssignProductCategory);
							}
						}
					}						
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates assignProductCategory.
	 *
			 * @param productCategoryParent productCategory parent
			 * @param productParent product parent
			 * @return assignProductCategory
	 */
	public AssignProductCategory createAssignProductCategory(
											ProductCategory productCategoryParent, 
																				Product productParent
										) {
		AssignProductCategory assignProductCategory = new AssignProductCategory(getModel());
					assignProductCategory.setProductCategory(productCategoryParent);
					assignProductCategory.setProduct(productParent);
						if (!add(assignProductCategory)) {
			assignProductCategory = null;
		}
		return assignProductCategory;
	}

}