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
package twoadw.website.productcomment;

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
 * JUnit tests for ProductComments.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductCommentsTest {

	private static ProductComments productComments;
	private static Products products;
	private static Product sampleProduct;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Website website = TwoadwTest.getSingleton().getTwoadw().getWebsite();
		products = website.getProducts();
		sampleProduct = products.createProduct("001", "Product1");
		productComments = sampleProduct.getProductComments();
	}

	@Before
	public void beforeTest() throws Exception {
		productComments.getErrors().empty();
	}

	@Test
	public void commentsRequiredCreated() throws Exception {
		ProductComment comment01 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle");
		ProductComment comment02 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle");
		ProductComment comment03 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle");

		assertTrue(productComments.contain(comment01));
		assertTrue(productComments.contain(comment02));
		assertTrue(productComments.contain(comment03));
		assertTrue(productComments.getErrors().isEmpty());
	}
	
	@Test
	public void commentsFullCreated() throws Exception {
		ProductComment comment01 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle", 
				new EasyDate(2009, 1, 12), new EasyDate(2009, 1, 12), true);
		ProductComment comment02 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle", 
				new EasyDate(2009, 1, 12), new EasyDate(2009, 1, 12), true);
		ProductComment comment03 = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle", 
				new EasyDate(2009, 1, 12), new EasyDate(2009, 1, 12), true);

		assertTrue(productComments.contain(comment01));
		assertTrue(productComments.contain(comment02));
		assertTrue(productComments.contain(comment03));
		assertTrue(productComments.getErrors().isEmpty());
	}
	
	@Test
	public void commentsUpdate() throws Exception {
		ProductComment comment = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle", 
				new EasyDate(2009, 1, 12), new EasyDate(2009, 1, 12), true);
		ProductComment commentCopy = comment.copy();
		commentCopy.setCommentText("text2");
		commentCopy.setCommentTitle("title09");
		commentCopy.setPublished(false);

		assertTrue(comment.equals(commentCopy));
		assertTrue(comment.equalOid(commentCopy));
		assertFalse(comment.equalProperties(commentCopy));
		productComments.update(comment, commentCopy);
		assertTrue(products.getErrors().isEmpty());
	}
	
	@Test
	public void commentsEquality()throws Exception{
		ProductComment comment = productComments.createProductComment(sampleProduct, "CommentText", "CommentTitle", 
				new EasyDate(2009, 1, 12), new EasyDate(2009, 1, 12), true);
		ProductComment commentCopy = comment.copy();
		
		assertEquals(comment,commentCopy);
		assertNotSame(comment,commentCopy);
		assertTrue(productComments.getErrors().isEmpty());
	}

	//ProductComment.commentText.required=CommentText is required.
	
	@Test
	public void commentTextRequired() throws Exception {
		ProductComment comment = productComments.createProductComment(sampleProduct, null, "CommentTitle");

		assertNull(comment);
		assertFalse(productComments.contain(comment));
		assertFalse(productComments.getErrors().isEmpty());
		assertNotNull(productComments.getErrors().getError(
				"ProductComment.commentText.required"));
	}
	
	//ProductComment.commentText.length=CommentText is longer than 4080.
	@Test
	public void commentTextLength() throws Exception {
		//Data type Code = 4080 char
		String stringlength = "";
		while (stringlength.length() <=4080) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductComment comment = productComments.createProductComment(sampleProduct, stringlength, "CommentTitle");

		assertNull(comment);
		assertFalse(productComments.contain(comment));
		assertFalse(productComments.getErrors().isEmpty());
		assertNotNull(productComments.getErrors().getError("ProductComment.commentText.length"));
	}
	
	//ProductComment.commentTitle.required=CommentTitle is required.	
	@Test
	public void commentTitleRequired() throws Exception {
		ProductComment comment = productComments.createProductComment(sampleProduct, "Text", null);

		assertNull(comment);
		assertFalse(productComments.contain(comment));
		assertFalse(productComments.getErrors().isEmpty());
		assertNotNull(productComments.getErrors().getError(
				"ProductComment.commentTitle.required"));
	}
	
	//ProductComment.commentTitle.length=CommentTitle is longer than 64.
	@Test
	public void commentTitleLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}		
		
		ProductComment comment = productComments.createProductComment(sampleProduct, "Text", stringlength);

		assertNull(comment);
		assertFalse(productComments.contain(comment));
		assertFalse(productComments.getErrors().isEmpty());
		assertNotNull(productComments.getErrors().getError("ProductComment.commentTitle.length"));
	}
	
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = true;
		ProductComment comment = productComments.createProductComment(sampleProduct, "Text", "Title");
		
		assertTrue(comment.getPublished().equals(defaultValue));
		assertTrue(productComments.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (ProductComment productComment : productComments.getList()) {
			productComments.remove(productComment);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		products.remove(sampleProduct);
		TwoadwTest.getSingleton().close();
	}

}