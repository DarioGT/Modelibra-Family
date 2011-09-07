package dmeduc.wicket.weblink.url;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.url.Urls;

/**
 * Entity display list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
public class EntityDisplayListPanel extends
		org.modelibra.wicket.concept.EntityDisplayListPanel {

	private static final long serialVersionUID = 110110115L;

	/**
	 * Constructs an entity display list panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayListPanel(final ViewModel viewModel, final View view) {
		super(getNewViewModel(viewModel), view);
	}

	/**
	 * Gets a new view model.
	 * 
	 * @param viewModel
	 *            model context
	 * @return new view model
	 */
	private static ViewModel getNewViewModel(final ViewModel viewModel) {
		ViewModel newViewModel = new ViewModel();
		newViewModel.copyPropertiesFrom(viewModel);
		Urls urls = (Urls) viewModel.getEntities();
		urls = urls.getApprovedUrls().getUrlsOrderedByName();
		newViewModel.setEntities(urls);
		return newViewModel;
	}

}
