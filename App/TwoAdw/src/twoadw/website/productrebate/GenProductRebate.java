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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.rebate.Rebate;
	import twoadw.website.rebate.Rebates;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * ProductRebate generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-27
 */
public abstract class GenProductRebate extends Entity<ProductRebate> {

	private static final long serialVersionUID = 1236704565607L;

	private static Log log = LogFactory.getLog(GenProductRebate.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
        
    /* ======= reference properties ======= */
	
    	    private Long rebateOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Product product;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Rebate rebate;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productRebate within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductRebate(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productRebate within its parent(s).
	     * 
	        		* @param Product product
		    		* @param Rebate rebate
			     */
	    public GenProductRebate(
	    		    							Product product,
	    			    		    							Rebate rebate  
	    			    		    ) {
	    				this(rebate.getModel());
			// parents
							setProduct(product);
	    					setRebate(rebate);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets rebateOid.
		 * 
		 * @param rebateOid
		 *            rebateOid
		 */
		public void setRebateOid(Long rebateOid) {
			this.rebateOid = rebateOid;
							rebate = null;
			}
		
		/**
		 * Gets rebateOid.
		 * 
		 * @return rebateOid
		 */
		public Long getRebateOid() {
			return rebateOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
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
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets rebate.
		 * 
		 * @param rebate
		 *            rebate
		 */
    	public void setRebate(Rebate rebate) {
			this.rebate = rebate;
			if (rebate != null) {
				rebateOid = rebate.getOid().getUniqueNumber();
			} else {
				rebateOid = null;
			}
		}

		/**
		 * Gets rebate.
		 * 
		 * @return rebate
		 */
		public Rebate getRebate() {
			if (rebate == null) {
				Website website = (Website) getModel();
				Rebates rebates = website.getRebates();
				if (rebateOid != null) {
											rebate = rebates.getRebate(rebateOid);
									}
			}
			return rebate;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}