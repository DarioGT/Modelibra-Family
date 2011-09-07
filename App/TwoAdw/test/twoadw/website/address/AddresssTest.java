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
package twoadw.website.address;

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
import twoadw.website.Website;
import twoadw.website.customer.Customer;
import twoadw.website.customer.Customers;

/**
 * JUnit tests for Addresss.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class AddresssTest {

	private static Customers customers;
	private static Customer sampleCustomer;
	private static Addresss addresss;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		customers = website.getCustomers();
		sampleCustomer = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		addresss = sampleCustomer.getAddresss();
	}

	@Before
	public void beforeTest() throws Exception {
		addresss.getErrors().empty();
	}

	@Test
	public void addressCreatedRequired() throws Exception {
		Address adr01= addresss.createAddress(sampleCustomer, null, "street", "city", "zipCode", "state", "country", "telephone");
		Address adr02= addresss.createAddress(sampleCustomer, "Name", "street", "city", "zipCode", "state", "country", "telephone");
		assertNotNull(adr01);
		assertTrue(addresss.contain(adr01));
		assertNotNull(adr02);
		assertTrue(addresss.contain(adr02));
		assertTrue(addresss.getErrors().isEmpty());
		
	}
	
	@Test
	public void idDefautValue() throws Exception {
		Address address= addresss.createAddress(sampleCustomer, null, "street", "city", "zipCode", "state", "country", "telephone");
		addresss.getErrors().empty();
		String statusValue="Default";
		
		assertTrue(address.getAddressName().equals(statusValue));
		assertTrue(addresss.getErrors().isEmpty());
		
	}
	
	@Test
	public void addressIdUnique() throws Exception {
		String id="001";
		addresss.createAddress(sampleCustomer,id, "2275 rue",
				"QC", "G1K 7P4", "Quebec", "Canada");
		Address adrNotUnique=addresss.createAddress(sampleCustomer,id, "2275 rue",
				"QC", "G1V 0A8", "Quebec", "Canada");
		assertNull(adrNotUnique);
		assertFalse(addresss.contain(adrNotUnique));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressNameLength() throws Exception {
		String id="";
		while(id.length()<=32){
			id+="1";
		}
		Address adr=addresss.createAddress(sampleCustomer, id, "street", "city", "zipCode", "state", "country", "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.addressName.length"));
	}
	
	@Test
	public void addressStreetRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer,"001", null,
				"QC", "G1K 7P4", "Quebec", "Canada");
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressStreetLength() throws Exception {
		String str="";
		while(str.length()<=64){
			str+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, str, "city", "zipCode", "state", "country", "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.street.length"));
	}
	@Test
	public void addressCityRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer,"001", "2275",
				null, "G1K 7P4", "Quebec", "Canada");
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressCityLength() throws Exception {
		String city="";
		while(city.length()<=32){
			city+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, "street", city, "zipCode", "state", "country", "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.city.length"));
	}
	@Test
	public void addressZipCodeRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer,"001", "2275",
				"QC", null, "Quebec", "Canada");
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressZipCodeLength() throws Exception {
		String zip="";
		while(zip.length()<=16){
			zip+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, "street", "city", zip, "state", "country", "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.zipCode.length"));
	}
	@Test
	public void addressStateRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer,"001", "2275",
				"QC", "G1K 7P4", null, "Canada");
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressStateLength() throws Exception {
		String state="";
		while(state.length()<=32){
			state+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, "street", "city", "zipCode", state, "country", "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.state.length"));
	}
	@Test
	public void addressCountryRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer,"001", "2275",
				"QC", "G1K 7P4", "Quebec", null);
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
	}
	@Test
	public void addressCountryLength() throws Exception {
		String country="";
		while(country.length()<=32){
			country+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, "str", "city", "zipCode", "state", country, "telephone");
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.country.length"));
	}
	
	//Address.telephone.required=Telephone is required.
	@Test
	public void telephoneRequired() throws Exception {
		Address adr01=addresss.createAddress(sampleCustomer, null, "str", "city", "zipCode", "state", "country",null);
		assertNull(adr01);
		assertFalse(addresss.contain(adr01));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.telephone.required"));
	}
	
	//Address.telephone.length=Telephone is longer than 32.
	@Test
	public void telephoneLength() throws Exception {
		String tel="";
		while(tel.length()<=32){
			tel+="1";
		}
		
		Address adr=addresss.createAddress(sampleCustomer, null, "str", "city", "zipCode", "state", "country", tel);
		assertNull(adr);
		assertFalse(addresss.contain(adr));
		assertFalse(addresss.getErrors().isEmpty());
		assertNotNull(addresss.getErrors().getError("Address.telephone.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (Address address : addresss.getList()) {
			addresss.remove(address);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		customers.remove(sampleCustomer);
		TwoadwTest.getSingleton().close();
	}

}