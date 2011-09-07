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
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;

public class UrlsTest {

	public static final String FRAMEWORK_CATEGORY = "Framework";

	private static Categories categories;
	private static Category frameworkCategory;

	private static Urls frameworkCategoryUrls;

	@BeforeClass
	public static void beforeTests() throws Exception {
		categories = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getCategories();
		frameworkCategory = categories.createCategory(FRAMEWORK_CATEGORY);
		frameworkCategoryUrls = frameworkCategory.getUrls();
	}

	@Before
	public void beforeTest() throws Exception {
		frameworkCategoryUrls.getErrors().empty();
	}

	@Test
	public void frameworkCategoryUrlsRequiredCreated() throws Exception {
		Url url01 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Struts", "http://struts.apache.org/");
		Url url02 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Wicket", "http://wicket.apache.org/");
		Url url03 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", "http://www.modelibra.org/");

		assertNotNull(url01);
		assertTrue(frameworkCategoryUrls.contain(url01));
		assertNotNull(url02);
		assertTrue(frameworkCategoryUrls.contain(url02));
		assertNotNull(url03);
		assertTrue(frameworkCategoryUrls.contain(url03));
		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void frameworkCategoryUrlsCreated() throws Exception {
		Url url01 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Struts", "http://struts.apache.org/", null, new EasyDate(2007,
						4, 3), false);
		Url url02 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Wicket", "http://wicket.apache.org/",
				"Web component framework.", new EasyDate(2008, 6, 22), true);
		Url url03 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", "http://www.modelibra.org/",
				"Domain model framework.", new EasyDate(2008, 3, 27), true);

		assertNotNull(url01);
		assertTrue(frameworkCategoryUrls.contain(url01));
		assertNotNull(url02);
		assertTrue(frameworkCategoryUrls.contain(url02));
		assertNotNull(url03);
		assertTrue(frameworkCategoryUrls.contain(url03));
		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void nameRequired() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, null,
				"http://www.modelibra.org/");

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.name.required"));
	}

	@Test
	public void linkRequired() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", null);

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.required"));
	}

	@Test
	public void nameCategoryUnique() throws Exception {
		String name = "Modelibra";
		String link = "http://www.modelibra.org/";
		frameworkCategoryUrls.createUrl(frameworkCategory, name, link);
		Url notUniqueUrl = frameworkCategoryUrls.createUrl(frameworkCategory,
				name, link);

		assertNull(notUniqueUrl);
		assertFalse(frameworkCategoryUrls.contain(notUniqueUrl));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.id.unique"));
	}

	@Test
	public void linkType() throws Exception {
		String link = "dzenanr@modelibra.org";
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", link);

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.validation"));
	}

	@Test
	public void linkLength() throws Exception {
		String link = "http://www.modelibra.org/framework/learning/documentation/introduction/selection/keyword/example/short/";
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", link);

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.length"));
	}

	@Test
	public void creationDateDefault() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", "http://www.modelibra.org/");
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
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"Modelibra", "http://www.modelibra.org/");
		Boolean approvedObject = url.getApproved();
		boolean approved = url.isApproved();

		assertNotNull(approvedObject);
		assertFalse(approved);
	}

	@Test
	public void urlEquality() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/");
		Url urlCopy = url.copy();

		assertEquals(url, urlCopy);
		assertNotSame(url, urlCopy);
	}

	@Test
	public void urlUpdate() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/");
		Url urlCopy = url.copy();
		urlCopy.setDescription("Component based Web framework.");
		urlCopy.setUpdateDate(new Date());

		assertTrue(url.equals(urlCopy));
		assertTrue(url.equalOid(urlCopy));
		assertFalse(url.equalProperties(urlCopy));

		frameworkCategoryUrls.update(url, urlCopy);

		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void nameOrder() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls orderedUrls = frameworkCategoryUrls.getUrlsOrderedByName();

		assertFalse(orderedUrls.isEmpty());
		assertEquals(frameworkCategoryUrls.size(), orderedUrls.size());
		assertNotSame(frameworkCategoryUrls, orderedUrls);

		for (Iterator<Url> iterator = orderedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();
			Url nextUrl;
			if (iterator.hasNext()) {
				nextUrl = iterator.next();
			} else {
				break;
			}
			String name = url.getName();
			String nextName = nextUrl.getName();

			assertTrue(name.compareTo(nextName) < 0);
		}
	}

	@Test
	public void creationDateAscendingOrder() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls orderedUrls = frameworkCategoryUrls
				.getUrlsOrderedByCreationDate(true);

		assertFalse(orderedUrls.isEmpty());
		assertEquals(frameworkCategoryUrls.size(), orderedUrls.size());
		assertNotSame(frameworkCategoryUrls, orderedUrls);

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

			EasyDate commentEasyDate = new EasyDate(commentDate);
			EasyDate nextCommentEasyDate = new EasyDate(nextCommentDate);

			assertTrue(commentEasyDate.isLessThan(nextCommentEasyDate)
					|| commentEasyDate.isEqualTo(nextCommentEasyDate));
		}
	}

	@Test
	public void creationDateDescendingOrder() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls orderedUrls = frameworkCategoryUrls
				.getUrlsOrderedByCreationDate(false);

		assertFalse(orderedUrls.isEmpty());
		assertEquals(frameworkCategoryUrls.size(), orderedUrls.size());
		assertNotSame(frameworkCategoryUrls, orderedUrls);

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

			assertTrue(!commentDate.before(nextCommentDate));

			EasyDate commentEasyDate = new EasyDate(commentDate);
			EasyDate nextCommentEasyDate = new EasyDate(nextCommentDate);

			assertFalse(commentEasyDate.isLessThan(nextCommentEasyDate));
		}
	}

	@Test
	public void approvedSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls approvedUrls = frameworkCategoryUrls.getApprovedUrls();

		assertFalse(approvedUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, approvedUrls);

		for (Iterator<Url> iterator = approvedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.isApproved());
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (url.isApproved())
				counter++;
		}

		assertEquals(approvedUrls.size(), counter);
	}

	@Test
	public void notApprovedSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls notApprovedUrls = frameworkCategoryUrls.getNotApprovedUrls();

		assertFalse(notApprovedUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, notApprovedUrls);

		for (Iterator<Url> iterator = notApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(!url.isApproved());
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (!url.isApproved())
				counter++;
		}

		assertEquals(notApprovedUrls.size(), counter);
	}

	@Test
	public void recentlyCreatedSelection() throws Exception {
		EasyDate recentEasyDate = new EasyDate(2008, 6, 1);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls recentlyCreatedUrls = frameworkCategoryUrls
				.getRecentlyCreatedUrls(recentEasyDate.getDate());

		assertFalse(recentlyCreatedUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, recentlyCreatedUrls);

		for (Iterator<Url> iterator = recentlyCreatedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getCreationDate().after(recentEasyDate.getDate()));
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (url.getCreationDate().after(recentEasyDate.getDate()))
				counter++;
		}

		assertEquals(recentlyCreatedUrls.size(), counter);
	}

	@Test
	public void firstCreationDateRetrieval() throws Exception {
		EasyDate oldestEasyDate = new EasyDate(2007, 4, 3);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, oldestEasyDate, false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Date urlFirstCreationDate = frameworkCategoryUrls
				.getFirstCreationDate();
		EasyDate urlFirstCreationEasyDate = new EasyDate(urlFirstCreationDate);

		assertNotNull(urlFirstCreationDate);
		assertEquals(urlFirstCreationEasyDate, oldestEasyDate);
	}

	@Test
	public void descriptionKeywordSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		String keyword = "model";
		Urls keywordUrls = frameworkCategoryUrls.getKeywordUrls(keyword);

		assertFalse(keywordUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, keywordUrls);

		for (Iterator<Url> iterator = keywordUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword));
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			String description = url.getDescription();
			if (description != null && description.contains(keyword))
				counter++;
		}

		assertEquals(keywordUrls.size(), counter);
	}

	@Test
	public void descriptionSomeKeywordSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		String keyword1 = "component";
		String keyword2 = "model";
		String[] keywordsArray = { keyword1, keyword2 };
		Urls someKeywordsUrls = frameworkCategoryUrls
				.getSomeKeywordUrls(keywordsArray);

		assertFalse(someKeywordsUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, someKeywordsUrls);

		for (Iterator<Url> iterator = someKeywordsUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword1)
					|| url.getDescription().contains(keyword2));
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
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
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		String keyword1 = "Domain";
		String keyword2 = "model";
		String[] keywordsArray = { keyword1, keyword2 };
		Urls allKeywordsUrls = frameworkCategoryUrls
				.getAllKeywordUrls(keywordsArray);

		assertFalse(allKeywordsUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, allKeywordsUrls);

		for (Iterator<Url> iterator = allKeywordsUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getDescription().contains(keyword1)
					&& url.getDescription().contains(keyword2));
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
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
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		EasyDate recentEasyDate = new EasyDate(2008, 6, 1);
		Urls recentlyApprovedUrls = frameworkCategoryUrls
				.getRecentlyCreatedUrls(recentEasyDate.getDate())
				.getApprovedUrls();

		assertFalse(recentlyApprovedUrls.isEmpty());
		assertNotSame(frameworkCategoryUrls, recentlyApprovedUrls);

		for (Iterator<Url> iterator = recentlyApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(url.getCreationDate().after(recentEasyDate.getDate())
					&& url.isApproved());
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (url.getCreationDate().after(recentEasyDate.getDate())
					&& url.isApproved())
				counter++;
		}

		assertEquals(recentlyApprovedUrls.size(), counter);
	}

	@Test
	public void todayCreatedSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls todayUrls = frameworkCategoryUrls.getTodayUrls();

		assertNotNull(todayUrls);
		assertNotSame(frameworkCategoryUrls, todayUrls);

		EasyDate today = new EasyDate(new Date());

		for (Iterator<Url> iterator = todayUrls.iterator(); iterator.hasNext();) {
			Url url = iterator.next();

			assertTrue(today.equals(url.getCreationDate()));
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (today.equals(url.getCreationDate()))
				counter++;
		}

		assertEquals(todayUrls.size(), counter);
	}

	@Test
	public void modelibraDescriptionUpdate() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		String name = "Modelibra";
		Url url = frameworkCategoryUrls.getUrlByName(name);
		Url urlCopy = url.copy();
		String description = "Modelibra is a domain model framework. A domain model is a model of specific domain classes that describe the core data and their behavior. For prototypes and small applications, the model objects are saved in XML files.";
		urlCopy.setDescription(description);
		frameworkCategoryUrls.update(url, urlCopy);

		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void additionalUrlCreation() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, "jQuery",
				"http://jquery.com/");

		assertNotNull(url);
		assertTrue(frameworkCategoryUrls.contain(url));
		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void todayCreatedAndApprovedSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Urls todayApprovedUrls = frameworkCategoryUrls
				.getUrlsCreatedAndApprovedToday();

		assertNotNull(todayApprovedUrls);
		assertNotSame(frameworkCategoryUrls, todayApprovedUrls);

		EasyDate today = new EasyDate(new Date());

		for (Iterator<Url> iterator = todayApprovedUrls.iterator(); iterator
				.hasNext();) {
			Url url = iterator.next();

			assertTrue(today.equals(url.getCreationDate()) && url.isApproved());
		}

		int counter = 0;
		for (Url url : frameworkCategoryUrls) {
			if (today.equals(url.getCreationDate()) && url.isApproved())
				counter++;
		}

		assertEquals(todayApprovedUrls.size(), counter);
	}

	@Test
	public void allUrls() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory, "Struts",
				"http://struts.apache.org/", null, new EasyDate(2007, 4, 3),
				false);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Wicket",
				"http://wicket.apache.org/", "Web component framework.",
				new EasyDate(2008, 6, 22), true);
		frameworkCategoryUrls.createUrl(frameworkCategory, "Modelibra",
				"http://www.modelibra.org/", "Domain model framework.",
				new EasyDate(2008, 3, 27), true);
		Category personalCategory = categories.createCategory("Personal");

		assertNotNull(personalCategory);
		assertTrue(categories.contain(personalCategory));
		assertTrue(categories.getErrors().isEmpty());

		Urls personalCategoryUrls = personalCategory.getUrls();
		Url personalCategoryUrl = personalCategoryUrls.createUrl(
				personalCategory, "Dzenan Ridjanovic",
				"http://drdb.fsa.ulaval/");

		assertNotNull(personalCategoryUrl);
		assertTrue(personalCategoryUrls.contain(personalCategoryUrl));
		assertTrue(personalCategoryUrls.getErrors().isEmpty());

		WebLink webLink = DmEducTest.getSingleton().getDmEduc().getWebLink();
		Urls allUrls = webLink.getUrls();

		assertNotNull(allUrls);
		assertEquals(allUrls.size(), frameworkCategoryUrls.size()
				+ personalCategoryUrls.size());

		categories.remove(personalCategory);

		assertFalse(categories.contain(personalCategory));
	}

	@After
	public void afterTest() throws Exception {
		for (Url url : frameworkCategoryUrls.getList()) {
			frameworkCategoryUrls.remove(url);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		categories.remove(frameworkCategory);

		DmEducTest.getSingleton().close();
	}

}
