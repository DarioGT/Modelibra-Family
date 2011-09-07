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
package twoadw.website.invoice;

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
import twoadw.website.address.Address;
import twoadw.website.address.Addresss;
import twoadw.website.customer.Customer;
import twoadw.website.customer.Customers;

/**
 * JUnit tests for Invoices.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoicesTest {

	private static Invoices invoices;
	private static Customers customers;
	private static Customer sampleCustomer;
	
	private static Addresss addresses;
	private static Address sampleAddress;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		// invoices = TwoadwTest.getSingleton().getTwoadw().getWebsite().getInvoices();
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		customers = website.getCustomers();
		sampleCustomer = customers.createCustomer("007", "jb", "Bond", "James", "JamesBonds@gov.uk");
		addresses = sampleCustomer.getAddresss();
		sampleAddress = addresses.createAddress(sampleCustomer, "99 street", "city", "zipCode", "state", "country", "telephone");
		invoices = sampleAddress.getInvoices();
	}

	@Before
	public void beforeTest() throws Exception {
		invoices.getErrors().empty();
	}

	@Test
	public void invoiceRequiredCreated() throws Exception {
		Invoice invoice01 = invoices.createInvoice(sampleAddress,"001");
		Invoice invoice02 = invoices.createInvoice(sampleAddress,"002");
		Invoice invoice03 = invoices.createInvoice(sampleAddress,"003");

		assertTrue(invoices.contain(invoice01));
		assertTrue(invoices.contain(invoice02));
		assertTrue(invoices.contain(invoice03));
		assertTrue(invoices.getErrors().isEmpty());
	}
	@Test
	public void invoiceCreated() throws Exception {
		Invoice invoice01 = invoices.createInvoice(sampleAddress,"0000001", true, true, new EasyDate(2009, 1, 12));
		Invoice invoice02 = invoices.createInvoice(sampleAddress,"0000002", true, true, new EasyDate(2009, 1, 12));
		Invoice invoice03 = invoices.createInvoice(sampleAddress,"0000003", true, true, new EasyDate(2009, 1, 12));

		assertTrue(invoices.contain(invoice01));
		assertTrue(invoices.contain(invoice02));
		assertTrue(invoices.contain(invoice03));
		assertTrue(invoices.getErrors().isEmpty());
	}
	
	@Test
	public void invoiceUpdate() throws Exception {
		Invoice invoice = invoices.createInvoice(sampleAddress, "0000001", true, true, new EasyDate(2009, 1, 12));
		Invoice invoiceCopy = invoice.copy();
	
		invoiceCopy.setInvoiceNumber("24");
		invoiceCopy.setShipped(false);
		invoiceCopy.setPaid(false);
		
		assertTrue(invoice.equals(invoiceCopy));
		assertTrue(invoice.equalOid(invoiceCopy));
		assertFalse(invoice.equalProperties(invoiceCopy));
	
		invoices.update(invoice, invoiceCopy);
		assertTrue(invoices.getErrors().isEmpty());
	}
	
	@Test
	public void invoiceEquality() throws Exception {
		Invoice invoice = invoices.createInvoice(sampleAddress, "0000001", true, true, new EasyDate(2009, 1, 12));
		Invoice invoiceCopy = invoice.copy();
	
		assertEquals(invoice,invoiceCopy);
		assertNotSame(invoice,invoiceCopy);
		assertTrue(invoices.getErrors().isEmpty());
	}
	
	@Test
	public void invoiceNumberLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Invoice invoice = invoices.createInvoice(sampleAddress, stringlength, true, true, new EasyDate(2009, 1, 12));

		assertNull(invoice);
		assertFalse(invoices.contain(invoice));
		assertFalse(invoices.getErrors().isEmpty());
		assertNotNull(invoices.getErrors().getError("Invoice.invoiceNumber.length"));
	}
	
	@Test
	public void paidDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = false;
		Invoice invoice = invoices.createInvoice(sampleAddress,"001");
		
		assertTrue(invoice.getPaid().equals(defaultValue));
		assertTrue(invoices.getErrors().isEmpty());
	}
	
	@Test
	public void shippedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = false;
		Invoice invoice = invoices.createInvoice(sampleAddress,"001");
		
		assertTrue(invoice.getShipped().equals(defaultValue));
		assertTrue(invoices.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (Invoice invoice : invoices.getList()) {
			invoices.remove(invoice);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		customers.remove(sampleCustomer);
		addresses.remove(sampleAddress);
		TwoadwTest.getSingleton().close();
	}

}