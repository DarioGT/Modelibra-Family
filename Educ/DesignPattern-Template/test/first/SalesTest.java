package first;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sales.Client;
import sales.Clients;
import sales.Product;
import sales.Products;
import sales.SalesFactory;

public class SalesTest extends ModelTestTemplate {

	private static IModel sales;

	protected IModel prepareModel() {
		sales = new Model(new SalesFactory());
		sales.createEntities("Clients");
		sales.createEntities("Products");
		return sales;
	}

	protected void prepareEntities() {
		Clients clients = getClients();
		Client client01 = new Client();
		client01.setOid(1010L);
		client01.setName("Google");
		clients.add(client01);
		Client client02 = new Client();
		client02.setOid(1020L);
		client02.setName("Oracle");
		clients.add(client02);

		Products products = getProducts();
		Product product01 = new Product();
		product01.setOid(2010L);
		product01.setName("Core Java Data Objects");
		product01.setCategory("JDO");
		products.add(product01);
	}

	Clients getClients() {
		return (Clients) sales.getEntities("Clients");
	}

	Products getProducts() {
		return (Products) sales.getEntities("Products");
	}

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesTest salesTest = new SalesTest();
		salesTest.prepareDataTemplate();
	}

	@Test
	public void showData() throws Exception {
		List<String> entitiesNameList = sales.getEntitiesNameList();
		for (String name : entitiesNameList) {
			IEntities<?> e = sales.getEntities(name);
			Entities<?> entities = (Entities<?>) e;
			entities.output(name);
		}
	}

	@Test
	public void googleCreated() throws Exception {
		Clients clients = getClients();
		Client client = new Client();
		client.setOid(1010L);
		client.setName("Google");
		assertTrue(clients.add(client));
		clients.output("Double Clients");
	}

}
