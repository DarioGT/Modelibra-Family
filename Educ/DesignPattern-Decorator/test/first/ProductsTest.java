package first;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProductsTest {

	private static Entities products;

	@BeforeClass
	public static void beforeTests() throws Exception {
		products = new Products();
	}

	@Test
	public void productWithCode() throws Exception {
		Product product = new Product();
		product.setOid(2000L);
		String computer = "computer";
		product.setName(computer);

		EntityWithCode entityWithCode = new EntityWithCode(product);
		entityWithCode.setCode("T45");

		products.add(entityWithCode);
		assertTrue(products.contain(entityWithCode));

		Product retrievedProduct = (Product) entityWithCode.getEntity();
		assertNotNull(retrievedProduct);
		String productName = retrievedProduct.getName();
		assertNotNull(productName);
		assertEquals(computer, productName); // expected, actual

		products.remove(entityWithCode);
		assertTrue(products.isEmpty());
	}
	
	@Test
	public void productWithCodeAndApproved() throws Exception {
		Product product = new Product();
		product.setOid(2000L);
		String computer = "computer";
		product.setName(computer);

		EntityWithCode entityWithCode = new EntityWithCode(product);
		entityWithCode.setCode("T45");
		EntityWithApproved entityWithApproved = new EntityWithApproved(entityWithCode);
		entityWithApproved.setApproved(Boolean.FALSE);
		
		products.add(entityWithApproved);
		assertTrue(products.contain(entityWithApproved));
		products.output("productWithCodeAndApproved");
		
		products.remove(entityWithApproved);
		assertTrue(products.isEmpty());
	}

}
