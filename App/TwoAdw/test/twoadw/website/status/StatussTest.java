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
package twoadw.website.status;

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

/**
 * JUnit tests for Statuss.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class StatussTest {

	private static Statuss statuss;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		statuss = TwoadwTest.getSingleton().getTwoadw().getWebsite().getStatuss();
	}

	@Before
	public void beforeTest() throws Exception {
		statuss.getErrors().empty();
	}

	@Test
	public void createStatus() throws Exception {
		Status status1 = statuss.createStatus("1");
		Status status2 = statuss.createStatus("2");
		Status status3 = statuss.createStatus("3");
		
		
		assertTrue(statuss.contain(status1));
		assertTrue(statuss.contain(status2));
		assertTrue(statuss.contain(status3));
		assertTrue(statuss.getErrors().isEmpty());
	}
	
	@Test
	public void statusEquality()throws Exception{
		Status status = statuss.createStatus("1");
		Status statusCopy= status.copy();
		
		assertEquals(status,statusCopy);
		assertNotSame(status,statusCopy);
		assertTrue(statuss.getErrors().isEmpty());
	}
	
	@Test
	public void statusUpdate()throws Exception{
		Status status = statuss.createStatus("1");
		Status statusCopy= status.copy();
		statusCopy.setStatusName("2");
		
		assertTrue(status.equals(statusCopy));
		assertTrue(status.equalOid(statusCopy));
		assertFalse(status.equalProperties(statusCopy));
		statuss.update(status, statusCopy);
		assertTrue(statuss.getErrors().isEmpty());
	}
	
	//Status.id.unique=Status identifier ([statusName] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		Status status1 = statuss.createStatus("1");
		Status status2 = statuss.createStatus("1");

		assertTrue(statuss.contain(status1));
		assertFalse(statuss.contain(status2));
		assertFalse(statuss.getErrors().isEmpty());
		assertNotNull(statuss.getErrors().getError("Status.id.unique"));
	}
	
	//Status.statusName.required=StatusName is required.
	@Test
	public void statusNameRequired() throws Exception {
		Status status = statuss.createStatus(null);

		assertFalse(statuss.contain(status));
		assertFalse(statuss.getErrors().isEmpty());
		assertNotNull(statuss.getErrors().getError("Status.statusName.required"));
	}
	
	//Status.statusName.length=StatusName is longer than 16.
	@Test
	public void roleNumberLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Status status = statuss.createStatus(stringlength);

		assertNull(status);
		assertFalse(statuss.contain(status));
		assertFalse(statuss.getErrors().isEmpty());
		assertNotNull(statuss.getErrors().getError("Status.statusName.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (Status status : statuss.getList()) {
			statuss.remove(status);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}