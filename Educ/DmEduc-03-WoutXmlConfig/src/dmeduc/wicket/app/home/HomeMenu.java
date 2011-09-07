package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.modelibra.wicket.concept.selection.EntitySelectionPage;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.app.about.AboutPage;

@SuppressWarnings("serial")
public class HomeMenu extends DmPanel {

	public HomeMenu(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		// Domain
		Link domainLink = new Link("domainLink") {
			public void onClick() {
				setResponsePage(dmEducApp.getDomainPageClass());
			}
		};
		add(domainLink);

		// Select categories
		ViewModel categorySelectionViewModel = new ViewModel(webLink);
		Categories manufacturers = webLink.getCategories()
				.getCategoriesOrderedByName(true);
		categorySelectionViewModel.setEntities(manufacturers);
		View categorySelectionView = new View();
		categorySelectionView.setContextView(view);
		categorySelectionView.setPage(view.getPage());

		Link categorySelectionLink = EntitySelectionPage.link(
				"categorySelectionLink", categorySelectionViewModel,
				categorySelectionView);
		add(categorySelectionLink);

		// About
		View aboutView = new View();
		aboutView.setContextView(view);
		aboutView.setPage(view.getPage());
		add(AboutPage.link("aboutLink", viewModel, aboutView));
	}
}
