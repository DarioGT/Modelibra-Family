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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.modelibra.exception.ModelibraException;
import org.modelibra.util.EmailConfig;

/**
 * Email message form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-05-15
 */
public class EmailMessageForm extends Form {

	private static final long serialVersionUID = 108010L;

	private static Log log = LogFactory.getLog(EmailMessageForm.class);

	private TextField subjectField;

	private TextArea messageArea;

	private String subject = "info";

	private String message = "";

	private Label errorLabel;

	private Label messageLabel;

	private EmailConfig emailConfig;

	private List emails;

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
	public EmailMessageForm(final String wicketId, final String title,
			final EmailConfig emailConfig, final List emails) {
		super(wicketId, new Model());
		this.emailConfig = emailConfig;
		this.emails = emails;
		add(new Label("title", title));
		subjectField = new TextField("subject", new Model(subject));
		add(subjectField);
		messageArea = new TextArea("message", new Model(message));
		add(messageArea);
		errorLabel = new Label("errorLabel", "");
		add(errorLabel);
		messageLabel = new Label("errorMessage", "");
		add(messageLabel);
	}

	/**
	 * Submits a user action.
	 */
	protected void onSubmit() {
		subject = subjectField.getModelObjectAsString();
		message = messageArea.getModelObjectAsString();
		sendMessage();
	}

	/**
	 * Sends an email message to multiple email addresses.
	 */
	private void sendMessage() {
		try {
			errorLabel.setModelObject("");
			messageLabel.setModelObject("");
			String emailAddress;
			for (Iterator x = emails.iterator(); x.hasNext();) {
				emailAddress = (String) x.next();
				emailConfig.send(emailAddress, subject, message);
			}
		} catch (ModelibraException e) {
			errorLabel.setModelObject("Error");
			messageLabel.setModelObject(e.getMessage());
		}
	}

}
