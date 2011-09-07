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
import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.util.Log4jConfigurator;

/**
 * Collection of concept property configurations.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-30
 */
public class PropertiesConfig extends Entities<PropertyConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 2090L;

	private static Log log = LogFactory.getLog(PropertiesConfig.class);

	private ConceptConfig conceptConfig; // parent

	private XmlPropertiesConfig xmlProperties;

	/**
	 * Constructs the concept properties configuration.
	 */
	public PropertiesConfig() {
		super();
		xmlProperties = new XmlPropertiesConfig(this);
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
	 * Gets the peoperty configuration for a given property code.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return property configuration
	 */
	public PropertyConfig getPropertyConfig(String propertyCode) {
		return (PropertyConfig) retrieveByCode(propertyCode);
	}

	/**
	 * Checks if the new property code is unique within the concept including
	 * neighbors.
	 * 
	 * @param newPropertyConfig
	 *            property configuration entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(PropertyConfig newPropertyConfig) {
		boolean validation = true;
		String newPropertyCode = newPropertyConfig.getCode();
		if (newPropertyCode != null) {
			for (PropertyConfig propertyConfig : this) {
				if (propertyConfig.getCode().equals(newPropertyCode)) {
					validation = false;
					log
							.info(newPropertyCode
									+ " property code must be unique within the concept.");
					break;
				}
			}
			for (NeighborConfig neighborConfig : conceptConfig
					.getNeighborsConfig()) {
				if (neighborConfig.getCode().equals(newPropertyCode)) {
					validation = false;
					log
							.info(newPropertyCode
									+ " property code must be unique within the concept including neighbors.");
					break;
				}
			}
		} else {
			validation = false;
			log.info("Property code is required.");
		}
		return validation;
	}

	/**
	 * Sets the parent concept configuration for a new property configuration.
	 * 
	 * @param propertyConfig
	 *            property configuration entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(PropertyConfig propertyConfig) {
		propertyConfig.setConceptConfig(conceptConfig);
		return true;
	}

	/**
	 * Outputs the properties configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		List<PropertyConfig> list = getList();
		log.info("*** " + title + " ***");
		for (PropertyConfig propertyConfig : list) {
			String classSimpleName = propertyConfig.getClass().getSimpleName();
			propertyConfig.output(classSimpleName);
		}
	}

	/**
	 * Gets XML properties.
	 * 
	 * @return XML properties
	 */
	public XmlPropertiesConfig getXmlProperties() {
		return xmlProperties;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlProperties.getPersistentModel();
	}

	/**
	 * Gets the property entities.
	 * 
	 * @return property entities
	 */
	public IEntities<?> getEntities() {
		return xmlProperties.getEntities();
	}

	/**
	 * Loads properties.
	 */
	public void load() {
		xmlProperties.load();
	}

	/**
	 * Saves properties.
	 */
	public void save() {
		xmlProperties.save();
	}

	/**
	 * Checks if there is any property extension. Returns <code>false</code> if
	 * there is none.
	 * 
	 * @return <code>true</code> if there is at least one property extension
	 */
	public boolean anyPropertyExtension() {
		boolean result = false;
		for (PropertyConfig propertyConfig : this) {
			if (propertyConfig.isExtension()) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Gets the first property configuration of the String type that has the
	 * maximal display length.
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getFirstMaxDisplayLengthStringPropertyConfig() {
		PropertyConfig maxTextSizePropertyConfig = null;
		int maxTextSize = 0;
		for (PropertyConfig propertyConfig : this) {
			if (propertyConfig.getPropertyClassObject() == String.class) {
				int currentSize = propertyConfig.getDisplayLengthInt();
				if (currentSize > maxTextSize) {
					maxTextSize = currentSize;
					maxTextSizePropertyConfig = propertyConfig;
				}
			}
		}
		return maxTextSizePropertyConfig;
	}

	/**
	 * Gets the first property configuration that is unique.
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getFirstUniquePropertyConfig() {
		PropertyConfig uniquePropertyConfig = null;
		for (PropertyConfig propertyConfig : this) {
			if (propertyConfig.isUnique()) {
				uniquePropertyConfig = propertyConfig;
				break;
			}
		}
		return uniquePropertyConfig;
	}

	/**
	 * Gets the first property configuration that has the Boolean class.
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getFirstBooleanPropertyConfig() {
		PropertyConfig firstBooleanPropertyConfig = null;
		for (PropertyConfig propertyConfig : this) {
			if (propertyConfig.getPropertyClass().equals("java.lang.Boolean")) {
				firstBooleanPropertyConfig = propertyConfig;
				break;
			}
		}
		return firstBooleanPropertyConfig;
	}

	/**
	 * Gets the first property configuration that is essential (for a table
	 * display).
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getFirstEssentialPropertyConfig() {
		PropertyConfig firstEssentialPropertyConfig = null;
		for (PropertyConfig propertyConfig : this) {
			if (propertyConfig.isEssential()) {
				firstEssentialPropertyConfig = propertyConfig;
				break;
			}
		}
		return firstEssentialPropertyConfig;
	}

	/**
	 * Gets the list of property configurations.
	 * 
	 * @return list of property configurations
	 */
	public List<PropertyConfig> getPropertyConfigList() {
		List<PropertyConfig> propertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			propertyConfigList.add(propertyConfig);
		}
		return propertyConfigList;
	}

	/**
	 * Gets the list of essential property configurations.
	 * 
	 * @return list of essential property configurations
	 */
	public List<PropertyConfig> getEssentialPropertyConfigList() {
		List<PropertyConfig> essentialPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isEssential()) {
				essentialPropertyConfigList.add(propertyConfig);
			}
		}
		return essentialPropertyConfigList;
	}

	/**
	 * Gets the list of not unique essential property configurations without
	 * emails and urls.
	 * 
	 * @return list of not unique essential property configurations without
	 *         emails and urls
	 */
	public List<PropertyConfig> getNotUniqueEssentialPropertyConfigListWithoutEmailAndUrl() {
		List<PropertyConfig> notUniqueEssentialPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (!propertyConfig.isUnique() && propertyConfig.isEssential()) {
				if (!propertyConfig.getPropertyClass().equals(
						"org.modelibra.type.Email")
						&& !propertyConfig.getPropertyClass().equals(
								"java.net.URL")) {
					String validationType = propertyConfig.getValidationType();
					if (validationType == null) {
						notUniqueEssentialPropertyConfigList
								.add(propertyConfig);
					} else if (!validationType
							.equals("org.modelibra.type.Email")
							&& !validationType.equals("java.net.URL")) {
						notUniqueEssentialPropertyConfigList
								.add(propertyConfig);
					}
				}
			}
		}
		return notUniqueEssentialPropertyConfigList;
	}

	/**
	 * Gets a list of unique property configurations.
	 * 
	 * @return List of unique property configurations.
	 */
	public List<PropertyConfig> getUniquePropertyConfigList() {
		List<PropertyConfig> result = new ArrayList<PropertyConfig>();

		for (PropertyConfig config : this.getEntityPropertyConfigList()) {
			if (config.isUnique())
				result.add(config);
		}

		return result;
	}

	/**
	 * Gets the list of entity property configurations (without the code,
	 * reference and derived property configurations).
	 * 
	 * @return list of entity property configurations
	 */
	public List<PropertyConfig> getEntityPropertyConfigList() {
		List<PropertyConfig> entityPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (!propertyConfig.getCode().equals("code")
					&& !propertyConfig.isReference()
					&& !propertyConfig.isDerived()) {
				entityPropertyConfigList.add(propertyConfig);
			}
		}
		return entityPropertyConfigList;
	}

	/**
	 * Gets the list of reference property configurations.
	 * 
	 * @return list of reference property configurations
	 */
	public List<PropertyConfig> getReferencePropertyConfigList() {
		List<PropertyConfig> referencePropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isReference()) {
				referencePropertyConfigList.add(propertyConfig);
			}
		}
		return referencePropertyConfigList;
	}
	
	/**
	 * Gets the list of property configurations without reference properties.
	 * 
	 * @return list of property configurations without reference properties
	 */
	public List<PropertyConfig> getPropertyConfigWithoutReferenceList() {
		List<PropertyConfig> propertyConfigWithoutReferenceList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (!propertyConfig.isReference()) {
				propertyConfigWithoutReferenceList.add(propertyConfig);
			}
		}
		return propertyConfigWithoutReferenceList;
	}

	/**
	 * Gets the list of not base class property configurations.
	 * 
	 * @return list of not base class property configurations
	 */
	public List<PropertyConfig> getNotBaseClassPropertyConfigList() {
		List<PropertyConfig> notBaseClassPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (!baseClassPropertyConfig(propertyConfig)) {
				notBaseClassPropertyConfigList.add(propertyConfig);
			}
		}
		return notBaseClassPropertyConfigList;
	}

	/**
	 * Gets the list of not base class essential property configurations.
	 * 
	 * @return list of not base class essential property configurations
	 */
	public List<PropertyConfig> getNotBaseClassEssentialPropertyConfigList() {
		List<PropertyConfig> notBaseClassEssentialPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getEssentialPropertyConfigList()) {
			if (!baseClassPropertyConfig(propertyConfig)) {
				notBaseClassEssentialPropertyConfigList.add(propertyConfig);
			}
		}
		return notBaseClassEssentialPropertyConfigList;
	}

	/**
	 * Checks if a property configuration has a base class.
	 * 
	 * @return <code>true</code> if a property configuration has a base class
	 */
	private boolean baseClassPropertyConfig(PropertyConfig propertyConfig) {
		boolean baseClassPropertyConfig = false;
		if (propertyConfig.getPropertyClass().equals("java.lang.String")
				|| propertyConfig.getPropertyClass()
						.equals("java.lang.Boolean")
				|| propertyConfig.getPropertyClass()
						.equals("java.lang.Integer")
				|| propertyConfig.getPropertyClass().equals("java.lang.Long")
				|| propertyConfig.getPropertyClass().equals("java.lang.Float")
				|| propertyConfig.getPropertyClass().equals("java.lang.Double")) {
			baseClassPropertyConfig = true;
		}
		return baseClassPropertyConfig;
	}

	/**
	 * Gets the list of required property configurations.
	 * 
	 * @return list of required property configurations
	 */
	public List<PropertyConfig> getRequiredPropertyList() {
		List<PropertyConfig> requiredPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isRequired()) {
				requiredPropertyConfigList.add(propertyConfig);
			}
		}
		return requiredPropertyConfigList;
	}

	/**
	 * Gets the list of required and without default value property
	 * configurations.
	 * 
	 * @return list of required and without default value property
	 *         configurations
	 */
	public List<PropertyConfig> getRequiredPropertyWithoutDefaultConfigList() {
		List<PropertyConfig> requiredPropertyWithoutDefaultConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isRequired()) {
				if (propertyConfig.getDefaultValue() == null
						|| propertyConfig.getDefaultValue().trim().equals("")) {
					requiredPropertyWithoutDefaultConfigList
							.add(propertyConfig);
				}
			}

		}
		return requiredPropertyWithoutDefaultConfigList;
	}

	/**
	 * Gets the list of derived property configurations.
	 * 
	 * @return list of derived property configurations
	 */
	public List<PropertyConfig> getDerivedPropertyConfigList() {
		List<PropertyConfig> derivedPropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isDerived()) {
				derivedPropertyConfigList.add(propertyConfig);
			}
		}
		return derivedPropertyConfigList;
	}

	/**
	 * Gets the last required and without default value property configuration.
	 * 
	 * @return last required and without default value property configuration
	 */
	public PropertyConfig getLastRequiredWithoutDefaultPropertyConfig() {
		PropertyConfig requiredPropertyWithoutDefaultConfig = null;
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isRequired()) {
				if (propertyConfig.getDefaultValue() == null
						|| propertyConfig.getDefaultValue().trim().equals("")) {
					requiredPropertyWithoutDefaultConfig = propertyConfig;
				}
			}
		}
		return requiredPropertyWithoutDefaultConfig;
	}

	/**
	 * Gets the list of must have property (value) configurations when creating
	 * a new entity.
	 * 
	 * @return list of must have property (value) configurations when creating a
	 *         new entity
	 */
	public List<PropertyConfig> getMustHavePropertyConfigList() {
		List<PropertyConfig> mustHavePropertyConfigList = new ArrayList<PropertyConfig>();
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isRequired() && !propertyConfig.isReference()
					&& !propertyConfig.isAutoIncrement()) {
				if (propertyConfig.getDefaultValue() == null
						|| propertyConfig.getDefaultValue().trim().equals("")) {
					mustHavePropertyConfigList.add(propertyConfig);
				}
			}
		}
		return mustHavePropertyConfigList;
	}

	/**
	 * Gets the last must have property (value) configuration.
	 * 
	 * @return last must have property (value) configuration
	 */
	public PropertyConfig getLastMustHavePropertyConfig() {
		PropertyConfig mustHavePropertyConfig = null;
		for (PropertyConfig propertyConfig : getList()) {
			if (propertyConfig.isRequired() && !propertyConfig.isReference()
					&& !propertyConfig.isAutoIncrement()) {
				if (propertyConfig.getDefaultValue() == null
						|| propertyConfig.getDefaultValue().trim().equals("")) {
					mustHavePropertyConfig = propertyConfig;
				}
			}
		}
		return mustHavePropertyConfig;
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		log.info("");
	}

}