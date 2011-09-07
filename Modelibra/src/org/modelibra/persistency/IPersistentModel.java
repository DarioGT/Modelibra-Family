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
import java.util.Observer;

import org.modelibra.IDomainModel;

/**
 * IPersistentModel interface to represent a persistent domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public interface IPersistentModel extends Serializable, Observer {

	/**
	 * Gets the domain model.
	 * 
	 * @return domain model
	 */
	public IDomainModel getModel();

	/**
	 * Gets a persistent entry for the given entry code.
	 * 
	 * @param entryCode
	 *            entry concept entity code or entry concept entities code
	 * @return persistent entities
	 */
	public IPersistentEntities getPersistentEntry(String entryCode);

	/**
	 * Checks if the domain model is loaded.
	 * 
	 * @return <code>true</code> if the domain model is loaded
	 */
	public boolean isLoaded();

	/**
	 * Loads the persistent model.
	 */
	public void load();

	/**
	 * Saves the persistent model.
	 */
	public void save();

	/**
	 * Closes the persistent model.
	 */
	public void close();

}