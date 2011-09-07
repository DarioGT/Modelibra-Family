package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.WebPage;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.neighbor.ParentChildPropertyDisplayListPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.member.MemberListPanel;

/**
 * Application home page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-26
 */
public class HomePage extends WebPage {

	public HomePage() {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		// Menu
		add(new HomeMenu("homeMenu"));

		// Questions
		ViewModel questionsModel = new ViewModel();
		questionsModel.setModel(webLink);
		Questions questions = webLink.getQuestions();
		questionsModel.setEntities(questions);
		questionsModel.setPropertyCode("text");

		View questionsView = new View();
		questionsView.setWicketId("questionTextList");
		questionsView.setTitle("Questions");

		EntityPropertyDisplayListPanel questionTextList = new EntityPropertyDisplayListPanel(
				questionsModel, questionsView);
		add(questionTextList);
		
		// Comments
		ViewModel commentsModel = new ViewModel();
		commentsModel.setModel(webLink);
		Comments comments = webLink.getComments();
		commentsModel.setEntities(comments);

		View commentsView = new View();
		commentsView.setWicketId("commentTable");

		EntityDisplayTablePanel commentTable = new EntityDisplayTablePanel(
				commentsModel, commentsView);
		add(commentTable);

		// Category Urls
		ViewModel categoryUrlsModel = new ViewModel();
		categoryUrlsModel.setModel(webLink);
		Categories categories = webLink.getCategories();
		Categories orderedApprovedCategories = categories
				.getApprovedCategories().getCategoriesOrderedByName();
		categoryUrlsModel.setEntities(orderedApprovedCategories);
		categoryUrlsModel.setPropertyCode("name");
		categoryUrlsModel.getUserProperties().addUserProperty("childNeighbor",
				"urls");
		categoryUrlsModel.getUserProperties().addUserProperty("childProperty",
				"link");

		View categoryUrlsView = new View();
		categoryUrlsView.setWicketId("categoryNameUrlLinkList");
		categoryUrlsView.setTitle("Category.WebLinks");

		ParentChildPropertyDisplayListPanel categoryNameUrlLinkList = new ParentChildPropertyDisplayListPanel(
				categoryUrlsModel, categoryUrlsView);
		add(categoryNameUrlLinkList);

		// Members
		Members members = webLink.getMembers();
		Members orderedMembers = members.getMembersOrderedByLastFirstName(true);
		add(new MemberListPanel("memberList", orderedMembers.getList()));
	}

}
