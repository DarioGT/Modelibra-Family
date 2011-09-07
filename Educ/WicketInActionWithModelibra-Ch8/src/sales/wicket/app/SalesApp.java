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
package sales.wicket.app;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.modelibra.wicket.app.DomainApp;

import sales.PersistentSales;
import sales.Sales;
import sales.SalesConfig;
import sales.wicket.cheesestore.cheeses.CheeseListPage;

/**
 * Sales domain web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public class SalesApp extends DomainApp {

	/**
	 * Constructs the domain web application.
	 */
	public SalesApp() {
		super(new PersistentSales(
				new Sales(new SalesConfig().getDomainConfig())));
	}

	/**
	 * Gets the Sales domain.
	 * 
	 * @return Sales domain
	 */
	public Sales getSales() {
		return (Sales) getDomain();
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public static SalesApp get() {
		return (SalesApp) Application.get();
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new SalesAppSession(request);
	}

	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("/cheese", CheeseListPage.class);
	}

}
