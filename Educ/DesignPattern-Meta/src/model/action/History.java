package model.action;

import model.util.Queue;

public class History implements IHistory {

	private Queue<IAction> actions = new Queue<IAction>();
	private Queue<IAction> undoActions = new Queue<IAction>();

	public void add(IAction action) {
		actions.enqueue(action);
	}

	public boolean execute() {
		boolean executed = false;
		if (!actions.empty()) {
			IAction action = actions.dequeue();
			undoActions.enqueue(action);
			if (action.getStatus().equals("started") && action.execute()) {
				executed = true;
			}
		}
		return executed;
	}

	public boolean executeAll() {
		boolean allExecuted = true;
		while (!actions.empty()) {
			if (!execute()) {
				allExecuted = false;
				break;
			}
		}
		return allExecuted;
	}

	public boolean undo() {
		boolean undone = false;
		if (!undoActions.empty()) {
			IAction action = undoActions.dequeue();
			actions.enqueue(action);
			if (action.getStatus().equals("executed") && action.undo()) {
				undone = true;
			}
		}
		return undone;
	}

	public boolean undoAll() {
		boolean allUndone = true;
		while (!undoActions.empty()) {
			if (!undo()) {
				allUndone = false;
				break;
			}
		}
		return allUndone;
	}

}
