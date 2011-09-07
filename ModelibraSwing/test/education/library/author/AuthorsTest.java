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
package education.library.author;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import education.EducationTest;

/**
 * JUnit tests for Authors.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class AuthorsTest {

	private static Authors authors;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// authors =
		// EducationTest.getSingleton().getEducation().getLibrary().getAuthors();
	}

	@Before
	public void beforeTest() throws Exception {
		authors.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		// to do
	}

	@After
	public void afterTest() throws Exception {
		for (Author author : authors.getList()) {
			authors.remove(author);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		EducationTest.getSingleton().close();
	}

}