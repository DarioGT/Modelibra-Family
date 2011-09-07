package dmeduc.wicket.weblink.category;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.concept.EntityAddFormPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.category.Category;
import dmeduc.wicket.weblink.url.UrlDisplayList;

/**
 * Category page link panel.
 * 
 * @author Vedad Kirlic
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class CategoryNodePanel extends Panel {

	/**
	 * Constructs the Category page link panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CategoryNodePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId(), new Model(viewModel.getEntity()));

		Category category = (Category) viewModel.getEntity();

		Link categoryPageLink = CategoryUrlsPage.link("categoryPageLink",
				viewModel, view);
		categoryPageLink.add(new Label("categoryName", new PropertyModel(
				category, "name")));
		add(categoryPageLink);

		final ViewModel categoriesAddViewModel = new ViewModel();
		categoriesAddViewModel.copyPropertiesFrom(viewModel);
		categoriesAddViewModel.setEntities(category.getCategories());
		categoriesAddViewModel.setEntity(null);

		add(new Link("categoryAddLink") {
			public void onClick() {
				setResponsePage(new EntityAddFormPage(categoriesAddViewModel,
						view));
			}
		});

		add(new UrlDisplayList("urlDisplayList", category));
	}

}
