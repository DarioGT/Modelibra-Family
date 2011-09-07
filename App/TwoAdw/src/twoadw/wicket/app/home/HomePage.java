package twoadw.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.app.domain.DomainPage;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import twoadw.Twoadw;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.website.productcategories.CategoryListPage;

@SuppressWarnings("serial")
public class HomePage extends DmPage {

	public HomePage() {
		TwoadwApp twoadwApp = (TwoadwApp) getApplication();
		Twoadw twoadw = twoadwApp.getTwoadw();
		
		// Domain
		Link domainLink = new Link("domainLink") {
			public void onClick() {
				setResponsePage(new DomainPage());
			}
		};
		add(domainLink);

	
		add(new Link("transaction") {
			public void onClick() {
				setResponsePage(new CategoryListPage());
			}
		});
	}

}
