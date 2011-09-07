package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.view.HomePage;

public class HomeLink extends Link<WebPage> {

	private static final long serialVersionUID = 1;

	public HomeLink(String wicketId) {
		super(wicketId);
	}

	@Override
	public void onClick() {
		setResponsePage(HomePage.class);
	}

}
