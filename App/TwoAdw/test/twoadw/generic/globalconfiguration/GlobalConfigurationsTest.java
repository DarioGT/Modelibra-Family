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
package twoadw.generic.globalconfiguration;

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
import twoadw.website.product.Product;

/**
 * JUnit tests for GlobalConfigurations.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class GlobalConfigurationsTest {

	private static GlobalConfigurations globalConfigurations;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		globalConfigurations = TwoadwTest.getSingleton().getTwoadw().getGeneric().getGlobalConfigurations();
	}

	@Before
	public void beforeTest() throws Exception {
		globalConfigurations.getErrors().empty();
	}

	@Test
	public void globalConfigurationRequiredCreated() throws Exception {
		GlobalConfiguration gc01 =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp");
		GlobalConfiguration gc02 =globalConfigurations.createGlobalConfiguration("testSite", "TeamFcp");

		assertTrue(globalConfigurations.contain(gc01));
		assertTrue(globalConfigurations.contain(gc02));
		assertTrue(globalConfigurations.getErrors().isEmpty());
	}
	
	@Test
	public void globalConfigurationFullCreated() throws Exception {
		GlobalConfiguration gc01 =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp", true,
				"offline", "template", "description", "keywords");
		GlobalConfiguration gc02 =globalConfigurations.createGlobalConfiguration("name", "TeamFcp", true,
				"offline", "template", "description", "keywords");

		assertTrue(globalConfigurations.contain(gc01));
		assertTrue(globalConfigurations.contain(gc02));
		assertTrue(globalConfigurations.getErrors().isEmpty());
	}
	
	@Test
	public void globalConfigurationEquality() throws Exception {
		GlobalConfiguration gc01 =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp");
		GlobalConfiguration gc02 =gc01.copy();

		assertEquals(gc01,gc02);
		assertNotSame(gc01,gc02);
		assertTrue(globalConfigurations.getErrors().isEmpty());
	}
	
	@Test
	public void globalConfigurationUpdate() throws Exception {
		GlobalConfiguration gc01 =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp");
		GlobalConfiguration gc02 =gc01.copy();
		gc02.setSiteName("name");
		gc02.setOrganisationName("org");
		gc02.setOnline(false);
		gc02.setOfflineMessage("offlinemsg");
		gc02.setTemplate("template");
		gc02.setSiteMetaDescription("metadesc");
		gc02.setSiteMetaKeywords("metakeys");

		assertTrue(gc01.equals(gc02));
		assertTrue(gc01.equalOid(gc02));
		assertFalse(gc01.equalProperties(gc02));
		globalConfigurations.update(gc01, gc02);
		
		assertTrue(globalConfigurations.getErrors().isEmpty());
	}

	//GlobalConfiguration.id.unique=GlobalConfiguration identifier ([siteName] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp");
		GlobalConfiguration gcNotUnique =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp");

		assertNull(gcNotUnique);
		assertFalse(globalConfigurations.contain(gcNotUnique));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.id.unique"));
	}
	
	//GlobalConfiguration.siteName.required=SiteName is required.
	@Test
	public void siteNameRequired() throws Exception {
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration(null, "TeamFcp");

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.siteName.required"));
	}
	
	//GlobalConfiguration.siteName.length=SiteName is longer than 64.
	@Test
	public void siteNameLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration(stringlength, "TeamFcp");

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.siteName.length"));
	}

	//GlobalConfiguration.organisationName.required=OrganisationName is required.
	@Test
	public void organisationNameRequired() throws Exception {
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", null);

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.organisationName.required"));
	}
	
	//GlobalConfiguration.organisationName.length=OrganisationName is longer than 64.
	@Test
	public void organisationNameLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw" , stringlength);

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.organisationName.length"));
	}
	
	

	//GlobalConfiguration.online.required=Online is required.
	//GlobalConfiguration.offlineMessage.required=OfflineMessage is required.
	//GlobalConfiguration.template.required=Template is required.
	//GlobalConfiguration.siteMetaDescription.required=SiteMetaDescription is required.
	//GlobalConfiguration.siteMetaKeywords.required=SiteMetaKeywords is required.
	@Test
	public void modelDefaultVariables() throws Exception {
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "orgname");
		
		assertTrue(gc.getOnline().equals(true));
		assertTrue(gc.getOfflineMessage().equals("The website is offline"));
		assertTrue(gc.getTemplate().equals("Classic"));
		assertTrue(gc.getSiteMetaDescription().equals("TwoAdw Wicket"));
		assertTrue(gc.getSiteMetaKeywords().equals("TwoAdw, Wicket, Modelibra"));
		assertTrue(globalConfigurations.getErrors().isEmpty());
	}
	
	//GlobalConfiguration.offlineMessage.length=OfflineMessage is longer than 510.
	@Test
	public void offlineMessageLength() throws Exception {
		//Data type Code = 510 char
		String stringlength = "";
		while (stringlength.length() <=510) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp", true,
				stringlength, "template", "desc", "keywords");

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.offlineMessage.length"));
	}
	
	//GlobalConfiguration.template.length=Template is longer than 64.
	@Test
	public void templateLength() throws Exception {
		//Data type Code = 64 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp", true,
				"off",stringlength, "desc", "keywords");

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.template.length"));
	}
	
	//GlobalConfiguration.siteMetaDescription.length=SiteMetaDescription is longer than 255.
	@Test
	public void siteMetaDescriptionLength() throws Exception {
		//Data type Code = 255 char
		String stringlength = "";
		while (stringlength.length() <=255) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp", true,
				"off","template",stringlength, "keywords");

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.siteMetaDescription.length"));
	}
	
	//GlobalConfiguration.siteMetaKeywords.length=SiteMetaKeywords is longer than 255.
	@Test
	public void siteMetaKeywordsLength() throws Exception {
		//Data type Code = 255 char
		String stringlength = "";
		while (stringlength.length() <=255) {
			stringlength = stringlength + "1"; 
		}	
		GlobalConfiguration gc =globalConfigurations.createGlobalConfiguration("2adw", "TeamFcp", true,
				"off","template","desc", stringlength);

		assertFalse(globalConfigurations.contain(gc));
		assertFalse(globalConfigurations.getErrors().isEmpty());
		assertNotNull(globalConfigurations.getErrors().getError(
				"GlobalConfiguration.siteMetaKeywords.length"));
	}
	
	@After
	public void afterTest() throws Exception {
		for (GlobalConfiguration globalConfiguration : globalConfigurations.getList()) {
			globalConfigurations.remove(globalConfiguration);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}