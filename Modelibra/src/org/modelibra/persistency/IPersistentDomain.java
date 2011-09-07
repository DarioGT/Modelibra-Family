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
package org.modelibra.persistency;

import java.io.Serializable;

import org.modelibra.IDomain;

/**
 * IPersistentDomain interface to represent a domain of persistent models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public interface IPersistentDomain extends Serializable {

	/**
	 * Gets the domain.
	 * 
	 * @return domain
	 */
	public IDomain getDomain();

	/**
	 * Gets the persistent models.
	 * 
	 * @return persistent models
	 */
	public IPersistentModels getPersistentModels();

	/**
	 * Loads the persistent domain.
	 */
	public void load();

	/**
	 * Saves the persistent domain.
	 */
	public void save();

	/**
	 * Closes the persistent domain.
	 */
	public void close();

}
