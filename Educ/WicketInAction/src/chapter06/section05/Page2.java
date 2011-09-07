package chapter06.section05;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author dashorst
 */
public class Page2 extends WebPage {
	public Page2(final Page returnTo) {
		add(new Link("returnLink") {
			@Override
			public void onClick() {
				setResponsePage(returnTo);
			}
		});
	}
}
