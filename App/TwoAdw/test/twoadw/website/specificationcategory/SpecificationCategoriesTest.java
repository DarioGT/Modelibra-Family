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
package twoadw.website.specificationcategory;

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
import twoadw.website.Website;
import twoadw.website.product.Product;
import twoadw.website.product.Products;

/**
 * JUnit tests for SpecificationCategories.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class SpecificationCategoriesTest {
	
	private static Product sampleProduct;
	private static Products products;

	private static SpecificationCategories specificationCategories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products=website.getProducts();
		sampleProduct=products.createProduct("productNumber","name");
		 specificationCategories = sampleProduct.getSpecificationCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		specificationCategories.getErrors().empty();
	}

	@Test
	public void specificationCategoryRequired() throws Exception {
		SpecificationCategory spc=specificationCategories.createSpecificationCategory(sampleProduct, "name");
		assertTrue(specificationCategories.contain(spc));
		assertTrue(specificationCategories.getErrors().isEmpty());
	}
	@Test
	public void specificationCategoryNameRequired() throws Exception {
		SpecificationCategory spc=specificationCategories.createSpecificationCategory(sampleProduct, null);
		assertFalse(specificationCategories.contain(spc));
		assertFalse(specificationCategories.getErrors().isEmpty());
		assertNotNull(specificationCategories.getErrors().getError("SpecificationCategory.name.required"));
	}
	@Test
	public void specificationCategoryNameLength() throws Exception {
		String name="";
		while(name.length()<=64){
			name+="1";
		}
		SpecificationCategory spc=specificationCategories.createSpecificationCategory(sampleProduct, name);
		assertFalse(specificationCategories.contain(spc));
		assertFalse(specificationCategories.getErrors().isEmpty());
		assertNotNull(specificationCategories.getErrors().getError("SpecificationCategory.name.length"));
	}
	@Test
	public void specificationCategoryNameEquality() throws Exception {
		SpecificationCategory spc=specificationCategories.createSpecificationCategory(sampleProduct, "name");
		SpecificationCategory copySPC=spc.copy();
		assertEquals(spc,copySPC);
		assertNotSame(spc,copySPC);
		assertTrue(specificationCategories.getErrors().isEmpty());
	}
	@Test
	public void specificationCategoryUpdate() throws Exception {
		SpecificationCategory spc=specificationCategories.createSpecificationCategory(sampleProduct, "name");
		SpecificationCategory copySPC=spc.copy();
		copySPC.setName("copyName");
		assertTrue(spc.equals(copySPC));
		assertTrue(spc.equalOid(copySPC));
		assertFalse(spc.equalProperties(copySPC));
	}
	@After
	public void afterTest() throws Exception {
		for (SpecificationCategory specificationCategory : specificationCategories.getList()) {
			specificationCategories.remove(specificationCategory);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct);
		TwoadwTest.getSingleton().close();
	}

}