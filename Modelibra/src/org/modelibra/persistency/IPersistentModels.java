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

/**
 * IPersistentModels interface to represent persistent domain models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
public interface IPersistentModels extends Serializable {

	/**
	 * Adds a persistent model.
	 * 
	 * @param persistentModel
	 *            persistent model
	 */
	public void add(IPersistentModel persistentModel);

	/**
	 * Gets the persistent model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel(String modelCode);

	/**
	 * Loads the persistent models if they are not configured for the default
	 * load.
	 * 
	 * @throws persistency
	 *             exception if there is a problem
	 */
	public void load();

	/**
	 * Saves the persistent models if they are not configured for the default
	 * save.
	 * 
	 * @throws persistency
	 *             exception if there is a problem
	 */
	public void save();

	/**
	 * Closes the persistent models.
	 */
	public void close();

}