package dmeduc.weblink.question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

public class QuestionsTest {

	public static final String FRAMEWORK_CATEGORY = "Framework";

	private static Categories categories;
	private static Category frameworkCategory;

	private static Questions frameworkCategoryQuestions;

	private static Questions questions;

	@BeforeClass
	public static void beforeQuestions() throws Exception {
		WebLink webLink = DmEducTest.getSingleton().getDmEduc().getWebLink();
		questions = webLink.getQuestions();
		categories = webLink.getCategories();
		frameworkCategory = categories.createCategory(FRAMEWORK_CATEGORY);
		frameworkCategoryQuestions = frameworkCategory.getQuestions();
	}

	@Before
	public void beforeTest() throws Exception {
		questions.getErrors().empty();
	}

	@Test
	public void questionjsRequiredCreated() throws Exception {
		Question question01 = questions.createQuestion("1+1=?");
		Question question02 = questions.createQuestion("2+2=?");
		Question question03 = questions.createQuestion("3+3=?");

		assertNotNull(question01);
		assertTrue(questions.contain(question01));
		assertNotNull(question02);
		assertTrue(questions.contain(question02));
		assertNotNull(question03);
		assertTrue(questions.contain(question03));
		assertTrue(questions.getErrors().isEmpty());
	}

	@Test
	public void questionjsCreated() throws Exception {
		Question question01 = frameworkCategoryQuestions.createQuestion(
				frameworkCategory, "What is a framework?");
		Question question02 = frameworkCategoryQuestions.createQuestion(
				frameworkCategory, "Is framework design only?");
		Question question03 = frameworkCategoryQuestions.createQuestion(
				frameworkCategory, "What is Modelibra?");

		assertNotNull(question01);
		assertTrue(frameworkCategoryQuestions.contain(question01));
		assertNotNull(question02);
		assertTrue(frameworkCategoryQuestions.contain(question02));
		assertNotNull(question03);
		assertTrue(frameworkCategoryQuestions.contain(question03));
		assertTrue(frameworkCategoryQuestions.getErrors().isEmpty());
	}

	@Test
	public void textRequired() throws Exception {
		Question question = questions.createQuestion(null, null);

		assertNull(question);
		assertFalse(questions.contain(question));
		assertFalse(questions.getErrors().isEmpty());
		assertNotNull(questions.getErrors().getError("Question.text.required"));
	}

	@Test
	public void typeLength() throws Exception {
		String text = "1+1=?";
		String type = "open ended question but with the restriction of at most one page";
		Question question = questions.createQuestion(null, text, type);

		assertNull(question);
		assertFalse(questions.contain(question));
		assertFalse(questions.getErrors().isEmpty());
		assertNotNull(questions.getErrors().getError("Question.type.length"));
	}

	@Test
	public void creationDateDefault() throws Exception {
		String questionText = "1+1=?";
		Question question = questions.createQuestion(null, questionText);
		Date creationDate = question.getCreationDate();
		EasyDate today = new EasyDate(new Date());

		assertNotNull(creationDate);
		assertTrue(today.equals(creationDate));
	}

	@Test
	public void pointsDefault() throws Exception {
		String questionText = "1+1=?";
		Question question = questions.createQuestion(null, questionText);
		Float pointsFloat = question.getPoints();
		String defaultValue = question.getConceptConfig().getPropertyConfig(
				"points").getDefaultValue();
		Float floatValue = new Float(defaultValue);

		assertNotNull(pointsFloat);
		assertTrue(pointsFloat.equals(floatValue));
	}

	@Test
	public void questionWithoutCategoryCreation() throws Exception {
		String questionText = "1+1=?";
		Question questionWithoutCategory = questions.createQuestion(null,
				questionText);
		// To be sure that question is not added to the framework category.
		assertFalse(frameworkCategoryQuestions.contain(questionWithoutCategory));
	}

	@Test
	public void questionWithoutCategoryRetrieval() throws Exception {
		String questionText = "1+1=?";
		questions.createQuestion(null, questionText);
		Question question = questions.getQuestion("text", questionText);

		assertNotNull(question);
	}

	@Test
	public void questionWithCategoryCreation() throws Exception {
		String questionText = "What is Modelibra?";
		Question questionWithCategory = questions.createQuestion(
				frameworkCategory, questionText);

		// To be sure that question is added to the framework category.
		assertTrue(frameworkCategoryQuestions.contain(questionWithCategory));
	}

	@Test
	public void anotherQuestionWithCategoryCreation() throws Exception {
		String questionText = "What is Modelibra?";
		Question questionWithCategory = frameworkCategoryQuestions
				.createQuestion(frameworkCategory, questionText);

		// To be sure that question is added to questions.
		assertTrue(questions.contain(questionWithCategory));
	}

	@Test
	public void questionWithCategoryRetrieval() throws Exception {
		String questionText = "What is Modelibra?";
		questions.createQuestion(frameworkCategory, questionText);
		Question question = questions.getQuestion("text", questionText);

		assertNotNull(question);

		Question frameworkCategoryQuestion = frameworkCategoryQuestions
				.getQuestion("text", questionText);

		assertNotNull(frameworkCategoryQuestion);
		assertEquals(question, frameworkCategoryQuestion);
	}

	@Test
	public void numberAutoIncrement() throws Exception {
		frameworkCategoryQuestions.createQuestion(frameworkCategory,
				"What is a framework?");
		frameworkCategoryQuestions.createQuestion(frameworkCategory,
				"Is framework design only?");
		frameworkCategoryQuestions.createQuestion(frameworkCategory,
				"What is Modelibra?");

		for (Iterator<Question> iterator = questions.iterator(); iterator
				.hasNext();) {
			Question question = iterator.next();
			Question nextQuestion;
			if (iterator.hasNext()) {
				nextQuestion = iterator.next();
			} else {
				break;
			}
			Integer number = question.getNumber();
			Integer nextNumber = nextQuestion.getNumber();

			assertTrue(number < nextNumber);
			assertTrue(nextNumber.equals(number + 1));
		}

	}

	@Test
	public void allQuestionsZeroPointsUpdate() throws Exception {
		for (Question question : questions) {
			Question questionCopy = question.copy();
			questionCopy.setPoints(0f);
			questions.update(question, questionCopy);
		}

		assertTrue(questions.getErrors().isEmpty());

		for (Question question : questions) {
			assertTrue(question.getPoints() == 0f);
		}
	}

	@Test
	public void questionWithCategoryRemoval() throws Exception {
		String questionText = "What is a framework";
		Question questionWithCategory = questions.createQuestion(
				frameworkCategory, questionText);
		boolean removed = questions.remove(questionWithCategory);

		assertTrue(removed);
		assertFalse(questions.contain(questionWithCategory));
		assertTrue(questions.getErrors().isEmpty());
		// To be sure that question is also removed the framework category.
		assertFalse(frameworkCategoryQuestions.contain(questionWithCategory));
	}

	@Test
	public void anotherQuestionWithCategoryRemoval() throws Exception {
		String questionText = "What is Modelibra";
		Question questionWithCategory = frameworkCategoryQuestions
				.createQuestion(frameworkCategory, questionText);
		boolean removed = frameworkCategoryQuestions
				.remove(questionWithCategory);

		assertTrue(removed);
		assertFalse(frameworkCategoryQuestions.contain(questionWithCategory));
		assertTrue(frameworkCategoryQuestions.getErrors().isEmpty());
		// To be sure that question is also removed from questions.
		assertFalse(questions.contain(questionWithCategory));
	}

	@After
	public void afterTest() throws Exception {
		for (Question question : questions.getList()) {
			questions.remove(question);
		}
	}

	@AfterClass
	public static void afterTests() throws Exception {
		categories.remove(frameworkCategory);

		DmEducTest.getSingleton().close();
	}

}
