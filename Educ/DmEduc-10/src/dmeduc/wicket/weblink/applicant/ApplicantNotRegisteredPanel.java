package dmeduc.wicket.weblink.applicant;

import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.wicket.app.DmEducApp;

/**
 * Panel that is used in ConfirmationPage when registration fails.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-11
 */
@SuppressWarnings("serial")
public class ApplicantNotRegisteredPanel extends DmPanel {

	/**
	 * Constructs the panel for not registered applicant.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ApplicantNotRegisteredPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		WebLink webLink = dmEducApp.getDmEduc().getWebLink();
		ViewModel signUpViewModel = new ViewModel(webLink);
		Members members = webLink.getMembers();
		signUpViewModel.setEntities(members);
		signUpViewModel.setEntity(new Member(members.getModel()));

		PageLink signUpLink = SignUpPage.link("signUpLink", signUpViewModel,
				view);
		add(signUpLink);
	}

}
