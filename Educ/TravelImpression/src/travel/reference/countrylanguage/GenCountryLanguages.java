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
package travel.reference.countrylanguage;

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
 * CountryLanguage generated entities. This class should not be changed
 * manually. Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public abstract class GenCountryLanguages extends Entities<CountryLanguage> {

	private static final long serialVersionUID = 1176319588569L;

	private static Log log = LogFactory.getLog(GenCountryLanguages.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs countryLanguages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCountryLanguages(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the countryLanguage with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return countryLanguage
	 */
	public CountryLanguage getCountryLanguage(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the countryLanguage with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return countryLanguage
	 */
	public CountryLanguage getCountryLanguage(Long oidUniqueNumber) {
		return getCountryLanguage(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first countryLanguage whose property with a property code
	 * is equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return countryLanguage
	 */
	public CountryLanguage getCountryLanguage(String propertyCode,
			Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects countryLanguages whose property with a property code is equal to
	 * a property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return countryLanguages
	 */
	public CountryLanguages getCountryLanguages(String propertyCode,
			Object property) {
		return (CountryLanguages) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets countryLanguages ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countryLanguages
	 */
	public CountryLanguages getCountryLanguages(String propertyCode,
			boolean ascending) {
		return (CountryLanguages) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets countryLanguages selected by a selector. Returns empty
	 * countryLanguages if there are no countryLanguages that satisfy the
	 * selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected countryLanguages
	 */
	public CountryLanguages getCountryLanguages(ISelector selector) {
		return (CountryLanguages) selectBySelector(selector);
	}

	/**
	 * Gets countryLanguages ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered countryLanguages
	 */
	public CountryLanguages getCountryLanguages(Comparator comparator,
			boolean ascending) {
		return (CountryLanguages) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets language countryLanguages.
	 * 
	 * @param language
	 *            language
	 * @return language countryLanguages
	 */
	public CountryLanguages getLanguageCountryLanguages(String language) {
		PropertySelector propertySelector = new PropertySelector("language");
		propertySelector.defineEqual(language);
		return getCountryLanguages(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets countryLanguages ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered countryLanguages
	 */
	public CountryLanguages getCountryLanguagesOrderedByCode(boolean ascending) {
		return getCountryLanguages("code", ascending);
	}

	/**
	 * Gets countryLanguages ordered by language.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered countryLanguages
	 */
	public CountryLanguages getCountryLanguagesOrderedByLanguage(
			boolean ascending) {
		return getCountryLanguages("language", ascending);
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
	 * Creates countryLanguage.
	 * 
	 * @param code
	 *            code
	 * @param language
	 *            language
	 * @return countryLanguage
	 */
	public CountryLanguage createCountryLanguage(String code, String language) {
		CountryLanguage countryLanguage = new CountryLanguage(getModel());
		countryLanguage.setCode(code);
		countryLanguage.setLanguage(language);
		if (!add(countryLanguage)) {
			countryLanguage = null;
		}
		return countryLanguage;
	}

}