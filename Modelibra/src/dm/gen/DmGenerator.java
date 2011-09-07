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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.DomainConfig;
import org.modelibra.gen.ModelibraGenerator;

/**
 * Generates the Dm domain and its Wicket application based on the domain
 * configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-02
 */
public class DmGenerator {

	private static final String DOMAIN_CODE = "Dm";

	private static final String DOMAIN_TYPE = "Modelibra";

	private static Log log = LogFactory.getLog(DmGenerator.class);

	public static void main(String[] args) {
		DmConfig dmConfig = new DmConfig(DOMAIN_CODE, DOMAIN_TYPE);
		DomainConfig domainConfig = dmConfig.getDomainConfig();
		ModelibraGenerator generator = new ModelibraGenerator(domainConfig);
		generator.generate();
	}

}
