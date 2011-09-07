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
package travel.reference.countryname;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * CountryName generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public abstract class GenCountryNames extends Entities<CountryName> {

	private static final long serialVersionUID = 1176319565378L;

	private static Log log = LogFactory.getLog(GenCountryNames.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs countryNames within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCountryNames(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the countryName with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return countryName
	 */
	public CountryName getCountryName(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the countryName with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return countryName
	 */
	public CountryName getCountryName(Long oidUniqueNumber) {
		return getCountryName(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first countryName whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return countryName
	 */
	public CountryName getCountryName(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects countryNames whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return countryNames
	 */
	public CountryNames getCountryNames(String propertyCode, Object property) {
		return (CountryNames) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets countryNames ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countryNames
	 */
	public CountryNames getCountryNames(String propertyCode, boolean ascending) {
		return (CountryNames) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets countryNames selected by a selector. Returns empty countryNames if
	 * there are no countryNames that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected countryNames
	 */
	public CountryNames getCountryNames(ISelector selector) {
		return (CountryNames) selectBySelector(selector);
	}

	/**
	 * Gets countryNames ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countryNames
	 */
	public CountryNames getCountryNames(Comparator comparator, boolean ascending) {
		return (CountryNames) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets country countryNames.
	 * 
	 * @param country
	 *            country
	 * @return country countryNames
	 */
	public CountryNames getCountryCountryNames(String country) {
		PropertySelector propertySelector = new PropertySelector("country");
		propertySelector.defineEqual(country);
		return getCountryNames(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets countryNames ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered countryNames
	 */
	public CountryNames getCountryNamesOrderedByCode(boolean ascending) {
		return getCountryNames("code", ascending);
	}

	/**
	 * Gets countryNames ordered by country.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered countryNames
	 */
	public CountryNames getCountryNamesOrderedByCountry(boolean ascending) {
		return getCountryNames("country", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates countryName.
	 * 
	 * @param code
	 *            code
	 * @param country
	 *            country
	 * @return countryName
	 */
	public CountryName createCountryName(String code, String country) {
		CountryName countryName = new CountryName(getModel());
		countryName.setCode(code);
		countryName.setCountry(country);
		if (!add(countryName)) {
			countryName = null;
		}
		return countryName;
	}

}