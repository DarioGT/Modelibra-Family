package dmeduc.wicket.app.about;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmMenuPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.wicket.weblink.comment.ContactUsPage;
import dmeduc.wicket.weblink.question.FaqPage;

/**
 * About menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-20
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
		Link faqLink = new Link("faqLink") {
			public void onClick() {
				setResponsePage(new FaqPage(viewModel, view));
			}
		};
		add(faqLink);

		// Contact Us
		PageLink contactUsLink = ContactUsPage.link("contactUsLink", viewModel,
				view);
		add(contactUsLink);
	}

}
