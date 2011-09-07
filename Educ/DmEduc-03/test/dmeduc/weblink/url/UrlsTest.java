package dmeduc.weblink.url;

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
		Url url01 = urls.createUrl("Dzenan Ridjanovic",
				"http://drdb.fsa.ulaval.ca/", "Personal");
		Url url02 = urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Framework");
		Url url03 = urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Framework");

		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertNotNull(url03);
		assertTrue(urls.contain(url03));
		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void urlsCreated() throws Exception {
		Url url01 = urls.createUrl("Dzenan Ridjanovic",
				"http://drdb.fsa.ulaval.ca/", null, new EasyDate(2007, 4, 3),
				false, "Personal");
		Url url02 = urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		Url url03 = urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");

		assertNotNull(url01);
		assertTrue(urls.contain(url01));
		assertNotNull(url02);
		assertTrue(urls.contain(url02));
		assertNotNull(url03);
		assertTrue(urls.contain(url03));
		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void nameRequired() throws Exception {
		Url url = urls
				.createUrl(null, "http://www.modelibra.org/", "Framework");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.name.required"));
	}

	@Test
	public void linkRequired() throws Exception {
		Url url = urls.createUrl("Modelibra", null, "Framework");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.required"));
	}

	@Test
	public void categoryRequired() throws Exception {
		Url url = urls
				.createUrl("Modelibra", "http://www.modelibra.org/", null);

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.category.required"));
	}

	@Test
	public void nameUnique() throws Exception {
		String name = "Modelibra";
		String link = "http://www.modelibra.org/";
		String category = "Framework";
		urls.createUrl(name, link, category);
		Url notUniqueUrl = urls.createUrl(name, link, category);

		assertNull(notUniqueUrl);
		assertFalse(urls.contain(notUniqueUrl));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.id.unique"));
	}

	@Test
	public void linkType() throws Exception {
		String link = "dzenan.ridjanovic@fsa.ulaval.ca";
		Url url = urls.createUrl("Dzenan Ridjanovic", link, "Personal");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.validation"));
	}

	@Test
	public void linkLength() throws Exception {
		String link = "http://drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca/drdb.fsa.ulaval.ca";
		Url url = urls.createUrl("Dzenan Ridjanovic", link, "Personal");

		assertNull(url);
		assertFalse(urls.contain(url));
		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.link.length"));
	}

	@Test
	public void categoryLengthUpdate() throws Exception {
		Url url = urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Framework");
		Url urlCopy = url.copy();
		String category = "Component based Web frameworks -- components as building blocks.";
		urlCopy.setCategory(category);
		urls.update(url, urlCopy);

		assertFalse(urls.getErrors().isEmpty());
		assertNotNull(urls.getErrors().getError("Url.category.length"));
	}

	@Test
	public void creationDateDefault() throws Exception {
		Url url = urls.createUrl("Dzenan Ridjanovic",
				"http://drdb.fsa.ulaval.ca/", "Personal");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		calendar.setTime(url.getCreationDate());

		assertEquals(year, calendar.get(Calendar.YEAR));
		assertEquals(month, calendar.get(Calendar.MONTH));
		assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(minute, calendar.get(Calendar.MINUTE));
	}

	@Test
	public void approvedDefault() throws Exception {
		Url url = urls.createUrl("Dzenan Ridjanovic",
				"http://drdb.fsa.ulaval.ca/", "Personal");
		Boolean approvedObject = url.getApproved();
		boolean approved = url.isApproved();

		assertNotNull(approvedObject);
		assertFalse(approved);
	}

	@Test
	public void urlEquality() throws Exception {
		Url url = urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Framework");
		Url urlCopy = url.copy();

		assertEquals(url, urlCopy);
		assertNotSame(url, urlCopy);
	}

	@Test
	public void urlUpdate() throws Exception {
		Url url = urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Framework");
		Url urlCopy = url.copy();
		urlCopy.setDescription("Component based Web framework.");
		urlCopy.setUpdateDate(new Date());

		assertTrue(url.equals(urlCopy));
		assertTrue(url.equalOid(urlCopy));
		assertFalse(url.equalProperties(urlCopy));

		urls.update(url, urlCopy);

		assertTrue(urls.getErrors().isEmpty());
	}

	@Test
	public void categoryOrder() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls orderedUrls = urls.getUrlsOrderedByCategory(true);

		assertFalse(orderedUrls.isEmpty());
		assertEquals(urls.size(), orderedUrls.size());
		assertNotSame(urls, orderedUrls);

		for (Iterator<Url> iterator = orderedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();
			Url nextUrl;
			if (iterator.hasNext()) {
				nextUrl = iterator.next();
			} else {
				break;
			}
			String category = url.getCategory();
			String nextCategory = nextUrl.getCategory();

			assertTrue(category.compareTo(nextCategory) < 1);
		}

	}

	@Test
	public void categoryDescendingOrder() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls orderedUrls = urls.getUrlsOrderedByCategory(false);

		assertFalse(orderedUrls.isEmpty());
		assertEquals(urls.size(), orderedUrls.size());
		assertNotSame(urls, orderedUrls);

		for (Iterator<Url> iterator = orderedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();
			Url nextUrl;
			if (iterator.hasNext()) {
				nextUrl = iterator.next();
			} else {
				break;
			}
			String category = url.getCategory();
			String nextCategory = nextUrl.getCategory();

			assertTrue(category.compareTo(nextCategory) >= 0);
		}

	}

	@Test
	public void creationDateOrder() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls orderedUrls = urls.getUrlsOrderedByCreationDate(true);

		assertFalse(orderedUrls.isEmpty());
		assertEquals(urls.size(), orderedUrls.size());
		assertNotSame(urls, orderedUrls);

		for (Iterator<Url> iterator = orderedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();
			Url nextUrl;
			if (iterator.hasNext()) {
				nextUrl = iterator.next();
			} else {
				break;
			}
			Date commentDate = url.getCreationDate();
			Date nextCommentDate = nextUrl.getCreationDate();

			assertTrue(commentDate.before(nextCommentDate));
		}
	}

	@Test
	public void approvedSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls approvedUrls = urls.getApprovedUrls();

		assertFalse(approvedUrls.isEmpty());
		assertNotSame(urls, approvedUrls);

		for (Iterator<Url> iterator = approvedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.isApproved());
		}

		int counter = 0;
		for (Url url : urls) {
			if (url.isApproved())
				counter++;
		}

		assertEquals(approvedUrls.size(), counter);
	}

	@Test
	public void notApprovedSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls notApprovedUrls = urls.getNotApprovedUrls();

		assertFalse(notApprovedUrls.isEmpty());
		assertNotSame(urls, notApprovedUrls);

		for (Iterator<Url> iterator = notApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(!url.isApproved());
		}

		int counter = 0;
		for (Url url : urls) {
			if (!url.isApproved())
				counter++;
		}

		assertEquals(notApprovedUrls.size(), counter);
	}

	@Test
	public void recentlyCreatedSelection() throws Exception {
		EasyDate recentEasyDate = new EasyDate(2008, 6, 1);
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls recentlyCreatedUrls = urls.getRecentlyCreatedUrls(recentEasyDate
				.getDate());

		assertFalse(recentlyCreatedUrls.isEmpty());
		assertNotSame(urls, recentlyCreatedUrls);

		for (Iterator<Url> iterator = recentlyCreatedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getCreationDate().after(recentEasyDate.getDate()));
		}

		int counter = 0;
		for (Url url : urls) {
			if (url.getCreationDate().after(recentEasyDate.getDate()))
				counter++;
		}

		assertEquals(recentlyCreatedUrls.size(), counter);
	}

	@Test
	public void firstCreationDateRetrieval() throws Exception {
		EasyDate oldestEasyDate = new EasyDate(2007, 4, 3);
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");

		Date urlFirstCreationDate = urls.getFirstCreationDate();
		EasyDate urlFirstCreationEasyDate = new EasyDate(urlFirstCreationDate);

		assertNotNull(urlFirstCreationDate);
		assertEquals(urlFirstCreationEasyDate, oldestEasyDate);
	}

	@Test
	public void categorySelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String category = "Framework";
		Urls categoryUrls = urls.getCategoryUrls(category);

		assertFalse(categoryUrls.isEmpty());
		assertNotSame(urls, categoryUrls);

		for (Iterator<Url> iterator = categoryUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getCategory().equals(category));
		}

		int counter = 0;
		for (Url url : urls) {
			if (url.getCategory().equals(category))
				counter++;
		}

		assertEquals(categoryUrls.size(), counter);
	}

	@Test
	public void descriptionKeywordSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String keyword = "model";
		Urls keywordUrls = urls.getKeywordUrls(keyword);

		assertFalse(keywordUrls.isEmpty());
		assertNotSame(urls, keywordUrls);

		for (Iterator<Url> iterator = keywordUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword));
		}

		int counter = 0;
		for (Url url : urls) {
			String description = url.getDescription();
			if (description != null && description.contains(keyword))
				counter++;
		}

		assertEquals(keywordUrls.size(), counter);
	}

	@Test
	public void descriptionSomeKeywordSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String keyword1 = "component";
		String keyword2 = "model";
		String[] keywordsArray = { keyword1, keyword2 };
		Urls someKeywordsUrls = urls.getSomeKeywordUrls(keywordsArray);

		assertFalse(someKeywordsUrls.isEmpty());
		assertNotSame(urls, someKeywordsUrls);

		for (Iterator<Url> iterator = someKeywordsUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword1)
					|| url.getDescription().contains(keyword2));
		}

		int counter = 0;
		for (Url url : urls) {
			String description = url.getDescription();
			if (description != null) {
				if (description.contains(keyword1)
						|| description.contains(keyword2)) {
					counter++;
				}
			}
		}

		assertEquals(someKeywordsUrls.size(), counter);
	}

	@Test
	public void descriptionAllKeywordSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String keyword1 = "Domain";
		String keyword2 = "model";
		String[] keywordsArray = { keyword1, keyword2 };
		Urls allKeywordsUrls = urls.getAllKeywordUrls(keywordsArray);

		assertFalse(allKeywordsUrls.isEmpty());
		assertNotSame(urls, allKeywordsUrls);

		for (Iterator<Url> iterator = allKeywordsUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword1)
					&& url.getDescription().contains(keyword2));
		}

		int counter = 0;
		for (Url url : urls) {
			String description = url.getDescription();
			if (description != null) {
				if (description.contains(keyword1)
						&& description.contains(keyword2)) {
					counter++;
				}
			}
		}

		assertEquals(allKeywordsUrls.size(), counter);
	}

	@Test
	public void recentlyCreatedAndApprovedSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		EasyDate recentEasyDate = new EasyDate(2008, 6, 1);
		Urls recentlyApprovedUrls = urls.getRecentlyCreatedUrls(
				recentEasyDate.getDate()).getApprovedUrls();

		assertFalse(recentlyApprovedUrls.isEmpty());
		assertNotSame(urls, recentlyApprovedUrls);

		for (Iterator<Url> iterator = recentlyApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getCreationDate().after(recentEasyDate.getDate())
					&& url.isApproved());
		}

		int counter = 0;
		for (Url url : urls) {
			if (url.getCreationDate().after(recentEasyDate.getDate())
					&& url.isApproved())
				counter++;
		}

		assertEquals(recentlyApprovedUrls.size(), counter);
	}

	@Test
	public void todayCreatedSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls todayUrls = urls.getTodayUrls();

		assertNotNull(todayUrls);
		assertNotSame(urls, todayUrls);

		EasyDate today = new EasyDate(new Date());

		for (Iterator<Url> iterator = todayUrls.iterator(); iterator.hasNext();) {
			Url url = iterator.next();

			assertTrue(today.equals(url.getCreationDate()));
		}

		int counter = 0;
		for (Url url : urls) {
			if (today.equals(url.getCreationDate()))
				counter++;
		}

		assertEquals(todayUrls.size(), counter);
	}

	@Test
	public void categoryUrlsSelectionUrlUpdate() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");

		String category = "Framework";
		Urls categoryUrls = urls.getCategoryUrls(category);

		assertFalse(categoryUrls.isEmpty());

		String name = "Modelibra";
		Url url = categoryUrls.getUrlByName(name);
		Url urlCopy = url.copy();
		String description = "Modelibra is a domain model framework. A domain model is a model of specific domain classes that describe the core data and their behavior. For prototypes and small applications, the model objects are saved in XML files.";
		urlCopy.setDescription(description);
		categoryUrls.update(url, urlCopy);

		assertTrue(categoryUrls.getErrors().isEmpty());

		Url updatedUrl = categoryUrls.getUrlByName(name);

		assertTrue(updatedUrl.getDescription().equals(description));
	}

	@Test
	public void nonExistantCategorySelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String category = "ABC";
		Urls categoryUrls = urls.getCategoryUrls(category);

		assertTrue(categoryUrls.isEmpty());
	}

	@Test
	public void categoryUrlsSelectionInvalidUrlCreation() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		String category = "Framework";
		Urls categoryUrls = urls.getCategoryUrls(category);

		assertFalse(categoryUrls.isEmpty());

		Url url = categoryUrls.createUrl("Struts", "struts.apache.org",
				category);

		assertNull(url);
	}

	@Test
	public void categoryUrlsSelectionUrlCreation() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");

		String category = "Framework";
		Urls categoryUrls = urls.getCategoryUrls(category);

		assertFalse(categoryUrls.isEmpty());

		Url url = categoryUrls.createUrl("Struts", "http://struts.apache.org/",
				category);

		assertNotNull(url);
	}

	@Test
	public void todayCreatedAndApprovedSelection() throws Exception {
		urls.createUrl("Dzenan Ridjanovic", "http://drdb.fsa.ulaval.ca/", null,
				new EasyDate(2007, 4, 3), false, "Personal");
		urls.createUrl("Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true,
				"Framework");
		urls.createUrl("Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true,
				"Framework");
		Urls todayApprovedUrls = urls.getUrlsCreatedAndApprovedToday();

		assertNotNull(todayApprovedUrls);
		assertNotSame(urls, todayApprovedUrls);

		EasyDate today = new EasyDate(new Date());

		for (Iterator<Url> iterator = todayApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(today.equals(url.getCreationDate()) && url.isApproved());
		}

		int counter = 0;
		for (Url url : urls) {
			if (today.equals(url.getCreationDate()) && url.isApproved())
				counter++;
		}

		assertEquals(todayApprovedUrls.size(), counter);

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
