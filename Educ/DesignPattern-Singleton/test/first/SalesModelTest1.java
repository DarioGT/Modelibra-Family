package first;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class SalesModelTest1 {

	private static App1 app;

	@BeforeClass
	public static void beforeTests() throws Exception {
		app = App1.getApp();
		app.setModel(new SalesModel(new SalesModelFactory()));
	}

	@Test
	public void clientsCreated() throws Exception {
		String name = "Clients";
		IEntities<?> entities = app.getModel().createEntities(name);
		assertNotNull(entities);
		boolean added = app.getModel().addEntities(name, entities);
		assertTrue(added);
		Clients clients = (Clients) app.getModel().getEntities(name);
		assertNotNull(clients);
		assertTrue(app.getModel().isEmpty());
	}

	@Test
	public void productsCreated() throws Exception {
		String name = "Products";
		IEntities<?> entities = app.getModel().createEntities(name);
		assertNotNull(entities);
		boolean added = app.getModel().addEntities(name, entities);
		assertTrue(added);
		Products products = (Products) app.getModel().getEntities(name);
		assertNotNull(products);
		assertTrue(app.getModel().isEmpty());
	}

	@Test
	public void namesOfEntitiesList() throws Exception {
		List<String> nameList = app.getModel().getEntitiesNameList();
		for (String name : nameList) {
			System.out.println(name);
		}
	}

}
