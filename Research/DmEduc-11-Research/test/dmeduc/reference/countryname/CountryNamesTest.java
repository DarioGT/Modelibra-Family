package dmeduc.reference.countryname;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.DmEducTest;

public class CountryNamesTest {

	private static CountryNames countryNames;

	@BeforeClass
	public static void beforeTests() throws Exception {
		countryNames = DmEducTest.getSingleton().getDmEduc().getReference()
				.getCountryNames();
	}

	@Before
	public void beforeTest() throws Exception {
		countryNames.getErrors().empty();
	}

	@Test
	public void countryNamesCreated() throws Exception {
		CountryName countryName01 = countryNames.createCountryName("AL",
				"Albania");
		CountryName countryName02 = countryNames.createCountryName("DZ",
				"Algeria");

		assertTrue(countryNames.contain(countryName01));
		assertTrue(countryNames.contain(countryName02));
		assertTrue(countryNames.getErrors().isEmpty());
	}

	@Test
	public void codeRequired() throws Exception {
		CountryName countryName = countryNames.createCountryName(null,
				"Albania");

		assertFalse(countryNames.contain(countryName));
		assertNotNull(countryNames.getErrors().getError(
				"CountryName.code.required"));
	}

	@Test
	public void countryRequired() throws Exception {
		CountryName countryName = countryNames.createCountryName("AL", null);

		assertFalse(countryNames.contain(countryName));
		assertNotNull(countryNames.getErrors().getError(
				"CountryName.country.required"));
	}

	@Test
	public void codeUnique() throws Exception {
		String code = "AL";
		countryNames.createCountryName(code, "Albania");
		CountryName notUniqueCountryName = countryNames.createCountryName(code,
				"Albania");

		assertFalse(countryNames.contain(notUniqueCountryName));
		assertNotNull(countryNames.getErrors()
				.getError("CountryName.id.unique"));
	}

	@Test
	public void codeLength() throws Exception {
		String code = "ALB";
		CountryName countryName = countryNames.createCountryName(code,
				"Albania");

		assertFalse(countryNames.contain(countryName));
		assertNotNull(countryNames.getErrors().getError(
				"CountryName.code.length"));
	}

	@Test
	public void countryLength() throws Exception {
		String country = "Former communist country that has become a country with more than one party.";
		CountryName countryName = countryNames.createCountryName("AL", country);

		assertFalse(countryNames.contain(countryName));
		assertNotNull(countryNames.getErrors().getError(
				"CountryName.country.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (CountryName countryName : countryNames.getList()) {
			countryNames.remove(countryName);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().close();
	}

}
