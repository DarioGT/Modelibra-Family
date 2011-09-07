package course.wicket.app.home;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.neighbor.ParentChildPropertyDisplayListPanel;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.security.SigninPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.Course;
import course.lecture.Lecture;
import course.lecture.presentation.Presentations;
import course.reference.Reference;
import course.reference.member.Member;
import course.reference.member.Members;
import course.wicket.app.CourseApp;

/**
 * Application home page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-16
 */
@SuppressWarnings("serial")
public class HomePage extends DmPage {

	public HomePage() {
		CourseApp courseApp = (CourseApp) getApplication();
		Course course = courseApp.getCourse();
		Lecture lecture = course.getLecture();
		Reference reference = course.getReference();

		ViewModel homePageModel = new ViewModel(lecture);
		View homePageView = new View();
		homePageView.setWicketId("homePageMenuSection");
		homePageView.setPage(this);

		// Menu
		add(new HomeMenu(homePageModel, homePageView));

		// Presentations
		ViewModel presentationsViewModel = new ViewModel();
		presentationsViewModel.setModel(lecture);
		Presentations presentations = lecture.getPresentations();
		Presentations presentationsOrderedByCode = presentations
				.getPresentationsOrderedByCode(true);
		presentationsViewModel.setEntities(presentationsOrderedByCode);

		View presentationsView = new View();
		presentationsView.setWicketId("presentationTableSection");
		presentationsView.setPage(this);

		EntityDisplayTablePanel presentationTableSection = new EntityDisplayTablePanel(
				presentationsViewModel, presentationsView);
		add(presentationTableSection);

		// Sign in
		ViewModel signinViewModel = new ViewModel();
		signinViewModel.setModel(reference);
		Members members = reference.getMembers();
		signinViewModel.setEntities(members);
		signinViewModel.setEntity(new Member(members.getModel()));

		View signinView = new View();
		signinView.setWicketId("signinSection");

		Panel signinPanel = new SigninPanel(signinViewModel, signinView);
		add(signinPanel);
		add(new FeedbackPanel("signinFeedback"));
		AppSession appSession = getAppSession();
		if (appSession.isUserSignedIn()) {
			signinPanel.setVisible(false);
		}

		// Member emails
		ViewModel membersViewModel = new ViewModel();
		membersViewModel.setModel(reference);
		Members receiveEmailMembers = members.getReceiveEmailMembers();
		membersViewModel.setEntities(receiveEmailMembers);
		membersViewModel.setPropertyCode("email");

		View membersView = new View();
		membersView.setWicketId("memberEmailListSection");
		membersView.setTitle("Member.Emails");

		EntityPropertyDisplayListPanel questionTextListSection = new EntityPropertyDisplayListPanel(
				membersViewModel, membersView);
		add(questionTextListSection);

		// Presentation Slides
		ViewModel presentationSlidesViewModel = new ViewModel();
		presentationSlidesViewModel.setModel(lecture);
		Presentations presentationsOrderedByTitle = presentations
				.getPresentationsOrderedByTitle(true);
		presentationSlidesViewModel.setEntities(presentationsOrderedByTitle);
		presentationSlidesViewModel.setPropertyCode("title");
		presentationSlidesViewModel.getUserProperties().addUserProperty(
				"childNeighbor", "slides");
		presentationSlidesViewModel.getUserProperties().addUserProperty(
				"childProperty", "header");

		View presentationSlidesView = new View();
		presentationSlidesView.setWicketId("presentationSlideListSection");
		presentationSlidesView.setTitle("Presentation.Slides");

		ParentChildPropertyDisplayListPanel presentationSlideListSection = new ParentChildPropertyDisplayListPanel(
				presentationSlidesViewModel, presentationSlidesView);
		add(presentationSlideListSection);
	}

}
