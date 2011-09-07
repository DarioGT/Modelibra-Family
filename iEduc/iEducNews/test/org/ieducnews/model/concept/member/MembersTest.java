package org.ieducnews.model.concept.member;

import org.ieducnews.model.DomainModel;
import org.ieducnews.model.config.ModelProperties;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MembersTest {

	private static DomainModel domainModel;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(MembersTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();
	}

	@Test
	public void orderMembersByAccount() throws Exception {
		Members members = domainModel.getMembers();
		Members orderedMembers = members.orderByAccount();

		if (!members.isEmpty()) {
			Assert.assertFalse(orderedMembers.isEmpty());
		}
		Assert.assertEquals(members.size(), orderedMembers.size());
		Assert.assertNotSame(members, orderedMembers);

		orderedMembers.output("---Members ordered by account---");
	}

	@Test
	public void orderMembersByLastThenFirstName() throws Exception {
		Members members = domainModel.getMembers();
		Members orderedMembers = members.orderByLastFirstName();

		if (!members.isEmpty()) {
			Assert.assertFalse(orderedMembers.isEmpty());
		}
		Assert.assertEquals(members.size(), orderedMembers.size());
		Assert.assertNotSame(members, orderedMembers);

		orderedMembers.output("---Members ordered by last then first name---");
	}

	@Test
	public void orderMembersByDescendingVote() throws Exception {
		Members members = domainModel.getMembers();
		Members orderedMembers = members.orderByVote(false);

		if (!members.isEmpty()) {
			Assert.assertFalse(orderedMembers.isEmpty());
		}
		Assert.assertEquals(members.size(), orderedMembers.size());
		Assert.assertNotSame(members, orderedMembers);

		orderedMembers.output("---Members ordered by descending vote---");
	}

	@Test
	public void retrieveMemberByAccount() throws Exception {
		Members members = domainModel.getMembers();
		String expectedAccount = "dzenanr";
		Member member = members.retrieveByAccount(expectedAccount);
		if (member != null) {
			Assert.assertEquals(expectedAccount, member.getAccount());
		}
	}

	@Test
	public void memberNotRetrievedByAccount() throws Exception {
		Members members = domainModel.getMembers();
		String expectedAccount = "boo";
		boolean containCheck = members.contains(expectedAccount);
		if (!containCheck) {
			Member member = members.retrieveByAccount(expectedAccount);
			Assert.assertNull(member);
		}
	}

	@Test
	public void selectApprovedMembers() throws Exception {
		Members members = domainModel.getMembers();
		Members selectedMembers = members.selectByApproved(true);

		selectedMembers.output("---Selected approved members---");
	}

	@Test
	public void selectAdministrators() throws Exception {
		Members members = domainModel.getMembers();
		Members selectedMembers = members
				.selectByRole(Member.SecurityRole.ADMIN);

		selectedMembers.output("---Selected administrators---");
	}

	@AfterClass
	public static void afterTests() {
		domainModel.save();
	}

}
