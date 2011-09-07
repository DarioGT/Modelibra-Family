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

import org.modelibra.IEntity;
import org.modelibra.exception.ActionRuntimeException;

/**
 * Encapsulates an entity action.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-09
 */
public class EntityAction extends Action {

	private IEntity<?> entity;

	private Object parameter;

	/**
	 * Constructs an entity action.
	 * 
	 * @param name
	 *            name
	 */
	public EntityAction() {
		super();
	}

	/**
	 * Sets an entity. 2008-05-11
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

	/**
	 * Sets a parameter.
	 * 
	 * @param parameter
	 *            parameter
	 */
	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	/**
	 * Gets a parameter.
	 * 
	 * @return parameter
	 */
	public Object getParameter() {
		return parameter;
	}

	/**
	 * Executes the action.
	 * 
	 * @return <code>true</code> if executed
	 */
	public boolean execute() {
		String error = "Execute is not supported on an entity.";
		throw new ActionRuntimeException(error);
	}

	/**
	 * Undos the action.
	 * 
	 * @return <code>true</code> if undone
	 */
	public boolean undo() {
		String error = "Undo is not supported on an entity.";
		throw new ActionRuntimeException(error);
	}
}