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

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

import education.library.Library;

/**
 * Education generated domain. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenEducation extends Domain {

	private static final long serialVersionUID = 1233251484875L;

	private Library library;

	/**
	 * Creates the Education domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenEducation(DomainConfig domainConfig) {
		super(domainConfig);
		library = new Library(this);
	}

	public Library getLibrary() {
		return library;
	}

}