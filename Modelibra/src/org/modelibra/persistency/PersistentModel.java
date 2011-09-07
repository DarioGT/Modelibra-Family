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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelibra.IEntities;
import org.modelibra.IDomainModel;
import org.modelibra.DomainModel;
import org.modelibra.config.ConceptConfig;

/**
 * Persistent model of entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public abstract class PersistentModel implements IPersistentModel {

	private static final long serialVersionUID = 4040L;

	private IDomainModel model;

	private boolean loaded = false;

	private Map<ConceptConfig, IPersistentEntities> persistentEntryMap = new HashMap<ConceptConfig, IPersistentEntities>();

	/**
	 * Constructs a persistent model from the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public PersistentModel(IDomainModel model) {
		this.model = model;
		DomainModel dm = (DomainModel) model;
		dm.addObserver(this);
		if (model.getModelConfig().isPersistent()) {
			createPersistentEntries();
		}
	}

	/**
	 * Gets the domain model.
	 * 
	 * @return domain model
	 */
	public IDomainModel getModel() {
		return model;
	}

	/**
	 * Sets to <code>true</code> if the persistent model is loaded.
	 * 
	 * @param loaded
	 *            <code>true</code> if the persistent model is loaded
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
		DomainModel model = (DomainModel) getModel();
		model.setInitialized(loaded);
	}

	/**
	 * Checks if the persistent model is loaded.
	 * 
	 * @return <code>true</code> if the persistent model is loaded
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * Creates persistent entries.
	 */
	private void createPersistentEntries() {
		DomainModel model = (DomainModel) getModel();
		List<IEntities<?>> entryList = model.getEntryList();
		for (IEntities<?> entry : entryList) {
			IPersistentEntities persistentEntry = new PersistentEntities(entry,
					this);
			persistentEntryMap.put(entry.getConceptConfig(), persistentEntry);
		}
	}

	/**
	 * Gets the persistent entry for a given entry code.
	 * 
	 * @param entryCode
	 *            entry concept entity code or entry concept entities code
	 * @return persistent entities
	 */
	public IPersistentEntities getPersistentEntry(String entryCode) {
		IPersistentEntities entry = null;
		for (Map.Entry<ConceptConfig, IPersistentEntities> e : persistentEntryMap
				.entrySet()) {
			ConceptConfig conceptConfig = e.getKey();
			if ((conceptConfig.getCode().equalsIgnoreCase(entryCode))
					|| (conceptConfig.getEntitiesCode()
							.equalsIgnoreCase(entryCode))) {
				entry = (IPersistentEntities) e.getValue();
				break;
			}
		}
		return entry;
	}

	/**
	 * Loads the persistent model.
	 */
	public void load() {
		setLoaded(false);
		for (Map.Entry<ConceptConfig, IPersistentEntities> e : persistentEntryMap
				.entrySet()) {
			IPersistentEntities persistentEntry = e.getValue();
			persistentEntry.load();
		}
		setLoaded(true);

	}

	/**
	 * Saves the persistent model.
	 */
	public void save() {
		for (Map.Entry<ConceptConfig, IPersistentEntities> e : persistentEntryMap
				.entrySet()) {
			IPersistentEntities persistentEntry = e.getValue();
			persistentEntry.save();
		}
	}

	/**
	 * Closes the persistent model.
	 */
	public void close() {
		getModel().close();
	}

}