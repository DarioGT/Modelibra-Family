package second;

public class AddAction extends EntitiesAction {

	public AddAction(IEntities entities, IEntity entity) {
		super(entities, entity);
	}

	public boolean execute() {
		boolean executed = getEntities().add(getEntity());
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
		undone = getEntities().remove(getEntity());
		if (undone) {
			setStatus("undone");
		}
		return undone;
	}

}
