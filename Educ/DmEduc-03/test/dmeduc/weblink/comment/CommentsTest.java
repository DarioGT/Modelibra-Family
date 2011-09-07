package dmeduc.weblink.comment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.type.EasyDate;

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
		Comment comment01 = comments.createComment("Modelibra is magic.");
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
	public void commentsCreated() throws Exception {
		Comment comment01 = comments.createComment("Modelibra is magic.",
				new EasyDate(2008, 1, 30));
		Comment comment02 = comments.createComment(
				"Modelibra is a domain model framework.", new EasyDate(2008, 1,
						30));
		Comment comment03 = comments.createComment(
				"Wicket is a web framework.", new EasyDate(2008, 8, 25));
		Comment comment04 = comments.createComment(
				"Wicket is a small gate or door.", new EasyDate(2008, 4, 3));

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

	@Test
	public void creationDateDefault() throws Exception {
		Comment comment = comments.createComment("Wicket is a web framework.");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		calendar.setTime(comment.getCreationDate());

		assertEquals(year, calendar.get(Calendar.YEAR));
		assertEquals(month, calendar.get(Calendar.MONTH));
		assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(minute, calendar.get(Calendar.MINUTE));

		Date creationDate = comment.getCreationDate();
		Date todayDate = new Date();

		assertNotNull(todayDate);
		// Sometime is true, sometime is false (Date is really date and time).
		// assertFalse(creationDate.equals(todayDate));

		EasyDate creationEasyDate = new EasyDate(creationDate);
		EasyDate todayEasyDate = new EasyDate(new Date());

		assertNotNull(creationEasyDate);
		assertNotNull(todayEasyDate);
		assertEquals(creationEasyDate, todayEasyDate);
	}

	@Test
	public void creationDateDescendingOrder() throws Exception {
		comments
				.createComment("Modelibra is magic.", new EasyDate(2008, 1, 30));
		comments.createComment("Modelibra is a domain model framework.",
				new EasyDate(2008, 1, 30));
		comments.createComment("Wicket is a web framework.", new EasyDate(2008,
				8, 25));
		comments.createComment("Wicket is a small gate or door.", new EasyDate(
				2008, 4, 3));
		Comments orderedComments = comments
				.getCommentsOrderedByCreationDate(false);

		assertFalse(orderedComments.isEmpty());
		assertEquals(comments.size(), orderedComments.size());
		assertNotSame(comments, orderedComments);

		for (Iterator<Comment> iterator = orderedComments.iterator(); iterator
				.hasNext();) {
			Comment comment = iterator.next();
			Comment nextComment;
			if (iterator.hasNext()) {
				nextComment = iterator.next();
			} else {
				break;
			}
			Date commentDate = comment.getCreationDate();
			Date nextCommentDate = nextComment.getCreationDate();

			assertTrue(!commentDate.before(nextCommentDate));
		}

	}

	@Test
	public void textKeywordSelection() throws Exception {
		comments
				.createComment("Modelibra is magic.", new EasyDate(2008, 1, 30));
		comments.createComment("Modelibra is a domain model framework.",
				new EasyDate(2008, 1, 30));
		comments.createComment("Wicket is a web framework.", new EasyDate(2008,
				8, 25));
		comments.createComment("Wicket is a small gate or door.", new EasyDate(
				2008, 4, 3));
		String keyword = "magic";
		Comments keywordComments = comments.getKeywordComments(keyword);

		assertFalse(keywordComments.isEmpty());
		assertTrue(comments.getErrors().isEmpty());
		assertNotSame(comments, keywordComments);

		for (Iterator<Comment> iterator = keywordComments.iterator(); iterator
				.hasNext();) {
			Comment comment = iterator.next();

			assertTrue(comment.getText().contains(keyword));
		}

		int counter = 0;
		for (Comment comment : comments) {
			if (comment.getText().contains(keyword))
				counter++;
		}

		assertEquals(keywordComments.size(), counter);
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
