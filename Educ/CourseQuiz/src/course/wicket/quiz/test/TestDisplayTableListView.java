package course.wicket.quiz.test;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.util.TextHandler;

import course.quiz.test.Test;
import course.quiz.test.Tests;
import course.wicket.app.CourseApp;
import course.wicket.quiz.document.DocumentDisplayTablePage;
import course.wicket.quiz.question.QuestionDisplaySlidePage;
import course.wicket.quiz.question.QuestionDisplayTablePage;

/**
 * Quiz display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class TestDisplayTableListView extends ListView {

	private static final long serialVersionUID = 282170L;

	public TestDisplayTableListView(String id, Tests tests) {
		super(id, tests.getList());
	}

	protected void populateItem(ListItem item) {
		Test test = (Test) item.getModelObject();

		item.add(new Label("name", test.getName()));

		TextHandler textExtractor = new TextHandler();
		String shortDescription = textExtractor.extractBegin(test
				.getDescription(), CourseApp.MIN_LONG_TEXT_LENGTH);
		item.add(new Label("shortDescription", shortDescription));

		item.add(QuestionDisplayTablePage
				.link("questionDisplayTablePage", test));

		item.add(QuestionDisplaySlidePage
				.link("questionDisplaySlidePage", test));

		item.add(DocumentDisplayTablePage
				.link("documentDisplayTablePage", test));
	}

}
