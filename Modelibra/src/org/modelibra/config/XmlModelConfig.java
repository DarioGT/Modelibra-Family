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
 * XML model configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlModelConfig extends XmlEntity {

	private static final long serialVersionUID = 2150L;

	private ModelConfig modelConfig;

	/**
	 * Constructs an XML model configuration.
	 */
	public XmlModelConfig(ModelConfig modelConfig) {
		super(modelConfig, null);
		this.modelConfig = modelConfig;
	}

	/**
	 * Loads an XML model element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element abstractionChild = element.element("abstraction");
		if (abstractionChild != null) {
			String abstraction = abstractionChild.getText().trim();
			modelConfig.setAbstraction(abstraction);
		}

		Element extensionChild = element.element("extension");
		if (extensionChild != null) {
			String extension = extensionChild.getText().trim();
			modelConfig.setExtension(extension);
		}

		Element extensionDomainChild = element.element("extensionDomain");
		if (extensionDomainChild != null) {
			String extensionDomain = extensionDomainChild.getText().trim();
			modelConfig.setExtensionDomain(extensionDomain);
		}

		Element extensionDomainTypeChild = element
				.element("extensionDomainType");
		if (extensionDomainTypeChild != null) {
			String extensionDomainType = extensionDomainTypeChild.getText()
					.trim();
			modelConfig.setExtensionDomainType(extensionDomainType);
		}

		Element extensionModelChild = element.element("extensionModel");
		if (extensionModelChild != null) {
			String extensionModel = extensionModelChild.getText().trim();
			modelConfig.setExtensionModel(extensionModel);
		}

		Element authorChild = element.element("author");
		if (authorChild != null) {
			String author = authorChild.getText().trim();
			modelConfig.setAuthor(author);
		}

		Element packageCodeChild = element.element("packageCode");
		if (packageCodeChild != null) {
			String packageCode = packageCodeChild.getText().trim();
			modelConfig.setPackageCode(packageCode);
		}

		Element persistentChild = element.element("persistent");
		if (persistentChild != null) {
			String persistent = persistentChild.getText().trim();
			modelConfig.setPersistent(persistent);
		}

		Element persistenceTypeChild = element.element("persistenceType");
		if (persistenceTypeChild != null) {
			String persistenceType = persistenceTypeChild.getText().trim();
			modelConfig.setPersistenceType(persistenceType);
		}

		Element persistenceRelativePathChild = element
				.element("persistenceRelativePath");
		if (persistenceRelativePathChild != null) {
			String persistenceRelativePath = persistenceRelativePathChild
					.getText().trim();
			modelConfig.setPersistenceRelativePath(persistenceRelativePath);
		}

		Element persistenceConfigChild = element.element("persistenceConfig");
		if (persistenceConfigChild != null) {
			String persistenceConfig = persistenceConfigChild.getText().trim();
			modelConfig.setPersistenceConfig(persistenceConfig);
		}

		Element defaultLoadSaveChild = element.element("defaultLoadSave");
		if (defaultLoadSaveChild != null) {
			String defaultLoadSave = defaultLoadSaveChild.getText().trim();
			modelConfig.setDefaultLoadSave(defaultLoadSave);
		}

		Element datePatternChild = element.element("datePattern");
		if (datePatternChild != null) {
			String datePattern = datePatternChild.getText().trim();
			modelConfig.setDatePattern(datePattern);
		}

		Element sessionChild = element.element("session");
		if (sessionChild != null) {
			String session = sessionChild.getText().trim();
			modelConfig.setSession(session);
		}

		Element conceptsChild = element.element("concepts");
		if (conceptsChild != null) {
			ConceptsConfig conceptsConfig = new ConceptsConfig();
			modelConfig.setConceptsConfig(conceptsConfig);
			conceptsConfig.getXmlConcepts().load(conceptsChild);
		}
	}

	/**
	 * Fills an XML model element (no action).
	 * 
	 * @param element
	 *            XML element
	 */
	protected void fill(Element element) {
		throw new PersistencyRuntimeException("A model configuration cannot be saved.");
	}

}