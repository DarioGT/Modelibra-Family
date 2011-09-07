package first;

public class RemoveAction extends EntitiesAction {

	public RemoveAction(IEntities entities, IEntity entity) {
		super(entities, entity);
	}

	public boolean execute() {
		return getEntities().remove(getEntity());
	}

}
