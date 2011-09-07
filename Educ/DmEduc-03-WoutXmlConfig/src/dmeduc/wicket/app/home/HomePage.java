package dmeduc.wicket.app.home;

import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.neighbor.ParentChildPropertyDisplayListPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.url.Urls;
import dmeduc.wicket.app.DmEducApp;

public class HomePage extends DmPage {

	public HomePage() {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		ViewModel homePageModel = new ViewModel(webLink);
		View homePageView = new View();
		homePageView.setContextView(homePageView);
		homePageView.setPage(this);
		homePageView.setWicketId("homeMenu");

		// Menu
		add(new HomeMenu(homePageModel, homePageView));

		// Category Urls
		ViewModel categoryUrlsModel = new ViewModel(webLink);
		Categories categories = webLink.getCategories();
		Categories orderedCategories = categories
				.getCategoriesOrderedByName(true);
		categoryUrlsModel.setEntities(orderedCategories);
		categoryUrlsModel.setPropertyCode("name");
		categoryUrlsModel.getUserProperties().addUserProperty("childNeighbor",
				"urls");
		categoryUrlsModel.getUserProperties().addUserProperty("childProperty",
				"link");

		View categoryUrlsView = new View();
		categoryUrlsView.setWicketId("categoryNameUrlLinkList");
		categoryUrlsView.setTitle("Category.WebLinks");

		ParentChildPropertyDisplayListPanel categoryNameUrlLinkList = new ParentChildPropertyDisplayListPanel(
				categoryUrlsModel, categoryUrlsView);
		add(categoryNameUrlLinkList);

		// Software descriptions
		ViewModel sofwareCategoryUrlsModel = new ViewModel(webLink);
		Category softwareCategory = categories.getCategory("name", "Software");
		Urls softwareCategoryUrls = softwareCategory.getUrls();
		sofwareCategoryUrlsModel.setEntities(softwareCategoryUrls);
		sofwareCategoryUrlsModel.setPropertyCode("description");

		View sofwareCategoryUrlsView = new View();
		sofwareCategoryUrlsView
				.setWicketId("sofwareCategoryUrlDescriptionList");
		sofwareCategoryUrlsView.setTitle("Category.Software");

		EntityPropertyDisplayListPanel sofwareCategoryUrlDescriptionList = new EntityPropertyDisplayListPanel(
				sofwareCategoryUrlsModel, sofwareCategoryUrlsView);
		add(sofwareCategoryUrlDescriptionList);
	}

}
