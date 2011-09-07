package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.neighbor.ParentChildPropertyDisplayListPanel;
import org.modelibra.wicket.security.SigninPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.reference.Reference;
import dmeduc.reference.countryname.CountryNames;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.member.MemberInterestsListPanel;

/**
 * Application home page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class HomePage extends DmPage {

	/**
	 * Constructs the Home page.
	 */
	public HomePage() {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();
		Reference reference = dmEduc.getReference();

		ViewModel homePageModel = new ViewModel(webLink);
		View homePageView = new View();
		homePageView.setWicketId("homeMenu");
		homePageView.setPage(this);

		// Menu
		add(new HomeMenu(homePageModel, homePageView));

		// Questions
		ViewModel questionsModel = new ViewModel(webLink);
		Questions questions = webLink.getQuestions();
		questionsModel.setEntities(questions);
		questionsModel.setPropertyCode("text");

		View questionsView = new View();
		questionsView.setWicketId("questionTextList");
		questionsView.setTitle("Questions");

		EntityPropertyDisplayListPanel questionTextList = new EntityPropertyDisplayListPanel(
				questionsModel, questionsView);
		add(questionTextList);

		// Category Urls
		ViewModel categoryUrlsModel = new ViewModel(webLink);
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

		// Member Interests
		ViewModel membersModel = new ViewModel(webLink);
		Members members = webLink.getMembers();
		Members orderedMembers = members.getMembersOrderedByLastFirstName(true);
		membersModel.setEntities(orderedMembers);

		View membersView = new View();
		membersView.setWicketId("memberInterestsList");

		MemberInterestsListPanel memberInterestList = new MemberInterestsListPanel(
				membersModel, membersView);
		add(memberInterestList);

		// Comments
		ViewModel commentsModel = new ViewModel(webLink);
		Comments comments = webLink.getComments();
		commentsModel.setEntities(comments);

		View commentsView = new View();
		commentsView.setWicketId("commentTable");
		commentsView.setContextView(homePageView);

		EntityDisplayTablePanel commentTable = new EntityDisplayTablePanel(
				commentsModel, commentsView);
		add(commentTable);

		// Country Names
		ViewModel countryNamesViewModel = new ViewModel(reference);
		CountryNames countryNames = reference.getCountryNames();
		countryNamesViewModel.setEntities(countryNames);

		View countryNamesView = new View();
		countryNamesView.setWicketId("countryNameTable");
		countryNamesView.setContextView(homePageView);

		EntityDisplayTablePanel countryTable = new EntityDisplayTablePanel(
				countryNamesViewModel, countryNamesView);
		add(countryTable);

		// Sign In
		ViewModel signInViewModel = new ViewModel(webLink);
		signInViewModel.setEntities(members);
		signInViewModel.setEntity(new Member(members.getModel()));

		View signInView = new View();
		signInView.setWicketId("signIn");
		signInView.setContextView(homePageView);

		Panel signIn = new SigninPanel(signInViewModel, signInView);
		add(signIn);
		add(new FeedbackPanel("signInFeedback"));
		if (!dmEduc.getDomainConfig().isSignin()) {
			signIn.setVisible(false);
		} else if (getAppSession().isUserSignedIn()) {
			signIn.setVisible(false);
		}
	}

}
