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
package medical.records.patient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import medical.records.Records;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Patient generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-17
 */
public abstract class GenPatient extends Entity<Patient> {

	private static final long serialVersionUID = 1253218298654L;

	private static Log log = LogFactory.getLog(GenPatient.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String firstName;
	
    	    private String lastName;
	
    	    private String email;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs patient within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPatient(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
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
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}