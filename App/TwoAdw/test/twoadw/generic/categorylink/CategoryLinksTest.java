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
package twoadw.generic.categorylink;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import twoadw.TwoadwTest;

/**
 * JUnit tests for CategoryLinks.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CategoryLinksTest {

	private static CategoryLinks categoryLinks;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		categoryLinks = TwoadwTest.getSingleton().getTwoadw().getGeneric().getCategoryLinks();
	}

	@Before
	public void beforeTest() throws Exception {
		categoryLinks.getErrors().empty();
	}

	@Test
	public void createRequiredCategory() throws Exception {
		CategoryLink category01 = categoryLinks.createCategoryLink(null, "catlink1");
		CategoryLink category02 = categoryLinks.createCategoryLink(null, "catlink2");
		CategoryLink category03 = categoryLinks.createCategoryLink(null, "catlink3");
		CategoryLink category01_01 = categoryLinks.createCategoryLink(category01, "subcatlink1");
		CategoryLink category01_02 = categoryLinks.createCategoryLink(category01_01, "subcatlink2");
		CategoryLink category01_03 = categoryLinks.createCategoryLink(category01_02, "subcatlink3");
		
		assertTrue(categoryLinks.contain(category01));
		assertTrue(categoryLinks.contain(category02));
		assertTrue(categoryLinks.contain(category03));
		assertTrue(categoryLinks.contain(category01_01));
		assertTrue(categoryLinks.contain(category01_02));
		assertTrue(categoryLinks.contain(category01_03));
		assertTrue(categoryLinks.getErrors().isEmpty());
	}
	
	@Test
	public void createFullCategory() throws Exception {
		CategoryLink category01 = categoryLinks.createCategoryLink(null, "catlink1", true);
		CategoryLink category02 = categoryLinks.createCategoryLink(null, "catlink2", false);
		CategoryLink category03 = categoryLinks.createCategoryLink(null, "catlink3", true);
		CategoryLink category01_01 = categoryLinks.createCategoryLink(category01, "subcatlink1", true);
		CategoryLink category01_02 = categoryLinks.createCategoryLink(category01_01, "subcatlink2", false);
		CategoryLink category01_03 = categoryLinks.createCategoryLink(category01_02, "subcatlink3", true);
		
		assertTrue(categoryLinks.contain(category01));
		assertTrue(categoryLinks.contain(category02));
		assertTrue(categoryLinks.contain(category03));
		assertTrue(categoryLinks.contain(category01_01));
		assertTrue(categoryLinks.contain(category01_02));
		assertTrue(categoryLinks.contain(category01_03));
		assertTrue(categoryLinks.getErrors().isEmpty());
	}
	
	@Test
	public void categoryEquality()throws Exception{
		CategoryLink category = categoryLinks.createCategoryLink(null, "catlink1", true);
		CategoryLink categoryCopy= category.copy();
		
		assertEquals(category,categoryCopy);
		assertNotSame(category,categoryCopy);
		assertTrue(categoryLinks.getErrors().isEmpty());
	}
	
	@Test
	public void categoryUpdate()throws Exception{
		CategoryLink category = categoryLinks.createCategoryLink(null, "catlink1", true);
		CategoryLink categoryCopy= category.copy();
		categoryCopy.setName("gfafdasdf");
		categoryCopy.setPublished(false);
		
		assertTrue(category.equals(categoryCopy));
		assertTrue(category.equalOid(categoryCopy));
		assertFalse(category.equalProperties(categoryCopy));
		categoryLinks.update(category, categoryCopy);
		assertTrue(categoryLinks.getErrors().isEmpty());
	}

	
	//CategoryLink.id.unique=CategoryLink identifier ([name] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		CategoryLink category01 = categoryLinks.createCategoryLink(null, "catlink1");
		CategoryLink category02 = categoryLinks.createCategoryLink(null, "catlink1");
		CategoryLink category03 = categoryLinks.createCategoryLink(null, "catlink1");

		assertTrue(categoryLinks.contain(category01));
		assertFalse(categoryLinks.contain(category02));
		assertFalse(categoryLinks.contain(category03));
		assertFalse(categoryLinks.getErrors().isEmpty());
		assertNotNull(categoryLinks.getErrors().getError("CategoryLink.id.unique"));
	}
	
	//CategoryLink.name.required=Name is required.
	@Test
	public void categoryNumberRequired() throws Exception {
		CategoryLink category = categoryLinks.createCategoryLink(null, null, true);

		assertFalse(categoryLinks.contain(category));
		assertFalse(categoryLinks.getErrors().isEmpty());
		assertNotNull(categoryLinks.getErrors().getError("CategoryLink.name.required"));
	}
	
	//CategoryLink.name.length=Name is longer than 32.
	@Test
	public void categoryNumberLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		CategoryLink category = categoryLinks.createCategoryLink(null, stringlength, true);

		assertNull(category);
		assertFalse(categoryLinks.contain(category));
		assertFalse(categoryLinks.getErrors().isEmpty());
		assertNotNull(categoryLinks.getErrors().getError("CategoryLink.name.length"));
	}
	
	//CategoryLink.published.required=Published is required.
	
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = true;
		CategoryLink category = categoryLinks.createCategoryLink(null, "moo");
		
		assertTrue(category.getPublished().equals(defaultValue));
		assertTrue(categoryLinks.getErrors().isEmpty());
	}
	
	@After
	public void afterTest() throws Exception {
		for (CategoryLink categoryLink : categoryLinks.getList()) {
			categoryLinks.remove(categoryLink);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}