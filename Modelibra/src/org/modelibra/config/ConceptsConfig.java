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
 * Collection of model concept configurations.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class ConceptsConfig extends Entities<ConceptConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 2020L;

	private static Log log = LogFactory.getLog(ConceptsConfig.class);

	private ModelConfig modelConfig; // parent

	private XmlConceptsConfig xmlConcepts;

	/**
	 * Constructs the model concepts configuration.
	 */
	public ConceptsConfig() {
		super();
		xmlConcepts = new XmlConceptsConfig(this);
	}

	/**
	 * Sets the model configuration.
	 * 
	 * @param modelConfig
	 *            model configuration
	 */
	public void setModelConfig(ModelConfig modelConfig) {
		this.modelConfig = modelConfig;
	}

	/**
	 * Gets the model configuration.
	 * 
	 * @return model configuration
	 */
	public ModelConfig getModelConfig() {
		return modelConfig;
	}

	/**
	 * Gets the concept configuration for a given concept code (or concepts
	 * code).
	 * 
	 * @param conceptCode
	 *            concept code
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig(String conceptCode) {
		ConceptConfig resultConceptConfig = (ConceptConfig) retrieveByCode(conceptCode);
		if (resultConceptConfig == null) {
			for (ConceptConfig conceptConfig : this) {
				String entitiesCode = conceptConfig.getEntitiesCode();
				if (entitiesCode != null && entitiesCode.equals(conceptCode)) {
					resultConceptConfig = conceptConfig;
				}
			}
		}
		return resultConceptConfig;
	}

	/**
	 * Checks if the new concept code is unique within the model.
	 * 
	 * @param newConceptConfig
	 *            concept configuration entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(ConceptConfig newConceptConfig) {
		boolean validation = true;
		String newConceptCode = newConceptConfig.getCode();
		if (newConceptCode != null) {
			for (ConceptConfig conceptConfig : this) {
				if (conceptConfig.getCode().equals(newConceptCode)) {
					validation = false;
					log.info(newConceptCode
							+ " concept code must be unique within the model.");
					break;
				}
			}
		} else {
			validation = false;
			log.info("Concept code is required.");
		}
		return validation;
	}

	/**
	 * Sets the parent model configuration for a new concept configuration.
	 * 
	 * @param conceptConfig
	 *            concept configuration entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(ConceptConfig conceptConfig) {
		conceptConfig.setModelConfig(modelConfig);
		return true;
	}

	/**
	 * Outputs the concepts configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		List<ConceptConfig> list = getList();
		log.info("*** " + title + " ***");
		for (ConceptConfig conceptConfig : list) {
			String classSimpleName = conceptConfig.getClass().getSimpleName();
			conceptConfig.output(classSimpleName);
		}
	}

	/**
	 * Gets XML concepts.
	 * 
	 * @return XML concepts
	 */
	public XmlConceptsConfig getXmlConcepts() {
		return xmlConcepts;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlConcepts.getPersistentModel();
	}

	/**
	 * Gets the concept entities.
	 * 
	 * @return concept entities
	 */
	public IEntities<?> getEntities() {
		return xmlConcepts.getEntities();
	}

	/**
	 * Loads concepts.
	 */
	public void load() {
		xmlConcepts.load();
	}

	/**
	 * Saves concepts.
	 */
	public void save() {
		xmlConcepts.save();
	}

	/**
	 * Checks if there is any concept extension directly or indirectly through
	 * its attributes. Returns <code>false</code> if there is none.
	 * 
	 * @return <code>true</code> if there is at least one concept extension
	 *         directly or indirectly
	 */
	public boolean anyConceptExtension() {
		boolean result = false;
		for (ConceptConfig conceptConfig : this) {
			if (conceptConfig.isExtension()
					|| conceptConfig.anyAttributeExtension()) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Gets the list of concept configurations.
	 * 
	 * @return list of concept configurations
	 */
	public List<ConceptConfig> getConceptConfigList() {
		List<ConceptConfig> conceptConfigList = new ArrayList<ConceptConfig>();
		for (ConceptConfig conceptConfig : getList()) {
			conceptConfigList.add(conceptConfig);

		}
		return conceptConfigList;
	}

	/**
	 * Gets the list of entry concept configurations.
	 * 
	 * @return list of entry concept configurations
	 */
	public List<ConceptConfig> getEntryConceptConfigList() {
		List<ConceptConfig> entryConceptConfigList = new ArrayList<ConceptConfig>();
		for (ConceptConfig conceptConfig : getList()) {
			if (conceptConfig.isEntry()) {
				entryConceptConfigList.add(conceptConfig);
			}
		}
		return entryConceptConfigList;
	}

}
