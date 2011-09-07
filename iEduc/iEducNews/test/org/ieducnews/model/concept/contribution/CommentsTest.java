package org.ieducnews.model.concept.contribution;

import java.util.Date;

import org.ieducnews.model.DomainModel;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.model.concept.member.Members;
import org.ieducnews.model.config.ModelProperties;
import org.ieducnews.model.type.EasyDate;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommentsTest {

	private static DomainModel domainModel;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				SubmissionsTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();
	}

	@Test
	public void orderCommentsByCreationDate() throws Exception {
		Comments comments = domainModel.getComments();
		Comments orderedComments = comments.orderByCreationDate();

		if (!comments.isEmpty()) {
			Assert.assertFalse(orderedComments.isEmpty());
		}
		Assert.assertEquals(comments.size(), orderedComments.size());
		Assert.assertNotSame(comments, orderedComments);

		orderedComments.output("---Comments ordered by creation date---");
	}
	
	@Test
	public void addValidComment() throws Exception {
		Comments comments = domainModel.getComments();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Hacker News";
		Submission submission = submissions.retrieveByName(expectedName);
		if (member != null && submission != null) {
			Comment comment = new Comment(member, submission);
			comment.setText("Comment text test");
			Assert.assertTrue(comments.add(comment));
			Assert.assertTrue(comments.contains(comment));
		}
	}
	
	@Test
	public void addValidReplyComment() throws Exception {
		Comments comments = domainModel.getComments();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Hacker News";
		Submission submission = submissions.retrieveByName(expectedName);
		if (member != null && submission != null) {
			Comment comment = new Comment(member, submission);
			comment.setText("Comment text test");
			Assert.assertTrue(comments.add(comment));
			Assert.assertTrue(comments.contains(comment));
			Comment replyComment = new Comment(member, submission, comment);
			replyComment.setText("Reply Comment text test");
			Assert.assertTrue(comments.add(replyComment));
			Assert.assertTrue(comments.contains(replyComment));
		}
	}

	@Test
	public void validateCommentForNullText() throws Exception {
		Comments comments = domainModel.getComments();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Hacker News";
		Submission submission = submissions.retrieveByName(expectedName);
		if (member != null && submission != null) {
			Comment comment = new Comment(member, submission);
			Assert.assertFalse(comments.add(comment));
			Assert.assertFalse(comments.contains(comment));
		}
	}

	@Test
	public void selectCommentsCreatedToday() throws Exception {
		Comments comments = domainModel.getComments();
		Date selectionDate = new Date();
		Comments selectedComments = comments.selectByDate(selectionDate);

		System.out.println("selection date: " + selectionDate);
		EasyDate selectionEasyDate = new EasyDate(selectionDate);
		System.out.println("selection easy date: " + selectionEasyDate);
		selectedComments.output("---Selected comments---");
	}

	@Test
	public void removeComment() throws Exception {
		Comments comments = domainModel.getComments();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions.retrieveByName("Hacker News");
		if (member != null && submission != null) {
			Comment comment = new Comment(member, submission);
			comment
					.setText("A useful collection of links and questions for dicussion.");
			Assert.assertTrue(comments.add(comment));
			comment.incrementPoints();
			Assert.assertTrue(member.getComments().add(comment));
			member.incrementKarma();
			Assert.assertTrue(comments.remove(comment));
			comment.decrementPoints();
			Assert.assertTrue(member.getComments().remove(comment));
			member.decrementKarma();
		}
	}

	@AfterClass
	public static void afterTests() {
		domainModel.save();
	}

}
