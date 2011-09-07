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
 * History of actions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-07-11
 */
public interface IHistory {

	/**
	 * Adds an action.
	 * 
	 * @param action
	 *            action
	 */
	public void add(Action action);

	/**
	 * Empties the history of actions.
	 */
	public void empty();

	/**
	 * Undos the current action.
	 * 
	 * @return <code»>true</code> if undone
	 */
	public boolean undo();

	/**
	 * Adds a history observer.
	 * 
	 * @param historyObserver
	 *            history observer
	 */
	public void addHistoryObserver(IHistoryObserver historyObserver);

	/**
	 * Removes a history observer.
	 * 
	 * @param historyObserver
	 *            history observer
	 */
	public void removeHistoryObserver(IHistoryObserver historyObserver);

}
