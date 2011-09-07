package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.view.AboutPage;
import org.ieducnews.view.WebAppSession;

public class FooterPanel extends BasePanel {

	private static final long serialVersionUID = 1;

	public FooterPanel(String wicketId) {
		super(wicketId);
		add(new HomeLink("new"));
		Link<WebPage> submissionLink = new SubmissionLink("submit");
		add(submissionLink);
		WebAppSession session = getWebAppSession();
		if (!session.isAuthenticated()) {
			submissionLink.setVisible(false);
		}
		add(new BookmarkablePageLink<WebPage>("about", AboutPage.class));
	}

}
