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
package twoadw.website.question;

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

/**
 * JUnit tests for Questions.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class QuestionsTest {
	
	private Product product1Questions;
	private Product product2Questions;
	private Products products;
    private static Questions questions;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		 questions = TwoadwTest.getSingleton().getTwoadw().getWebsite().getQuestions();
	}

	@Before
	public void beforeTest() throws Exception {
		questions.getErrors().empty();
	}

	@Test
	public void questionRequired() throws Exception {
		Question q1=questions.createQuestion(null, "questionText");
		Question q2=questions.createQuestion(null, "questionText1");
		
		assertTrue(questions.contain(q1));
		assertTrue(questions.contain(q2));
		assertTrue(questions.getErrors().isEmpty());
		
	}
	@Test
	public void questionCreated() throws Exception {
		Question q1=questions.createQuestion(product1Questions, "questionText");
		Question q2=questions.createQuestion(product2Questions, "questionText");
		
		assertTrue(questions.contain(q1));
		assertTrue(questions.contain(q2));
		assertTrue(questions.getErrors().isEmpty());
	}
	@Test
	public void questionUpdate() throws Exception {
		Question q1=questions.createQuestion(product1Questions, "questionText");
		Question copyQuestion=q1.copy();
		copyQuestion.setProduct(product2Questions);
		
		assertTrue(q1.equals(copyQuestion));
		assertTrue(q1.equalOid(copyQuestion));
		assertTrue(q1.equalProperties(copyQuestion));
		questions.update(q1, copyQuestion);
		assertTrue(questions.getErrors().isEmpty());
		
	}
	@Test
	public void questionEquallity() throws Exception {
		Question q1=questions.createQuestion(product1Questions, "questionText");
		Question copyQuestion=q1.copy();
		assertEquals(q1, copyQuestion);
		assertNotSame(q1, copyQuestion);
		assertTrue(questions.getErrors().isEmpty());
	}
	@Test
	public void questionTextREquired() throws Exception {
		Question q1=questions.createQuestion(product1Questions,null);
		assertFalse(questions.contain(q1));
		assertFalse(questions.getErrors().isEmpty());
		assertNotNull(questions.getErrors().getError("Question.questionText.required"));
	}
	@Test
	public void questionTextLength() throws Exception {
		String questionText="";
		while (questionText.length() <=1022) {
			questionText = questionText + "1"; 
		}	
		Question q1= questions.createQuestion(product1Questions, questionText);
		assertFalse(questions.contain(q1));
		assertFalse(questions.getErrors().isEmpty());
		assertNotNull(questions.getErrors().getError("Question.questionText.length"));
	}
	@Test
	public void questionPublishedDefaultValue() throws Exception {
		Question q1=questions.createQuestion(product1Questions,"questionText");
		boolean bool=false;
		assertEquals((Boolean)q1.getPublished(), (Boolean)bool);
		
	}
	@After
	public void afterTest() throws Exception {
		for (Question question : questions.getList()) {
			questions.remove(question);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}