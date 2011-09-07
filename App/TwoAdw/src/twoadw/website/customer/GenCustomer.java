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
package twoadw.website.customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

	import twoadw.website.address.Addresss;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Customer generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCustomer extends Entity<Customer> {

	private static final long serialVersionUID = 1234213586153L;

	private static Log log = LogFactory.getLog(GenCustomer.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String codeCustomer;
	
    	    private String password;
	
    	    private String lastName;
	
    	    private String firstName;
	
    	    private String email;
	
    	    private Boolean receiveEmail;
	
    	    private Date startDate;
	
    	    private String securityRole;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
    	    private Addresss addresss;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs customer within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCustomer(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setAddresss(new Addresss((Customer) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets codeCustomer.
		 * 
		 * @param codeCustomer
		 *            codeCustomer
		 */
		public void setCodeCustomer(String codeCustomer) {
			this.codeCustomer = codeCustomer;
		}
		
		/**
		 * Gets codeCustomer.
		 * 
		 * @return codeCustomer
		 */
		public String getCodeCustomer() {
			return codeCustomer;
		}  
		
				    		/**
		 * Sets password.
		 * 
		 * @param password
		 *            password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
		/**
		 * Gets password.
		 * 
		 * @return password
		 */
		public String getPassword() {
			return password;
		}  
		
				    		/**
		 * Sets lastName.
		 * 
		 * @param lastName
		 *            lastName
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		/**
		 * Gets lastName.
		 * 
		 * @return lastName
		 */
		public String getLastName() {
			return lastName;
		}  
		
				    		/**
		 * Sets firstName.
		 * 
		 * @param firstName
		 *            firstName
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		/**
		 * Gets firstName.
		 * 
		 * @return firstName
		 */
		public String getFirstName() {
			return firstName;
		}  
		
				    		/**
		 * Sets email.
		 * 
		 * @param email
		 *            email
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		
		/**
		 * Gets email.
		 * 
		 * @return email
		 */
		public String getEmail() {
			return email;
		}  
		
				    		/**
		 * Sets receiveEmail.
		 * 
		 * @param receiveEmail
		 *            receiveEmail
		 */
		public void setReceiveEmail(Boolean receiveEmail) {
			this.receiveEmail = receiveEmail;
		}
		
		/**
		 * Gets receiveEmail.
		 * 
		 * @return receiveEmail
		 */
		public Boolean getReceiveEmail() {
			return receiveEmail;
		}  
		
							/**
		     * Sets receiveEmail.
		     * 
		     * @param receiveEmail
		     *            receiveEmail
		     */
			public void setReceiveEmail(boolean receiveEmail) {
				setReceiveEmail(new Boolean(receiveEmail));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isReceiveEmail() {
				return getReceiveEmail().booleanValue();
			}
			
		    		/**
		 * Sets startDate.
		 * 
		 * @param startDate
		 *            startDate
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		
		/**
		 * Gets startDate.
		 * 
		 * @return startDate
		 */
		public Date getStartDate() {
			return startDate;
		}  
		
				    		/**
		 * Sets securityRole.
		 * 
		 * @param securityRole
		 *            securityRole
		 */
		public void setSecurityRole(String securityRole) {
			this.securityRole = securityRole;
		}
		
		/**
		 * Gets securityRole.
		 * 
		 * @return securityRole
		 */
		public String getSecurityRole() {
			return securityRole;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets addresss.
		 * 
		 * @param addresss
		 *            addresss
		 */
    	public void setAddresss(Addresss addresss) {
			this.addresss = addresss;
			if (addresss != null) {
				addresss.setCustomer((Customer) this);
			}
		}

		/**
		 * Gets addresss.
		 * 
		 * @return addresss
		 */
		public Addresss getAddresss() {
			return addresss;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}