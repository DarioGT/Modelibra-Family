package dmeduc.reference.countrylanguage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.DmEducTest;

public class CountryLanguagesTest {

	private static CountryLanguages countryLanguages;

	@BeforeClass
	public static void beforeTests() throws Exception {
		countryLanguages = DmEducTest.getSingleton().getDmEduc().getReference()
				.getCountryLanguages();
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

	@Test
	public void codeLength() throws Exception {
		String code = "eng";
		CountryLanguage countryLanguage = countryLanguages
				.createCountryLanguage(code, "english");

		assertFalse(countryLanguages.contain(countryLanguage));
		assertNotNull(countryLanguages.getErrors().getError(
				"CountryLanguage.code.length"));
	}

	@Test
	public void languageLength() throws Exception {
		String language = "Bosnian language is one of the three official languages of the country";
		CountryLanguage countryLanguage = countryLanguages
				.createCountryLanguage("ba", language);

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
		DmEducTest.getSingleton().close();
	}

}
