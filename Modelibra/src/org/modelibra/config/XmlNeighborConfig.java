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
 * XML concept neighbor configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlNeighborConfig extends XmlEntity {

	private static final long serialVersionUID = 2170L;

	private NeighborConfig neighborConfig;

	/**
	 * Constructs an XML concept neighbor configuration.
	 */
	public XmlNeighborConfig(NeighborConfig neighborConfig) {
		super(neighborConfig, null);
		this.neighborConfig = neighborConfig;
	}

	/**
	 * Loads an XML concept neighbor element.
	 * 
	 * @param element
	 *            an XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element extensionChild = element.element("extension");
		if (extensionChild != null) {
			String extension = extensionChild.getText().trim();
			neighborConfig.setExtension(extension);
		}

		Element extensionNeighborChild = element.element("extensionNeighbor");
		if (extensionNeighborChild != null) {
			String extensionNeighbor = extensionNeighborChild.getText().trim();
			neighborConfig.setExtensionNeighbor(extensionNeighbor);
		}

		Element destinationConceptChild = element.element("destinationConcept");
		if (destinationConceptChild != null) {
			String destinationConcept = destinationConceptChild.getText()
					.trim();
			neighborConfig.setDestinationConcept(destinationConcept);
		}

		Element inverseNeighborChild = element.element("inverseNeighbor");
		if (inverseNeighborChild != null) {
			String inverseNeighbor = inverseNeighborChild.getText().trim();
			neighborConfig.setInverseNeighbor(inverseNeighbor);
		}

		Element internalChild = element.element("internal");
		if (internalChild != null) {
			String internal = internalChild.getText().trim();
			neighborConfig.setInternal(internal);
		}

		Element partOfManyToManyChild = element.element("partOfManyToMany");
		if (partOfManyToManyChild != null) {
			String partOfManyToMany = partOfManyToManyChild.getText().trim();
			neighborConfig.setPartOfManyToMany(partOfManyToMany);
		}

		Element typeChild = element.element("type");
		if (typeChild != null) {
			String type = typeChild.getText().trim();
			neighborConfig.setType(type);
		}

		Element minChild = element.element("min");
		if (minChild != null) {
			String min = minChild.getText().trim();
			neighborConfig.setMin(min);
		}

		Element maxChild = element.element("max");
		if (maxChild != null) {
			String max = maxChild.getText().trim();
			neighborConfig.setMax(max);
		}

		Element uniqueChild = element.element("unique");
		if (uniqueChild != null) {
			String unique = uniqueChild.getText().trim();
			neighborConfig.setUnique(unique);
		}

		Element indexChild = element.element("index");
		if (indexChild != null) {
			String index = indexChild.getText().trim();
			neighborConfig.setIndex(index);
		}

		Element displayChild = element.element("display");
		if (displayChild != null) {
			String display = displayChild.getText().trim();
			neighborConfig.setDisplay(display);
		}

		Element updateChild = element.element("update");
		if (updateChild != null) {
			String update = updateChild.getText().trim();
			neighborConfig.setUpdate(update);
		}

		Element absorbChild = element.element("absorb");
		if (absorbChild != null) {
			String absorb = absorbChild.getText().trim();
			neighborConfig.setAbsorb(absorb);
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
				"A neighbor configuration cannot be saved.");
	}

}