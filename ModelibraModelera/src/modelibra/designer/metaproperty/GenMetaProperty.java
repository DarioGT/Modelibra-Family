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

import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metatype.MetaType;
import modelibra.designer.metatype.MetaTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * MetaProperty generated entity. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaProperty extends Entity<MetaProperty> {

	private static final long serialVersionUID = 1211138929239L;

	private static Log log = LogFactory.getLog(GenMetaProperty.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Boolean id;

	private Boolean increment;

	private Boolean value;

	private String init;

	/* ======= reference properties ======= */

	private Long metaTypeOid;

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaConcept;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private MetaType metaType;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaProperty within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaProperty(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaProperty within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaConcept
	 * @param MetaType
	 *            metaType
	 */
	public GenMetaProperty(MetaConcept metaConcept, MetaType metaType) {
		this(metaConcept.getModel()); // generated: this(metaType.getModel());
		// parents
		setMetaConcept(metaConcept);
		setMetaType(metaType);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets id.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(Boolean id) {
		this.id = id;
	}

	/**
	 * Gets id.
	 * 
	 * @return id
	 */
	public Boolean getId() {
		return id;
	}

	/**
	 * Sets id.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(boolean id) {
		setId(new Boolean(id));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isId() {
		return getId().booleanValue();
	}

	/**
	 * Sets increment.
	 * 
	 * @param increment
	 *            increment
	 */
	public void setIncrement(Boolean increment) {
		this.increment = increment;
	}

	/**
	 * Gets increment.
	 * 
	 * @return increment
	 */
	public Boolean getIncrement() {
		return increment;
	}

	/**
	 * Sets increment.
	 * 
	 * @param increment
	 *            increment
	 */
	public void setIncrement(boolean increment) {
		setIncrement(new Boolean(increment));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isIncrement() {
		return getIncrement().booleanValue();
	}

	/**
	 * Sets value.
	 * 
	 * @param value
	 *            value
	 */
	public void setValue(Boolean value) {
		this.value = value;
	}

	/**
	 * Gets value.
	 * 
	 * @return value
	 */
	public Boolean getValue() {
		return value;
	}

	/**
	 * Sets value.
	 * 
	 * @param value
	 *            value
	 */
	public void setValue(boolean value) {
		setValue(new Boolean(value));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isValue() {
		return getValue().booleanValue();
	}

	/**
	 * Sets init.
	 * 
	 * @param init
	 *            init
	 */
	public void setInit(String init) {
		this.init = init;
	}

	/**
	 * Gets init.
	 * 
	 * @return init
	 */
	public String getInit() {
		return init;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets metaTypeOid.
	 * 
	 * @param metaTypeOid
	 *            metaTypeOid
	 */
	public void setMetaTypeOid(Long metaTypeOid) {
		this.metaTypeOid = metaTypeOid;
		metaType = null;
	}

	/**
	 * Gets metaTypeOid.
	 * 
	 * @return metaTypeOid
	 */
	public Long getMetaTypeOid() {
		return metaTypeOid;
	}

	/* ======= derived property abstract get methods ======= */

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

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets metaType.
	 * 
	 * @param metaType
	 *            metaType
	 */
	public void setMetaType(MetaType metaType) {
		this.metaType = metaType;
		if (metaType != null) {
			metaTypeOid = metaType.getOid().getUniqueNumber();
		} else {
			metaTypeOid = null;
		}
	}

	/**
	 * Gets metaType.
	 * 
	 * @return metaType
	 */
	public MetaType getMetaType() {
		if (metaType == null) {
			Designer designer = (Designer) getModel();
			MetaTypes metaTypes = designer.getMetaTypes();
			if (metaTypeOid != null) {
				metaType = metaTypes.getMetaType(metaTypeOid);
			}
		}
		return metaType;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}