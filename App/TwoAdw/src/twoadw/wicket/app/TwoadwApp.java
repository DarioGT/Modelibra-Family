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
package twoadw.wicket.app;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.modelibra.wicket.app.DomainApp;

import twoadw.Twoadw;
import twoadw.TwoadwConfig;
import twoadw.PersistentTwoadw;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.generic.informationpages.InformationPageView;
import twoadw.wicket.website.productcategories.CategoryListPage;

/**
 * Twoadw domain web application.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class TwoadwApp extends DomainApp {

	/**
	 * Constructs the domain web application.
	 */
	public TwoadwApp() {
		super(new PersistentTwoadw(new Twoadw(new TwoadwConfig().getDomainConfig())));
	}

	/**
	 * Gets the Twoadw domain.
	 * 
	 * @return Twoadw domain
	 */
	public Twoadw getTwoadw() {
		return (Twoadw) super.getDomain();
	}
	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	public static TwoadwApp get() {
		return (TwoadwApp) Application.get();
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new TwoadwAppSession(request);
	}
	
	
	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("/twoadw", CategoryListPage.class);
		mountBookmarkablePage("/checkout", CheckoutPage.class);
	}
}
