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
package twoadw.website.productimage;

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
import twoadw.website.product.Product;
import twoadw.website.product.Products;

/**
 * JUnit tests for ProductImages.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductImagesTest {

	private static ProductImages productImages;
	private static Products products;
	private static Product sampleProduct;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products = website.getProducts();
		sampleProduct = products.createProduct("001", "Product1");
		productImages = sampleProduct.getProductImages();
	}

	@Before
	public void beforeTest() throws Exception {
		productImages.getErrors().empty();
	}

	@Test
	public void imagesRequiredCreated() throws Exception {
		ProductImage image01 = productImages.createProductImage(sampleProduct, "name1");
		ProductImage image02 = productImages.createProductImage(sampleProduct, "name2");
		ProductImage image03= productImages.createProductImage(sampleProduct, "name3");

		assertTrue(productImages.contain(image01));
		assertTrue(productImages.contain(image02));
		assertTrue(productImages.contain(image03));
		assertTrue(productImages.getErrors().isEmpty());
	}
	
	@Test
	public void imagesFullCreated() throws Exception {
		ProductImage image01 = productImages.createProductImage(sampleProduct, "name1", "96", "180", "FS");
		ProductImage image02 = productImages.createProductImage(sampleProduct, "name2", "96", "180", "FS");
		ProductImage image03 = productImages.createProductImage(sampleProduct, "name3", "96", "180", "FS");

		assertTrue(productImages.contain(image01));
		assertTrue(productImages.contain(image02));
		assertTrue(productImages.contain(image03));
		assertTrue(productImages.getErrors().isEmpty());
	}
	
	@Test
	public void imageUpdate() throws Exception {
		ProductImage image = productImages.createProductImage(sampleProduct, "name", "96", "180", "FS");
		ProductImage imageCopy = image.copy();
		imageCopy.setName("dsafsdaf");
		imageCopy.setImageUrl96x96("96-2");
		imageCopy.setImageUrl180x130("imageUrl180x130");
		imageCopy.setImageUrlFullSize("imageUrlFullSize");

		assertTrue(image.equals(imageCopy));
		assertTrue(image.equalOid(imageCopy));
		assertFalse(image.equalProperties(imageCopy));
		productImages.update(image, imageCopy);
		assertTrue(products.getErrors().isEmpty());
	}
	
	@Test
	public void imageEquality()throws Exception{
		ProductImage image = productImages.createProductImage(sampleProduct, "name", "96", "180", "FS");
		ProductImage imageCopy = image.copy();
		
		assertEquals(image,imageCopy);
		assertNotSame(image,imageCopy);
		assertTrue(productImages.getErrors().isEmpty());
	}
	
	//ProductImage.name.length=Name is longer than 64.
	@Test
	public void nameLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductImage image = productImages.createProductImage(sampleProduct, stringlength, "96", "180", "FS");
		assertNull(image);
		assertFalse(productImages.contain(image));
		assertFalse(productImages.getErrors().isEmpty());
		assertNotNull(productImages.getErrors().getError("ProductImage.name.length"));
	}
	
	
	//ProductImage.imageUrl96x96.required=ImageUrl96x96 is required.
	@Test
	public void imageUrl96x96DefautValue() throws Exception {
		String statusValue="/css-specific/images/noimage96.jpg";
		
		ProductImage image = productImages.createProductImage(sampleProduct, "name");
		
		assertTrue(image.getImageUrl96x96().equals(statusValue));
		assertTrue(productImages.getErrors().isEmpty());
	}
	
	//ProductImage.imageUrl180x130.required=ImageUrl180x130 is required.
	@Test
	public void imageUrl180x130DefautValue() throws Exception {
		String statusValue="/css-specific/images/noimage180.jpg";
		
		ProductImage image = productImages.createProductImage(sampleProduct, "name");
		
		assertTrue(image.getImageUrl180x130().equals(statusValue));
		assertTrue(productImages.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (ProductImage productProductImage : productImages.getList()) {
			productImages.remove(productProductImage);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct);
		TwoadwTest.getSingleton().close();
	}

}