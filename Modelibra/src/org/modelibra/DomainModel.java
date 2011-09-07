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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.util.OutTester;

/**
 * <p>
 * A domain model can be accessed only through one or more entries. An entry is
 * an object of the IEntities type. The model is implemented as a map of
 * entries. An entry key is the concept configuration and the entry value is an
 * object of the IEntities type.
 * </p>
 * 
 * <p>
 * The model has a configuration that comes from an XML configuration file. The
 * model configuration contains a configuration of the model concepts. There are
 * three model configuration files: specific-domain-config.xml,
 * reusable-domain-config.xml and modelibra-domain-config.xml. Predefined domain
 * configurations are located in the modelibra-domian-config.xml. They can be
 * inherited by the specific domain configuration through the reusable domain
 * configuration. The reusable domain configuration may have user defined
 * concepts. A domain model cannot be created without at least one domain
 * configuration and one model configuration.
 * </p>
 * 
 * <p>
 * The model changes can be observed by an object of the Observer type. An
 * observer is used to persist domain model data. However, the model does not
 * have any knowledge of a persistence mechanism. The model changes are reported
 * to the observer only after the model is initialized (e.g., loaded from a
 * persistence store).
 * </p>
 * 
 * <p>
 * The model meta handling is done by the model property of the ModelMeta type.
 * An example of meta handling is a creation of the concept entry entities given
 * the full (completed with the package name) class name.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-02-10
 */
public class DomainModel extends Observable implements IDomainModel {

	private static final long serialVersionUID = 1020L;

	private static Log log = LogFactory.getLog(DomainModel.class);

	private ModelMeta modelMeta = new ModelMeta(this);

	private IDomain domain;

	private ModelConfig modelConfig;

	private IDomainModel referenceModel = this;

	private Map<ConceptConfig, IEntities<?>> entryMap = new HashMap<ConceptConfig, IEntities<?>>();

	private boolean initialized = false;

	/**
	 * Constructs a domain model within the given domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public DomainModel(IDomain domain) {
		this.domain = domain;
		if (domain == null) {
			throw new ModelibraRuntimeException(
					"Domain.constructor -- domain is null.");
		}
		String modelCode = getClass().getSimpleName();
		modelConfig = domain.getDomainConfig().getModelConfig(
				modelCode);
		if (modelConfig == null) {
			String msg = "Model.constructor -- model code is not valid: "
					+ domain.getDomainConfig().getCode() + "."
					+ modelCode;
			throw new ConfigRuntimeException(msg);
		}
		if (modelConfig.isAbstraction()) {
			String msg = "Model.constructor -- model is configured as an abstraction: "
					+ domain.getDomainConfig().getCode()
					+ "."
					+ modelConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		if (domain.getDomainConfig().isDefaultConstruct()) {
			createEntries();
		}
		domain.getModels().add(this);
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
	 * Adds the entry.
	 * 
	 * @param entry
	 *            entry entities
	 */
	public void addEntry(IEntities<?> entry) {
		if (entry != null) {
			ConceptConfig conceptConfig = entry.getConceptConfig();
			if (conceptConfig != null) {
				IEntities<?> entryEntities = getEntry(conceptConfig);
				if (entryEntities == null) {
					entryMap.put(entry.getConceptConfig(), entry);
				}
			}
		} else {
			String msg = "Model.addEntry -- entry is null.";
			throw new ModelibraRuntimeException(msg);
		}
	}

	/**
	 * Replaces the entry. Sets new entry for conceptConfig key if there are
	 * entry values set for that key.
	 * 
	 * @param entry
	 *            entry entities
	 */
	public void replaceEntry(IEntities<?> entry) {
		if (entry != null) {
			ConceptConfig conceptConfig = entry.getConceptConfig();
			if (conceptConfig != null) {
				IEntities<?> entryEntities = getEntry(conceptConfig);
				if (entryEntities != null) {
					entryMap.put(entry.getConceptConfig(), entry);
				}
			}
		} else {
			String msg = "Model.replaceEntry -- entry is null.";
			throw new ModelibraRuntimeException(msg);
		}
	}

	/**
	 * Creates an entry.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 * @return entry entities
	 */
	private IEntities<?> createEntry(ConceptConfig conceptConfig) {
		IEntities<?> entry = null;
		if (conceptConfig.isAbstraction()) {
			String msg = "Model.createEntry -- concept is configured as an abstraction : "
					+ domain.getDomainConfig().getCode()
					+ "."
					+ modelConfig.getCode() + "." + conceptConfig.getCode();
			throw new ConfigRuntimeException(msg);
		} else if (conceptConfig.isEntry()) {
			String entitiesClassName = conceptConfig.getEntitiesClass();
			getModelMeta().createEntities(entitiesClassName);
			// Entities constructor added the entry.
		} else {
			String msg = "Model.createEntry -- concept is not entry: "
					+ domain.getDomainConfig().getCode() + "."
					+ modelConfig.getCode() + "." + conceptConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		return entry;
	}

	/**
	 * Creates entries.
	 */
	private void createEntries() {
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			createEntry(conceptConfig);
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
	 * Sets to <code>true</code> if the domain model is initialized with the
	 * model data. If the model is persistent, the setInitialized method should
	 * be used only by the persistence mechanism to indicate that the loading of
	 * the model is done.
	 * 
	 * @param initialized
	 *            <code>true</code> if the domain model is initialized with the
	 *            model data
	 */
	public void setInitialized(boolean initialized) {
		if (initialized) {
			endInitialize();
		}
		this.initialized = initialized;
	}

	/**
	 * Checks if the domain model is initialized with the model data.
	 * 
	 * @return <code>true</code> if the domain model is initialized with the
	 *         model data
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Allows to finish the initialization of the model. For example, after the
	 * model is loaded, all external children are initialized as well. Called by
	 * the setInitialized method.
	 */
	protected void endInitialize() {
		Iterator<IEntities<?>> iterator = getEntryList().iterator();
		while (iterator.hasNext()) {
			IEntities<?> entities = iterator.next();
			getModelMeta().initializeExternalChildNeighbors(entities);
		}
	}

	/**
	 * Checks if sessions will be used.
	 * 
	 * @return <code>true</code> if sessions will be used
	 */
	public boolean isSession() {
		return getModelConfig().isSession();
	}

	/**
	 * Gets the domain model code.
	 * 
	 * @return domain model code
	 */
	public String getCode() {
		return modelConfig.getCode();
	}

	/**
	 * Sets the domain model configuration.
	 * 
	 * @param modelConfig
	 *            domain model configuration
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
	 * Sets the reference (e.g., validation type) model.
	 * 
	 * @param referenceModel
	 *            reference model
	 */
	public void setReferenceModel(IDomainModel referenceModel) {
		this.referenceModel = referenceModel;
	}

	/**
	 * Gets the reference (e.g., validation type) model.
	 * 
	 * @return reference model
	 */
	public IDomainModel getReferenceModel() {
		return referenceModel;
	}

	/**
	 * Gets the specific model entities.
	 * 
	 * @param entitiesCode
	 *            entities code
	 * @return entities
	 */
	public IEntities<?> getSpecificModelEntities(String entitiesCode) {
		return (IEntities<?>) getModelMeta().getSpecificModelEntities(
				entitiesCode);
	}

	/**
	 * Checks if the model is empty.
	 * 
	 * @return <code>true</code> if the model is empty
	 */
	public boolean isEmpty() {
		for (Map.Entry<ConceptConfig, IEntities<?>> e : entryMap.entrySet()) {
			IEntities<?> entry = e.getValue();
			if (!entry.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the model entry entities for a given entry code.
	 * 
	 * @param entryCode
	 *            entry concept entity code or entry concept entities code
	 * @return entry entities
	 */
	public IEntities<?> getEntry(String entryCode) {
		IEntities<?> entry = null;
		for (Map.Entry<ConceptConfig, IEntities<?>> e : entryMap.entrySet()) {
			ConceptConfig conceptConfig = e.getKey();
			if ((conceptConfig.getCode().equalsIgnoreCase(entryCode))
					|| (conceptConfig.getEntitiesCode()
							.equalsIgnoreCase(entryCode))) {
				entry = (IEntities<?>) e.getValue();
				break;
			}
		}
		return entry;
	}

	/**
	 * Gets the model entry entities for a given concept configuration.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 * @return entry entities
	 */
	private IEntities<?> getEntry(ConceptConfig conceptConfig) {
		IEntities<?> entry = null;
		for (Map.Entry<ConceptConfig, IEntities<?>> e : entryMap.entrySet()) {
			ConceptConfig entryConceptConfig = e.getKey();
			if (entryConceptConfig.equals(conceptConfig)) {
				entry = (IEntities<?>) e.getValue();
				break;
			}
		}
		return entry;
	}

	/**
	 * Gets the domain model list of entry entities.
	 * 
	 * @return domain model list of entry entities
	 */
	public List<IEntities<?>> getEntryList() {
		List<IEntities<?>> entries = new ArrayList<IEntities<?>>();
		List<String> entryCodes = getEntryCodeList();
		for (String entryCode : entryCodes) {
			IEntities<?> entities = getEntry(entryCode);
			entries.add(entities);
		}
		return entries;
	}

	/**
	 * Gets the domain model list of entry codes.
	 * 
	 * @return domain model list of entry codes
	 */
	public List<String> getEntryCodeList() {
		List<String> entryCodes = new ArrayList<String>();
		for (Map.Entry<ConceptConfig, IEntities<?>> e : entryMap.entrySet()) {
			ConceptConfig conceptConfig = e.getKey();
			String conceptCode = conceptConfig.getCode();
			entryCodes.add(conceptCode);
		}
		Collections.sort(entryCodes);
		return entryCodes;
	}

	/**
	 * Gets the domain model list of entry names (display names).
	 * 
	 * @return domain model list of entry names
	 */
	public List<String> getEntryNameList() {
		List<String> entryNames = new ArrayList<String>();
		for (Map.Entry<ConceptConfig, IEntities<?>> e : entryMap.entrySet()) {
			ConceptConfig conceptConfig = e.getKey();
			String conceptName = conceptConfig.getConceptName();
			entryNames.add(conceptName);
		}
		Collections.sort(entryNames);
		return entryNames;
	}

	/**
	 * Exports the base model to the taken model.
	 * 
	 * @param takenModel
	 *            taken model
	 * @param exportSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            exported
	 */
	public void export(IDomainModel takenModel, boolean sensitive) {
		ModelConfig baseModelConfig = getModelConfig();
		String baseModelCode = baseModelConfig.getCode();
		ModelConfig toModelConfig = takenModel.getModelConfig();
		String toModelCode = toModelConfig.getCode();
		log.info("--- Exporting " + baseModelCode + " to " + toModelCode
				+ ". ---");
		List<IEntities<?>> baseModelEntryList = getEntryList();
		for (IEntities baseModelEntry : baseModelEntryList) {
			String baseModelEntryCode = baseModelEntry.getConceptConfig()
					.getCode();
			IEntities toModelEntry = takenModel.getEntry(baseModelEntryCode);
			toModelEntry.getErrors().empty();
			baseModelEntry.export(toModelEntry, sensitive);
			List<String> errors = toModelEntry.getErrors().getErrorList();
			if (errors.size() > 0) {
				OutTester.outputCollection(errors, toModelEntry
						.getConceptConfig().getCode()
						+ " Export Errors");
			}
		}
		log.info("--- Model exporting ends. ---");
	}

	/**
	 * Synchronizes the returned model with the base model (adds and updates).
	 * The returned model is a new version of the taken model.
	 * 
	 * @param takenModel
	 *            taken model
	 * @param returnedModel
	 *            returned model
	 * @param synchronizeSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            synchronized
	 */
	public void synchronize(IDomainModel takenModel, IDomainModel returnedModel,
			boolean sensitive) {
		ModelConfig baseModelConfig = getModelConfig();
		String baseModelCode = baseModelConfig.getCode();
		ModelConfig returnedModelConfig = returnedModel.getModelConfig();
		String returnedModelCode = returnedModelConfig.getCode();
		log.info("--- Synchronization of " + returnedModelCode + " to "
				+ baseModelCode + " begins. ---");
		List<IEntities<?>> returnedModelEntryList = returnedModel
				.getEntryList();
		for (IEntities returnedModelEntry : returnedModelEntryList) {
			String returnedModelEntryCode = returnedModelEntry
					.getConceptConfig().getCode();
			IEntities takenModelEntry = takenModel
					.getEntry(returnedModelEntryCode);
			IEntities baseModelEntry = getEntry(returnedModelEntryCode);
			baseModelEntry.getErrors().empty();
			baseModelEntry.synchronize(takenModelEntry, returnedModelEntry,
					sensitive);
			List<String> errors = baseModelEntry.getErrors().getErrorList();
			if (errors.size() > 0) {
				OutTester.outputCollection(errors, baseModelEntry
						.getConceptConfig().getCode()
						+ " Integrate Errors");
			}
		}
		log.info("--- Model synchronization ends. ---");
	}

	/**
	 * The base model is cleaned of entities that are removed from the returned
	 * model (in comparison with the taken model).
	 * 
	 * @param takenModel
	 *            taken model
	 * @param returnedModel
	 *            returned model
	 */
	public void clean(IDomainModel takenModel, IDomainModel returnedModel) {
		ModelConfig baseModelConfig = getModelConfig();
		String baseModelCode = baseModelConfig.getCode();
		ModelConfig returnedModelConfig = returnedModel.getModelConfig();
		String returnedModelCode = returnedModelConfig.getCode();
		log.info("--- Cleaning of " + baseModelCode + " with respect to "
				+ returnedModelCode + " begins. ---");
		List<IEntities<?>> returnedModelEntryList = returnedModel
				.getEntryList();
		for (IEntities returnedModelEntry : returnedModelEntryList) {
			String returnedModelEntryCode = returnedModelEntry
					.getConceptConfig().getCode();
			IEntities takenModelEntry = takenModel
					.getEntry(returnedModelEntryCode);
			IEntities baseModelEntry = getEntry(returnedModelEntryCode);
			baseModelEntry.getErrors().empty();
			baseModelEntry.clean(takenModelEntry, returnedModelEntry);
			List<String> errors = baseModelEntry.getErrors().getErrorList();
			if (errors.size() > 0) {
				OutTester.outputCollection(errors, baseModelEntry
						.getConceptConfig().getCode()
						+ " Clean Errors");
			}
		}
		log.info("--- Model cleaning ends. ---");
	}

	/**
	 * Gets a new session.
	 * 
	 * @return new session
	 */
	public ModelSession getNewSession() {
		ModelSession session = null;
		if (isSession()) {
			session = new ModelSession(this);
		} else {
			String msg = "Model.getNewSession -- model session is configured as false: "
					+ domain.getDomainConfig().getCode()
					+ "."
					+ modelConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		return session;
	}

	/**
	 * Closes the model.
	 */
	public void close() {

	}

	/**
	 * Notifies observes.
	 * 
	 * @param arg
	 *            arg
	 */
	@Override
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}

}