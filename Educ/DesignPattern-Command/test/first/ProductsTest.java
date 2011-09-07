package first;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductsTest {

	private static Products products;

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesModel salesModel = new SalesModel(new SalesModelFactory());

		products = salesModel.createProducts();
		assertNotNull(products);
		boolean productsAdded = salesModel.addProducts(products);
		assertTrue(productsAdded);
	}

	@Test
	public void productsCreated() throws Exception {
		assertTrue(products.isEmpty());

		Product product1 = new Product();
		product1.setOid(1000L);
		product1.setName("book");
		IAction product1Add = new AddAction(products, product1);
		boolean addedProduct1 = product1Add.execute();
		assertTrue(addedProduct1);

		Product product2 = new Product();
		product2.setOid(2000L);
		product2.setName("computer");
		IAction product2Add = new AddAction(products, product2);
		boolean addedProduct2 = product2Add.execute();
		assertTrue(addedProduct2);

		assertFalse(products.isEmpty());

		products.output("twoValidProducts");
		assertTrue(products.contain(product1));
		assertTrue(products.contain(product2));
	}

	@After
	public void afterTest() throws Exception {
		for (Product product : products.getList()) {
			IAction productRemove = new RemoveAction(products, product);
			boolean removedProduct = productRemove.execute();
			assertTrue(removedProduct);
			assertFalse(products.contain(product));
		}
	}

}
