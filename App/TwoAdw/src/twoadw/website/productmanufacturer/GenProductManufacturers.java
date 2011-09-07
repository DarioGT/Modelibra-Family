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
package twoadw.website.productmanufacturer;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.product.Product;

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * ProductManufacturer generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductManufacturers extends Entities<ProductManufacturer> {
	
	private static final long serialVersionUID = 1234729930223L;

	private static Log log = LogFactory.getLog(GenProductManufacturers.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Product product;	
    
/* ======= external parent neighbors ======= */
	
	private Manufacturer manufacturer;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productManufacturers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductManufacturers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs productManufacturers for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenProductManufacturers(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
    	/**
		 * Constructs productManufacturers for the manufacturer parent.
		 * 
		 * @param manufacturer
		 *            manufacturer
		 */
		public GenProductManufacturers(Manufacturer manufacturer) {
			this(manufacturer.getModel());
			// parent
			setManufacturer(manufacturer);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the productManufacturer with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return productManufacturer
	 */
public ProductManufacturer getProductManufacturer(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the productManufacturer with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return productManufacturer
	 */
	public ProductManufacturer getProductManufacturer(Long oidUniqueNumber) {
		return getProductManufacturer(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first productManufacturer whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productManufacturer
	 */
	public ProductManufacturer getProductManufacturer(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects productManufacturers whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productManufacturers
	 */
	public ProductManufacturers getProductManufacturers(String propertyCode, Object property) {
		return (ProductManufacturers) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets productManufacturers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productManufacturers
	 */
	public ProductManufacturers getProductManufacturers(String propertyCode, boolean ascending) {
		return (ProductManufacturers) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets productManufacturers selected by a selector. Returns empty productManufacturers if there are no
	 * productManufacturers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected productManufacturers
	 */
	public ProductManufacturers getProductManufacturers(ISelector selector) {
		return (ProductManufacturers) selectBySelector(selector);
	}
	
	/**
	 * Gets productManufacturers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productManufacturers
	 */
	public ProductManufacturers getProductManufacturers(Comparator comparator, boolean ascending) {
		return (ProductManufacturers) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets productManufacturer based on many-to-many parents.
		 * 
				 * @param Product product
				 * @param Manufacturer manufacturer
			 */
		public ProductManufacturer getProductManufacturer(
									Product product,
										Manufacturer manufacturer  
						) {
			for (ProductManufacturer productManufacturer : this) {
				if (
																	productManufacturer.getProduct() == product &&
																						productManufacturer.getManufacturer() == manufacturer  
													) {
					return productManufacturer;
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
		 * Sets manufacturer.
		 * 
		 * @param manufacturer
		 *            manufacturer
		 */
public void setManufacturer(Manufacturer manufacturer) {
			this.manufacturer = manufacturer;
		}

		/**
		 * Gets manufacturer.
		 * 
		 * @return manufacturer
		 */
		public Manufacturer getManufacturer() {
			return manufacturer;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
				protected boolean postAdd(ProductManufacturer productManufacturer) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(productManufacturer)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											Product product = getProduct();	
						if (product == null) {
							Product productManufacturerProduct = productManufacturer.getProduct();
							if (!productManufacturerProduct.getProductManufacturers().contain(productManufacturer)) {
								post = productManufacturerProduct.getProductManufacturers().add(productManufacturer);
							}
						}						
											Manufacturer manufacturer = getManufacturer();	
						if (manufacturer == null) {
							Manufacturer productManufacturerManufacturer = productManufacturer.getManufacturer();
							if (!productManufacturerManufacturer.getProductManufacturers().contain(productManufacturer)) {
								post = productManufacturerManufacturer.getProductManufacturers().add(productManufacturer);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(ProductManufacturer productManufacturer) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(productManufacturer)) {		
									Product product = getProduct();	
					if (product == null) {
						Product productManufacturerProduct = productManufacturer.getProduct();
						if (productManufacturerProduct.getProductManufacturers().contain(productManufacturer)) {
							post = productManufacturerProduct.getProductManufacturers().remove(productManufacturer);
						}
					}					
									Manufacturer manufacturer = getManufacturer();	
					if (manufacturer == null) {
						Manufacturer productManufacturerManufacturer = productManufacturer.getManufacturer();
						if (productManufacturerManufacturer.getProductManufacturers().contain(productManufacturer)) {
							post = productManufacturerManufacturer.getProductManufacturers().remove(productManufacturer);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(ProductManufacturer beforeProductManufacturer, ProductManufacturer afterProductManufacturer) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeProductManufacturer, afterProductManufacturer)) {					
									Product beforeProductManufacturerProduct = beforeProductManufacturer.getProduct();
					Product afterProductManufacturerProduct = afterProductManufacturer.getProduct();
						
					if (beforeProductManufacturerProduct != afterProductManufacturerProduct) {
						post = beforeProductManufacturerProduct.getProductManufacturers().remove(beforeProductManufacturer);
						if (post) {
							post = afterProductManufacturerProduct.getProductManufacturers().add(afterProductManufacturer);
							if (!post) {
								beforeProductManufacturerProduct.getProductManufacturers().add(beforeProductManufacturer);
							}
						}
					}						
									Manufacturer beforeProductManufacturerManufacturer = beforeProductManufacturer.getManufacturer();
					Manufacturer afterProductManufacturerManufacturer = afterProductManufacturer.getManufacturer();
						
					if (beforeProductManufacturerManufacturer != afterProductManufacturerManufacturer) {
						post = beforeProductManufacturerManufacturer.getProductManufacturers().remove(beforeProductManufacturer);
						if (post) {
							post = afterProductManufacturerManufacturer.getProductManufacturers().add(afterProductManufacturer);
							if (!post) {
								beforeProductManufacturerManufacturer.getProductManufacturers().add(beforeProductManufacturer);
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
	 * Creates productManufacturer.
	 * 
	 * @param productParent
	 *            product parent
	 * @param manufacturerParent
	 *            manufacturer parent
	 * @return productManufacturer
	 */
	public ProductManufacturer createProductManufacturer(Product productParent,
			Manufacturer manufacturerParent) {
		ProductManufacturer productManufacturer = new ProductManufacturer(
				getModel());
		productManufacturer.setProduct(productParent);
		productManufacturer.setManufacturer(manufacturerParent);
		if (!add(productManufacturer)) {
			productManufacturer = null;
		}
		return productManufacturer;
	}

}