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

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

import dm.meta.concept.Concept;

/**
 * Property entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class Properties extends Entities<Property> {

	private static final long serialVersionUID = 110110141L;

	// private static Log log = LogFactory.getLog(Properties.class);

	// Concept parent neighbor (internal)
	private Concept concept;

	/**
	 * Constructs concept properties within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Properties(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Constructs concept properties for the parent concept.
	 * 
	 * @param concept
	 *            concept
	 */
	public Properties(Concept concept) {
		this(concept.getModel());
		// parent
		this.concept = concept;
	}

	/**
	 * Gets a property.
	 * 
	 * @param code
	 *            code
	 * @return property
	 */
	public Property getProperty(String code) {
		return retrieveByCode(code);
	}

	/**
	 * Gets properties ordered by code.
	 * 
	 * @return properties
	 */
	public Properties getPropertiesOrderedByCode() {
		return (Properties) orderByCode();
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