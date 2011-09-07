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
package twoadw.website.invoiceproduct;

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
import twoadw.website.invoice.Invoice;
import twoadw.website.invoice.Invoices;
import twoadw.website.product.Product;
import twoadw.website.product.Products;

/**
 * JUnit tests for InvoiceProducts.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceProductsTest {

	private static InvoiceProducts invoiceProducts;
	
	private static Products products;
	private static Product sampleProduct01;
	private static Product sampleProduct02;
	private static Product sampleProduct03;
	
	private static Addresss addresses;
	private static Address sampleAddress;
	
	private static Customers customers;
	private static Customer sampleCustomer;

	private static Invoices invoices;
	private static Invoice sampleInvoice01;
	private static Invoice sampleInvoice02;
	private static Invoice sampleInvoice03;
	private static Double price = 9.99;
	

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products = website.getProducts();
		sampleProduct01 = products.createProduct("001", "Product1");
		sampleProduct02 = products.createProduct("002", "Product2");
		sampleProduct03 = products.createProduct("003", "Product3");
		customers = website.getCustomers();
		sampleCustomer = customers.createCustomer("007", "jb", "Bond", "James", "JamesBonds@gov.uk");
		addresses = sampleCustomer.getAddresss();
		sampleAddress = addresses.createAddress(sampleCustomer, "99 street", "city", "zipCode", "state", "country", "telephone");
		invoices = sampleAddress.getInvoices();
		sampleInvoice01 = invoices.createInvoice(sampleAddress, "001");
		sampleInvoice02 = invoices.createInvoice(sampleAddress, "002");
		sampleInvoice03 = invoices.createInvoice(sampleAddress, "003");
		
	}



	//Products associated with one invoice
	@Test
	public void invoiceProductRequiredCreatedbyInvoice() throws Exception {
		//Before Tests
		invoiceProducts = sampleInvoice01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		InvoiceProduct invoiceProduct02 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct02, price);
		InvoiceProduct invoiceProduct03 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct03, price);

		assertTrue(invoiceProducts.contain(invoiceProduct01));
		assertTrue(invoiceProducts.contain(invoiceProduct02));
		assertTrue(invoiceProducts.contain(invoiceProduct03));
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	//Products associated with one invoice
	@Test
	public void invoiceProductCreatedbyInvoice() throws Exception {
		//Before Tests
		invoiceProducts = sampleInvoice01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price, "2");
		InvoiceProduct invoiceProduct02 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct02, price, "3");
		InvoiceProduct invoiceProduct03 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct03, price, "4");

		assertTrue(invoiceProducts.contain(invoiceProduct01));
		assertTrue(invoiceProducts.contain(invoiceProduct02));
		assertTrue(invoiceProducts.contain(invoiceProduct03));
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	//Products associated with one invoice
	@Test
	public void invoiceProductRequiredCreatedbyProduct() throws Exception {
		//Before Tests
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		InvoiceProduct invoiceProduct02 = invoiceProducts.createInvoiceProduct(sampleInvoice02, sampleProduct01, price);
		InvoiceProduct invoiceProduct03 = invoiceProducts.createInvoiceProduct(sampleInvoice03, sampleProduct01, price);

		assertTrue(invoiceProducts.contain(invoiceProduct01));
		assertTrue(invoiceProducts.contain(invoiceProduct02));
		assertTrue(invoiceProducts.contain(invoiceProduct03));
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	//Products associated with one invoice
	@Test
	public void invoiceProductCreatedbyProduct() throws Exception {
		//Before Tests
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		InvoiceProduct invoiceProduct02 = invoiceProducts.createInvoiceProduct(sampleInvoice02, sampleProduct01, price);
		InvoiceProduct invoiceProduct03 = invoiceProducts.createInvoiceProduct(sampleInvoice03, sampleProduct01, price);

		assertTrue(invoiceProducts.contain(invoiceProduct01));
		assertTrue(invoiceProducts.contain(invoiceProduct02));
		assertTrue(invoiceProducts.contain(invoiceProduct03));
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	@Test
	public void invoiceProductUpdate()throws Exception{
		//Before Tests
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		InvoiceProduct invoiceProductCopy= invoiceProduct01.copy();
		invoiceProductCopy.setProduct(sampleProduct02);
		
		
		assertTrue(invoiceProduct01.equals(invoiceProductCopy));
		assertTrue(invoiceProduct01.equalOid(invoiceProductCopy));
		assertFalse(invoiceProduct01.equalProperties(invoiceProductCopy));
		invoiceProducts.update(invoiceProduct01, invoiceProductCopy);
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	@Test
	public void invoiceProductEquality()throws Exception{
		//Before Tests
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		InvoiceProduct invoiceProductCopy= invoiceProduct01.copy();
				
		assertEquals(invoiceProduct01,invoiceProductCopy);
		assertNotSame(invoiceProduct01,invoiceProductCopy);
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	@Test
	public void quantityDefautValue() throws Exception {
		//Before Test
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		String statusValue="1";
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, price);
		
		assertTrue(invoiceProduct01.getQuantity().equals(statusValue));
		assertTrue(invoiceProducts.getErrors().isEmpty());
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	//InvoiceProduct.price.required=Price is required.
	@Test
	public void priceRequired() throws Exception {
		//Before Test
		invoiceProducts = sampleProduct01.getInvoiceProducts();
		invoiceProducts.getErrors().empty();
		
		InvoiceProduct invoiceProduct01 = invoiceProducts.createInvoiceProduct(sampleInvoice01, sampleProduct01, null);
		
		assertFalse(invoiceProducts.contain(invoiceProduct01));
		assertNotNull(invoiceProducts.getErrors().getError("InvoiceProduct.price.required"));
		
		//After Tests
		for (InvoiceProduct invoiceProduct : invoiceProducts.getList()) {
			invoiceProducts.remove(invoiceProduct);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct01);
		products.remove(sampleProduct02);
		products.remove(sampleProduct03);
		customers.remove(sampleCustomer);
		invoices.remove(sampleInvoice01);
		invoices.remove(sampleInvoice02);
		invoices.remove(sampleInvoice03);
		TwoadwTest.getSingleton().close();
	}

}