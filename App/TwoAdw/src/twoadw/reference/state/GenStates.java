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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.reference.country.Country;	

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * State generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenStates extends Entities<State> {
	
	private static final long serialVersionUID = 1236723018873L;

	private static Log log = LogFactory.getLog(GenStates.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Country countryName;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs states within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenStates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs states for the countryName parent.
		 * 
		 * @param countryName
		 *            countryName
		 */
		public GenStates(Country countryName) {
			this(countryName.getModel());
			// parent
			setCountryName(countryName);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the state with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return state
	 */
public State getState(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the state with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return state
	 */
	public State getState(Long oidUniqueNumber) {
		return getState(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first state whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return state
	 */
	public State getState(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects states whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return states
	 */
	public States getStates(String propertyCode, Object property) {
		return (States) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets states ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered states
	 */
	public States getStates(String propertyCode, boolean ascending) {
		return (States) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets states selected by a selector. Returns empty states if there are no
	 * states that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected states
	 */
	public States getStates(ISelector selector) {
		return (States) selectBySelector(selector);
	}
	
	/**
	 * Gets states ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered states
	 */
	public States getStates(Comparator comparator, boolean ascending) {
		return (States) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets stateName states.
		 * 
		 * @param stateName 
		 *            stateName
		 * @return stateName states
		 */
		public States getStateNameStates(String stateName) {
			PropertySelector propertySelector = new PropertySelector("stateName");
			propertySelector.defineEqual(stateName);
			return getStates(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets stateCode state.
		 * 
		 * @param stateCode 
		 *            stateCode
		 * @return stateCode state
		 */
		public State getStateCodeState(String stateCode) {
			PropertySelector propertySelector = new PropertySelector("stateCode");
						propertySelector.defineEqual(stateCode);
			List<State> list = getStates(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets states ordered by stateCode.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered states
		 */
		public States getStatesOrderedByStateCode(boolean ascending) {			
			return getStates("stateCode", ascending);
		}
	
	/**
		 * Gets states ordered by stateName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered states
		 */
		public States getStatesOrderedByStateName(boolean ascending) {			
			return getStates("stateName", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
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
		
		
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates state.
	 *
			 * @param countryNameParent countryName parent
		 * @param stateCode stateCode 
	 * @param stateName stateName 
		 * @return state
	 */
	public State createState(
																	Country countryNameParent,
																	String stateCode,
											String stateName 
				) {
		State state = new State(getModel());
					state.setCountryName(countryNameParent);
						state.setStateCode(stateCode);
				state.setStateName(stateName);
				if (!add(state)) {
			state = null;
		}
		return state;
	}

}