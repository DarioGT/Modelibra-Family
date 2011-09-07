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
package twoadw.generic.informationpage;

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

import twoadw.TwoadwTest;
import twoadw.generic.categorylink.CategoryLink;

/**
 * JUnit tests for InformationPages.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InformationPagesTest {

	private static InformationPages informationPages;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a
		// collection of entities.
		// For an entry point the following code is correct.
		informationPages = TwoadwTest.getSingleton().getTwoadw().getGeneric()
				.getInformationPages();
	}

	@Before
	public void beforeTest() throws Exception {
		informationPages.getErrors().empty();
	}

	// TODO
	/*

	 * InformationPage.published=Published
	 * InformationPage.published.required=Published is required.
	 */

	@Test
	public void createRequiredInformationPage() throws Exception {
		InformationPage infoPage01 = informationPages.createInformationPage(
				"AboutUs1", "About Us", "<p>page</p>");
		InformationPage infoPage02 = informationPages.createInformationPage(
				"AboutUs2", "About Us", "<p>page</p>");
		InformationPage infoPage03 = informationPages.createInformationPage(
				"AboutUs3", "About Us", "<p>page</p>");

		assertTrue(informationPages.contain(infoPage01));
		assertTrue(informationPages.contain(infoPage02));
		assertTrue(informationPages.contain(infoPage03));
		assertTrue(informationPages.getErrors().isEmpty());
	}

	@Test
	public void createFullInformationPage() throws Exception {
		InformationPage infoPage01 = informationPages.createInformationPage(
				"AboutUs1", "About Us", "<p>page</p>", false);
		InformationPage infoPage02 = informationPages.createInformationPage(
				"AboutUs2", "About Us", "<p>page</p>", true);
		InformationPage infoPage03 = informationPages.createInformationPage(
				"AboutUs3", "About Us", "<p>page</p>", true);

		assertTrue(informationPages.contain(infoPage01));
		assertTrue(informationPages.contain(infoPage02));
		assertTrue(informationPages.contain(infoPage03));
		assertTrue(informationPages.getErrors().isEmpty());
	}

	@Test
	public void informationPageEquality() throws Exception {
		InformationPage infoPage = informationPages.createInformationPage(
				"AboutUs1", "About Us", "<p>page</p>", false);
		InformationPage infoPageCopy = infoPage.copy();

		assertEquals(infoPage, infoPageCopy);
		assertNotSame(infoPage, infoPageCopy);
		assertTrue(informationPages.getErrors().isEmpty());
	}

	@Test
	public void informationPageUpdate() throws Exception {
		InformationPage infoPage = informationPages.createInformationPage(
				"AboutUs1", "About Us", "<p>page</p>", false);
		InformationPage infoPageCopy = infoPage.copy();
		infoPageCopy.setCodePage("codepage");
		infoPageCopy.setTitle("title");
		infoPageCopy.setPage("page");
		infoPageCopy.setPublished(true);

		assertTrue(infoPage.equals(infoPageCopy));
		assertTrue(infoPage.equalOid(infoPageCopy));
		assertFalse(infoPage.equalProperties(infoPageCopy));
		informationPages.update(infoPage, infoPageCopy);
		assertTrue(informationPages.getErrors().isEmpty());
	}

	// InformationPage.id.unique=InformationPage identifier ([codePage] []) is
	// not unique.
	@Test
	public void idUnique() throws Exception {
		InformationPage infoPage01 = informationPages.createInformationPage(
				"AboutUs", "About Us", "<p>page</p>", false);
		InformationPage infoPage02 = informationPages.createInformationPage(
				"AboutUs", "About Us", "<p>page</p>", true);
		InformationPage infoPage03 = informationPages.createInformationPage(
				"AboutUs", "About Us", "<p>page</p>", true);

		assertTrue(informationPages.contain(infoPage01));
		assertFalse(informationPages.contain(infoPage02));
		assertFalse(informationPages.contain(infoPage03));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError(
				"InformationPage.id.unique"));
	}
	
	//InformationPage.codePage.required=CodePage is required.
	@Test
	public void codePageRequired() throws Exception {
		InformationPage infoPage = informationPages.createInformationPage(
				null, "About Us", "<p>page</p>", false);

		assertFalse(informationPages.contain(infoPage));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError("InformationPage.codePage.required"));
	}
	
	//InformationPage.codePage.length=CodePage is longer than 16.
	@Test
	public void codePageLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}		
		
		InformationPage infoPage = informationPages.createInformationPage(
				stringlength, "About Us", "<p>page</p>", false);

		assertNull(infoPage);
		assertFalse(informationPages.contain(infoPage));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError("InformationPage.codePage.length"));
	}
	
	//InformationPage.title.required=Title is required. 
	@Test
	public void titleRequired() throws Exception {
		InformationPage infoPage = informationPages.createInformationPage(
				"code", null, "<p>page</p>", false);

		assertFalse(informationPages.contain(infoPage));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError("InformationPage.title.required"));
	}
	
	//InformationPage.title.length=Title is longer than 64.
	@Test
	public void titleLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}		
		
		InformationPage infoPage = informationPages.createInformationPage(
				"code", stringlength, "<p>page</p>", false);

		assertNull(infoPage);
		assertFalse(informationPages.contain(infoPage));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError("InformationPage.title.length"));
	}
	
	//* InformationPage.page.required=Page is required.
	@Test
	public void pageRequired() throws Exception {
		InformationPage infoPage = informationPages.createInformationPage(
				"code", "title", null, false);


		assertFalse(informationPages.contain(infoPage));
		assertFalse(informationPages.getErrors().isEmpty());
		assertNotNull(informationPages.getErrors().getError("InformationPage.page.required"));
	}
	
	
	//InformationPage.published.required=Published is required.
	@Test
	public void publishedDefautValue() throws Exception {
		//defaultValue is = to the Default value of the model
		//String defaultValue="Waiting Process";
		Boolean defaultValue = true;
		InformationPage infoPage = informationPages.createInformationPage(
				"code", "title", "page");
		
		assertTrue(infoPage.getPublished().equals(defaultValue));
		assertTrue(informationPages.getErrors().isEmpty());
	}
	
	@After
	public void afterTest() throws Exception {
		for (InformationPage informationPage : informationPages.getList()) {
			informationPages.remove(informationPage);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}