package course.wicket.quiz.question;

import org.apache.wicket.markup.html.form.Button;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Table question form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-08-26
 */
public class TableQuestionForm extends QuestionForm {

	private static final long serialVersionUID = 282130L;

	public TableQuestionForm(String id, Questions questions,
			final Question question, String answer) {
		super(id, questions, question, answer);

		add(new Button("cancel") {
			static final long serialVersionUID = 282131L;

			public void onSubmit() {
				setResponsePage(new QuestionDisplayTablePage(question.getTest()));
			}
		}.setDefaultFormProcessing(false));
	}

	protected void onSubmit() {
		setResponsePage(new TableQuestionPage(questions, question,
				userAnswerPanel.getAnswer()));
	}

}
