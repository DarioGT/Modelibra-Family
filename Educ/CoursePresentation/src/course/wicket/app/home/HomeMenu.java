package course.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.Course;
import course.lecture.Lecture;
import course.lecture.presentation.Presentations;
import course.reference.Reference;
import course.reference.countrylanguage.CountryLanguage;
import course.reference.countrylanguage.CountryLanguages;
import course.wicket.app.CourseApp;
import course.wicket.app.about.AboutPage;
import course.wicket.app.upload.UploadPage;
import course.wicket.lecture.presentation.EntityUpdateTablePage;

/**
 * Application home page menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-18
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
		CourseApp courseApp = (CourseApp) getApplication();
		Course course = courseApp.getCourse();
		Lecture lecture = course.getLecture();
		Reference reference = course.getReference();

		// Update Presentations
		ViewModel presentationsViewModel = new ViewModel();
		presentationsViewModel.setModel(lecture);
		Presentations presentations = lecture.getPresentations();
		Presentations presentationsOrderedByTitle = presentations
				.getPresentationsOrderedByTitle(true);
		presentationsViewModel.setEntities(presentationsOrderedByTitle);

		View presentationsView = new View();
		presentationsView.setWicketId("presentationUpdateTable");
		presentationsView.setContextView(view);
		presentationsView.setPage(view.getPage());
		presentationsView.setUpdate(true);

		Link presentationUpdateTableLink = EntityUpdateTablePage.link(
				presentationsViewModel, presentationsView);
		add(presentationUpdateTableLink);

		// Sign out
		final AppSession appSession = getAppSession();

		Link signoutLink = new Link("signout") {
			public void onClick() {
				appSession.invalidate();
				setResponsePage(HomePage.class);
			}
		};
		add(signoutLink);
		if (!appSession.isUserSignedIn()) {
			signoutLink.setVisible(false);
		}

		// Upload
		Link uploadLink = new Link("upload") {
			public void onClick() {
				setResponsePage(UploadPage.class);
			}
		};
		add(uploadLink);
		if (!appSession.isUserSignedIn()) {
			uploadLink.setVisible(false);
		}

		// I18n
		ViewModel countryLanguageViewModel = new ViewModel();
		countryLanguageViewModel.setModel(reference);
		CountryLanguages countryLanguages = reference.getCountryLanguages();
		countryLanguageViewModel.setEntities(countryLanguages);
		String languageCode = null;
		CountryLanguage defaultLanguage = null;
		languageCode = appSession.getLocale().getLanguage();
		defaultLanguage = (CountryLanguage) countryLanguages
				.retrieveByCode(languageCode);
		if (defaultLanguage == null) {
			defaultLanguage = (CountryLanguage) countryLanguages
					.retrieveByCode("en");
		}
		countryLanguageViewModel.setEntity(defaultLanguage);
		countryLanguageViewModel.getUserProperties().addUserProperty(
				"getLanguageMethod", "getLanguage");
		countryLanguageViewModel.getUserProperties().addUserProperty(
				"getLanguageListMethod", "getLanguageList");
		countryLanguageViewModel.getUserProperties().addUserProperty(
				"getLanguageCodeMethod", "getLanguageCode");

		View countryLanguageView = new View();
		countryLanguageView.setWicketId("countryLanguageChoiceSection");

		Panel countryLanguageChoicePanel = new CountryLanguageChoicePanel(
				countryLanguageViewModel, countryLanguageView);
		add(countryLanguageChoicePanel);
		if (!course.getDomainConfig().isI18n()) {
			countryLanguageChoicePanel.setVisible(false);
		}

		// About
		Link aboutLink = new Link("aboutLink") {
			public void onClick() {
				setResponsePage(new AboutPage(viewModel, view));
			}
		};
		add(aboutLink);
	}

}
