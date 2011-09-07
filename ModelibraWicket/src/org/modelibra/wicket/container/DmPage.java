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
package org.modelibra.wicket.container;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.html.WebPage;
import org.modelibra.IEntities;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;

/**
 * Dm page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
@SuppressWarnings("serial")
public abstract class DmPage extends WebPage {

	/**
	 * Constructs a dm page.
	 */
	public DmPage() {
		super();
	}

	/**
	 * Gets an application session.
	 * 
	 * @return application session
	 */
	public AppSession getAppSession() {
		return (AppSession) getSession();
	}

	/**
	 * Gets server path.
	 * 
	 * @return server path
	 */
	public String getServerPath() {
		String serverPath = "";

		HttpServletRequest req = getWebRequestCycle().getWebRequest()
				.getHttpServletRequest();

		String scheme = req.getScheme(); // http
		String serverName = req.getServerName(); // localhost
		int serverPort = req.getServerPort(); // 8081

		// i.e http://localhost:8081
		serverPath += scheme + "://" + serverName + ":" + serverPort;
		return serverPath;
	}

	/**
	 * Gets application context path.
	 * 
	 * @return application context path
	 */
	public String getAppContextPath() {
		String appContextPath = "";

		HttpServletRequest req = getWebRequestCycle().getWebRequest()
				.getHttpServletRequest();

		String contextPath = req.getContextPath(); // /ModelibraWicketApp
		String servletPath = req.getServletPath(); // /app

		// i.e http://localhost:8081/ModelibraWicketApp/app/
		appContextPath += getServerPath() + contextPath + servletPath;
		return appContextPath;
	}

	/**
	 * Adds an error based on the error key.
	 * 
	 * @param key
	 *            error key
	 */
	protected void addErrorByKey(String key) {
		String validationError = LocalizedText.getText(this, key);
		error(validationError);
	}

	/**
	 * Adds errors based on error keys.
	 * 
	 * @param entities
	 *            entities
	 */
	protected void addErrorsByKeys(IEntities<?> entities) {
		List<String> errorKeys = entities.getErrors().getKeyList();
		for (String errorKey : errorKeys) {
			String errorMsg = LocalizedText.getErrorMessage(this, errorKey);
			error(errorMsg);
		}
	}

	/**
	 * Adds dmLite errors.
	 * 
	 * @param entities
	 *            entities
	 */
	protected void addErrors(IEntities<?> entities) {
		List<String> errorMsgs = entities.getErrors().getErrorList();
		for (String errorMsg : errorMsgs) {
			error(errorMsg);
		}
	}

}
