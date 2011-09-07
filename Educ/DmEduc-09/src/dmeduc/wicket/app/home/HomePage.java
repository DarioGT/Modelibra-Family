package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.security.SigninPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.category.CategoryTreePanel;

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

		ViewModel homePageModel = new ViewModel(webLink);
		View homePageView = new View();
		homePageView.setWicketId("homeMenu");
		homePageView.setPage(this);

		// Menu
		add(new HomeMenu(homePageModel, homePageView));

		add(new Image("treeImage"));

		// Category Tree
		ViewModel treeViewModel = new ViewModel(webLink);
		Categories categories = webLink.getCategories();
		treeViewModel.setEntities(categories);
		View treeView = new View();
		treeView.setPage(this);
		treeView.setContextView(homePageView);
		treeView.setWicketId("categoryTree");
		add(new CategoryTreePanel(treeViewModel, treeView));

		// Sign In
		ViewModel signInViewModel = new ViewModel(webLink);
		Members members = webLink.getMembers();
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
	}

}
