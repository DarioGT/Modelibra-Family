package model;

import java.util.ArrayList;

public class History implements IHistory {

	private ArrayList<IAction> actions = new ArrayList<IAction>();

	private int cursor = 0;

	public void add(IAction action) {
		if (!actions.contains(action)) {
			removeRightOfCursor();
			actions.add(action);
			moveCursorForward();
		}
	}

	public boolean execute() {
		boolean executed = false;
		if (actions.size() > 0) {
			IAction action = actions.get(cursor);
			if (action.getStatus().equals("started") && action.execute()) {
				executed = true;
				moveCursorForward();
			}
		}
		return executed;
	}

	public boolean executeAll() {
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

	public boolean undo() {
		boolean undone = false;
		if (actions.size() > 0) {
			moveCursorBackward();
			IAction action = actions.get(cursor);
			if (action.getStatus().equals("executed") && action.undo()) {
				undone = true;
			}
		}
		return undone;
	}
	
	public boolean undoAll() {
		boolean allUndone = true;
		while (cursor > 0) {
			if (!undo()) {
				allUndone = false;
				break;
			}
		}
		return allUndone;
	}

	private void removeRightOfCursor() {
		for (int i = actions.size() - 1; i >= cursor; i--) {
			actions.remove(i);
		}
	}

	private void moveCursorForward() {
		cursor++;
	}

	private void moveCursorBackward() {
		if (cursor == 0) {
			return;
		} else {
			cursor--;
		}
	}

}
