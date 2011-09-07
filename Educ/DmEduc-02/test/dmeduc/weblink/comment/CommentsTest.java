package dmeduc.weblink.comment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.DmEducTest;

public class CommentsTest {

	private static Comments comments;

	@BeforeClass
	public static void beforeTests() throws Exception {
		comments = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getComments();
	}

	@Before
	public void beforeTest() throws Exception {
		comments.getErrors().empty();
	}

	@Test
	public void commentsRequiredCreated() throws Exception {
		Comment comment01 = comments.createComment("Modelibra is magic!");
		Comment comment02 = comments
				.createComment("Modelibra is a domain model framework.");
		Comment comment03 = comments
				.createComment("Wicket is a web framework.");
		Comment comment04 = comments
				.createComment("Wicket is a small gate or door.");

		assertNotNull(comment01);
		assertTrue(comments.contain(comment01));
		assertNotNull(comment02);
		assertTrue(comments.contain(comment02));
		assertNotNull(comment03);
		assertTrue(comments.contain(comment03));
		assertNotNull(comment04);
		assertTrue(comments.contain(comment04));
		assertTrue(comments.getErrors().isEmpty());
	}

	@Test
	public void textRequired() throws Exception {
		Comment comment = comments.createComment(null);

		assertNull(comment);
		assertFalse(comments.contain(comment));
		assertFalse(comments.getErrors().isEmpty());
		assertNotNull(comments.getErrors().getError("Comment.text.required"));
	}

	@After
	public void afterTest() throws Exception {
		for (Comment comment : comments.getList()) {
			comments.remove(comment);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().close();
	}

}
