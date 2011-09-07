package org.ieducnews.model.concept.contribution;

import org.ieducnews.model.concept.member.Member;

public class Comment extends Contribution {

	private static final long serialVersionUID = 1;

	private String text;

	private Member member;

	private Submission repliedToSubmission;

	private Comment repliedToComment;

	private Comments replies = new Comments();

	public Comment(Member member, Submission repliedToSubmission) {
		if (member == null || repliedToSubmission == null) {
			throw new RuntimeException(
					"A comment must have a member and a submission to reply to.");
		}
		this.member = member;
		this.repliedToSubmission = repliedToSubmission;
	}

	public Comment(Member member, Submission repliedToSubmission,
			Comment repliedToComment) {
		this(member, repliedToSubmission);
		if (repliedToComment == null) {
			throw new RuntimeException(
					"A comment must have a comment to reply to.");
		}
		this.repliedToComment = repliedToComment;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setRepliedToSubmission(Submission repliedToSubmission) {
		this.repliedToSubmission = repliedToSubmission;
	}

	public Submission getRepliedToSubmission() {
		return repliedToSubmission;
	}

	public void setRepliedToComment(Comment repliedToComment) {
		this.repliedToComment = repliedToComment;
	}

	public Comment getRepliedToComment() {
		return repliedToComment;
	}

	public Comments getReplies() {
		return replies;
	}

	public void output() {
		super.output();
		System.out.println("text: " + getText());

		System.out.println("member account: " + getMember().getAccount());
		System.out.println("replied submission name: "
				+ getRepliedToSubmission().getName());
		if (getRepliedToComment() != null) {
			System.out.println("replied comment text: "
					+ getRepliedToComment().getText());
		}

		getReplies().output("Comment Replies");
	}

}
