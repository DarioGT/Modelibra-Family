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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IEntity;
import org.modelibra.Oid;
import org.modelibra.exception.ModelibraException;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;

/**
 * Email configuration.
 * 
 * @version 2007-01-08
 * @author Dzenan Ridjanovic
 */
public class EmailConfig extends Entity<EmailConfig> implements
		IPersistentEntity {

	private static final long serialVersionUID = 5010L;

	private static Log log = LogFactory.getLog(EmailConfig.class);

	private String toSendEmail;

	private boolean toSend = true;

	private String from;

	private String outServer;

	private String password;

	private Emailer emailer = new Emailer();

	private XmlEmailConfig xmlEmail;

	/**
	 * Constructs an email configuration.
	 */
	public EmailConfig() {
		super();
		xmlEmail = new XmlEmailConfig(this);
	}

	/**
	 * Sets yes or no: yes, to send an email; no, not to send.
	 * 
	 * @param toSendEmail
	 *            yes or no, to send an email
	 */
	public void setToSendEmail(String toSendEmail) {
		this.toSendEmail = toSendEmail;
		if (toSendEmail != null) {
			if (toSendEmail.equalsIgnoreCase("yes")) {
				setToSend(true);
			} else {
				setToSend(false);
			}
		}
	}

	/**
	 * Gets yes or no: yes, to send an email; no, not to send.
	 * 
	 * @return yes or no, to send an email
	 */
	public String getToSendEmail() {
		return toSendEmail;
	}

	/**
	 * Sets <code>true</code> if an email should be sent.
	 * 
	 * @param toSend
	 *            <code>true</code> if an email should be sent
	 */
	public void setToSend(boolean toSend) {
		this.toSend = toSend;
	}

	/**
	 * Checks if an email should be sent.
	 * 
	 * @return <code>true</code> if an email should be sent
	 */
	public boolean getToSend() {
		return toSend;
	}

	/**
	 * Sets a user email account (code).
	 * 
	 * @param code
	 *            user mail account (code)
	 */
	public void setCode(String code) {
		super.setCode(code);
		emailer.setCode(code);
	}

	/**
	 * Sets a from email.
	 * 
	 * @param from
	 *            from email
	 */
	public void setFrom(String from) {
		this.from = from;
		emailer.setFrom(from);
	}

	/**
	 * Gets a from email.
	 * 
	 * @return from email
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets an outgoing email server.
	 * 
	 * @param outServer
	 *            outgoing email server
	 */
	public void setOutServer(String outServer) {
		this.outServer = outServer;
		emailer.setOutServer(outServer);
	}

	/**
	 * Gets an outgoing email server.
	 * 
	 * @return outgoing email server
	 */
	public String getOutServer() {
		return outServer;
	}

	/**
	 * Sets a user email account password.
	 * 
	 * @param password
	 *            user email account password
	 */
	public void setPassword(String password) {
		this.password = password;
		emailer.setPassword(password);
	}

	/**
	 * Gets a user email account password.
	 * 
	 * @return user email account password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Updates an email configuration (not allowed).
	 * 
	 * @param emailConfig
	 *            email configuration entity
	 * @return <code>true</code> if this entity is updated with a given entity
	 */
	public boolean update(EmailConfig emailConfig) {
		return false;
	}

	/**
	 * Sends an email.
	 * 
	 * @param to
	 *            email to address
	 * @param subject
	 *            email subject
	 * @param content
	 *            email content
	 * @throws dmLite
	 *             exception if there is a problem
	 */
	public void send(String to, String subject, String content)
			throws ModelibraException {
		if (getToSend()) {
			emailer.send(to, subject, content);
		}
	}

	/**
	 * Outputs email configuration information. Used in testing.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("--- " + title + " ---");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(To send email = " + getToSendEmail() + ")");
		log.info("(From email = " + getFrom() + ")");
		log.info("(Outgoing server = " + getOutServer() + ")");
		log.info("(Password = " + getPassword() + ")");
	}

	/**
	 * Gets the XML persistent email.
	 * 
	 * @return XML persistent email
	 */
	public XmlEmailConfig getXmlEmail() {
		return xmlEmail;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlEmail.getPersistentModel();
	}

	/**
	 * Gets the concept entity.
	 * 
	 * @return concept entity
	 */
	public IEntity<?> getEntity() {
		return xmlEmail.getEntity();
	}

	public static void main(String[] args) {
		try {
			Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
			log4jConfigurator.configure();

			EmailConfig emailConfig = new EmailConfig();
			emailConfig.setOid(new Oid(Long.valueOf("1")));
			emailConfig.setCode("vlgiiora");
			emailConfig.setToSendEmail("yes");
			emailConfig.setFrom("dzenan.ridjanovic@videotron.ca");
			emailConfig.setOutServer("relais.videotron.ca");
			emailConfig.setPassword("xxxxxxxx");

			// emailConfig.output("Dzenan Ridjanovic Email Configuration");

			String to = "dzenan.ridjanovic@fsa.ulaval.ca";
			String subject = "org.dzenanr.utils.Emailer: javamail test";
			String content = "It works!";
			emailConfig.send(to, subject, content);
			log.info(to + " -- " + subject + ": " + content);
		} catch (ModelibraException e) {
			log.error("Error in EmailConfig.main: " + e.getMessage());
		}
	}

}
