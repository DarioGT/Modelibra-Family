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
package twoadw.reference.countrylanguage;

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
import twoadw.reference.country.Country;

/**
 * JUnit tests for CountryLanguages.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CountryLanguagesTest {

	private static CountryLanguages countryLanguages;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		countryLanguages = TwoadwTest.getSingleton().getTwoadw().getReference().getCountryLanguages();
	}

	@Before
	public void beforeTest() throws Exception {
		countryLanguages.getErrors().empty();
	}

	@Test
	public void countryLanguagesCreated() throws Exception {
		CountryLanguage countryLanguage01 = countryLanguages
				.createCountryLanguage("en", "english");
		CountryLanguage countryLanguage02 = countryLanguages
				.createCountryLanguage("ba", "bosanski");

		assertTrue(countryLanguages.contain(countryLanguage01));
		assertTrue(countryLanguages.contain(countryLanguage02));
		assertTrue(countryLanguages.getErrors().isEmpty());
	}

	@Test
	public void codeRequired() throws Exception {
		CountryLanguage countryLanguage = countryLanguages
				.createCountryLanguage(null, "english");

		assertFalse(countryLanguages.contain(countryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.code.required"));
	}

	@Test
	public void languageRequired() throws Exception {
		CountryLanguage countryLanguage = countryLanguages
				.createCountryLanguage("en", null);

		assertFalse(countryLanguages.contain(countryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.language.required"));
	}

	@Test
	public void codeUnique() throws Exception {
		String code = "en";
		countryLanguages.createCountryLanguage(code, "english");
		CountryLanguage notUniqueCountryLanguage = countryLanguages
				.createCountryLanguage("en", "english");

		assertFalse(countryLanguages.contain(notUniqueCountryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.id.unique"));
	}
	

	//CountryLanguage.code.length=Code is longer than 16.
	@Test
	public void codeLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		CountryLanguage countryLanguage = countryLanguages.createCountryLanguage(stringlength, "english");

		assertFalse(countryLanguages.contain(countryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.code.length"));
	}
	
	//CountryLanguage.language.length=Language is longer than 32.
	@Test
	public void languageLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		CountryLanguage countryLanguage = countryLanguages.createCountryLanguage("en", stringlength);

		assertFalse(countryLanguages.contain(countryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.language.length"));
	}
	
	@After
	public void afterTest() throws Exception {
		for (CountryLanguage countryLanguage : countryLanguages.getList()) {
			countryLanguages.remove(countryLanguage);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}