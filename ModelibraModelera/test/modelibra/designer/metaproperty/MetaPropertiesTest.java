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
package modelibra.designer.metaproperty;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.OutTester;

/**
 * JUnit tests for MetaProperties.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaPropertiesTest {

	private static MetaProperties metaProperties;

	@BeforeClass
	public static void beforeMetaProperties() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		// metaProperties =
		// ModelibraTest.getSingleton().getModelibra().getDesigner().getMetaProperties();
	}

	@Before
	public void beforeTest() throws Exception {
		metaProperties.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		OutTester.outputText("=== Test: Test Name ===");
		// to do
		assertFalse(metaProperties.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		metaProperties.getErrors().output("MetaProperties");
	}

}