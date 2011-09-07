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
package org.modelibra.wicket.util;

import java.util.List;

import org.modelibra.IDomainModel;
import org.modelibra.util.EmailConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.security.SecureWebPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Email message page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public final class EmailMessagePage extends SecureWebPage {

	private static final long serialVersionUID = 108020L;

	/**
	 * Constructs an email message page.
	 * 
	 * @param model
	 *            domain model
	 * @param title
	 *            title
	 * @param emailConfig
	 *            email configuration
	 * @param emails
	 *            email list
	 */
	public EmailMessagePage(final IDomainModel model, final String title,
			final EmailConfig emailConfig, final List emails) {
		DomainApp app = (DomainApp) getApplication();

		ViewModel menuModelContext = new ViewModel();
		menuModelContext.setModel(model);

		View menuViewContext = new View();
		menuViewContext.setWicketId("dmMenuPanel");

		String modelCode = model.getModelConfig().getCode();
		add(app.getViewMeta(modelCode).getDmMenuPanel(menuModelContext,
				menuViewContext));

		add(new EmailMessagePanel("emailMessagePanel", title, emailConfig,
				emails));
	}

}
