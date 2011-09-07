package dmeduc.weblink.url;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	public void urlsCreated() throws Exception {
		Url url01 = urls.createUrl("http://www.modelibra.org/");

		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertTrue(urls.getErrors().isEmpty());

		Url url02 = urls.createUrl("http://drdb.fsa.ulaval.ca/");

		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertTrue(urls.getErrors().isEmpty());
	}

}
