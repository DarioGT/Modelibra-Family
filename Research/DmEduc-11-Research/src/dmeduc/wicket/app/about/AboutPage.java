package dmeduc.wicket.app.about;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
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
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
public class AboutPage extends DmPage {

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

		// About Menu
		View aboutMenuView = new View();
		aboutMenuView.setContextView(view);
		aboutMenuView.setPage(this);
		aboutMenuView.setWicketId("aboutMenu");
		add(new AboutMenu(viewModel, aboutMenuView));

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

	/**
	 * Constructs a link to this page.
	 * 
	 * @param linkId
	 *            link Wicket id
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public static PageLink link(final String linkId, final ViewModel viewModel,
			final View view) {
		PageLink link = new PageLink(linkId, new IPageLink() {
			public Page getPage() {
				return new AboutPage(viewModel, view);
			}

			public Class<?> getPageIdentity() {
				return AboutPage.class;
			}
		});
		return link;
	}

}
