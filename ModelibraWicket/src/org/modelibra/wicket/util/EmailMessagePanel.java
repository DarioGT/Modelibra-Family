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

import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.util.EmailConfig;

/**
 * Email message panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-05-15
 */
public class EmailMessagePanel extends Panel {

	private static final long serialVersionUID = 108030L;

	/**
	 * Constructs an email message form.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param title
	 *            title
	 * @param emailConfig
	 *            email configuration
	 * @param emails
	 *            email list
	 */
	public EmailMessagePanel(final String wicketId, final String title,
			final EmailConfig emailConfig, final List emails) {
		super(wicketId);
		add(new EmailMessageForm("emailMessageForm", title, emailConfig, emails));
	}

}
