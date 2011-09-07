package second;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProductsTest {

	private static Products products;
	
	private static IHistory history;

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesModel salesModel = new SalesModel(new SalesModelFactory());

		products = salesModel.createProducts();
		assertNotNull(products);
		boolean productsAdded = salesModel.addProducts(products);
		assertTrue(productsAdded);
		
		history = new History();
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
		
		history.add(product1Add);

		Product product2 = new Product();
		product2.setOid(2000L);
		product2.setName("computer");
		IAction product2Add = new AddAction(products, product2);
		boolean addedProduct2 = product2Add.execute();
		assertTrue(addedProduct2);
		
		history.add(product2Add);

		assertFalse(products.isEmpty());

		products.output("twoValidProducts");
		assertTrue(products.contain(product1));
		assertTrue(products.contain(product2));
		
		assertTrue(history.undo());
		assertTrue(history.undo());

		assertTrue(products.isEmpty());
	}

}
