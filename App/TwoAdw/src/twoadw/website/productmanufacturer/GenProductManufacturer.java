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

	import twoadw.website.manufacturer.Manufacturer;
	import twoadw.website.manufacturer.Manufacturers;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * ProductManufacturer generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductManufacturer extends Entity<ProductManufacturer> {

	private static final long serialVersionUID = 1234729930222L;

	private static Log log = LogFactory.getLog(GenProductManufacturer.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String partNumber;
	
        
    /* ======= reference properties ======= */
	
    	    private Long manufacturerOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Product product;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Manufacturer manufacturer;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productManufacturer within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductManufacturer(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productManufacturer within its parent(s).
	     * 
	        		* @param Product product
		    		* @param Manufacturer manufacturer
			     */
	    public GenProductManufacturer(
	    		    							Product product,
	    			    		    							Manufacturer manufacturer  
	    			    		    ) {
	    				this(manufacturer.getModel());
			// parents
							setProduct(product);
	    					setManufacturer(manufacturer);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets partNumber.
		 * 
		 * @param partNumber
		 *            partNumber
		 */
		public void setPartNumber(String partNumber) {
			this.partNumber = partNumber;
		}
		
		/**
		 * Gets partNumber.
		 * 
		 * @return partNumber
		 */
		public String getPartNumber() {
			return partNumber;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets manufacturerOid.
		 * 
		 * @param manufacturerOid
		 *            manufacturerOid
		 */
		public void setManufacturerOid(Long manufacturerOid) {
			this.manufacturerOid = manufacturerOid;
							manufacturer = null;
			}
		
		/**
		 * Gets manufacturerOid.
		 * 
		 * @return manufacturerOid
		 */
		public Long getManufacturerOid() {
			return manufacturerOid;
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
		 * Sets manufacturer.
		 * 
		 * @param manufacturer
		 *            manufacturer
		 */
    	public void setManufacturer(Manufacturer manufacturer) {
			this.manufacturer = manufacturer;
			if (manufacturer != null) {
				manufacturerOid = manufacturer.getOid().getUniqueNumber();
			} else {
				manufacturerOid = null;
			}
		}

		/**
		 * Gets manufacturer.
		 * 
		 * @return manufacturer
		 */
		public Manufacturer getManufacturer() {
			if (manufacturer == null) {
				Website website = (Website) getModel();
				Manufacturers manufacturers = website.getManufacturers();
				if (manufacturerOid != null) {
											manufacturer = manufacturers.getManufacturer(manufacturerOid);
									}
			}
			return manufacturer;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}