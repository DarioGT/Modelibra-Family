package org.ieducnews.view;

import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.config.ModelProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebAppTest {

	private static WicketTester tester;

	private static WebApp webApp;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				AboutPageTest.class);
		DomainModel domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();
		webApp = new WebApp();
		webApp.setDomainModel(domainModel);
		tester = new WicketTester(webApp);
	}

	@Test
	public void defineHomePage() throws Exception {
		Assert.assertEquals(HomePage.class, webApp.getHomePage());
	}

	@Test
	public void renderHomePage() {
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
		tester.assertNoErrorMessage();
	}

}
