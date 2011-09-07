package course.wicket.quiz.question;

import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Question display slide panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-11-04
 */
public class QuestionDisplaySlidePanel extends Panel {

	private static final long serialVersionUID = 282060L;

	public QuestionDisplaySlidePanel(String id, Questions questions,
			Question question, String answer) {
		super(id);
		add(new SlideQuestionFormPanel("slideQuestionFormPanel", questions,
				question, answer));
		add(new SlideQuestionNavigationPanel("slideQuestionNavigatePanel",
				questions, question));
	}

}
