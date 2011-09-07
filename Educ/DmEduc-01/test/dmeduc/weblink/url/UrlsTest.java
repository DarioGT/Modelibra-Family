package dmeduc.weblink.url;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

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
		Url url01 = urls.createUrl("http://www.modelibra.org/", "Framework");
		Url url02 = urls.createUrl("http://drdb.fsa.ulaval.ca/", "Personal");
		
		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void linkRequired() throws Exception {
		Url url = urls.createUrl(null, "Framework");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.required"));
	}

	@Test
	public void categoryRequired() throws Exception {
		Url url = urls.createUrl("http://www.modelibra.org/", null);

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.category.required"));
	}

	@Test
	public void linkUnique() throws Exception {
		String link = "http://drdb.fsa.ulaval.ca/";
		urls.createUrl(link, "Personal");
		Url notUniqueUrl = urls.createUrl(link, "Personal");

		assertNull(notUniqueUrl);
		assertFalse(urls.contain(notUniqueUrl));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.id.unique"));
	}

	@Test
	public void linkType() throws Exception {
		String link = "dzenan.ridjanovic@fsa.ulaval.ca";
		Url url = urls.createUrl(link, "Personal");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.validation"));
	}

	@Test
	public void linkLength() throws Exception {
		String link = "http://drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca";
		Url url = urls.createUrl(link, "Personal");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.length"));
	}

	@Test
	public void categoryLengthUpdate() throws Exception {
		Url url = urls.createUrl("http://wicket.apache.org/", "Framework");
		Url urlCopy = url.copy();
		String category = "Component based Web frameworks -- components as building blocks.";
		urlCopy.setCategory(category);
		urls.update(url, urlCopy);

		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.category.length"));
	}

	@Test
	public void creationDateDefault() throws Exception {
		Url url = urls.createUrl("http://drdb.fsa.ulaval.ca/", "Personal");
		Date creationDate = url.getCreationDate();

		assertNotNull(creationDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		calendar.setTime(creationDate);

		assertEquals(year, calendar.get(Calendar.YEAR));
		assertEquals(month, calendar.get(Calendar.MONTH));
		assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(minute, calendar.get(Calendar.MINUTE));
	}

	@Test
	public void approvedDefault() throws Exception {
		Url url = urls.createUrl("http://drdb.fsa.ulaval.ca/", "Personal");
		Boolean approvedObject = url.getApproved();
		boolean approved = url.isApproved();

		assertNotNull(approvedObject);
		assertFalse(approved);
	}

	@Test
	public void urlEquality() throws Exception {
		Url url = urls.createUrl("http://wicket.apache.org/", "Framework");
		Url urlCopy = url.copy();

		assertNotNull(urlCopy);
		assertEquals(url, urlCopy);
		assertNotSame(url, urlCopy);
	}

	@Test
	public void urlCopy() throws Exception {
		Url url = urls.createUrl("http://wicket.apache.org/", "Framework");
		assertNotNull(url);
		assertTrue(urls.getErrors().isEmpty());

		Url urlCopy = url.copy();
		assertNotNull(urlCopy);
		assertEquals(url, urlCopy);
		assertNotSame(url, urlCopy);
		assertEquals(url.getLink(), urlCopy.getLink());
		assertEquals(url.getCategory(), urlCopy.getCategory());
	}
	
	@Test
	public void urlUpdate() throws Exception {
		Url url = urls.createUrl("http://wicket.apache.org/", "Framework");
		Url urlCopy = url.copy();
		urlCopy.setDescription("Component based Web framework.");
		urlCopy.setUpdateDate(new Date());

		assertNotNull(urlCopy);
		assertNotSame(url, urlCopy);
		assertEquals(url, urlCopy);
		assertTrue(url.equals(urlCopy));
		assertTrue(url.equalOid(urlCopy));
		assertFalse(url.equalProperties(urlCopy));

		urls.update(url, urlCopy);

		assertTrue(urls.getErrors().isEmpty());

		Url updatedUrl = urls.getUrl(urlCopy.getOid());

		assertNotNull(updatedUrl);
		assertNotSame(url, urlCopy);
		assertNotSame(updatedUrl, urlCopy);
		assertSame(url, updatedUrl);
		assertEquals(url, urlCopy);
		assertEquals(updatedUrl, urlCopy);
		assertEquals(url, updatedUrl);
	}

	@After
	public void afterTest() throws Exception {
		for (Url url : urls.getList()) {
			urls.remove(url);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().close();
	}

}
