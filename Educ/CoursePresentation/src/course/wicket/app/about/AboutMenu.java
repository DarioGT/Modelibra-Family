package course.wicket.app.about;

import org.modelibra.wicket.container.DmMenuPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * About menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class AboutMenu extends DmMenuPanel {

	/**
	 * Constructs the About page menu.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public AboutMenu(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

}
