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

import org.modelibra.IEntities;

/**
 * IPersistentEntities interface to represent persistent entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public interface IPersistentEntities extends Serializable {

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel();

	/**
	 * Gets the entities.
	 * 
	 * @return entities
	 */
	public IEntities<?> getEntities();

	/**
	 * Loads the entities.
	 */
	public void load();

	/**
	 * Saves the entities.
	 */
	public void save();

}