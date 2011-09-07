package second;

@SuppressWarnings("serial")
public class SalesModel extends Model {
	
	private IEntitiesFactory entitiesFactory;
	
	public SalesModel(IEntitiesFactory entitiesFactory) {
		this.entitiesFactory = entitiesFactory;
	}

	protected IEntities<?> createEntities(String name) {
		return entitiesFactory.createEntities(name);
	}

}
