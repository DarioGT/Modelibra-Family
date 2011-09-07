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
package org.modelibra.action;

import org.modelibra.DomainModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.ModelSession;
import org.modelibra.exception.ActionRuntimeException;

/**
 * Encapsulates the update action on entities.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-02-09
 */
public class UpdateAction extends EntitiesAction {

	private IEntity<?> updateEntity;

	private IEntity<?> beforeUpdateEntity;

	public IEntity<?> getBeforeUpdateEntity() {
		return beforeUpdateEntity;
	}

	/**
	 * Constructs entities update action within a session.
	 * 
	 * @param session
	 *            session
	 */
	public UpdateAction(ModelSession session) {
		super(session);
	}

	/**
	 * Constructs entities update action within a transaction.
	 * 
	 * @param transaction
	 *            transaction
	 */
	public UpdateAction(Transaction transaction) {
		super(transaction);
	}

	/**
	 * Constructs entities update action within a session.
	 * 
	 * @param session
	 *            session
	 * 
	 * @param entities
	 *            entities on which action is executed - update entity
	 * 
	 * @param entity
	 *            entity to be used in action - updated
	 * 
	 * @param updateEntity
	 *            entity to use for update
	 */
	public UpdateAction(ModelSession session, IEntities<?> entities,
			IEntity<?> entity, IEntity<?> updateEntity) {
		super(session, entities, entity);
		this.updateEntity = updateEntity;
	}

	/**
	 * Constructs entities update action within a transaction.
	 * 
	 * @param transaction
	 *            transaction
	 * 
	 * @param entities
	 *            entities on which action is executed - update entity
	 * 
	 * @param entity
	 *            entity to be used in action - updated
	 * 
	 * @param updateEntity
	 *            entity to use for update
	 */
	public UpdateAction(Transaction transaction, IEntities<?> entities,
			IEntity<?> entity, IEntity<?> updateEntity) {
		super(transaction, entities, entity);
		this.updateEntity = updateEntity;
	}

	/**
	 * Constructs entities update action. This kind of action will never be
	 * executed. It is used as wrapper for IEntities and IEntity to notify
	 * observers.
	 * 
	 * @param entities
	 *            entities on which action is executed
	 * 
	 * @param entity
	 *            updated entity
	 * 
	 * @param updateEntity
	 *            entity used for update
	 */
	public UpdateAction(IEntities<?> entities, IEntity<?> entity,
			IEntity<?> updateEntity) {
		super(entities, entity);
		this.updateEntity = updateEntity;
	}

	/**
	 * Sets an update entity.
	 * 
	 * @param updateEntity
	 *            update entity
	 */
	public void setUpdateEntity(IEntity<?> updateEntity) {
		this.updateEntity = updateEntity;
	}

	/**
	 * Gets an update entity.
	 * 
	 * @return update entity
	 */
	public IEntity<?> getUpdateEntity() {
		return updateEntity;
	}

	/**
	 * Executes the action.
	 * 
	 * @return <code>true</code> if executed
	 */
	public boolean execute() {
		boolean executed = false;
		if (getSession() == null && getTransaction() == null) {
			String error = "Action must be within a session or within a transaction";
			throw new ActionRuntimeException(error);
		}
		beforeUpdateEntity = entity.copy();
		executed = getEntities().update(entity, updateEntity);
		if (executed) {
			setStatus("executed");
			if (getTransaction() == null) {
				getSession().getHistory().add(this);
				DomainModel domainModel = (DomainModel) getSession().getModel();
				domainModel.notifyObservers(this);
			}
		}
		return executed;
	}

	/**
	 * Undoes the action.
	 * 
	 * @return <code>true</code> if undone
	 */
	public boolean undo() {
		boolean undone = false;
		if (!isExecuted()) {
			String error = "An action must be executed first.";
			throw new ActionRuntimeException(error);
		}
		undone = getEntities().update(entity, beforeUpdateEntity);
		if (undone) {
			setStatus("undone");
			if (getTransaction() == null) {
				DomainModel domainModel = (DomainModel) getSession().getModel();
				domainModel.notifyObservers(this);
			}
		}
		return undone;
	}

	// TODO: keep until refactoring of classes that use this class.
	public String getName() {
		return "update";
	}
}