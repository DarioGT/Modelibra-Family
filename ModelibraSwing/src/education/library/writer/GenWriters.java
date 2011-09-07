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
package education.library.writer;

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
 * Writer generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenWriters extends Entities<Writer> {

	private static final long serialVersionUID = 1235856417415L;

	private static Log log = LogFactory.getLog(GenWriters.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs writers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenWriters(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the writer with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return writer
	 */
	public Writer getWriter(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the writer with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return writer
	 */
	public Writer getWriter(Long oidUniqueNumber) {
		return getWriter(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first writer whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return writer
	 */
	public Writer getWriter(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects writers whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return writers
	 */
	public Writers getWriters(String propertyCode, Object property) {
		return (Writers) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets writers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered writers
	 */
	public Writers getWriters(String propertyCode, boolean ascending) {
		return (Writers) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets writers selected by a selector. Returns empty writers if there are
	 * no writers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected writers
	 */
	public Writers getWriters(ISelector selector) {
		return (Writers) selectBySelector(selector);
	}

	/**
	 * Gets writers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered writers
	 */
	public Writers getWriters(Comparator comparator, boolean ascending) {
		return (Writers) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets lastName writer.
	 * 
	 * @param lastName
	 *            lastName
	 * @return lastName writer
	 */
	public Writer getLastNameWriter(String lastName) {
		PropertySelector propertySelector = new PropertySelector("lastName");
		propertySelector.defineEqual(lastName);
		List<Writer> list = getWriters(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/**
	 * Gets firstName writer.
	 * 
	 * @param firstName
	 *            firstName
	 * @return firstName writer
	 */
	public Writer getFirstNameWriter(String firstName) {
		PropertySelector propertySelector = new PropertySelector("firstName");
		propertySelector.defineEqual(firstName);
		List<Writer> list = getWriters(propertySelector).getList();

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
	 * Gets writers ordered by lastName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered writers
	 */
	public Writers getWritersOrderedByLastName(boolean ascending) {
		return getWriters("lastName", ascending);
	}

	/**
	 * Gets writers ordered by firstName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered writers
	 */
	public Writers getWritersOrderedByFirstName(boolean ascending) {
		return getWriters("firstName", ascending);
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
	 * Creates writer.
	 * 
	 * @param lastName
	 *            lastName
	 * @param firstName
	 *            firstName
	 * @return writer
	 */
	public Writer createWriter(String lastName, String firstName) {
		Writer writer = new Writer(getModel());
		writer.setLastName(lastName);
		writer.setFirstName(firstName);
		if (!add(writer)) {
			writer = null;
		}
		return writer;
	}

}