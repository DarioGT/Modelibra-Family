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
package twoadw.website.invoiceproduct;

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

import twoadw.website.invoice.Invoice;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;	
	import twoadw.website.product.Products;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * InvoiceProduct generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenInvoiceProducts extends Entities<InvoiceProduct> {
	
	private static final long serialVersionUID = 1234213515631L;

	private static Log log = LogFactory.getLog(GenInvoiceProducts.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Invoice invoice;	
    
/* ======= external parent neighbors ======= */
	
	private Product product;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceProducts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoiceProducts(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs invoiceProducts for the invoice parent.
		 * 
		 * @param invoice
		 *            invoice
		 */
		public GenInvoiceProducts(Invoice invoice) {
			this(invoice.getModel());
			// parent
			setInvoice(invoice);
		}
	
    	/**
		 * Constructs invoiceProducts for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenInvoiceProducts(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the invoiceProduct with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return invoiceProduct
	 */
public InvoiceProduct getInvoiceProduct(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the invoiceProduct with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return invoiceProduct
	 */
	public InvoiceProduct getInvoiceProduct(Long oidUniqueNumber) {
		return getInvoiceProduct(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first invoiceProduct whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoiceProduct
	 */
	public InvoiceProduct getInvoiceProduct(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects invoiceProducts whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoiceProducts
	 */
	public InvoiceProducts getInvoiceProducts(String propertyCode, Object property) {
		return (InvoiceProducts) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets invoiceProducts ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoiceProducts
	 */
	public InvoiceProducts getInvoiceProducts(String propertyCode, boolean ascending) {
		return (InvoiceProducts) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets invoiceProducts selected by a selector. Returns empty invoiceProducts if there are no
	 * invoiceProducts that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected invoiceProducts
	 */
	public InvoiceProducts getInvoiceProducts(ISelector selector) {
		return (InvoiceProducts) selectBySelector(selector);
	}
	
	/**
	 * Gets invoiceProducts ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoiceProducts
	 */
	public InvoiceProducts getInvoiceProducts(Comparator comparator, boolean ascending) {
		return (InvoiceProducts) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets price invoiceProducts.
		 * 
		 * @param price 
		 *            price
		 * @return price invoiceProducts
		 */
		public InvoiceProducts getPriceInvoiceProducts(Double price) {
			PropertySelector propertySelector = new PropertySelector("price");
			propertySelector.defineEqual(price);
			return getInvoiceProducts(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets invoiceProducts ordered by price.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered invoiceProducts
		 */
		public InvoiceProducts getInvoiceProductsOrderedByPrice(boolean ascending) {			
			return getInvoiceProducts("price", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets invoiceProduct based on many-to-many parents.
		 * 
				 * @param Invoice invoice
				 * @param Product product
			 */
		public InvoiceProduct getInvoiceProduct(
									Invoice invoice,
										Product product  
						) {
			for (InvoiceProduct invoiceProduct : this) {
				if (
																	invoiceProduct.getInvoice() == invoice &&
																						invoiceProduct.getProduct() == product  
													) {
					return invoiceProduct;
				}
			}
			return null;
		}
		
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets invoice.
		 * 
		 * @param invoice
		 *            invoice
		 */
public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		/**
		 * Gets invoice.
		 * 
		 * @return invoice
		 */
		public Invoice getInvoice() {
			return invoice;
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
	
				protected boolean postAdd(InvoiceProduct invoiceProduct) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(invoiceProduct)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											Invoice invoice = getInvoice();	
						if (invoice == null) {
							Invoice invoiceProductInvoice = invoiceProduct.getInvoice();
							if (!invoiceProductInvoice.getInvoiceProducts().contain(invoiceProduct)) {
								post = invoiceProductInvoice.getInvoiceProducts().add(invoiceProduct);
							}
						}						
											Product product = getProduct();	
						if (product == null) {
							Product invoiceProductProduct = invoiceProduct.getProduct();
							if (!invoiceProductProduct.getInvoiceProducts().contain(invoiceProduct)) {
								post = invoiceProductProduct.getInvoiceProducts().add(invoiceProduct);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(InvoiceProduct invoiceProduct) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(invoiceProduct)) {		
									Invoice invoice = getInvoice();	
					if (invoice == null) {
						Invoice invoiceProductInvoice = invoiceProduct.getInvoice();
						if (invoiceProductInvoice.getInvoiceProducts().contain(invoiceProduct)) {
							post = invoiceProductInvoice.getInvoiceProducts().remove(invoiceProduct);
						}
					}					
									Product product = getProduct();	
					if (product == null) {
						Product invoiceProductProduct = invoiceProduct.getProduct();
						if (invoiceProductProduct.getInvoiceProducts().contain(invoiceProduct)) {
							post = invoiceProductProduct.getInvoiceProducts().remove(invoiceProduct);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(InvoiceProduct beforeInvoiceProduct, InvoiceProduct afterInvoiceProduct) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeInvoiceProduct, afterInvoiceProduct)) {					
									Invoice beforeInvoiceProductInvoice = beforeInvoiceProduct.getInvoice();
					Invoice afterInvoiceProductInvoice = afterInvoiceProduct.getInvoice();
						
					if (beforeInvoiceProductInvoice != afterInvoiceProductInvoice) {
						post = beforeInvoiceProductInvoice.getInvoiceProducts().remove(beforeInvoiceProduct);
						if (post) {
							post = afterInvoiceProductInvoice.getInvoiceProducts().add(afterInvoiceProduct);
							if (!post) {
								beforeInvoiceProductInvoice.getInvoiceProducts().add(beforeInvoiceProduct);
							}
						}
					}						
									Product beforeInvoiceProductProduct = beforeInvoiceProduct.getProduct();
					Product afterInvoiceProductProduct = afterInvoiceProduct.getProduct();
						
					if (beforeInvoiceProductProduct != afterInvoiceProductProduct) {
						post = beforeInvoiceProductProduct.getInvoiceProducts().remove(beforeInvoiceProduct);
						if (post) {
							post = afterInvoiceProductProduct.getInvoiceProducts().add(afterInvoiceProduct);
							if (!post) {
								beforeInvoiceProductProduct.getInvoiceProducts().add(beforeInvoiceProduct);
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
	 * Creates invoiceProduct.
	 *
			 * @param invoiceParent invoice parent
			 * @param productParent product parent
		 * @param price price 
		 * @return invoiceProduct
	 */
	public InvoiceProduct createInvoiceProduct(
											Invoice invoiceParent, 
																				Product productParent,
																	Double price 
				) {
		InvoiceProduct invoiceProduct = new InvoiceProduct(getModel());
					invoiceProduct.setInvoice(invoiceParent);
					invoiceProduct.setProduct(productParent);
						invoiceProduct.setPrice(price);
				if (!add(invoiceProduct)) {
			invoiceProduct = null;
		}
		return invoiceProduct;
	}

}