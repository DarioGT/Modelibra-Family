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
package modelibra.designer.metaconceptgraphic;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

/**
 * JUnit tests for MetaConceptGraphics.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaConceptGraphicsTest {

	private static MetaConceptGraphics metaConceptGraphics;

	@BeforeClass
	public static void beforeMetaConceptGraphics() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// metaConceptGraphics =
		// ModelibraTest.getSingleton().getModelibra().getDesigner().getMetaConceptGraphics();
	}

	@Before
	public void beforeTest() throws Exception {
		metaConceptGraphics.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		OutTester.outputText("=== Test: Test Name ===");
		// to do
		assertFalse(metaConceptGraphics.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		metaConceptGraphics.getErrors().output("MetaConceptGraphics");
	}

}