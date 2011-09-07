package model;

public class Transaction extends Action {

	private IHistory actions = new History();

	public void add(IAction action) {
		actions.add(action);
	}

	public boolean execute() {
		boolean executed = actions.executeAll();
		if (executed) {
			setStatus("executed");
		} else {
			actions.undoAll();
		}
		return executed;
	}

	public boolean undo() {
		if (!isExecuted()) {
			String error = "A transaction must be executed first.";
			throw new RuntimeException(error);
		}
		boolean undone = actions.undoAll();
		if (undone) {
			setStatus("undone");
		} else {
			actions.executeAll();
		}
		return undone;
	}

}
