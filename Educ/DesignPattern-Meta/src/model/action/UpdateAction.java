package model.action;

import model.IEntity;

public class UpdateAction<T extends IEntity<T>> extends EntityAction<T> {

	public UpdateAction(T entity, String propertyName, Object property) {
		super(entity, propertyName, property);
	}

	public boolean execute() {
		if (isStarted() || (isUndone())) {
			boolean executed = getEntity().setProperty(getPropertyName(),
					getProperty());
			if (executed) {
				setStatus("executed");
			} else {
				setStatus("error");
			}
			return executed;
		} else {
			throw new RuntimeException(
					"An action must be either started or undone to be executed.");
		}
	}

	public boolean undo() {
		if (isExecuted()) {
			boolean undone = getEntity().setProperty(getPropertyName(),
					getOldProperty());
			if (undone) {
				setStatus("undone");
			} else {
				setStatus("error");
			}
			return undone;
		} else {
			throw new RuntimeException(
					"An action must be executed to be undone.");
		}
	}

	public boolean redo() {
		if (isUndone()) {
			return execute();
		} else {
			throw new RuntimeException(
					"An action must be undone to be re-executed.");
		}
	}

}
