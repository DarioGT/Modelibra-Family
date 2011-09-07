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
package dm.meta.model;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

import dm.meta.domain.Domain;

/**
 * Model entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public class Models extends Entities<Model> {

	private static final long serialVersionUID = 110110121L;

	// Domain parent neighbor (internal)
	private Domain domain;

	/**
	 * Constructs models within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Models(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Constructs models for the parent domain.
	 * 
	 * @param model
	 *            model
	 */
	public Models(Domain domain) {
		this(domain.getModel());
		// parent
		this.domain = domain;
	}

	/**
	 * Gets a model.
	 * 
	 * @param code
	 *            code
	 * @return model
	 */
	public Model getModel(String code) {
		return retrieveByCode(code);
	}

	/**
	 * Gets models ordered by code.
	 * 
	 * @return ordered models
	 */
	public Models getModelsOrderedByCode() {
		return (Models) orderByCode();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets a domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	/**
	 * Gets a domain.
	 * 
	 * @return domain
	 */
	public Domain getDomain() {
		return domain;
	}

}