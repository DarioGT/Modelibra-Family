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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.ModelSession;

/**
 * Encapsulates an action on entities. An action may be executed or undone. An
 * action may be used within a transaction or within a session. If an action is
 * used within a session, the domain model observers are informed about the
 * execute or undo events.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-05-11
 */
public abstract class EntitiesAction extends Action {

	private static Log log = LogFactory.getLog(EntitiesAction.class);

	private ModelSession session;

	private Transaction transaction;

	private IEntities<?> entities;

	protected IEntity<?> entity;

	/**
	 * Constructs entities action within a session.
	 * 
	 * @param session
	 *            session
	 */
	public EntitiesAction(ModelSession session) {
		super();
		if (session != null) {
			this.session = session;
		} else {
			log.info("Action session is null.");
		}
	}

	/**
	 * Constructs entities action within a transaction.
	 * 
	 * @param transaction
	 *            transaction
	 */
	public EntitiesAction(Transaction transaction) {
		super();
		this.transaction = transaction;
		if (transaction != null) {
			transaction.add(this);
		} else {
			log.info("Action transaction is null.");
		}
	}

	/**
	 * Constructs entities action within a session.
	 * 
	 * @param session
	 *            session
	 * 
	 * @param entities
	 *            entities on which to execute action
	 * 
	 * @param entity
	 *            entity to be used in action
	 */
	public EntitiesAction(ModelSession session, IEntities<?> entities,
			IEntity<?> entity) {
		this(session);
		this.entities = entities;
		this.entity = entity;
	}

	/**
	 * Constructs entities action within a transaction.
	 * 
	 * @param transaction
	 *            transaction
	 * 
	 * @param entities
	 *            entities on which to execute action
	 * 
	 * @param entity
	 *            entity to be used in action
	 */
	public EntitiesAction(Transaction transaction, IEntities<?> entities,
			IEntity<?> entity) {
		this(transaction);
		this.entities = entities;
		this.entity = entity;
	}

	/**
	 * Constructs entities action. This kind of action will never be executed.
	 * It is used as wrapper for IEntities and IEntity to notify observers.
	 * 
	 * @param entities
	 *            entities on which action is executed
	 * 
	 * @param entity
	 *            entity used in action
	 */
	public EntitiesAction(IEntities<?> entities, IEntity<?> entity) {
		this.entities = entities;
		this.entity = entity;
	}

	/**
	 * Gets a session.
	 * 
	 * @return session
	 */
	public ModelSession getSession() {
		return session;
	}

	/**
	 * Gets a transaction.
	 * 
	 * @return transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * Sets entities.
	 * 
	 * @param entities
	 *            entities
	 */
	public void setEntities(IEntities<?> entities) {
		this.entities = entities;
	}

	/**
	 * Gets entities.
	 * 
	 * @return entities
	 */
	public IEntities getEntities() {
		return entities;
	}

	/**
	 * Sets an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void setEntity(IEntity<?> entity) {
		this.entity = entity;
	}

	/**
	 * Gets an entity.
	 * 
	 * @return entity
	 */
	public IEntity<?> getEntity() {
		return entity;
	}

}