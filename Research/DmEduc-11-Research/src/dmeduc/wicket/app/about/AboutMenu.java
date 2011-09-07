package dmeduc.wicket.app.about;

import org.modelibra.wicket.container.DmMenuPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.wicket.weblink.comment.ContactUsPage;
import dmeduc.wicket.weblink.question.FaqPage;

/**
 * About menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
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
		// FAQ
		View faqView = new View();
		faqView.setContextView(view);
		faqView.setPage(view.getPage());
		add(FaqPage.link("faqLink", viewModel, faqView));

		// Contact Us
		View contactUsView = new View();
		contactUsView.setContextView(view);
		contactUsView.setPage(view.getPage());
		add(ContactUsPage.link("contactUsLink", viewModel, contactUsView));
	}

}
