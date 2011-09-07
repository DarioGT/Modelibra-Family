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

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.exception.ModelibraRuntimeException;

/**
 * Domain: a group of domain models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-22
 */
public class Domain implements IDomain {

	private static final long serialVersionUID = 1010L;

	private ModelMeta modelMeta = new ModelMeta(this);

	private DomainConfig domainConfig;

	private IDomainModels models;

	/**
	 * Constructs the domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public Domain(DomainConfig domainConfig) {
		super();
		this.domainConfig = domainConfig;
		if (domainConfig == null) {
			throw new ConfigRuntimeException(
					"Domain.constructor -- domain configuration is null.");
		}
		if (domainConfig.isAbstraction()) {
			String msg = "Domain.constructor -- domain is configured as an abstraction: "
					+ domainConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		models = new DomainModels();
		if (domainConfig.isDefaultConstruct()) {
			createModels();
		}
	}

	/**
	 * Creates a domain model.
	 * 
	 * @param modelConfig
	 *            model configuration
	 * @return domain model
	 */
	private IDomainModel createModel(ModelConfig modelConfig) {
		IDomainModel model = null;
		String modelClassName = modelConfig.getModelClass();
		String domainCode = getDomainConfig().getCode();
		model = modelMeta.createModel(domainCode, modelClassName);
		if (model == null) {
			String msg = "Domain.createModel -- domain model could not be created: "
					+ domainCode + "." + modelConfig.getCode();
			throw new ModelibraRuntimeException(msg);
		} else {
			models.add(model);
		}
		return model;
	}

	/**
	 * Creates domain models.
	 */
	private void createModels() {
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			createModel(modelConfig);
		}
	}

	/**
	 * Gets the model meta.
	 * 
	 * @return model meta
	 */
	public ModelMeta getModelMeta() {
		return modelMeta;
	}

	/**
	 * Gets the domain code.
	 * 
	 * @return domain code
	 */
	public String getCode() {
		return domainConfig.getCode();
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
	 * Gets a domain model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return domain model
	 */
	public IDomainModel getModel(String modelCode) {
		return models.getModel(modelCode);
	}

	/**
	 * Gets the reference model.
	 * 
	 * @return reference model
	 */
	public IDomainModel getReferenceModel() {
		String referenceModelCode = getDomainConfig().getReferenceModel();
		return getModel(referenceModelCode);
	}

	/**
	 * Gets domain models.
	 * 
	 * @return domain models
	 */
	public IDomainModels getModels() {
		return models;
	}

	/**
	 * Closes the domain.
	 */
	public void close() {
		models.close();
	}

}
