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

import java.io.Serializable;
import java.util.List;

import org.modelibra.config.ModelConfig;

/**
 * <p>
 * IModel interface to represent a domain model.
 * </p>
 * 
 * <p>
 * A domain model is a model of concepts, each with properties and neighbors.
 * </p>
 * 
 * <p>
 * The domain model can be accessed only through one or more entries. An entry
 * is an object of the IEntities type.
 * </p>
 * 
 * <p>
 * The domain model is configured in one of three XML configuration files. A
 * concept that has the "true" value for the entry element in the XML concept
 * configuration is an entry concept.
 * </p>
 * 
 * <p>
 * The domain model can be used with sessions (must be configured) or without
 * sessions. If a session is used, transactions of actions on entities are
 * possible, as well as actions on entities. Actions and/or transactions can be
 * undone. If a session is not used, only a direct use of add, remove and update
 * actions on entities, without undo, is supported.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-10
 */
public interface IDomainModel extends Serializable {

	/**
	 * Gets the domain.
	 * 
	 * @return domain
	 */
	public IDomain getDomain();

	/**
	 * Gets the domain model configuration.
	 * 
	 * @return domain model configuration
	 */
	public ModelConfig getModelConfig();

	/**
	 * Checks if the model is empty.
	 * 
	 * @return <code>true</code> if the model is empty
	 */
	public boolean isEmpty();

	/**
	 * Gets the model entry entities for the given entry code.
	 * 
	 * @param entryCode
	 *            entry concept entity code or entry concept entities code
	 * @return entry entities
	 */
	public IEntities<?> getEntry(String entryCode);

	/**
	 * Gets the model list of entry entities.
	 * 
	 * @return model list of entry entities
	 */
	public List<IEntities<?>> getEntryList();

	/**
	 * Exports the base model to the taken model.
	 * 
	 * @param takenModel
	 *            taken model
	 * @param exportSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            exported
	 */
	public void export(IDomainModel takenModel, boolean exportSensitive);

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
			boolean synchronizeSensitive);

	/**
	 * The base model is cleaned of entities that are removed from the returned
	 * model (in comparison with the taken model).
	 * 
	 * @param takenModel
	 *            taken model
	 * @param returnedModel
	 *            returned model
	 */
	public void clean(IDomainModel takenModel, IDomainModel returnedModel);

	/**
	 * Checks if sessions will be used.
	 * 
	 * @return <code>true</code> if sessions will be used
	 */
	public boolean isSession();

	/**
	 * Gets a new session.
	 * 
	 * @return new session
	 */
	public ModelSession getNewSession();

	/**
	 * Closes the model.
	 */
	public void close();

}