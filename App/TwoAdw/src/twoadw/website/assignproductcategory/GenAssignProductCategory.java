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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.productcategory.ProductCategory;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;
	import twoadw.website.product.Products;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * AssignProductCategory generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenAssignProductCategory extends Entity<AssignProductCategory> {

	private static final long serialVersionUID = 1237227206430L;

	private static Log log = LogFactory.getLog(GenAssignProductCategory.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
        
    /* ======= reference properties ======= */
	
    	    private Long productOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private ProductCategory productCategory;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Product product;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs assignProductCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAssignProductCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs assignProductCategory within its parent(s).
	     * 
	        		* @param ProductCategory productCategory
		    		* @param Product product
			     */
	    public GenAssignProductCategory(
	    		    							ProductCategory productCategory,
	    			    		    							Product product  
	    			    		    ) {
	    				this(product.getModel());
			// parents
							setProductCategory(productCategory);
	    					setProduct(product);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
        
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