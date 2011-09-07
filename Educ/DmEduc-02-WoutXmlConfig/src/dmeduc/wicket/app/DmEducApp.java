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
package dmeduc.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import dmeduc.DmEduc;
import dmeduc.DmEducReusableConfig;
import dmeduc.PersistentDmEduc;

/**
 * DmEduc domain web application.
 * 
 * @author unknown
 * @version 2008-11-25
 */
public class DmEducApp extends DomainApp {

	/**
	 * Constructs the domain web application.
	 */
	public DmEducApp() {
		super(new PersistentDmEduc(new DmEduc(new DmEducReusableConfig()
				.getReusableDomainConfig())));
	}

	/**
	 * Gets the DmEduc domain.
	 * 
	 * @return DmEduc domain
	 */
	public DmEduc getDmEduc() {
		return (DmEduc) super.getDomain();
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}
