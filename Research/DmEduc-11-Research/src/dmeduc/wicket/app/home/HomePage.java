package dmeduc.wicket.app.home;

import modelibra.wicket.component.old.Entity2DisplayPanel;
import modelibra.wicket.component.old.EntityDisplayPanel;
import modelibra.wicket.component.old.EntityList2DisplayPanel;
import modelibra.wicket.component.old.EntityListDisplayPanel;
import modelibra.wicket.component.old.EntityRepeatingDisplayPanel;
import modelibra.wicket.component.old.EntityTableDisplayPanel;
import modelibra.wicket.component.view.ComponentView;
import modelibra.wicket.component.view.ConceptView;
import modelibra.wicket.component.view.LookupConceptView;
import modelibra.wicket.component.view.PropertyView;
import modelibra.wicket.component.view.RootConceptView;

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
import dmeduc.weblink.comment.Comments;
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

		// Url
		Category frameworkCategory = categories.getCategoryByName("Framework");
		Urls urls = frameworkCategory.getUrls();
		Url modelibraUrl = urls.getUrl("name", "Modelibra");

		ComponentView modelibraComponentView = new ComponentView(webLink);
		modelibraComponentView.setWicketId("Modelibra");
		ConceptView modelibraConceptView = new RootConceptView(
				modelibraComponentView, "Url");
		modelibraConceptView.setEntity(modelibraUrl);
		add(new EntityDisplayPanel(modelibraComponentView));

		// Url
		Url wicketUrl = urls.getUrl("name", "Wicket");

		ComponentView wicketComponentView = new ComponentView(webLink);
		wicketComponentView.setWicketId("Wicket");
		ConceptView wicketConceptView = new RootConceptView(
				wicketComponentView, "Url");
		wicketConceptView.setEntity(wicketUrl);
		PropertyView linkPropertyView = new PropertyView(wicketConceptView,
				"link");
		linkPropertyView.getUserProperties().addUserProperty(
				"displayLinkAsText", "Wicket");
		PropertyView descriptionPropertyView = new PropertyView(
				wicketConceptView, "description");
		descriptionPropertyView.setShortDisplay(true);
		add(new EntityDisplayPanel(wicketComponentView));

		// Url
		Url jbossUrl = urls.getUrl("name", "JBoss Seam");

		ComponentView jbossComponentView = new ComponentView(webLink);
		jbossComponentView.setWicketId("JBoss Seam");
		ConceptView categoryLookupConceptView = new LookupConceptView(
				jbossComponentView, "Category");
		categoryLookupConceptView.setEntity(frameworkCategory);
		ConceptView urlConceptView = new RootConceptView(jbossComponentView, "Url");
		urlConceptView.setEntity(jbossUrl);
		urlConceptView.setAbsorptionPermitted(true);
		urlConceptView.includeEssentialProperties();
		add(new EntityDisplayPanel(jbossComponentView));

		// Url
		ComponentView modelibra2ComponentView = new ComponentView(webLink);
		modelibra2ComponentView.setWicketId("Modelibra2");
		ConceptView modelibra2ConceptView = new RootConceptView(
				modelibra2ComponentView, "Url");
		modelibra2ConceptView.setEntity(modelibraUrl);
		modelibra2ConceptView.setDisplayPropertyIfNull(false);
		add(new Entity2DisplayPanel(modelibra2ComponentView));

		// Comment table with essential properties
		Comments comments = webLink.getComments();
		ComponentView commentTable1ComponentView = new ComponentView(webLink);
		commentTable1ComponentView.setWicketId("commentTable1");
		ConceptView comment1ConceptView = new RootConceptView(
				commentTable1ComponentView, "Comment");
		comment1ConceptView.setEntities(comments);
		add(new EntityTableDisplayPanel(commentTable1ComponentView));

		// Comment table with given properties
		ComponentView commentTable2ComponentView = new ComponentView(webLink);
		commentTable2ComponentView.setWicketId("commentTable2");
		ConceptView comment2ConceptView = new RootConceptView(
				commentTable2ComponentView, "Comment");
		comment2ConceptView.setEntities(comments);
		new PropertyView(comment2ConceptView, "text");
		new PropertyView(comment2ConceptView, "creationDate");
		add(new EntityTableDisplayPanel(commentTable2ComponentView));

		// Repeating comments with essential properties
		ComponentView repeatingCommentsComponentView = new ComponentView(
				webLink);
		repeatingCommentsComponentView.setWicketId("commentTable3");
		ConceptView comment3ConceptView = new RootConceptView(
				repeatingCommentsComponentView, "Comment");
		comment3ConceptView.setEntities(comments);
		add(new EntityRepeatingDisplayPanel(repeatingCommentsComponentView));

		// List of comments with given properties
		ComponentView commentList1ComponentView = new ComponentView(webLink);
		commentList1ComponentView.setWicketId("commentList1");
		ConceptView commentList1ConceptView = new RootConceptView(
				commentList1ComponentView, "Comment");
		commentList1ConceptView.setEntities(comments);
		new PropertyView(commentList1ConceptView, "text");
		new PropertyView(commentList1ConceptView, "creationDate");
		add(new EntityListDisplayPanel(commentList1ComponentView));

		// List of comments with all properties (and with headers before values)
		ComponentView commentList2ComponentView = new ComponentView(webLink);
		commentList2ComponentView.setWicketId("commentList2");
		ConceptView commentList2ConceptView = new RootConceptView(
				commentList2ComponentView, "Comment");
		commentList2ConceptView.setEntities(comments);
		commentList2ConceptView.includeAllProperties();
		add(new EntityList2DisplayPanel(commentList2ComponentView));

		// List of comments with given properties (and with headers before
		// values)
		ComponentView commentList3ComponentView = new ComponentView(webLink);
		commentList3ComponentView.setWicketId("commentList3");
		ConceptView commentList3ConceptView = new RootConceptView(
				commentList3ComponentView, "Comment");
		commentList3ConceptView.setEntities(comments);
		new PropertyView(commentList3ConceptView, "source");
		new PropertyView(commentList3ConceptView, "creationDate");
		add(new EntityList2DisplayPanel(commentList3ComponentView));

	}

}
