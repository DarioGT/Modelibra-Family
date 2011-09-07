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
package modelibra.designer.metaneighbor;

import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconcept.MetaConcepts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * MetaNeighbor generated entity. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public abstract class GenMetaNeighbor extends Entity<MetaNeighbor> {

	private static final long serialVersionUID = 1211983552926L;

	private static Log log = LogFactory.getLog(GenMetaNeighbor.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Boolean id;

	private Integer min;

	private String max;

	private Boolean internal;

	/* ======= reference properties ======= */

	private Long metaDestinationConceptOid;

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaSourceConcept;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private MetaConcept metaDestinationConcept;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaNeighbor within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaNeighbor(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaNeighbor within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaSourceConcept
	 * @param MetaConcept
	 *            metaDestinationConcept
	 */
	public GenMetaNeighbor(MetaConcept metaSourceConcept,
			MetaConcept metaDestinationConcept) {
		this(metaDestinationConcept.getModel());
		// parents
		setMetaSourceConcept(metaSourceConcept);
		setMetaDestinationConcept(metaDestinationConcept);
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
	 * Sets min.
	 * 
	 * @param min
	 *            min
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * Gets min.
	 * 
	 * @return min
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * Sets max.
	 * 
	 * @param max
	 *            max
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets max.
	 * 
	 * @return max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * Sets internal.
	 * 
	 * @param internal
	 *            internal
	 */
	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	/**
	 * Gets internal.
	 * 
	 * @return internal
	 */
	public Boolean getInternal() {
		return internal;
	}

	/**
	 * Sets internal.
	 * 
	 * @param internal
	 *            internal
	 */
	public void setInternal(boolean internal) {
		setInternal(new Boolean(internal));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isInternal() {
		return getInternal().booleanValue();
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets metaDestinationConceptOid.
	 * 
	 * @param metaDestinationConceptOid
	 *            metaDestinationConceptOid
	 */
	public void setMetaDestinationConceptOid(Long metaDestinationConceptOid) {
		this.metaDestinationConceptOid = metaDestinationConceptOid;
		metaDestinationConcept = null;
	}

	/**
	 * Gets metaDestinationConceptOid.
	 * 
	 * @return metaDestinationConceptOid
	 */
	public Long getMetaDestinationConceptOid() {
		return metaDestinationConceptOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaSourceConcept.
	 * 
	 * @param metaSourceConcept
	 *            metaSourceConcept
	 */
	public void setMetaSourceConcept(MetaConcept metaSourceConcept) {
		this.metaSourceConcept = metaSourceConcept;
	}

	/**
	 * Gets metaSourceConcept.
	 * 
	 * @return metaSourceConcept
	 */
	public MetaConcept getMetaSourceConcept() {
		return metaSourceConcept;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets metaDestinationConcept.
	 * 
	 * @param metaDestinationConcept
	 *            metaDestinationConcept
	 */
	public void setMetaDestinationConcept(MetaConcept metaDestinationConcept) {
		this.metaDestinationConcept = metaDestinationConcept;
		if (metaDestinationConcept != null) {
			metaDestinationConceptOid = metaDestinationConcept.getOid()
					.getUniqueNumber();
		} else {
			metaDestinationConceptOid = null;
		}
	}

	/**
	 * Gets metaDestinationConcept.
	 * 
	 * @return metaDestinationConcept
	 */
	public MetaConcept getMetaDestinationConcept() {
		if (metaDestinationConcept == null) {
			Designer designer = (Designer) getModel();
			MetaConcepts metaConcepts = designer.getMetaConcepts();
			if (metaDestinationConceptOid != null) {
				metaDestinationConcept = metaConcepts
						.getMetaConcept(metaDestinationConceptOid);
			}
		}
		return metaDestinationConcept;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}