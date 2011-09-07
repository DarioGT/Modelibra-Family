package course.wicket.quiz.question;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;

import course.quiz.question.Questions;
import course.quiz.test.Test;

/**
 * Question display table page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class QuestionDisplayTablePage extends WebPage {

	private static final long serialVersionUID = 282080L;

	public QuestionDisplayTablePage(Test test) {
		add(homePageLink("homePage"));
		Questions questions = test.getQuestions();
		Questions orderedQuestions = questions
				.getQuestionsOrderedByNumber(true);
		add(new QuestionDisplayTablePanel("questionDisplayTablePanel",
				orderedQuestions));
	}

	public static PageLink link(String id, final Test test) {
		PageLink link = new PageLink(id, new IPageLink() {
			static final long serialVersionUID = 282081L;

			public Page getPage() {
				return new QuestionDisplayTablePage(test);
			}

			public Class<? extends Page> getPageIdentity() {
				return QuestionDisplayTablePage.class;
			}
		});
		return link;
	}

}
