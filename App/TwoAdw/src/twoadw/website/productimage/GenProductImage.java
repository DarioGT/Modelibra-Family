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
package twoadw.website.productimage;

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


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * ProductImage generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductImage extends Entity<ProductImage> {

	private static final long serialVersionUID = 1234725739868L;

	private static Log log = LogFactory.getLog(GenProductImage.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String name;
	
    	    private String imageUrl96x96;
	
    	    private String imageUrl180x130;
	
    	    private String imageUrlFullSize;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Product product;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productImage within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductImage(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productImage within its parent(s).
	     * 
	        		* @param Product product
			     */
	    public GenProductImage(
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
		
				    		/**
		 * Sets imageUrl96x96.
		 * 
		 * @param imageUrl96x96
		 *            imageUrl96x96
		 */
		public void setImageUrl96x96(String imageUrl96x96) {
			this.imageUrl96x96 = imageUrl96x96;
		}
		
		/**
		 * Gets imageUrl96x96.
		 * 
		 * @return imageUrl96x96
		 */
		public String getImageUrl96x96() {
			return imageUrl96x96;
		}  
		
				    		/**
		 * Sets imageUrl180x130.
		 * 
		 * @param imageUrl180x130
		 *            imageUrl180x130
		 */
		public void setImageUrl180x130(String imageUrl180x130) {
			this.imageUrl180x130 = imageUrl180x130;
		}
		
		/**
		 * Gets imageUrl180x130.
		 * 
		 * @return imageUrl180x130
		 */
		public String getImageUrl180x130() {
			return imageUrl180x130;
		}  
		
				    		/**
		 * Sets imageUrlFullSize.
		 * 
		 * @param imageUrlFullSize
		 *            imageUrlFullSize
		 */
		public void setImageUrlFullSize(String imageUrlFullSize) {
			this.imageUrlFullSize = imageUrlFullSize;
		}
		
		/**
		 * Gets imageUrlFullSize.
		 * 
		 * @return imageUrlFullSize
		 */
		public String getImageUrlFullSize() {
			return imageUrlFullSize;
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
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}