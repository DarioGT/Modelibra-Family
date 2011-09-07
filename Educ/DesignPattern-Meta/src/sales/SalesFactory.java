package sales;

import model.IEntities;
import model.IEntitiesFactory;

public class SalesFactory implements IEntitiesFactory {
	
	private static final long serialVersionUID = 1;

	public IEntities<?> createEntities(String name) {
		IEntities<?> entities = null;
		if (name.equals("Clients")) {
			entities = new Clients();
		} else if (name.equals("Products")) {
			entities = new Products();
		}
		return entities;
	}

}
