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
package course.reference.questiontype;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

import course.CourseTest;

/**
 * JUnit tests for QuestionTypes.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class QuestionTypesTest {

	private static QuestionTypes questionTypes;

	@BeforeClass
	public static void beforeQuestionTypes() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		// questionTypes = CourseTest.getSingleton().getCourse().getReference().getQuestionTypes();
	}

	@Before
	public void beforeTest() throws Exception {
		questionTypes.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		OutTester.outputText("=== Test: Test Name ===");
		// to do
		assertFalse(questionTypes.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		questionTypes.getErrors().output("QuestionTypes");
	}

}