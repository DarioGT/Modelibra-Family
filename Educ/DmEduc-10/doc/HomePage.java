package dmeduc.wicket.app.home;

import modelibra.wicket.component.ConceptView;
import modelibra.wicket.component.EntityCombinedPropertyDisplayListPanel;
import modelibra.wicket.component.EntityDisplayPanel;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.neighbor.tree.AjaxEntityTreePanel;
import org.modelibra.wicket.security.SigninPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;
import dmeduc.weblink.url.Url;
import dmeduc.weblink.url.Urls;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.category.CategoryNodePanel;
import dmeduc.wicket.weblink.category.CategoryRootPanel;

/**
 * Application home page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-14
 */
@SuppressWarnings("serial")
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
		homePageView.setContextView(homePageView);
		homePageView.setPage(this);
		homePageView.setWicketId("homeMenu");

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

		add(new AjaxEntityTreePanel(treeViewModel, treeView) {
			@Override
			protected Panel getRootNodePanel(ViewModel viewModel, View view) {
				return new CategoryRootPanel(viewModel, view);
			}

			@Override
			protected Panel getNodePanel(ViewModel viewModel, View view) {
				return new CategoryNodePanel(viewModel, view);
			}
		});

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

		// Members
		ViewModel membersModel = new ViewModel(webLink);
		Members orderedMembers = webLink.getMembers()
				.getMembersOrderedByLastFirstName(true);
		membersModel.setEntities(orderedMembers);
		membersModel.getUserProperties()
				.addUserProperty("lastName", "lastName");
		membersModel.getUserProperties().addUserProperty("firstName",
				"firstName");

		View membersView = new View();
		membersView.setWicketId("memberNameList");
		membersView.setTitle("Members");
		membersView.getUserProperties().addUserProperty("separatorText", ", ");

		EntityCombinedPropertyDisplayListPanel memberNameList = new EntityCombinedPropertyDisplayListPanel(
				membersModel, membersView);
		add(memberNameList);

		// Url
		Category frameworkCategory = categories.getCategoryByName("Framework");
		Urls urls = frameworkCategory.getUrls();
		Url modelibraUrl = urls.getUrl("name", "Modelibra");
		
		ConceptView urlConceptView = new ConceptView("Url");
		urlConceptView.setEntity(modelibraUrl);
		urlConceptView.setWicketId("Modelibra");
		EntityDisplayPanel modelibraDisplayPanel = new EntityDisplayPanel(urlConceptView);
		add(modelibraDisplayPanel);
	}
}
