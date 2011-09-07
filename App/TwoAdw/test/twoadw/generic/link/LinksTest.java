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
package twoadw.generic.link;

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
import twoadw.generic.Generic;
import twoadw.generic.categorylink.CategoryLink;
import twoadw.generic.categorylink.CategoryLinks;
import twoadw.website.Website;


/**
 * JUnit tests for Links.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class LinksTest {

	private static Links links;
	
	private static CategoryLinks categoryLinks;
	private static CategoryLink sampleCategoryLink;

	@BeforeClass
	public static void beforeTests() throws Exception {
		Generic generic = TwoadwTest.getSingleton().getTwoadw().getGeneric();
		categoryLinks = generic.getCategoryLinks();
		sampleCategoryLink = categoryLinks.createCategoryLink(null, "sampleCategoryLink");
		links = sampleCategoryLink.getLinks();
	}

	@Before
	public void beforeTest() throws Exception {
		links.getErrors().empty();
	}

	@Test
	public void createRequiredLink() throws Exception {
		Link link01 = links.createLink(sampleCategoryLink, "http://www.url1.com", "Url1");
		Link link02 = links.createLink(sampleCategoryLink, "http://www.url2.com", "Url2");
		Link link03 = links.createLink(sampleCategoryLink, "http://www.url3.com", "Url3");

		
		assertTrue(links.contain(link01));
		assertTrue(links.contain(link02));
		assertTrue(links.contain(link03));
		assertTrue(links.getErrors().isEmpty());
	}
	
	@Test
	public void createFullLink() throws Exception {
		Link link01 = links.createLink(sampleCategoryLink, "http://www.url1.com", "Url1", "description", true);
		Link link02 = links.createLink(sampleCategoryLink, "http://www.url2.com", "Url2", "description", true);
		Link link03 = links.createLink(sampleCategoryLink, "http://www.url3.com", "Url3", "description", false);
		
		assertTrue(links.contain(link01));
		assertTrue(links.contain(link02));
		assertTrue(links.contain(link03));
		assertTrue(links.getErrors().isEmpty());
	}
	
	@Test
	public void linkEquality()throws Exception{
		Link link = links.createLink(sampleCategoryLink, "http://www.url1.com", "Url1", "description", true);
		Link linkCopy= link.copy();
		
		assertEquals(link,linkCopy);
		assertNotSame(link,linkCopy);
		assertTrue(links.getErrors().isEmpty());
	}
	
	@Test
	public void linkUpdate()throws Exception{
		Link link = links.createLink(sampleCategoryLink, "http://www.url1.com", "Url1", "description", true);
		Link linkCopy= link.copy();
		linkCopy.setUrl("http://www.newurl.com");
		linkCopy.setName("newname");
		linkCopy.setDescription("newdescription");
		linkCopy.setPublished(false);
		
		assertTrue(link.equals(linkCopy));
		assertTrue(link.equalOid(linkCopy));
		assertFalse(link.equalProperties(linkCopy));
		links.update(link, linkCopy);
		assertTrue(links.getErrors().isEmpty());
	}

	//Link.id.unique=Link identifier ([url] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		Link link01 = links.createLink(sampleCategoryLink, "http://www.url.com", "Url1", "description", true);
		Link link02 = links.createLink(sampleCategoryLink, "http://www.url.com", "Url2", "description", true);
		Link link03 = links.createLink(sampleCategoryLink, "http://www.url.com", "Url3", "description", false);

		assertTrue(links.contain(link01));
		assertFalse(links.contain(link02));
		assertFalse(links.contain(link03));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.id.unique"));
	}
	
	//Link.url.required=Url is required.
	@Test
	public void urlRequired() throws Exception {
		Link link = links.createLink(sampleCategoryLink, null, "Url1", "description", true);

		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.url.required"));
	}
	
	//Link.url.length=Url is longer than 96.
	@Test
	public void urlLength() throws Exception {
		//Data type Code = 96 char
		String stringlength = "http://www.url.com/";
		while (stringlength.length() <=96) {
			stringlength = stringlength + "1"; 
		}		
		
		Link link = links.createLink(sampleCategoryLink, stringlength, "Url1", "description", true);

		assertNull(link);
		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.url.length"));
	}
	
	//Link.url.validation=Url is not a valid java.net.URL value.
	@Test
	public void urlValidation() throws Exception {
		//Data type Code = 96 char
		String stringlength = "not a valid url";
		while (stringlength.length() <=96) {
			stringlength = stringlength + "1"; 
		}		
		
		Link link = links.createLink(sampleCategoryLink, stringlength, "Url1", "description", true);

		assertNull(link);
		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.url.validation"));
	}
	
	//Link.name.required=Name is required.
	@Test
	public void nameRequired() throws Exception {
		Link link = links.createLink(sampleCategoryLink, "http://www.url.com", null, "description", true);

		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.name.required"));
	}
	
	//Link.name.length=Name is longer than 32.
	public void nameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}		
		
		Link link = links.createLink(sampleCategoryLink, "http://www.url.com", stringlength, "description", true);

		assertNull(link);
		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.name.length"));
	}
	
	//Link.description.length=Description is longer than 510.
	public void descriptionLength() throws Exception {
		//Data type Code = 510 char
		String stringlength = "";
		
		while (stringlength.length() <=510) {
			stringlength = stringlength + "1"; 
		}		
		
		Link link = links.createLink(sampleCategoryLink, "http://www.url.com", "name", stringlength, true);

		assertNull(link);
		assertFalse(links.contain(link));
		assertFalse(links.getErrors().isEmpty());
		assertNotNull(links.getErrors().getError("Link.description.length"));
	}
	
	//Link.published.required=Published is required.
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = true;
		Link link = links.createLink(sampleCategoryLink, "http://www.url.com", "name", "desc", true);
		
		assertTrue(link.getPublished().equals(defaultValue));
		assertTrue(links.getErrors().isEmpty());
	}
	

	@After
	public void afterTest() throws Exception {
		for (Link link : links.getList()) {
			links.remove(link);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		categoryLinks.remove(sampleCategoryLink);
		TwoadwTest.getSingleton().close();
	}

}