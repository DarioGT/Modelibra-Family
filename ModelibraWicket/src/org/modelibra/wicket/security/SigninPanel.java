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
package org.modelibra.wicket.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Signin panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class SigninPanel extends Panel {

	private static final long serialVersionUID = 106030L;

	private static Log log = LogFactory.getLog(SigninPanel.class);

	private String codePropertyCode;

	private String passwordPropertyCode;

	/**
	 * Constructs a login panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public SigninPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		codePropertyCode = (String) viewModel.getUserProperties()
				.getUserProperty(AccessPoint.CODE);
		if (codePropertyCode == null) {
			codePropertyCode = AccessPoint.CODE;
		}
		passwordPropertyCode = (String) viewModel.getUserProperties()
				.getUserProperty(AccessPoint.PASSWORD);
		if (passwordPropertyCode == null) {
			passwordPropertyCode = AccessPoint.PASSWORD;
		}
		IEntity<?> signinEntity = viewModel.getEntity();
		add(new SigninForm("signinForm", signinEntity));
	}

	/**
	 * Signin form.
	 */
	private class SigninForm extends Form {

		static final long serialVersionUID = 220151L;

		/**
		 * Constructs a sign in form.
		 * 
		 * @param wicketId
		 *            Wicket id
		 * @param signinEntity
		 *            signin entity
		 */
		public SigninForm(final String wicketId, final IEntity<?> signinEntity) {
			super(wicketId, new Model(signinEntity));

			add(new TextField("code", new PropertyModel(signinEntity,
					codePropertyCode)));
			add(new PasswordTextField("password", new PropertyModel(
					signinEntity, passwordPropertyCode)));
		}

		/**
		 * Submits a user action.
		 */
		protected void onSubmit() {
			AppSession appSession = (AppSession) getSession();
			DomainApp app = (DomainApp) getApplication();
			if (appSession.isUserSignedIn()) {
				setResponsePage(app.getHomePage());
			} else if (appSession.authenticate((IEntity<?>) getModelObject(),
					codePropertyCode, passwordPropertyCode)) {
				if (!continueToOriginalDestination()) {
					setResponsePage(app.getHomePage());
				}
			} else {
				error(LocalizedText.getSignInErrorMessage(this));
			}
		}
	}

}
