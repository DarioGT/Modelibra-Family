package travel.wicket.app.home;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.security.SigninPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import travel.Travel;
import travel.impression.Impression;
import travel.impression.place.Places;
import travel.impression.traveler.Traveler;
import travel.impression.traveler.Travelers;
import travel.wicket.app.TravelApp;

/**
 * Application home page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public class HomePage extends DmPage {

	private static Log log = LogFactory.getLog(HomePage.class);

	public HomePage() {
		try {
			TravelApp travelApp = (TravelApp) getApplication();
			Travel travel = travelApp.getTravel();
			Impression impression = travel.getImpression();

			// Menu
			add(new HomePageMenuPanel("homePageMenuSection", this));

			// Places
			ViewModel placesViewModel = new ViewModel();
			placesViewModel.setModel(impression);
			Places places = impression.getPlaces();
			Places placesOrderedByName = places.getPlacesOrderedByName(true);
			placesViewModel.setEntities(placesOrderedByName);

			View placesView = new View();
			placesView.setWicketId("placeTableSection");
			//placesView.setContextPage(this);
			placesView.setPage(this);

			EntityDisplayTablePanel placeTableSection = new EntityDisplayTablePanel(
					placesViewModel, placesView);
			add(placeTableSection);

			// Sign in
			ViewModel signinViewModel = new ViewModel();
			signinViewModel.setModel(impression);
			Travelers travelers = impression.getTravelers();
			signinViewModel.setEntities(travelers);
			signinViewModel.setEntity(new Traveler(travelers.getModel()));

			View signinView = new View();
			signinView.setWicketId("signinSection");

			Panel signinPanel = new SigninPanel(signinViewModel, signinView);
			add(signinPanel);
			add(new FeedbackPanel("signinFeedback"));
			AppSession appSession = getAppSession();
			if (!travel.getDomainConfig().isSignin()) {
				signinPanel.setVisible(false);
			} else if (appSession.isUserSignedIn()) {
				signinPanel.setVisible(false);
			}
		} catch (Exception e) {
			log.error("Error in HomePage: " + e.getMessage());
		}
	}

}
