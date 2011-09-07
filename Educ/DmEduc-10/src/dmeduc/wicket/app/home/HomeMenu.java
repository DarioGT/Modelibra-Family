package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.neighbor.EntityParentChildUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.reference.Reference;
import dmeduc.reference.countrylanguage.CountryLanguage;
import dmeduc.reference.countrylanguage.CountryLanguages;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.app.about.AboutPage;
import dmeduc.wicket.weblink.applicant.SignUpPage;
import dmeduc.wicket.weblink.question.QuestionSelectionPage;

/**
 * Application home menu.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
public class HomeMenu extends DmPanel {

	/**
	 * Constructs the Home page menu.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public HomeMenu(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();
		Reference reference = dmEduc.getReference();

		// Sign Up
		ViewModel signUpViewModel = new ViewModel(webLink);
		Members members = webLink.getMembers();
		signUpViewModel.setEntities(members);
		signUpViewModel.setEntity(new Member(members.getModel()));

		PageLink signUpLink = SignUpPage.link("signUpLink", signUpViewModel,
				view);
		add(signUpLink);
		if (!dmEduc.getDomainConfig().isSignin()) {
			signUpLink.setVisible(false);
		} else if (getAppSession().isUserSignedIn()) {
			signUpLink.setVisible(false);
		}

		// Sign Out
		Link signOutLink = new Link("signOutLink") {
			public void onClick() {
				getAppSession().signOutUser();
				setResponsePage(HomePage.class);
			}
		};
		add(signOutLink);
		if (!getAppSession().isUserSignedIn()) {
			signOutLink.setVisible(false);
		}

		// Domain
		Link domainLink = new Link("domainLink") {
			public void onClick() {
				setResponsePage(dmEducApp.getDomainPageClass());
			}
		};
		add(domainLink);
		if (!getAppSession().isUserSignedIn()) {
			domainLink.setVisible(false);
		} else {
			Member signedInMember = (Member) getAppSession().getSignedInUser();
			if (!signedInMember.hasAdminAccess()) {
				domainLink.setVisible(false);
			}
		}

		// About
		View aboutView = new View();
		aboutView.setContextView(view);
		aboutView.setPage(view.getPage());
		add(AboutPage.link("aboutLink", viewModel, aboutView));

		// I18n
		ViewModel countryLanguageViewModel = new ViewModel(reference);
		CountryLanguages countryLanguages = reference.getCountryLanguages();
		countryLanguageViewModel.setEntities(countryLanguages);
		String languageCode = null;
		CountryLanguage defaultLanguage = null;
		languageCode = getAppSession().getLocale().getLanguage();
		defaultLanguage = countryLanguages.getCountryLanguage(languageCode);
		if (defaultLanguage == null) {
			defaultLanguage = countryLanguages.getCountryLanguage("en");
		}
		countryLanguageViewModel.setEntity(defaultLanguage);

		View countryLanguageView = new View();
		countryLanguageView.setWicketId("countryLanguageChoice");

		Panel countryLanguageChoice = new CountryLanguageChoicePanel(
				countryLanguageViewModel, countryLanguageView);
		add(countryLanguageChoice);
		if (!dmEduc.getDomainConfig().isI18n()) {
			countryLanguageChoice.setVisible(false);
		}

		// Member Page
		ViewModel myViewModel = new ViewModel(webLink);
		myViewModel.setEntities(members);
		View myView = new View();
		myView.setContextView(view);
		myView.setPage(view.getPage());
		Link myLink = EntityParentChildUpdatePage.link("myLink", myViewModel,
				myView);
		add(myLink);
		if (getAppSession().isUserSignedIn()) {
			Member signedInMember = (Member) getAppSession().getSignedInUser();
			myViewModel.setEntity(signedInMember);
		} else {
			myLink.setVisible(false);
		}

		// Select Questions
		ViewModel selectQuestionsViewModel = new ViewModel(webLink);
		Questions questions = webLink.getQuestions();
		selectQuestionsViewModel.setEntities(questions);
		selectQuestionsViewModel.setPropertyCode("text");
		View selectQuestionsView = new View();
		selectQuestionsView.setContextView(view);
		selectQuestionsView.setPage(view.getPage());

		Link selectQuestionsLink = QuestionSelectionPage.link(
				"selectQuestionsLink", selectQuestionsViewModel,
				selectQuestionsView);
		add(selectQuestionsLink);
	}

}
