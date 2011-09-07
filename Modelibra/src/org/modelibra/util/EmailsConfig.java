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

import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;

/**
 * Emails configuration.
 * 
 * @version 2008-10-16
 * @author Dzenan Ridjanovic
 */
public class EmailsConfig extends Entities<EmailConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 5020L;

	private XmlEmailsConfig xmlEmails;

	/**
	 * Constructs email configurations using the email configuration path.
	 * 
	 * @param emailConfigFilePath
	 *            email configuration file path
	 */
	public EmailsConfig(String emailConfigFilePath) {
		super();
		xmlEmails = new XmlEmailsConfig(this);
		configureEmail(emailConfigFilePath);
	}

	/**
	 * Configures email using the email configuration path.
	 * 
	 * @param emailConfigFilePath
	 *            email configuration file path
	 */
	private void configureEmail(String emailConfigFilePath) {
		xmlEmails.setDataFilePath(emailConfigFilePath);
		load();
	}

	/**
	 * Gets the first email configuration.
	 * 
	 * @return first email config
	 */
	public EmailConfig getFirstEmailConfig() {
		return first();
	}

	/**
	 * Gets the last email configuration.
	 * 
	 * @return last email config
	 */
	public EmailConfig getLastEmailConfig() {
		return last();
	}

	/**
	 * Gets the (last) email configuration.
	 * 
	 * @return default email configuration
	 */
	public EmailConfig getEmailConfig() {
		return getLastEmailConfig();
	}

	/**
	 * Gets XML email configurations.
	 * 
	 * @return XML email configurations
	 */
	public XmlEmailsConfig getXmlEmails() {
		return xmlEmails;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlEmails.getPersistentModel();
	}

	/**
	 * Gets the entities.
	 * 
	 * @return entities
	 */
	public IEntities<?> getEntities() {
		return xmlEmails.getEntities();
	}

	/**
	 * Loads the entities.
	 */
	public void load() {
		xmlEmails.load();
	}

	/**
	 * Saves the entities.
	 */
	public void save() {
		xmlEmails.save();
	}

}
