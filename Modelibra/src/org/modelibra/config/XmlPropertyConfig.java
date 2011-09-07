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
import org.dom4j.Element;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.xml.XmlEntity;

/**
 * XML concept property configuration.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-16
 */
public class XmlPropertyConfig extends XmlEntity {

	private static final long serialVersionUID = 2200L;

	private static Log log = LogFactory.getLog(XmlPropertyConfig.class);

	private PropertyConfig propertyConfig;

	/**
	 * Constructs an XML concept property configuration.
	 */
	public XmlPropertyConfig(PropertyConfig propertyConfig) {
		super(propertyConfig, null);
		this.propertyConfig = propertyConfig;
	}

	/**
	 * Loads an XML concept property element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element extensionChild = element.element("extension");
		if (extensionChild != null) {
			String extension = extensionChild.getText().trim();
			propertyConfig.setExtension(extension);
		}

		Element extensionPropertyChild = element.element("extensionProperty");
		if (extensionPropertyChild != null) {
			String extensionProperty = extensionPropertyChild.getText().trim();
			propertyConfig.setExtensionProperty(extensionProperty);
		}

		Element propertyClassChild = element.element("propertyClass");
		if (propertyClassChild != null) {
			String propertyClass = propertyClassChild.getText().trim();
			propertyConfig.setPropertyClass(propertyClass);
		}

		Element derivedChild = element.element("derived");
		if (derivedChild != null) {
			String derived = derivedChild.getText().trim();
			propertyConfig.setDerived(derived);
		}

		Element validateTypeChild = element.element("validateType");
		if (validateTypeChild != null) {
			String validateType = validateTypeChild.getText().trim();
			propertyConfig.setValidateType(validateType);
		}

		Element validationTypeChild = element.element("validationType");
		if (validationTypeChild != null) {
			String validationType = validationTypeChild.getText().trim();
			propertyConfig.setValidationType(validationType);
		}

		Element maxLengthChild = element.element("maxLength");
		if (maxLengthChild != null) {
			String maxLength = maxLengthChild.getText().trim();
			propertyConfig.setMaxLength(maxLength);
		}

		Element requiredChild = element.element("required");
		if (requiredChild != null) {
			String required = requiredChild.getText().trim();
			propertyConfig.setRequired(required);
		}

		Element sensitiveChild = element.element("sensitive");
		if (sensitiveChild != null) {
			String sensitive = sensitiveChild.getText().trim();
			propertyConfig.setSensitive(sensitive);
		}

		Element defaultValueChild = element.element("defaultValue");
		if (defaultValueChild != null) {
			String defaultValue = defaultValueChild.getText().trim();
			propertyConfig.setDefaultValue(defaultValue);
		}

		Element autoIncrementChild = element.element("autoIncrement");
		if (autoIncrementChild != null) {
			String autoIncrement = autoIncrementChild.getText().trim();
			propertyConfig.setAutoIncrement(autoIncrement);
		}

		Element uniqueChild = element.element("unique");
		if (uniqueChild != null) {
			String unique = uniqueChild.getText().trim();
			propertyConfig.setUnique(unique);
		}

		Element indexChild = element.element("index");
		if (indexChild != null) {
			String index = indexChild.getText().trim();
			propertyConfig.setIndex(index);
		}

		Element referenceChild = element.element("reference");
		if (referenceChild != null) {
			String reference = referenceChild.getText().trim();
			propertyConfig.setReference(reference);
		}

		Element referenceNeighborChild = element.element("referenceNeighbor");
		if (referenceNeighborChild != null) {
			String referenceNeighbor = referenceNeighborChild.getText().trim();
			propertyConfig.setReferenceNeighbor(referenceNeighbor);
		}

		Element displayChild = element.element("display");
		if (displayChild != null) {
			String display = displayChild.getText().trim();
			propertyConfig.setDisplay(display);
		}

		Element updateChild = element.element("update");
		if (updateChild != null) {
			String update = updateChild.getText().trim();
			propertyConfig.setUpdate(update);
		}

		Element displayLengthChild = element.element("displayLength");
		if (displayLengthChild != null) {
			String displayLength = displayLengthChild.getText().trim();
			propertyConfig.setDisplayLength(displayLength);
		}

		Element essentialChild = element.element("essential");
		if (essentialChild != null) {
			String essential = essentialChild.getText().trim();
			propertyConfig.setEssential(essential);
		}

		Element scrambleChild = element.element("scramble");
		if (scrambleChild != null) {
			String scramble = scrambleChild.getText().trim();
			propertyConfig.setScramble(scramble);
		}

		Element whitespaceAllowedChild = element.element("whitespaceAllowed");
		if (whitespaceAllowedChild != null) {
			String whitespaceAllowed = whitespaceAllowedChild.getText().trim();
			propertyConfig.setWhitespaceAllowed(whitespaceAllowed);
		}

		Element referenceDropDownLookupChild = element
				.element("referenceDropDownLookup");
		if (referenceDropDownLookupChild != null) {
			String referenceDropDownLookup = referenceDropDownLookupChild
					.getText().trim();
			propertyConfig.setReferenceDropDownLookup(referenceDropDownLookup);
		}
	}

	/**
	 * Fills an XML concept element (no action).
	 * 
	 * @param element
	 *            XML element
	 */
	protected void fill(Element element) {
		throw new PersistencyRuntimeException(
				"A property configuration cannot be saved.");
	}

}