package dmeduc.wicket.weblink.applicant;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.modelibra.IEntities;
import org.modelibra.wicket.security.registration.RegistrationConfirmationPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.wicket.app.DmEducApp;

/**
 * Confirmation page.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
@SuppressWarnings("serial")
public class ConfirmationPage extends RegistrationConfirmationPage {

	/**
	 * Constructs the Confirmation page.
	 * 
	 * @param pageParameters
	 *            page parameters
	 */
	public ConfirmationPage(final PageParameters pageParameters) {
		super(pageParameters);
	}

	/**
	 * Implementation of abstract method to provide applicant entities.
	 */
	protected IEntities<?> getApplicantEntities() {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		return dmEducApp.getDmEduc().getWebLink().getApplicants();
	}

	/**
	 * Get panel that will be used when user registration failed. Used to
	 * provide different panel than default.
	 */
	protected Component getComponentForNotRegistered() {
		View view = new View();
		view.setWicketId("confirmation");
		return new ApplicantNotRegisteredPanel(new ViewModel(), view);
	}

}
