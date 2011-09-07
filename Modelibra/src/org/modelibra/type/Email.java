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
package org.modelibra.type;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.Transformer;

/**
 * Modelibra Email property validation type. Email type value must be a valid
 * email address. A valid email address must have at least two characters before
 * the @ sign (before text) and at least three characters after the sign (after
 * text). The after text must have at least one inner dot.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-28
 */
public class Email {

	private static Log log = LogFactory.getLog(Email.class);

	public static final int BEFORE_AT_TEXT_MIN_LENGTH = 2;

	public static final int AFTER_AT_TEXT_MIN_LENGTH = 3;

	private String emailAddress;

	/**
	 * Constructs an email address object.
	 * 
	 * @param emailAddress
	 *            email address
	 * @throws email
	 *             address exception if there is a problem
	 */
	public Email(String emailAddress) throws AddressException {
		if (validate(emailAddress)) {
			this.emailAddress = emailAddress;
		} else {
			throw new AddressException(emailAddress
					+ " is not a valid email address.");
		}
	}

	/**
	 * Provides an email address as a string.
	 * 
	 * @return email address string
	 */
	public String toString() {
		return emailAddress;
	}

	/**
	 * Validates an email address string. A valid email address must have at
	 * least two characters before the @ sign (before text) and at least three
	 * characters after the sign (after text). The after text must have at least
	 * one inner dot.
	 * 
	 * @param emailAddress
	 *            email address
	 * @return <code>true</code> if a valid email address
	 * @throws email
	 *             address exception if there is a problem
	 */
	private boolean validate(String emailAddress) throws AddressException {
		boolean result = false;
		if (emailAddress != null) {
			new InternetAddress(emailAddress);
			String[] atTokens = emailAddress.split("@");
			if (atTokens.length == 2) {
				String beforeAt = atTokens[0];
				String afterAt = atTokens[1];
				if (beforeAt.length() >= BEFORE_AT_TEXT_MIN_LENGTH
						&& afterAt.length() >= AFTER_AT_TEXT_MIN_LENGTH) {
					if (atLeastOneInnerDot(afterAt)) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Validates if after the @ sign text (after text) has at least one inner
	 * dot.
	 * 
	 * @param afterText
	 *            after the AT sign text
	 * @return <code>true</code> if after text has at least one inner dot
	 */
	private boolean atLeastOneInnerDot(String afterText) {
		boolean result = false;
		int firstDotIndex = afterText.indexOf(".");
		if (firstDotIndex > 0 && firstDotIndex < afterText.length() - 1) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks if two email addresses are equal.
	 * 
	 * @param value
	 *            email address
	 * @return <code>true</code> if two email addresses are equal
	 */
	public boolean equals(Object value) {
		if (value instanceof Email) {
			Email email = (Email) value;
			if (toString().equals(email.toString())) {
				return true;
			}
		} else if (value instanceof String) {
			String emailString = (String) value;
			try {
				Email email = Transformer.email(emailString);
				return equals(email);
			} catch (TypeRuntimeException e) {
				String msg = "Email.equals --email is not valid: "
						+ emailString;
				throw new TypeRuntimeException(msg, e);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();		
		// Email email0 = new Email("dd");
		// Email email1 = new Email("dzenan.ridjanovic@fsa.ulaval.ca");
		// Email email2 = new Email("");
		// Email email3 = new Email("dr@");
		// Email email4 = new Email("dr@a");
		// Email email5 = new Email("dr@a.a");
		// Email email6 = new Email("dr@a.ba");
		// Email email7 = new Email("dr@aba.");
		// Email email8 = new Email("dr@ab.a");
		// Email email9 = new Email(null);	
	}

}
