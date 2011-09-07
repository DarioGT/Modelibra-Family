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
package dm.meta.neighbor;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dm.meta.concept.Concept;

/**
 * Neighbor entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-13
 */
public class Neighbor extends Entity<Neighbor> {

	private static final long serialVersionUID = 110110150L;

	// private static Log log = LogFactory.getLog(Neighbor.class);

	// dmLite model

	public static final boolean DEFAULT_EXTENSION = false;

	public static final boolean DEFAULT_INTERNAL = true;

	public static final boolean DEFAULT_PART_OF_MANY_TO_MANY = false;

	public static final String DEFAULT_TYPE = "child";

	public static final int DEFAULT_MIN = 0;

	public static final String DEFAULT_MAX = "N";

	public static final boolean DEFAULT_UNIQUE = false;

	public static final boolean DEFAULT_INDEX = false;

	public static final String DEFAULT_ADD_RULE = "NONE";

	public static final String DEFAULT_REMOVE_RULE = "NONE";

	public static final String DEFAULT_UPDATE_RULE = "NONE";

	// dmLite views

	public static final boolean DEFAULT_DISPLAY = true;

	public static final boolean DEFAULT_UPDATE = true;

	public static final boolean DEFAULT_ABSORB = true;

	// dmLite model

	private Boolean extension = Boolean.valueOf(DEFAULT_EXTENSION);

	private String extensionNeighbor;

	private String destinationConcept;

	private String inverseNeighbor;

	private Boolean internal = Boolean.valueOf(DEFAULT_INTERNAL);

	private Boolean partOfManyToMany = Boolean
			.valueOf(DEFAULT_PART_OF_MANY_TO_MANY);

	private String type = DEFAULT_TYPE;

	private Integer min = Integer.valueOf(DEFAULT_MIN);

	private String max = DEFAULT_MAX;

	private Boolean unique = Boolean.valueOf(DEFAULT_UNIQUE);

	private Boolean index = Boolean.valueOf(DEFAULT_INDEX);

	private String addRule = DEFAULT_ADD_RULE;

	private String removeRule = DEFAULT_REMOVE_RULE;

	private String updateRule = DEFAULT_UPDATE_RULE;

	// dmLite views

	private Boolean display = Boolean.valueOf(DEFAULT_DISPLAY);

	private Boolean update = Boolean.valueOf(DEFAULT_UPDATE);

	private Boolean absorb = Boolean.valueOf(DEFAULT_ABSORB);

	// Concept parent neighbor (internal)
	private Concept concept;

	/**
	 * Constructs a concept neighbor within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Neighbor(IDomainModel domainModel) {
		super(domainModel);
		// no internal child neighbors
	}

	/**
	 * Constructs a concept neighbor for the parent concept.
	 * 
	 * @param concept
	 *            concept
	 */
	public Neighbor(Concept concept) {
		this(concept.getModel());
		// parent
		this.concept = concept;
	}

	/**
	 * Sets an extension switch.
	 * 
	 * @param extension
	 *            extension
	 */
	public void setExtension(Boolean extension) {
		this.extension = extension;
	}

	/**
	 * Gets an extension switch.
	 * 
	 * @return extension
	 */
	public Boolean getExtension() {
		if (extension == null) {
			setExtension(Boolean.valueOf(DEFAULT_EXTENSION));
		}
		return extension;
	}

	/**
	 * Checks if extension.
	 * 
	 * @return <code>true</code> if extension
	 */
	public boolean isExtension() {
		return getExtension().booleanValue();
	}

	/**
	 * Sets an extension neighbor.
	 * 
	 * @param extensionNeighbor
	 *            extension neighbor
	 */
	public void setExtensionNeighbor(String extensionNeighbor) {
		this.extensionNeighbor = extensionNeighbor;
	}

	/**
	 * Gets an extension neighbor.
	 * 
	 * @return extension neighbor
	 */
	public String getExtensionNeighbor() {
		return extensionNeighbor;
	}

	/**
	 * Sets a destination concept.
	 * 
	 * @param destinationConcept
	 *            destination concept
	 */
	public void setDestinationConcept(String destinationConcept) {
		this.destinationConcept = destinationConcept;
	}

	/**
	 * Gets a destination concept.
	 * 
	 * @return destination concept
	 */
	public String getDestinationConcept() {
		return destinationConcept;
	}

	/**
	 * Sets an inverse neighbor.
	 * 
	 * @param inverseNeighbor
	 *            inverse neighbor
	 */
	public void setInverseNeighbor(String inverseNeighbor) {
		this.inverseNeighbor = inverseNeighbor;
	}

	/**
	 * Gets an inverse neighbor.
	 * 
	 * @return inverse neighbor
	 */
	public String getInverseNeighbor() {
		return inverseNeighbor;
	}

	/**
	 * Sets an internal switch.
	 * 
	 * @param internal
	 *            internal switch
	 */
	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	/**
	 * Gets an internal switch.
	 * 
	 * @return <code>true</code> if internal switch
	 */
	public Boolean getInternal() {
		if (internal == null) {
			setInternal(Boolean.valueOf(DEFAULT_INTERNAL));
		}
		return internal;
	}

	/**
	 * Checks if an internal neighbor.
	 * 
	 * @return <code>true</code> if internal neighbor
	 */
	public boolean isInternal() {
		return getInternal().booleanValue();
	}

	/**
	 * Sets a part of many-to-many switch.
	 * 
	 * @param partOfManyToMany
	 *            part of many-to-many switch
	 */
	public void setPartOfManyToMany(Boolean partOfManyToMany) {
		this.partOfManyToMany = partOfManyToMany;
	}

	/**
	 * Gets a part of many-to-many switch.
	 * 
	 * @return <code>true</code> if part of many-to-many switch
	 */
	public Boolean getPartOfManyToMany() {
		if (partOfManyToMany == null) {
			setPartOfManyToMany(Boolean.valueOf(DEFAULT_PART_OF_MANY_TO_MANY));
		}
		return partOfManyToMany;
	}

	/**
	 * Checks if a part of many-to-many neighbor.
	 * 
	 * @return <code>true</code> if part of many-to-many neighbor
	 */
	public boolean isPartOfManyToMany() {
		return getPartOfManyToMany().booleanValue();
	}

	/**
	 * Sets a neighbor type.
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		if (type != null && (type.equals("child") || type.equals("parent"))) {
			this.type = type;
		} else {
			type = DEFAULT_TYPE;
		}
	}

	/**
	 * Gets a neighbor type.
	 * 
	 * @return type
	 */
	public String getType() {
		if (type == null) {
			setType(DEFAULT_TYPE);
		}
		return type;
	}

	/**
	 * Sets a minimal cardinality.
	 * 
	 * @param min
	 *            minimal cardinality
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * Gets a minimal cardinality.
	 * 
	 * @return minimal cardinality
	 */
	public Integer getMin() {
		if (min == null) {
			setMin(Integer.valueOf(DEFAULT_MIN));
		}
		return min;
	}

	/**
	 * Sets a maximal cardinality.
	 * 
	 * @param max
	 *            maximal cardinality
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets a maximal cardinality.
	 * 
	 * @return maximal cardinality
	 */
	public String getMax() {
		if (max == null) {
			setMax(DEFAULT_MAX);
		}
		return max;
	}

	/**
	 * Sets a unique switch.
	 * 
	 * @param unique
	 *            unique switch
	 */
	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	/**
	 * Gets a unique switch.
	 * 
	 * @return unique switch
	 */
	public Boolean getUnique() {
		if (unique == null) {
			setUnique(Boolean.valueOf(DEFAULT_UNIQUE));
		}
		return unique;
	}

	/**
	 * Checks if the neighbor is a part of the unique definition.
	 * 
	 * @return <code>true</code> if unique neighbor
	 */
	public boolean isUnique() {
		return getUnique().booleanValue();
	}

	/**
	 * Sets an index switch.
	 * 
	 * @param index
	 *            index switch
	 */
	public void setIndex(Boolean index) {
		this.index = index;
	}

	/**
	 * Gets an index switch.
	 * 
	 * @return index switch
	 */
	public Boolean getIndex() {
		if (index == null) {
			setIndex(Boolean.valueOf(DEFAULT_INDEX));
		}
		return index;
	}

	/**
	 * Checks if the property is indexed.
	 * 
	 * @return <code>true</code> if the property is indexed
	 */
	public boolean isIndex() {
		return getIndex().booleanValue();
	}

	/**
	 * Sets an add rule.
	 * 
	 * @param addRule
	 *            add rule
	 */
	public void setAddRule(String addRule) {
		if (addRule.equals("NONE") || addRule.equals("RESTRICT")
				|| addRule.equals("DETACH")) {
			this.addRule = addRule;
		} else {
			addRule = DEFAULT_ADD_RULE;
		}
	}

	/**
	 * Gets an add rule.
	 * 
	 * @return add rule.
	 */
	public String getAddRule() {
		if (addRule == null) {
			setAddRule(DEFAULT_ADD_RULE);
		}
		return addRule;
	}

	/**
	 * Sets a remove rule.
	 * 
	 * @param removeRule
	 *            remove rule
	 */
	public void setRemoveRule(String removeRule) {
		if (removeRule.equals("NONE") || removeRule.equals("RESTRICT")
				|| removeRule.equals("CASCADE")) {
			this.removeRule = removeRule;
		} else {
			removeRule = DEFAULT_REMOVE_RULE;
		}
	}

	/**
	 * Gets a remove rule.
	 * 
	 * @return remove rule.
	 */
	public String getRemoveRule() {
		if (removeRule == null) {
			setRemoveRule(DEFAULT_REMOVE_RULE);
		}
		return removeRule;
	}

	/**
	 * Sets an update rule.
	 * 
	 * @param updateRule
	 *            update rule
	 */
	public void setUpdateRule(String updateRule) {
		if (updateRule.equals("NONE") || updateRule.equals("RESTRICT")
				|| updateRule.equals("DETACH")) {
			this.updateRule = updateRule;
		} else {
			updateRule = DEFAULT_UPDATE_RULE;
		}
	}

	/**
	 * Gets an update rule.
	 * 
	 * @return update rule.
	 */
	public String getUpdateRule() {
		if (updateRule == null) {
			setUpdateRule(DEFAULT_UPDATE_RULE);
		}
		return updateRule;
	}

	/**
	 * Sets a display switch.
	 * 
	 * @param display
	 *            display switch
	 */
	public void setDisplay(Boolean display) {
		this.display = display;
	}

	/**
	 * Gets a display switch.
	 * 
	 * @return display switch
	 */
	public Boolean getDisplay() {
		if (display == null) {
			setDisplay(Boolean.valueOf(DEFAULT_DISPLAY));
		}
		return display;
	}

	/**
	 * Checks if to display the neighbor.
	 * 
	 * @return <code>true</code> if to display the neighbor
	 */
	public boolean isDisplay() {
		return getDisplay().booleanValue();
	}

	/**
	 * Sets an update switch.
	 * 
	 * @param update
	 *            update switch
	 */
	public void setUpdate(Boolean update) {
		this.update = update;
	}

	/**
	 * Gets an update switch.
	 * 
	 * @return update switch
	 */
	public Boolean getUpdate() {
		if (update == null) {
			setUpdate(Boolean.valueOf(DEFAULT_UPDATE));
		}
		return update;
	}

	/**
	 * Checks if to update the neighbor.
	 * 
	 * @return <code>true</code> if to update the neighbor
	 */
	public boolean isUpdate() {
		return getUpdate().booleanValue();
	}

	/**
	 * Sets an absorb switch.
	 * 
	 * @param absorb
	 *            absorb switch
	 */
	public void setAbsorb(Boolean absorb) {
		this.absorb = absorb;
	}

	/**
	 * Gets an absorb switch.
	 * 
	 * @return absorb switch
	 */
	public Boolean getAbsorb() {
		if (absorb == null) {
			setAbsorb(Boolean.valueOf(DEFAULT_ABSORB));
		}
		return absorb;
	}

	/**
	 * Checks if to absorb the neighbor.
	 * 
	 * @return <code>true</code> if to absorb the neighbor
	 */
	public boolean isAbsorb() {
		return getAbsorb().booleanValue();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets a concept.
	 * 
	 * @param concept
	 *            concept
	 */
	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	/**
	 * Gets a concept.
	 * 
	 * @return concept
	 */
	public Concept getConcept() {
		return concept;
	}

}