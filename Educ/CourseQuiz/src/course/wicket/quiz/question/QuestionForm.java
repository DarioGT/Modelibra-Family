package course.wicket.quiz.question;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Question form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
@SuppressWarnings("serial")
public abstract class QuestionForm extends Form {

	protected Questions questions;

	protected Question question;

	protected UserAnswerPanel userAnswerPanel;

	public QuestionForm(String id, Questions questions,
			final Question question, String answer) {
		super(id);
		this.questions = questions;
		this.question = question;
		if (answer == null) {
			answer = "";
		}
		add(new Label("quiz", question.getTest().getName()));
		add(new Label("number", question.getNumber().toString()));
		add(new Label("type", question.getType()));
		Float numberOfPoints = question.getPoints();
		String points = "";
		if (numberOfPoints != null) {
			points = numberOfPoints.toString();
		}
		add(new Label("points", points));
		add(new MultiLineLabel("text", question.getText()));
		userAnswerPanel = new UserAnswerPanel("userAnswerPanel", question,
				answer);
		add(userAnswerPanel);
	}

}
