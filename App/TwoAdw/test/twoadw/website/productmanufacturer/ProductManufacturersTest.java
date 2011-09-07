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
package twoadw.website.productmanufacturer;

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
import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Product;
import twoadw.website.product.Products;
import twoadw.website.productcategory.ProductCategory;

/**
 * JUnit tests for ProductManufacturers.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductManufacturersTest {

	private static ProductManufacturers productManufacturers;
	
	private static Products products;
	private static Product sampleProduct01;
	private static Product sampleProduct02;
	private static Product sampleProduct03;
	
	private static Manufacturers manufacturers;
	private static Manufacturer sampleManufacturer01;
	private static Manufacturer sampleManufacturer02;
	private static Manufacturer sampleManufacturer03;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products = website.getProducts();
		sampleProduct01 = products.createProduct("001", "Product1");
		sampleProduct02 = products.createProduct("002", "Product2");
		sampleProduct03 = products.createProduct("003", "Product3");
		manufacturers = website.getManufacturers();
		sampleManufacturer01 = manufacturers.createManufacturer("001", "Manufacturer1");
		sampleManufacturer02 = manufacturers.createManufacturer("002", "Manufacturer2");
		sampleManufacturer03 = manufacturers.createManufacturer("003", "Manufacturer3");
	}

	//Products associated with one manufacturer
	@Test
	public void productManufacturerCreatedbyManunfacturer() throws Exception {
		//Before Test
		productManufacturers = sampleManufacturer01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturers01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturers02 = productManufacturers.createProductManufacturer(sampleProduct02, sampleManufacturer01);
		ProductManufacturer productManufacturers03 = productManufacturers.createProductManufacturer(sampleProduct03, sampleManufacturer01);

		assertTrue(productManufacturers.contain(productManufacturers01));
		assertTrue(productManufacturers.contain(productManufacturers02));
		assertTrue(productManufacturers.contain(productManufacturers03));
		assertTrue(productManufacturers.getErrors().isEmpty());
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	//Manufacturers associated with one product
	@Test
	public void productManufacturerCreatedbyProduct() throws Exception {
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturers01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturers02 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer02);
		ProductManufacturer productManufacturers03 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer03);

		assertTrue(productManufacturers.contain(productManufacturers01));
		assertTrue(productManufacturers.contain(productManufacturers02));
		assertTrue(productManufacturers.contain(productManufacturers03));
		assertTrue(productManufacturers.getErrors().isEmpty());
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	@Test
	public void productManufacturerUpdate()throws Exception{
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturer01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturerCopy= productManufacturer01.copy();
		productManufacturerCopy.setManufacturer(sampleManufacturer02);
		productManufacturerCopy.setProduct(sampleProduct02);
		
		assertTrue(productManufacturer01.equals(productManufacturerCopy));
		assertTrue(productManufacturer01.equalOid(productManufacturerCopy));
		assertFalse(productManufacturer01.equalProperties(productManufacturerCopy));
		productManufacturers.update(productManufacturer01, productManufacturerCopy);
		assertTrue(productManufacturers.getErrors().isEmpty());
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	@Test
	public void productManufacturerEquality()throws Exception{
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturer01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturerCopy= productManufacturer01.copy();
		
		assertEquals(productManufacturer01,productManufacturerCopy);
		assertNotSame(productManufacturer01,productManufacturerCopy);
		assertTrue(productManufacturers.getErrors().isEmpty());
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	//ProductManufacturer.id.unique=ProductManufacturer identifier ([] [product, manufacturer]) is not unique.
	@Test
	public void idUnique()throws Exception{
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturer01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturer02 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		ProductManufacturer productManufacturer03 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01);
		
		assertTrue(productManufacturers.contain(productManufacturer01));
		assertFalse(productManufacturers.contain(productManufacturer02));
		assertFalse(productManufacturers.contain(productManufacturer03));
		assertFalse(productManufacturers.getErrors().isEmpty());
		assertNotNull(productManufacturers.getErrors().getError("ProductManufacturer.id.unique"));
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	//ProductManufacturer.manufacturerOid.required=ManufacturerOid is required.
	@Test
	public void manufacturerOidRequired()throws Exception{
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		ProductManufacturer productManufacturer01 = productManufacturers.createProductManufacturer(sampleProduct01, null);
		
		assertFalse(productManufacturers.contain(productManufacturer01));
		assertFalse(productManufacturers.getErrors().isEmpty());
		assertNotNull(productManufacturers.getErrors().getError("ProductManufacturer.manufacturerOid.required"));
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	//ProductManufacturer.partNumber.length=PartNumber is longer than 64.
	@Test
	public void partNumberLength()throws Exception{
		//Before Test
		productManufacturers = sampleProduct01.getProductManufacturers();
		productManufacturers.getErrors().empty();
		
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		
		ProductManufacturer productManufacturer01 = productManufacturers.createProductManufacturer(sampleProduct01, sampleManufacturer01, stringlength);

		assertNull(productManufacturer01);
		assertFalse(productManufacturers.contain(productManufacturer01));
		assertFalse(productManufacturers.getErrors().isEmpty());
		assertNotNull(productManufacturers.getErrors().getError("ProductManufacturer.partNumber.length"));
		
		//After Test
		for (ProductManufacturer productManufacturer : productManufacturers.getList()) {
			productManufacturers.remove(productManufacturer);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct01);
		products.remove(sampleProduct02);
		products.remove(sampleProduct03);
		manufacturers.remove(sampleManufacturer01);
		manufacturers.remove(sampleManufacturer02);
		manufacturers.remove(sampleManufacturer03);
		TwoadwTest.getSingleton().close();
	}

}