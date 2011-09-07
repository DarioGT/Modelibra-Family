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
package twoadw.website.questioncategory;

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
 * JUnit tests for QuestionCategories.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class QuestionCategoriesTest {

	private static QuestionCategories questionCategories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		 questionCategories = TwoadwTest.getSingleton().getTwoadw().getWebsite().getQuestionCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		questionCategories.getErrors().empty();
	}

	@Test
	public void questionCategoryRequired() throws Exception {
		QuestionCategory qc1=questionCategories.createQuestionCategory(null, "name");
		QuestionCategory qc2=questionCategories.createQuestionCategory(null, "name1");
		
		assertTrue(questionCategories.contain(qc1));
		assertTrue(questionCategories.contain(qc2));
		assertTrue(questionCategories.getErrors().isEmpty());
	}
	@Test
	public void questionCategoryNameRequired() throws Exception {
		QuestionCategory qc1=questionCategories.createQuestionCategory(null, null);
		assertFalse(questionCategories.contain(qc1));
		assertFalse(questionCategories.getErrors().isEmpty());
	}
	@Test
	public void questionCategoryNameUnique() throws Exception {
		QuestionCategory qc1=questionCategories.createQuestionCategory(null, "name");
		QuestionCategory qc2=questionCategories.createQuestionCategory(null, "name");
		assertTrue(questionCategories.contain(qc1));
		assertFalse(questionCategories.contain(qc2));
		assertFalse(questionCategories.getErrors().isEmpty());
		assertNotNull(questionCategories.getErrors().getError("QuestionCategory.id.unique"));
	}
	@Test
	public void questionCategoryNameLenght() throws Exception {
		String name="";
		while(name.length()<=32){
			name+="1";
		}
		QuestionCategory qc1=questionCategories.createQuestionCategory(null, name);
		assertFalse(questionCategories.contain(qc1));
		assertFalse(questionCategories.getErrors().isEmpty());
		assertNotNull(questionCategories.getErrors().getError("QuestionCategory.name.length"));
		
	}
	@Test
	public void questionCategoryPublishedDefaultValue() throws Exception {
		Boolean bool=false;
		QuestionCategory qc1=questionCategories.createQuestionCategory(null, "name");
		assertEquals(bool,(Boolean)qc1.getPublished());
	}
	@After
	public void afterTest() throws Exception {
		for (QuestionCategory questionCategory : questionCategories.getList()) {
			questionCategories.remove(questionCategory);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}