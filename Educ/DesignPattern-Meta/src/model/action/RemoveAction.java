package model.action;

import model.IEntities;
import model.IEntity;

public class RemoveAction<T extends IEntity<T>> extends EntitiesAction<T> {

	public RemoveAction(IEntities<T> entities, T entity) {
		super(entities, entity);
	}
	
	public boolean execute() {
		if (isStarted() || (isUndone())) {
			boolean executed = getEntities().remove(getEntity());
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
			boolean undone = getEntities().add(getEntity());
			if (undone) {
				setStatus("undone");
			} else {
				setStatus("error");
			}
			return undone;
		} else {
			throw new RuntimeException("An action must be executed to be undone.");
		}
	}

	public boolean redo() {
		if (isUndone()) {
			return execute();
		} else {
			throw new RuntimeException("An action must be undone to be re-executed.");
		}
	}

}
