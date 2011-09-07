package course.wicket.quiz.question;

import org.apache.wicket.markup.html.form.Button;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Slide question form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-08-26
 */
public class SlideQuestionForm extends QuestionForm {

	private static final long serialVersionUID = 282100L;

	public SlideQuestionForm(String id, Questions questions,
			final Question question, String answer) {
		super(id, questions, question, answer);

		add(new Button("cancel") {
			static final long serialVersionUID = 282101L;

			public void onSubmit() {
				setResponsePage(new QuestionDisplaySlidePage(question.getTest()));
			}
		}.setDefaultFormProcessing(false));
	}

	protected void onSubmit() {
		setResponsePage(new QuestionDisplaySlidePage(questions, question,
				userAnswerPanel.getAnswer()));
	}

}
