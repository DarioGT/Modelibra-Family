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
package org.modelibra.wicket.security.registration;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.modelibra.IEntity;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.security.AccessPoint;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * RegisteredPanel generic component used as default in
 * RegistrationConfirmationPage in case user is registered properly.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
@SuppressWarnings("serial")
public class RegisteredPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public RegisteredPanel(ViewModel viewModel, View view) {
		super(view.getWicketId());
		final IEntity<?> signinEntity = viewModel.getEntity();
		IModel entityModel = new Model(signinEntity);
		Label welvomeLabel = new Label("messageLabel", new StringResourceModel(
				"message", this, entityModel));
		add(welvomeLabel);

		Link siginLink = new Link("signinLink") {
			@Override
			public void onClick() {
				getAppSession().authenticate(signinEntity, AccessPoint.CODE,
						AccessPoint.PASSWORD);
				setResponsePage(getApplication().getHomePage());
			}
		};
		add(siginLink);
	}

}
