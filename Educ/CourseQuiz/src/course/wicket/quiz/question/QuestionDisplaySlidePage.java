package course.wicket.quiz.question;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.question.Question;
import course.quiz.question.Questions;
import course.quiz.test.Test;

/**
 * Question display slide page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class QuestionDisplaySlidePage extends WebPage {

	private static final long serialVersionUID = 282050L;

	public QuestionDisplaySlidePage(Test test) {
		add(homePageLink("homePage"));

		Questions questions = test.getQuestions();
		Questions orderedQuestions = questions
				.getQuestionsOrderedByNumber(true);
		Question firstQuestion = (Question) orderedQuestions.first();
		if (firstQuestion == null) {
			Panel panel = new Panel("questionDisplaySlidePanel");
			panel.setVisible(false);
			add(panel);
		} else {
			add(new QuestionDisplaySlidePanel("questionDisplaySlidePanel",
					orderedQuestions, firstQuestion, null));
		}

	}

	QuestionDisplaySlidePage(Questions questions, Question question,
			String answer) {
		add(homePageLink("homePage"));

		add(new QuestionDisplaySlidePanel("questionDisplaySlidePanel",
				questions, question, answer));
	}

	public static PageLink link(String id, final Test test) {
		PageLink link = new PageLink(id, new IPageLink() {
			static final long serialVersionUID = 282051L;

			public Page getPage() {
				return new QuestionDisplaySlidePage(test);
			}

			public Class<? extends Page> getPageIdentity() {
				return QuestionDisplaySlidePage.class;
			}
		});
		return link;
	}

}
