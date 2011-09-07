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
package education.library.writer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import education.EducationTest;

/**
 * JUnit tests for Writers.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class WritersTest {

	private static Writers writers;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// writers =
		// EducationTest.getSingleton().getEducation().getLibrary().getWriters();
	}

	@Before
	public void beforeTest() throws Exception {
		writers.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		// to do
	}

	@After
	public void afterTest() throws Exception {
		for (Writer writer : writers.getList()) {
			writers.remove(writer);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		EducationTest.getSingleton().close();
	}

}