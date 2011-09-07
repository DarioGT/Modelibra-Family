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


/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Country generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCountries extends Entities<Country> {
	
	private static final long serialVersionUID = 1236722681013L;

	private static Log log = LogFactory.getLog(GenCountries.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs countries within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCountries(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the country with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return country
	 */
public Country getCountry(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the country with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return country
	 */
	public Country getCountry(Long oidUniqueNumber) {
		return getCountry(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first country whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return country
	 */
	public Country getCountry(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects countries whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return countries
	 */
	public Countries getCountries(String propertyCode, Object property) {
		return (Countries) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets countries ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countries
	 */
	public Countries getCountries(String propertyCode, boolean ascending) {
		return (Countries) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets countries selected by a selector. Returns empty countries if there are no
	 * countries that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected countries
	 */
	public Countries getCountries(ISelector selector) {
		return (Countries) selectBySelector(selector);
	}
	
	/**
	 * Gets countries ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countries
	 */
	public Countries getCountries(Comparator comparator, boolean ascending) {
		return (Countries) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets countryName countries.
		 * 
		 * @param countryName 
		 *            countryName
		 * @return countryName countries
		 */
		public Countries getCountryNameCountries(String countryName) {
			PropertySelector propertySelector = new PropertySelector("countryName");
			propertySelector.defineEqual(countryName);
			return getCountries(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets countryCode country.
		 * 
		 * @param countryCode 
		 *            countryCode
		 * @return countryCode country
		 */
		public Country getCountryCodeCountry(String countryCode) {
			PropertySelector propertySelector = new PropertySelector("countryCode");
						propertySelector.defineEqual(countryCode);
			List<Country> list = getCountries(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets countries ordered by countryCode.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered countries
		 */
		public Countries getCountriesOrderedByCountryCode(boolean ascending) {			
			return getCountries("countryCode", ascending);
		}
	
	/**
		 * Gets countries ordered by countryName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered countries
		 */
		public Countries getCountriesOrderedByCountryName(boolean ascending) {			
			return getCountries("countryName", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates country.
	 *
		 * @param countryCode countryCode 
	 * @param countryName countryName 
		 * @return country
	 */
	public Country createCountry(
											String countryCode,
											String countryName 
				) {
		Country country = new Country(getModel());
						country.setCountryCode(countryCode);
				country.setCountryName(countryName);
				if (!add(country)) {
			country = null;
		}
		return country;
	}

}