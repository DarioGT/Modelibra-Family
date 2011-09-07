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
package twoadw.website.qqcategory;

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
import twoadw.website.question.Question;
import twoadw.website.question.Questions;
import twoadw.website.questioncategory.QuestionCategories;
import twoadw.website.questioncategory.QuestionCategory;

/**
 * JUnit tests for QQCategories.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class QQCategoriesTest {
    private static Question sampleQuestion;
    private static Questions questions;
    private static QuestionCategory sampleQuestionCategory;
    private static QuestionCategories questionCategories;
	private static QQCategories qQCategories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		questions=website.getQuestions();
		sampleQuestion=questions.createQuestion(null, "questionText");
		questionCategories=website.getQuestionCategories();
		sampleQuestionCategory=questionCategories.createQuestionCategory(null, "name");
		 qQCategories = sampleQuestionCategory.getQQCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		qQCategories.getErrors().empty();
	}

	@Test
	public void qQCategoryRequired() throws Exception {
		QQCategory qqC1=qQCategories.createQQCategory(sampleQuestion, sampleQuestionCategory);
		// To be sure that qqC1 is added to  qQCategories.
		assertTrue(qQCategories.contain(qqC1));
		// To be sure that qqC1 is added to  sampleQuestion.
		assertTrue(sampleQuestion.getQQCategories().contain(qqC1));
		// To be sure that qqC1 is added to  sampleQuestionCategory.
		assertTrue(sampleQuestionCategory.getQQCategories().contain(qqC1));
		assertTrue(qQCategories.getErrors().isEmpty());
	}
	@Test
	public void qQCategoryRemoved() throws Exception {
		QQCategory qqC1=qQCategories.createQQCategory(sampleQuestion, sampleQuestionCategory);
		// To be sure that qqC1 is added to  qQCategories.
		assertTrue(qQCategories.contain(qqC1));
		// To be sure that qqC1 is added to  sampleQuestion.
		assertTrue(sampleQuestion.getQQCategories().contain(qqC1));
		// To be sure that qqC1 is added to  sampleQuestionCategory.
		assertTrue(sampleQuestionCategory.getQQCategories().contain(qqC1));
		qQCategories.remove(qqC1);
		// To be sure that qqC1 is removed to  qQCategories.
		assertFalse(qQCategories.contain(qqC1));
		// To be sure that qqC1 is removed to  sampleQuestion.
		assertFalse(sampleQuestion.getQQCategories().contain(qqC1));
		// To be sure that qqC1 is removed to  sampleQuestionCategory.
		assertFalse(sampleQuestionCategory.getQQCategories().contain(qqC1));
		assertTrue(qQCategories.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (QQCategory qQCategory : qQCategories.getList()) {
			qQCategories.remove(qQCategory);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		questions.remove(sampleQuestion);
		questionCategories.remove(sampleQuestionCategory);
		
		TwoadwTest.getSingleton().close();
	}

}