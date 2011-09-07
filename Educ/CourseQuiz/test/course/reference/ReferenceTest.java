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
package course.reference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.util.OutTester;

import course.CourseTest;
import course.reference.countrylanguage.CountryLanguages;
import course.reference.questiontype.QuestionTypes;
import course.reference.securityrole.SecurityRoles;

/**
 * Reference model tests.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class ReferenceTest {

	private static Log log = LogFactory.getLog(ReferenceTest.class);

	private Reference reference;

	public ReferenceTest() {
		super();
		try {
			begin();
		} catch (Exception e) {
			log.error("Error in ReferenceTest(): " + e.getMessage());
		}
	}

	private void begin() throws Exception {
		reference = CourseTest.getSingleton().getCourse().getReference();
	}

	public Reference getReference() {
		return reference;
	}

	private void end() {
		reference.close();
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	private void initReference() {
		initSecurityRoles();
		initCountryLanguages();
		initQuestionTypes();
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private void initSecurityRoles() {
		outputMessage("??????? initSecurityRoles: creation of SecurityRoles ???????");

		SecurityRoles securityRoles = reference.getSecurityRoles();

		outputErrors(securityRoles, "SecurityRole Add Errors");
	}

	private void initCountryLanguages() {
		outputMessage("??????? initCountryLanguages: creation of CountryLanguages ???????");

		CountryLanguages countryLanguages = reference.getCountryLanguages();

		outputErrors(countryLanguages, "CountryLanguage Add Errors");
	}

	private void initQuestionTypes() {
		outputMessage("??????? initQuestionTypes: creation of QuestionTypes ???????");

		QuestionTypes questionTypes = reference.getQuestionTypes();

		outputErrors(questionTypes, "QuestionType Add Errors");
	}

	private void test01() {
		outputMessage("??????? test01: test name ???????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		ReferenceTest referenceTest = null;
		try {
			referenceTest = new ReferenceTest();
			if (false) {
				referenceTest.initReference();
			}
			referenceTest.test();
		} catch (Exception e) {
			log.error("Error in ReferenceTest.main: " + e.getMessage());
		} finally {
			referenceTest.end();
		}
	}

}
