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

import java.util.Iterator;

import org.dom4j.Element;
import org.modelibra.persistency.xml.XmlEntities;

/**
 * XML emails configuration.
 * 
 * Entitis are saved (loaded) to (from) an XML file.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class XmlEmailsConfig extends XmlEntities<EmailConfig> {

	private static final long serialVersionUID = 5060L;

	private EmailsConfig emailsConfig;

	/**
	 * Constructs the XML emails configuration.
	 * 
	 * @param emailsConfig
	 *            email configuration
	 */
	public XmlEmailsConfig(EmailsConfig emailsConfig) {
		super(emailsConfig, null);
		this.emailsConfig = emailsConfig;
	}

	/**
	 * Loads the root element.
	 * 
	 * @param root
	 *            root element
	 */
	public void load(Element root) {
		// Iterate through child elements of the root.
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element conceptElement = (Element) i.next();
			EmailConfig emailConfig = new EmailConfig();
			emailConfig.getXmlEmail().load(conceptElement);
			emailsConfig.add(emailConfig);
		}
	}

	/**
	 * Saves data to an XML file (not available).
	 */
	public void save() {

	}

}