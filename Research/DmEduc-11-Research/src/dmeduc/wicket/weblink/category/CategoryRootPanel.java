package dmeduc.wicket.weblink.category;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityAddFormPage;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.WebLink;

/**
 * Category tree root panel.
 * 
 * @author Vedad Kirlic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class CategoryRootPanel extends Panel {

	/**
	 * Constructs the Category tree root panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CategoryRootPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());

		String categories = LocalizedText.getApplicationPropertiesText(this,
				"Categories");
		add(new Label("rootName", categories));

		WebLink webLink = (WebLink) viewModel.getModel();
		final ViewModel categoriesAddViewModel = new ViewModel();
		categoriesAddViewModel.copyPropertiesFrom(viewModel);
		categoriesAddViewModel.setEntities(webLink.getCategories());
		categoriesAddViewModel.setEntity(null);

		add(new Link("categoryAddLink") {
			public void onClick() {
				setResponsePage(new EntityAddFormPage(categoriesAddViewModel,
						view));
			}
		});
	}

}
