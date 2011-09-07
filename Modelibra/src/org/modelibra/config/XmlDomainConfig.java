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
 * XML domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlDomainConfig extends XmlEntity {

	private static final long serialVersionUID = 2130L;

	private DomainConfig domainConfig;

	/**
	 * Constructs an XML domain configuration.
	 */
	public XmlDomainConfig(DomainConfig domainConfig) {
		super(domainConfig, null);
		this.domainConfig = domainConfig;
	}

	/**
	 * Loads an XML domain element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element typeChild = element.element("type");
		if (typeChild != null) {
			String type = typeChild.getText().trim();
			domainConfig.setType(type);
		}

		Element abstractionChild = element.element("abstraction");
		if (abstractionChild != null) {
			String abstraction = abstractionChild.getText().trim();
			domainConfig.setAbstraction(abstraction);
		}

		Element defaultConstructChild = element.element("defaultConstruct");
		if (defaultConstructChild != null) {
			String defaultConstruct = defaultConstructChild.getText().trim();
			domainConfig.setDefaultConstruct(defaultConstruct);
		}

		Element packagePrefixChild = element.element("packagePrefix");
		if (packagePrefixChild != null) {
			String packagePrefix = packagePrefixChild.getText().trim();
			domainConfig.setPackagePrefix(packagePrefix);
		}

		Element packageCodeChild = element.element("packageCode");
		if (packageCodeChild != null) {
			String packageCode = packageCodeChild.getText().trim();
			domainConfig.setPackageCode(packageCode);
		}

		Element referenceModelChild = element.element("referenceModel");
		if (referenceModelChild != null) {
			String referenceModel = referenceModelChild.getText().trim();
			domainConfig.setReferenceModel(referenceModel);
		}

		Element i18nChild = element.element("i18n");
		if (i18nChild != null) {
			String i18n = i18nChild.getText().trim();
			domainConfig.setI18n(i18n);
		}

		Element signinChild = element.element("signin");
		if (signinChild != null) {
			String signin = signinChild.getText().trim();
			domainConfig.setSignin(signin);
		}

		Element signinConceptChild = element.element("signinConcept");
		if (signinConceptChild != null) {
			String signinConcept = signinConceptChild.getText().trim();
			domainConfig.setSigninConcept(signinConcept);
		}

		Element shortTextDefaultLengthChild = element
				.element("shortTextDefaultLength");
		if (shortTextDefaultLengthChild != null) {
			String shortTextDefaultLength = shortTextDefaultLengthChild
					.getText().trim();
			domainConfig.setShortTextDefaultLength(shortTextDefaultLength);
		}

		Element pageBlockDefaultSizeChild = element
				.element("pageBlockDefaultSize");
		if (pageBlockDefaultSizeChild != null) {
			String pageBlockDefaultSize = pageBlockDefaultSizeChild.getText()
					.trim();
			domainConfig.setPageBlockDefaultSize(pageBlockDefaultSize);
		}

		Element validateFormChild = element.element("validateForm");
		if (validateFormChild != null) {
			String validateForm = validateFormChild.getText().trim();
			domainConfig.setValidateForm(validateForm);
		}

		Element confirmRemoveChild = element.element("confirmRemove");
		if (confirmRemoveChild != null) {
			String confirmRemove = confirmRemoveChild.getText().trim();
			domainConfig.setConfirmRemove(confirmRemove);
		}

		Element modelsChild = element.element("models");
		if (modelsChild != null) {
			ModelsConfig modelsConfig = new ModelsConfig();
			domainConfig.setModelsConfig(modelsConfig);
			modelsConfig.getXmlModels().load(modelsChild);
		}
	}

	/**
	 * Fills an XML domain element (no action).
	 * 
	 * @param element
	 *            XML element
	 */
	protected void fill(Element element) {
		throw new PersistencyRuntimeException(
				"A domain configuration cannot be saved.");
	}

}