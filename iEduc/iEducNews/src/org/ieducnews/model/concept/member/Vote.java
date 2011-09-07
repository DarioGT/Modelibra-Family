package org.ieducnews.model.concept.member;

import java.io.Serializable;

import org.ieducnews.model.concept.contribution.Comment;
import org.ieducnews.model.concept.contribution.Submission;

public class Vote implements Serializable {

	private static final long serialVersionUID = 1;

	private Boolean up = true;

	private Member member;

	private Submission submission;

	private Comment comment;

	public Vote(Member member, Submission submission) {
		if (member == null || submission == null) {
			throw new RuntimeException(
					"A vote must have a member and a submission.");
		}
		this.member = member;
		this.submission = submission;
	}

	public Vote(Member member, Comment comment) {
		if (member == null || comment == null) {
			throw new RuntimeException(
					"A vote must have a member and a comment.");
		}
		this.member = member;
		this.comment = comment;
	}

	public void setUp(Boolean up) {
		this.up = up;
	}

	public Boolean getUp() {
		return up;
	}

	public boolean isUp() {
		return getUp();
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

	public Submission getSubmission() {
		return submission;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}

	public void output() {
		System.out.println("text: " + getUp());

		if (getComment() == null) {
			System.out.println("submission name: " + getSubmission().getName());
		} else {
			System.out.println("comment text: " + getComment().getText());
		}
	}

}
