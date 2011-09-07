package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.view.concept.contribution.AddSubmissionPage;

public class SubmissionLink extends Link<WebPage> {

	private static final long serialVersionUID = 1;

	public SubmissionLink(String wicketId) {
		super(wicketId);
	}

	@Override
	public void onClick() {
		setResponsePage(AddSubmissionPage.class);
	}

}