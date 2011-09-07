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
 * Observer of the history of actions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-07-11
 */
public interface IHistoryObserver {

	/**
	 * When there is no history, there is nothing to undo.
	 */
	public void noHistory();

	/**
	 * When there is history, the last action can be undone.
	 */
	public void yesHistory();

}