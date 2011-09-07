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
package modelibra.designer.metadomain;

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

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * MetaDomain generated entities. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaDomains extends Entities<MetaDomain> {

	private static final long serialVersionUID = 1208025829106L;

	private static Log log = LogFactory.getLog(GenMetaDomains.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaDomains within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaDomains(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaDomain with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaDomain
	 */
	public MetaDomain getMetaDomain(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaDomain with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaDomain
	 */
	public MetaDomain getMetaDomain(Long oidUniqueNumber) {
		return getMetaDomain(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaDomain whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaDomain
	 */
	public MetaDomain getMetaDomain(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaDomains whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaDomains
	 */
	public MetaDomains getMetaDomains(String propertyCode, Object property) {
		return (MetaDomains) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaDomains ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaDomains
	 */
	public MetaDomains getMetaDomains(String propertyCode, boolean ascending) {
		return (MetaDomains) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaDomains selected by a selector. Returns empty metaDomains if
	 * there are no metaDomains that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaDomains
	 */
	public MetaDomains getMetaDomains(ISelector selector) {
		return (MetaDomains) selectBySelector(selector);
	}

	/**
	 * Gets metaDomains ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaDomains
	 */
	public MetaDomains getMetaDomains(Comparator comparator, boolean ascending) {
		return (MetaDomains) orderByComparator(comparator, ascending);
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
	 * Gets metaDomains ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaDomains
	 */
	public MetaDomains getMetaDomainsOrderedByCode(boolean ascending) {
		return getMetaDomains("code", ascending);
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
	 * Creates metaDomain.
	 * 
	 * @param code
	 *            code
	 * @return metaDomain
	 */
	public MetaDomain createMetaDomain(String code) {
		MetaDomain metaDomain = new MetaDomain(getModel());
		metaDomain.setCode(code);
		if (!add(metaDomain)) {
			metaDomain = null;
		}
		return metaDomain;
	}

}