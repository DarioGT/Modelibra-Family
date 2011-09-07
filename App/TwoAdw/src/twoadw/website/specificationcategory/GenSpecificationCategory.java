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

	import twoadw.website.specification.Specifications;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * SpecificationCategory generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenSpecificationCategory extends Entity<SpecificationCategory> {

	private static final long serialVersionUID = 1236796091291L;

	private static Log log = LogFactory.getLog(GenSpecificationCategory.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String name;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Product product;	
    
	/* ======= internal child neighbors ======= */
	
    	    private Specifications specifications;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs specificationCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSpecificationCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setSpecifications(new Specifications((SpecificationCategory) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs specificationCategory within its parent(s).
	     * 
	        		* @param Product product
			     */
	    public GenSpecificationCategory(
	    		    							Product product  
	    			    		    ) {
	    				this(product.getModel());
			// parents
							setProduct(product);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets name.
		 * 
		 * @param name
		 *            name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets name.
		 * 
		 * @return name
		 */
		public String getName() {
			return name;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
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
    
        	/**
		 * Sets specifications.
		 * 
		 * @param specifications
		 *            specifications
		 */
    	public void setSpecifications(Specifications specifications) {
			this.specifications = specifications;
			if (specifications != null) {
				specifications.setSpecificationCategory((SpecificationCategory) this);
			}
		}

		/**
		 * Gets specifications.
		 * 
		 * @return specifications
		 */
		public Specifications getSpecifications() {
			return specifications;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}