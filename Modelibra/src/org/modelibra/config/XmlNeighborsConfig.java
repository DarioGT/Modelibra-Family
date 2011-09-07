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
 * XML concept neighbors configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class XmlNeighborsConfig extends XmlEntities<NeighborConfig> {

	private static final long serialVersionUID = 2180L;

	private NeighborsConfig neighborsConfig;

	/**
	 * Constructs an XML neighbor configurations object.
	 */
	public XmlNeighborsConfig(NeighborsConfig neighborsConfig) {
		super(neighborsConfig, null);
		this.neighborsConfig = neighborsConfig;
	}

	/**
	 * Loads the neighbor root element.
	 * 
	 * @param root
	 *            root element
	 */
	public void load(Element root) {
		// Iterate through child elements of the root.
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element conceptElement = (Element) i.next();
			NeighborConfig neighborConfig = new NeighborConfig();
			neighborConfig.getXmlNeighbor().load(conceptElement);
			neighborsConfig.add(neighborConfig);
		}
	}

	/**
	 * Saves XML neighbor configurations to an XML file (no action).
	 */
	public void save() {

	}

}