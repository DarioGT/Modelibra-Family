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
package twoadw.reference.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.reference.Reference;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.reference.country.Country;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * State generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenState extends Entity<State> {

	private static final long serialVersionUID = 1236723018872L;

	private static Log log = LogFactory.getLog(GenState.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String stateCode;
	
    	    private String stateName;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Country countryName;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs state within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenState(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs state within its parent(s).
	     * 
	        		* @param Country countryName
			     */
	    public GenState(
	    		    							Country countryName  
	    			    		    ) {
	    				this(countryName.getModel());
			// parents
							setCountryName(countryName);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets stateCode.
		 * 
		 * @param stateCode
		 *            stateCode
		 */
		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}
		
		/**
		 * Gets stateCode.
		 * 
		 * @return stateCode
		 */
		public String getStateCode() {
			return stateCode;
		}  
		
				    		/**
		 * Sets stateName.
		 * 
		 * @param stateName
		 *            stateName
		 */
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		
		/**
		 * Gets stateName.
		 * 
		 * @return stateName
		 */
		public String getStateName() {
			return stateName;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
        	/**
		 * Sets countryName.
		 * 
		 * @param countryName
		 *            countryName
		 */
    	public void setCountryName(Country countryName) {
			this.countryName = countryName;
		}

		/**
		 * Gets countryName.
		 * 
		 * @return countryName
		 */
		public Country getCountryName() {
			return countryName;
		}
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}