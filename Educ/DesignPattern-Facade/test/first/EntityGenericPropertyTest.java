package first;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import first.Product;

public class EntityGenericPropertyTest {

	@Test
	public void propertyObtained() throws Exception {
		Product product = new Product();
		product.setOid(1000L);
		product.setName("Core Java Data Objects");
		product.setCategory("JDO");
		String category = (String) product.getProperty("category");
		assertEquals("JDO", category);
	}

}
