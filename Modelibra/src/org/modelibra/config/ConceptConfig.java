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
 * Model concept configuration consists of properties for the concept
 * configuration used in Modelibra, and concept properties for the model default
 * application used in dmWicket.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-28
 */
public class ConceptConfig extends Entity<ConceptConfig> implements
		IPersistentEntity {

	private static final long serialVersionUID = 2010L;

	private static Log log = LogFactory.getLog(ConceptConfig.class);

	private ModelConfig modelConfig; // parent

	/*
	 * The concept configuration properties.
	 */

	private String abstraction; // Boolean

	private String extension; // Boolean

	private String extensionDomain;

	private String extensionDomainType;

	private String extensionModel;

	private String extensionConcept;

	private String extensionWithNeighbors; // Boolean

	private String entitiesCode;

	private String packageCode;

	private String min; // Integer

	private String max;

	private String entry; // Boolean

	private String fileName;

	private String index; // Boolean

	/*
	 * End of the model configuration properties.
	 */

	/*
	 * The model default application concept configuration properties.
	 */

	private String display; // Boolean

	private String displayType;

	private String add; // Boolean

	private String remove; // Boolean

	private String update; // Boolean

	/*
	 * End of the model default application concept configuration properties.
	 */

	private PropertiesConfig propertiesConfig; // children

	private NeighborsConfig neighborsConfig; // children

	private CombinationConfig uniqueConfig;

	private CombinationConfig indexConfig;

	private XmlConceptConfig xmlConcept;

	private TextHandler textHandler = new TextHandler();

	private boolean extended = false;

	/**
	 * Constructs a concept configuration.
	 */
	public ConceptConfig() {
		super();
		xmlConcept = new XmlConceptConfig(this);
		propertiesConfig = new PropertiesConfig();
		propertiesConfig.setConceptConfig(this);
		neighborsConfig = new NeighborsConfig();
		neighborsConfig.setConceptConfig(this);
	}

	/**
	 * Sets the model configuration.
	 * 
	 * @param modelConfig
	 *            model configuration
	 */
	public void setModelConfig(ModelConfig modelConfig) {
		this.modelConfig = modelConfig;
	}

	/**
	 * Gets the model configuration.
	 * 
	 * @return model configuration
	 */
	public ModelConfig getModelConfig() {
		return modelConfig;
	}

	/**
	 * Gets the concept code that starts with a capital letter.
	 * 
	 * @return concept code that starts with a capital letter
	 */
	public String getCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getCode());
	}

	/**
	 * Gets the concept code that starts with a lower letter.
	 * 
	 * @return concept code that starts with a lower letter
	 */
	public String getCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getCode());
	}

	/**
	 * Gets the concept code in lower letters.
	 * 
	 * @return concept code in lower letters
	 */
	public String getCodeInLowerLetters() {
		return textHandler.allLettersToLower(getCode());
	}

	/**
	 * Gets the entities code that starts with a capital letter.
	 * 
	 * @return entities code that starts with a capital letter
	 */
	public String getEntitiesCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getEntitiesCode());
	}

	/**
	 * Gets the entities code that starts with a lower letter.
	 * 
	 * @return entities code that starts with a lower letter
	 */
	public String getEntitiesCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getEntitiesCode());
	}

	/**
	 * Gets the entities code in lower letters.
	 * 
	 * @return entities code in lower letters
	 */
	public String getEntitiesCodeInLowerLetters() {
		return textHandler.allLettersToLower(getEntitiesCode());
	}

	/**
	 * Gets the concept name.
	 * 
	 * @return concept name
	 */
	public String getConceptName() {
		return getCodeWithFirstLetterAsUpper();
	}

	/**
	 * Gets the concepts name.
	 * 
	 * @return concepts name
	 */
	public String getConceptsName() {
		return getEntitiesCodeWithFirstLetterAsUpper();
	}

	/**
	 * Sets the concept abstraction flag: true string if it is an abstraction.
	 * 
	 * @param abstraction
	 *            true string if it is an abstraction
	 */
	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	/**
	 * Gets the concept abstraction flag: true string if it is an abstraction.
	 * 
	 * @return true string if it is an abstraction
	 */
	public String getAbstraction() {
		return abstraction;
	}

	/**
	 * Sets the concept abstraction flag: <code>true</code> if it is an
	 * abstraction.
	 * 
	 * @param abstraction
	 *            <code>true</code> if it is an abstraction
	 */
	public void setAbstraction(boolean abstraction) {
		if (abstraction) {
			setAbstraction("true");
		} else {
			setAbstraction("false");
		}
	}

	/**
	 * Checks if it is an abstraction. Returns <code>false</code> if there is no
	 * value.
	 * 
	 * @return <code>true</code> if it is an abstraction
	 */
	public boolean isAbstraction() {
		boolean result = false;
		if (abstraction != null) {
			if (abstraction.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the concept extension flag: true string if there is an extension.
	 * 
	 * @param extension
	 *            true string if there is an extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets the concept extension flag: true string if there is an extension.
	 * 
	 * @return true string if there is an extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the concept extension flag: <code>true</code> if there is an
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
	 * Checks if the concept is extensible. Returns <code>false</code> if there
	 * is nothing to extend.
	 * 
	 * @return <code>true</code> if the concept is extensible
	 */
	public boolean isExtensible() {
		return isExtension() || anyAttributeExtension();
	}

	/**
	 * Checks if there is any property or neighbor (attribute) extension.
	 * Returns <code>false</code> if there is none.
	 * 
	 * @return <code>true</code> if there is at least one property or neighbor
	 *         extension
	 */
	public boolean anyAttributeExtension() {
		return getPropertiesConfig().anyPropertyExtension()
				|| getNeighborsConfig().anyNeighborExtension();
	}

	/**
	 * Sets the extension domain.
	 * 
	 * @param extensionDomain
	 *            extension domain
	 */
	public void setExtensionDomain(String extensionDomain) {
		this.extensionDomain = extensionDomain;
	}

	/**
	 * Gets the extension domain.
	 * 
	 * @return extension domain
	 */
	public String getExtensionDomain() {
		if (isExtension() && extensionDomain == null) {
			String extensionConcept = getExtensionConcept();
			ModelConfig modelConfig = getModelConfig();
			if (!getCode().equals(extensionConcept)) {
				setExtensionDomain(modelConfig.getDomainConfig().getCode());
			} else if (modelConfig.isExtension()) {
				String modelExtensionDomain = modelConfig.getExtensionDomain();
				if (modelExtensionDomain != null) {
					setExtensionDomain(modelExtensionDomain);
				} else {
					log.error(getCode()
							+ " concept does not have the extension domain.");
				}
			} else {
				log.error(getCode()
						+ " concept does not have the extension domain.");
			}
		}
		return extensionDomain;
	}

	/**
	 * Sets the extension domain type.
	 * 
	 * @param extensionDomainType
	 *            extension domain type
	 */
	public void setExtensionDomainType(String extensionDomainType) {
		this.extensionDomainType = extensionDomainType;
	}

	/**
	 * Gets the extension domain type.
	 * 
	 * @return extension domain type
	 */
	public String getExtensionDomainType() {
		if (isExtension() && extensionDomainType == null) {
			String extensionConcept = getExtensionConcept();
			ModelConfig modelConfig = getModelConfig();
			if (!getCode().equals(extensionConcept)) {
				setExtensionDomainType(modelConfig.getDomainConfig().getType());
			} else if (modelConfig.isExtension()) {
				String modelExtensionDomainType = modelConfig
						.getExtensionDomainType();
				if (modelExtensionDomainType != null) {
					setExtensionDomainType(modelExtensionDomainType);
				} else {
					log
							.error(getCode()
									+ " concept does not have the extension domain type.");
				}
			} else {
				log.error(getCode()
						+ " concept does not have the extension domain type.");
			}
		}
		return extensionDomainType;
	}

	/**
	 * Sets the extension model.
	 * 
	 * @param extensionModel
	 *            extension model
	 */
	public void setExtensionModel(String extensionModel) {
		this.extensionModel = extensionModel;
	}

	/**
	 * Gets the extension model.
	 * 
	 * @return extension model
	 */
	public String getExtensionModel() {
		if (isExtension() && extensionModel == null) {
			String extensionConcept = getExtensionConcept();
			ModelConfig modelConfig = getModelConfig();
			if (!getCode().equals(extensionConcept)) {
				setExtensionModel(modelConfig.getCode());
			} else if (modelConfig.isExtension()) {
				String modelExtensionModel = modelConfig.getExtensionModel();
				if (modelExtensionModel != null) {
					setExtensionModel(modelExtensionModel);
				} else {
					log.error(getCode()
							+ " concept does not have the extension model.");
				}
			} else {
				log.error(getCode()
						+ " concept does not have the extension model.");
			}
		}
		return extensionModel;
	}

	/**
	 * Sets the extension concept.
	 * 
	 * @param extensionConcept
	 *            extension concept
	 */
	public void setExtensionConcept(String extensionConcept) {
		this.extensionConcept = extensionConcept;
	}

	/**
	 * Gets the extension concept.
	 * 
	 * @return extension concept
	 */
	public String getExtensionConcept() {
		if (isExtension() && extensionConcept == null) {
			log.error(getCode()
					+ " concept does not have the extension concept: ");
		}
		return extensionConcept;
	}

	/**
	 * Sets the concept extension with neighbors flag: true string if there is
	 * an extension with neighbors.
	 * 
	 * @param extensionWithNeighbors
	 *            true string if there is an extension with neighbors
	 */
	public void setExtensionWithNeighbors(String extensionWithNeighbors) {
		this.extensionWithNeighbors = extensionWithNeighbors;
	}

	/**
	 * Gets the concept extension with neighbors flag: true string if there is
	 * an extension with neighbors.
	 * 
	 * @return true string if there is an extension with neighbors
	 */
	public String getExtensionWithNeighbors() {
		return extensionWithNeighbors;
	}

	/**
	 * Sets the concept extension with neighbors flag: <code>true</code> if
	 * there is an extension with neighbors.
	 * 
	 * @param extensionWithNeighbors
	 *            <code>true</code> if there is an extension with neighbors
	 */
	public void setExtensionWithNeighbors(boolean extensionWithNeighbors) {
		if (extensionWithNeighbors) {
			setExtensionWithNeighbors("true");
		} else {
			setExtensionWithNeighbors("false");
		}
	}

	/**
	 * Checks if there is an extension with neighbors. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if there is an extension with neighbors
	 */
	public boolean isExtensionWithNeighbors() {
		boolean result = true;
		if (extensionWithNeighbors != null) {
			if (extensionWithNeighbors.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the concept entities code.
	 * 
	 * @param entitiesCode
	 *            concept entities code
	 */
	public void setEntitiesCode(String entitiesCode) {
		this.entitiesCode = entitiesCode;
	}

	/**
	 * Gets the concept entities code.
	 * 
	 * @return concept entities code
	 */
	public String getEntitiesCode() {
		if (entitiesCode == null) {
			if (!isExtension() && getCode() != null) {
				return getCode() + "s";
			}
		}
		return entitiesCode;
	}

	/**
	 * Sets the concept package code.
	 * 
	 * @param packageCode
	 *            concept package code
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	/**
	 * Gets the concept package code.
	 * 
	 * @return concept package code
	 */
	public String getPackageCode() {
		if (packageCode == null || packageCode.trim().equals("")) {
			return getConceptPackageCode();
		}
		return packageCode;
	}

	/**
	 * Gets (derives) the concept package.
	 * 
	 * @return concept package
	 */
	private String getConceptPackageCode() {
		String conceptPackageCode = null;
		String modelPackageCode = getModelConfig().getPackageCode();
		conceptPackageCode = modelPackageCode + "." + getCodeInLowerLetters();
		return conceptPackageCode;
	}

	/**
	 * Gets (derives) the concept entity class (class full name).
	 * 
	 * @return concept entity class
	 */
	public String getEntityClass() {
		return getPackageCode() + "." + getConceptName();
	}

	/**
	 * Gets (derives) the concept entities class (class full name).
	 * 
	 * @return concept entities class
	 */
	public String getEntitiesClass() {
		return getPackageCode() + "." + getConceptsName();
	}

	/**
	 * Sets the concept minimal cardinality (required minimal number of
	 * entities).
	 * 
	 * @param min
	 *            minimal cardinality
	 */
	public void setMin(String min) {
		this.min = min;
	}

	/**
	 * Gets the concept minimal cardinality (required minimal number of
	 * entities). Default is 0.
	 * 
	 * @return minimal cardinality
	 */
	public String getMin() {
		if (min == null) {
			if (!isExtension()) {
				return "0";
			}
		}
		return min;
	}

	/**
	 * Gets the concept minimal cardinality (as an int number).
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
	 * Sets the concept maximal cardinality (maximal number of entities).
	 * 
	 * @param max
	 *            maximal cardinality
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets the concept maximal cardinality (maximal number of entities).
	 * Default is N.
	 * 
	 * @return maximal cardinality
	 */
	public String getMax() {
		if (max == null) {
			if (!isExtension()) {
				return "N";
			}
		}
		return max;
	}

	/**
	 * Sets the entry concept flag: true string if the concept is entry.
	 * 
	 * @param entry
	 *            true string if the concept is entry
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

	/**
	 * Gets the entry concept flag: true string if the concept is entry.
	 * 
	 * @return true string if the concept is entry
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * Gets the entry concept flag: <code>true</code> if the concept is entry.
	 * 
	 * @return <code>true</code> if the concept is entry
	 */
	public void setEntry(boolean entry) {
		if (entry) {
			setEntry("true");
		} else {
			setEntry("false");
		}
	}

	/**
	 * Checks if the concept is entry. Returns <code>false</code> if there is no
	 * value.
	 * 
	 * @return <code>true</code> if the concept is entry
	 */
	public boolean isEntry() {
		boolean result = false;
		if (entry != null) {
			if (entry.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the concept data file name.
	 * 
	 * @param fileName
	 *            concept data file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the concept data file name. For the persistent entry concept,
	 * default is conceptcode.xml.
	 * 
	 * @return concept data file name
	 */
	public String getFileName() {
		if (fileName == null) {
			if (getModelConfig().isPersistent() && isEntry()) {
				return getCodeInLowerLetters() + ".xml";
			}
		}
		return fileName;
	}

	/**
	 * Sets the index flag: true string if the concept has index.
	 * 
	 * @param index
	 *            true string if the concept has index
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets the index flag: true string if the concept has index.
	 * 
	 * @return true string if the concept has index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index flag: <code>true</code> if the concept has index.
	 * 
	 * @param index
	 *            <code>true</code> if the concept has index
	 */
	public void setIndex(boolean index) {
		if (index) {
			setIndex("true");
		} else {
			setIndex("false");
		}
	}

	/**
	 * Checks if the concept has index. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the concept has index
	 */
	public boolean isIndex() {
		boolean result = false;
		if (index != null) {
			if (index.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Derives if the concept is reflexive. Returns <code>true</code> if there
	 * is at least one reflexive neighbor.
	 * 
	 * @return <code>true</code> if there is at least one reflexive neighbor.
	 */
	public boolean isReflexive() {
		boolean result = false;
		for (NeighborConfig neighborConfig : getNeighborsConfig()) {
			if (neighborConfig.getDestinationConcept().equals(getCode())) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Gets a reflexive parent neighbor.
	 * 
	 * @return reflexive parent neighbor.
	 */
	public NeighborConfig getReflexiveParent() {
		for (NeighborConfig neighborConfig : getNeighborsConfig()) {
			if (neighborConfig.getDestinationConcept().equals(getCode())
					&& neighborConfig.isParent()) {
				return neighborConfig;
			}
		}
		return null;
	}

	/**
	 * Gets a reflexive child neighbor.
	 * 
	 * @return reflexive child neighbor.
	 */
	public NeighborConfig getReflexiveChild() {
		for (NeighborConfig neighborConfig : getNeighborsConfig()) {
			if (neighborConfig.getDestinationConcept().equals(getCode())
					&& neighborConfig.isChild()) {
				return neighborConfig;
			}
		}
		return null;
	}

	/**
	 * Derives if the concept is intersection. Returns <code>true</code> if
	 * there is more than one parent neighbor.
	 * 
	 * @return <code>true</code> if there is more than one parent neighbor.
	 */
	public boolean isIntersection() {
		boolean result = false;
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		if (neighborsConfig.size() > 1) {
			int count = 0;
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isParent()) {
					count++;
				}
			}
			if (count > 1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Derives if the concept is many-to-many. Returns <code>true</code> if
	 * there is more than one part of many-to-many parent neighbor.
	 * 
	 * @return <code>true</code> if there is more than one parent neighbor.
	 */
	public boolean isManyToMany() {
		boolean result = false;
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		if (neighborsConfig.size() > 1) {
			int count = 0;
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isParent()
						&& neighborConfig.isPartOfManyToMany()) {
					count++;
				}
			}
			if (count > 1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the display concept flag: true string if the concept will be
	 * displayed.
	 * 
	 * @param display
	 *            true string if the concept will be displayed
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * Gets the display concept flag: true string if the concept will be
	 * displayed.
	 * 
	 * @return true string if the concept will be displayed
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * Sets the display concept flag: <code>true</code> if the concept will be
	 * displayed.
	 * 
	 * @param display
	 *            <code>true</code> if the concept will be displayed
	 */
	public void setDisplay(boolean display) {
		if (display) {
			setDisplay("true");
		} else {
			setDisplay("false");
		}
	}

	/**
	 * Checks if the concept will be displayed. Returns <code>true</code> if
	 * there is no value.
	 * 
	 * @return <code>true</code> if the concept will be displayed
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
	 * Sets the display concept type: table, list or slide.
	 * 
	 * @param displayType
	 *            display type
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	/**
	 * Gets the display concept type: table, list or slide. If display, default
	 * is table.
	 * 
	 * @return display type
	 */
	public String getDisplayType() {
		if (displayType == null) {
			if (!isExtension() && isDisplay()) {
				return "table";
			}
		}
		return displayType;
	}

	/**
	 * Sets the add concept flag: true string if the concept entity is allowed
	 * to be added.
	 * 
	 * @param add
	 *            true string if the concept entity is allowed to be added
	 */
	public void setAdd(String add) {
		this.add = add;
	}

	/**
	 * Gets the add concept flag: true string if the concept entity is allowed
	 * to be added.
	 * 
	 * @return true string if the concept entity is allowed to be added
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * Gets the add concept flag: <code>true</code> if the concept entity is
	 * allowed to be added.
	 * 
	 * @return <code>true</code> if the concept entity is allowed to be added
	 */
	public void setAdd(boolean add) {
		if (add) {
			setAdd("true");
		} else {
			setAdd("false");
		}
	}

	/**
	 * Checks if the concept entity is allowed to be added. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the concept entity is allowed to be added
	 */
	public boolean isAdd() {
		boolean result = true;
		if (add != null) {
			if (add.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the remove concept flag: true string if the concept entity is
	 * allowed to be removed.
	 * 
	 * @param remove
	 *            true string if the concept entity is allowed to be removed
	 */
	public void setRemove(String remove) {
		this.remove = remove;
	}

	/**
	 * Gets the remove concept flag: true string if the concept entity is
	 * allowed to be removed.
	 * 
	 * @return true string if the concept entity is allowed to be removed
	 */
	public String getRemove() {
		return remove;
	}

	/**
	 * Sets the remove concept flag: <code>true</code> if the concept entity is
	 * allowed to be removed.
	 * 
	 * @param remove
	 *            <code>true</code> if the concept entity is allowed to be
	 *            removed
	 */
	public void setRemove(boolean remove) {
		if (remove) {
			setRemove("true");
		} else {
			setRemove("false");
		}
	}

	/**
	 * Checks if the concept entity is allowed to be removed. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the concept entity is allowed to be removed
	 */
	public boolean isRemove() {
		boolean result = true;
		if (remove != null) {
			if (remove.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the update concept flag: true string if the concept entity is
	 * allowed to be updated.
	 * 
	 * @param update
	 *            true string if the concept entity is allowed to be updated
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * Gets the update concept flag: true string if the concept entity is
	 * allowed to be updated.
	 * 
	 * @return true string if the concept entity is allowed to be updated
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * Sets the update concept flag: <code>true</code> if the concept entity is
	 * allowed to be updated.
	 * 
	 * @param update
	 *            <code>true</code> if the concept entity is allowed to be
	 *            updated
	 */
	public void setUpdate(boolean update) {
		if (update) {
			setUpdate("true");
		} else {
			setUpdate("false");
		}
	}

	/**
	 * Checks if the concept entity is allowed to be updated. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the concept entity is allowed to be updated
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
	 * Sets properties configuration.
	 * 
	 * @param propertiesConfig
	 *            properties configuration
	 */
	public void setPropertiesConfig(PropertiesConfig propertiesConfig) {
		propertiesConfig.setConceptConfig(this);
		for (PropertyConfig propertyConfig : propertiesConfig) {
			propertyConfig.setConceptConfig(this);
		}
		this.propertiesConfig = propertiesConfig;
	}

	/**
	 * Gets properties configuration.
	 * 
	 * @return properties configuration
	 */
	public PropertiesConfig getPropertiesConfig() {
		return propertiesConfig;
	}

	/**
	 * Gets the property configuration for a given property code.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return property configuration
	 */
	public PropertyConfig getPropertyConfig(String propertyCode) {
		PropertyConfig propertyConfig = null;
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		if (propertiesConfig != null) {
			propertyConfig = propertiesConfig.getPropertyConfig(propertyCode);
		}
		return propertyConfig;
	}

	/**
	 * Sets neighbors configuration.
	 * 
	 * @param neighborsConfig
	 *            neighbors configuration
	 */
	public void setNeighborsConfig(NeighborsConfig neighborsConfig) {
		neighborsConfig.setConceptConfig(this);
		for (NeighborConfig neighborConfig : neighborsConfig) {
			neighborConfig.setConceptConfig(this);
		}
		this.neighborsConfig = neighborsConfig;
	}

	/**
	 * Gets neighbors configuration.
	 * 
	 * @return neighbors configuration
	 */
	public NeighborsConfig getNeighborsConfig() {
		return neighborsConfig;
	}

	/**
	 * Gets the neighbor configuration for a given neighbor code.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @return neighbor configuration
	 */
	public NeighborConfig getNeighborConfig(String neighborCode) {
		NeighborConfig neighborConfig = null;
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		if (neighborsConfig != null) {
			neighborConfig = neighborsConfig.getNeighborConfig(neighborCode);
		}
		return neighborConfig;
	}

	/**
	 * Updates the concept configuration (not allowed).
	 * 
	 * @param conceptConfig
	 *            concept configuration entity
	 * @return <code>true</code> if the concept configuration is updated with a
	 *         given entity
	 */
	public boolean update(ConceptConfig conceptConfig) {
		return false;
	}

	/**
	 * If null, derives the concept unique (id) configuration that consists of
	 * unique properties and/or unique parent neighbors.
	 * 
	 * @return concept id configuration
	 */
	public CombinationConfig getUniqueConfig() {
		if (uniqueConfig == null) {
			uniqueConfig = new CombinationConfig();

			PropertiesConfig propertiesConfig = getPropertiesConfig();
			for (PropertyConfig propertyConfig : propertiesConfig) {
				if (propertyConfig.isUnique()) {
					uniqueConfig.add(propertyConfig.getCode(), propertyConfig);
				}
			}

			NeighborsConfig neighborsConfig = getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isUnique()) {
					uniqueConfig.add(neighborConfig.getCode(), neighborConfig);
				}
			}
		}
		return uniqueConfig;
	}

	/**
	 * If null, derives the concept index configuration that consists of index
	 * properties and/or index parent neighbors.
	 * 
	 * @return concept index configuration
	 */
	public CombinationConfig getIndexConfig() {
		if (indexConfig == null) {
			indexConfig = new CombinationConfig();

			PropertiesConfig propertiesConfig = getPropertiesConfig();
			for (PropertyConfig propertyConfig : propertiesConfig) {
				if (propertyConfig.isIndex()) {
					indexConfig.add(propertyConfig.getCode(), propertyConfig);
				}
			}

			NeighborsConfig neighborsConfig = getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isIndex()) {
					indexConfig.add(neighborConfig.getCode(), neighborConfig);
				}
			}
		}
		return indexConfig;
	}

	/**
	 * Gets the XML persistent concept.
	 * 
	 * @return XML persistent concept
	 */
	public XmlConceptConfig getXmlConcept() {
		return xmlConcept;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlConcept.getPersistentModel();
	}

	/**
	 * Gets the concept entity.
	 * 
	 * @return concept entity
	 */
	public IEntity<?> getEntity() {
		return xmlConcept.getEntity();
	}

	/**
	 * Gets a list of essential (for table display) property codes.
	 * 
	 * @return list of essential (for table display) property codes
	 */
	public List<String> getEssentialPropertyCodes() {
		List<String> essentialPropertyCodes = new ArrayList<String>();
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (propertyConfig.isEssential()) {
				essentialPropertyCodes.add(propertyConfig.getCode());
			}
		}
		return essentialPropertyCodes;
	}

	/**
	 * Gets a list of essential (for table display) property names.
	 * 
	 * @return list of essential (for table display) property names
	 */
	public List<String> getEssentialPropertyNames() {
		List<String> essentialPropertyNames = new ArrayList<String>();
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (propertyConfig.isEssential()) {
				essentialPropertyNames.add(propertyConfig.getPropertyName());
			}
		}
		return essentialPropertyNames;
	}

	/**
	 * Gets a list of concept code dot essential property code.
	 * 
	 * @return list of concept code dot essential property code
	 */
	public List<String> getConceptCodeEssentialPropertyCodes() {
		List<String> conceptCodeEssentialPropertyCodes = new ArrayList<String>();
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		String conceptCodeEssentialPropertyCode;
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (propertyConfig.isEssential()) {
				conceptCodeEssentialPropertyCode = getCode() + "."
						+ propertyConfig.getCode();
				conceptCodeEssentialPropertyCodes
						.add(conceptCodeEssentialPropertyCode);
			}
		}
		return conceptCodeEssentialPropertyCodes;
	}

	/**
	 * Gets a list of concept name dot essential property name.
	 * 
	 * @return list of concept name dot essential property name
	 */
	public List<String> getConceptNameEssentialPropertyNames() {
		List<String> conceptNameEssentialPropertyNames = new ArrayList<String>();
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		String conceptNameEssentialPropertyName;
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (propertyConfig.isEssential()) {
				conceptNameEssentialPropertyName = getConceptName() + " "
						+ propertyConfig.getPropertyName();
				conceptNameEssentialPropertyNames
						.add(conceptNameEssentialPropertyName);
			}
		}
		return conceptNameEssentialPropertyNames;
	}

	/**
	 * Gets a list of parent code dot essential property code.
	 * 
	 * @return list of parent code dot essential property code
	 */
	public List<String> getParentCodeEssentialPropertyCodes() {
		List<String> parentEssentialPropertyCodes = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("parent")
					&& neighborConfig.getMax().equals("1")
					&& neighborConfig.isAbsorb()) {
				ConceptConfig parentConceptConfig = neighborConfig
						.getDestinationConceptConfig();
				if (parentConceptConfig != null) {
					/*
					 * String code = getCode(); if (code != null &&
					 * code.equals(parentConceptConfig.getCode())) { continue; }
					 */
					PropertiesConfig parentConceptPropertiesConfig = parentConceptConfig
							.getPropertiesConfig();
					for (PropertyConfig parentConceptPropertyConfig : parentConceptPropertiesConfig) {
						if (parentConceptPropertyConfig.isEssential()) {
							String parentConceptPropertyCode = parentConceptPropertyConfig
									.getCode();
							parentConceptPropertyCode = neighborConfig
									.getDestinationConcept()
									+ "." + parentConceptPropertyCode;
							parentEssentialPropertyCodes
									.add(parentConceptPropertyCode);
						} // if
					} // for
				}
			} // if
		} // for
		return parentEssentialPropertyCodes;
	}

	/**
	 * Gets a list of parent name dot essential property name.
	 * 
	 * @return list of parent name dot essential property name
	 */
	public List<String> getParentNameEssentialPropertyNames() {
		List<String> parentEssentialPropertyNames = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("parent")
					&& neighborConfig.getMax().equals("1")
					&& neighborConfig.isAbsorb()) {
				ConceptConfig parentConceptConfig = neighborConfig
						.getDestinationConceptConfig();
				if (parentConceptConfig != null) {
					/*
					 * String code = getCode(); if (code != null &&
					 * code.equals(parentConceptConfig.getCode())) { continue; }
					 */
					PropertiesConfig parentConceptPropertiesConfig = parentConceptConfig
							.getPropertiesConfig();
					for (PropertyConfig parentConceptPropertyConfig : parentConceptPropertiesConfig) {
						if (parentConceptPropertyConfig.isEssential()) {
							String parentConceptPropertyName = parentConceptPropertyConfig
									.getPropertyName();
							String destinationConceptCode = neighborConfig
									.getDestinationConcept();
							String parentName = getModelConfig()
									.getConceptConfig(destinationConceptCode)
									.getConceptName();
							String parentEssentialPropertyName = parentName
									+ " " + parentConceptPropertyName;
							parentEssentialPropertyNames
									.add(parentEssentialPropertyName);
						} // if
					} // for
				}
			} // if
		} // for
		return parentEssentialPropertyNames;
	}

	/**
	 * Gets a list of child neighbor codes.
	 * 
	 * @return list of child neighbor codes
	 */
	public List<String> getChildNeighborCodes() {
		List<String> neighborCodes = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")) {
				String neighborCode = neighborConfig.getCode();
				neighborCodes.add(neighborCode);
			}
		}
		return neighborCodes;
	}

	/**
	 * Gets a list of child neighbor names.
	 * 
	 * @return list of child neighbor names
	 */
	public List<String> getChildNeighborNames() {
		List<String> neighborNames = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")) {
				String neighborName = neighborConfig.getNeighborName();
				neighborNames.add(neighborName);
			}
		}
		return neighborNames;
	}

	/**
	 * Gets a list of internal child neighbor names.
	 * 
	 * @return list of internal child neighbor names
	 */
	public List<String> getInternalChildNeighborNames() {
		List<String> internalNeighborNames = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")
					&& neighborConfig.isInternal()) {
				String neighborName = neighborConfig.getNeighborName();
				internalNeighborNames.add(neighborName);
			}
		}
		return internalNeighborNames;
	}

	/**
	 * Gets a list of internal child neighbor codes.
	 * 
	 * @return list of internal child neighbor codes
	 */
	public List<String> getInternalChildNeighborCodes() {
		List<String> internalNeighborCodes = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")
					&& neighborConfig.isInternal()) {
				String neighborCode = neighborConfig.getCode();
				internalNeighborCodes.add(neighborCode);
			}
		}
		return internalNeighborCodes;
	}

	/**
	 * Gets the internal parent neighbor (can only be one, if the concept is not
	 * entry).
	 * 
	 * @return internal parent neighbor
	 */
	public NeighborConfig getInternalParentNeighbor() {
		NeighborConfig internalParentNeighbor = null;
		if (!isEntry()) {
			NeighborsConfig neighborsConfig = getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.getType().equals("parent")
						&& neighborConfig.isInternal()) {
					internalParentNeighbor = neighborConfig;
					break;
				}
			}
		}
		return internalParentNeighbor;
	}

	/**
	 * Gets internal parent concept configuration.
	 * 
	 * @return internal parent concept configuration
	 */
	public ConceptConfig getInternalParentNeighborConceptConfig() {
		return getInternalParentNeighbor().getDestinationConceptConfig();
	}

	/**
	 * Gets a list of external child neighbor codes.
	 * 
	 * @return list of external child neighbor codes
	 */
	public List<String> getExternalChildNeighborCodes() {
		List<String> extternalNeighborCodes = new ArrayList<String>();
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")
					&& !neighborConfig.isInternal()) {
				String neighborCode = neighborConfig.getCode();
				extternalNeighborCodes.add(neighborCode);
			}
		}
		return extternalNeighborCodes;
	}

	/**
	 * Checks if there is at least one external parent. Returns
	 * <code>false</code> if there are no external parents.
	 * 
	 * @return <code>true</code> if there is at least one external parent
	 */
	public boolean hasExternalParent() {
		boolean hasExternalParent = false;
		List<NeighborConfig> externalParentConfigList = getNeighborsConfig()
				.getExternalParentConfigList();
		if (externalParentConfigList.size() > 0) {
			hasExternalParent = true;
		}
		return hasExternalParent;
	}

	/**
	 * Checks if this is a non entry concept that has at least one external
	 * child.
	 * 
	 * @return <code>true</code> if this is a non entry concept that has at
	 *         least one external parent.
	 */
	public boolean isNonEntryExternalParent() {
		return !isEntry() && hasExternalChild();
	}

	/**
	 * Checks if there is at least one external child. Returns
	 * <code>false</code> if there are no external children.
	 * 
	 * @return <code>true</code> if there is at least one external child
	 */
	public boolean hasExternalChild() {
		boolean hasExternalChild = false;
		List<NeighborConfig> externalChildConfigList = getNeighborsConfig()
				.getExternalChildConfigList();
		if (externalChildConfigList.size() > 0) {
			hasExternalChild = true;
		}
		return hasExternalChild;
	}

	/**
	 * Checks if this is a non entry concept that has at least one external
	 * parent.
	 * 
	 * @return <code>true</code> if this is a non entry concept that has at
	 *         least one external child.
	 */
	public boolean isNonEntryExternalChild() {
		return !isEntry() && hasExternalParent();
	}

	public boolean requiresGetter() {
		return isNonEntryExternalChild() || isNonEntryExternalParent();
	}

	/**
	 * Gets the list of external many-to-many parent concept configurations.
	 * 
	 * @return list of external many-to-many parent concept configurations
	 */
	public List<ConceptConfig> getExternalManyToManyParentConceptConfigList() {
		List<ConceptConfig> parentConceptConfigList = new ArrayList<ConceptConfig>();
		if (isManyToMany()) {
			List<NeighborConfig> externalParentConfigList = getNeighborsConfig()
					.getExternalManyToManyParentConfigList();
			for (NeighborConfig externalParentConfig : externalParentConfigList) {
				ConceptConfig externalParentConceptConfig = externalParentConfig
						.getDestinationConceptConfig();
				parentConceptConfigList.add(externalParentConceptConfig);
			}
		}
		return parentConceptConfigList;
	}

	/**
	 * Extends the concept configuration.
	 */
	void extend() {
		if (isExtensible()) {
			if (!isExtended()) {
				if (isExtension()) {
					extendWithConcept();
				}
				extendProperties();
				extendNeighbors();
			}
		}
	}

	/**
	 * Extends property configurations of the concept.
	 */
	private void extendProperties() {
		PropertiesConfig propertiesConfig = getPropertiesConfig();
		if (propertiesConfig != null) {
			for (PropertyConfig propertyConfig : propertiesConfig) {
				propertyConfig.extend();
			}
		}
	}

	/**
	 * Extends neighbor configurations of the concept.
	 */
	private void extendNeighbors() {
		NeighborsConfig neighborsConfig = getNeighborsConfig();
		if (neighborsConfig != null) {
			for (NeighborConfig neighborConfig : neighborsConfig) {
				neighborConfig.extend();
			}
		}
	}

	/**
	 * Extends the concept configuration with concept.
	 */
	private void extendWithConcept() {
		String extensionDomain = getExtensionDomain();
		String extensionDomainType = getExtensionDomainType();
		String extensionModel = getExtensionModel();
		String extensionConcept = getExtensionConcept();
		String concept = getCode();
		if (concept.equals(extensionConcept)
				&& getModelConfig().getCode().equals(extensionModel)
				&& getModelConfig().getDomainConfig().getCode().equals(
						extensionDomain)
				&& getModelConfig().getDomainConfig().getType().equals(
						extensionDomainType)) {
			log.error(concept + " concept cannot extend itself.");
		} else {
			DomainConfig extensionDomainConfig = getModelConfig()
					.getDomainConfig().getConfig().getDomainConfig(
							extensionDomain, extensionDomainType);
			if (extensionDomainConfig != null) {
				ModelConfig extensionModelConfig = extensionDomainConfig
						.getModelConfig(extensionModel);
				if (extensionModelConfig != null) {
					ConceptConfig extensionConceptConfig = extensionModelConfig
							.getConceptConfig(extensionConcept);
					if (extensionConceptConfig != null) {
						extendWithConcept(extensionConceptConfig);
					} else {
						log.error(extensionConcept
								+ " is not the correct extension concept.");
					}
				} else {
					log.error(extensionModel
							+ " is not the correct extension model.");
				}
			} else {
				log.error(extensionDomain
						+ " is not the correct extension domain.");
			}
		}
	}

	/**
	 * Extends the concept configuration with inheritance parent concept
	 * properties that are neither overriden nor used as extension properties.
	 * If configured, extends the concept configuration with inheritance parent
	 * concept neighbors that are are neither overriden nor used as extension
	 * neighbors.
	 * 
	 * @param extensionConceptConfig
	 *            extension concept configuration
	 */
	private void extendWithConcept(ConceptConfig extensionConceptConfig) {
		// Extend the concept configuration fields that are null.
		extendConcepConfigNullFields(extensionConceptConfig);

		PropertiesConfig extensionPropertiesConfig = extensionConceptConfig
				.getPropertiesConfig();
		for (PropertyConfig extensionPropertyConfig : extensionPropertiesConfig) {
			PropertyConfig conceptPropertyConfig = getPropertyConfig(extensionPropertyConfig
					.getCode());
			if (conceptPropertyConfig == null) {
				// There is no property with that name in the concept
				// (not overriden by the same name).
				boolean extensionUsed = false;
				PropertiesConfig propertiesConfig = getPropertiesConfig();
				for (PropertyConfig propertyConfig : propertiesConfig) {
					String extensionProperty = propertyConfig
							.getExtensionProperty();
					if (propertyConfig.isExtension()) {
						if (extensionProperty.equals(extensionPropertyConfig
								.getCode())) {
							extensionUsed = true;
						}
					}
				}
				if (!extensionUsed) {
					PropertyConfig extendedPropertyConfig = copyPropertyConfig(extensionPropertyConfig);
					propertiesConfig.add(extendedPropertyConfig);
				}
			}
		}

		if (isExtensionWithNeighbors()) {
			NeighborsConfig extensionNeighborsConfig = extensionConceptConfig
					.getNeighborsConfig();
			for (NeighborConfig extensionNeighborConfig : extensionNeighborsConfig) {
				NeighborConfig conceptNeighborConfig = getNeighborConfig(extensionNeighborConfig
						.getCode());
				if (conceptNeighborConfig == null) {
					// There is no neighbor with that name in the concept
					// (not overriden by the same name).
					boolean extensionUsed = false;
					NeighborsConfig neighborsConfig = getNeighborsConfig();
					for (NeighborConfig neighborConfig : neighborsConfig) {
						String extensionNeighbor = neighborConfig
								.getExtensionNeighbor();
						if (neighborConfig.isExtension()) {
							if (extensionNeighbor
									.equals(extensionNeighborConfig.getCode())) {
								extensionUsed = true;
							}
						}
					}
					if (!extensionUsed) {
						NeighborConfig extendedNeighborConfig = copyNeighborConfig(extensionNeighborConfig);
						neighborsConfig.add(extendedNeighborConfig);
					}
				}
			}
		}
		setExtended(true);
	}

	/**
	 * Extends the concept configuration null fields with fields from a given
	 * extension concept configuration.
	 * 
	 * @param extensionConceptConfig
	 *            extension concept configuration
	 */
	private void extendConcepConfigNullFields(
			ConceptConfig extensionConceptConfig) {
		if (getAbstraction() == null) {
			setAbstraction(extensionConceptConfig.getAbstraction());
		}
		if (getEntitiesCode() == null) {
			setEntitiesCode(extensionConceptConfig.getEntitiesCode());
		}
		if (getPackageCode() == null) {
			setPackageCode(extensionConceptConfig.getPackageCode());
		}
		if (getMin() == null) {
			setMin(extensionConceptConfig.getMin());
		}
		if (getMax() == null) {
			setMax(extensionConceptConfig.getMax());
		}
		if (getEntry() == null) {
			setEntry(extensionConceptConfig.getEntry());
		}
		if (getFileName() == null) {
			setFileName(extensionConceptConfig.getFileName());
		}
		if (getIndex() == null) {
			setIndex(extensionConceptConfig.getIndex());
		}
		if (getDisplay() == null) {
			setDisplay(extensionConceptConfig.getDisplay());
		}
		if (getDisplayType() == null) {
			setDisplayType(extensionConceptConfig.getDisplayType());
		}
		if (getAdd() == null) {
			setAdd(extensionConceptConfig.getAdd());
		}
		if (getUpdate() == null) {
			setUpdate(extensionConceptConfig.getUpdate());
		}
		if (getRemove() == null) {
			setRemove(extensionConceptConfig.getRemove());
		}
	}

	/**
	 * Copies a property configuration.
	 * 
	 * @param extensionPropertyConfig
	 *            extension property configuration
	 */
	private PropertyConfig copyPropertyConfig(
			PropertyConfig extensionPropertyConfig) {
		PropertyConfig copiedPropertyConfig = new PropertyConfig();
		copiedPropertyConfig.setConceptConfig(this);
		copiedPropertyConfig.copyPropertiesFrom(extensionPropertyConfig);
		return copiedPropertyConfig;
	}

	/**
	 * Copies a neighbor configuration.
	 * 
	 * @param extensionNeighborConfig
	 *            extension neighbor configuration
	 */
	private NeighborConfig copyNeighborConfig(
			NeighborConfig extensionNeighborConfig) {
		NeighborConfig copiedNeighborConfig = new NeighborConfig();
		copiedNeighborConfig.setConceptConfig(this);
		copiedNeighborConfig.copyPropertiesFrom(extensionNeighborConfig);
		return copiedNeighborConfig;
	}

	/**
	 * Copies a given extension concept configuration to the current concept
	 * configuration (only properties, without parent and child neighbors).
	 * 
	 * @param extensionConceptConfig
	 *            extension concept configuration
	 */
	public ConceptConfig copyPropertiesFrom(ConceptConfig extensionConceptConfig) {
		this.setCode(extensionConceptConfig.getCode());
		this.setAbstraction(extensionConceptConfig.getAbstraction());
		this.setExtension(extensionConceptConfig.getExtension());
		this.setExtensionDomain(extensionConceptConfig.getExtensionDomain());
		this.setExtensionDomainType(extensionConceptConfig
				.getExtensionDomainType());
		this.setExtensionModel(extensionConceptConfig.getExtensionModel());
		this.setExtensionConcept(extensionConceptConfig.getExtensionConcept());
		this.setExtensionWithNeighbors(extensionConceptConfig
				.getExtensionWithNeighbors());
		this.setEntitiesCode(extensionConceptConfig.getEntitiesCode());
		this.setPackageCode(extensionConceptConfig.getPackageCode());
		this.setMin(extensionConceptConfig.getMin());
		this.setMax(extensionConceptConfig.getMax());
		this.setEntry(extensionConceptConfig.getEntry());
		// An entry concept that extends another entry concept should have its
		// own XML data file.
		// this.setFileName(extensionConceptConfig.getFileName());
		this.setIndex(extensionConceptConfig.getIndex());
		this.setDisplay(extensionConceptConfig.getDisplay());
		this.setDisplayType(extensionConceptConfig.getDisplayType());
		this.setAdd(extensionConceptConfig.getAdd());
		this.setRemove(extensionConceptConfig.getRemove());
		this.setUpdate(extensionConceptConfig.getUpdate());
		return this;
	}

	/**
	 * Outputs concept configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("================ " + title + " ================");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(Abstraction = " + getAbstraction() + ")");
		log.info("(Extension = " + getExtension() + ")");
		log.info("(Extension Domain = " + getExtensionDomain() + ")");
		log.info("(Extension Domain Type = " + getExtensionDomainType() + ")");
		log.info("(Extension Model = " + getExtensionModel() + ")");
		log.info("(Extension Concept = " + getExtensionConcept() + ")");
		log.info("(Extension With Neighbors = " + getExtensionWithNeighbors()
				+ ")");
		log.info("(Package Code = " + getPackageCode() + ")");
		log.info("(Entities Code = " + getEntitiesCode() + ")");
		log.info("(Entities Class = " + getEntitiesClass() + ")");
		log.info("(Entity Class = " + getEntityClass() + ")");
		log.info("(Min = " + getMin() + ")");
		log.info("(Max = " + getMax() + ")");
		log.info("(Entry = " + getEntry() + ")");
		log.info("(File Name = " + getFileName() + ")");
		log.info("(Index = " + getIndex() + ")");
		log.info("(Intersection = " + isIntersection() + ")");
		log.info("(Display = " + getDisplay() + ")");
		log.info("(Display Type = " + getDisplayType() + ")");
		log.info("(Add = " + getAdd() + ")");
		log.info("(Remove = " + getRemove() + ")");
		log.info("(Update = " + getUpdate() + ")");

		if (propertiesConfig != null) {
			propertiesConfig.output("Properties Config");
		}
		if (neighborsConfig != null) {
			neighborsConfig.output("Neighbors Config");
		}
	}

	/**
	 * Concepts must have the same concept code, the same model code and the
	 * same domain code to be equal.
	 */
	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		ConceptConfig conceptConfig = (ConceptConfig) object;
		String conceptCode = conceptConfig.getCode();
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		String modelCode = modelConfig.getCode();
		DomainConfig domainConfig = modelConfig.getDomainConfig();
		String domainCode = domainConfig.getCode();
		if (getCode().equals(conceptCode)
				&& getModelConfig().getCode().equals(modelCode)
				&& getModelConfig().getDomainConfig().getCode().equals(
						domainCode)) {
			equal = true;
		}
		return equal;
	}

	/**
	 * Sets the concept extended flag.
	 * 
	 * @param extended
	 *            <code>true</code> if the concept has been extended
	 */
	private void setExtended(boolean extended) {
		this.extended = extended;
	}

	/**
	 * Checks if the concept is extended. Returns <code>false</code> if the
	 * concept has not been extended
	 * 
	 * @return <code>true</code> if the concept has been extended
	 */
	private boolean isExtended() {
		return extended;
	}

}
