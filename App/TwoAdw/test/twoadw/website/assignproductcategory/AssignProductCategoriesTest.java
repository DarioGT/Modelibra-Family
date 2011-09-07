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
package twoadw.website.assignproductcategory;

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
import twoadw.website.productcategory.ProductCategories;
import twoadw.website.productcategory.ProductCategory;

/**
 * JUnit tests for AssignProductCategories.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class AssignProductCategoriesTest {

	private static AssignProductCategories assignProductCategories;
	private static ProductCategories productCategories;
	private static ProductCategory sampleCategory;
	
	private static Products products;
	private static Product sampleProduct1;
	private static Product sampleProduct2;
	
	@BeforeClass
	public static void beforeTests() throws Exception {
		productCategories = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProductCategories();
		sampleCategory = productCategories.createProductCategory(null, "cat1", "Category1");
		
		products = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProducts();
		sampleProduct1 = products.createProduct("p1", "Product1");
		sampleProduct2 = products.createProduct("p2", "Product2");
		
		assignProductCategories = sampleCategory.getAssignProductCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		assignProductCategories.getErrors().empty();
	}

	@Test
	public void assignProductCategoriesCreated() throws Exception {
		AssignProductCategory aPC1 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct1);
		AssignProductCategory aPC2 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct2);

		assertTrue(assignProductCategories.contain(aPC1));
		assertTrue(assignProductCategories.contain(aPC2));
		assertTrue(assignProductCategories.getErrors().isEmpty());
	}

	@Test
	public void assignProductCategoriesEquality() throws Exception {
		AssignProductCategory aPC1 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct1);
		AssignProductCategory aPC2 = aPC1.copy();

		assertEquals(aPC1,aPC2);
		assertNotSame(aPC1,aPC2);
		assertTrue(assignProductCategories.getErrors().isEmpty());
	}
	
	@Test
	public void assignProductCategoriesUpdate() throws Exception {
		AssignProductCategory aPC1 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct1);
		AssignProductCategory aPC2 = aPC1.copy();
		aPC2.setProduct(sampleProduct2);

		assertTrue(aPC1.equals(aPC2));
		assertTrue(aPC1.equalOid(aPC2));
		assertFalse(aPC1.equalProperties(aPC2));
		assignProductCategories.update(aPC1, aPC2);
		assertTrue(assignProductCategories.getErrors().isEmpty());
	}
	
	//AssignProductCategory.id.unique=AssignProductCategory identifier ([] [product, productCategory]) is not unique.
	@Test
	public void idUnique() throws Exception {
		AssignProductCategory aPC1 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct1);
		AssignProductCategory aPC2 = assignProductCategories.createAssignProductCategory(sampleCategory, sampleProduct1);
		
		assertNull(aPC2);
		assertTrue(assignProductCategories.contain(aPC1));
		assertFalse(assignProductCategories.contain(aPC2));
		assertFalse(assignProductCategories.getErrors().isEmpty());
		assertNotNull(assignProductCategories.getErrors().getError("AssignProductCategory.id.unique"));
	}
	
	//AssignProductCategory.productOid.required=ProductOid is required.
	@Test
	public void productOIDRequired() throws Exception {
		AssignProductCategory aPC1 = assignProductCategories.createAssignProductCategory(sampleCategory, null);

		assertNull(aPC1);
		assertFalse(assignProductCategories.getErrors().isEmpty());
		assertNotNull(assignProductCategories.getErrors().getError("AssignProductCategory.productOid.required"));
	}
	
	@After
	public void afterTest() throws Exception {
		for (AssignProductCategory assignProductCategory : assignProductCategories.getList()) {
			assignProductCategories.remove(assignProductCategory);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		productCategories.remove(sampleCategory);
		products.remove(sampleProduct1);
		products.remove(sampleProduct2);
		TwoadwTest.getSingleton().close();
	}

}