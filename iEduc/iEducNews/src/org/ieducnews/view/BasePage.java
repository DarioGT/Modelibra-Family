package org.ieducnews.view;

import org.apache.wicket.markup.html.WebPage;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.view.component.FooterPanel;
import org.ieducnews.view.component.MenuMemberPanel;
import org.ieducnews.view.component.MenuPanel;

public abstract class BasePage extends WebPage {

	public BasePage() {
		add(new MenuPanel("menu"));
		add(new MenuMemberPanel("member"));
		add(new FooterPanel("footer"));
	}

	public WebApp getWebApp() {
		return (WebApp) getApplication();
	}

	public WebAppSession getWebAppSession() {
		return (WebAppSession) getSession();
	}

	public Member getMember() {
		return getWebAppSession().getMember();
	}

}
