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
package org.modelibra.wicket.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.app.DomainApp;

/**
 * Obtaining localized text for elements of the domain model.
 * 
 * @author Sherif Behna
 * @author Kirlic Vedad
 * @version 2007-09-12
 */
public class LocalizedText {

	private static Log log = LogFactory.getLog(LocalizedText.class);

	private static final ClassStringResourceLoader classStringResourceLoader = (ClassStringResourceLoader) Application
			.get().getResourceSettings().getStringResourceLoaders().get(1);

	/**
	 * Obtains the localized model name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param model
	 *            the model
	 * @return the model name
	 */
	public static String getModelName(Component comp, IDomainModel model) {
		ModelConfig modelConfig = model.getModelConfig();
		String modelKey = modelConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, modelKey);
	}

	/**
	 * Obtains the localized model description.
	 * 
	 * @param comp
	 *            the component requesting the description
	 * @param model
	 *            the model
	 * @return the model description.
	 */
	public static String getModelDescription(Component comp, IDomainModel model) {
		ModelConfig modelConfig = model.getModelConfig();
		String modelDescriptionKey = modelConfig.getCode() + ".description";

		return LocalizedText.getApplicationPropertiesText(comp,
				modelDescriptionKey);
	}

	/**
	 * Obtains the localized domain name.
	 * 
	 * @param comp
	 *            the component requesting the description
	 * @return the domain name
	 */
	public static String getDomainName(Component comp) {
		DomainApp app = (DomainApp) comp.getApplication();
		String domainKey = app.getDomain().getDomainConfig().getCode();

		return LocalizedText.getApplicationPropertiesText(comp, domainKey);
	}

	/**
	 * Obtains the localized domain title.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @return the domain title
	 */
	public static String getDomainTitle(Component comp) {
		DomainApp app = (DomainApp) comp.getApplication();
		String domainCode = app.getDomain().getDomainConfig().getCode();
		String domainTitleKey = domainCode + ".title";

		return LocalizedText.getApplicationPropertiesText(comp, domainTitleKey);
	}

	/**
	 * Obtains the localized concept name.
	 * 
	 * @param comp
	 *            the component requesting the name.
	 * @param entity
	 *            An {@link IEntity} instance of the concept.
	 * @return the concept name
	 */
	public static String getConceptName(Component comp, IEntity<?> entity) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		String conceptKey = conceptConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, conceptKey);
	}

	/**
	 * Obtains the localized concept name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param entities
	 *            the concept's {@link IEntities} instance.
	 * @return the concept name
	 */
	public static String getConceptName(Component comp, IEntities<?> entities) {
		ConceptConfig conceptConfig = entities.getConceptConfig();
		String conceptKey = conceptConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, conceptKey);
	}

	/**
	 * Obtains the localized concepts (plural concept) name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param entities
	 * @return the plural concept name
	 */
	public static String getConceptsName(Component comp, IEntities<?> entities) {
		ConceptConfig conceptConfig = entities.getConceptConfig();
		String conceptsKey = conceptConfig.getEntitiesCode();

		return LocalizedText.getApplicationPropertiesText(comp, conceptsKey);
	}

	/**
	 * Obtains the localized property name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param entity
	 * @param propertyConfig
	 * @return the property name
	 */
	public static String getPropertyName(Component comp, IEntity<?> entity,
			PropertyConfig propertyConfig) {
		String propertyKey = entity.getConceptConfig().getCode() + "."
				+ propertyConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, propertyKey);
	}

	/**
	 * Obtains the localized property name.
	 * 
	 * @param comp
	 *            The component requesting the name
	 * @param conceptConfig
	 * @param propertyConfig
	 * @return the property name
	 */
	public static String getPropertyName(Component comp,
			ConceptConfig conceptConfig, PropertyConfig propertyConfig) {
		String propertyKey = conceptConfig.getCode() + "."
				+ propertyConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, propertyKey);
	}

	/**
	 * Obtains the localized property name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param entities
	 * @param propertyConfig
	 * @return the property name
	 */
	public static String getPropertyName(Component comp, IEntities<?> entities,
			PropertyConfig propertyConfig) {
		String propertyKey = entities.getConceptConfig().getCode() + "."
				+ propertyConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, propertyKey);
	}

	/**
	 * Obtains the localized neighbor name.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @param entity
	 * @param neighborConfig
	 * @return the neighbor's name
	 */
	public static String getNeighborName(Component comp, IEntity<?> entity,
			NeighborConfig neighborConfig) {
		String neighborKey = entity.getConceptConfig().getCode() + "."
				+ neighborConfig.getCode();

		return LocalizedText.getApplicationPropertiesText(comp, neighborKey);
	}

	/**
	 * Obtains the localized sign-in error message.
	 * 
	 * @param comp
	 *            the component requesting the name
	 * @return the sign-in error message.
	 */
	public static String getSignInErrorMessage(Component comp) {
		return LocalizedText.getText(comp, "signinError");
	}

	/**
	 * Obtains the localized error message.
	 * 
	 * @param comp
	 *            The component requesting the name
	 * @param errorKey
	 *            error key
	 * @return the error message
	 */
	public static String getErrorMessage(Component comp, String errorKey) {
		return LocalizedText.getText(comp, errorKey);
	}

	/**
	 * Obtains a localized text by asking the component's localizer. If the text
	 * is not found, the method returns the text key. Do not use this method in
	 * component constructor.
	 * 
	 * @param comp
	 *            the component requesting the text
	 * @param textKey
	 * @return text
	 */
	public static String getText(Component comp, String textKey) {
		String text;
		text = comp.getLocalizer().getString(textKey, comp);
		return text;
	}

	/**
	 * Obtains a localized text from application properties. If the text is not
	 * found, the method returns the text key. Use this method if you need
	 * localized text in component constructor.
	 * 
	 * @param comp
	 *            the component requesting the text
	 * @param textKey
	 * @return text
	 */
	public static String getApplicationPropertiesText(Component comp,
			String textKey) {
		String text;
		text = classStringResourceLoader.loadStringResource(null, textKey, comp
				.getLocale(), comp.getStyle());
		return text;
	}

}
