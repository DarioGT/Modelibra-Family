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
package dm.meta.concept;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

import dm.meta.model.Model;

/**
 * Concept entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class Concepts extends Entities<Concept> {

	private static final long serialVersionUID = 110110131L;

	// Model parent neighbor (internal)
	private Model model;

	/**
	 * Constructs concepts within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Concepts(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Constructs concepts for the parent model.
	 * 
	 * @param model
	 *            model
	 */
	public Concepts(Model model) {
		this(model.getModel());
		// parent
		this.model = model;
	}

	/**
	 * Gets a concept.
	 * 
	 * @param code
	 *            code
	 * @return concept
	 */
	public Concept getConcept(String code) {
		return retrieveByCode(code);
	}

	/**
	 * Gets concepts ordered by code.
	 * 
	 * @return ordered concepts
	 */
	public Concepts getConceptsOrderedByCode() {
		return (Concepts) orderByCode();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets a model.
	 * 
	 * @param model
	 *            model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Gets a model.
	 * 
	 * @return model
	 */
	public Model getDomainModel() {
		return model;
	}

}