package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.app.domain.DomainPage;

/**
 * Application home page menu.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-26
 */
@SuppressWarnings("serial")
public class HomeMenu extends Panel {

	public HomeMenu(final String wicketId) {
		super(wicketId);
		Link domainLink = new Link("domainLink") {
			public void onClick() {
				setResponsePage(new DomainPage());
			}
		};
		add(domainLink);
	}

}
