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
package sales;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

/* ======= import models ======= */

import sales.cheesestore.CheeseStore;	

/**
 * Sales generated domain. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenSales extends Domain {

	private static final long serialVersionUID = 1231088671635L;
	
	private CheeseStore cheeseStore;
		
	/**
	 * Creates the Sales domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenSales(DomainConfig domainConfig) {
		super(domainConfig);
		cheeseStore = new CheeseStore(this);		
	}

	public CheeseStore getCheeseStore() {
		return cheeseStore;
	}
		
}