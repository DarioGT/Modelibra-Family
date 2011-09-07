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

import org.dom4j.Element;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.xml.XmlEntity;

/**
 * XML model concept configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlConceptConfig extends XmlEntity {

	private static final long serialVersionUID = 2110L;

	private ConceptConfig conceptConfig;

	/**
	 * Constructs an XML concept configuration.
	 */
	public XmlConceptConfig(ConceptConfig conceptConfig) {
		super(conceptConfig, null);
		this.conceptConfig = conceptConfig;
	}

	/**
	 * Loads an XML concept element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element abstractionChild = element.element("abstraction");
		if (abstractionChild != null) {
			String abstraction = abstractionChild.getText().trim();
			conceptConfig.setAbstraction(abstraction);
		}

		Element extensionChild = element.element("extension");
		if (extensionChild != null) {
			String extension = extensionChild.getText().trim();
			conceptConfig.setExtension(extension);
		}

		Element extensionDomainChild = element.element("extensionDomain");
		if (extensionDomainChild != null) {
			String extensionDomain = extensionDomainChild.getText().trim();
			conceptConfig.setExtensionDomain(extensionDomain);
		}

		Element extensionDomainTypeChild = element
				.element("extensionDomainType");
		if (extensionDomainTypeChild != null) {
			String extensionDomainType = extensionDomainTypeChild.getText()
					.trim();
			conceptConfig.setExtensionDomainType(extensionDomainType);
		}

		Element extensionModelChild = element.element("extensionModel");
		if (extensionModelChild != null) {
			String extensionModel = extensionModelChild.getText().trim();
			conceptConfig.setExtensionModel(extensionModel);
		}

		Element extensionConceptChild = element.element("extensionConcept");
		if (extensionConceptChild != null) {
			String extensionConcept = extensionConceptChild.getText().trim();
			conceptConfig.setExtensionConcept(extensionConcept);
		}

		Element entitiesCodeChild = element.element("entitiesCode");
		if (entitiesCodeChild != null) {
			String entitiesCode = entitiesCodeChild.getText().trim();
			conceptConfig.setEntitiesCode(entitiesCode);
		}

		Element packageCodeChild = element.element("packageCode");
		if (packageCodeChild != null) {
			String packageCode = packageCodeChild.getText().trim();
			conceptConfig.setPackageCode(packageCode);
		}

		Element minChild = element.element("min");
		if (minChild != null) {
			String min = minChild.getText().trim();
			conceptConfig.setMin(min);
		}

		Element maxChild = element.element("max");
		if (maxChild != null) {
			String max = maxChild.getText().trim();
			conceptConfig.setMax(max);
		}

		Element entryChild = element.element("entry");
		if (entryChild != null) {
			String entry = entryChild.getText().trim();
			conceptConfig.setEntry(entry);
		}

		Element fileNameChild = element.element("fileName");
		if (fileNameChild != null) {
			String fileName = fileNameChild.getText().trim();
			conceptConfig.setFileName(fileName);
		}

		Element indexChild = element.element("index");
		if (indexChild != null) {
			String index = indexChild.getText().trim();
			conceptConfig.setIndex(index);
		}

		Element displayChild = element.element("display");
		if (displayChild != null) {
			String display = displayChild.getText().trim();
			conceptConfig.setDisplay(display);
		}

		Element displayTypeChild = element.element("displayType");
		if (displayTypeChild != null) {
			String displayType = displayTypeChild.getText().trim();
			conceptConfig.setDisplayType(displayType);
		}

		Element addChild = element.element("add");
		if (addChild != null) {
			String add = addChild.getText().trim();
			conceptConfig.setAdd(add);
		}

		Element updateChild = element.element("update");
		if (updateChild != null) {
			String update = updateChild.getText().trim();
			conceptConfig.setUpdate(update);
		}

		Element removeChild = element.element("remove");
		if (removeChild != null) {
			String remove = removeChild.getText().trim();
			conceptConfig.setRemove(remove);
		}

		Element propertiesChild = element.element("properties");
		if (propertiesChild != null) {
			PropertiesConfig propertiesConfig = new PropertiesConfig();
			conceptConfig.setPropertiesConfig(propertiesConfig);
			propertiesConfig.getXmlProperties().load(propertiesChild);
		}

		Element neighborsChild = element.element("neighbors");
		if (neighborsChild != null) {
			NeighborsConfig neighborsConfig = new NeighborsConfig();
			conceptConfig.setNeighborsConfig(neighborsConfig);
			neighborsConfig.getXmlNeighbors().load(neighborsChild);
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
				"A concept configuration cannot be saved.");
	}

}