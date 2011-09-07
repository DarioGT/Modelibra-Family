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

import java.util.Iterator;

import org.dom4j.Element;
import org.modelibra.persistency.xml.XmlEntities;

/**
 * XML models configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class XmlModelsConfig extends XmlEntities<ModelConfig> {

	private static final long serialVersionUID = 2160L;

	private ModelsConfig modelsConfig;

	/**
	 * Constructs an XML model configurations object.
	 */
	public XmlModelsConfig(ModelsConfig modelsConfig) {
		super(modelsConfig, null);
		this.modelsConfig = modelsConfig;
	}

	/**
	 * Loads the model root element.
	 * 
	 * @param root
	 *            root element
	 */
	public void load(Element root) {
		// Iterate through child elements of the root.
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element conceptElement = (Element) i.next();
			ModelConfig modelConfig = new ModelConfig();
			modelConfig.getXmlModel().load(conceptElement);
			modelsConfig.add(modelConfig);
		}
	}

	/**
	 * Saves XML model configurations to an XML file (no action).
	 */
	public void save() {

	}

}