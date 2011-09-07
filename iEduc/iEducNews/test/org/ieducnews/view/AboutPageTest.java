package org.ieducnews.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.config.ModelProperties;
import org.junit.BeforeClass;
import org.junit.Test;

public class AboutPageTest {

	private static WicketTester tester;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				AboutPageTest.class);
		DomainModel domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();

		WebApp webApp = new WebApp();
		webApp.setDomainModel(domainModel);

		tester = new WicketTester(webApp);
		tester.startPage(AboutPage.class);
	}

	@Test
	public void renderAboutPage() {
		tester.assertRenderedPage(AboutPage.class);
		tester.assertNoErrorMessage();
	}

	@Test
	public void containComponents() {
		tester.assertComponent("menu", Panel.class);
		tester.assertComponent("footer", Panel.class);
	}

}
