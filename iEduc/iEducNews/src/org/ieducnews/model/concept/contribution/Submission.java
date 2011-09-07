package org.ieducnews.model.concept.contribution;

import org.ieducnews.model.concept.member.Member;

public class Submission extends Contribution {

	private static final long serialVersionUID = 1;

	private String name;

	private Subtype subtype = Subtype.WEBLINK;

	private Member member;

	private Comments comments = new Comments();

	public enum Subtype {
		WEBLINK, QUESTION
	}

	public Submission(Member member) {
		if (member == null) {
			throw new RuntimeException("A submission must have a member.");
		}
		this.member = member;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSubtype(Subtype subtype) {
		this.subtype = subtype;
	}

	public Subtype getSubtype() {
		return subtype;
	}

	public boolean isWebLink() {
		if (subtype.equals(Subtype.WEBLINK)) {
			return true;
		}
		return false;
	}

	public boolean isQuestion() {
		if (subtype.equals(Subtype.QUESTION)) {
			return true;
		}
		return false;
	}

	public Member getMember() {
		return member;
	}

	public Comments getComments() {
		return comments;
	}

	public void output() {
		super.output();
		System.out.println("name: " + getName());
		System.out.println("subtype: " + getSubtype());

		System.out.println("member account: " + getMember().getAccount());

		getComments().output("Comments");
	}

}
