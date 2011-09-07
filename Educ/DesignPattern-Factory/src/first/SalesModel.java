package first;

@SuppressWarnings("serial")
public class SalesModel extends Model {

	protected IEntities<?> createEntities(String name) {
		IEntities<?> entities = null;
		if (name.equals("Clients")) {
			entities = new Clients();
		} else if (name.equals("Products")) {
			entities = new Products();
		}
		return entities;
	}

}
