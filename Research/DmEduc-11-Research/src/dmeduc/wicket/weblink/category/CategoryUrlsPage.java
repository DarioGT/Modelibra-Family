package dmeduc.wicket.weblink.category;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.neighbor.EntityParentChildUpdatePanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.category.Category;

/**
 * Category Urls page.
 * 
 * @author Vedad Kirlic
 * @version 2008-05-31
 */
@SuppressWarnings("serial")
public class CategoryUrlsPage extends DmUpdatePage {

	/**
	 * Constructs the CategoryUrls page.
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
		categoryUrlsPageView.setRecreateContext(false);
		categoryUrlsPageView.setContextView(view);
		categoryUrlsPageView.setWicketId("categoryUrlsSection");
		categoryUrlsPageView.setPage(this);
		setVersioned(true);

		Panel categoryUrlsPanel = new EntityParentChildUpdatePanel(
				categoryUrlsPageModel, categoryUrlsPageView) {
			@Override
			protected IEntities<?> getChildEntities(IEntity<?> parentEntity) {
				Category category = (Category) parentEntity;
				if (category.isApproved()) {
					return category.getUrls();
				}
				return null;
			}
		};
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
