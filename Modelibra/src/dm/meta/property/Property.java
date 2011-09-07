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
package dm.meta.property;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dm.meta.concept.Concept;

/**
 * Property entity.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-09-25
 */
public class Property extends Entity<Property> {

	private static final long serialVersionUID = 110110140L;

	// private static Log log = LogFactory.getLog(Property.class);

	// modelibra model

	public static final boolean DEFAULT_EXTENSION = false;

	public static final String DEFAULT_PROPERTY_CLASS = "java.lang.String";

	public static final boolean DEFAULT_DERIVED = false;

	public static final boolean DEFAULT_VALIDATE_TYPE = false;

	public static final String DEFAULT_VALIDATION_TYPE = "org.dmlite.model.type.Email";

	public static final boolean DEFAULT_REQUIRED = false;

	public static final boolean DEFAULT_SENSITIVE = false;

	public static final boolean DEFAULT_AUTO_INCREMENT = false;

	public static final boolean DEFAULT_UNIQUE = false;

	public static final boolean DEFAULT_INDEX = false;

	public static final boolean DEFAULT_REFERENCE = false;

	// modelibra views

	public static final boolean DEFAULT_DISPLAY = true;

	public static final boolean DEFAULT_UPDATE = true;

	public static final int DEFAULT_STRING_LENGTH = 32;

	public static final int DEFAULT_BOOLEAN_LENGTH = 1;

	public static final int DEFAULT_DATE_LENGTH = 16;

	public static final int DEFAULT_INTEGER_LENGTH = 8;

	public static final int DEFAULT_LONG_LENGTH = 16;

	public static final int DEFAULT_FLOAT_LENGTH = 16;

	public static final int DEFAULT_DOUBLE_LENGTH = 32;

	public static final int DEFAULT_EMAIL_LENGTH = 80;

	public static final int DEFAULT_URL_LENGTH = 96;

	public static final boolean DEFAULT_ESSENTIAL = false;

	public static final boolean DEFAULT_SCRAMBLE = false;

	public static final boolean DEFAULT_WHITESPACE_ALLOWED = false;

	public static final boolean DEFAULT_REFERENCE_DROP_DOWN_LOOKUP = true;

	// modelibra model

	private Boolean extension = Boolean.valueOf(DEFAULT_EXTENSION);

	private String extensionProperty;

	private String propertyClass = DEFAULT_PROPERTY_CLASS;

	private Boolean derived = Boolean.valueOf(DEFAULT_DERIVED);

	private Boolean validateType = Boolean.valueOf(DEFAULT_VALIDATE_TYPE);

	private String validationType;

	private Integer maxLength = Integer.valueOf(DEFAULT_STRING_LENGTH);

	private Boolean required = Boolean.valueOf(DEFAULT_REQUIRED);

	private Boolean sensitive = Boolean.valueOf(DEFAULT_SENSITIVE);

	private String defaultValue;

	private Boolean autoIncrement = Boolean.valueOf(DEFAULT_AUTO_INCREMENT);

	private Boolean unique = Boolean.valueOf(DEFAULT_UNIQUE);

	private Boolean index = Boolean.valueOf(DEFAULT_INDEX);

	private Boolean reference = Boolean.valueOf(DEFAULT_REFERENCE);

	private String referenceNeighbor;

	// modelibra views

	private Boolean display = Boolean.valueOf(DEFAULT_DISPLAY);

	private Boolean update = Boolean.valueOf(DEFAULT_UPDATE);

	private Integer displayLength = Integer.valueOf(DEFAULT_STRING_LENGTH);

	private Boolean essential = Boolean.valueOf(DEFAULT_ESSENTIAL);

	private Boolean scramble = Boolean.valueOf(DEFAULT_SCRAMBLE);

	private Boolean whitespaceAllowed = Boolean
			.valueOf(DEFAULT_WHITESPACE_ALLOWED);

	private Boolean referenceDropDownLookup = Boolean
			.valueOf(DEFAULT_REFERENCE_DROP_DOWN_LOOKUP);

	// Concept parent neighbor (internal)
	private Concept concept;

	/**
	 * Constructs a concept property within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Property(IDomainModel domainModel) {
		super(domainModel);
		// no internal child neighbors
	}

	/**
	 * Constructs a concept property for the parent concept.
	 * 
	 * @param concept
	 *            concept
	 */
	public Property(Concept concept) {
		this(concept.getModel());
		// parent
		this.concept = concept;
	}

	/**
	 * Sets an extension switch.
	 * 
	 * @param extension
	 *            extension switch
	 */
	public void setExtension(Boolean extension) {
		this.extension = extension;
	}

	/**
	 * Gets an extension switch.
	 * 
	 * @return extension switch
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
	 * Sets an extension property.
	 * 
	 * @param extensionProperty
	 *            extension property
	 */
	public void setExtensionProperty(String extensionProperty) {
		this.extensionProperty = extensionProperty;
	}

	/**
	 * Gets an extension property.
	 * 
	 * @return extension property
	 */
	public String getExtensionProperty() {
		return extensionProperty;
	}

	/**
	 * Sets a property class (name).
	 * 
	 * @param propertyClass
	 *            property class complete name
	 */
	public void setPropertyClass(String propertyClass) {
		this.propertyClass = propertyClass;
	}

	/**
	 * Gets a property class (name).
	 * 
	 * @return property class complete name
	 */
	public String getPropertyClass() {
		if (propertyClass == null) {
			setPropertyClass(DEFAULT_PROPERTY_CLASS);
		}
		return propertyClass;
	}

	/**
	 * Sets a derived switch.
	 * 
	 * @param derived
	 *            derived switch
	 */
	public void setDerived(Boolean derived) {
		this.derived = derived;
	}

	/**
	 * Gets a derived switch.
	 * 
	 * @return derived switch
	 */
	public Boolean getDerived() {
		if (derived == null) {
			setDerived(Boolean.valueOf(DEFAULT_DERIVED));
		}
		return derived;
	}

	/**
	 * Checks if the property is derived.
	 * 
	 * @return <code>true</code> if the property is derived
	 */
	public boolean isDerived() {
		return getDerived().booleanValue();
	}

	/**
	 * Sets a validate type switch.
	 * 
	 * @param validateType
	 *            validate type switch
	 */
	public void setValidateType(Boolean validateType) {
		this.validateType = validateType;
	}

	/**
	 * Gets a validate type switch.
	 * 
	 * @return validate type switch
	 */
	public Boolean getValidateType() {
		if (validateType == null) {
			setValidateType(Boolean.valueOf(DEFAULT_VALIDATE_TYPE));
		}
		return validateType;
	}

	/**
	 * Checks if to validate property validation type.
	 * 
	 * @return <code>true</code> if to validate
	 */
	public boolean isValidateType() {
		return getValidateType().booleanValue();
	}

	/**
	 * Sets a validation type.
	 * 
	 * @param validationType
	 *            validation type
	 */
	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

	/**
	 * Gets a validation type.
	 * 
	 * @return validation type
	 */
	public String getValidationType() {
		if (isValidateType() && validationType == null) {
			setValidationType(DEFAULT_VALIDATION_TYPE);
		}
		return validationType;
	}

	/**
	 * Sets a maximal length.
	 * 
	 * @param maxLength
	 *            maximal length
	 */
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Gets a maximal length.
	 * 
	 * @return maximal length
	 */
	public Integer getMaxLength() {
		if (maxLength == null) {
			maxLength = Integer.valueOf(0);
		}
		return maxLength;
	}

	/**
	 * Sets a required switch.
	 * 
	 * @param required
	 *            required switch
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * Gets a required switch.
	 * 
	 * @return required switch
	 */
	public Boolean getRequired() {
		if (required == null) {
			setRequired(Boolean.valueOf(DEFAULT_REQUIRED));
		}
		return required;
	}

	/**
	 * Checks if the property is required.
	 * 
	 * @return <code>true</code> if the property is required
	 */
	public boolean isRequired() {
		return getRequired().booleanValue();
	}

	/**
	 * Sets a sensitive switch.
	 * 
	 * @param sensitive
	 *            sensitive switch
	 */
	public void setSensitive(Boolean sensitive) {
		this.sensitive = sensitive;
	}

	/**
	 * Gets a sensitive switch.
	 * 
	 * @return sensitive switch
	 */
	public Boolean getSensitive() {
		if (sensitive == null) {
			setSensitive(Boolean.valueOf(DEFAULT_SENSITIVE));
		}
		return sensitive;
	}

	/**
	 * Checks if the property is sensitive.
	 * 
	 * @return <code>true</code> if the property is sensitive
	 */
	public boolean isSensitive() {
		return getSensitive().booleanValue();
	}

	/**
	 * Sets a default value.
	 * 
	 * @param defaultValue
	 *            default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets a default value.
	 * 
	 * @return default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Sets an auto increment switch.
	 * 
	 * @param autoIncrement
	 *            auto increment switch
	 */
	public void setAutoIncrement(Boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	/**
	 * Gets an auto increment switch.
	 * 
	 * @return auto increment switch
	 */
	public Boolean getAutoIncrement() {
		if (autoIncrement == null) {
			setAutoIncrement(Boolean.valueOf(DEFAULT_AUTO_INCREMENT));
		}
		return autoIncrement;
	}

	/**
	 * Checks if the property is auto incremental.
	 * 
	 * @return <code>true</code> if the property is auto incremental
	 */
	public boolean isAutoIncrement() {
		return getAutoIncrement().booleanValue();
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
	 * Checks if the property is a part of the unique definition.
	 * 
	 * @return <code>true</code> if the property is a part of the unique
	 *         definition
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
	 * Sets a reference switch.
	 * 
	 * @param reference
	 *            reference switch
	 */
	public void setReference(Boolean reference) {
		this.reference = reference;
	}

	/**
	 * Gets a reference switch.
	 * 
	 * @return reference switch
	 */
	public Boolean getReference() {
		if (reference == null) {
			setReference(Boolean.valueOf(DEFAULT_REFERENCE));
		}
		return reference;
	}

	/**
	 * Checks if the property is reference.
	 * 
	 * @return <code>true</code> if the property is reference
	 */
	public boolean isReference() {
		return getReference().booleanValue();
	}

	/**
	 * Sets a reference neighbor.
	 * 
	 * @param referenceNeighbor
	 *            reference neighbor
	 */
	public void setReferenceNeighbor(String referenceNeighbor) {
		this.referenceNeighbor = referenceNeighbor;
	}

	/**
	 * Gets a reference neighbor.
	 * 
	 * @return reference neighbor
	 */
	public String getReferenceNeighbor() {
		return referenceNeighbor;
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
	 * Checks if the property will be displayed.
	 * 
	 * @return <code>true</code> if the property will be displayed
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
	 * Checks if the property allows updates.
	 * 
	 * @return <code>true</code> if the property allows updates
	 */
	public boolean isUpdate() {
		return getUpdate().booleanValue();
	}

	/**
	 * Sets a display length.
	 * 
	 * @param displayLength
	 *            display length
	 */
	public void setDisplayLength(Integer displayLength) {
		this.displayLength = displayLength;
	}

	/**
	 * Gets a display length.
	 * 
	 * @return display length
	 */
	public Integer getDisplayLength() {
		if (displayLength == null && getPropertyClass() != null) {
			displayLength = this.getMaxLength();
			int displayLengthInt = 0;
			if (displayLength != null) {
				displayLengthInt = displayLength.intValue();
			}
			if (displayLength == null || displayLengthInt == 0) {
				if (getPropertyClass().equals("java.lang.String")) {
					setDisplayLength(Integer.valueOf(DEFAULT_STRING_LENGTH));
				} else if (getPropertyClass().equals("java.lang.Boolean")) {
					setDisplayLength(Integer.valueOf(DEFAULT_BOOLEAN_LENGTH));
				} else if (getPropertyClass().equals("java.util.Date")) {
					setDisplayLength(Integer.valueOf(DEFAULT_DATE_LENGTH));
				} else if (getPropertyClass().equals("java.lang.Integer")) {
					setDisplayLength(Integer.valueOf(DEFAULT_INTEGER_LENGTH));
				} else if (getPropertyClass().equals("java.lang.Long")) {
					setDisplayLength(Integer.valueOf(DEFAULT_LONG_LENGTH));
				} else if (getPropertyClass().equals("java.lang.Float")) {
					setDisplayLength(Integer.valueOf(DEFAULT_FLOAT_LENGTH));
				} else if (getPropertyClass().equals("java.lang.Double")) {
					setDisplayLength(Integer.valueOf(DEFAULT_DOUBLE_LENGTH));
				} else if (getPropertyClass().equals("java.net.URL")) {
					setDisplayLength(Integer.valueOf(DEFAULT_URL_LENGTH));
				} else if (getPropertyClass().equals(
						"org.dmlite.util.email.Email")) {
					setDisplayLength(Integer.valueOf(DEFAULT_EMAIL_LENGTH));
				} else {
					setDisplayLength(Integer.valueOf(DEFAULT_STRING_LENGTH));
				}
			}
		}
		return displayLength;
	}

	/**
	 * Sets an essential (for a table display) switch.
	 * 
	 * @param essential
	 *            essential switch
	 */
	public void setEssential(Boolean essential) {
		this.essential = essential;
	}

	/**
	 * Gets an essential switch.
	 * 
	 * @return essential switch
	 */
	public Boolean getEssential() {
		if (essential == null) {
			setEssential(Boolean.valueOf(DEFAULT_ESSENTIAL));
		}
		return essential;
	}

	/**
	 * Checks if the property is essential.
	 * 
	 * @return <code>true</code> if the property is essential
	 */
	public boolean isEssential() {
		return getEssential().booleanValue();
	}

	/**
	 * Sets a scramble (display value) switch.
	 * 
	 * @param scramble
	 *            scramble switch
	 */
	public void setScramble(Boolean scramble) {
		this.scramble = scramble;
	}

	/**
	 * Gets a scramble switch.
	 * 
	 * @return scramble switch
	 */
	public Boolean getScramble() {
		if (scramble == null) {
			setScramble(Boolean.valueOf(DEFAULT_SCRAMBLE));
		}
		return scramble;
	}

	/**
	 * Checks if the property will be scrambled in a display.
	 * 
	 * @return <code>true</code> if the property will be scrambled in a display
	 */
	public boolean isScramble() {
		return getScramble().booleanValue();
	}

	/**
	 * Sets a whitespaceAllowed switch.
	 * 
	 * @param whitespaceAllowed
	 *            whitespaceAllowed switch
	 */
	public void setWhitespaceAllowed(Boolean whitespaceAllowed) {
		this.whitespaceAllowed = whitespaceAllowed;
	}

	/**
	 * Gets a whitespaceAllowed switch.
	 * 
	 * @return whitespaceAllowed switch
	 */
	public Boolean getWhitespaceAllowed() {
		if (whitespaceAllowed == null
				|| !getPropertyClass().equals("java.lang.String")) {
			setWhitespaceAllowed(Boolean.valueOf(DEFAULT_WHITESPACE_ALLOWED));
		}
		return whitespaceAllowed;
	}

	/**
	 * Checks if whitespace is allowed for this property.
	 * 
	 * @return <code>true</code> if whitespace is allowed for this property
	 */
	public boolean isWhitespaceAllowed() {
		return getWhitespaceAllowed().booleanValue();
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