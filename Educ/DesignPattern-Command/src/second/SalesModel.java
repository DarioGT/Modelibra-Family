package second;

@SuppressWarnings("serial")
public class SalesModel extends Model {

	private IEntitiesFactory entitiesFactory;

	public SalesModel(IEntitiesFactory entitiesFactory) {
		this.entitiesFactory = entitiesFactory;
	}

	public IEntities<?> createEntities(String name) {
		return entitiesFactory.createEntities(name);
	}
	
	public Clients createClients() {
		return (Clients) createEntities("Clients");
	}
	
	public Products createProducts() {
		return (Products) createEntities("Products");
	}
	
	public boolean addClients(Clients clients) {
		return addEntities("Clients", clients);
	}

	
	public boolean addProducts(Products products) {
		return addEntities("Products", products);
	}
	
	public Clients getClients() {
		return (Clients) getEntities("Clients");
	}
	
	public Products getProducts() {
		return (Products) getEntities("Products");
	}

}
