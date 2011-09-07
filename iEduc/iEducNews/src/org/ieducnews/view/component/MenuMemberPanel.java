package org.ieducnews.view.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.ieducnews.model.concept.contribution.WebLink;
import org.ieducnews.view.HomePage;
import org.ieducnews.view.WebAppSession;
import org.ieducnews.view.concept.member.MemberPage;

public class MenuMemberPanel extends BasePanel {

	private static final long serialVersionUID = 1;

	public MenuMemberPanel(String wicketId) {
		super(wicketId);

		SignInLink signInLink = new SignInLink("signIn");
		signInLink.setVisible(!getWebAppSession().isAuthenticated());
		add(signInLink);
		add(new AccountLink("member"));
		add(new SignOutLink("signOut"));
	}

	private class AccountLink extends Link<WebPage> {

		private static final long serialVersionUID = 1;

		private AccountLink(String wicketId) {
			super(wicketId);
			String accountName = "";
			if (getWebAppSession().isAuthenticated()) {
				accountName = getWebAppSession().getMember().getAccount();
			}
			add(new Label("account", accountName));
		}

		@Override
		public void onClick() {
			setResponsePage(new MemberPage(getWebAppSession().getMember()));
		}

		@Override
		public boolean isVisible() {
			if (getWebAppSession().isAuthenticated()) {
				return true;
			}
			return false;
		}
	}

	private class SignOutLink extends Link<WebLink> {

		private static final long serialVersionUID = 1;

		private SignOutLink(String wicketId) {
			super(wicketId);
		}

		@Override
		public void onClick() {
			WebAppSession.get().invalidate();
			setResponsePage(HomePage.class);
		}

		@Override
		public boolean isVisible() {
			if (getWebAppSession().isAuthenticated()) {
				return true;
			}
			return false;
		}
	}

}