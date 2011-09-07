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
package org.modelibra.wicket.app;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.settings.IRequestCycleSettings;
import org.apache.wicket.util.io.IObjectStreamFactory;
import org.apache.wicket.util.lang.Objects;
import org.modelibra.IDomain;
import org.modelibra.persistency.IPersistentDomain;
import org.modelibra.wicket.app.domain.DomainPage;
import org.modelibra.wicket.security.AccessPoint;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.security.AppSimplePageAuthorizationStrategy;
import org.modelibra.wicket.view.ViewMeta;

/**
 * Domain web application provides a secured access to domain data.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-09-29
 */
public class DomainApp extends WebApplication implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_LONG_TEXT_LENGTH = 128;

	public static final int AREA_COLUMN_SIZE = 64;

	private IPersistentDomain persistentDomain;

	private IDomain domain;

	private ViewMeta viewMeta;

	private AccessPoint accessPoint;

	/**
	 * Constructs a domain application.
	 */
	public DomainApp() {
		super();
	}

	/**
	 * Constructs a domain application.
	 * 
	 * @param persistentDomain
	 *            persistent domain
	 */
	public DomainApp(IPersistentDomain persistentDomain) {
		super();
		setPersistentDomain(persistentDomain);
		accessPoint = new AccessPoint();
	}

	/**
	 * Constructs a domain application.
	 * 
	 * @param persistentDomain
	 *            persistent domain
	 * @param codePropertyCode
	 *            code property code
	 * @param rolePropertyCode
	 *            role property code
	 */
	public DomainApp(IPersistentDomain persistentDomain,
			String codePropertyCode, String rolePropertyCode) {
		super();
		setPersistentDomain(persistentDomain);
		accessPoint = new AccessPoint(codePropertyCode, rolePropertyCode);
	}

	/**
	 * Initializes the application.
	 */
	protected void init() {
		getMarkupSettings().setStripWicketTags(true);
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

		/*
		 * Error with REDIRECT_TO_BUFFER, when the Administration menu is used
		 * in DmEduc-08 before Sign in: WicketMessage: Already redirecting to
		 * '?wicket:interface=:19::::'. Cannot redirect more than once Root
		 * cause: org.apache.wicket.WicketRuntimeException: Already redirecting
		 * to '?wicket:interface=:19::::'. Cannot redirect more than once at
		 * org.apache
		 * .wicket.protocol.http.BufferedWebResponse.redirect(BufferedWebResponse
		 * .java:98)
		 */
		/*
		 * getRequestCycleSettings().setRenderStrategy(
		 * IRequestCycleSettings.REDIRECT_TO_BUFFER);
		 */
		getRequestCycleSettings().setRenderStrategy(
				IRequestCycleSettings.REDIRECT_TO_RENDER);

		getSecuritySettings().setAuthorizationStrategy(
				new AppSimplePageAuthorizationStrategy(this));

		// Sets default page versioning to false.
		getPageSettings().setVersionPagesByDefault(false);

		// Avoids using complete serialization of pages.
		Objects
				.setObjectStreamFactory(new IObjectStreamFactory.DefaultObjectStreamFactory());
	}

	/**
	 * Sets the persistent domain.
	 * 
	 * @param persistentDomain
	 *            persistent domain
	 */
	public void setPersistentDomain(IPersistentDomain persistentDomain) {
		this.persistentDomain = persistentDomain;
		domain = persistentDomain.getDomain();
		viewMeta = new ViewMeta(domain);
		if (accessPoint == null) {
			accessPoint = new AccessPoint();
		}
	}

	/**
	 * Gets the application home page class.
	 * 
	 * @return home page class
	 */
	public Class<?> getHomePage() {
		return getHomePageClass();
	}

	/**
	 * Gets the application home page.
	 * 
	 * @return home page
	 */
	public Page getHomePagePage() {
		return getViewMeta().getHomePage();
	}

	/**
	 * Gets the application home page class.
	 * 
	 * @return home page class
	 */
	public Class<?> getHomePageClass() {
		return getViewMeta().getHomePageClass();
	}

	/**
	 * Gets the application sign in page class.
	 * 
	 * @return sign in page class
	 */
	public Class<?> getSigninPageClass() {
		return getViewMeta().getSigninPageClass();
	}

	/**
	 * Gets the application sign in page.
	 * 
	 * @return sign in page
	 */
	public Page getSigninPage() {
		return getViewMeta().getSigninPage();
	}

	/**
	 * Gets the application domain page class.
	 * 
	 * @return domain page class
	 */
	public Class<? extends Page> getDomainPageClass() {
		return DomainPage.class;
	}

	/**
	 * Gets the persistent domain.
	 * 
	 * @return persistent domain
	 */
	public IPersistentDomain getPersistentDomain() {
		return persistentDomain;
	}

	/**
	 * Saves the application models, if they are not configured for the default
	 * save.
	 */
	public void save() {
		persistentDomain.save();
	}

	/**
	 * Saves the application models, then closes them.
	 */
	public void finalize() {
		save();
		persistentDomain.close();
	}

	/**
	 * Gets the domain.
	 * 
	 * @return domain
	 */
	public IDomain getDomain() {
		return domain;
	}

	/**
	 * Gets the default view meta.
	 * 
	 * @return default view meta
	 */
	public ViewMeta getViewMeta() {
		return viewMeta;
	}

	/**
	 * Gets the view meta handling.
	 * 
	 * @return view meta
	 */
	public ViewMeta getViewMeta(String modelCode) {
		viewMeta.setModelCode(modelCode);
		return viewMeta;
	}

	/**
	 * Gets the access point.
	 * 
	 * @return access point
	 */
	public AccessPoint getAccessPoint() {
		return accessPoint;
	}

	/**
	 * Gets a new session.
	 * 
	 * @return new session
	 */
	@Override
	protected ISessionStore newSessionStore() {
		return new HttpSessionStore(this);
	}

	/**
	 * Gets a new session.
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return new session
	 */
	@Override
	public Session newSession(Request request, Response response) {
		return new AppSession(request);
	}

}
