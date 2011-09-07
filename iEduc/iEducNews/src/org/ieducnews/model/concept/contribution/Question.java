package org.ieducnews.model.concept.contribution;

import org.ieducnews.model.concept.member.Member;

public class Question extends Submission {

	private static final long serialVersionUID = 1;

	private String text;
	
	public Question(Member member) {
		super(member);
		setSubtype(Submission.Subtype.QUESTION);
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void output() {
		super.output();
		System.out.println("text: " + getText());
	}

}
