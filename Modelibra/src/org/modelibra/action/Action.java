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

/**
 * Encapsulates an action. An action is either an action on entities or a
 * transaction.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-02-09
 */
public abstract class Action {

	private String status = "started";

	/**
	 * Constructs an action.
	 */
	public Action() {
		super();
	}

	/**
	 * Sets the action status.
	 * 
	 * @param status
	 *            action status
	 */
	void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the action status.
	 * 
	 * @return action status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Checks if the action is undone.
	 * 
	 * @return <code>true</code> if executed
	 */
	public boolean isExecuted() {
		if (getStatus().equals("executed")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the action is undone.
	 * 
	 * @return <code>true</code> if undone
	 */
	public boolean isUndone() {
		if (getStatus().equals("undone")) {
			return true;
		}
		return false;
	}

	/**
	 * Executes the action.
	 * 
	 * @return <code>true</code> if executed
	 */
	public abstract boolean execute();

	/**
	 * Undos the action.
	 * 
	 * @return <code>true</code> if undone
	 */
	public abstract boolean undo();

	// TODO: keep until refactoring of classes that use this class.
	public String getName() {
		return null;
	}
}