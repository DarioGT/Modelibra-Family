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
package twoadw.website.specification;

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
import twoadw.website.specificationcategory.SpecificationCategories;
import twoadw.website.specificationcategory.SpecificationCategory;

/**
 * JUnit tests for Specifications.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class SpecificationsTest {
	
	private static Product sampleProduct;
	private static Products products;
	private static SpecificationCategory sampleSpecificationCategory;
	private static SpecificationCategories specificationCategories;
	private static Specifications specifications;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products=website.getProducts();
		sampleProduct=products.createProduct("productNumber","name");
		specificationCategories=sampleProduct.getSpecificationCategories();
		sampleSpecificationCategory=specificationCategories.createSpecificationCategory(sampleProduct, "name");
		specifications =sampleSpecificationCategory.getSpecifications();
	}

	@Before
	public void beforeTest() throws Exception {
		specifications.getErrors().empty();
	}

	@Test
	public void specificationRequired() throws Exception {
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, "title", "detail");
		assertTrue(specifications.contain(sp));
		assertTrue(specifications.getErrors().isEmpty());
	}
	@Test
	public void specificationTitleRequired() throws Exception {
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, null, "detail");
		assertFalse(specifications.contain(sp));
		assertFalse(specifications.getErrors().isEmpty());
		assertNotNull(specifications.getErrors().getError("Specification.title.required"));
	}
	@Test
	public void specificationTitleLength() throws Exception {
		String title="";
		while(title.length()<=64){
			title+="1";
		}
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, title, "detail");
		assertFalse(specifications.contain(sp));
		assertFalse(specifications.getErrors().isEmpty());
		assertNotNull(specifications.getErrors().getError("Specification.title.length"));
	}
	@Test
	public void specificationDetailRequired() throws Exception {
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, "title", null);
		assertFalse(specifications.contain(sp));
		assertFalse(specifications.getErrors().isEmpty());
		assertNotNull(specifications.getErrors().getError("Specification.detail.required"));
	}
	@Test
	public void specificationDetailLength() throws Exception {
		String detail="";
		while(detail.length()<=128){
			detail+="1";
		}
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, "title", detail);
		assertFalse(specifications.contain(sp));
		assertFalse(specifications.getErrors().isEmpty());
		assertNotNull(specifications.getErrors().getError("Specification.detail.length"));
	}
	@Test
	public void specificationEquality() throws Exception {
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, "title", "detail");
		Specification copySP=sp.copy();
		assertEquals(sp,copySP);
		assertNotSame(sp,copySP);
		assertTrue(specifications.getErrors().isEmpty());
	}
	@Test
	public void specificationUpdate() throws Exception {
		Specification sp=specifications.createSpecification(sampleSpecificationCategory, "title", "detail");
		Specification copySP=sp.copy();
		copySP.setTitle("copyTitle");
		assertTrue(sp.equals(copySP));
		assertTrue(sp.equalOid(copySP));
		assertFalse(sp.equalProperties(copySP));
	}
		

	@After
	public void afterTest() throws Exception {
		for (Specification specification : specifications.getList()) {
			specifications.remove(specification);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct);
		
		TwoadwTest.getSingleton().close();
	}

}