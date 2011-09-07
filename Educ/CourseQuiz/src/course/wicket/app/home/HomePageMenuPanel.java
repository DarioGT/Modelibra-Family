package course.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.modelibra.wicket.container.MainMenuPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.wicket.app.about.AboutPage;
import course.wicket.app.upload.UploadPage;

/**
 * Application home page menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-19
 */
@SuppressWarnings("serial")
public class HomePageMenuPanel extends MainMenuPanel {

	public HomePageMenuPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		
		add(new Link("upload") {
			public void onClick() {
				setResponsePage(UploadPage.class);
			}
		});

		add(new Link("about") {
			public void onClick() {
				setResponsePage(AboutPage.class);
			}
		});
	}

}
