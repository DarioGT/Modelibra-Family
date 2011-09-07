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
package twoadw.website.rebate;

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

import twoadw.TwoadwTest;
import twoadw.website.productcomment.ProductComment;
import twoadw.website.question.Question;

/**
 * JUnit tests for Rebates.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class RebatesTest {

	private static Rebates rebates;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		rebates = TwoadwTest.getSingleton().getTwoadw().getWebsite().getRebates();
	}

	@Before
	public void beforeTest() throws Exception {
		rebates.getErrors().empty();
	}

	@Test
	public void rebateRequiredCreated() throws Exception {
		Rebate rebate01 = rebates.createRebate("rebateName1");
		Rebate rebate02 = rebates.createRebate("rebateName2");
		Rebate rebate03 = rebates.createRebate("rebateName3");
		
		assertTrue(rebates.contain(rebate01));
		assertTrue(rebates.contain(rebate02));
		assertTrue(rebates.contain(rebate03));
		assertTrue(rebates.getErrors().isEmpty());
	}
	
	@Test
	public void rebateCreated() throws Exception {
		Rebate rebate01 = rebates.createRebate("rebateName1", "description", Double.valueOf("2.33"), true, true,
				Long.valueOf("1"), new EasyDate(2009, 1, 12),new EasyDate(2010, 1, 12));
		Rebate rebate02 = rebates.createRebate("rebateName2", "description", Double.valueOf("2.33"), true, true,
				Long.valueOf("1"), new EasyDate(2009, 1, 12),new EasyDate(2010, 1, 12));
		Rebate rebate03 = rebates.createRebate("rebateName3", "description", Double.valueOf("2.33"), true, true,
				Long.valueOf("1"), new EasyDate(2009, 1, 12),new EasyDate(2010, 1, 12));

		assertTrue(rebates.contain(rebate01));
		assertTrue(rebates.contain(rebate02));
		assertTrue(rebates.contain(rebate03));
		assertTrue(rebates.getErrors().isEmpty());
	}
	
	@Test
	public void rebateEquality()throws Exception{
		Rebate rebate = rebates.createRebate("rebateName1");
		Rebate rebateCopy = rebate.copy();
		
		assertEquals(rebate,rebateCopy);
		assertNotSame(rebate,rebateCopy);
		assertTrue(rebates.getErrors().isEmpty());
	}

	
	@Test
	public void rebateUpdate()throws Exception{
		Rebate rebate = rebates.createRebate("rebateName1");
		Rebate rebateCopy = rebate.copy();
		rebateCopy.setRebateName("name");
		rebateCopy.setDescription("desc2");
		rebateCopy.setRebateValue(Double.valueOf("9.99"));
		rebateCopy.setPostalRebate(true);
		rebateCopy.setPercentRebate(true);
		rebateCopy.setRebatePriority(Long.valueOf("99"));
		rebateCopy.setStart(new EasyDate(2009, 1, 12).getDate());
		rebateCopy.setFinish(new EasyDate(2010, 1, 12).getDate());
		
		assertTrue(rebate.equals(rebateCopy));
		assertTrue(rebate.equalOid(rebateCopy));
		assertFalse(rebate.equalProperties(rebateCopy));
		rebates.update(rebate, rebateCopy);
		assertTrue(rebates.getErrors().isEmpty());
	}
	
	//Rebate.id.unique=Rebate identifier ([rebateName] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		Rebate rebate = rebates.createRebate("rebateName1");
		Rebate rebateNotUnique = rebates.createRebate("rebateName1");

		assertNull(rebateNotUnique);
		assertFalse(rebates.contain(rebateNotUnique));
		assertFalse(rebates.getErrors().isEmpty());
		assertNotNull(rebates.getErrors().getError(
				"Rebate.id.unique"));
	}
	
	//Rebate.rebateName.required=RebateName is required.
	@Test
	public void rebateNameRequired() throws Exception {
		Rebate rebate = rebates.createRebate(null);
		
		assertNull(rebate);
		assertFalse(rebates.contain(rebate));
		assertFalse(rebates.getErrors().isEmpty());
		assertNotNull(rebates.getErrors().getError(
				"Rebate.rebateName.required"));
	}
	
	//Rebate.rebateName.length=RebateName is longer than 32.
	@Test
	public void rebateNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Rebate rebate = rebates.createRebate(stringlength);

		assertNull(rebate);
		assertFalse(rebates.contain(rebate));
		assertFalse(rebates.getErrors().isEmpty());
		assertNotNull(rebates.getErrors().getError("Rebate.rebateName.length"));
	}
	
	//Rebate.description.length=Description is longer than 510.
	@Test
	public void descriptionLength() throws Exception {
		//Data type Code = 510 char
		String stringlength = "";
		while (stringlength.length() <=510) {
			stringlength = stringlength + "1"; 
		}		
		
		Rebate rebate = rebates.createRebate("rebateName1", stringlength, Double.valueOf("2.33"), true, true,
				Long.valueOf("1"), new EasyDate(2009, 1, 12),new EasyDate(2010, 1, 12));

		assertNull(rebate);
		assertFalse(rebates.contain(rebate));
		assertFalse(rebates.getErrors().isEmpty());
		assertNotNull(rebates.getErrors().getError("Rebate.description.length"));
	}
	
	//Model Defaut Values:
	/*
	Rebate.rebateValue.required=RebateValue is required.
	Rebate.postalRebate.required=PostalRebate is required.
	Rebate.percentRebate.required=PercentRebate is required.
	Rebate.rebatePriority.required=RebatePriority is required.
	 */
	@Test
	public void modelDefaultValues() throws Exception {
		Rebate rebate = rebates.createRebate("name");
		
		assertNotNull(rebate);
		assertTrue(rebate.getRebateValue().equals(Double.valueOf("0")));
		assertTrue(rebate.getPostalRebate().equals(false));
		assertTrue(rebate.getPercentRebate().equals(false));
		assertTrue(rebate.getRebatePriority().equals(Long.valueOf("0")));
		
		assertTrue(rebates.contain(rebate));
		assertTrue(rebates.getErrors().isEmpty());
		
	}
	
	
	@After
	public void afterTest() throws Exception {
		for (Rebate rebate : rebates.getList()) {
			rebates.remove(rebate);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}