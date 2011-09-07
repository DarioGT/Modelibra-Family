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
package travel.reference.securityrole;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

/* ======= import essential property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * SecurityRole generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public abstract class GenSecurityRoles extends Entities<SecurityRole> {

	private static final long serialVersionUID = 1176319603401L;

	private static Log log = LogFactory.getLog(GenSecurityRoles.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs securityRoles within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSecurityRoles(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the securityRole with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return securityRole
	 */
	public SecurityRole getSecurityRole(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the securityRole with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return securityRole
	 */
	public SecurityRole getSecurityRole(Long oidUniqueNumber) {
		return getSecurityRole(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first securityRole whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return securityRole
	 */
	public SecurityRole getSecurityRole(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects securityRoles whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return securityRoles
	 */
	public SecurityRoles getSecurityRoles(String propertyCode, Object property) {
		return (SecurityRoles) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets securityRoles ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered securityRoles
	 */
	public SecurityRoles getSecurityRoles(String propertyCode, boolean ascending) {
		return (SecurityRoles) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets securityRoles selected by a selector. Returns empty securityRoles if
	 * there are no securityRoles that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected securityRoles
	 */
	public SecurityRoles getSecurityRoles(ISelector selector) {
		return (SecurityRoles) selectBySelector(selector);
	}

	/**
	 * Gets securityRoles ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered securityRoles
	 */
	public SecurityRoles getSecurityRoles(Comparator comparator,
			boolean ascending) {
		return (SecurityRoles) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets securityRoles ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered securityRoles
	 */
	public SecurityRoles getSecurityRolesOrderedByCode(boolean ascending) {
		return getSecurityRoles("code", ascending);
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
	 * Creates securityRole.
	 * 
	 * @param code
	 *            code
	 * @return securityRole
	 */
	public SecurityRole createSecurityRole(String code) {
		SecurityRole securityRole = new SecurityRole(getModel());
		securityRole.setCode(code);
		if (!add(securityRole)) {
			securityRole = null;
		}
		return securityRole;
	}

}