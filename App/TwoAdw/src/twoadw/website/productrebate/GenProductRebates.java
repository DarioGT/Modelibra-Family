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
package twoadw.website.productrebate;

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

	import twoadw.website.rebate.Rebate;	
	import twoadw.website.rebate.Rebates;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * ProductRebate generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-27
 */
public abstract class GenProductRebates extends Entities<ProductRebate> {
	
	private static final long serialVersionUID = 1236704565608L;

	private static Log log = LogFactory.getLog(GenProductRebates.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Product product;	
    
/* ======= external parent neighbors ======= */
	
	private Rebate rebate;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productRebates within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductRebates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs productRebates for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenProductRebates(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
    	/**
		 * Constructs productRebates for the rebate parent.
		 * 
		 * @param rebate
		 *            rebate
		 */
		public GenProductRebates(Rebate rebate) {
			this(rebate.getModel());
			// parent
			setRebate(rebate);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the productRebate with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return productRebate
	 */
public ProductRebate getProductRebate(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the productRebate with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return productRebate
	 */
	public ProductRebate getProductRebate(Long oidUniqueNumber) {
		return getProductRebate(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first productRebate whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productRebate
	 */
	public ProductRebate getProductRebate(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects productRebates whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productRebates
	 */
	public ProductRebates getProductRebates(String propertyCode, Object property) {
		return (ProductRebates) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets productRebates ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productRebates
	 */
	public ProductRebates getProductRebates(String propertyCode, boolean ascending) {
		return (ProductRebates) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets productRebates selected by a selector. Returns empty productRebates if there are no
	 * productRebates that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected productRebates
	 */
	public ProductRebates getProductRebates(ISelector selector) {
		return (ProductRebates) selectBySelector(selector);
	}
	
	/**
	 * Gets productRebates ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productRebates
	 */
	public ProductRebates getProductRebates(Comparator comparator, boolean ascending) {
		return (ProductRebates) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets productRebate based on many-to-many parents.
		 * 
				 * @param Product product
				 * @param Rebate rebate
			 */
		public ProductRebate getProductRebate(
									Product product,
										Rebate rebate  
						) {
			for (ProductRebate productRebate : this) {
				if (
																	productRebate.getProduct() == product &&
																						productRebate.getRebate() == rebate  
													) {
					return productRebate;
				}
			}
			return null;
		}
		
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
    
/**
		 * Sets rebate.
		 * 
		 * @param rebate
		 *            rebate
		 */
public void setRebate(Rebate rebate) {
			this.rebate = rebate;
		}

		/**
		 * Gets rebate.
		 * 
		 * @return rebate
		 */
		public Rebate getRebate() {
			return rebate;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
				protected boolean postAdd(ProductRebate productRebate) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(productRebate)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											Product product = getProduct();	
						if (product == null) {
							Product productRebateProduct = productRebate.getProduct();
							if (!productRebateProduct.getProductRebates().contain(productRebate)) {
								post = productRebateProduct.getProductRebates().add(productRebate);
							}
						}						
											Rebate rebate = getRebate();	
						if (rebate == null) {
							Rebate productRebateRebate = productRebate.getRebate();
							if (!productRebateRebate.getProductRebates().contain(productRebate)) {
								post = productRebateRebate.getProductRebates().add(productRebate);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(ProductRebate productRebate) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(productRebate)) {		
									Product product = getProduct();	
					if (product == null) {
						Product productRebateProduct = productRebate.getProduct();
						if (productRebateProduct.getProductRebates().contain(productRebate)) {
							post = productRebateProduct.getProductRebates().remove(productRebate);
						}
					}					
									Rebate rebate = getRebate();	
					if (rebate == null) {
						Rebate productRebateRebate = productRebate.getRebate();
						if (productRebateRebate.getProductRebates().contain(productRebate)) {
							post = productRebateRebate.getProductRebates().remove(productRebate);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(ProductRebate beforeProductRebate, ProductRebate afterProductRebate) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeProductRebate, afterProductRebate)) {					
									Product beforeProductRebateProduct = beforeProductRebate.getProduct();
					Product afterProductRebateProduct = afterProductRebate.getProduct();
						
					if (beforeProductRebateProduct != afterProductRebateProduct) {
						post = beforeProductRebateProduct.getProductRebates().remove(beforeProductRebate);
						if (post) {
							post = afterProductRebateProduct.getProductRebates().add(afterProductRebate);
							if (!post) {
								beforeProductRebateProduct.getProductRebates().add(beforeProductRebate);
							}
						}
					}						
									Rebate beforeProductRebateRebate = beforeProductRebate.getRebate();
					Rebate afterProductRebateRebate = afterProductRebate.getRebate();
						
					if (beforeProductRebateRebate != afterProductRebateRebate) {
						post = beforeProductRebateRebate.getProductRebates().remove(beforeProductRebate);
						if (post) {
							post = afterProductRebateRebate.getProductRebates().add(afterProductRebate);
							if (!post) {
								beforeProductRebateRebate.getProductRebates().add(beforeProductRebate);
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
	 * Creates productRebate.
	 *
			 * @param productParent product parent
			 * @param rebateParent rebate parent
			 * @return productRebate
	 */
	public ProductRebate createProductRebate(
											Product productParent, 
																				Rebate rebateParent
										) {
		ProductRebate productRebate = new ProductRebate(getModel());
					productRebate.setProduct(productParent);
					productRebate.setRebate(rebateParent);
						if (!add(productRebate)) {
			productRebate = null;
		}
		return productRebate;
	}

}