package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.view.concept.member.SignInPage;

public class SignInLink extends Link<WebPage> {

	private static final long serialVersionUID = 1;

	public SignInLink(String wicketId) {
		super(wicketId);
	}

	@Override
	public void onClick() {
		setResponsePage(SignInPage.class);
	}

}