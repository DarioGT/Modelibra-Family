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
package org.modelibra.exception;

import org.modelibra.action.Action;

/**
 * Action exception.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-07-10
 * 
 */
public class ActionException extends ModelibraException {

	private static final long serialVersionUID = 3010L;

	private Action action;

	/**
	 * Constructs an action exception with an error message.
	 * 
	 * @param error
	 *            error message
	 * @param action
	 *            action
	 */
	public ActionException(String error, Action action) {
		super(error);
		this.action = action;
	}

	/**
	 * Gets an action.
	 * 
	 * @return action
	 */
	public Action getAction() {
		return action;
	}

}