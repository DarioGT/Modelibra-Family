package dmeduc.wicket.weblink.category;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityDisplayPanel;
import org.modelibra.wicket.concept.EntityUpdateTablePanel;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.url.Urls;

/**
 * Category page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class CategoryUrlsPage extends DmUpdatePage {

	/**
	 * Constructs the Category page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CategoryUrlsPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel categoryUrlsPageModel = new ViewModel();
		categoryUrlsPageModel.copyPropertiesFrom(viewModel);
		View categoryUrlsPageView = new View();
		categoryUrlsPageView.copyPropertiesFrom(view);
		categoryUrlsPageView.setContextView(view);
		categoryUrlsPageView.setPage(this);

		// Category
		ViewModel categoryModel = new ViewModel();
		categoryModel.copyPropertiesFrom(categoryUrlsPageModel);
		View categoryView = new View();
		categoryView.copyPropertiesFrom(categoryUrlsPageView);
		categoryView.setWicketId("categorySection");
		add(new EntityDisplayPanel(categoryModel, categoryView));

		// Category Urls
		ViewModel categoryUrlsModel = new ViewModel();
		categoryUrlsModel.copyPropertiesFrom(categoryUrlsPageModel);
		Category category = (Category) viewModel.getEntity();
		Urls categoryUrls = category.getUrls().getApprovedUrls();
		categoryUrlsModel.setEntities(categoryUrls);
		categoryUrlsModel.setEntity(null);
		View categoryUrlsView = new View();
		categoryUrlsView.copyPropertiesFrom(categoryUrlsPageView);
		categoryUrlsView.setContextView(categoryUrlsPageView);
		categoryUrlsView.setUpdate(true);
		categoryUrlsView.setWicketId("categoryUrlsSection");
		Panel categoryUrlsPanel;
		if (category.isApproved()) {
			categoryUrlsPanel = new EntityUpdateTablePanel(categoryUrlsModel,
					categoryUrlsView);
		} else {
			categoryUrlsPanel = new Panel("categoryUrlsSection");
			categoryUrlsPanel.setVisible(false);
		}
		add(categoryUrlsPanel);
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
				return new CategoryUrlsPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return CategoryUrlsPage.class;
			}
		});
		return link;
	}

}
