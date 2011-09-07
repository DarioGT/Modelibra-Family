package dmeduc.wicket.app.about;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmMenuPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

@SuppressWarnings("serial")
public class AboutPage extends DmPage {

	public AboutPage(final ViewModel viewModel, final View view) {
		// About Menu
		View aboutMenuView = new View();
		aboutMenuView.setContextView(view);
		aboutMenuView.setPage(this);
		aboutMenuView.setWicketId("aboutMenu");
		add(new DmMenuPanel(viewModel, aboutMenuView));

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
