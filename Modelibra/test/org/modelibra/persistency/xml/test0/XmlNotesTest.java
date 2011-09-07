package org.modelibra.persistency.xml.test0;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.DomainConfig;
import org.modelibra.persistency.xml.PersistentXml;
import org.modelibra.persistency.xml.Xml;
import org.modelibra.persistency.xml.notes.Notes;
import org.modelibra.persistency.xml.notes.comment.Comments;

public class XmlNotesTest {

	private static Xml xmlDomain;

	@BeforeClass
	public static void beforeTests() throws Exception {
		XmlConfig xmlConfig = new XmlConfig();
		DomainConfig domainConfig = xmlConfig.getDomainConfig();
		xmlDomain = new Xml(domainConfig);
		new PersistentXml(xmlDomain);
	}

	@Test
	public void notesModel() throws Exception {
		Notes notesModel = xmlDomain.getNotes();
		assertNotNull(notesModel);
	}

	@Test
	public void commentsEntry() throws Exception {
		Notes notesModel = xmlDomain.getNotes();
		Comments comments = notesModel.getComments();
		assertNotNull(comments);
	}

}
