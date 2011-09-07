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
package twoadw.website.manufacturer;

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
 * JUnit tests for Manufacturers.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ManufacturersTest {

	private static Manufacturers manufacturers;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		manufacturers = TwoadwTest.getSingleton().getTwoadw().getWebsite().getManufacturers();
	}

	@Before
	public void beforeTest() throws Exception {
		manufacturers.getErrors().empty();
	}

	@Test
	public void createRequiredManufacturer() throws Exception {
		Manufacturer manufacturer01 = manufacturers.createManufacturer("001", "Acme01");
		Manufacturer manufacturer02 = manufacturers.createManufacturer("002", "Acme02");
		Manufacturer manufacturer03 = manufacturers.createManufacturer("003", "Acme03");
		
		assertTrue(manufacturers.contain(manufacturer01));
		assertTrue(manufacturers.contain(manufacturer02));
		assertTrue(manufacturers.contain(manufacturer03));
		assertTrue(manufacturers.getErrors().isEmpty());
		
	}
	
	@Test
	public void createFullManufacturer() throws Exception {
		Manufacturer manufacturer01 = manufacturers.createManufacturer("001", "Acme01", "http://www.google.ca", "http://www.google.ca", "contact@hotmail.com", "contact@hotmail.com");
		Manufacturer manufacturer02 = manufacturers.createManufacturer("002", "Acme02", "http://www.google.ca", "http://www.google.ca", "contact@hotmail.com", "contact@hotmail.com");
		Manufacturer manufacturer03 = manufacturers.createManufacturer("003", "Acme03", "http://www.google.ca", "http://www.google.ca", "contact@hotmail.com", "contact@hotmail.com");
		
		assertTrue(manufacturers.contain(manufacturer01));
		assertTrue(manufacturers.contain(manufacturer02));
		assertTrue(manufacturers.contain(manufacturer03));
		assertTrue(manufacturers.getErrors().isEmpty());
		
	}
	
	@Test
	public void manufacturerEquality()throws Exception{
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "Acme01", "http://www.google.ca", "http://www.google.ca", "contact@hotmail.com", "contact@hotmail.com");
		Manufacturer manufacturerCopy= manufacturer.copy();
		
		assertEquals(manufacturer,manufacturerCopy);
		assertNotSame(manufacturer,manufacturerCopy);
		assertTrue(manufacturers.getErrors().isEmpty());
	}
	
	@Test
	public void manufacturerUpdate()throws Exception{
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "Acme01", "http://www.google.ca", "http://www.google.ca", "contact@hotmail.com", "contact@hotmail.com");
		Manufacturer manufacturerCopy= manufacturer.copy();
		manufacturerCopy.setManufacturerNumber("002");
		manufacturerCopy.setName("name");
		manufacturerCopy.setWebsiteUrl("http://www.support.ca");
		manufacturerCopy.setSupportUrl("http://www.support.ca");
		manufacturerCopy.setContactEmail("dafsd@gasd.com");
		manufacturerCopy.setContactName("name");
		
		assertTrue(manufacturer.equals(manufacturerCopy));
		assertTrue(manufacturer.equalOid(manufacturerCopy));
		assertFalse(manufacturer.equalProperties(manufacturerCopy));
		manufacturers.update(manufacturer, manufacturerCopy);
		assertTrue(manufacturers.getErrors().isEmpty());
	}
	
	@Test
	public void manufacturerIdUnique() throws Exception {
		Manufacturer manufacturer01 = manufacturers.createManufacturer("001", "Acme01");
		Manufacturer manufacturer02 = manufacturers.createManufacturer("001", "Acme02");
		
		assertTrue(manufacturers.contain(manufacturer01));
		assertNull(manufacturer02);
		assertFalse(manufacturers.contain(manufacturer02));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.id.unique"));
		
	}
	
	@Test
	public void manufacturerNumberRequired() throws Exception {
		Manufacturer manufacturer = manufacturers.createManufacturer(null, "Acme01");
		
		assertFalse(manufacturers.contain(manufacturer));
		assertNull(manufacturer);
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.manufacturerNumber.required"));
		
	}
	
	@Test
	public void manufacturerNumberLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Manufacturer manufacturer = manufacturers.createManufacturer(stringlength, "Acme01");

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError("Manufacturer.manufacturerNumber.length"));
	}
	
	@Test
	public void manufacturerNameRequired() throws Exception {
		Manufacturer manufacturer = manufacturers.createManufacturer("001" , null);
		
		assertFalse(manufacturers.contain(manufacturer));
		assertNull(manufacturer);
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.name.required"));
		
	}
	
	@Test
	public void manufacturerNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Manufacturer manufacturer = manufacturers.createManufacturer("001", stringlength);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError("Manufacturer.name.length"));
	}
	
	@Test
	public void websiteUrlLength() throws Exception {
		//Data type Code = 96 char
		String stringlength = "http://www.";
		while (stringlength.length() <=96) {
			stringlength = stringlength + "1"; 
		}		
		
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", stringlength, null, null, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError("Manufacturer.websiteUrl.length"));
	}
	
	@Test
	public void websiteUrlValidation() throws Exception {
		String link = "This is not a URL!";
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", link, null, null, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.websiteUrl.validation"));
	}
	
	@Test
	public void supportUrlLength() throws Exception {
		//Data type Code = 96 char
		String stringlength = "http://www.";
		while (stringlength.length() <=96) {
			stringlength = stringlength + "1"; 
		}		
		
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", null, stringlength, null, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError("Manufacturer.supportUrl.length"));
	}
	
	@Test
	public void supportUrlValidation() throws Exception {
		String link = "This is not a URL!";
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", null, link, null, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.supportUrl.validation"));
	}
	
	@Test
	public void contactEmailLength() throws Exception {
		//Data type Code = 80 char - 4char (.com) = 76
		String stringlength = "email@";
		while (stringlength.length() <=76) {
			stringlength = stringlength + "1"; 
		}		
		stringlength = stringlength + ".com";
		
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", null, null, stringlength, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError("Manufacturer.contactEmail.length"));
	}
	
	@Test
	public void contactEmailValidation() throws Exception {
		String link = "This is not a Email!";
		Manufacturer manufacturer = manufacturers.createManufacturer("001", "acme", null, null, link, null);

		assertNull(manufacturer);
		assertFalse(manufacturers.contain(manufacturer));
		assertFalse(manufacturers.getErrors().isEmpty());
		assertNotNull(manufacturers.getErrors().getError(
				"Manufacturer.contactEmail.validation"));
	}

	@After
	public void afterTest() throws Exception {
		for (Manufacturer manufacturer : manufacturers.getList()) {
			manufacturers.remove(manufacturer);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}