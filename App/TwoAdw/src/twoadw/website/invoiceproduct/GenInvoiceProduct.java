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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;
	import twoadw.website.product.Products;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * InvoiceProduct generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenInvoiceProduct extends Entity<InvoiceProduct> {

	private static final long serialVersionUID = 1234213515630L;

	private static Log log = LogFactory.getLog(GenInvoiceProduct.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String quantity;
	
    	    private Double price;
	
        
    /* ======= reference properties ======= */
	
    	    private Long productOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Invoice invoice;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Product product;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceProduct within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoiceProduct(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoiceProduct within its parent(s).
	     * 
	        		* @param Invoice invoice
		    		* @param Product product
			     */
	    public GenInvoiceProduct(
	    		    							Invoice invoice,
	    			    		    							Product product  
	    			    		    ) {
	    				this(product.getModel());
			// parents
							setInvoice(invoice);
	    					setProduct(product);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets quantity.
		 * 
		 * @param quantity
		 *            quantity
		 */
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		
		/**
		 * Gets quantity.
		 * 
		 * @return quantity
		 */
		public String getQuantity() {
			return quantity;
		}  
		
				    		/**
		 * Sets price.
		 * 
		 * @param price
		 *            price
		 */
		public void setPrice(Double price) {
			this.price = price;
		}
		
		/**
		 * Gets price.
		 * 
		 * @return price
		 */
		public Double getPrice() {
			return price;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets productOid.
		 * 
		 * @param productOid
		 *            productOid
		 */
		public void setProductOid(Long productOid) {
			this.productOid = productOid;
							product = null;
			}
		
		/**
		 * Gets productOid.
		 * 
		 * @return productOid
		 */
		public Long getProductOid() {
			return productOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
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
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets product.
		 * 
		 * @param product
		 *            product
		 */
    	public void setProduct(Product product) {
			this.product = product;
			if (product != null) {
				productOid = product.getOid().getUniqueNumber();
			} else {
				productOid = null;
			}
		}

		/**
		 * Gets product.
		 * 
		 * @return product
		 */
		public Product getProduct() {
			if (product == null) {
				Website website = (Website) getModel();
				Products products = website.getProducts();
				if (productOid != null) {
											product = products.getProduct(productOid);
									}
			}
			return product;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}