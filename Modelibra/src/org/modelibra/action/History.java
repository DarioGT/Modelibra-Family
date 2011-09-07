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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * History of actions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-09
 */
public class History implements IHistory {

	private int cursor = 0;

	private ArrayList<Action> actions = new ArrayList<Action>();

	private ArrayList<IHistoryObserver> historyObservers = new ArrayList<IHistoryObserver>();

	/**
	 * Constructs an empty history.
	 */
	public History() {
		super();
	}

	/**
	 * Adds an action.
	 * 
	 * @param action
	 *            action
	 */
	public void add(Action action) {
		if (!actions.contains(action)) {
			removeRightOfCursor();
			actions.add(action);
			moveCursorForward();
		}
	}

	/**
	 * Empties the history of actions.
	 */
	public void empty() {
		cursor = 0;
		actions.removeAll(actions);
		fireNoHistory();
	}

	/**
	 * Executes the current action.
	 * 
	 * @return <code»>true</code> if executed
	 */
	boolean execute() {
		boolean executed = false;
		if (actions.size() > 0) {
			Action action = actions.get(cursor);
			if (action.getStatus().equals("started") && action.execute()) {
				action.setStatus("executed");
				executed = true;
				moveCursorForward();
			}
		}
		return executed;
	}

	/**
	 * Undos the current action.
	 * 
	 * @return <code»>true</code> if undone
	 */
	public boolean undo() {
		boolean undone = false;
		if (actions.size() > 0) {
			moveCursorBackward();
			Action action = actions.get(cursor);
			if (action.getStatus().equals("executed") && action.undo()) {
				action.setStatus("undone");
				undone = true;
			}
		}
		return undone;
	}

	/**
	 * Executes all actions.
	 * 
	 * @return <code»>true</code> if all actions executed
	 */
	boolean executeAll() {
		boolean allExecuted = true;
		cursor = 0;
		while (cursor < actions.size()) {
			if (!execute()) {
				allExecuted = false;
				break;
			}
		}
		return allExecuted;
	}

	/**
	 * Undos all actions.
	 * 
	 * @return <code»>true</code> if all actions undone
	 */
	boolean undoAll() {
		boolean allUndone = true;
		while (cursor > 0) {
			if (!undo()) {
				allUndone = false;
				break;
			}
		}
		return allUndone;
	}

	/**
	 * Adds a history observer.
	 * 
	 * @param historyObserver
	 *            history observer
	 */
	public void addHistoryObserver(IHistoryObserver historyObserver) {
		if (!historyObservers.contains(historyObserver)) {
			historyObservers.add(historyObserver);
		}
	}

	/**
	 * Removes a history observer.
	 * 
	 * @param historyObserver
	 *            history observer
	 */
	public void removeHistoryObserver(IHistoryObserver historyObserver) {
		if (historyObservers.contains(historyObserver)) {
			historyObservers.remove(historyObserver);
		}
	}

	/**
	 * Removes actions right of the action cursor.
	 */
	private void removeRightOfCursor() {
		for (int i = actions.size() - 1; i >= cursor; i--) {
			actions.remove(i);
		}
	}

	/**
	 * Moves the action cursor forward.
	 */
	private void moveCursorForward() {
		cursor++;
		fireYesHistory();
	}

	/**
	 * Moves the action cursor backward.
	 */
	private void moveCursorBackward() {
		if (cursor == 0) {
			return;
		}
		if (--cursor == 0) {
			fireNoHistory();
		}
	}

	/**
	 * Informs observers that there is no history of actions.
	 */
	private void fireNoHistory() {
		Collection<IHistoryObserver> observers;
		synchronized (this) {
			observers = (Collection<IHistoryObserver>) historyObservers.clone();
		}
		for (IHistoryObserver historyObserver : observers) {
			historyObserver.noHistory();
		}
	}

	/**
	 * Informs observers that there is history of actions.
	 */
	private void fireYesHistory() {
		Collection<IHistoryObserver> observers;
		synchronized (this) {
			observers = (Collection<IHistoryObserver>) historyObservers.clone();
		}
		for (IHistoryObserver historyObserver : observers) {
			historyObserver.yesHistory();
		}
	}

	/**
	 * Gets a list copy of actions.
	 * 
	 * @return list copy of actions
	 */
	List<Action> getCopyOfActions() {
		List<Action> clonedActions;
		synchronized (this) {
			clonedActions = (List<Action>) actions.clone();
		}
		return clonedActions;
	}

}
