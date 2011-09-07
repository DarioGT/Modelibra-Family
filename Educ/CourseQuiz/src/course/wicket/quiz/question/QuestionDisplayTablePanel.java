package course.wicket.quiz.question;

import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.question.Questions;

/**
 * Question display table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-08-24
 */
@SuppressWarnings("serial")
public class QuestionDisplayTablePanel extends Panel {

	private static final long serialVersionUID = 282090L;

	public QuestionDisplayTablePanel(String id, Questions questions) {
		super(id);
		QuestionDisplayTableListView questionDisplayTableListView = new QuestionDisplayTableListView(
				"questionDisplayTableListView", questions);
		add(questionDisplayTableListView);
	}

}
