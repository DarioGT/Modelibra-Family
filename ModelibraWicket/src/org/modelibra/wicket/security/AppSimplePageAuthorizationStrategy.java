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

import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.modelibra.wicket.app.DomainApp;

/**
 * App simple page authorization strategy.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-11
 */
public class AppSimplePageAuthorizationStrategy extends
		SimplePageAuthorizationStrategy {

	private DomainApp app;

	/**
	 * Constructs an app simple page authorization strategy.
	 * 
	 * @param app
	 *            application
	 */
	public AppSimplePageAuthorizationStrategy(final DomainApp app) {
		super(ISimpleAuthorization.class, app.getSigninPageClass());
		this.app = app;
	}

	/**
	 * Checks if a user (member) is authorized.
	 * 
	 * @return <code>true</code> if a user is authorized
	 */
	protected boolean isAuthorized() {
		boolean authorized = true;
		if (app.getDomain().getDomainConfig().isSignin()) {
			if (((AppSession) Session.get()).isUserSignedIn()) {
				authorized = true;
			} else {
				authorized = false;
			}
		}
		return authorized;
	}

}
