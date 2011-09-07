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
package twoadw.reference.state;

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
import twoadw.reference.country.Countries;
import twoadw.reference.country.Country;

/**
 * JUnit tests for States.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class StatesTest {

	private static States states;
	
	private static Countries countries;
	
	private static Country sampleCountry;

	@BeforeClass
	public static void beforeTests() throws Exception {
		countries = TwoadwTest.getSingleton().getTwoadw().getReference().getCountries();
		sampleCountry = countries.createCountry("CA", "Canada");
		states = sampleCountry.getStates();
	}

	@Before
	public void beforeTest() throws Exception {
		states.getErrors().empty();
	}

	@Test
	public void stateCreated() throws Exception {
		State state01 = states.createState(sampleCountry, "QC", "Québec");
		State state02 = states.createState(sampleCountry, "ON", "Ontario");

		assertTrue(states.contain(state01));
		assertTrue(states.contain(state02));
		assertTrue(states.getErrors().isEmpty());
	}
	
	@Test
	public void stateUpdate() throws Exception {
		State state = states.createState(sampleCountry, "QC", "Québec");
		State stateCopy = state.copy();
		stateCopy.setStateCode("QUE");
		stateCopy.setStateName("Quebec");

		assertTrue(state.equals(stateCopy));
		assertTrue(state.equalOid(stateCopy));
		assertFalse(state.equalProperties(stateCopy));
	
		states.update(state, stateCopy);
		assertTrue(states.getErrors().isEmpty());
	}
	
	@Test
	public void stateEquality() throws Exception {
		State state = states.createState(sampleCountry, "QC", "Québec");
		State stateCopy = state.copy();

		assertEquals(state,stateCopy);
		assertNotSame(state,stateCopy);
		assertTrue(states.getErrors().isEmpty());
	}
	
	//State.id.unique=State identifier ([stateCode] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		State state = states.createState(sampleCountry, "QC", "Québec");
		State stateNotUnique = states.createState(sampleCountry, "QC", "Québec");

		assertFalse(states.contain(stateNotUnique));
		assertNotNull(states.getErrors()
				.getError("State.id.unique"));
	}
	
	//State.stateCode.required=StateCode is required.
	@Test
	public void stateCodeRequired() throws Exception {
		State state = states.createState(sampleCountry, null, "Québec");

		assertFalse(states.contain(state));
		assertNotNull(states.getErrors()
				.getError("State.stateCode.required"));
	}
	
	//State.stateCode.length=StateCode is longer than 16.
	@Test
	public void stateCodeLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		State state = states.createState(sampleCountry, stringlength, "Québec");

		assertFalse(states.contain(state));
		assertNotNull(states.getErrors().getError(
				"State.stateCode.length"));
	}
	
	//	State.stateName.required=StateName is required.
	@Test
	public void stateNameeRequired() throws Exception {
		State state = states.createState(sampleCountry, "QC", null);

		assertFalse(states.contain(state));
		assertNotNull(states.getErrors()
				.getError("State.stateName.required"));
	}
	
	//State.stateName.length=StateName is longer than 64.
	@Test
	public void stateNameLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}		
		
		State state = states.createState(sampleCountry, "QC", stringlength);

		assertFalse(states.contain(state));
		assertNotNull(states.getErrors().getError(
				"State.stateName.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (State state : states.getList()) {
			states.remove(state);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		countries.remove(sampleCountry);
		TwoadwTest.getSingleton().close();
	}

}