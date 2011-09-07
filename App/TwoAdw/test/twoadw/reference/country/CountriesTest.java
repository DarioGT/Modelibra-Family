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
package twoadw.reference.country;

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
 * JUnit tests for Countries.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CountriesTest {

	private static Countries countries;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		countries = TwoadwTest.getSingleton().getTwoadw().getReference().getCountries();
	}

	@Before
	public void beforeTest() throws Exception {
		countries.getErrors().empty();
	}

	@Test
	public void countryCreated() throws Exception {
		Country countryName01 = countries.createCountry("AL",
				"Albania");
		Country countryName02 = countries.createCountry("DZ",
				"Algeria");

		assertTrue(countries.contain(countryName01));
		assertTrue(countries.contain(countryName02));
		assertTrue(countries.getErrors().isEmpty());
	}
	
	@Test
	public void countryUpdate() throws Exception {
		Country countryName01 = countries.createCountry("AL",
				"Albania");
		Country countryName02 = countryName01.copy();
		countryName02.setCountryCode("CA");
		countryName02.setCountryName("Canada");

		assertTrue(countryName01.equals(countryName02));
		assertTrue(countryName01.equalOid(countryName02));
		assertFalse(countryName01.equalProperties(countryName02));
	
		countries.update(countryName01, countryName02);
		assertTrue(countries.getErrors().isEmpty());
	}
	
	@Test
	public void countryEquality() throws Exception {
		Country countryName01 = countries.createCountry("AL",
				"Albania");
		Country countryName02 = countryName01.copy();

		assertEquals(countryName01,countryName02);
		assertNotSame(countryName01,countryName02);
		assertTrue(countries.getErrors().isEmpty());
	}

	@Test
	public void codeRequired() throws Exception {
		Country countryName = countries.createCountry(null,
				"Albania");

		assertFalse(countries.contain(countryName));
		assertNotNull(countries.getErrors().getError(
				"Country.countryCode.required"));
	}

	@Test
	public void countryRequired() throws Exception {
		Country countryName = countries.createCountry("AL", null);

		assertFalse(countries.contain(countryName));
		assertNotNull(countries.getErrors().getError(
				"Country.countryName.required"));
	}

	@Test
	public void codeUnique() throws Exception {
		String code = "AL";
		countries.createCountry(code, "Albania");
		Country notUniqueCountryName = countries.createCountry(code,
				"Albania");

		assertFalse(countries.contain(notUniqueCountryName));
		assertNotNull(countries.getErrors()
				.getError("Country.id.unique"));
	}

	@Test
	public void codeLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Country countryName = countries.createCountry(stringlength,
				"Albania");

		assertFalse(countries.contain(countryName));
		assertNotNull(countries.getErrors().getError(
				"Country.countryCode.length"));
	}
	
	@Test
	public void countryLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		
		Country countryName = countries.createCountry("AL",
				stringlength);

		assertFalse(countries.contain(countryName));
		assertNotNull(countries.getErrors().getError(
				"Country.countryName.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (Country country : countries.getList()) {
			countries.remove(country);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}