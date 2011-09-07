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

	public static final String SOFTWARE_CATEGORY = "Software";

	private static Categories categories;
	private static Category softwareCategory;

	private static Categories softwareCategorySubcategories;

	@BeforeClass
	public static void beforeTests() throws Exception {
		categories = DmEducTest.getSingleton().getDmEduc().getWebLink()
				.getCategories();
	}

	@Before
	public void beforeTest() throws Exception {
		softwareCategory = categories.createCategory(SOFTWARE_CATEGORY);
		softwareCategorySubcategories = softwareCategory.getCategories();

		categories.getErrors().empty();
		softwareCategorySubcategories.getErrors().empty();
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
		Category category01 = categories
				.createCategory("Personal", null, false);
		Category category02 = categories.createCategory("Framework",
				"Java and Javascript frameworks", true);
		Category category03 = categories.createCategory("Database",
				"Relational, object and XML databases.", true);
		Category category04 = categories.createCategory("Programming Language",
				"Only object oriented languages.", false);

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
	public void subcategoriesRequiredCreated() throws Exception {
		Category category01 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Framework");
		Category category02 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Database");
		Category category03 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Programming Language");

		assertNotNull(category01);
		assertTrue(softwareCategorySubcategories.contain(category01));
		assertNotNull(category02);
		assertTrue(softwareCategorySubcategories.contain(category02));
		assertNotNull(category03);
		assertTrue(softwareCategorySubcategories.contain(category03));
		assertTrue(softwareCategorySubcategories.getErrors().isEmpty());
	}

	@Test
	public void subcategoriesCreated() throws Exception {
		Category category01 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Framework",
				"Java and Javascript frameworks", true);
		Category category02 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Database",
				"Relational, object and XML databases.", true);
		Category category03 = softwareCategorySubcategories.createCategory(
				softwareCategory, "Programming Language",
				"Only object oriented languages.", false);

		assertNotNull(category01);
		assertTrue(softwareCategorySubcategories.contain(category01));
		assertNotNull(category02);
		assertTrue(softwareCategorySubcategories.contain(category02));
		assertNotNull(category03);
		assertTrue(softwareCategorySubcategories.contain(category03));
		assertTrue(softwareCategorySubcategories.getErrors().isEmpty());
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
	public void approvedDefault() throws Exception {
		Category category = categories.createCategory("Framework");
		Boolean approvedObject = category.getApproved();
		boolean approved = category.isApproved();

		assertNotNull(approvedObject);
		assertFalse(approved);
	}

	@Test
	public void nameOrder() throws Exception {
		categories.createCategory("Framework");
		categories.createCategory("Database");
		categories.createCategory("Programming Language");
		Categories orderedCategories = categories.getCategoriesOrderedByName();

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
	public void approvedSelection() throws Exception {
		categories.createCategory("Personal", null, false);
		categories.createCategory("Framework",
				"Java and Javascript frameworks", true);
		categories.createCategory("Database",
				"Relational, object and XML databases.", true);
		categories.createCategory("Programming Language",
				"Only object oriented languages.", false);
		Categories approvedCategories = categories.getApprovedCategories();

		assertFalse(approvedCategories.isEmpty());
		assertNotSame(categories, approvedCategories);

		for (Iterator<Category> iterator = approvedCategories.iterator(); iterator
				.hasNext();) {
			Category category = iterator.next();

			assertTrue(category.isApproved());
		}

		int counter = 0;
		for (Category category : categories) {
			if (category.isApproved())
				counter++;
		}

		assertEquals(approvedCategories.size(), counter);
	}

	@Test
	public void descriptionKeywordSelection() throws Exception {
		categories.createCategory("Personal", null, false);
		categories.createCategory("Framework",
				"Java and Javascript frameworks", true);
		categories.createCategory("Database",
				"Relational, object and XML databases.", true);
		categories.createCategory("Programming Language",
				"Only object oriented languages.", false);
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

	@Test
	public void softwareFrameworkRetrieval() throws Exception {
		String framework = "Framework";
		softwareCategorySubcategories.createCategory(softwareCategory,
				framework, "Java and Javascript frameworks", true);
		Category frameworkSubcategory = softwareCategorySubcategories
				.getCategory("name", framework);

		assertNotNull(frameworkSubcategory);
		assertEquals(frameworkSubcategory.getName(), framework);
	}

	@Test
	public void softwareFrameworkModelibraCreated() throws Exception {
		Category softwareFrameworkCategory = softwareCategorySubcategories
				.createCategory(softwareCategory, "Framework",
						"Java and Javascript frameworks", true);
		Categories softwareFrameworkCategorySubcategories = softwareFrameworkCategory
				.getCategories();
		Category softwareFrameworkModelibraCategory = softwareFrameworkCategorySubcategories
				.createCategory(softwareFrameworkCategory, "Modelibra",
						"Modelibra domain model framework", true);

		assertNotNull(softwareFrameworkModelibraCategory);
		assertTrue(softwareFrameworkCategorySubcategories
				.contain(softwareFrameworkModelibraCategory));
		assertTrue(softwareFrameworkCategorySubcategories.getErrors().isEmpty());
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
