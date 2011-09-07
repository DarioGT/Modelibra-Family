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
package modelibra.designer.metaconcept;

import java.util.Comparator;

import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaneighbor.MetaNeighbor;
import modelibra.designer.metaneighbor.MetaNeighbors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

/**
 * MetaConcept generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-28
 */
public abstract class GenMetaConcepts extends Entities<MetaConcept> {

	private static final long serialVersionUID = 1208025843877L;

	private static Log log = LogFactory.getLog(GenMetaConcepts.class);

	/* ======= internal parent neighbors ======= */

	private MetaModel metaModel;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConcepts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaConcepts(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaConcepts for the metaModel parent.
	 * 
	 * @param metaModel
	 *            metaModel
	 */
	public GenMetaConcepts(MetaModel metaModel) {
		this(metaModel.getModel());
		// parent
		setMetaModel(metaModel);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaConcept with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaConcept
	 */
	public MetaConcept getMetaConcept(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaConcept with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaConcept
	 */
	public MetaConcept getMetaConcept(Long oidUniqueNumber) {
		return getMetaConcept(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaConcept whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaConcept
	 */
	public MetaConcept getMetaConcept(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaConcepts whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaConcepts
	 */
	public MetaConcepts getMetaConcepts(String propertyCode, Object property) {
		return (MetaConcepts) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaConcepts ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaConcepts
	 */
	public MetaConcepts getMetaConcepts(String propertyCode, boolean ascending) {
		return (MetaConcepts) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaConcepts selected by a selector. Returns empty metaConcepts if
	 * there are no metaConcepts that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaConcepts
	 */
	public MetaConcepts getMetaConcepts(ISelector selector) {
		return (MetaConcepts) selectBySelector(selector);
	}

	/**
	 * Gets metaConcepts ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaConcepts
	 */
	public MetaConcepts getMetaConcepts(Comparator comparator, boolean ascending) {
		return (MetaConcepts) orderByComparator(comparator, ascending);
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
	 * Gets metaConcepts ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaConcepts
	 */
	public MetaConcepts getMetaConceptsOrderedByCode(boolean ascending) {
		return getMetaConcepts("code", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets metaConcept metaNeighbors.
	 * 
	 * @return metaConcept metaNeighbors
	 */
	public MetaNeighbors getMetaDestinationConceptMetaSourceNeighbors(
			MetaConcept metaDestinationConcept) {
		MetaNeighbors metaSourceNeighbors = new MetaNeighbors(null,
				metaDestinationConcept);
		metaSourceNeighbors.setPersistent(false);
		for (MetaConcept metaConcept : this) {
			MetaNeighbor metaSourceNeighbor = metaConcept
					.getMetaDestinationNeighbors().getMetaNeighbor(metaConcept,
							metaDestinationConcept);
			if (metaSourceNeighbor != null) {
				metaSourceNeighbors.setPropagateToSource(false);
				metaSourceNeighbors.add(metaSourceNeighbor);
				metaSourceNeighbors.setPropagateToSource(true);
			}
		}
		return metaSourceNeighbors;
	}

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaModel.
	 * 
	 * @param metaModel
	 *            metaModel
	 */
	public void setMetaModel(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	/**
	 * Gets metaModel.
	 * 
	 * @return metaModel
	 */
	public MetaModel getMetaModel() {
		return metaModel;
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
	 * Creates metaConcept.
	 * 
	 * @param metaModelParent
	 *            metaModel parent
	 * @param code
	 *            code
	 * @return metaConcept
	 */
	public MetaConcept createMetaConcept(MetaModel metaModelParent, String code) {
		MetaConcept metaConcept = new MetaConcept(getModel());
		metaConcept.setMetaModel(metaModelParent);
		metaConcept.setCode(code);
		if (!add(metaConcept)) {
			metaConcept = null;
		}
		return metaConcept;
	}

}