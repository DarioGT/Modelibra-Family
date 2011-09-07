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
package twoadw.reference.country;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.reference.Reference;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

	import twoadw.reference.state.States;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Country generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCountry extends Entity<Country> {

	private static final long serialVersionUID = 1236722681012L;

	private static Log log = LogFactory.getLog(GenCountry.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String countryCode;
	
    	    private String countryName;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
    	    private States states;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs country within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCountry(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setStates(new States((Country) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets countryCode.
		 * 
		 * @param countryCode
		 *            countryCode
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		
		/**
		 * Gets countryCode.
		 * 
		 * @return countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}  
		
				    		/**
		 * Sets countryName.
		 * 
		 * @param countryName
		 *            countryName
		 */
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		
		/**
		 * Gets countryName.
		 * 
		 * @return countryName
		 */
		public String getCountryName() {
			return countryName;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets states.
		 * 
		 * @param states
		 *            states
		 */
    	public void setStates(States states) {
			this.states = states;
			if (states != null) {
				states.setCountryName((Country) this);
			}
		}

		/**
		 * Gets states.
		 * 
		 * @return states
		 */
		public States getStates() {
			return states;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}