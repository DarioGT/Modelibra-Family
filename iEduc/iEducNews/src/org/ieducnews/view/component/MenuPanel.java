package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.view.AboutPage;
import org.ieducnews.view.WebAppSession;

public class MenuPanel extends BasePanel {

	private static final long serialVersionUID = 1;

	public MenuPanel(String wicketId) {
		super(wicketId);
		add(new HomeLink("new"));
		Link<WebPage> submissionLink = new SubmissionLink("submit");
		add(submissionLink);
		add(new BookmarkablePageLink<WebPage>("about", AboutPage.class));
	}

}
