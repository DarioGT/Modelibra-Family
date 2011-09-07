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
package dm.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import dm.Dm;
import dm.DmConfig;
import dm.PersistentDm;

/**
 * Web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-05
 */
public class DmApp extends DomainApp {

	/**
	 * Constructs the web application.
	 */
	public DmApp() {
		super(new PersistentDm(new Dm(new DmConfig().getDomainConfig())));
	}

	/**
	 * Gets the Dm domain.
	 * 
	 * @return Dm domain
	 */
	public Dm getDm() {
		return (Dm) super.getDomain();
	}

}
