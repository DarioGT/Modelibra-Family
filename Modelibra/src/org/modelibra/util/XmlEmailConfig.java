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
package org.modelibra.util;

import org.dom4j.Element;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.xml.XmlEntity;

/**
 * XML email configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlEmailConfig extends XmlEntity {

	private static final long serialVersionUID = 5050L;

	private EmailConfig emailConfig;

	/**
	 * Constructs the XML email configuration.
	 * 
	 * @param emailConfig
	 *            email configuration
	 */
	public XmlEmailConfig(EmailConfig emailConfig) {
		super(emailConfig, null);
		this.emailConfig = emailConfig;
	}

	/**
	 * Loads an XML element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		super.load(element);
		Element toSendEmailChild = element.element("toSendEmail");
		if (toSendEmailChild != null) {
			String toSendEmail = toSendEmailChild.getText().trim();
			emailConfig.setToSendEmail(toSendEmail);
		}

		Element fromChild = element.element("from");
		if (fromChild != null) {
			String from = fromChild.getText().trim();
			emailConfig.setFrom(from);
		}

		Element outServerChild = element.element("outServer");
		if (outServerChild != null) {
			String outServer = outServerChild.getText().trim();
			emailConfig.setOutServer(outServer);
		}

		Element passwordChild = element.element("password");
		if (passwordChild != null) {
			String password = passwordChild.getText().trim();
			emailConfig.setPassword(password);
		}
	}

	/**
	 * Fills an XML concept element (no action).
	 * 
	 * @param element
	 *            XML element
	 */
	protected void fill(Element element) {
		throw new PersistencyRuntimeException(
				"An email configuration cannot be saved.");
	}

}