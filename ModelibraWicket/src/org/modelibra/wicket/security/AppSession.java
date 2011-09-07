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
package org.modelibra.wicket.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.modelibra.IDomain;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.DomainModel;
import org.modelibra.config.DomainConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.Clipboard;

/**
 * Authenticates users.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-11-20
 */
public class AppSession extends WebSession {

	private static final long serialVersionUID = 106010L;

	private static Log log = LogFactory.getLog(AppSession.class);

	private IEntity<?> signedInUser;

	private Clipboard clipboard = new Clipboard();

	/**
	 * Constructs the aplication session.
	 * 
	 * @param application
	 *            application
	 * @param request
	 *            request
	 */
	public AppSession(Request request) {
		super(request);
	}

	/**
	 * Authenticates the given user code and password, returning
	 * <code>true</code> if the user code and password are valid.
	 * 
	 * @param user
	 *            signed in user
	 * @param codePropertyCode
	 *            code property code
	 * @param passwordPropertyCode
	 *            password property code
	 * @return <code>true</code> if the user was authenticated
	 */
	public final boolean authenticate(IEntity<?> user, String codePropertyCode,
			String passwordPropertyCode) {
		boolean isValidUser = false;
		if (user != null) {
			DomainModel model = (DomainModel) user.getModel();
			Object userPasswordObject = model.getModelMeta().getProperty(user,
					passwordPropertyCode);
			if (userPasswordObject != null) {
				if (userPasswordObject instanceof String) {
					String userPassword = (String) userPasswordObject;
					if (userPassword != null) {
						DomainApp app = (DomainApp) getApplication();
						IDomain domain = app.getDomain();
						DomainConfig domainConfig = domain.getDomainConfig();
						String signinConcept = domainConfig.getSigninConcept();
						IEntities<?> validUsers = model.getEntry(signinConcept);
						if (validUsers == null) {
							IDomainModel referenceModel = domain.getReferenceModel();
							if (referenceModel != null) {
								if (referenceModel != model) {
									validUsers = referenceModel
											.getEntry(signinConcept);
								}
							} else {
								log
										.info(domainConfig.getCode()
												+ " domain does not have the sign in model.");
							}
						}
						if (validUsers != null) {
							Object userCodeObject = model.getModelMeta()
									.getProperty(user, codePropertyCode);
							if (userCodeObject != null) {
								if (userCodeObject instanceof String) {
									IEntity<?> validUser = validUsers
											.retrieveByProperty(
													codePropertyCode,
													userCodeObject);
									if (validUser != null) {
										Object validUserPasswordObject = model
												.getModelMeta().getProperty(
														validUser,
														passwordPropertyCode);
										if (validUserPasswordObject != null) {
											if (validUserPasswordObject instanceof String) {
												String validUserPassword = (String) validUserPasswordObject;
												if (validUserPassword != null
														&& validUserPassword
																.equals(userPassword)) {
													isValidUser = true;
													signedInUser = validUser;
												}
											}
										}
									}
								}
							}
						} else {
							log
									.info("There are no valid users to validate the password.");
						}
					}
				}
			}
		}
		return isValidUser;
	}

	/**
	 * Checks if the user is signed in.
	 * 
	 * @return <code>true</true> if the user is signed in
	 */
	public final boolean isUserSignedIn() {
		return signedInUser != null;
	}

	/**
	 * Gets a signed in entity.
	 * 
	 * @return signed in entity
	 */
	public final IEntity<?> getSignedInUser() {
		return signedInUser;
	}

	/**
	 * Sign out the current user.
	 */
	public final void signOutUser() {
		if (isUserSignedIn()) {
			signedInUser = null;
			invalidate();
		}
	}

	/**
	 * Gets a clipboard.
	 * 
	 * @return clipboard
	 */
	public Clipboard getClipboard() {
		return clipboard;
	}

}
