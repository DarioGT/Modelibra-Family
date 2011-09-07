package dmeduc.weblink.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.type.EasyDate;

import dmeduc.DmEducTest;

public class MembersTest {

	private static Members members;

	@BeforeClass
	public static void beforeMembers() throws Exception {
		members = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getMembers();
	}

	@Before
	public void beforeTest() throws Exception {
		members.getErrors().empty();
	}

	@Test
	public void membersRequiredCreated() throws Exception {
		Member member01 = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");
		Member member02 = members.createMember("admin", "admin", "Adminovic",
				"Admin", "admina@gmail.com");
		Member member03 = members.createMember("tim", "timr", "Ridjanovic",
				"Timur", "timurr@gmail.com");
		Member member04 = members.createMember("ogi", "ogi", "Ridjanovic",
				"Ogden", "ogdenr@gmail.com");

		assertTrue(members.contain(member01));
		assertTrue(members.contain(member02));
		assertTrue(members.contain(member03));
		assertTrue(members.contain(member04));
		assertTrue(members.getErrors().isEmpty());
	}

	@Test
	public void membersCreated() throws Exception {
		Member member01 = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com", false, "admin", new EasyDate(
						2007, 6, 16));
		Member member02 = members.createMember("admin", "admin", "Adminovic",
				"Admin", "admina@gmail.com", true, "admin", new EasyDate(2006,
						2, 12));
		Member member03 = members.createMember("tim", "timr", "Ridjanovic",
				"Timur", "timurr@gmail.com", true, "regular", new EasyDate(
						20087, 5, 4));
		Member member04 = members.createMember("ogi", "ogi", "Ridjanovic",
				"Ogden", "ogdenr@gmail.com", false, "regular", new EasyDate(
						2008, 8, 31));

		assertTrue(members.contain(member01));
		assertTrue(members.contain(member02));
		assertTrue(members.contain(member03));
		assertTrue(members.contain(member04));
		assertTrue(members.getErrors().isEmpty());
	}

	@Test
	public void codeRequired() throws Exception {
		Member member = members.createMember(null, "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		assertNull(member);
		assertFalse(members.contain(member));
		assertFalse(members.getErrors().isEmpty());
		assertNotNull(members.getErrors().getError("Member.code.required"));
	}

	@Test
	public void codeUnique() throws Exception {
		String code = "dr";
		members.createMember(code, "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com");
		Member notUniqueMember = members.createMember(code, "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		assertFalse(members.contain(notUniqueMember));
		assertNotNull(members.getErrors().getError("Member.id.unique"));
	}

	@Test
	public void codeLength() throws Exception {
		String code = "Dzenan.Ridjanovic";
		Member member = members.createMember(code, "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.code.length"));
	}

	@Test
	public void passwordRequired() throws Exception {
		Member member = members.createMember("dr", null, "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.password.required"));
	}

	@Test
	public void passwordLength() throws Exception {
		String password = "Dzenan.Ridjanovic.dr17";
		Member member = members.createMember("dr", password, "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.password.length"));
	}

	@Test
	public void lastNameRequired() throws Exception {
		Member member = members.createMember("dr", "dr", null, "Dzenan",
				"dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.lastName.required"));
	}

	@Test
	public void lastNameLength() throws Exception {
		String lastName = "Ridjanovic from Bosnia from Sarajevo leaving in Québec";
		Member member = members.createMember("dr", "dr", lastName, "Dzenan",
				"dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.lastName.length"));
	}

	@Test
	public void firstNameRequired() throws Exception {
		Member member = members.createMember("dr", "dr", "Ridjanovic", null,
				"dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.firstName.required"));
	}

	@Test
	public void firstNameLength() throws Exception {
		String firstName = "Dzenan from Bosnia from Sarajevo leaving in Québec";
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				firstName, "dzenanr@gmail.com");

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.firstName.length"));
	}

	@Test
	public void emailRequired() throws Exception {
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", null);

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.email.required"));
	}

	@Test
	public void emailType() throws Exception {
		String email = "dzenanr#modelibra.org";
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", email);

		assertFalse(members.contain(member));
		assertNotNull(members.getErrors().getError("Member.email.validation"));
	}

	@Test
	public void receiveEmailDefault() throws Exception {
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");
		boolean receiveEmail = member.isReceiveEmail();
		String defaultReceiveEmailString = members.getConceptConfig()
				.getPropertyConfig("receiveEmail").getDefaultValue();

		assertEquals(receiveEmail, new Boolean(defaultReceiveEmailString));
	}

	@Test
	public void roleDefault() throws Exception {
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");
		String role = member.getRole();
		String defaultRole = members.getConceptConfig().getPropertyConfig(
				"role").getDefaultValue();

		assertEquals(role, defaultRole);
	}

	@Test
	public void startDateDefault() throws Exception {
		Member member = members.createMember("dr", "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");
		Date startDate = member.getStartDate();
		Date today = new Date();
		EasyDate startEasyDate = new EasyDate(startDate);
		EasyDate todayEasyDate = new EasyDate(today);

		assertEquals(startEasyDate, todayEasyDate);
	}

	@Test
	public void emptyMemberCreation() throws Exception {
		String code = "";
		String password = "";
		String lastName = "";
		String firstName = "";
		String email = "";
		members.createMember(code, password, lastName, firstName, email);

		assertFalse(members.getErrors().isEmpty());
	}

	@Test
	public void emptyMemberWithEmailCreation() throws Exception {
		String code = "";
		String password = "";
		String lastName = "";
		String firstName = "";
		String email = "empty.member@empty.com";
		members.createMember(code, password, lastName, firstName, email);

		assertTrue(members.getErrors().isEmpty());
	}

	@Test
	public void profRoleUpdate() throws Exception {
		members.createMember("dr", "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com");
		Member dr = members.getMemberByCode("dr");
		Member memberCopy = dr.copy();
		memberCopy.setRole("prof");
		members.update(dr, memberCopy);

		assertNotNull(members.getErrors().getError("Member.role.validation"));
	}

	@Test
	public void adminRole() throws Exception {
		Member dr = members.createMember("dr", "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com");
		Member memberCopy = dr.copy();
		memberCopy.setRole("admin");
		members.update(dr, memberCopy);

		assertNull(members.getErrors().getError("Member.role.validation"));
	}

	@Test
	public void allMembersReceiveEmailUpdate() throws Exception {
		members.createMember("dr", "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com");
		members.createMember("admin", "admin", "Adminovic", "Admin",
				"admina@gmail.com");
		members.createMember("tim", "timr", "Ridjanovic", "Timur",
				"timurr@gmail.com");
		members.createMember("ogi", "ogi", "Ridjanovic", "Ogden",
				"ogdenr@gmail.com");
		for (Member member : members) {
			Member memberCopy = member.copy();
			memberCopy.setReceiveEmail(true);
			members.update(member, memberCopy);
		}

		for (Member member : members) {
			assertTrue(member.isReceiveEmail());
		}
	}

	@Test
	public void lastFirstNameAscendingOrder() throws Exception {
		members.createMember("dr", "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com");
		members.createMember("admin", "admin", "Adminovic", "Admin",
				"admina@gmail.com");
		members.createMember("tim", "timr", "Ridjanovic", "Timur",
				"timurr@gmail.com");
		members.createMember("ogi", "ogi", "Ridjanovic", "Ogden",
				"ogdenr@gmail.com");
		Members orderedMembers = members.getMembersOrderedByLastFirstName(true);

		assertEquals(members.size(), orderedMembers.size());
		assertNotSame(members, orderedMembers);

		for (Iterator<Member> iterator = orderedMembers.iterator(); iterator
				.hasNext();) {
			Member member = iterator.next();
			Member nextMember;
			if (iterator.hasNext()) {
				nextMember = iterator.next();
			} else {
				break;
			}
			String lastName = member.getLastName();
			String nextLastName = nextMember.getLastName();

			assertTrue(nextLastName.compareTo(lastName) >= 0);

			if (nextLastName.equals(lastName)) {
				String firstName = member.getFirstName();
				String nextFirstName = nextMember.getFirstName();

				assertTrue(nextFirstName.compareTo(firstName) >= 0);
			}
		}
	}

	@Test
	public void receiveEmailSelection() throws Exception {
		members.createMember("dr", "dr", "Ridjanovic", "Dzenan",
				"dzenanr@gmail.com", false, "admin", new EasyDate(2007, 6, 16));
		members.createMember("admin", "admin", "Adminovic", "Admin",
				"admina@gmail.com", true, "admin", new EasyDate(2006, 2, 12));
		members.createMember("tim", "timr", "Ridjanovic", "Timur",
				"timurr@gmail.com", true, "regular", new EasyDate(20087, 5, 4));
		members
				.createMember("ogi", "ogi", "Ridjanovic", "Ogden",
						"ogdenr@gmail.com", false, "regular", new EasyDate(
								2008, 8, 31));
		Members receiveEmailMembers = members.getReceiveEmailMembers();

		assertNotSame(members, receiveEmailMembers);

		for (Iterator<Member> iterator = receiveEmailMembers.iterator(); iterator
				.hasNext();) {
			Member member = iterator.next();

			assertTrue(member.isReceiveEmail());
		}

		int counter = 0;
		for (Member member : members) {
			if (member.isReceiveEmail())
				counter++;
		}

		assertEquals(receiveEmailMembers.size(), counter);
	}

	@After
	public void afterTest() throws Exception {
		for (Member member : members.getList()) {
			members.remove(member);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().close();
	}

}