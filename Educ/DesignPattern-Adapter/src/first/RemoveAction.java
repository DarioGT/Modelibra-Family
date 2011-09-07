package first;

public class RemoveAction extends EntitiesAction {

	public RemoveAction(IEntities entities, IEntity entity) {
		super(entities, entity);
	}

	public boolean execute() {
		boolean executed = getEntities().remove(getEntity());
		if (executed) {
			setStatus("executed");
		}
		return executed;
	}

	public boolean undo() {
		boolean undone = false;
		if (!isExecuted()) {
			String error = "An action must be executed first.";
			throw new RuntimeException(error);
		}
		undone = getEntities().add(getEntity());
		if (undone) {
			setStatus("undone");
		}
		return undone;
	}

}
