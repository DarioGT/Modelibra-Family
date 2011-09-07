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
package org.modelibra.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;

/**
 * Collection of model configurations.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class ModelsConfig extends Entities<ModelConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 2060L;

	private static Log log = LogFactory.getLog(ModelsConfig.class);

	private DomainConfig domainConfig; // parent

	private XmlModelsConfig xmlModels;

	/**
	 * Constructs the models configuration.
	 */
	public ModelsConfig() {
		super();
		xmlModels = new XmlModelsConfig(this);
	}

	/**
	 * Sets the domain configuration.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public void setDomainConfig(DomainConfig domainConfig) {
		this.domainConfig = domainConfig;
	}

	/**
	 * Gets the domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

	/**
	 * Gets the model configuration based on a model configuration code.
	 * 
	 * @param modelCode
	 *            model code
	 * @return model configuration
	 */
	public ModelConfig getModelConfig(String modelCode) {
		return (ModelConfig) retrieveByCode(modelCode);
	}

	/**
	 * Checks if the new model code is unique within the dm configuration.
	 * 
	 * @param newModelConfig
	 *            model configuration entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(ModelConfig newModelConfig) {
		boolean validation = true;
		String newModelCode = newModelConfig.getCode();
		if (newModelCode != null) {
			for (ModelConfig modelConfig : this) {
				if (modelConfig.getCode().equals(newModelCode)) {
					validation = false;
					log
							.info(newModelCode
									+ " model code must be unique within the dm configuration.");
					break;
				}
			}
		} else {
			validation = false;
			log.info("Model code is required.");
		}
		return validation;
	}

	/**
	 * Sets a new model configuration.
	 * 
	 * @param modelConfig
	 *            model configuration entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(ModelConfig modelConfig) {
		modelConfig.setDomainConfig(domainConfig);
		return true;
	}

	/**
	 * Outputs the models configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		List<ModelConfig> list = getList();
		log.info("*** " + title + " ***");
		for (ModelConfig modelConfig : list) {
			String classSimpleName = modelConfig.getClass().getSimpleName();
			modelConfig.output(classSimpleName);
		}
	}

	/**
	 * Gets XML models.
	 * 
	 * @return XML models
	 */
	public XmlModelsConfig getXmlModels() {
		return xmlModels;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlModels.getPersistentModel();
	}

	/**
	 * Gets the model entities.
	 * 
	 * @return model entities
	 */
	public IEntities<?> getEntities() {
		return xmlModels.getEntities();
	}

	/**
	 * Loads models.
	 */
	public void load() {
		xmlModels.load();
	}

	/**
	 * Saves models.
	 */
	public void save() {
		xmlModels.save();
	}

	/**
	 * Gets the list of model configurations.
	 * 
	 * @return list of model configurations
	 */
	public List<ModelConfig> getModelConfigList() {
		List<ModelConfig> modelConfigList = new ArrayList<ModelConfig>();
		for (ModelConfig modelConfig : getList()) {
			modelConfigList.add(modelConfig);
		}
		return modelConfigList;
	}

}
