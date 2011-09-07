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
package travel.impression.traveler;

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

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Traveler generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenTravelers extends Entities<Traveler> {

	private static final long serialVersionUID = 1189698214160L;

	private static Log log = LogFactory.getLog(GenTravelers.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs travelers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenTravelers(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the traveler with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return traveler
	 */
	public Traveler getTraveler(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the traveler with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return traveler
	 */
	public Traveler getTraveler(Long oidUniqueNumber) {
		return getTraveler(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first traveler whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return traveler
	 */
	public Traveler getTraveler(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects travelers whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return travelers
	 */
	public Travelers getTravelers(String propertyCode, Object property) {
		return (Travelers) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets travelers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered travelers
	 */
	public Travelers getTravelers(String propertyCode, boolean ascending) {
		return (Travelers) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets travelers selected by a selector. Returns empty travelers if there
	 * are no travelers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected travelers
	 */
	public Travelers getTravelers(ISelector selector) {
		return (Travelers) selectBySelector(selector);
	}

	/**
	 * Gets travelers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered travelers
	 */
	public Travelers getTravelers(Comparator comparator, boolean ascending) {
		return (Travelers) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets name travelers.
	 * 
	 * @param name
	 *            name
	 * @return name travelers
	 */
	public Travelers getNameTravelers(String name) {
		PropertySelector propertySelector = new PropertySelector("name");
		propertySelector.defineEqual(name);
		return getTravelers(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets travelers ordered by name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered travelers
	 */
	public Travelers getTravelersOrderedByName(boolean ascending) {
		return getTravelers("name", ascending);
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
	 * Creates traveler.
	 * 
	 * @param code
	 *            code
	 * @param firstName
	 *            firstName
	 * @param lastName
	 *            lastName
	 * @param email
	 *            email
	 * @param password
	 *            password
	 * @return traveler
	 */
	public Traveler createTraveler(String code, String firstName,
			String lastName, String email, String password) {
		Traveler traveler = new Traveler(getModel());
		traveler.setCode(code);
		traveler.setFirstName(firstName);
		traveler.setLastName(lastName);
		traveler.setEmail(email);
		traveler.setPassword(password);
		if (!add(traveler)) {
			traveler = null;
		}
		return traveler;
	}

}