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

	public boolean undo() {
		boolean undone = false;
		if (actions.size() > 0) {
			moveCursorBackward();
			Action action = (Action) actions.get(cursor);
			if (action.getStatus().equals("executed") && action.undo()) {
				undone = true;
			}
		}
		return undone;
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
