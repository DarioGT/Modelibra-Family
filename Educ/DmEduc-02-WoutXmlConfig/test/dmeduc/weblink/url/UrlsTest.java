package dmeduc.weblink.url;

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

public class UrlsTest {

	private static Urls urls;

	@BeforeClass
	public static void beforeTests() throws Exception {
		urls = DmEducTest.getSingleton().getDmEduc().getWebLink().getUrls();
	}

	@Before
	public void beforeTest() throws Exception {
		urls.getErrors().empty();
	}

	@Test
	public void urlsRequiredCreated() throws Exception {
		Url url01 = urls.createUrl("http://www.modelibra.org/");

		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertTrue(urls.getErrors().isEmpty());

		Url url02 = urls.createUrl("http://drdb.fsa.ulaval.ca/");

		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void urlsCreated() throws Exception {
		Url url01 = urls.createUrl("http://www.modelibra.org/", null);

		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertTrue(urls.getErrors().isEmpty());

		Url url02 = urls.createUrl("http://drdb.fsa.ulaval.ca/", "");

		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertTrue(urls.getErrors().isEmpty());

		Url url03 = urls.createUrl("http://drdb.fsa.ulaval.ca/dr/", null);

		assertNotNull(url03);
		assertTrue(urls.contain(url03));
		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void linkRequired() throws Exception {
		Url url = urls.createUrl(null);

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.required"));
	}

	@Test
	public void linkUnique() throws Exception {
		String link = "http://drdb.fsa.ulaval.ca/";
		urls.createUrl(link);
		Url notUniqueUrl = urls.createUrl(link);

		assertNull(notUniqueUrl);
		assertFalse(urls.contain(notUniqueUrl));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.id.unique"));
	}
	
	@Test
	public void linkType() throws Exception {
		String link = "dzenan.ridjanovic@fsa.ulaval.ca";
		Url url = urls.createUrl(link, "My web site as a professor at Université Laval.");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.validation"));
	}

	@Test
	public void linkLength() throws Exception {
		String link = "http://drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca";
		Url url = urls.createUrl(link, "My web site as a professor at Université Laval.");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.length"));
	}

	@Test
	public void descriptionNotRequired() throws Exception {
		Url url = urls.createUrl("http://www.modelibra.org/", null);

		assertNotNull(url);
		assertTrue(urls.contain(url));
		assertTrue(urls.getErrors().isEmpty());
		assertNull(urls.getErrors().getError("Url.link.required"));
	}

	@Test
	public void descriptionNotUnique() throws Exception {
		String description = "Modelibra";
		urls.createUrl("http://www.modelibra.org/", description);
		Url url = urls.createUrl("http://www.modelibra.com/", description);

		assertNotNull(url);
		assertTrue(urls.contain(url));
		assertTrue(urls.getErrors().isEmpty());
		assertNull(urls.getErrors().getError("Url.link.required"));
	}

	@After
	public void afterTest() throws Exception {
		for (Url url : urls.getList()) {
			urls.remove(url);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().getDmEduc().close();
	}

}
