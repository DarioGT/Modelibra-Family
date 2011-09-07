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

import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.config.ModelConfig;
import org.modelibra.persistency.xml.XmlEntities;

/**
 * Persistent entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-01
 */
public class PersistentEntities implements IPersistentEntities {

	private static final long serialVersionUID = 4020L;

	private IPersistentEntities storeEntities;

	/**
	 * Constructs persistent entities from given entities, within the persistent
	 * model.
	 * 
	 * @param entities
	 *            entities
	 * @param persistentModel
	 *            persistent model
	 */
	public PersistentEntities(IEntities<?> entities,
			IPersistentModel persistentModel) {
		ModelConfig modelConfig = persistentModel.getModel().getModelConfig();
		if (modelConfig.isPersistent()) {
			if (modelConfig.getPersistenceType().equalsIgnoreCase("xml")) {
				storeEntities = new XmlEntities(entities, persistentModel);
			}
		}
	}

	/**
	 * Gets persistent store entities.
	 * 
	 * @return persistent store entities
	 */
	public IPersistentEntities getStoreEntities() {
		return storeEntities;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return storeEntities.getPersistentModel();
	}

	/**
	 * Gets the entities.
	 * 
	 * @return entities
	 */
	public IEntities<?> getEntities() {
		return storeEntities.getEntities();
	}

	/**
	 * Loads the entities.
	 */
	public void load() {
		Entities<?> entities = (Entities<?>) getEntities();
		if (entities.isPersistent()) {
			storeEntities.load();
		}
	}

	/**
	 * Saves the entities.
	 */
	public void save() {
		Entities<?> entities = (Entities<?>) getEntities();
		if (entities.isPersistent()) {
			storeEntities.save();
		}
	}

}