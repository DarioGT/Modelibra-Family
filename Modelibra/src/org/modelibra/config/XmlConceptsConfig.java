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
 * XML model concepts configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class XmlConceptsConfig extends XmlEntities<ConceptConfig> {

	private static final long serialVersionUID = 2120L;

	private ConceptsConfig conceptsConfig;

	/**
	 * Constructs an XML concept configurations object.
	 */
	public XmlConceptsConfig(ConceptsConfig conceptsConfig) {
		super(conceptsConfig, null);
		this.conceptsConfig = conceptsConfig;
	}

	/**
	 * Loads the concept root element.
	 * 
	 * @param root
	 *            root element
	 */
	public void load(Element root) {
		// Iterate through child elements of the root.
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element conceptElement = (Element) i.next();
			ConceptConfig conceptConfig = new ConceptConfig();
			conceptConfig.getXmlConcept().load(conceptElement);
			conceptsConfig.add(conceptConfig);
		}
	}

	/**
	 * Saves XML concept configurations to an XML file (no action).
	 */
	public void save() {

	}

}