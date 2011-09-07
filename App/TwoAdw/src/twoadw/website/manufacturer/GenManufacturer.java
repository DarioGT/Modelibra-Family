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
package twoadw.website.manufacturer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */

	import twoadw.website.productmanufacturer.ProductManufacturers;	

/* ======= import external many-to-many internal parent entities classes ======= */

			import twoadw.website.product.Products;
	
/**
 * Manufacturer generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenManufacturer extends Entity<Manufacturer> {

	private static final long serialVersionUID = 1234727536423L;

	private static Log log = LogFactory.getLog(GenManufacturer.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String manufacturerNumber;
	
    	    private String name;
	
    	    private String websiteUrl;
	
    	    private String supportUrl;
	
    	    private String contactEmail;
	
    	    private String contactName;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
    	    private ProductManufacturers productManufacturers;
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs manufacturer within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenManufacturer(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets manufacturerNumber.
		 * 
		 * @param manufacturerNumber
		 *            manufacturerNumber
		 */
		public void setManufacturerNumber(String manufacturerNumber) {
			this.manufacturerNumber = manufacturerNumber;
		}
		
		/**
		 * Gets manufacturerNumber.
		 * 
		 * @return manufacturerNumber
		 */
		public String getManufacturerNumber() {
			return manufacturerNumber;
		}  
		
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
		 * Sets websiteUrl.
		 * 
		 * @param websiteUrl
		 *            websiteUrl
		 */
		public void setWebsiteUrl(String websiteUrl) {
			this.websiteUrl = websiteUrl;
		}
		
		/**
		 * Gets websiteUrl.
		 * 
		 * @return websiteUrl
		 */
		public String getWebsiteUrl() {
			return websiteUrl;
		}  
		
				    		/**
		 * Sets supportUrl.
		 * 
		 * @param supportUrl
		 *            supportUrl
		 */
		public void setSupportUrl(String supportUrl) {
			this.supportUrl = supportUrl;
		}
		
		/**
		 * Gets supportUrl.
		 * 
		 * @return supportUrl
		 */
		public String getSupportUrl() {
			return supportUrl;
		}  
		
				    		/**
		 * Sets contactEmail.
		 * 
		 * @param contactEmail
		 *            contactEmail
		 */
		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}
		
		/**
		 * Gets contactEmail.
		 * 
		 * @return contactEmail
		 */
		public String getContactEmail() {
			return contactEmail;
		}  
		
				    		/**
		 * Sets contactName.
		 * 
		 * @param contactName
		 *            contactName
		 */
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
		
		/**
		 * Gets contactName.
		 * 
		 * @return contactName
		 */
		public String getContactName() {
			return contactName;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
    					/**
			 * Sets productManufacturers.
			 * 
			 * @param productManufacturers
			 *            productManufacturers
			 */
	    	public void setProductManufacturers(ProductManufacturers productManufacturers) {
				this.productManufacturers = productManufacturers;
				if (productManufacturers != null) {
					productManufacturers.setManufacturer((Manufacturer) this);
				}
			}
	
			/**
			 * Gets productManufacturers.
			 * 
			 * @return productManufacturers
			 */
			public ProductManufacturers getProductManufacturers() {
			    if (productManufacturers == null) {
					Website website = (Website) getModel();
					Products products = website.getProducts();
					setProductManufacturers(products.getManufacturerProductManufacturers((Manufacturer) this));
				}
				return productManufacturers;
			}
			
			    
}