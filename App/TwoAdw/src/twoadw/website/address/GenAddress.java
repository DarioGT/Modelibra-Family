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
package twoadw.website.address;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.customer.Customer;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */

	import twoadw.website.invoice.Invoices;	

/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Address generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenAddress extends Entity<Address> {

	private static final long serialVersionUID = 1234726511675L;

	private static Log log = LogFactory.getLog(GenAddress.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String addressName;
	
    	    private String street;
	
    	    private String city;
	
    	    private String zipCode;
	
    	    private String state;
	
    	    private String country;
	
    	    private String telephone;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Customer customer;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
    	    private Invoices invoices;
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs address within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAddress(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs address within its parent(s).
	     * 
	        		* @param Customer customer
			     */
	    public GenAddress(
	    		    							Customer customer  
	    			    		    ) {
	    				this(customer.getModel());
			// parents
							setCustomer(customer);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets addressName.
		 * 
		 * @param addressName
		 *            addressName
		 */
		public void setAddressName(String addressName) {
			this.addressName = addressName;
		}
		
		/**
		 * Gets addressName.
		 * 
		 * @return addressName
		 */
		public String getAddressName() {
			return addressName;
		}  
		
				    		/**
		 * Sets street.
		 * 
		 * @param street
		 *            street
		 */
		public void setStreet(String street) {
			this.street = street;
		}
		
		/**
		 * Gets street.
		 * 
		 * @return street
		 */
		public String getStreet() {
			return street;
		}  
		
				    		/**
		 * Sets city.
		 * 
		 * @param city
		 *            city
		 */
		public void setCity(String city) {
			this.city = city;
		}
		
		/**
		 * Gets city.
		 * 
		 * @return city
		 */
		public String getCity() {
			return city;
		}  
		
				    		/**
		 * Sets zipCode.
		 * 
		 * @param zipCode
		 *            zipCode
		 */
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		
		/**
		 * Gets zipCode.
		 * 
		 * @return zipCode
		 */
		public String getZipCode() {
			return zipCode;
		}  
		
				    		/**
		 * Sets state.
		 * 
		 * @param state
		 *            state
		 */
		public void setState(String state) {
			this.state = state;
		}
		
		/**
		 * Gets state.
		 * 
		 * @return state
		 */
		public String getState() {
			return state;
		}  
		
				    		/**
		 * Sets country.
		 * 
		 * @param country
		 *            country
		 */
		public void setCountry(String country) {
			this.country = country;
		}
		
		/**
		 * Gets country.
		 * 
		 * @return country
		 */
		public String getCountry() {
			return country;
		}  
		
				    		/**
		 * Sets telephone.
		 * 
		 * @param telephone
		 *            telephone
		 */
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		
		/**
		 * Gets telephone.
		 * 
		 * @return telephone
		 */
		public String getTelephone() {
			return telephone;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
        	/**
		 * Sets customer.
		 * 
		 * @param customer
		 *            customer
		 */
    	public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		/**
		 * Gets customer.
		 * 
		 * @return customer
		 */
		public Customer getCustomer() {
			return customer;
		}
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
        	/**
		 * Sets invoices.
		 * 
		 * @param invoices
		 *            invoices
		 */
    	public void setInvoices(Invoices invoices) {
			this.invoices = invoices;
			if (invoices != null) {
				invoices.setAddress((Address) this);
			}
		}

		/**
		 * Gets invoices.
		 * 
		 * @return invoices
		 */
		public Invoices getInvoices() {
			if (invoices == null) {
				Website website = (Website) getModel();
				Invoices invoices = website.getInvoices();
				setInvoices (invoices.getAddressInvoices((Address) this));
			}
			return invoices;
		}
		
		
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}