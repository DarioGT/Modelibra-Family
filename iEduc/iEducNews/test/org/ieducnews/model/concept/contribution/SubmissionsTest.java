package org.ieducnews.model.concept.contribution;

import java.net.URL;
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

public class SubmissionsTest {

	private static DomainModel domainModel;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				SubmissionsTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();
	}

	@Test
	public void orderSubmissionsByName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Submissions orderedSubmissions = submissions.orderByName();

		if (!submissions.isEmpty()) {
			Assert.assertFalse(orderedSubmissions.isEmpty());
		}
		Assert.assertEquals(submissions.size(), orderedSubmissions.size());
		Assert.assertNotSame(submissions, orderedSubmissions);

		orderedSubmissions.output("---Submissions ordered by name---");
	}

	@Test
	public void orderSubmissionsByDescendingName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Submissions orderedSubmissions = submissions.orderByName(false);

		if (!submissions.isEmpty()) {
			Assert.assertFalse(orderedSubmissions.isEmpty());
		}
		Assert.assertEquals(submissions.size(), orderedSubmissions.size());
		Assert.assertNotSame(submissions, orderedSubmissions);

		orderedSubmissions
				.output("---Submissions ordered by descending name---");
	}

	@Test
	public void orderSubmissionsByCreationDate() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Submissions orderedSubmissions = submissions.orderByCreationDate();

		if (!submissions.isEmpty()) {
			Assert.assertFalse(orderedSubmissions.isEmpty());
		}
		Assert.assertEquals(submissions.size(), orderedSubmissions.size());
		Assert.assertNotSame(submissions, orderedSubmissions);

		orderedSubmissions.output("---Submissions ordered by creation date---");
	}

	@Test
	public void retrieveSubmissionByName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Hacker News";
		Submission submission = submissions.retrieveByName(expectedName);
		if (submission != null) {
			Assert.assertEquals(expectedName, submission.getName());
		}
	}

	@Test
	public void submissionsNotRetrievedByName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Educ News";
		boolean containCheck = submissions.contains(expectedName);
		if (!containCheck) {
			Submission submission = submissions.retrieveByName(expectedName);
			Assert.assertNull(submission);
		}
	}

	@Test
	public void validateSubmissionForNullName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		if (member != null) {
			Submission submission = new WebLink(member);
			Assert.assertFalse(submissions.add(submission));
			Assert.assertFalse(submissions.contains(submission));
		}
	}

	@Test
	public void validateSubmissionForUniqueName() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		String expectedName = "Hacker News";
		Submission submission = submissions.retrieveByName(expectedName);
		if (submission != null && submission.isWebLink()) {
			Members members = domainModel.getMembers();
			Member member = members.retrieveByAccount("dzenanr");
			if (member != null) {
				Submission anotherLink = new WebLink(member);
				anotherLink.setName("Hacker News");
				Assert.assertFalse(submissions.add(anotherLink));
				Assert.assertFalse(submissions.contains(anotherLink));
			}
		}
	}

	@Test
	public void selectSubmissionsCreatedToday() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Date selectionDate = new Date();
		Submissions selectedSubmissions = submissions
				.selectByDate(selectionDate);

		System.out.println("selection date: " + selectionDate);
		EasyDate selectionEasyDate = new EasyDate(selectionDate);
		System.out.println("selection easy date: " + selectionEasyDate);
		selectedSubmissions.output("---Selected web links---");
	}

	@Test
	public void submissionNotRemoved() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		if (member != null) {
			WebLink calendarLink = new WebLink(member);
			calendarLink.setName("Class Calendar");
			calendarLink
					.setLink(new URL(
							"http://java.sun.com/javase/6/docs/api/java/util/Calendar.html"));
			Assert.assertFalse(submissions.remove(calendarLink));
			Assert.assertFalse(member.getSubmissions().remove(calendarLink));
		}
	}

	@Test
	public void removeSubmission() throws Exception {
		Submissions submissions = domainModel.getSubmissions();
		Members members = domainModel.getMembers();
		Member member = members.retrieveByAccount("dzenanr");
		if (member != null) {
			WebLink gregorianCalendarLink = new WebLink(member);
			gregorianCalendarLink.setName("Class GregorianCalendar");
			gregorianCalendarLink
					.setLink(new URL(
							"http://java.sun.com/javase/6/docs/api/java/util/GregorianCalendar.html"));
			Assert.assertTrue(submissions.add(gregorianCalendarLink));
			gregorianCalendarLink.incrementPoints();
			Assert.assertTrue(member.getSubmissions().add(gregorianCalendarLink));
			member.incrementKarma();
			Assert.assertTrue(submissions.remove(gregorianCalendarLink));
			gregorianCalendarLink.decrementPoints();
			Assert.assertTrue(member.getSubmissions().remove(gregorianCalendarLink));
			member.decrementKarma();
		}
	}

	@AfterClass
	public static void afterTests() {
		domainModel.save();
	}

}
