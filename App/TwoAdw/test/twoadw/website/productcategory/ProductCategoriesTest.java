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
package twoadw.website.productcategory;

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
 * JUnit tests for ProductCategories.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductCategoriesTest {

	private static ProductCategories productCategories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		productCategories = TwoadwTest.getSingleton().getTwoadw().getWebsite().getProductCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		productCategories.getErrors().empty();
	}

	@Test
	public void createProductCategory() throws Exception {
		ProductCategory category01 = productCategories.createProductCategory(null, "001", "ProductCategory1");
		ProductCategory category02 = productCategories.createProductCategory(null, "002", "ProductCategory2");
		ProductCategory category03 = productCategories.createProductCategory(null, "003", "ProductCategory3");
		ProductCategory category01_01 = productCategories.createProductCategory(category01, "001-001", "ProductCategory1-001");
		ProductCategory category01_02 = productCategories.createProductCategory(category01, "001-002", "ProductCategory1-002");
		ProductCategory category01_03 = productCategories.createProductCategory(category01, "001-003", "ProductCategory1-003");
		
		assertTrue(productCategories.contain(category01));
		assertTrue(productCategories.contain(category02));
		assertTrue(productCategories.contain(category03));
		assertTrue(productCategories.contain(category01_01));
		assertTrue(productCategories.contain(category01_02));
		assertTrue(productCategories.contain(category01_03));
		assertTrue(productCategories.getErrors().isEmpty());
	}
	
	@Test
	public void createFullProductCategory() throws Exception {
		ProductCategory category01 = productCategories.createProductCategory(null, "001", "ProductCategory1", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory category02 = productCategories.createProductCategory(null, "002", "ProductCategory2", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory category03 = productCategories.createProductCategory(null, "003", "ProductCategory3", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory category01_01 = productCategories.createProductCategory(category01, "001-001", "ProductCategory1-001", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory category01_02 = productCategories.createProductCategory(category01, "001-002", "ProductCategory1-002", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory category01_03 = productCategories.createProductCategory(category01, "001-003", "ProductCategory1-003", "description", null, true, new EasyDate(2009, 1, 12));
		
		assertTrue(productCategories.contain(category01));
		assertTrue(productCategories.contain(category02));
		assertTrue(productCategories.contain(category03));
		assertTrue(productCategories.contain(category01_01));
		assertTrue(productCategories.contain(category01_02));
		assertTrue(productCategories.contain(category01_03));
		assertTrue(productCategories.getErrors().isEmpty());
	}
	
	@Test
	public void categoryEquality()throws Exception{
		ProductCategory category = productCategories.createProductCategory(null, "001", "ProductCategory1", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory categoryCopy= category.copy();
		
		assertEquals(category,categoryCopy);
		assertNotSame(category,categoryCopy);
		assertTrue(productCategories.getErrors().isEmpty());
	}
	
	@Test
	public void categoryUpdate()throws Exception{
		ProductCategory category = productCategories.createProductCategory(null, "001", "ProductCategory1", "description", null, true, new EasyDate(2009, 1, 12));
		ProductCategory categoryCopy= category.copy();
		categoryCopy.setCategoryNumber("002");
		categoryCopy.setCategoryName("moo");
		categoryCopy.setDescription("moo?");
		categoryCopy.setPublished(false);
		
		assertTrue(category.equals(categoryCopy));
		assertTrue(category.equalOid(categoryCopy));
		assertFalse(category.equalProperties(categoryCopy));
		productCategories.update(category, categoryCopy);
		assertTrue(productCategories.getErrors().isEmpty());
	}
	
	//ProductCategory.id.unique=ProductCategory identifier ([categoryNumber] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		ProductCategory category01 = productCategories.createProductCategory(null, "001", "ProductCategory1");
		ProductCategory category02 = productCategories.createProductCategory(null, "001", "ProductCategory2");
		ProductCategory category03 = productCategories.createProductCategory(null, "001", "ProductCategory3");

		assertTrue(productCategories.contain(category01));
		assertFalse(productCategories.contain(category02));
		assertFalse(productCategories.contain(category03));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.id.unique"));
	}
	
	//ProductCategory.categoryNumber.required=ProductCategoryNumber is required.
	@Test
	public void categoryNumberRequired() throws Exception {
		ProductCategory category = productCategories.createProductCategory(null, null, "ProductCategory1");

		assertFalse(productCategories.contain(category));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.categoryNumber.required"));
	}
	
	//ProductCategory.categoryNumber.length=ProductCategoryNumber is longer than 16.
	@Test
	public void categoryNumberLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductCategory category = productCategories.createProductCategory(null, stringlength, "ProductCategory1");

		assertNull(category);
		assertFalse(productCategories.contain(category));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.categoryNumber.length"));
	}
	
	//ProductCategory.categoryName.required=ProductCategoryName is required.
	@Test
	public void categoryNameRequired() throws Exception {
		ProductCategory category = productCategories.createProductCategory(null, "001", null);

		assertFalse(productCategories.contain(category));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.categoryName.required"));
	}
	
	//ProductCategory.categoryName.length=ProductCategoryName is longer than 32.
	public void categoryNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductCategory category = productCategories.createProductCategory(null, "001", stringlength);

		assertNull(category);
		assertFalse(productCategories.contain(category));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.categoryName.length"));
	}
	
	//ProductCategory.description=Description
	//ProductCategory.description.length=Description is longer than 128.
	public void descriptionLength() throws Exception {
		//Data type Code = 128 char
		String stringlength = "";
		while (stringlength.length() <=128) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductCategory category = productCategories.createProductCategory(null, "001", "ProductCategory1", stringlength, null, null, null);

		assertNull(category);
		assertFalse(productCategories.contain(category));
		assertFalse(productCategories.getErrors().isEmpty());
		assertNotNull(productCategories.getErrors().getError("ProductCategory.description.length"));
	}
	
	//ProductCategory.published=Published
	//ProductCategory.published.required=Published is required.
	
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = false;
		ProductCategory category = productCategories.createProductCategory(null, "001", "ProductCategory1");
		
		assertTrue(category.getPublished().equals(defaultValue));
		assertTrue(productCategories.getErrors().isEmpty());
	}
	
	//ProductCategory.imageUrl180x130.required=ImageUrl180x130 is required.
	@Test
	public void imageUrl180x130DefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		String defaultValue="/css-specific/images/noimage180.jpg";
		//Boolean defaultValue = false;
		ProductCategory category = productCategories.createProductCategory(null, "001", "ProductCategory1");
		
		assertTrue(category.getImageUrl180x130().equals(defaultValue));
		assertTrue(productCategories.getErrors().isEmpty());
	}
	


	@After
	public void afterTest() throws Exception {
		for (ProductCategory productProductCategory : productCategories.getList()) {
			productCategories.remove(productProductCategory);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}