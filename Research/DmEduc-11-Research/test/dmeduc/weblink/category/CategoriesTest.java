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
package dmeduc.weblink.category;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

import dmeduc.DmEducTest;
import dmeduc.weblink.question.Question;
import dmeduc.weblink.question.Questions;

/**
 * JUnit tests for Categories.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-05
 */
public class CategoriesTest {

	private static Categories categories;

	@BeforeClass
	public static void beforeCategories() throws Exception {
		categories = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		categories.getErrors().empty();
	}

	@Test
	public void nullName() throws Exception {
		OutTester.outputText("=== Test: Null Name ===");
		categories.createCategory(null, null);
		assertFalse(categories.getErrors().isEmpty());
	}

	@Test
	public void nameTooLong() throws Exception {
		OutTester.outputText("=== Test: Name Too Long ===");
		String longName = "The definitions never made much sense, as a brief journey into history shows. ";
		categories.createCategory(null, longName);
		assertFalse(categories.getErrors().isEmpty());
	}

	@Test
	public void softwareFramework() throws Exception {
		OutTester.outputText("=== Test: Software Framework ===");
		Category category = categories.getCategory("name", "Software");
		if (category != null) {
			Categories subcategories = category.getCategories();
			if (!subcategories.isEmpty()) {
				category = subcategories.getCategory("name", "Framework");
				if (category != null) {
					category.output("Framework");
				} else {
					OutTester.outputText("Framework category does not exist.");
				}
			} else {
				OutTester
						.outputText("Software category does not have subcategories.");
			}
		} else {
			OutTester.outputText("Software category does not exist.");
		}
		assertTrue(categories.getErrors().isEmpty());
	}

	@Test
	public void frameworkQuestion() throws Exception {
		OutTester.outputText("=== Test: Framework Question ===");
		Category category = categories
				.getReflexiveCategory("name", "Framework");
		if (category != null) {
			Questions questions = category.getQuestions();
			Question question = questions.getQuestion("text",
					"Is framework a hard work?");
			if (question == null) {
				question = questions.createQuestion(category,
						"Is framework a hard work?");
			}
			question.output("Framework Question");
		} else {
			OutTester.outputText("Framework category does not exist.");
		}
		assertTrue(categories.getErrors().isEmpty());
	}

	@Test
	public void modelibraFramework() throws Exception {
		OutTester.outputText("=== Test: Modelibra Framework ===");
		Category category = categories.getCategoryByName("Framework");
		if (category != null) {
			Categories subcategories = category.getCategories();
			categories = subcategories;
			Category modelibraCategory = subcategories
					.getCategoryByName("Modelibra");
			if (modelibraCategory == null) {
				Category subcategory = new Category(category);
				subcategory.setName("Modelibra");
				subcategory.setApproved(true);
				subcategories.add(subcategory);
				assertTrue(subcategories.getErrors().isEmpty());
			} else {
				OutTester
						.outputText("Modelibra framework category already exists.");
			}
		} else {
			OutTester.outputText("Framework category does not exist.");
		}
	}

	@After
	public void afterTest() throws Exception {
		categories.getErrors().output("Categories");
	}

}