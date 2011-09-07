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
package course.quiz.question;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

/**
 * JUnit tests for Questions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class QuestionsTest {

	private static Questions questions;

	@BeforeClass
	public static void beforeQuestions() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// questions =
		// CourseTest.getSingleton().getCourse().getQuiz().getQuestions();
	}

	@Before
	public void beforeTest() throws Exception {
		questions.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		OutTester.outputText("=== Test: Test Name ===");
		// to do
		assertFalse(questions.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		questions.getErrors().output("Questions");
	}

}