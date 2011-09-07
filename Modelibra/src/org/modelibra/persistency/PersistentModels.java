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

import java.util.ArrayList;
import java.util.List;

/**
 * Persistent domain models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class PersistentModels implements IPersistentModels {

	private static final long serialVersionUID = 4050L;

	private List<IPersistentModel> persistentModels = new ArrayList<IPersistentModel>();

	/**
	 * Constructs a container for persistent models.
	 */
	public PersistentModels() {
		super();
	}

	/**
	 * Adds a persistent model.
	 * 
	 * @param persistentModel
	 *            persistent model
	 */
	public void add(IPersistentModel persistentModel) {
		persistentModels.add(persistentModel);
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel(String modelCode) {
		for (IPersistentModel persistentModel : persistentModels) {
			if (persistentModel.getModel().getModelConfig().getCode().equals(
					modelCode)) {
				return persistentModel;
			}
		}
		return null;
	}

	/**
	 * Loads the persistent models if they are not configured for the default
	 * load.
	 */
	public void load() {
		for (IPersistentModel persistentModel : persistentModels) {
			if (!persistentModel.getModel().getModelConfig()
					.isDefaultLoadSave()) {
				persistentModel.load();
			}
		}
	}

	/**
	 * Saves the persistent models if they are not configured for the default
	 * save.
	 */
	public void save() {
		for (IPersistentModel persistentModel : persistentModels) {
			if (!persistentModel.getModel().getModelConfig()
					.isDefaultLoadSave()) {
				persistentModel.save();
			}
		}
	}

	/**
	 * Closes the persistent models.
	 */
	public void close() {
		for (IPersistentModel persistentModel : persistentModels) {
			persistentModel.close();
		}
	}

}
