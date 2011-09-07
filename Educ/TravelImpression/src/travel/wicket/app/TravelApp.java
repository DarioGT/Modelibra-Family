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
package travel.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import travel.Travel;
import travel.TravelConfig;
import travel.PersistentTravel;

/**
 * Travel domain web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-02
 */
public class TravelApp extends DomainApp {

	/**
	 * Constructs the domain web application.
	 */
	public TravelApp() {
		super(new PersistentTravel(new Travel(new TravelConfig().getDomainConfig())));
	}

	/**
	 * Gets the Travel domain.
	 * 
	 * @return Travel domain
	 */
	public Travel getTravel() {
		return (Travel) super.getDomain();
	}
	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}
