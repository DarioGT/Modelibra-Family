package first;

public class AddAction extends EntitiesAction {

	public AddAction(IEntities entities, IEntity entity) {
		super(entities, entity);
	}

	public boolean execute() {
		return getEntities().add(getEntity());
	}

}
