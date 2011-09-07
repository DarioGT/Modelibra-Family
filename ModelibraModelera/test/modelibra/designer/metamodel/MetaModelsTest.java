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
package modelibra.designer.metamodel;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

/**
 * JUnit tests for MetaModels.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaModelsTest {

	private static MetaModels metaModels;

	@BeforeClass
	public static void beforeMetaModels() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// metaModels =
		// ModelibraTest.getSingleton().getModelibra().getDesigner().getMetaModels();
	}

	@Before
	public void beforeTest() throws Exception {
		metaModels.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		OutTester.outputText("=== Test: Test Name ===");
		// to do
		assertFalse(metaModels.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		metaModels.getErrors().output("MetaModels");
	}

}