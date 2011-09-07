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
package sales.cheesestore.discount;

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

import sales.SalesTest;

/**
 * JUnit tests for Discounts.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public class DiscountsTest {

	private static Discounts discounts;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		// discounts = SalesTest.getSingleton().getSales().getCheeseStore().getDiscounts();
	}

	@Before
	public void beforeTest() throws Exception {
		discounts.getErrors().empty();
	}

	@Test
	public void testName() throws Exception {
		// to do
	}

	@After
	public void afterTest() throws Exception {
		for (Discount discount : discounts.getList()) {
			discounts.remove(discount);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		SalesTest.getSingleton().close();
	}

}