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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.IDomainModel;
import org.modelibra.ModelMeta;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.xml.XmlModel;

/**
 * A group of persistent domain models.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-09-01
 */
public class PersistentDomain implements IPersistentDomain {

	private static final long serialVersionUID = 4010L;

	private static Log log = LogFactory.getLog(PersistentDomain.class);

	private IDomain domain;

	private IPersistentModels persistentModels;

	/**
	 * Constructs the persistent domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public PersistentDomain(DomainConfig domainConfig) {
		super();
		ModelMeta modelMeta = new ModelMeta(domainConfig);
		this.domain = modelMeta.createDomain(domainConfig.getDomainClass());
		createPersistentModels();
	}

	/**
	 * Constructs the persistent domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public PersistentDomain(IDomain domain) {
		super();
		this.domain = domain;
		createPersistentModels();
	}

	/**
	 * Creates a persistent model.
	 * 
	 * @param model
	 *            domain model
	 * @return persistent model
	 */
	private IPersistentModel createPersistentModel(IDomainModel model) {
		IPersistentModel persistentModel = null;
		ModelConfig modelConfig = model.getModelConfig();
		String dataPath = domain.getDomainConfig().getConfig()
				.getDataDirectoryPath(modelConfig);
		String modelCode = modelConfig.getCode();
		if (modelConfig.getPersistenceType().equals("xml")) {
			log.info(modelCode + " domain model with xml files.");
			persistentModel = new XmlModel(model, dataPath);
		}
		return persistentModel;
	}

	/**
	 * Creates persistent models.
	 */
	private void createPersistentModels() {
		persistentModels = new PersistentModels();
		List<IDomainModel> modelList = domain.getModels().getList();
		for (IDomainModel model : modelList) {
			IPersistentModel persistentModel = createPersistentModel(model);
			if (persistentModel != null) {
				persistentModels.add(persistentModel);
			} else {
				throw new PersistencyRuntimeException(model.getModelConfig()
						.getCode()
						+ " persistent model could not be created");
			}
		}
	}

	/**
	 * Gets the domain.
	 * 
	 * @return domain
	 */
	public IDomain getDomain() {
		return domain;
	}

	/**
	 * Gets the persistent models.
	 * 
	 * @return persistent models
	 */
	public IPersistentModels getPersistentModels() {
		return persistentModels;
	}

	/**
	 * Loads the persistent domain.
	 */
	public void load() {
		persistentModels.load();
	}

	/**
	 * Saves the persistent domain.
	 */
	public void save() {
		persistentModels.save();
	}

	/**
	 * Closes the persistent domain.
	 */
	public void close() {
		persistentModels.close();
		domain.close();
	}

}
