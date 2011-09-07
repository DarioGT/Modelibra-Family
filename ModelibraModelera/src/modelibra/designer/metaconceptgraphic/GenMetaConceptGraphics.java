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
package modelibra.designer.metaconceptgraphic;

import java.util.Comparator;

import modelibra.designer.metaconcept.MetaConcept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * MetaConceptGraphic generated entities. This class should not be changed
 * manually. Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaConceptGraphics extends
		Entities<MetaConceptGraphic> {

	private static final long serialVersionUID = 1208025959032L;

	private static Log log = LogFactory.getLog(GenMetaConceptGraphics.class);

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaConcept;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConceptGraphics within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaConceptGraphics(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaConceptGraphics for the metaConcept parent.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public GenMetaConceptGraphics(MetaConcept metaConcept) {
		this(metaConcept.getModel());
		// parent
		setMetaConcept(metaConcept);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaConceptGraphic with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaConceptGraphic
	 */
	public MetaConceptGraphic getMetaConceptGraphic(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaConceptGraphic with a given oid unique number. Null if
	 * not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaConceptGraphic
	 */
	public MetaConceptGraphic getMetaConceptGraphic(Long oidUniqueNumber) {
		return getMetaConceptGraphic(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaConceptGraphic whose property with a property
	 * code is equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaConceptGraphic
	 */
	public MetaConceptGraphic getMetaConceptGraphic(String propertyCode,
			Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaConceptGraphics whose property with a property code is equal
	 * to a property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaConceptGraphics
	 */
	public MetaConceptGraphics getMetaConceptGraphics(String propertyCode,
			Object property) {
		return (MetaConceptGraphics) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaConceptGraphics ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaConceptGraphics
	 */
	public MetaConceptGraphics getMetaConceptGraphics(String propertyCode,
			boolean ascending) {
		return (MetaConceptGraphics) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaConceptGraphics selected by a selector. Returns empty
	 * metaConceptGraphics if there are no metaConceptGraphics that satisfy the
	 * selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaConceptGraphics
	 */
	public MetaConceptGraphics getMetaConceptGraphics(ISelector selector) {
		return (MetaConceptGraphics) selectBySelector(selector);
	}

	/**
	 * Gets metaConceptGraphics ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaConceptGraphics
	 */
	public MetaConceptGraphics getMetaConceptGraphics(Comparator comparator,
			boolean ascending) {
		return (MetaConceptGraphics) orderByComparator(comparator, ascending);
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

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaConcept.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public void setMetaConcept(MetaConcept metaConcept) {
		this.metaConcept = metaConcept;
	}

	/**
	 * Gets metaConcept.
	 * 
	 * @return metaConcept
	 */
	public MetaConcept getMetaConcept() {
		return metaConcept;
	}

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
	 * Creates metaConceptGraphic.
	 * 
	 * @param metaConceptParent
	 *            metaConcept parent
	 * @return metaConceptGraphic
	 */
	public MetaConceptGraphic createMetaConceptGraphic(
			MetaConcept metaConceptParent) {
		MetaConceptGraphic metaConceptGraphic = new MetaConceptGraphic(
				getModel());
		metaConceptGraphic.setMetaConcept(metaConceptParent);
		if (!add(metaConceptGraphic)) {
			metaConceptGraphic = null;
		}
		return metaConceptGraphic;
	}

}