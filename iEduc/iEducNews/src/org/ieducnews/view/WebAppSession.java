package org.ieducnews.view;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.model.concept.member.Member.SecurityRole;

public class WebAppSession extends WebSession {

	private static final long serialVersionUID = 1;

	private Member member;

	public WebAppSession(Request request) {
		super(request);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		dirty();
		this.member = member;
	}

	public boolean isAuthenticated() {
		return (member != null);
	}

	public boolean isAdmin() {
		return (member.getRole().equals(SecurityRole.ADMIN));
	}

}