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
package education;

import org.modelibra.config.DomainConfig;

/**
 * Creates domain and its models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Education extends GenEducation {

	private static final long serialVersionUID = 1233251484876L;

	/**
	 * Creates the Education domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public Education(DomainConfig domainConfig) {
		super(domainConfig);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}