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

import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * NotRegisteredPanel generic component used as default in
 * RegistrationConfirmationPage in case user registration failed.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class NotRegisteredPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public NotRegisteredPanel(ViewModel viewModel, View view) {
		super(view.getWicketId());
		// TODO change link to generic signup page when done
		add(new PageLink("homePageLink", getApplication().getHomePage()));
	}

}
