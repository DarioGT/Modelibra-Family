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
 * Encapsulates the add action on entities.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-02-09
 */
public class AddAction extends EntitiesAction {

	/**
	 * Constructs entities add action within a session.
	 * 
	 * @param session
	 *            session
	 */
	public AddAction(ModelSession session) {
		super(session);
	}

	/**
	 * Constructs entities add action within a transaction.
	 * 
	 * @param transaction
	 *            transaction
	 */
	public AddAction(Transaction transaction) {
		super(transaction);
	}

	/**
	 * Constructs entities add action within a session.
	 * 
	 * @param session
	 *            session
	 * 
	 * @param entities
	 *            entities on which to execute action - add entity
	 * 
	 * @param entity
	 *            entity to be used in action - added to entities
	 */
	public AddAction(ModelSession session, IEntities<?> entities, IEntity<?> entity) {
		super(session, entities, entity);
	}

	/**
	 * Constructs entities add action within a session.
	 * 
	 * @param transaction
	 *            transaction
	 * 
	 * @param entities
	 *            entities on which to execute action - add entity
	 * 
	 * @param entity
	 *            entity to be used in action - added to entities
	 */
	public AddAction(Transaction transaction, IEntities<?> entities,
			IEntity<?> entity) {
		super(transaction, entities, entity);
	}

	/**
	 * Constructs entities add action. This kind of action will never be
	 * executed. It is used as wrapper for IEntities and IEntity to notify
	 * observers.
	 * 
	 * @param entities
	 *            entities on which action is executed - entity added
	 * 
	 * @param entity
	 *            entity used in action - added to entities
	 */
	public AddAction(IEntities<?> entities, IEntity<?> entity) {
		super(entities, entity);
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
		executed = getEntities().add(entity);
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
		undone = getEntities().remove(entity);
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
		return "add";
	}
}