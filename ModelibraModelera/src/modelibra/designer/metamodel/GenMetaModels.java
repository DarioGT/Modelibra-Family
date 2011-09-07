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
package modelibra.designer.metamodel;

import java.util.Comparator;

import modelibra.designer.metadomain.MetaDomain;

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
 * MetaModel generated entities. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaModels extends Entities<MetaModel> {

	private static final long serialVersionUID = 1208025838299L;

	private static Log log = LogFactory.getLog(GenMetaModels.class);

	/* ======= internal parent neighbors ======= */

	private MetaDomain metaDomain;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaModels within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaModels(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaModels for the metaDomain parent.
	 * 
	 * @param metaDomain
	 *            metaDomain
	 */
	public GenMetaModels(MetaDomain metaDomain) {
		this(metaDomain.getModel());
		// parent
		setMetaDomain(metaDomain);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaModel with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaModel
	 */
	public MetaModel getMetaModel(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaModel with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaModel
	 */
	public MetaModel getMetaModel(Long oidUniqueNumber) {
		return getMetaModel(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaModel whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaModel
	 */
	public MetaModel getMetaModel(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaModels whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaModels
	 */
	public MetaModels getMetaModels(String propertyCode, Object property) {
		return (MetaModels) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaModels ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaModels
	 */
	public MetaModels getMetaModels(String propertyCode, boolean ascending) {
		return (MetaModels) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaModels selected by a selector. Returns empty metaModels if there
	 * are no metaModels that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaModels
	 */
	public MetaModels getMetaModels(ISelector selector) {
		return (MetaModels) selectBySelector(selector);
	}

	/**
	 * Gets metaModels ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaModels
	 */
	public MetaModels getMetaModels(Comparator comparator, boolean ascending) {
		return (MetaModels) orderByComparator(comparator, ascending);
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
	 * Gets metaModels ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaModels
	 */
	public MetaModels getMetaModelsOrderedByCode(boolean ascending) {
		return getMetaModels("code", ascending);
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

	/**
	 * Sets metaDomain.
	 * 
	 * @param metaDomain
	 *            metaDomain
	 */
	public void setMetaDomain(MetaDomain metaDomain) {
		this.metaDomain = metaDomain;
	}

	/**
	 * Gets metaDomain.
	 * 
	 * @return metaDomain
	 */
	public MetaDomain getMetaDomain() {
		return metaDomain;
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
	 * Creates metaModel.
	 * 
	 * @param metaDomainParent
	 *            metaDomain parent
	 * @param code
	 *            code
	 * @return metaModel
	 */
	public MetaModel createMetaModel(MetaDomain metaDomainParent, String code) {
		MetaModel metaModel = new MetaModel(getModel());
		metaModel.setMetaDomain(metaDomainParent);
		metaModel.setCode(code);
		if (!add(metaModel)) {
			metaModel = null;
		}
		return metaModel;
	}

}