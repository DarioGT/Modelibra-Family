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
package dm.gen;

import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;

/**
 * Provides the dm configuration. The dmConfig.properties file must be included
 * in the same directory.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-02
 */
public class DmConfig extends Config {

	private DomainConfig domainConfig;

	/**
	 * Creates the dm configuration.
	 * 
	 * @param domainCode
	 *            domain code
	 * @param domainType
	 *            domain type
	 */
	public DmConfig(String domainCode, String domainType) {
		super();
		domainConfig = getDomainConfig(domainCode, domainType);
	}

	/**
	 * Gets the domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

}
