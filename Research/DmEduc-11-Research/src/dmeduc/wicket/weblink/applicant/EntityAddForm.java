/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package dmeduc.wicket.weblink.applicant;

import org.apache.wicket.PageParameters;
import org.modelibra.config.DomainConfig;
import org.modelibra.util.EmailConfig;
import org.modelibra.wicket.security.AccessPoint;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.applicant.Applicant;
import dmeduc.weblink.applicant.Applicants;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.app.home.HomePage;

/**
 * Applicant add form for Sign Up.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-13
 */
@SuppressWarnings("serial")
public class EntityAddForm extends org.modelibra.wicket.concept.EntityAddForm {

	/**
	 * Constructs an entity add form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityAddForm(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

	/**
	 * Submits a user action.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	protected void onSubmit(final ViewModel viewModel, final View view) {
		super.onSubmit(viewModel, view);
		Applicant applicant = (Applicant) viewModel.getEntity();
		WebLink webLink = (WebLink) viewModel.getModel();
		Applicants applicants = webLink.getApplicants();
		if (applicants.contain(applicant)) {
			sendEmailToConfirm(viewModel);
			signOutGest();
			setResponsePage(HomePage.class);
		}
	}

	/**
	 * Cancels a user action.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	protected void onCancel(final ViewModel viewModel, final View view) {
		super.onCancel(viewModel, view);
		signOutGest();
		setResponsePage(HomePage.class);
	}

	/**
	 * Signs out a guest user.
	 */
	private void signOutGest() {
		if (getAppSession().isUserSignedIn()) {
			Member user = (Member) getAppSession().getSignedInUser();
			if (user.getRole().equals(AccessPoint.CASUAL)) {
				if (user.getCode().equals(AccessPoint.GUEST)
						&& user.getPassword().equals(AccessPoint.GUEST)) {
					getAppSession().signOutUser();
				}
			}
		}
	}

	/**
	 * Sends an email to confirm the registration.
	 * 
	 * @param viewModel
	 *            viewModel
	 */
	private void sendEmailToConfirm(final ViewModel viewModel) {
		DmEducApp app = (DmEducApp) getApplication();
		DomainConfig domainConfig = (DomainConfig) app.getDomain()
				.getDomainConfig();
		EmailConfig emailConfig = domainConfig.getConfig().getEmailConfig();

		String messageSubject = LocalizedText.getText(this,
				"signUp.message.subject");
		String messageStart = LocalizedText.getText(this,
				"signUp.message.start");
		Applicant applicant = (Applicant) viewModel.getEntity();

		String confirmationLink = getConfirmationLink(viewModel);

		applicant.emailMessage(emailConfig, messageSubject, messageStart + " "
				+ confirmationLink);
	}

	/**
	 * Gets confirmation link based on user oid.
	 * 
	 * @param viewModel
	 *            viewModel that contains applicant entity for which
	 *            confirmation link is created
	 * @return confirmation link
	 */
	private String getConfirmationLink(final ViewModel viewModel) {
		Applicant applicant = (Applicant) viewModel.getEntity();
		Long confirmationNubmer = applicant.getOid().getUniqueNumber();

		// get url with parameters for RegistrationConfirmationPage i.e.
		// confirmation/register/132156165116
		PageParameters pageParameters = new PageParameters();
		pageParameters.put("register", confirmationNubmer);
		CharSequence url = urlFor(ConfirmationPage.class, pageParameters);

		String appContextPath = (String) viewModel.getUserProperties()
				.getUserProperty("appContextPath");

		return appContextPath + url;
	}

}
