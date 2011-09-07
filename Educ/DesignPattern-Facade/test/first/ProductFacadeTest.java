package first;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import first.IReference;
import first.ProductFacade;
import first.Reference;

public class ProductFacadeTest {

	private static IReference reference = new Reference();

	@BeforeClass
	public static void beforeTests() throws Exception {
		reference.add("JDO", "Java Data Objects");
		reference.add("JDBC", "Java Database Connection");
		reference.add("DBMS", "Database Management System");
	}

	@Test
	public void facadeToReference() throws Exception {
		ProductFacade product = new ProductFacade(reference);
		product.setOid(1000L);
		product.setName("Core Java Data Objects");
		product.setCategory("JDO");

		String categoryName = product.getCategoryName();
		assertEquals("Java Data Objects", categoryName);
		product.output();
	}

}
