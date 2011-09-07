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

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.ModelibraException;

/**
 * Emailer to send an email.
 * 
 * @version 2006-01-25
 * @author Dzenan Ridjanovic
 */
public class Emailer {

	protected static Log log = LogFactory.getLog(Emailer.class);

	private String from;

	private String to;

	private String subject;

	private String content;

	private String outServer;

	private String code;

	private String password;

	private Properties emailProperties = new Properties();

	private Session emailSession;

	private Store emailStore;

	/**
	 * Sets a from address.
	 * 
	 * @param from
	 *            from address
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Sets a to address.
	 * 
	 * @param to
	 *            to address
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Sets a message subject.
	 * 
	 * @param subject
	 *            message subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Sets a message content.
	 * 
	 * @param content
	 *            message content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Sets an outgoing server.
	 * 
	 * @param outServer
	 *            outgoing server
	 */
	public void setOutServer(String outServer) {
		try {
			this.outServer = outServer;
			emailProperties.put("mail.smtp.host", outServer);
			emailSession = Session.getInstance(emailProperties);
			emailStore = emailSession.getStore("pop3");
		} catch (NoSuchProviderException e) {
			log.error("Email error : " + e.getMessage());
		}
	}

	/**
	 * Sets a user code.
	 * 
	 * @param code
	 *            user code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets a user password.
	 * 
	 * @param password
	 *            user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sends an email.
	 * 
	 * @throws dmLite
	 *             exception if there is a problem
	 */
	public void send() throws ModelibraException {
		try {
			MimeMessage message = new MimeMessage(emailSession);
			InternetAddress fromIA = new InternetAddress(from);
			message.setFrom(fromIA);
			InternetAddress toIA = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toIA);
			message.setSubject(subject);
			message.setText(content);
			emailStore.connect(outServer, code, password);
			Transport.send(message);
			emailStore.close();
		} catch (MessagingException e) {
			throw new ModelibraException("Could not send an email: " + e.getMessage());
		} catch (IllegalStateException e) {
			throw new ModelibraException("Could not send an email: " + e.getMessage());
		}
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
		setTo(to);
		setSubject(subject);
		setContent(content);
		send();
	}

	/**
	 * Prepares default parameters.
	 */
	private void prepareDefaults() {
		// Universite Laval
		/*
		 * setFrom("dzenan.ridjanovic@sio.ulaval.ca");
		 * setOutServer("hermes.ulaval.ca"); setCode("ridjanod");
		 * setPassword("xxxxxxxx");
		 */

		// Videotron
		setFrom("dzenan.ridjanovic@videotron.ca");
		setOutServer("relais.videotron.ca");
		setCode("vlgiiora");
		setPassword("xxxxxxxx");
	}

	public static void main(String[] args) {
		try {
			Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
			log4jConfigurator.configure();

			Emailer emailer = new Emailer();
			emailer.prepareDefaults();
			String to = "dzenan.ridjanovic@fsa.ulaval.ca";
			String subject = "org.dzenanr.utils.Emailer: javamail test";
			String content = "It works!";
			emailer.send(to, subject, content);
			log.info(to + " -- " + subject + ": " + content);
		} catch (ModelibraException e) {
			log.error("Error in Emailer.main: " + e.getMessage());
		}
	}

}