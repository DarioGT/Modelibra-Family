package org.ieducnews.model.concept.contribution;

import java.net.URL;

import org.ieducnews.model.concept.member.Member;

public class WebLink extends Submission {

	private static final long serialVersionUID = 1;

	private URL link;

	public WebLink(Member member) {
		super(member);
		setSubtype(Submission.Subtype.WEBLINK);
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public URL getLink() {
		return link;
	}

	public void output() {
		super.output();
		System.out.println("link: " + getLink());
	}

}
