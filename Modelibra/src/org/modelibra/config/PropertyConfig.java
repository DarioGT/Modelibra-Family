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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IEntity;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.util.Reflector;
import org.modelibra.util.TextHandler;
import org.modelibra.util.Transformer;

/**
 * Concept property configuration consists of properties for the concept
 * property configuration used in Modelibra, and property properties for the
 * model default application used in dmWicket.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-28
 */
public class PropertyConfig extends Entity<PropertyConfig> implements
		IPersistentEntity {

	private static final long serialVersionUID = 2100L;

	private static Log log = LogFactory.getLog(PropertyConfig.class);

	private ConceptConfig conceptConfig; // parent

	/*
	 * The concept property configuration properties.
	 */

	private String extension; // Boolean

	private String extensionProperty;

	private String propertyClass;

	private String derived; // Boolean

	private String validateType; // Boolean

	private String validationType;

	private String maxLength; // Integer

	private String required; // Boolean

	private String sensitive; // Boolean

	private String defaultValue;

	private String autoIncrement; // Boolean

	private String unique; // Boolean

	private String index; // Boolean

	private String reference; // Boolean

	private String referenceNeighbor;

	private String referenceDropDownLookup; // Boolean

	/*
	 * End of the concept property configuration properties.
	 */

	/*
	 * The model default application concept property configuration properties.
	 */

	private String display; // Boolean

	private String update; // Boolean

	private String displayLength; // Integer

	private String essential; // Boolean

	private String scramble; // Boolean

	private String whitespaceAllowed; // Boolean

	/*
	 * End of the model default application concept property configuration
	 * properties.
	 */

	private XmlPropertyConfig xmlProperty;

	private TextHandler textHandler = new TextHandler();

	private boolean extended = false;

	/**
	 * Constructs the concept property configuration.
	 */
	public PropertyConfig() {
		super();
		xmlProperty = new XmlPropertyConfig(this);
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
	 * Gets the concept property code that starts with a capital letter.
	 * 
	 * @return concept property code that starts with a capital letter
	 */
	public String getCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getCode());
	}

	/**
	 * Gets the concept property code that starts with a lower letter.
	 * 
	 * @return concept property code that starts with a lower letter
	 */
	public String getCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getCode());
	}

	/**
	 * Gets the property name that starts with a capital letter.
	 * 
	 * @return property name
	 */
	public String getPropertyName() {
		return getCodeWithFirstLetterAsUpper();
	}

	/**
	 * Gets the full name of the property (domain.model.property).
	 * 
	 * @return full name of the property
	 */
	public String getFullName() {
		ConceptConfig conceptConfig = getConceptConfig();
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		DomainConfig domainConfig = modelConfig.getDomainConfig();
		String fullName = domainConfig.getCode() + "." + modelConfig.getCode()
				+ "." + conceptConfig.getCode();
		return fullName;
	}

	/**
	 * Sets the property extension flag: true string if there is an extension.
	 * 
	 * @param extension
	 *            true string if there is an extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets the property extension flag: true string if there is an extension.
	 * 
	 * @return true string if there is an extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the property extension flag: <code>true</code> if there is an
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
	 * Sets the extension property.
	 * 
	 * @param extensionProperty
	 *            extension property
	 */
	public void setExtensionProperty(String extensionProperty) {
		this.extensionProperty = extensionProperty;
	}

	/**
	 * Gets the extension property.
	 * 
	 * @return extension property
	 */
	public String getExtensionProperty() {
		if (isExtension() && extensionProperty == null) {
			String message = getCode()
					+ " property does not have the extension property";
			throw new ConfigRuntimeException(message);
		}
		return extensionProperty;
	}

	/**
	 * Sets the concept property class (class full name). Any Java class could
	 * be used.
	 * 
	 * @param propertyClass
	 *            concept property class
	 */
	public void setPropertyClass(String propertyClass) {
		this.propertyClass = propertyClass;
	}

	/**
	 * Gets the concept property class (class full name). Default is
	 * java.lang.String.
	 * 
	 * @return concept property class
	 */
	public String getPropertyClass() {
		if (propertyClass == null) {
			if (!isExtension()) {
				return "java.lang.String";
			}
		}
		return propertyClass;
	}

	/**
	 * Gets the concept property class simple name.
	 * 
	 * @return concept property class simple name
	 */
	public String getPropertyClassSimpleName() {
		String propertyClassSimpleName = null;
		String propertyClass = getPropertyClass();
		if (propertyClass != null) {
			TextHandler textExtractor = new TextHandler();
			propertyClassSimpleName = textExtractor
					.extractClassSimpleName(propertyClass);
		}
		return propertyClassSimpleName;
	}

	/**
	 * Gets the concept property class object.
	 * 
	 * @return concept property class object
	 */
	public Class<?> getPropertyClassObject() {
		Class<?> propertyClassObject = null;
		String propertyClass = getPropertyClass();
		if (propertyClass != null) {
			propertyClassObject = Reflector.getClass(propertyClass);
		}
		return propertyClassObject;
	}

	/**
	 * Sets the derived flag: true if the property is to be derived by the get
	 * method.
	 * 
	 * @param derived
	 *            derived
	 */
	public void setDerived(String derived) {
		this.derived = derived;
	}

	/**
	 * Gets the derived flag: true if the property is to be derived by the get
	 * method.
	 * 
	 * @return true string if the property is derived
	 */
	public String getDerived() {
		return derived;
	}

	/**
	 * Sets the derived flag: <code>true</code> if the property is to be derived
	 * by the get method.
	 * 
	 * @param derived
	 *            derived
	 */
	public void setDerived(boolean derived) {
		if (derived) {
			setDerived("true");
		} else {
			setDerived("false");
		}
	}

	/**
	 * Checks if the property is derived. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the property is derived
	 */
	public boolean isDerived() {
		boolean result = false;
		if (derived != null) {
			if (derived.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the validate property type flag (for not basic types): true if the
	 * property is to be validated with the validation type.
	 * 
	 * @param validateType
	 *            validate property type
	 */
	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	/**
	 * Gets the validate property type flag (for not basic types): true if the
	 * property is to be validated with the validation type.
	 * 
	 * @return validate property type
	 */
	public String getValidateType() {
		return validateType;
	}

	/**
	 * Sets the validate property type flag (for not basic types):
	 * <code>true</code> if the property is to be validated with the validation
	 * type.
	 * 
	 * @param validateType
	 *            validate property type
	 */
	public void setValidateType(boolean validateType) {
		if (validateType) {
			setValidateType("true");
		} else {
			setValidateType("false");
		}
	}

	/**
	 * Checks if to validate the property validation type. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if to validate the property validation type
	 */
	public boolean isValidateType() {
		boolean result = false;
		if (validateType != null) {
			if (validateType.trim().equalsIgnoreCase("true")) {
				if (getPropertyClass().equals(PropertyClass.getString())) {
					result = true;
				} else {
					String message = getCode() + " property class must be "
							+ PropertyClass.getString()
							+ " to be type validated.";
					throw new ConfigRuntimeException(message);
				}
			}
		}
		return result;
	}

	/**
	 * Sets the property validation type. Used to validate a property value in
	 * addition to its class. Validation type may be either a valid validation
	 * type from the the ValidationType class or entities so that a dynamic
	 * validation may be done based on the the code value in entities. For
	 * cleaner handling two different properties should have been used:
	 * validationType and validationEntities.
	 * 
	 * @param validationType
	 *            property validation type
	 */
	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

	/**
	 * Gets the property validation type. Used to validate a property value in
	 * addition to its class.
	 * 
	 * @return property validation type
	 */
	public String getValidationType() {
		if (validationType == null) {
			if (!isExtension() && isValidateType()) {
				String message = getCode()
						+ " property does not have the validation type.";
				throw new ConfigRuntimeException(message);
			}
		}
		return validationType;
	}

	/**
	 * Checks if the validation type is represented by a class.
	 * 
	 * @return <code>true</code> if the validation type is represented by a
	 *         class
	 */
	public boolean isValidateClassType() {
		boolean result = false;
		String validationType = getValidationType();
		if (isValidateType() && ValidationType.isValid(validationType)) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks if the validation type is represented by entities (code).
	 * 
	 * @return <code>true</code> if the validation type is represented by
	 *         entities (code)
	 */
	public boolean isValidateEntitiesType() {
		boolean result = false;
		if (isValidateType() && !isValidateClassType()) {
			result = true;
		}
		return result;
	}

	/**
	 * Sets the property maximal length.
	 * 
	 * @param maxLength
	 *            property maximal length
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Gets the property maximal length.
	 * 
	 * @return property maximal length
	 */
	public String getMaxLength() {
		return maxLength;
	}

	/**
	 * Gets the property maximal length (as an int number).
	 * 
	 * @return property maximal length
	 */
	public int getMaxLengthInt() {
		try {
			Integer maxLengthInteger = Transformer.integer(getMaxLength());
			return maxLengthInteger.intValue();
		} catch (TypeRuntimeException e) {
			return 0;
		}
	}

	/**
	 * Checks if the property maximal length is not null and greater than 0.
	 * Returns <code>false</code> if there is no value or the length is 0.
	 * 
	 * @return <code>true</code> if the property maximal length is not null and
	 *         greater than 0
	 */
	public boolean isMaxLength() {
		boolean result = false;
		if (getMaxLengthInt() > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * Sets the required property flag: true string if the property is required
	 * (not null).
	 * 
	 * @param required
	 *            true string if the property is required
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * 
	 * Gets the required property flag: true string if the property is required
	 * (not null).
	 * 
	 * @return true string if the property is required
	 */
	public String getRequired() {
		return required;
	}

	/**
	 * Sets the required property flag: <code>true</code> if the property is
	 * required (not null).
	 * 
	 * @param required
	 *            <code>true</code> if the property is required
	 */
	public void setRequired(boolean required) {
		if (required) {
			setRequired("true");
		} else {
			setRequired("true");
		}
	}

	/**
	 * Checks if the property is required. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the property is required
	 */
	public boolean isRequired() {
		boolean result = false;
		if (required != null) {
			if (required.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the sensitive property flag: true string if the property is
	 * sensitive.
	 * 
	 * @param sensitive
	 *            true string if the property is sensitive
	 */
	public void setSensitive(String sensitive) {
		this.sensitive = sensitive;
	}

	/**
	 * 
	 * Gets the sensitive property flag: true string if the property is
	 * sensitive.
	 * 
	 * @return true string if the property is sensitive
	 */
	public String getSensitive() {
		return sensitive;
	}

	/**
	 * Sets the sensitive property flag: <code>true</code> if the property is
	 * sensitive.
	 * 
	 * @param sensitive
	 *            <code>true</code> if the property is sensitive
	 */
	public void setSensitive(boolean sensitive) {
		if (sensitive) {
			setSensitive("true");
		} else {
			setSensitive("false");
		}
	}

	/**
	 * Checks if the property is sensitive. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the property is sensitive
	 */
	public boolean isSensitive() {
		boolean result = false;
		if (sensitive != null) {
			if (sensitive.trim().equalsIgnoreCase("true")) {
				result = true;
				setRequired("false");
			}
		}
		return result;
	}

	/**
	 * Sets the property default value (as a string value).
	 * 
	 * @param defaultValue
	 *            default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets the property default value (as a string value).
	 * 
	 * @return default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Sets the auto increment property flag: true string if the property is
	 * auto increment.
	 * 
	 * @param autoIncrement
	 *            true string if the property is auto increment
	 */
	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	/**
	 * Gets the auto increment property flag: true string if the property is
	 * auto increment.
	 * 
	 * @return true string if the property is auto increment
	 */
	public String getAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * Sets the auto increment property flag: <code>true</code> if the property
	 * is auto increment.
	 * 
	 * @param autoIncrement
	 *            <code>true</code> if the property is auto increment
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		if (autoIncrement) {
			setAutoIncrement("true");
		} else {
			setAutoIncrement("false");
		}
	}

	/**
	 * Checks if the property is auto increment. Returns <code>false</code> if
	 * there is no value.
	 * 
	 * @return <code>true</code> if the property is auto increment
	 */
	public boolean isAutoIncrement() {
		boolean result = false;
		if (autoIncrement != null) {
			if (autoIncrement.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the unique property flag: true string if the property is unique.
	 * 
	 * @param unique
	 *            true string if the property is unique
	 */
	public void setUnique(String unique) {
		this.unique = unique;
	}

	/**
	 * Gets the unique property flag: <code>true</code> if the property is
	 * unique.
	 * 
	 * @return <code>true</code> if the property is unique
	 */
	public String getUnique() {
		return unique;
	}

	/**
	 * Sets the unique property flag: <code>true</code> if the property is
	 * unique.
	 * 
	 * @param unique
	 *            <code>true</code> if the property is unique
	 */
	public void setUnique(boolean unique) {
		if (unique) {
			setUnique("true");
		} else {
			setUnique("false");
		}
	}

	/**
	 * Checks if the property is unique. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the property is unique
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
	 * Sets the index property flag: true string if the property is indexed.
	 * 
	 * @param index
	 *            true string if the property is indexed
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets the index property flag: true string if the property is indexed.
	 * 
	 * @return true string if the property is indexed
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index property flag: <code>true</code> if the property is
	 * indexed.
	 * 
	 * @param index
	 *            <code>true</code> if the property is indexed
	 */
	public void setIndex(boolean index) {
		if (index) {
			setIndex("true");
		} else {
			setIndex("false");
		}
	}

	/**
	 * Checks if the property is indexed. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the property is indexed
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
	 * Sets the reference property flag: true string if the property is
	 * reference.
	 * 
	 * @param reference
	 *            true string if the property is reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the reference property flag: true string if the property is
	 * reference.
	 * 
	 * @return true string if the property is reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference property flag: <code>true</code> if the property is
	 * reference.
	 * 
	 * @param reference
	 *            <code>true</code> if the property is reference
	 */
	public void setReference(boolean reference) {
		if (reference) {
			setReference("true");
		} else {
			setReference("false");
		}
	}

	/**
	 * Checks if the property is reference. Returns <code>false</code> if there
	 * is no value.
	 * 
	 * @return <code>true</code> if the property is reference
	 */
	public boolean isReference() {
		boolean result = false;
		if (reference != null) {
			if (reference.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Gets the reference concept configuration.
	 * 
	 * @return reference concept configuration
	 */
	public ConceptConfig getReferenceConceptConfig() {
		ConceptConfig conceptConfig = null;
		if (isReference()) {
			ConceptConfig contextConceptConfig = getConceptConfig();
			NeighborsConfig neighborsConfig = contextConceptConfig
					.getNeighborsConfig();
			if (neighborsConfig != null) {
				NeighborConfig neighborConfig = neighborsConfig
						.getNeighborConfig(getReferenceNeighbor());
				if (neighborConfig != null) {
					conceptConfig = neighborConfig
							.getDestinationConceptConfig();
				} else {
					log.info(getConceptConfig().getCode() + "." + getCode()
							+ " property is not reference.");
				}
			} else {
				log.info(getConceptConfig()
						+ " concept does not have neighbors.");
			}
		} else {
			log.info(getConceptConfig().getCode() + "."
					+ getReferenceNeighbor() + " neighbor does not exist.");
		}
		return conceptConfig;
	}

	/**
	 * Sets the reference neighbor (concept code).
	 * 
	 * @param referenceNeighbor
	 *            reference neighbor
	 */
	public void setReferenceNeighbor(String referenceNeighbor) {
		this.referenceNeighbor = referenceNeighbor;
	}

	/**
	 * Gets the reference neighbor (concept code).
	 * 
	 * @return reference neighbor
	 */
	public String getReferenceNeighbor() {
		if (referenceNeighbor == null) {
			if (!isExtension() && isReference()) {
				log
						.info("The reference property does not have the reference neighbor: "
								+ getCode());
			}
		}
		return referenceNeighbor;
	}

	/**
	 * Sets the display property flag: true string if the property will be
	 * displayed.
	 * 
	 * @param display
	 *            true string if the the property will be displayed
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * Gets the display property flag: true string if the property will be
	 * displayed.
	 * 
	 * @return true string if the the property will be displayed
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * Sets the display property flag: <code>true</code> if the property will be
	 * displayed.
	 * 
	 * @param display
	 *            <code>true</code> if the the property will be displayed
	 */
	public void setDisplay(boolean display) {
		if (display) {
			setDisplay("true");
		} else {
			setDisplay("false");
		}
	}

	/**
	 * Checks if the property will be displayed. Returns <code>true</code> if
	 * there is no value.
	 * 
	 * @return <code>true</code> if the property will be displayed
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
	 * Sets the update property flag: true string if the property is allowed to
	 * be updated.
	 * 
	 * @param update
	 *            true string if the the property is allowed to be updated
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * Gets the update property flag: true string if the property is allowed to
	 * be updated.
	 * 
	 * @return true string if the the property is allowed to be updated
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * Sets the update property flag: <code>true</code> if the property is
	 * allowed to be updated.
	 * 
	 * @param update
	 *            <code>true</code> if the property is allowed to be updated
	 */
	public void setUpdate(boolean update) {
		if (update) {
			setUpdate("true");
		} else {
			setUpdate("false");
		}
	}

	/**
	 * Checks if the property is allowed to be updated. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the property is allowed to be updated
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
	 * Sets the property display length.
	 * 
	 * @param displayLength
	 *            display length
	 */
	public void setDisplayLength(String displayLength) {
		this.displayLength = displayLength;
	}

	/**
	 * Gets the property display length. If max. length is greater than 0,
	 * default is max. length.
	 * 
	 * @return display length
	 */
	public String getDisplayLength() {
		if (displayLength == null) {
			if (!isExtension() && getMaxLengthInt() > 0) {
				return getMaxLength();
			}
		}
		return displayLength;
	}

	/**
	 * Gets the property display length (as an int number). Returns 0 if there
	 * is no length.
	 * 
	 * @return display length
	 */
	public int getDisplayLengthInt() {
		try {
			Integer displayLengthInteger = Transformer
					.integer(getDisplayLength());
			return displayLengthInteger.intValue();
		} catch (TypeRuntimeException e) {
			return 0;
		}
	}

	/**
	 * Sets the essential property flag: true string if the property is
	 * essential. A property is essential if it is one of the most important
	 * concept properties. Essential properties are displayed in a table of
	 * concept entities.
	 * 
	 * @param essential
	 *            true string if the property is essential
	 */
	public void setEssential(String essential) {
		this.essential = essential;
	}

	/**
	 * Gets the essential property flag: true string if the property is
	 * essential. A property is essential if it is one of the most important
	 * concept properties. Essential properties are displayed in a table of
	 * concept entities. Default is the same as required.
	 * 
	 * @return true string if the property is essential
	 */
	public String getEssential() {
		if (essential == null) {
			if (!isExtension()) {
				return getRequired();
			}
		}
		return essential;
	}

	/**
	 * Sets the essential property flag: <code>true</code> if the property is
	 * essential. A property is essential if it is one of the most important
	 * concept properties. Essential properties are displayed in a table of
	 * concept entities.
	 * 
	 * @param essential
	 *            <code>true</code> if the property is essential
	 */
	public void setEssential(boolean essential) {
		if (essential) {
			setEssential("true");
		} else {
			setEssential("false");
		}
	}

	/**
	 * Checks if the property is essential. Returns <code>false</code> if there
	 * is no value, except if there is no extension and the property is
	 * required.
	 * 
	 * @return <code>true</code> if the property is essential
	 */
	public boolean isEssential() {
		boolean result = false;
		String essential = getEssential();
		if (essential != null) {
			if (essential.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		} else if (!isExtension()) {
			result = isRequired();
		}
		return result;
	}

	/**
	 * Sets the scramble property value flag: true string if the property will
	 * be scrambled in a display.
	 * 
	 * @param scramble
	 *            true string if the property will be scrambled in a display
	 */
	public void setScramble(String scramble) {
		this.scramble = scramble;
	}

	/**
	 * Gets the scramble property value flag: true string if the property will
	 * be scrambled in a display.
	 * 
	 * @return true string if the property will be scrambled in a display
	 */
	public String getScramble() {
		return scramble;
	}

	/**
	 * Sets the scramble property value flag: <code>true</code> if the property
	 * will be scrambled in a display.
	 * 
	 * @param scramble
	 *            <code>true</code> if the property will be scrambled in a
	 *            display
	 */
	public void setScramble(boolean scramble) {
		if (scramble) {
			setScramble("true");
		} else {
			setScramble("false");
		}
	}

	/**
	 * Checks if the property will be scrambled in a display. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if the property will be scrambled in a display
	 */
	public boolean isScramble() {
		boolean result = false;
		if (scramble != null) {
			if (scramble.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the whitespaceAllowed property flag: true string if whitespace is
	 * allowed for this property.
	 * 
	 * @param whitespaceValid
	 *            true string if the whitespace is allowed for this property
	 */
	public void setWhitespaceAllowed(String whitespaceAllowed) {
		if (getPropertyClass().equals("java.lang.String")) {
			this.whitespaceAllowed = whitespaceAllowed;
		}
	}

	/**
	 * 
	 * Gets the whitespaceAllowed property flag: true string if whitespace is
	 * valid for this property.
	 * 
	 * @return true string if whitespace is allowed for this property
	 */
	public String getWhitespaceAllowed() {
		return whitespaceAllowed;
	}

	/**
	 * Sets the whitespaceAllowed property flag: <code>true</code> if whitespace
	 * is allowed for this property.
	 * 
	 * @param whitespaceValid
	 *            <code>true</code> if the whitespace is allowed for this
	 *            property
	 */
	public void setWhitespaceAllowed(boolean whitespaceAllowed) {
		if (whitespaceAllowed) {
			setWhitespaceAllowed("true");
		} else {
			setWhitespaceAllowed("false");
		}
	}

	/**
	 * Checks if whitespace is allowed for this property. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if the whitespace is allowed for this property
	 */
	public boolean isWhitespaceAllowed() {
		boolean result = false;
		if (whitespaceAllowed != null) {
			if (whitespaceAllowed.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the reference drop down lookup property flag: true string if the
	 * drop down choice will be used for the reference neighbor lookup; false
	 * string if a table of neighbor entities in a separate page will be used.
	 * 
	 * @param referenceDropDownLookup
	 *            true string if the drop down choice will be used for the
	 *            reference neighbor lookup
	 */
	public void setReferenceDropDownLookup(String referenceDropDownLookup) {
		this.referenceDropDownLookup = referenceDropDownLookup;
	}

	/**
	 * Gets the reference drop down lookup property flag: true string if the
	 * drop down choice will be used for the reference neighbor lookup; false
	 * string if a table of neighbor entities in a separate page will be used.
	 * 
	 * @return true string if the drop down choice will be used for the
	 *         reference neighbor lookup
	 */
	public String getReferenceDropDownLookup() {
		return referenceDropDownLookup;
	}

	/**
	 * Sets the reference drop down lookup property flag: <code>true</code> if
	 * the drop down choice will be used for the reference neighbor lookup;
	 * false string if a table of neighbor entities in a separate page will be
	 * used.
	 * 
	 * @param referenceDropDownLookup
	 *            <code>true</code> if the drop down choice will be used for the
	 *            reference neighbor lookup
	 */
	public void setReferenceDropDownLookup(boolean referenceDropDownLookup) {
		if (referenceDropDownLookup) {
			setReferenceDropDownLookup("true");
		} else {
			setReferenceDropDownLookup("false");
		}
	}

	/**
	 * Checks if the drop down choice will be used for the reference neighbor
	 * lookup. Returns <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the drop down choice will be used for the
	 *         reference neighbor lookup
	 */
	public boolean isReferenceDropDownLookup() {
		boolean result = true;
		if (referenceDropDownLookup != null) {
			if (referenceDropDownLookup.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Updates the property configuration (not allowed).
	 * 
	 * @param propertyConfig
	 *            property configuration entity
	 * @return <code>true</code> if the property configuration is updated with a
	 *         given entity
	 */
	public boolean update(PropertyConfig propertyConfig) {
		return false;
	}

	/**
	 * Gets the XML persistent property.
	 * 
	 * @return XML persistent property
	 */
	public XmlPropertyConfig getXmlProperty() {
		return xmlProperty;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlProperty.getPersistentModel();
	}

	/**
	 * Gets the property entity.
	 * 
	 * @return property entity
	 */
	public IEntity<?> getEntity() {
		return xmlProperty.getEntity();
	}

	/**
	 * Extends the property configuration.
	 */
	void extend() {
		if (!isExtended()) {
			if (isExtension()) {
				extendWithProperty();
			}
		}
	}

	/**
	 * Extends the property configuration with property.
	 */
	private void extendWithProperty() {
		String extensionDomain = getConceptConfig().getExtensionDomain();
		String extensionDomainType = getConceptConfig()
				.getExtensionDomainType();
		String extensionModel = getConceptConfig().getExtensionModel();
		String extensionConcept = getConceptConfig().getExtensionConcept();
		String extensionProperty = getExtensionProperty();

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
					PropertyConfig extensionPropertyConfig = extensionConceptConfig
							.getPropertyConfig(extensionProperty);
					if (extensionPropertyConfig != null) {
						extendWithProperty(extensionPropertyConfig);
					} else {
						log.error(extensionProperty
								+ " is not the correct extension property.");
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
	 * Extends null fields of the property configuration.
	 * 
	 * @param extensionPropertyConfig
	 *            extension property configuration
	 */
	private void extendWithProperty(PropertyConfig extensionPropertyConfig) {
		extendPropertyConfigNullFields(extensionPropertyConfig);

		setExtended(true);
	}

	/**
	 * Extends the property configuration null fields with fields from a given
	 * extension property configuration.
	 * 
	 * @param extensionPropertyConfig
	 *            extension property configuration
	 */
	private void extendPropertyConfigNullFields(
			PropertyConfig extensionPropertyConfig) {
		if (getPropertyClass() == null) {
			setPropertyClass(extensionPropertyConfig.getPropertyClass());
		}
		if (getDerived() == null) {
			setDerived(extensionPropertyConfig.getDerived());
		}
		if (getValidateType() == null) {
			setValidateType(extensionPropertyConfig.getValidateType());
		}
		if (getValidationType() == null) {
			setValidationType(extensionPropertyConfig.getValidationType());
		}
		if (getMaxLength() == null) {
			setMaxLength(extensionPropertyConfig.getMaxLength());
		}
		if (getRequired() == null) {
			setRequired(extensionPropertyConfig.getRequired());
		}
		if (getSensitive() == null) {
			setSensitive(extensionPropertyConfig.getSensitive());
		}
		if (getDefaultValue() == null) {
			setDefaultValue(extensionPropertyConfig.getDefaultValue());
		}
		if (getAutoIncrement() == null) {
			setAutoIncrement(extensionPropertyConfig.getAutoIncrement());
		}
		if (getUnique() == null) {
			setUnique(extensionPropertyConfig.getUnique());
		}
		if (getIndex() == null) {
			setIndex(extensionPropertyConfig.getIndex());
		}
		if (getReference() == null) {
			setReference(extensionPropertyConfig.getReference());
		}
		if (getReferenceNeighbor() == null) {
			setReferenceNeighbor(extensionPropertyConfig.getReferenceNeighbor());
		}
		if (getDisplay() == null) {
			setDisplay(extensionPropertyConfig.getDisplay());
		}
		if (getUpdate() == null) {
			setUpdate(extensionPropertyConfig.getUpdate());
		}
		if (getDisplayLength() == null) {
			setDisplayLength(extensionPropertyConfig.getDisplayLength());
		}
		if (getEssential() == null) {
			setEssential(extensionPropertyConfig.getEssential());
		}
		if (getScramble() == null) {
			setScramble(extensionPropertyConfig.getScramble());
		}
		if (getWhitespaceAllowed() == null) {
			setWhitespaceAllowed(extensionPropertyConfig.getWhitespaceAllowed());
		}
		if (getReferenceDropDownLookup() == null) {
			setReferenceDropDownLookup(extensionPropertyConfig
					.getReferenceDropDownLookup());
		}
	}

	/**
	 * Copies a given extension property configuration to the current property
	 * configuration (only properties, without parent).
	 * 
	 * @param extensionPropertyConfig
	 *            extension property configuration
	 */
	public PropertyConfig copyPropertiesFrom(
			PropertyConfig extensionPropertyConfig) {
		this.setCode(extensionPropertyConfig.getCode());
		this.setExtension(extensionPropertyConfig.getExtension());
		this.setExtensionProperty(extensionPropertyConfig
				.getExtensionProperty());
		this.setPropertyClass(extensionPropertyConfig.getPropertyClass());
		this.setDerived(extensionPropertyConfig.getDerived());
		this.setValidateType(extensionPropertyConfig.getValidateType());
		this.setValidationType(extensionPropertyConfig.getValidationType());
		this.setMaxLength(extensionPropertyConfig.getMaxLength());
		this.setRequired(extensionPropertyConfig.getRequired());
		this.setSensitive(extensionPropertyConfig.getSensitive());
		this.setDefaultValue(extensionPropertyConfig.getDefaultValue());
		this.setAutoIncrement(extensionPropertyConfig.getAutoIncrement());
		this.setUnique(extensionPropertyConfig.getUnique());
		this.setIndex(extensionPropertyConfig.getIndex());
		this.setReference(extensionPropertyConfig.getReference());
		this.setReferenceNeighbor(extensionPropertyConfig
				.getReferenceNeighbor());
		this.setDisplay(extensionPropertyConfig.getDisplay());
		this.setUpdate(extensionPropertyConfig.getUpdate());
		this.setDisplayLength(extensionPropertyConfig.getDisplayLength());
		this.setEssential(extensionPropertyConfig.getEssential());
		this.setScramble(extensionPropertyConfig.getScramble());
		this.setWhitespaceAllowed(extensionPropertyConfig
				.getWhitespaceAllowed());
		this.setReferenceDropDownLookup(extensionPropertyConfig
				.getReferenceDropDownLookup());
		return this;
	}

	/**
	 * Outputs property configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("---------------- " + title + " ----------------");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(Extension = " + getExtension() + ")");
		log.info("(Extension Property = " + getExtensionProperty() + ")");
		log.info("(Property class object = " + getPropertyClassObject() + ")");
		log.info("(Property class name = " + getPropertyClass() + ")");
		log.info("(Property class simple name = "
				+ getPropertyClassSimpleName() + ")");
		log.info("(Derived = " + getDerived() + ")");
		log.info("(Validate type = " + getValidateType() + ")");
		log.info("(Validation class name = " + getValidationType() + ")");
		log.info("(Max length = " + getMaxLength() + ")");
		log.info("(Required = " + getRequired() + ")");
		log.info("(Sensitive = " + getSensitive() + ")");
		log.info("(Default value = " + getDefaultValue() + ")");
		log.info("(Auto increment = " + getAutoIncrement() + ")");
		log.info("(Unique = " + getUnique() + ")");
		log.info("(Index = " + getIndex() + ")");
		log.info("(Reference = " + getReference() + ")");
		log.info("(Reference neighbor = " + getReferenceNeighbor() + ")");
		log.info("(Display = " + getDisplay() + ")");
		log.info("(Update = " + getUpdate() + ")");
		log.info("(Display length = " + getDisplayLength() + ")");
		log.info("(Essential = " + getEssential() + ")");
		log.info("(Scramble = " + getScramble() + ")");
		log.info("(Whitespace allowed = " + getWhitespaceAllowed() + ")");
		log.info("(Referenec drop down lookup = "
				+ getReferenceDropDownLookup() + ")");
	}

	/**
	 * Sets the property extended flag.
	 * 
	 * @param extended
	 *            <code>true</code> if the property has been extended
	 */
	private void setExtended(boolean extended) {
		this.extended = extended;
	}

	/**
	 * Checks if the property is extended. Returns <code>false</code> if the
	 * property has not been extended
	 * 
	 * @return <code>true</code> if the property has been extended
	 */
	private boolean isExtended() {
		return extended;
	}

}