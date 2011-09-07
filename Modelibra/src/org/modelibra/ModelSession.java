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

import org.modelibra.action.History;
import org.modelibra.action.IHistory;

/**
 * Domain model session. Used to support transactions (transaction history of
 * actions) and the history of session actions and/or transactions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-10
 */
public class ModelSession {

	private IDomainModel model;

	private History history = new History();

	/**
	 * Constructs a session.
	 * 
	 * @param model
	 *            model
	 */
	ModelSession(IDomainModel model) {
		super();
		this.model = model;
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
	 * Gets the session history.
	 * 
	 * @return history
	 */
	public IHistory getHistory() {
		return history;
	}

}