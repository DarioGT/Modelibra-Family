package second;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductsTest implements Observer {

	private static Products products;

	public void update(Observable observable, Object object) {
		if (observable instanceof Products) {
			String action = (String) object;
			System.out.println(action);
		}
	}

	@BeforeClass
	public static void beforeTests() throws Exception {
		products = new Products();
	}

	@Before
	public void beforeTest() throws Exception {
		products.addObserver(this);
	}

	@Test
	public void oidRequired() throws Exception {
		Product product = new Product();
		product.setName("computer");
		products.add(product);
		products.output("oidRequired");
		assertFalse(products.contain(product));
	}

	@Test
	public void nameIsNotRequired() throws Exception {
		Product product = new Product();
		product.setOid(1000L);
		products.add(product);
		products.output("nameIsNotRequired");
		assertTrue(products.contain(product));
		Product retrievedProduct = products.retrieveByOid(1000L);
		assertNotNull(retrievedProduct);
		products.remove(retrievedProduct);
		assertTrue(products.isEmpty());
	}

	@Test
	public void twoValidProducts() throws Exception {
		Product product1 = new Product();
		product1.setOid(1000L);
		product1.setName("book");
		products.add(product1);

		Product product2 = new Product();
		product2.setOid(2000L);
		product2.setName("computer");
		products.add(product2);

		products.output("twoValidProducts");
		assertTrue(products.contain(product1));
		assertTrue(products.contain(product2));
	}

	@After
	public void afterTest() throws Exception {
		products.deleteObserver(this);
		for (Product product : products.getList()) {
			products.remove(product);
		}
	}

}
