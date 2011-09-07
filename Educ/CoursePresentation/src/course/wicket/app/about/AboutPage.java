package course.wicket.app.about;

import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * About application page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
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
		View menuView = new View();
		menuView.setWicketId("aboutMenu");
		menuView.setPage(this);
		menuView.setContextView(view);

		add(new AboutMenu(viewModel, menuView));
	}

}
