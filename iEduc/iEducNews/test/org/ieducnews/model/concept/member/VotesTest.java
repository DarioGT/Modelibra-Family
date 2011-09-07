package org.ieducnews.model.concept.member;

import org.ieducnews.model.DomainModel;
import org.ieducnews.model.concept.contribution.Comment;
import org.ieducnews.model.concept.contribution.Comments;
import org.ieducnews.model.concept.contribution.Submission;
import org.ieducnews.model.concept.contribution.Submissions;
import org.ieducnews.model.concept.contribution.SubmissionsTest;
import org.ieducnews.model.config.ModelProperties;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VotesTest {

	private static DomainModel domainModel;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				SubmissionsTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();
	}

	@Test
	public void memberUpvotesSubmission() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions.retrieveByName("Hacker News");
		if (member != null && submission != null) {
			Vote vote = new Vote(member, submission);
			Assert.assertTrue(member.getVotes().add(vote));
			int submissionPreviousPoints = submission.getPoints();
			submission.incrementPoints();
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPoints + 1);
		}
	}

	@Test
	public void memberDownvotesSubmission() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions.retrieveByName("Hacker News");
		if (member != null && submission != null) {
			Vote vote = new Vote(member, submission);
			vote.setUp(false);
			Assert.assertTrue(member.getVotes().add(vote));
			int submissionPreviousPoints = submission.getPoints();
			submission.decrementPoints();
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPoints - 1);
		}
	}

	@Test
	public void memberUpvotesComment() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions
				.retrieveByName("Jane's E-Learning Pick");
		Comments comments = submission.getComments();
		Comment comment = comments.retrieveByKeyword("pick");
		if (member != null && submission != null && comment != null) {
			Vote vote = new Vote(member, comment);
			Assert.assertTrue(member.getVotes().add(vote));
			int submissionPreviousPoints = submission.getPoints();
			submission.incrementPoints();
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPoints + 1);
		}
	}

	@Test
	public void memberDownvotesComment() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions
				.retrieveByName("Jane's E-Learning Pick");
		Comments comments = submission.getComments();
		Comment comment = comments.retrieveByKeyword("pick");
		if (member != null && submission != null && comment != null) {
			Vote vote = new Vote(member, comment);
			vote.setUp(false);
			Assert.assertTrue(member.getVotes().add(vote));
			int submissionPreviousPoints = submission.getPoints();
			submission.decrementPoints();
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPoints - 1);
		}
	}

	@Test
	public void memberAddVoteSubmission() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions.retrieveByName("Hacker News");
		if (member != null && submission != null) {
			Vote vote = new Vote(member, submission);
			Assert.assertTrue(member.getVotes().add(vote));
			submission.incrementPoints();
			Assert.assertTrue(member.getVotes().contains(vote));
		}
	}

	@Test
	public void memberUpvotesThenDownvoteSubmission() throws Exception {
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("pascald");
		Submissions submissions = domainModel.getSubmissions();
		Submission submission = submissions.retrieveByName("Hacker News");
		if (member != null && submission != null) {
			Vote vote1 = new Vote(member, submission);
			Assert.assertTrue(member.getVotes().add(vote1));
			int submissionPreviousPointsForVote1 = submission.getPoints();
			submission.incrementPoints();
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPointsForVote1 + 1);
			Assert.assertTrue(member.getVotes().contains(vote1));

			Vote vote2 = new Vote(member, submission);
			vote2.setUp(false);
			Assert.assertTrue(member.getVotes().add(vote2));
			int submissionPreviousPointsForVote2 = submission.getPoints();
			
			submission.decrementPoints();
			
			Assert
					.assertTrue(submission.getPoints() == submissionPreviousPointsForVote2 - 1);
			
			System.out.println("Initial Submission points: " + submissionPreviousPointsForVote1);
			System.out.println("After Upvote Submission points: " + submissionPreviousPointsForVote2);
			System.out.println("After Downvote Submission points: " + (submissionPreviousPointsForVote2 -1));
			
		}
	}

	@AfterClass
	public static void afterTests() {
		domainModel.save();
	}

}
