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
package twoadw.website.customer;

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
import twoadw.website.invoice.Invoice;

/**
 * JUnit tests for Customers.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CustomersTest {

	private static Customers customers;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		customers = TwoadwTest.getSingleton().getTwoadw().getWebsite().getCustomers();
	}

	@Before
	public void beforeTest() throws Exception {
		customers.getErrors().empty();
	}

	@Test
	public void customersRequiredCreated() throws Exception {
		Customer customer01 = customers.createCustomer( "0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		Customer customer02 = customers.createCustomer("0000002", "password", "Bellesfeuilles", "Rosario", "RB@gmail.com");
		Customer customer03 = customers.createCustomer("0000003", "password", "Customer", "Mystery", "ScoobyDoo@gmail.com");

		assertTrue(customers.contain(customer01));
		assertTrue(customers.contain(customer02));
		assertTrue(customers.contain(customer03));
		assertTrue(customers.getErrors().isEmpty());
	}
	
	@Test
	public void customersCompleteCreated() throws Exception {
		Customer customer01 = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com", true, new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("0000002", "password", "Bellesfeuilles", "Rosario", "RB@gmail.com", false, new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("0000003", "password", "Customer", "Mystery", "ScoobyDoo@gmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertTrue(customers.contain(customer01));
		assertTrue(customers.contain(customer02));
		assertTrue(customers.contain(customer03));
		assertTrue(customers.getErrors().isEmpty());
	}
	
	@Test
	public void customersCreated() throws Exception {
		Customer customer01 = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		Customer customer02 = customers.createCustomer("0000002", "password", "Bellesfeuilles", "Rosario", "RB@gmail.com");
		Customer customer03 = customers.createCustomer("0000003", "password", "Customer", "Mystery", "ScoobyDoo@gmail.com");

		assertTrue(customers.contain(customer01));
		assertTrue(customers.contain(customer02));
		assertTrue(customers.contain(customer03));
		assertTrue(customers.getErrors().isEmpty());
	}
	
	@Test
	public void customerEquality()throws Exception{
		Customer customer = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		Customer customerCopy= customer.copy();
		
		assertEquals(customer,customerCopy);
		assertNotSame(customer,customerCopy);
		assertTrue(customers.getErrors().isEmpty());
	}
	
	@Test
	public void categoryUpdate()throws Exception{
		Customer customer = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		Customer customerCopy= customer.copy();
		customerCopy.setCodeCustomer("newcode");
		customerCopy.setPassword("newpassword");
		customerCopy.setLastName("newlastName");
		customerCopy.setFirstName("newfirstName");
		customerCopy.setEmail("newemail@email.com");
		customerCopy.setReceiveEmail(true);
		
		assertTrue(customer.equals(customerCopy));
		assertTrue(customer.equalOid(customerCopy));
		assertFalse(customer.equalProperties(customerCopy));
		customers.update(customer, customerCopy);
		assertTrue(customers.getErrors().isEmpty());
	}
	
	//Customer.codeCustomer.required=CodeCustomer is required.
	@Test
	public void customerCodeRequired() throws Exception {
		Customer customer01 = customers.createCustomer(null, "password", "Daneault", "Pascal", "pascal.daneault@gmail.com", true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer(null, "password", "Bellesfeuilles", "Rosario", "RB@gmail.com", false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer(null, "password", "Customer", "Mystery", "ScoobyDoo@gmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.codeCustomer.required"));
		
	}
	
	//Customer.codeCustomer.length=CodeCustomer is longer than 16.
	@Test
	public void codeCustomerLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer(stringlength, "password", "Daneault", "Pascal", "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.codeCustomer.length"));
	}
	
	//Customer.password.required=Password is required.
	@Test
	public void customerPasswordRequired() throws Exception {
		Customer customer01 = customers.createCustomer("0001", null, "Daneault", "Pascal", "pascal.daneault@gmail.com", true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("00002", null, "Bellesfeuilles", "Rosario", "RB@gmail.com", false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("00003", null, "Customer", "Mystery", "ScoobyDoo@gmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.password.required"));
	}
	
	//Customer.password.length=Password is longer than 16.
	@Test
	public void passwordLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer("000", stringlength, "Daneault", "Pascal", "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.password.length"));
	}
	
	//Customer.lastName.required=LastName is required.
	@Test
	public void customerLastNameRequired() throws Exception {
		Customer customer01 = customers.createCustomer("0001", "password", null, "Pascal", "pascal.daneault@gmail.com", true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("0002", "password", null, "Rosario", "RB@gmail.com", false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("0003", "password", null, "Mystery", "ScoobyDoo@gmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.lastName.required"));
	}
	
	//Customer.lastName.length=LastName is longer than 32.
	@Test
	public void lastNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer("000", "000", stringlength, "Pascal", "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.lastName.length"));
	}
	
	//Customer.firstName.required=FirstName is required.
	@Test
	public void customerFirstNameRequired() throws Exception {
		Customer customer01 = customers.createCustomer("0001", "password", "Daneault", null, "pascal.daneault@gmail.com", true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("0002", "password", "Bellesfeuilles", null, "RB@gmail.com", false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("0003", "password", "Mystery", null, "ScoobyDoo@gmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.firstName.required"));
	}
	
	//Customer.firstName.length=FirstName is longer than 32.
	@Test
	public void firstNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer("000", "000", "lastname", stringlength, "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.firstName.length"));
	}
	
	//Customer.email.required=Email is required.
	@Test
	public void customersEmailRequired() throws Exception {
		Customer customer01 = customers.createCustomer("0000001", "password", "Daneault", "Pascal", null, true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("0000002", "password", "Bellesfeuilles", "Rosario", null, false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("0000003", "password", "Customer", "Mystery", null, true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.email.required"));
	}
	
	//Customer.email.validation=Email is not a valid org.modelibra.type.Email value.
	@Test
	public void customersEmailType() throws Exception {
		Customer customer01 = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),"1");
		Customer customer02 = customers.createCustomer("0000002", "password", "Bellesfeuilles", "Rosario", "RBgmail.com", false,  new EasyDate(2009, 1, 12),"1");
		Customer customer03 = customers.createCustomer("0000003", "password", "Customer", "Mystery", "ScoobyDoogmail.com", true,  new EasyDate(2009, 1, 12),"1");

		assertFalse(customers.contain(customer01));
		assertFalse(customers.contain(customer02));
		assertFalse(customers.contain(customer03));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.email.validation"));
	}
	
	//Customer.email.length=Email is longer than 80.
	@Test
	public void emailLength() throws Exception {
		//Data type Code = 80 char
		String stringlength = "";
		while (stringlength.length() <=80) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer("000", "000", "lastname", "1rstname", stringlength, true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.email.length"));
	}
	
	
	
	//Customer.id.unique=Customer identifier ([codeCustomer] []) is not unique.
	@Test
	public void customersCodeUnique() throws Exception {
		String idCustomer="00001";
		customers.createCustomer(idCustomer, "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		Customer notUniqueCustomer= customers.createCustomer(idCustomer, "password", "Traore", "Mahamadou", "mtraore01@gmail.com");
		assertFalse(customers.contain(notUniqueCustomer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.id.unique"));
	}

	//Customer.securityRole.required=SecurityRole is required.
	@Test
	public void securityRoleDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		String defaultValue="1";
		//Boolean defaultValue = false;
		Customer customer = customers.createCustomer( "0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		
		assertTrue(customer.getSecurityRole().equals(defaultValue));
		assertTrue(customers.getErrors().isEmpty());
	}
	
	
	
	//Customer.securityRole.length=SecurityRole is longer than 16.
	@Test
	public void invoiceNumberLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		Customer customer = customers.createCustomer("0000001", "password", "Daneault", "Pascal", "pascal.daneaultgmail.com", true,  new EasyDate(2009, 1, 12),stringlength);

		assertNull(customer);
		assertFalse(customers.contain(customer));
		assertFalse(customers.getErrors().isEmpty());
		assertNotNull(customers.getErrors().getError("Customer.securityRole.length"));
	}
	
	//Customer.receiveEmail.required=ReceiveEmail is required.
	@Test
	public void receiveEmailDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = false;
		Customer customer = customers.createCustomer( "0000001", "password", "Daneault", "Pascal", "pascal.daneault@gmail.com");
		
		assertTrue(customer.getReceiveEmail().equals(defaultValue));
		assertTrue(customers.getErrors().isEmpty());
	}
	
	@After
	public void afterTest() throws Exception {
		for (Customer customer : customers.getList()) {
			customers.remove(customer);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}