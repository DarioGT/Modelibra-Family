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

import modelibra.designer.Designer;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphics;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaneighbor.MetaNeighbors;
import modelibra.designer.metaproperty.MetaProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * MetaConcept generated entity. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-28
 */
public abstract class GenMetaConcept extends Entity<MetaConcept> {

	private static final long serialVersionUID = 1208025843876L;

	private static Log log = LogFactory.getLog(GenMetaConcept.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Boolean entry;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private MetaModel metaModel;

	/* ======= internal child neighbors ======= */

	private MetaConceptGraphics metaConceptGraphics;

	private MetaProperties metaProperties;

	private MetaNeighbors metaDestinationNeighbors;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private MetaNeighbors metaSourceNeighbors;

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConcept within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaConcept(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setMetaConceptGraphics(new MetaConceptGraphics((MetaConcept) this));
		setMetaProperties(new MetaProperties((MetaConcept) this));
		setMetaDestinationNeighbors(new MetaNeighbors((MetaConcept) this, null));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaConcept within its parent(s).
	 * 
	 * @param MetaModel
	 *            metaModel
	 */
	public GenMetaConcept(MetaModel metaModel) {
		this(metaModel.getModel());
		// parents
		setMetaModel(metaModel);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets entry.
	 * 
	 * @param entry
	 *            entry
	 */
	public void setEntry(Boolean entry) {
		this.entry = entry;
	}

	/**
	 * Gets entry.
	 * 
	 * @return entry
	 */
	public Boolean getEntry() {
		return entry;
	}

	/**
	 * Sets entry.
	 * 
	 * @param entry
	 *            entry
	 */
	public void setEntry(boolean entry) {
		setEntry(new Boolean(entry));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isEntry() {
		return getEntry().booleanValue();
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

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

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets metaConceptGraphics.
	 * 
	 * @param metaConceptGraphics
	 *            metaConceptGraphics
	 */
	public void setMetaConceptGraphics(MetaConceptGraphics metaConceptGraphics) {
		this.metaConceptGraphics = metaConceptGraphics;
		if (metaConceptGraphics != null) {
			metaConceptGraphics.setMetaConcept((MetaConcept) this);
		}
	}

	/**
	 * Gets metaConceptGraphics.
	 * 
	 * @return metaConceptGraphics
	 */
	public MetaConceptGraphics getMetaConceptGraphics() {
		return metaConceptGraphics;
	}

	/**
	 * Sets metaProperties.
	 * 
	 * @param metaProperties
	 *            metaProperties
	 */
	public void setMetaProperties(MetaProperties metaProperties) {
		this.metaProperties = metaProperties;
		if (metaProperties != null) {
			metaProperties.setMetaConcept((MetaConcept) this);
		}
	}

	/**
	 * Gets metaProperties.
	 * 
	 * @return metaProperties
	 */
	public MetaProperties getMetaProperties() {
		return metaProperties;
	}

	/**
	 * Sets metaDestinationNeighbors.
	 * 
	 * @param metaDestinationNeighbors
	 *            metaDestinationNeighbors
	 */
	public void setMetaDestinationNeighbors(
			MetaNeighbors metaDestinationNeighbors) {
		this.metaDestinationNeighbors = metaDestinationNeighbors;
		if (metaDestinationNeighbors != null) {
			metaDestinationNeighbors.setMetaSourceConcept((MetaConcept) this);
		}
	}

	/**
	 * Gets metaDestinationNeighbors.
	 * 
	 * @return metaDestinationNeighbors
	 */
	public MetaNeighbors getMetaDestinationNeighbors() {
		return metaDestinationNeighbors;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

	/**
	 * Sets metaSourceNeighbors.
	 * 
	 * @param metaSourceNeighbors
	 *            metaSourceNeighbors
	 */
	public void setMetaSourceNeighbors(MetaNeighbors metaSourceNeighbors) {
		this.metaSourceNeighbors = metaSourceNeighbors;
		if (metaSourceNeighbors != null) {
			metaSourceNeighbors.setMetaDestinationConcept((MetaConcept) this);
		}
	}

	/**
	 * Gets metaSourceNeighbors.
	 * 
	 * @return metaSourceNeighbors
	 */
	public MetaNeighbors getMetaSourceNeighbors() {
		if (metaSourceNeighbors == null) {
			Designer designer = (Designer) getModel();
			MetaConcepts metaConcepts = designer.getMetaConcepts();
			setMetaSourceNeighbors(metaConcepts
					.getMetaDestinationConceptMetaSourceNeighbors((MetaConcept) this));
		}
		return metaSourceNeighbors;
	}

}