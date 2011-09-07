package course.wicket.quiz.question;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Slide question navigation panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-11-04
 */
public class SlideQuestionNavigationPanel extends Panel {

	private static final long serialVersionUID = 282120L;

	public SlideQuestionNavigationPanel(String id, final Questions questions,
			final Question question) {
		super(id);

		add(new Link("first") {
			static final long serialVersionUID = 282121L;

			public void onClick() {
				Question firstQuestion = (Question) questions.first();
				setResponsePage(new QuestionDisplaySlidePage(questions,
						firstQuestion, null));
			}
		});

		add(new Link("next") {
			static final long serialVersionUID = 282122L;

			public void onClick() {
				Question nextQuestion = (Question) questions.next(question);
				if (nextQuestion == null) {
					nextQuestion = question;
				}
				setResponsePage(new QuestionDisplaySlidePage(questions,
						nextQuestion, null));
			}
		});

		add(new Link("prior") {
			static final long serialVersionUID = 282123L;

			public void onClick() {
				Question priorQuestion = (Question) questions.prior(question);
				if (priorQuestion == null) {
					priorQuestion = question;
				}
				setResponsePage(new QuestionDisplaySlidePage(questions,
						priorQuestion, null));
			}
		});

		add(new Link("last") {
			static final long serialVersionUID = 282124L;

			public void onClick() {
				Question lastQuestion = (Question) questions.last();
				setResponsePage(new QuestionDisplaySlidePage(questions,
						lastQuestion, null));
			}
		});

	}

}
