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

import org.modelibra.IEntity;
import org.modelibra.config.ModelConfig;
import org.modelibra.persistency.xml.XmlEntity;

/**
 * Persistent entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class PersistentEntity implements IPersistentEntity {

	private static final long serialVersionUID = 4030L;

	private IPersistentEntity storeEntity;

	/**
	 * Constructs a persistent entity from a given entity, within the persistent
	 * model.
	 * 
	 * @param entity
	 *            entity
	 * @param persistentModel
	 *            persistent model
	 */
	public PersistentEntity(IEntity<?> entity, IPersistentModel persistentModel) {
		ModelConfig modelConfig = persistentModel.getModel().getModelConfig();
		if (modelConfig.isPersistent()) {
			if (modelConfig.getPersistenceType().equalsIgnoreCase("xml")) {
				storeEntity = new XmlEntity(entity, persistentModel);
			}
		}
	}

	/**
	 * Gets a persistent store entity.
	 * 
	 * @return persistent store entity
	 */
	public IPersistentEntity getStoreEntity() {
		return storeEntity;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return storeEntity.getPersistentModel();
	}

	/**
	 * Gets the entity.
	 * 
	 * @return entity
	 */
	public IEntity<?> getEntity() {
		return storeEntity.getEntity();
	}

}