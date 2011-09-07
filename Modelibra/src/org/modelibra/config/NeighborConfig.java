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
package org.modelibra.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IEntity;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.util.TextHandler;
import org.modelibra.util.Transformer;

/**
 * Concept neighbor configuration consists of properties for the concept
 * neighbor configuration used in Modelibra, and neighbor properties for the
 * model default application used in dmWicket.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-28
 */
public class NeighborConfig extends Entity<NeighborConfig> implements
		IPersistentEntity {

	private static final long serialVersionUID = 2070L;;

	private static Log log = LogFactory.getLog(NeighborConfig.class);

	public static final String PARENT = "parent";

	public static final String CHILD = "child";

	private ConceptConfig conceptConfig; // parent

	/*
	 * The concept neighbor configuration properties.
	 */

	private String extension; // Boolean

	private String extensionNeighbor;

	private String destinationConcept;

	private String inverseNeighbor;

	private String internal; // Boolean

	private String partOfManyToMany; // Boolean

	private String type;

	private String min; // Integer

	private String max;

	private String unique; // Boolean

	private String index; // Boolean

	private String addRule;

	private String removeRule;

	private String updateRule;

	/*
	 * End of the concept neighbor configuration properties.
	 */

	/*
	 * The model default application concept neighbor configuration properties.
	 */

	private String display; // Boolean

	private String update; // Boolean

	private String absorb; // Boolean

	/*
	 * End of the model default application concept neighbor configuration
	 * properties.
	 */

	private XmlNeighborConfig xmlNeighbor;

	private TextHandler textHandler = new TextHandler();

	private boolean extended = false;

	public NeighborConfig() {
		super();
		xmlNeighbor = new XmlNeighborConfig(this);
	}

	/**
	 * Sets the concept configuration.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void setConceptConfig(ConceptConfig conceptConfig) {
		this.conceptConfig = conceptConfig;
	}

	/**
	 * Gets the concept configuration.
	 * 
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig() {
		return conceptConfig;
	}

	/**
	 * Gets the concept neighbor code that starts with a capital letter.
	 * 
	 * @return concept neighbor code that starts with a capital letter
	 */
	public String getCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getCode());
	}

	/**
	 * Gets the concept neighbor code that starts with a lower letter.
	 * 
	 * @return concept neighbor code that starts with a lower letter
	 */
	public String getCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getCode());
	}

	/**
	 * Gets the neighbor name that starts with a capital letter.
	 * 
	 * @return neighbor name
	 */
	public String getNeighborName() {
		return getCodeWithFirstLetterAsUpper();
	}

	/**
	 * Sets the neighbor extension flag: true string if there is an extension.
	 * 
	 * @param extension
	 *            true string if there is an extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets the neighbor extension flag: true string if there is an extension.
	 * 
	 * @return true string if there is an extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the neighbor extension flag: <code>true</code> if there is an
	 * extension.
	 * 
	 * @param extension
	 *            <code>true</code> if there is an extension
	 */
	public void setExtension(boolean extension) {
		if (extension) {
			setExtension("true");
		} else {
			setExtension("false");
		}
	}

	/**
	 * Checks if there is an extension. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if there is an extension
	 */
	public boolean isExtension() {
		boolean result = false;
		if (extension != null) {
			if (extension.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the extension neighbor.
	 * 
	 * @param extensionNeighbor
	 *            extension neighbor
	 */
	public void setExtensionNeighbor(String extensionNeighbor) {
		this.extensionNeighbor = extensionNeighbor;
	}

	/**
	 * Gets the extension neighbor.
	 * 
	 * @return extension neighbor
	 */
	public String getExtensionNeighbor() {
		if (isExtension() && extensionNeighbor == null) {
			log.error(getCode()
					+ " neighbor does not have the extension neighbor.");
		}
		return extensionNeighbor;
	}

	/**
	 * Sets the destination concept code.
	 * 
	 * @param destinationConcept
	 *            destination concept code
	 */
	public void setDestinationConcept(String destinationConcept) {
		this.destinationConcept = destinationConcept;
	}

	/**
	 * Gets the destination concept code.
	 * 
	 * @return destination concept code
	 */
	public String getDestinationConcept() {
		if (!isExtension() && destinationConcept == null) {
			log.info("The neighbor does not have the destination concept: "
					+ getCode());
		}
		return destinationConcept;
	}

	/**
	 * Gets the neighbor destination concept configuration.
	 * 
	 * @return neighbor destination concept configuration
	 */
	public ConceptConfig getDestinationConceptConfig() {
		ModelConfig modelConfig = getConceptConfig().getModelConfig();
		ConceptsConfig conceptsConfig = modelConfig.getConceptsConfig();
		ConceptConfig conceptConfig = conceptsConfig
				.getConceptConfig(getDestinationConcept());
		return conceptConfig;
	}

	/**
	 * Gets the neighbor destination concept class name (complete name).
	 * 
	 * @return neighbor destination concept class name
	 */
	public String getDestinationConceptClass() {
		String destinationConceptClass = null;
		ConceptConfig destinationConceptConfig = getDestinationConceptConfig();
		if (isParent() && getMax().equals("1")) {
			destinationConceptClass = destinationConceptConfig.getEntityClass();
		} else {
			destinationConceptClass = destinationConceptConfig
					.getEntitiesClass();
		}
		return destinationConceptClass;
	}

	/**
	 * Sets the inverse neighbor.
	 * 
	 * @param inverseNeighbor
	 *            inverse neighbor
	 */
	public void setInverseNeighbor(String inverseNeighbor) {
		this.inverseNeighbor = inverseNeighbor;
	}

	/**
	 * Gets the inverse neighbor.
	 * 
	 * @return inverse neighbor
	 */
	public String getInverseNeighbor() {
		if (!isExtension() && inverseNeighbor == null) {
			String derivedInverseNeighbor = deriveInverseNeighbor();
			if (derivedInverseNeighbor != null) {
				return derivedInverseNeighbor;
			} else {
				log.info("The neighbor does not have the inverse neighbor: "
						+ getCode());
			}
		}
		return inverseNeighbor;
	}

	/**
	 * Derives the inverse neighbor (code).
	 * 
	 * @return inverse neighbor
	 */
	private String deriveInverseNeighbor() {
		String inverseNeighbor = null;
		ConceptConfig contextConceptConfig = getConceptConfig();
		String code;
		if (isChild()) {
			code = contextConceptConfig.getCodeWithFirstLetterAsLower();
		} else {
			code = contextConceptConfig.getEntitiesCodeWithFirstLetterAsLower();
		}
		ConceptConfig destinationConceptConfig = getDestinationConceptConfig();
		NeighborsConfig destinationConceptNeighborsConfig = destinationConceptConfig
				.getNeighborsConfig();
		NeighborConfig inverseNeighborConfig = destinationConceptNeighborsConfig
				.getNeighborConfig(code);
		if (inverseNeighborConfig != null) {
			inverseNeighbor = inverseNeighborConfig.getCode();
		}
		return inverseNeighbor;
	}

	/**
	 * Gets the concept neighbor code that starts with a capital letter.
	 * 
	 * @return concept neighbor code that starts with a capital letter
	 */
	public String getInverseNeighborFirstLetterAsUpper() {
		if (getInverseNeighbor() != null) {
			return textHandler.firstLetterToUpper(getInverseNeighbor());
		}
		return null;
	}

	/**
	 * Gets the inverse neighbor configuration.
	 * 
	 * @return inverse neighbor configuration
	 */
	public NeighborConfig getInverseNeighborConfig() {
		String inverseNeighbor = getInverseNeighbor();
		if (inverseNeighbor == null) {
			return null;
		}
		NeighborConfig inverseNeighborConfig = null;
		ConceptConfig destinationConceptConfig = getDestinationConceptConfig();
		NeighborsConfig destinationConceptNeighborsConfig = destinationConceptConfig
				.getNeighborsConfig();
		inverseNeighborConfig = destinationConceptNeighborsConfig
				.getNeighborConfig(inverseNeighbor);
		return inverseNeighborConfig;
	}

	/**
	 * Sets the internal neighbor flag: true if the neighbor is internal (saved
	 * within the parent).
	 * 
	 * @param internal
	 *            internal
	 */
	public void setInternal(String internal) {
		this.internal = internal;
	}

	/**
	 * Gets the internal neighbor flag: true if the neighbor is internal (saved
	 * within the parent).
	 * 
	 * @return internal
	 */
	public String getInternal() {
		return internal;
	}

	/**
	 * Sets the internal neighbor flag: <code>true</code> if the neighbor is
	 * internal (saved within the parent).
	 * 
	 * @param internal
	 *            internal
	 */
	public void setInternal(boolean internal) {
		if (internal) {
			setInternal("true");
		} else {
			setInternal("false");
		}
	}

	/**
	 * Checks if the neighbor is internal. Returns <code>true</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the neighbor is internal
	 */
	public boolean isInternal() {
		boolean result = true;
		if (internal != null) {
			if (internal.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Checks if the neighbor is external. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the neighbor is external
	 */
	public boolean isExternal() {
		return !isInternal();
	}

	/**
	 * Sets the part of many-to-many neighbor flag: true if the neighbor is part
	 * of many-to-many (a part of the many-to-many relationship).
	 * 
	 * @param partOfManyToMany
	 *            partOfManyToMany
	 */
	public void setPartOfManyToMany(String partOfManyToMany) {
		this.partOfManyToMany = partOfManyToMany;
	}

	/**
	 * Gets the part of many-to-many neighbor flag: true if the neighbor is part
	 * of many-to-many (a part of the many-to-many relationship).
	 * 
	 * @return part of many-to-many
	 */
	public String getPartOfManyToMany() {
		return partOfManyToMany;
	}

	/**
	 * Sets the part of many-to-many neighbor flag: <code>true</code> if the
	 * neighbor is part of many-to-many (a part of the many-to-many
	 * relationship).
	 * 
	 * @param partOfManyToMany
	 *            partOfManyToMany
	 */
	public void setPartOfManyToMany(boolean partOfManyToMany) {
		if (partOfManyToMany) {
			setPartOfManyToMany("true");
		} else {
			setPartOfManyToMany("false");
		}
	}

	/**
	 * Checks if the neighbor is part of many-to-many. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if the neighbor is part of many-to-many
	 */
	public boolean isPartOfManyToMany() {
		boolean result = false;
		if (partOfManyToMany != null) {
			if (partOfManyToMany.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Checks if the neighbor is one-to-many. Returns <code>false</code> if
	 * there is no value.
	 * 
	 * @return <code>true</code> if the neighbor is one-to-many
	 */
	public boolean isOneToMany() {
		return !isPartOfManyToMany();
	}

	/**
	 * Starting with the external child part of many-to-many neighbor, gets the
	 * destination concept configuration of the many-to-many internal parent
	 * neighbor.
	 * 
	 * @return destination concept configuration of the many-to-many internal
	 *         parent neighbor
	 */
	public ConceptConfig getInternalManyToManyParentConceptConfig() {
		ConceptConfig internalManyToManyParentConceptConfig = null;
		if (isExternal() && isChild() && isPartOfManyToMany()) {
			ConceptConfig manyToManyConceptConfig = getDestinationConceptConfig();
			if (manyToManyConceptConfig.isManyToMany()) {
				NeighborConfig internalParentNeighborConfig = manyToManyConceptConfig
						.getInternalParentNeighbor();
				if (internalParentNeighborConfig != null
						&& internalParentNeighborConfig.isPartOfManyToMany()) {
					internalManyToManyParentConceptConfig = internalParentNeighborConfig
							.getDestinationConceptConfig();
				}
			}
		}
		return internalManyToManyParentConceptConfig;
	}

	/**
	 * Sets the neighbor type: parent or child.
	 * 
	 * @param type
	 *            neighbor type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the neighbor type: parent or child.
	 * 
	 * @return neighbor type
	 */
	public String getType() {
		if (type == null) {
			if (!isExtension()) {
				log
						.info("The neighbor does not have the type (parent or child): "
								+ getCode());
			}
		}
		return type;
	}

	/**
	 * Checks if the neighbor is parent. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the neighbor is parent
	 */
	public boolean isParent() {
		boolean result = false;
		if (getType() != null && getType().trim().equalsIgnoreCase(PARENT)) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks if the neighbor is child. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the neighbor is child
	 */
	public boolean isChild() {
		boolean result = false;
		if (getType() != null && getType().trim().equalsIgnoreCase(CHILD)) {
			result = true;
		}
		return result;
	}

	/**
	 * Sets the neighbor (direction) minimal cardinality (required minimal
	 * number of neighbor entities).
	 * 
	 * @param min
	 *            minimal cardinality
	 */
	public void setMin(String min) {
		this.min = min;
	}

	/**
	 * Gets the neighbor (direction) minimal cardinality (required minimal
	 * number of neighbor entities).
	 * 
	 * @return minimal cardinality
	 */
	public String getMin() {
		return min;
	}

	/**
	 * Gets the neighbor (direction) minimal cardinality (as an int number).
	 * 
	 * @return minimal cardinality
	 */
	public int getMinInt() {
		try {
			Integer minInteger = Transformer.integer(getMin());
			return minInteger.intValue();
		} catch (TypeRuntimeException e) {
			return 0;
		}
	}

	/**
	 * Checks if the neighbor is mandatory. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the neighbor is mandatory
	 */
	public boolean isMandatory() {
		boolean result = false;
		if (min != null) {
			if (getMinInt() > 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Checks if the neighbor is optional. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the neighbor is optional
	 */
	public boolean isOptional() {
		boolean result = false;
		if (min != null) {
			if (getMinInt() == 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the neighbor (direction) maximal cardinality (maximal number of
	 * neighbor entities).
	 * 
	 * @param max
	 *            maximal cardinality
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets the neighbor (direction) maximal cardinality (maximal number of
	 * neighbor entities).
	 * 
	 * @return maximal cardinality
	 */
	public String getMax() {
		if (max == null) {
			if (!isExtension()) {
				if (isChild()) {
					return "N";
				} else {
					return "1";
				}
			}
		}
		return max;
	}

	/**
	 * Sets the unique neighbor flag: true string if the neighbor is unique.
	 * 
	 * @param unique
	 *            true string if the neighbor is unique
	 */
	public void setUnique(String unique) {
		this.unique = unique;
	}

	/**
	 * Gets the unique neighbor flag: true string if the neighbor is unique.
	 * 
	 * @return true string if the neighbor is unique
	 */
	public String getUnique() {
		return unique;
	}

	/**
	 * Sets the unique neighbor flag: <code>true</code> if the neighbor is
	 * unique.
	 * 
	 * @param unique
	 *            <code>true</code> if the neighbor is unique
	 */
	public void setUnique(boolean unique) {
		if (unique) {
			setUnique("true");
		} else {
			setUnique("false");
		}
	}

	/**
	 * Checks if the neighbor is unique. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the neighbor is unique
	 */
	public boolean isUnique() {
		boolean result = false;
		if (unique != null) {
			if (unique.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the index neighbor flag: true string if the neighbor is indexed.
	 * 
	 * @param index
	 *            true string if the neighbor is indexed
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets the index neighbor flag: true string if the neighbor is indexed.
	 * 
	 * @return true string if the neighbor is indexed
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index neighbor flag: <code>true</code> if the neighbor is
	 * indexed.
	 * 
	 * @param index
	 *            <code>true</code> if the neighbor is indexed
	 */
	public void setIndex(boolean index) {
		if (index) {
			setIndex("true");
		} else {
			setIndex("false");
		}
	}

	/**
	 * Checks if the neighbor is indexed. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the neighbor is indexed
	 */
	public boolean isIndex() {
		boolean result = false;
		if (getConceptConfig().isIndex()) {
			if (index != null) {
				if (index.trim().equalsIgnoreCase("true")) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Sets the neighbor (direction) add rule: NONE, RESTRICT, DETACH.
	 * 
	 * @param addRule
	 *            add rule
	 */
	public void setAddRule(String addRule) {
		this.addRule = addRule;
	}

	/**
	 * Gets the neighbor (direction) add rule: NONE, RESTRICT, DETACH. Default
	 * is NONE.
	 * 
	 * @return add rule
	 */
	public String getAddRule() {
		if (addRule == null) {
			if (!isExtension()) {
				return "NONE";
			}
		}
		return addRule;
	}

	/**
	 * Sets the neighbor (direction) remove rule: NONE, RESTRICT, CASCADE.
	 * 
	 * @param removeRule
	 *            remove rule
	 */
	public void setRemoveRule(String removeRule) {
		this.removeRule = removeRule;
	}

	/**
	 * Gets the neighbor (direction) remove rule: NONE, RESTRICT, CASCADE.
	 * Default is NONE.
	 * 
	 * @return remove rule
	 */
	public String getRemoveRule() {
		if (removeRule == null) {
			if (!isExtension()) {
				return "NONE";
			}
		}
		return removeRule;
	}

	/**
	 * Sets the neighbor (direction) add rule: NONE, RESTRICT, DETACH.
	 * 
	 * @param updateRule
	 *            update rule
	 */
	public void setUpdateRule(String updateRule) {
		this.updateRule = updateRule;
	}

	/**
	 * Gets the neighbor (direction) add rule: NONE, RESTRICT, DETACH. Default
	 * is NONE.
	 * 
	 * @return update rule
	 */
	public String getUpdateRule() {
		if (updateRule == null) {
			if (!isExtension()) {
				return "NONE";
			}
		}
		return updateRule;
	}

	/**
	 * Sets the display neighbor flag: true string if the neighbor will be
	 * displayed.
	 * 
	 * @param display
	 *            true string if the the neighbor will be displayed
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * Gets the display neighbor flag: true string if the neighbor will be
	 * displayed.
	 * 
	 * @return true string if the the neighbor will be displayed
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * Sets the display neighbor flag: <code>true</code> if the neighbor will be
	 * displayed.
	 * 
	 * @param display
	 *            <code>true</code> if the the neighbor will be displayed
	 */
	public void setDisplay(boolean display) {
		if (display) {
			setDisplay("true");
		} else {
			setDisplay("false");
		}
	}

	/**
	 * Checks if the neighbor will be displayed. Returns <code>true</code> if
	 * there is no value.
	 * 
	 * @return <code>true</code> if the neighbor will be displayed
	 */
	public boolean isDisplay() {
		boolean result = true;
		if (display != null) {
			if (display.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the update neighbor flag: true string if the neighbor is allowed to
	 * be updated.
	 * 
	 * @param update
	 *            true string if the the neighbor is allowed to be updated
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * Gets the update neighbor flag: true string if the neighbor is allowed to
	 * be updated.
	 * 
	 * @return true string if the the neighbor is allowed to be updated
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * Sets the update neighbor flag: <code>true</code> if the neighbor is
	 * allowed to be updated.
	 * 
	 * @param update
	 *            <code>true</code> if the neighbor is allowed to be updated
	 */
	public void setUpdate(boolean update) {
		if (update) {
			setUpdate("true");
		} else {
			setUpdate("false");
		}
	}

	/**
	 * Checks if the neighbor is allowed to be updated. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the neighbor is allowed to be updated
	 */
	public boolean isUpdate() {
		boolean result = true;
		if (update != null) {
			if (update.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the absorb neighbor flag: true string if the parent neighbor will be
	 * absorbed in a display.
	 * 
	 * @param absorb
	 *            true string if the the neighbor will be absorbed
	 */
	public void setAbsorb(String absorb) {
		this.absorb = absorb;
	}

	/**
	 * Gets the absorb neighbor flag: true string if the parent neighbor will be
	 * absorbed in a display.
	 * 
	 * @return true string if the the neighbor will be absorbed
	 */
	public String getAbsorb() {
		return absorb;
	}

	/**
	 * Sets the absorb neighbor flag: <code>true</code> if the parent neighbor
	 * will be absorbed in a display.
	 * 
	 * @param absorb
	 *            <code>true</code> if the the neighbor will be absorbed
	 */
	public void setAbsorb(boolean absorb) {
		if (absorb) {
			setAbsorb("true");
		} else {
			setAbsorb("false");
		}
	}

	/**
	 * Checks if the neighbor will be absorbed in a display. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the neighbor will be absorbed in a display
	 */
	public boolean isAbsorb() {
		boolean result = true;
		if (isChild()) {
			result = false;
		} else if (absorb != null) {
			if (absorb.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Updates the neighbor configuration (not allowed).
	 * 
	 * @param neighborConfig
	 *            neighbor configuration entity
	 * @return <code>true</code> if the neighbor configuration is updated with a
	 *         given entity
	 */
	public boolean update(NeighborConfig neighborConfig) {
		return false;
	}

	/**
	 * Gets the XML persistent neighbor.
	 * 
	 * @return XML persistent neighbor
	 */
	public XmlNeighborConfig getXmlNeighbor() {
		return xmlNeighbor;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlNeighbor.getPersistentModel();
	}

	/**
	 * Gets the neighbor entity.
	 * 
	 * @return neighbor entity
	 */
	public IEntity<?> getEntity() {
		return xmlNeighbor.getEntity();
	}

	/**
	 * Gets the list of external parent concept configurations from the internal
	 * part of many-to-many child configuration.
	 * 
	 * @return list of external parent concept configurations from the internal
	 *         part of many-to-many child configuration
	 */
	public List<ConceptConfig> getExternalManyToManyParentConceptConfigList() {
		List<ConceptConfig> parentConceptConfigList = new ArrayList<ConceptConfig>();
		ConceptConfig destinationConceptConfig = getDestinationConceptConfig();
		if (destinationConceptConfig.isManyToMany()) {
			if (isInternal() && isChild() && isPartOfManyToMany()) {
				List<NeighborConfig> externalParentConfigList = destinationConceptConfig
						.getNeighborsConfig()
						.getExternalManyToManyParentConfigList();
				for (NeighborConfig externalParentConfig : externalParentConfigList) {
					ConceptConfig externalParentConceptConfig = externalParentConfig
							.getDestinationConceptConfig();
					parentConceptConfigList.add(externalParentConceptConfig);
				}
			}
		}
		return parentConceptConfigList;
	}

	/**
	 * Extends the neighbor configuration.
	 */
	void extend() {
		if (!isExtended()) {
			if (isExtension()) {
				extendWithNeighbor();
			}
		}
	}

	/**
	 * Extends the neighbor configuration with neighbor.
	 */
	private void extendWithNeighbor() {
		String extensionDomain = getConceptConfig().getExtensionDomain();
		String extensionDomainType = getConceptConfig()
				.getExtensionDomainType();
		String extensionModel = getConceptConfig().getExtensionModel();
		String extensionConcept = getConceptConfig().getExtensionConcept();
		String extensionNeighbor = getExtensionNeighbor();

		DomainConfig extensionDomainConfig = getConceptConfig()
				.getModelConfig().getDomainConfig().getConfig()
				.getDomainConfig(extensionDomain, extensionDomainType);
		if (extensionDomainConfig != null) {
			ModelConfig extensionModelConfig = extensionDomainConfig
					.getModelConfig(extensionModel);
			if (extensionModelConfig != null) {
				ConceptConfig extensionConceptConfig = extensionModelConfig
						.getConceptConfig(extensionConcept);
				if (extensionConceptConfig != null) {
					NeighborConfig extensionNeighborConfig = extensionConceptConfig
							.getNeighborConfig(extensionNeighbor);
					if (extensionNeighborConfig != null) {
						extendWithNeighbor(extensionNeighborConfig);
					} else {
						log.error(extensionNeighbor
								+ " is not the correct extension neighbor.");
					}
				} else {
					log.error(extensionConcept
							+ " is not the correct extension concept.");
				}
			} else {
				log.error(extensionModel
						+ " is not the correct extension model.");
			}
		} else {
			log
					.error(extensionDomain
							+ " is not the correct extension domain.");
		}
	}

	/**
	 * Extends null fields with the neighbor configuration.
	 * 
	 * @param extensionNeighborConfig
	 *            extension neighbor configuration
	 */
	private void extendWithNeighbor(NeighborConfig extensionNeighborConfig) {
		extendNeighborConfigNullFields(extensionNeighborConfig);

		setExtended(true);
	}

	/**
	 * Extends the neighbor configuration null fields with fields from a given
	 * extension neighbor configuration.
	 * 
	 * @param extensionNeighborConfig
	 *            extension neighbor configuration
	 */
	private void extendNeighborConfigNullFields(
			NeighborConfig extensionNeighborConfig) {
		if (getDestinationConcept() == null) {
			setDestinationConcept(extensionNeighborConfig
					.getDestinationConcept());
		}
		if (getInverseNeighbor() == null) {
			setInverseNeighbor(extensionNeighborConfig.getInverseNeighbor());
		}
		if (getInternal() == null) {
			setInternal(extensionNeighborConfig.getInternal());
		}
		if (getPartOfManyToMany() == null) {
			setPartOfManyToMany(extensionNeighborConfig.getPartOfManyToMany());
		}
		if (getType() == null) {
			setType(extensionNeighborConfig.getType());
		}
		if (getMin() == null) {
			setMin(extensionNeighborConfig.getMin());
		}
		if (getMax() == null) {
			setMax(extensionNeighborConfig.getMax());
		}
		if (getUnique() == null) {
			setUnique(extensionNeighborConfig.getUnique());
		}
		if (getIndex() == null) {
			setIndex(extensionNeighborConfig.getIndex());
		}
		if (getAddRule() == null) {
			setAddRule(extensionNeighborConfig.getAddRule());
		}
		if (getRemoveRule() == null) {
			setRemoveRule(extensionNeighborConfig.getRemoveRule());
		}
		if (getUpdateRule() == null) {
			setUpdateRule(extensionNeighborConfig.getUpdateRule());
		}
		if (getDisplay() == null) {
			setDisplay(extensionNeighborConfig.getDisplay());
		}
		if (getUpdate() == null) {
			setUpdate(extensionNeighborConfig.getUpdate());
		}
		if (getAbsorb() == null) {
			setAbsorb(extensionNeighborConfig.getAbsorb());
		}
	}

	/**
	 * Copies a given extension neighbor configuration to the current neighbor
	 * configuration (only properties, without parent).
	 * 
	 * @param extensionNeighborConfig
	 *            extension neighbor configuration
	 */
	public NeighborConfig copyPropertiesFrom(
			NeighborConfig extensionNeighborConfig) {
		this.setCode(extensionNeighborConfig.getCode());
		this.setExtension(extensionNeighborConfig.getExtension());
		this.setExtensionNeighbor(extensionNeighborConfig
				.getExtensionNeighbor());
		this.setDestinationConcept(extensionNeighborConfig
				.getDestinationConcept());
		this.setInverseNeighbor(extensionNeighborConfig.getInverseNeighbor());
		this.setInternal(extensionNeighborConfig.getInternal());
		this.setPartOfManyToMany(extensionNeighborConfig.getPartOfManyToMany());
		this.setType(extensionNeighborConfig.getType());
		this.setMin(extensionNeighborConfig.getMin());
		this.setMax(extensionNeighborConfig.getMax());
		this.setUnique(extensionNeighborConfig.getUnique());
		this.setIndex(extensionNeighborConfig.getIndex());
		this.setAddRule(extensionNeighborConfig.getAddRule());
		this.setRemoveRule(extensionNeighborConfig.getRemoveRule());
		this.setUpdateRule(extensionNeighborConfig.getUpdateRule());
		this.setDisplay(extensionNeighborConfig.getDisplay());
		this.setUpdate(extensionNeighborConfig.getUpdate());
		this.setAbsorb(extensionNeighborConfig.getAbsorb());
		return this;
	}

	/**
	 * Outputs neighbor configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("---------------- " + title + " ----------------");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(Extension = " + getExtension() + ")");
		log.info("(Extension Neighbor = " + getExtensionNeighbor() + ")");
		log.info("(Destination Concept = " + getDestinationConcept() + ")");
		log.info("(Inverse Neighbor = " + getInverseNeighbor() + ")");
		log.info("(Internal = " + getInternal() + ")");
		log.info("(Part of many-to-many = " + getPartOfManyToMany() + ")");
		log.info("(Type = " + getType() + ")");
		log.info("(Min = " + getMin() + ")");
		log.info("(Max = " + getMax() + ")");
		log.info("(Unique = " + getUnique() + ")");
		log.info("(Index = " + getIndex() + ")");
		log.info("(Add Rule = " + getAddRule() + ")");
		log.info("(Remove Rule = " + getRemoveRule() + ")");
		log.info("(Update Rule = " + getUpdateRule() + ")");
		log.info("(Display = " + getDisplay() + ")");
		log.info("(Update = " + getUpdate() + ")");
		log.info("(Absorb = " + getAbsorb() + ")");
	}

	/**
	 * Sets the neighbor extended flag.
	 * 
	 * @param extended
	 *            <code>true</code> if the neighbor has been extended
	 */
	private void setExtended(boolean extended) {
		this.extended = extended;
	}

	/**
	 * Checks if the neighbor is extended. Returns <code>false</code> if the
	 * neighbor has not been extended
	 * 
	 * @return <code>true</code> if the neighbor has been extended
	 */
	private boolean isExtended() {
		return extended;
	}

}