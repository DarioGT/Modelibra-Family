package org.ieducnews.view;

import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.config.ModelProperties;
import org.junit.BeforeClass;
import org.junit.Test;

public class HomePageTest {

	private static DomainModel domainModel;

	private static WicketTester tester;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				AboutPageTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();

		WebApp webApp = new WebApp();
		webApp.setDomainModel(domainModel);

		tester = new WicketTester(webApp);
		tester.startPage(HomePage.class);
	}

	@Test
	public void containComponents() {
		tester.assertComponent("menu", Panel.class);
		tester.assertComponent("submissions", PageableListView.class);
		if (domainModel.getSubmissions().size() <= HomePage.NUMBER_OF_SUBMISSIONS_ON_ONE_PAGE) {
			tester.assertInvisible("navigator");
		} else {
			tester.assertComponent("navigator", PagingNavigator.class);
			tester.assertVisible("navigator");
		}
		tester.assertComponent("footer", Panel.class);
	}

}
