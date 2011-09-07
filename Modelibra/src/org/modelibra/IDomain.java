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
package org.modelibra;

import java.io.Serializable;

import org.modelibra.config.DomainConfig;

/**
 * IDomain interface to represent a domain of models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public interface IDomain extends Serializable {

	/**
	 * Gets the domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig();

	/**
	 * Gets a domain model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return model
	 */
	public IDomainModel getModel(String modelCode);

	/**
	 * Gets the reference model.
	 * 
	 * @return reference model
	 */
	public IDomainModel getReferenceModel();

	/**
	 * Gets domain models.
	 * 
	 * @return domain models
	 */
	public IDomainModels getModels();

	/**
	 * Closes the domain.
	 */
	public void close();

}
