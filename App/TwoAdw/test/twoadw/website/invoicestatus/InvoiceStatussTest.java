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
package twoadw.website.invoicestatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
import twoadw.website.invoice.Invoice;
import twoadw.website.invoice.Invoices;
import twoadw.website.status.Status;
import twoadw.website.status.Statuss;

/**
 * JUnit tests for InvoiceStatuss.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceStatussTest {

	private static Customers customers;
	private static Customer sampleCustomer;
	private static Invoice sampleInvoice;
	private static Invoices invoices;
	private static Status sampleStatus;
	private static Status sampleStatus01;
	private static Statuss statuss;
	
    private static InvoiceStatuss invoiceStatuss;
    
    private static Addresss addresses;
	private static Address sampleAddress;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		customers=website.getCustomers();
		sampleCustomer=customers.createCustomer("007", "jb", "Bond", "James", "JamesBonds@gov.uk");
		addresses = sampleCustomer.getAddresss();
		sampleAddress = addresses.createAddress(sampleCustomer, "99 street", "city", "zipCode", "state", "country", "telephone");
		invoices=sampleAddress.getInvoices();
		sampleInvoice=invoices.createInvoice(sampleAddress,"001");
		statuss=website.getStatuss();
		sampleStatus=statuss.createStatus("waiting");
		sampleStatus01=statuss.createStatus("Sent");
		invoiceStatuss=sampleInvoice.getInvoiceStatuss();
	}

	@Before
	public void beforeTest() throws Exception {
		invoiceStatuss.getErrors().empty();
	}

	@Test
	public void invoiceStatusRequired() throws Exception {
		
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus);
		InvoiceStatus invoiceStatus02=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus01);
		
		assertTrue(invoiceStatuss.contain(invoiceStatus01));
		// To be sure that invoiceStatus01 is added to the sampleStatus.
		assertTrue(sampleStatus.getInvoiceStatuss().contain(invoiceStatus01));
		
		assertTrue(invoiceStatuss.contain(invoiceStatus02));
		// To be sure that invoiceStatus02 is added to the sampleStatus01.
		assertTrue(sampleStatus01.getInvoiceStatuss().contain(invoiceStatus02));
		// To be sure that invoiceStatus01 is added to the sampleInvoice.
		assertTrue(sampleInvoice.getInvoiceStatuss().contain(invoiceStatus01));
		// To be sure that invoiceStatus02 is added to the sampleInvoice.
		assertTrue(sampleInvoice.getInvoiceStatuss().contain(invoiceStatus02));
		assertTrue(invoiceStatuss.getErrors().isEmpty());
		
}

	@Test
	public void invoiceStatusCreated() throws Exception {
		String note="Will be send within 2 days";
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus, note);
		InvoiceStatus invoiceStatus02=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus01, note);
		assertTrue(invoiceStatuss.contain(invoiceStatus01));
		// To be sure that invoiceStatus01 is added to the sampleStatus.
		assertTrue(sampleStatus.getInvoiceStatuss().contain(invoiceStatus01));
		
		assertTrue(invoiceStatuss.contain(invoiceStatus02));
		// To be sure that invoiceStatus02 is added to the sampleStatus01.
		assertTrue(sampleStatus01.getInvoiceStatuss().contain(invoiceStatus02));
		// To be sure that invoiceStatus01 is added to the sampleInvoice.
		assertTrue(sampleInvoice.getInvoiceStatuss().contain(invoiceStatus01));
		// To be sure that invoiceStatus02 is added to the sampleInvoice.
		assertTrue(sampleInvoice.getInvoiceStatuss().contain(invoiceStatus02));
		assertTrue(invoiceStatuss.getErrors().isEmpty());
		
	}
	@Test
	public void invoiceStatusUnique() throws Exception {
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus);
		InvoiceStatus notUniqueInvoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus);
		assertFalse(invoiceStatuss.contain(notUniqueInvoiceStatus01));
		assertNotNull(invoiceStatuss.getErrors().getError("InvoiceStatus.id.unique"));
		assertFalse(invoiceStatuss.getErrors().isEmpty());
	}
	@Test
	public void invoiceStatusDefaultDate() throws Exception {
		Date date=new Date();
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus);
		assertEquals(invoiceStatus01.getDate(), date);
		
	}
	@Test
	public void invoiceStatusRemoved() throws Exception {
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus);
		assertTrue(invoiceStatuss.contain(invoiceStatus01));
		// To be sure that invoiceStatus01 is added to the sampleStatus.
		assertTrue(sampleStatus.getInvoiceStatuss().contain(invoiceStatus01));
		assertTrue(invoiceStatuss.getErrors().isEmpty());
		invoiceStatuss.remove(invoiceStatus01);
		// To be sure that invoiceStatus01 is removed to the invoiceStatuss.
		assertFalse(invoiceStatuss.contain(invoiceStatus01));
		
		// To be sure that invoiceStatus01 is removed to the sampleStatus.
		assertFalse(sampleStatus.getInvoiceStatuss().contain(invoiceStatus01));
	}
	@Test
	public void invoiceStatusUpdate() throws Exception {
		String note="Will be send within 2 days";
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus, note);
		InvoiceStatus invoiceStatus01Copy=invoiceStatus01.copy();
		invoiceStatus01Copy.setNote("Shipped");
		assertTrue(invoiceStatuss.contain(invoiceStatus01));
		assertTrue(invoiceStatus01.equals(invoiceStatus01Copy));
		assertTrue(invoiceStatus01.equalOid(invoiceStatus01Copy));
		assertFalse(invoiceStatus01.equalProperties(invoiceStatus01Copy));
		invoiceStatuss.update(invoiceStatus01, invoiceStatus01Copy);
		assertTrue(invoiceStatuss.getErrors().isEmpty());
		
	}
	@Test
	public void invoiceStatusEquality() throws Exception {
		String note="Will be send within 2 days";
		InvoiceStatus invoiceStatus01=invoiceStatuss.createInvoiceStatus(sampleInvoice, sampleStatus, note);
		InvoiceStatus invoiceStatus01Copy=invoiceStatus01.copy();
		assertEquals(invoiceStatus01, invoiceStatus01Copy);
		assertNotSame(invoiceStatus01, invoiceStatus01Copy);
		assertTrue(invoiceStatuss.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (InvoiceStatus invoiceStatus : invoiceStatuss.getList()) {
			invoiceStatuss.remove(invoiceStatus);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		invoices.remove(sampleInvoice);
		statuss.remove(sampleStatus);
		statuss.remove(sampleStatus01);
		addresses.remove(sampleAddress);
		customers.remove(sampleCustomer);
		TwoadwTest.getSingleton().close();
	}

}