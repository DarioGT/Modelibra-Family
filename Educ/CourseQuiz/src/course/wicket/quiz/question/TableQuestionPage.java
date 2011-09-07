package course.wicket.quiz.question;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;

import course.quiz.question.Question;
import course.quiz.question.Questions;

/**
 * Table question page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class TableQuestionPage extends WebPage {

	private static final long serialVersionUID = 282150L;

	public TableQuestionPage(Questions questions, Question question,
			String answer) {
		add(homePageLink("homePage"));

		add(QuestionDisplayTablePage.link("questionDisplayTablePage", question
				.getTest()));

		add(new TableQuestionFormPanel("tableQuestionFormPanel", questions,
				question, answer));
	}

	public static PageLink link(String id, final Questions questions,
			final Question question) {
		PageLink link = new PageLink(id, new IPageLink() {
			static final long serialVersionUID = 282151L;

			public Page getPage() {
				return new TableQuestionPage(questions, question, null);
			}

			public Class<? extends Page> getPageIdentity() {
				return TableQuestionPage.class;
			}
		});
		return link;
	}

}
