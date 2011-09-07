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

import modelibra.designer.Designer;
import modelibra.designer.metaproperty.MetaProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * MetaType generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaType extends Entity<MetaType> {

	private static final long serialVersionUID = 1211139625807L;

	private static Log log = LogFactory.getLog(GenMetaType.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String base;

	private Integer length;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private MetaProperties metaProperties;

	/* ======= base constructor ======= */

	/**
	 * Constructs metaType within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaType(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets base.
	 * 
	 * @param base
	 *            base
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Gets base.
	 * 
	 * @return base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Sets length.
	 * 
	 * @param length
	 *            length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Gets length.
	 * 
	 * @return length
	 */
	public Integer getLength() {
		return length;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets metaProperties.
	 * 
	 * @param metaProperties
	 *            metaProperties
	 */
	public void setMetaProperties(MetaProperties metaProperties) {
		this.metaProperties = metaProperties;
		if (metaProperties != null) {
			metaProperties.setMetaType((MetaType) this);
		}
	}

	/**
	 * Gets metaProperties.
	 * 
	 * @return metaProperties
	 */
	public MetaProperties getMetaProperties() {
		if (metaProperties == null) {
			Designer designer = (Designer) getModel();
			MetaProperties metaProperties = designer.getMetaProperties();
			setMetaProperties(metaProperties
					.getMetaTypeMetaProperties((MetaType) this));
		}
		return metaProperties;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}