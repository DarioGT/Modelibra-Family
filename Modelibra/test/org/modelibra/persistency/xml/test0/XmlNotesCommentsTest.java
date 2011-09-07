package org.modelibra.persistency.xml.test0;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.DomainConfig;
import org.modelibra.persistency.xml.PersistentXml;
import org.modelibra.persistency.xml.Xml;
import org.modelibra.persistency.xml.notes.Notes;
import org.modelibra.persistency.xml.notes.comment.Comment;
import org.modelibra.persistency.xml.notes.comment.Comments;

public class XmlNotesCommentsTest {

	private static Comments comments;

	@BeforeClass
	public static void beforeTests() throws Exception {
		XmlConfig xmlConfig = new XmlConfig();
		DomainConfig domainConfig = xmlConfig.getDomainConfig();
		Xml xml = new Xml(domainConfig);
		new PersistentXml(xml);
		Notes notes = xml.getNotes();
		comments = notes.getComments();
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

}
