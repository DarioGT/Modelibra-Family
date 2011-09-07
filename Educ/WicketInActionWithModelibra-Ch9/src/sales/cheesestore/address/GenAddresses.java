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
package sales.cheesestore.address;

import java.util.Comparator;
import java.util.List;

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
 * Address generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenAddresses extends Entities<Address> {

	private static final long serialVersionUID = 1231169511159L;

	private static Log log = LogFactory.getLog(GenAddresses.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs addresses within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAddresses(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the address with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return address
	 */
	public Address getAddress(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the address with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return address
	 */
	public Address getAddress(Long oidUniqueNumber) {
		return getAddress(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first address whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return address
	 */
	public Address getAddress(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects addresses whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return addresses
	 */
	public Addresses getAddresses(String propertyCode, Object property) {
		return (Addresses) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets addresses ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered addresses
	 */
	public Addresses getAddresses(String propertyCode, boolean ascending) {
		return (Addresses) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets addresses selected by a selector. Returns empty addresses if there
	 * are no addresses that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected addresses
	 */
	public Addresses getAddresses(ISelector selector) {
		return (Addresses) selectBySelector(selector);
	}

	/**
	 * Gets addresses ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered addresses
	 */
	public Addresses getAddresses(Comparator comparator, boolean ascending) {
		return (Addresses) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets street addresses.
	 * 
	 * @param street
	 *            street
	 * @return street addresses
	 */
	public Addresses getStreetAddresses(String street) {
		PropertySelector propertySelector = new PropertySelector("street");
		propertySelector.defineEqual(street);
		return getAddresses(propertySelector);
	}

	/**
	 * Gets city addresses.
	 * 
	 * @param city
	 *            city
	 * @return city addresses
	 */
	public Addresses getCityAddresses(String city) {
		PropertySelector propertySelector = new PropertySelector("city");
		propertySelector.defineEqual(city);
		return getAddresses(propertySelector);
	}

	/**
	 * Gets zipcode addresses.
	 * 
	 * @param zipcode
	 *            zipcode
	 * @return zipcode addresses
	 */
	public Addresses getZipcodeAddresses(String zipcode) {
		PropertySelector propertySelector = new PropertySelector("zipcode");
		propertySelector.defineEqual(zipcode);
		return getAddresses(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets name address.
	 * 
	 * @param name
	 *            name
	 * @return name address
	 */
	public Address getNameAddress(String name) {
		PropertySelector propertySelector = new PropertySelector("name");
		propertySelector.defineEqual(name);
		List<Address> list = getAddresses(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets addresses ordered by name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered addresses
	 */
	public Addresses getAddressesOrderedByName(boolean ascending) {
		return getAddresses("name", ascending);
	}

	/**
	 * Gets addresses ordered by street.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered addresses
	 */
	public Addresses getAddressesOrderedByStreet(boolean ascending) {
		return getAddresses("street", ascending);
	}

	/**
	 * Gets addresses ordered by city.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered addresses
	 */
	public Addresses getAddressesOrderedByCity(boolean ascending) {
		return getAddresses("city", ascending);
	}

	/**
	 * Gets addresses ordered by zipcode.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered addresses
	 */
	public Addresses getAddressesOrderedByZipcode(boolean ascending) {
		return getAddresses("zipcode", ascending);
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
	 * Creates address.
	 * 
	 * @param name
	 *            name
	 * @param street
	 *            street
	 * @param city
	 *            city
	 * @param zipcode
	 *            zipcode
	 * @return address
	 */
	public Address createAddress(String name, String street, String city,
			String zipcode) {
		Address address = new Address(getModel());
		address.setName(name);
		address.setStreet(street);
		address.setCity(city);
		address.setZipcode(zipcode);
		if (!add(address)) {
			address = null;
		}
		return address;
	}

}