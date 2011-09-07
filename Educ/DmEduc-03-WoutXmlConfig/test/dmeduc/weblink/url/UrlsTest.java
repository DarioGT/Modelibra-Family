package dmeduc.weblink.url;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
				"http://struts.apache.org/", "Struts");
		Url url02 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Wicket");
		Url url03 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Modelibra");

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
				"http://struts.apache.org/", null);
		Url url02 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		Url url03 = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");

		assertNotNull(url01);
		assertTrue(frameworkCategoryUrls.contain(url01));
		assertNotNull(url02);
		assertTrue(frameworkCategoryUrls.contain(url02));
		assertNotNull(url03);
		assertTrue(frameworkCategoryUrls.contain(url03));
		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void linkRequired() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, null,
				"Modelibra");

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.required"));
	}

	@Test
	public void linkUnique() throws Exception {
		String link = "http://www.modelibra.org/";
		frameworkCategoryUrls.createUrl(frameworkCategory, link, "Modelibra");
		Url notUniqueUrl = frameworkCategoryUrls.createUrl(frameworkCategory,
				link, "Modelibra");

		assertNull(notUniqueUrl);
		assertFalse(frameworkCategoryUrls.contain(notUniqueUrl));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.id.unique"));
	}

	@Test
	public void linkType() throws Exception {
		String link = "dzenanr@modelibra.org";
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, link,
				"Modelibra");

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.validation"));
	}

	@Test
	public void linkLength() throws Exception {
		String link = "http://www.modelibra.org/framework/learning/documentation/introduction/selection/keyword/example/short/";
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory, link,
				"Modelibra");

		assertNull(url);
		assertFalse(frameworkCategoryUrls.contain(url));
		assertFalse(frameworkCategoryUrls.getErrors().isEmpty());
		assertNotNull(frameworkCategoryUrls.getErrors().getError(
				"Url.link.length"));
	}

	@Test
	public void urlEquality() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Wicket");
		Url urlCopy = url.copy();

		assertEquals(url, urlCopy);
		assertNotSame(url, urlCopy);
	}

	@Test
	public void urlUpdate() throws Exception {
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Wicket");
		Url urlCopy = url.copy();
		urlCopy.setDescription("Component based Web framework.");

		assertTrue(url.equals(urlCopy));
		assertTrue(url.equalOid(urlCopy));
		assertFalse(url.equalProperties(urlCopy));

		frameworkCategoryUrls.update(url, urlCopy);

		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void descriptionKeywordSelection() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/", null);
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
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
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/", null);
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
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
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/", null);
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
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
	public void modelibraDescriptionUpdate() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/", null);
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
		String link = "http://www.modelibra.org/";
		Url url = frameworkCategoryUrls.getUrl("link", link);
		Url urlCopy = url.copy();
		String description = "Modelibra is a domain model framework. A domain model is a model of specific domain classes that describe the core data and their behavior. For prototypes and small applications, the model objects are saved in XML files.";
		urlCopy.setDescription(description);
		frameworkCategoryUrls.update(url, urlCopy);

		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void additionalUrlCreation() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
		Url url = frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://jquery.com/", "jQuery");

		assertNotNull(url);
		assertTrue(frameworkCategoryUrls.contain(url));
		assertTrue(frameworkCategoryUrls.getErrors().isEmpty());
	}

	@Test
	public void allUrls() throws Exception {
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://struts.apache.org/", null);
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://wicket.apache.org/", "Web component framework.");
		frameworkCategoryUrls.createUrl(frameworkCategory,
				"http://www.modelibra.org/", "Domain model framework.");
		Category personalCategory = categories.createCategory("Personal");

		assertNotNull(personalCategory);
		assertTrue(categories.contain(personalCategory));
		assertTrue(categories.getErrors().isEmpty());

		Urls personalCategoryUrls = personalCategory.getUrls();
		Url personalCategoryUrl = personalCategoryUrls.createUrl(
				personalCategory, "http://drdb.fsa.ulaval/",
				"Dzenan Ridjanovic");

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
