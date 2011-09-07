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
package course;

import org.modelibra.config.DomainConfig;

import course.reference.Reference;

/**
 * Creates domain and its models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-25
 */
public class Course extends GenCourse {

	private static final long serialVersionUID = 1196702256689L;

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private Reference reference;

	/**
	 * Creates the Course domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public Course(DomainConfig domainConfig) {
		super(domainConfig);
		reference = new Reference(this);
	}

	public Reference getReference() {
		return reference;
	}

}