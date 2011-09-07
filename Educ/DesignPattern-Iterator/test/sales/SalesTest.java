package sales;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Entities;
import model.IEntities;
import model.IModel;
import model.Model;
import model.ModelTestTemplate;

import org.junit.BeforeClass;
import org.junit.Test;

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
	public void googleNotCreated() throws Exception {
		Clients clients = getClients();
		Client client = new Client();
		client.setOid(1010L);
		client.setName("Google");
		assertFalse(clients.add(client));
	}

	@Test
	public void ibmCreated() throws Exception {
		Clients clients = getClients();
		Client client = new Client();
		client.setName("IBM"); // note no oid
		assertTrue(clients.add(client));
	}

	@Test
	public void showClientNames() throws Exception {
		Clients clients = getClients();
		System.out.println("--- Client Names ---");
		for (Client client : clients) {
			System.out.println(client.getName());
		}
	}

}
