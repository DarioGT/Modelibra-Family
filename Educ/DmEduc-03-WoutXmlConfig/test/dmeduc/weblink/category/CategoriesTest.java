package dmeduc.weblink.category;

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

public class CategoriesTest {

	private static Categories categories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		categories = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		categories.getErrors().empty();
	}

	@Test
	public void categoriesRequiredCreated() throws Exception {
		Category category01 = categories.createCategory("Personal");
		Category category02 = categories.createCategory("Framework");
		Category category03 = categories.createCategory("Database");
		Category category04 = categories.createCategory("Programming Language");

		assertNotNull(category01);
		assertTrue(categories.contain(category01));
		assertNotNull(category02);
		assertTrue(categories.contain(category02));
		assertNotNull(category03);
		assertTrue(categories.contain(category03));
		assertNotNull(category04);
		assertTrue(categories.contain(category04));
		assertTrue(categories.getErrors().isEmpty());
	}

	@Test
	public void categoriesCreated() throws Exception {
		Category category01 = categories.createCategory("Personal", null);
		Category category02 = categories.createCategory("Framework",
				"Java and Javascript frameworks");
		Category category03 = categories.createCategory("Database",
				"Relational, object and XML databases.");
		Category category04 = categories.createCategory("Programming Language",
				"Only object oriented languages.");

		assertNotNull(category01);
		assertTrue(categories.contain(category01));
		assertNotNull(category02);
		assertTrue(categories.contain(category02));
		assertNotNull(category03);
		assertTrue(categories.contain(category03));
		assertNotNull(category04);
		assertTrue(categories.contain(category04));
		assertTrue(categories.getErrors().isEmpty());
	}

	@Test
	public void nameRequired() throws Exception {
		Category category = categories.createCategory(null);

		assertNull(category);
		assertFalse(categories.contain(category));
		assertFalse(categories.getErrors().isEmpty());
		assertNotNull(categories.getErrors().getError("Category.name.required"));
	}

	@Test
	public void nameUnique() throws Exception {
		String name = "Framework";
		categories.createCategory(name);
		Category notUniqueCategory = categories.createCategory(name);

		assertNull(notUniqueCategory);
		assertFalse(categories.contain(notUniqueCategory));
		assertFalse(categories.getErrors().isEmpty());
		assertNotNull(categories.getErrors().getError("Category.id.unique"));
	}

	@Test
	public void nameLength() throws Exception {
		String name = "The definitions never made much sense, as a brief journey into history shows. ";
		Category category = categories.createCategory(name);

		assertNull(category);
		assertFalse(categories.contain(category));
		assertFalse(categories.getErrors().isEmpty());
		assertNotNull(categories.getErrors().getError("Category.name.length"));
	}

	@Test
	public void nameOrder() throws Exception {
		categories.createCategory("Framework");
		categories.createCategory("Database");
		categories.createCategory("Programming Language");
		Categories orderedCategories = categories
				.getCategoriesOrderedByName(true);

		assertFalse(orderedCategories.isEmpty());
		assertEquals(categories.size(), orderedCategories.size());
		assertNotSame(categories, orderedCategories);

		for (Iterator<Category> iterator = orderedCategories.iterator(); iterator
				.hasNext();) {
			Category category = iterator.next();
			Category nextCategory;
			if (iterator.hasNext()) {
				nextCategory = iterator.next();
			} else {
				break;
			}

			assertTrue(category.getName().compareTo(nextCategory.getName()) < 0);
		}

	}

	@Test
	public void nameIgnoreCaseOrder() throws Exception {
		categories.createCategory("framework");
		categories.createCategory("database");
		categories.createCategory("Programming Language");
		Categories orderedCategories = categories
				.getCategoriesOrderedByNameIgnoringCase();

		assertFalse(orderedCategories.isEmpty());
		assertEquals(categories.size(), orderedCategories.size());
		assertNotSame(categories, orderedCategories);

		for (Iterator<Category> iterator = orderedCategories.iterator(); iterator
				.hasNext();) {
			Category category = iterator.next();
			Category nextCategory;
			if (iterator.hasNext()) {
				nextCategory = iterator.next();
			} else {
				break;
			}

			assertTrue(category.getName().compareTo(nextCategory.getName()) < 0);
		}

	}

	@Test
	public void descriptionKeywordSelection() throws Exception {
		categories.createCategory("Personal", null);
		categories
				.createCategory("Framework", "Java and Javascript frameworks");
		categories.createCategory("Database",
				"Relational, object and XML databases.");
		categories.createCategory("Programming Language",
				"Only object oriented languages.");
		String keyword = "object";
		Categories keywordCategories = categories.getKeywordCategories(keyword);

		assertFalse(keywordCategories.isEmpty());
		assertNotSame(categories, keywordCategories);

		for (Iterator<Category> iterator = keywordCategories.iterator(); iterator
				.hasNext();) {
			Category category = iterator.next();

			assertTrue(category.getDescription().contains(keyword));
		}

		int counter = 0;
		for (Category category : categories) {
			String description = category.getDescription();
			if (description != null && description.contains(keyword))
				counter++;
		}

		assertEquals(keywordCategories.size(), counter);
	}

	@After
	public void afterTest() throws Exception {
		for (Category category : categories.getList()) {
			categories.remove(category);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		DmEducTest.getSingleton().close();
	}

}
