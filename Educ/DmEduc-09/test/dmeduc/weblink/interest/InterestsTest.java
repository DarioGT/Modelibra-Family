package dmeduc.weblink.interest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.DmEducTest;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;

public class InterestsTest {

	public static final String DR_MEMBER = "dr";

	private static Members members;
	private static Member drMember;

	public static final String FRAMEWORK_CATEGORY = "Framework";
	public static final String DB_CATEGORY = "Database";

	private static Categories categories;
	private static Category frameworkCategory;
	private static Category dbCategory;

	private static Interests drMemberInterests;

	@BeforeClass
	public static void beforeInterests() throws Exception {
		members = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getMembers();
		drMember = members.createMember(DR_MEMBER, "dr", "Ridjanovic",
				"Dzenan", "dzenanr@gmail.com");

		categories = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getCategories();
		frameworkCategory = categories.createCategory(FRAMEWORK_CATEGORY);
		dbCategory = categories.createCategory(DB_CATEGORY);

		drMemberInterests = drMember.getInterests();
	}

	@Before
	public void beforeTest() throws Exception {
		drMemberInterests.getErrors().empty();
	}

	@Test
	public void interestsRequiredCreated() throws Exception {
		Interest interest01 = drMemberInterests.createInterest(drMember,
				frameworkCategory);
		Interest interest02 = drMemberInterests.createInterest(drMember,
				dbCategory);

		assertTrue(drMemberInterests.contain(interest01));
		// To be sure that interest01 is added to the framework category.
		assertTrue(frameworkCategory.getInterests().contain(interest01));

		assertTrue(drMemberInterests.contain(interest02));
		// To be sure that interest02 is added to the db category.
		assertTrue(dbCategory.getInterests().contain(interest02));

		assertTrue(drMemberInterests.getErrors().isEmpty());
	}

	@Test
	public void interestsCreated() throws Exception {
		Interest interest01 = drMemberInterests.createInterest(drMember,
				frameworkCategory,
				"Dzenan Ridjanovic has created several software frameworks.");
		Interest interest02 = drMemberInterests
				.createInterest(drMember, dbCategory,
						"Dzenan Ridjanovic has ben developing databases for more than thirty years.");

		assertTrue(drMemberInterests.contain(interest01));
		// To be sure that interest01 is added to the framework category.
		assertTrue(frameworkCategory.getInterests().contain(interest01));

		assertTrue(drMemberInterests.contain(interest02));
		// To be sure that interest02 is added to the db category.
		assertTrue(dbCategory.getInterests().contain(interest02));

		assertTrue(drMemberInterests.getErrors().isEmpty());
	}

	@Test
	public void interestUnique() throws Exception {
		drMemberInterests.createInterest(drMember, frameworkCategory,
				"Dzenan Ridjanovic has created several software frameworks.");
		Interest notUniqueInterest = drMemberInterests
				.createInterest(drMember, frameworkCategory,
						"The last framework created by Dzenan Ridjanovic is Modelibra.");

		assertFalse(drMemberInterests.contain(notUniqueInterest));
		assertNotNull(drMemberInterests.getErrors().getError(
				"Interest.id.unique"));
		// To be sure that not unique interest is not added to the framework
		// category.
		assertFalse(frameworkCategory.getInterests().contain(notUniqueInterest));
	}

	@Test
	public void interestsRemoved() throws Exception {
		Interest drMemberInterestInFrameworkCategory = drMemberInterests
				.createInterest(drMember, frameworkCategory);

		assertTrue(drMemberInterests
				.contain(drMemberInterestInFrameworkCategory));
		// To be sure that drMemberInterestInFrameworkCategory is added to the
		// framework category.
		assertTrue(frameworkCategory.getInterests().contain(
				drMemberInterestInFrameworkCategory));

		assertTrue(drMemberInterests.getErrors().isEmpty());

		drMemberInterests.remove(drMemberInterestInFrameworkCategory);

		assertFalse(drMemberInterests
				.contain(drMemberInterestInFrameworkCategory));
		// To be sure that drMemberInterestInFrameworkCategory is removed from
		// the framework category.
		assertFalse(frameworkCategory.getInterests().contain(
				drMemberInterestInFrameworkCategory));

		assertTrue(drMemberInterests.getErrors().isEmpty());
	}

	@After
	public void afterTest() throws Exception {
		for (Interest interest : drMemberInterests.getList()) {
			drMemberInterests.remove(interest);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		members.remove(drMember);
		categories.remove(frameworkCategory);
		categories.remove(dbCategory);

		DmEducTest.getSingleton().close();
	}

}