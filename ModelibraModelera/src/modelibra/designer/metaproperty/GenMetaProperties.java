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
package modelibra.designer.metaproperty;

import java.util.Comparator;

import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metatype.MetaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * MetaProperty generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaProperties extends Entities<MetaProperty> {

	private static final long serialVersionUID = 1211138929240L;

	private static Log log = LogFactory.getLog(GenMetaProperties.class);

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaConcept;

	/* ======= external parent neighbors ======= */

	private MetaType metaType;

	/* ======= base constructor ======= */

	/**
	 * Constructs metaProperties within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaProperties(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaProperties for the metaConcept parent.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public GenMetaProperties(MetaConcept metaConcept) {
		this(metaConcept.getModel());
		// parent
		setMetaConcept(metaConcept);
	}

	/**
	 * Constructs metaProperties for the metaType parent.
	 * 
	 * @param metaType
	 *            metaType
	 */
	public GenMetaProperties(MetaType metaType) {
		this(metaType.getModel());
		// parent
		setMetaType(metaType);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the metaProperty with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaProperty
	 */
	public MetaProperty getMetaProperty(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaProperty with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaProperty
	 */
	public MetaProperty getMetaProperty(Long oidUniqueNumber) {
		return getMetaProperty(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaProperty whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaProperty
	 */
	public MetaProperty getMetaProperty(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaProperties whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaProperties
	 */
	public MetaProperties getMetaProperties(String propertyCode, Object property) {
		return (MetaProperties) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaProperties ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaProperties(String propertyCode,
			boolean ascending) {
		return (MetaProperties) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaProperties selected by a selector. Returns empty metaProperties
	 * if there are no metaProperties that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaProperties
	 */
	public MetaProperties getMetaProperties(ISelector selector) {
		return (MetaProperties) selectBySelector(selector);
	}

	/**
	 * Gets metaProperties ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaProperties(Comparator comparator,
			boolean ascending) {
		return (MetaProperties) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets id metaProperties.
	 * 
	 * @param id
	 *            id
	 * @return id metaProperties
	 */
	public MetaProperties getIdMetaProperties(Boolean id) {
		PropertySelector propertySelector = new PropertySelector("id");
		propertySelector.defineEqual(id);
		return getMetaProperties(propertySelector);
	}

	/**
	 * Gets increment metaProperties.
	 * 
	 * @param increment
	 *            increment
	 * @return increment metaProperties
	 */
	public MetaProperties getIncrementMetaProperties(Boolean increment) {
		PropertySelector propertySelector = new PropertySelector("increment");
		propertySelector.defineEqual(increment);
		return getMetaProperties(propertySelector);
	}

	/**
	 * Gets value metaProperties.
	 * 
	 * @param value
	 *            value
	 * @return value metaProperties
	 */
	public MetaProperties getValueMetaProperties(Boolean value) {
		PropertySelector propertySelector = new PropertySelector("value");
		propertySelector.defineEqual(value);
		return getMetaProperties(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets metaType metaProperties.
	 * 
	 * @param metaType
	 *            metaType oid unique number
	 * @return metaType metaProperties
	 */
	public MetaProperties getMetaTypeMetaProperties(Long metaType) {
		PropertySelector propertySelector = new PropertySelector("metaTypeOid");
		propertySelector.defineEqual(metaType);
		return getMetaProperties(propertySelector);
	}

	/**
	 * Gets metaType metaProperties.
	 * 
	 * @param metaType
	 *            metaType oid
	 * @return metaType metaProperties
	 */
	public MetaProperties getMetaTypeMetaProperties(Oid metaType) {
		return getMetaTypeMetaProperties(metaType.getUniqueNumber());
	}

	/**
	 * Gets metaType metaProperties.
	 * 
	 * @param metaType
	 *            metaType
	 * @return metaType metaProperties
	 */
	public MetaProperties getMetaTypeMetaProperties(MetaType metaType) {
		return getMetaTypeMetaProperties(metaType.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets metaProperties ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaPropertiesOrderedByCode(boolean ascending) {
		return getMetaProperties("code", ascending);
	}

	/**
	 * Gets metaProperties ordered by id.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaPropertiesOrderedById(boolean ascending) {
		return getMetaProperties("id", ascending);
	}

	/**
	 * Gets metaProperties ordered by increment.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaPropertiesOrderedByIncrement(boolean ascending) {
		return getMetaProperties("increment", ascending);
	}

	/**
	 * Gets metaProperties ordered by value.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaProperties
	 */
	public MetaProperties getMetaPropertiesOrderedByValue(boolean ascending) {
		return getMetaProperties("value", ascending);
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

	/**
	 * Sets metaType.
	 * 
	 * @param metaType
	 *            metaType
	 */
	public void setMetaType(MetaType metaType) {
		this.metaType = metaType;
	}

	/**
	 * Gets metaType.
	 * 
	 * @return metaType
	 */
	public MetaType getMetaType() {
		return metaType;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(MetaProperty metaProperty) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(metaProperty)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				MetaType metaType = getMetaType();
				if (metaType == null) {
					MetaType metaPropertyMetaType = metaProperty.getMetaType();
					if (metaPropertyMetaType != null) {
						if (!metaPropertyMetaType.getMetaProperties().contain(
								metaProperty)) {
							metaPropertyMetaType.getMetaProperties()
									.setPropagateToSource(false);
							post = metaPropertyMetaType.getMetaProperties()
									.add(metaProperty);
							metaPropertyMetaType.getMetaProperties()
									.setPropagateToSource(true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(MetaProperty metaProperty) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(metaProperty)) {
			MetaType metaType = getMetaType();
			if (metaType == null) {
				MetaType metaPropertyMetaType = metaProperty.getMetaType();
				if (metaPropertyMetaType != null) {
					if (metaPropertyMetaType.getMetaProperties().contain(
							metaProperty)) {
						metaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(false);
						post = metaPropertyMetaType.getMetaProperties().remove(
								metaProperty);
						metaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(MetaProperty beforeMetaProperty,
			MetaProperty afterMetaProperty) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeMetaProperty, afterMetaProperty)) {
			MetaType metaType = getMetaType();
			if (metaType == null) {
				MetaType beforeMetaPropertyMetaType = beforeMetaProperty
						.getMetaType();
				MetaType afterMetaPropertyMetaType = afterMetaProperty
						.getMetaType();
				if (beforeMetaPropertyMetaType == null
						&& afterMetaPropertyMetaType != null) {
					// attach
					if (!afterMetaPropertyMetaType.getMetaProperties().contain(
							afterMetaProperty)) {
						afterMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(false);
						post = afterMetaPropertyMetaType.getMetaProperties()
								.add(afterMetaProperty);
						afterMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(true);
					}
				} else if (beforeMetaPropertyMetaType != null
						&& afterMetaPropertyMetaType == null) {
					// detach
					if (beforeMetaPropertyMetaType.getMetaProperties().contain(
							beforeMetaProperty)) {
						beforeMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(false);
						post = beforeMetaPropertyMetaType.getMetaProperties()
								.remove(beforeMetaProperty);
						beforeMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(true);
					}
				} else if (beforeMetaPropertyMetaType != null
						&& afterMetaPropertyMetaType != null
						&& beforeMetaPropertyMetaType != afterMetaPropertyMetaType) {
					// detach
					if (beforeMetaPropertyMetaType.getMetaProperties().contain(
							beforeMetaProperty)) {
						beforeMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(false);
						post = beforeMetaPropertyMetaType.getMetaProperties()
								.remove(beforeMetaProperty);
						beforeMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(true);
					}
					// attach
					if (!afterMetaPropertyMetaType.getMetaProperties().contain(
							afterMetaProperty)) {
						afterMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(false);
						post = afterMetaPropertyMetaType.getMetaProperties()
								.add(afterMetaProperty);
						afterMetaPropertyMetaType.getMetaProperties()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates metaProperty.
	 * 
	 * @param metaConceptParent
	 *            metaConcept parent
	 * @param metaTypeParent
	 *            metaType parent
	 * @param code
	 *            code
	 * @param id
	 *            id
	 * @param increment
	 *            increment
	 * @param value
	 *            value
	 * @return metaProperty
	 */
	public MetaProperty createMetaProperty(MetaConcept metaConceptParent,
			MetaType metaTypeParent, String code, Boolean id,
			Boolean increment, Boolean value) {
		MetaProperty metaProperty = new MetaProperty(getModel());
		metaProperty.setMetaConcept(metaConceptParent);
		metaProperty.setMetaType(metaTypeParent);
		metaProperty.setCode(code);
		metaProperty.setId(id);
		metaProperty.setIncrement(increment);
		metaProperty.setValue(value);
		if (!add(metaProperty)) {
			metaProperty = null;
		}
		return metaProperty;
	}

}