package dmeduc.wicket.app.about;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.url.Urls;
import dmeduc.wicket.app.DmEducApp;

/**
 * About application page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class AboutPage extends DmPage {

	private static Log log = LogFactory.getLog(AboutPage.class);

	/**
	 * Constructs the About page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public AboutPage(final ViewModel viewModel, final View view) {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		// Menu
		View menuView = new View();
		menuView.setWicketId("aboutMenu");
		menuView.setPage(this);
		menuView.setContextView(view);

		add(new AboutMenu(viewModel, menuView));

		// Links Category Urls
		ViewModel linksModel = new ViewModel(webLink);
		Categories categories = webLink.getCategories();
		Category linksCategory = categories.getCategoryByName("Links");
		if (linksCategory != null) {
			Urls linksCategoryUrls = linksCategory.getUrls().getApprovedUrls();
			linksModel.setEntities(linksCategoryUrls);
			linksModel.setPropertyCode("link");
		}

		View linksView = new View();
		linksView.setPage(this);
		linksView.setWicketId("linksCategoryUrlsList");

		Panel links;
		if (linksCategory != null) {
			links = new EntityPropertyDisplayListPanel(linksModel, linksView);
		} else {
			links = new Panel("linksCategoryUrlsList");
			links.setVisible(false);
		}
		add(links);
	}

}
