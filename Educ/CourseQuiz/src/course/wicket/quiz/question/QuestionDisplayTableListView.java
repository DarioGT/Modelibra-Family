package course.wicket.quiz.question;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.util.TextHandler;

import course.quiz.question.Question;
import course.quiz.question.Questions;
import course.wicket.app.CourseApp;

/**
 * Question display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-16
 */
public class QuestionDisplayTableListView extends ListView {

	private static final long serialVersionUID = 282070L;

	private Questions questions;

	public QuestionDisplayTableListView(String id, Questions questions) {
		super(id, questions.getList());
		this.questions = questions;
	}

	protected void populateItem(ListItem item) {
		Question question = (Question) item.getModelObject();

		item.add(new Label("quiz", question.getTest().getName()));

		item.add(new Label("number", question.getNumber().toString()));

		TextHandler textExtractor = new TextHandler();
		String shortText = textExtractor.extractBegin(question.getText(),
				CourseApp.MIN_LONG_TEXT_LENGTH);
		item.add(new Label("shortText", shortText));

		item.add(TableQuestionPage.link("tableQuestionPage", questions,
				question));
	}

}
