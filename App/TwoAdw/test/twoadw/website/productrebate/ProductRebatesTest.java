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
package twoadw.website.productrebate;

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
import twoadw.website.product.Product;
import twoadw.website.product.Products;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;

/**
 * JUnit tests for ProductRebates.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductRebatesTest {

	private static ProductRebates productRebates;
	private static Products products;
	private static Product sampleProduct;
	private static Rebates rebates;
	private static Rebate sampleRebate1;
	private static Rebate sampleRebate2;
	
	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		// productRebates = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProductRebates();
		products = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProducts();
		sampleProduct = products.createProduct("001", "name");
		rebates = TwoadwTest.getSingleton().getTwoadw().getWebsite().getRebates();
		sampleRebate1 = rebates.createRebate("rebate1");
		sampleRebate2 = rebates.createRebate("rebate2");
		productRebates = sampleProduct.getProductRebates();
	}

	@Before
	public void beforeTest() throws Exception {
		productRebates.getErrors().empty();
	}

	@Test
	public void createProductRebate() throws Exception {
		ProductRebate pm1 = productRebates.createProductRebate(sampleProduct, sampleRebate1);
		ProductRebate pm2 = productRebates.createProductRebate(sampleProduct, sampleRebate2);
		
		assertTrue(productRebates.contain(pm1));
		assertTrue(productRebates.contain(pm2));
		assertTrue(productRebates.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (ProductRebate productRebate : productRebates.getList()) {
			productRebates.remove(productRebate);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct);
		rebates.remove(sampleRebate1);
		rebates.remove(sampleRebate2);
		TwoadwTest.getSingleton().close();
	}

}