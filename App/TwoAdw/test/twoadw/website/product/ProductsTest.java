/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package twoadw.website.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.type.EasyDate;

import twoadw.TwoadwTest;

/**
 * JUnit tests for Products.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductsTest {

	private static Products products;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		products = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProducts();
	}

	@Before
	public void beforeTest() throws Exception {
		products.getErrors().empty();
	}

	@Test
	public void productsRequiredCreated() throws Exception {
		Product product01 = products.createProduct( "001", "Product1");
		Product product02 = products.createProduct( "02-001-001", "Product Exemple 2");
		Product product03 = products.createProduct( "03-001-001", "Product Exemple 3");
		Product product04 = products.createProduct( "04-001-001", "Product Exemple 4");
		Product product05 = products.createProduct( "05-001-001", "Product Exemple 5");
		Product product06 = products.createProduct( "06-001-001", "Product Exemple 6");

		assertTrue(products.contain(product01));
		assertTrue(products.contain(product02));
		assertTrue(products.contain(product03));
		assertTrue(products.contain(product04));
		assertTrue(products.contain(product05));
		assertTrue(products.contain(product06));
		assertTrue(products.getErrors().isEmpty());
	}
		
	@Test
	public void productsFullCreated() throws Exception {
		Product product01 = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				"Long Description1", 0.01, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		Product product02 = products.createProduct( "02-001-001", "Product Exemple 2", "This exemple product is the definition of well crafted Canadian know how.",
				"Long Description1", 0.01, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		Product product03 = products.createProduct( "03-001-001", "Product Exemple 3", "There's no word to describe this exemple product.",
				"Long Description1", 0.01, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		

		assertTrue(products.contain(product01));
		assertTrue(products.contain(product02));
		assertTrue(products.contain(product03));
		assertTrue(products.getErrors().isEmpty());
	}
	
	@Test
	public void productUpdate() throws Exception {
		Product product = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				"Long Description1", 0.01, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		Product productCopy = product.copy();
		productCopy.setProductNumber("99-999-999");
		productCopy.setName("UpadatedName");
		productCopy.setShortDescription("UpdatedDescription");
		productCopy.setLongDescription("UpdatedDescription");
		productCopy.setPrice(999.99);
		productCopy.setPublished(false);
		
		assertTrue(product.equals(productCopy));
		assertTrue(product.equalOid(productCopy));
		assertFalse(product.equalProperties(productCopy));
		
		
		products.update(product, productCopy);
		
		assertTrue(products.getErrors().isEmpty());

	}
	
	@Test
	public void productEquality() throws Exception {
		Product product = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				"Long Description1", 0.01, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		Product productCopy = product.copy();
				
		assertEquals(product,productCopy);
		assertNotSame(product,productCopy);
		assertTrue(products.getErrors().isEmpty());
	}
	
	@Test
	public void productNumberRequired() throws Exception {
		Product product = products.createProduct( null, "Product Exemple 1");

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError(
				"Product.productNumber.required"));
	}
	
	@Test
	public void productIdUnique() throws Exception {
		Product product = products.createProduct( "01-125-125", "Product Exemple 1");
		Product productNotUnique = products.createProduct( "01-125-125", "Product Exemple 2");

		assertNull(productNotUnique);
		assertFalse(products.contain(productNotUnique));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError(
				"Product.id.unique"));
	}
	
	@Test
	public void productNumberLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Product product = products.createProduct( stringlength, "Product Exemple 1");

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError("Product.productNumber.length"));
	}
	

	
	@Test
	public void nameRequired() throws Exception {
		Product product = products.createProduct( "01-125-125", null);

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError(
				"Product.name.required"));
	}
	
	@Test
	public void nameLength() throws Exception {
		//Data type Name = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}		
		
		Product product = products.createProduct( "01-125-125", stringlength);

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError("Product.name.length"));
	}
	
	@Test
	public void shortDescriptionLength() throws Exception {
		//Data type shorttext = 128 char
		String stringlength = "";
		while (stringlength.length() <=128) {
			stringlength = stringlength + "1"; 
		}		
		
		Product product = products.createProduct("01-125-125", "Product Exemple 1", stringlength, null, 0.01, new EasyDate(2009, 1, 12), null, null, null);

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError("Product.shortDescription.length"));
	}
	
	@Test
	public void longDescriptionLength() throws Exception {
		//Data type shorttext = 4080 char
		String stringlength = "";
		while (stringlength.length() <=4080) {
			stringlength = stringlength + "1"; 
		}		
		
		Product product = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				stringlength, 0.01, new EasyDate(2009, 1, 12), true, null, null);

		assertNull(product);
		assertFalse(products.contain(product));
		assertFalse(products.getErrors().isEmpty());
		assertNotNull(products.getErrors().getError("Product.longDescription.length"));
	}
	
	@Test
	public void priceDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="default";
		//Boolean defaultValue = false;
		Double defaultValue = 99999.00;
		
		Product product = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				"dd", null, new EasyDate(2009, 1, 12), true, Long.valueOf("1"), true );
		
		assertTrue(product.getPrice().equals(defaultValue));
		assertTrue(products.getErrors().isEmpty());
	}
	
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="default";
		Boolean defaultValue = false;
		//Double defaultValue = 99999.99;
		
		Product product = products.createProduct( "01-125-125", "Product Exemple 1", "This wonderfull product exemple is obtainable in 3 colors!", 
				"dd", 0.99 , new EasyDate(2009, 1, 12), null, Long.valueOf("1"), true );
		
		assertTrue(product.getPublished().equals(defaultValue));
		assertTrue(products.getErrors().isEmpty());
	}
	
	//Product.soldNumber.required=SoldNumber is required.
	@Test
	public void soldNumberDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="default";
		//Boolean defaultValue = false;
		//Double defaultValue = 99999.99;
		Long defaultValue = Long.valueOf("0");
		
		Product product = products.createProduct( "001", "Product1");
		
		assertTrue(product.getSoldNumber().equals(defaultValue));
		assertTrue(products.getErrors().isEmpty());
	}
	
	
	//Product.frontpage.required=Frontpage is required.
	@Test
	public void frontpageDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="default";
		Boolean defaultValue = false;
		//Double defaultValue = 99999.99;
		//Long defaultValue = Long.valueOf("0");
		
		Product product = products.createProduct( "001", "Product1");
		
		assertTrue(product.getFrontpage().equals(defaultValue));
		assertTrue(products.getErrors().isEmpty());
	}
	

	@After
	public void afterTest() throws Exception {
		for (Product product : products.getList()) {
			products.remove(product);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}