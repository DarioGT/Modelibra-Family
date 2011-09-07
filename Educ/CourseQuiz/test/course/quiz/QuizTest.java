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
package course.quiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.util.OutTester;

import course.CourseTest;
import course.quiz.member.Members;
import course.quiz.test.Tests;

/**
 * Quiz model tests.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class QuizTest {

	private static Log log = LogFactory.getLog(QuizTest.class);

	private Quiz quiz;

	public QuizTest() {
		super();
		try {
			begin();
		} catch (Exception e) {
			log.error("Error in QuizTest(): " + e.getMessage());
		}
	}

	private void begin() throws Exception {
		quiz = CourseTest.getSingleton().getCourse().getQuiz();
	}

	public Quiz getQuiz() {
		return quiz;
	}

	private void end() {
		quiz.close();
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	private void initQuiz() {
		initTests();
		initMembers();
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private void initTests() {
		outputMessage("??????? initTests: creation of Tests ???????");

		Tests tests = quiz.getTests();

		outputErrors(tests, "Test Add Errors");
	}

	private void initMembers() {
		outputMessage("??????? initMembers: creation of Members ???????");

		Members members = quiz.getMembers();

		outputErrors(members, "Member Add Errors");
	}

	private void test01() {
		outputMessage("??????? test01: test name ???????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		QuizTest quizTest = null;
		try {
			quizTest = new QuizTest();
			if (false) {
				quizTest.initQuiz();
			}
			quizTest.test();
		} catch (Exception e) {
			log.error("Error in QuizTest.main: " + e.getMessage());
		} finally {
			quizTest.end();
		}
	}

}
