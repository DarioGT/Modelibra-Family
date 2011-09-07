package first;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class SalesModelTest {

	private static SalesModel salesModel;

	@BeforeClass
	public static void beforeTests() throws Exception {
		salesModel = new SalesModel();
	}

	@Test
	public void clientsCreated() throws Exception {
		String name = "Clients";
		IEntities<?> entities = salesModel.createEntities(name);
		assertNotNull(entities);
		boolean added = salesModel.addEntities(name, entities);
		assertTrue(added);
		Clients clients = (Clients) salesModel.getEntities(name);
		assertNotNull(clients);
		assertTrue(salesModel.isEmpty());
	}
	
	@Test
	public void productsCreated() throws Exception {
		String name = "Products";
		IEntities<?> entities = salesModel.createEntities(name);
		assertNotNull(entities);
		boolean added = salesModel.addEntities(name, entities);
		assertTrue(added);
		Products products = (Products) salesModel.getEntities(name);
		assertNotNull(products);
		assertTrue(salesModel.isEmpty());
	}
	
	@Test
	public void namesOfEntitiesList() throws Exception {
		List<String> nameList = salesModel.getEntitiesNameList();
		for (String name : nameList) {
			System.out.println(name);
		}
	}

}
