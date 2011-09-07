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
package modelibra.designer.metatype;

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
 * MetaType generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaTypes extends Entities<MetaType> {

	private static final long serialVersionUID = 1211139625808L;

	private static Log log = LogFactory.getLog(GenMetaTypes.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaTypes within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaTypes(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaType with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaType
	 */
	public MetaType getMetaType(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaType with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaType
	 */
	public MetaType getMetaType(Long oidUniqueNumber) {
		return getMetaType(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaType whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaType
	 */
	public MetaType getMetaType(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaTypes whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaTypes
	 */
	public MetaTypes getMetaTypes(String propertyCode, Object property) {
		return (MetaTypes) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaTypes ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaTypes
	 */
	public MetaTypes getMetaTypes(String propertyCode, boolean ascending) {
		return (MetaTypes) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaTypes selected by a selector. Returns empty metaTypes if there
	 * are no metaTypes that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaTypes
	 */
	public MetaTypes getMetaTypes(ISelector selector) {
		return (MetaTypes) selectBySelector(selector);
	}

	/**
	 * Gets metaTypes ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaTypes
	 */
	public MetaTypes getMetaTypes(Comparator comparator, boolean ascending) {
		return (MetaTypes) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets base metaTypes.
	 * 
	 * @param base
	 *            base
	 * @return base metaTypes
	 */
	public MetaTypes getBaseMetaTypes(String base) {
		PropertySelector propertySelector = new PropertySelector("base");
		propertySelector.defineEqual(base);
		return getMetaTypes(propertySelector);
	}

	/**
	 * Gets length metaTypes.
	 * 
	 * @param length
	 *            length
	 * @return length metaTypes
	 */
	public MetaTypes getLengthMetaTypes(Integer length) {
		PropertySelector propertySelector = new PropertySelector("length");
		propertySelector.defineEqual(length);
		return getMetaTypes(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets metaTypes ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaTypes
	 */
	public MetaTypes getMetaTypesOrderedByCode(boolean ascending) {
		return getMetaTypes("code", ascending);
	}

	/**
	 * Gets metaTypes ordered by base.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaTypes
	 */
	public MetaTypes getMetaTypesOrderedByBase(boolean ascending) {
		return getMetaTypes("base", ascending);
	}

	/**
	 * Gets metaTypes ordered by length.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaTypes
	 */
	public MetaTypes getMetaTypesOrderedByLength(boolean ascending) {
		return getMetaTypes("length", ascending);
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
	 * Creates metaType.
	 * 
	 * @param code
	 *            code
	 * @param base
	 *            base
	 * @param length
	 *            length
	 * @return metaType
	 */
	public MetaType createMetaType(String code, String base, Integer length) {
		MetaType metaType = new MetaType(getModel());
		metaType.setCode(code);
		metaType.setBase(base);
		metaType.setLength(length);
		if (!add(metaType)) {
			metaType = null;
		}
		return metaType;
	}

}