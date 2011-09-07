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

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

/* ======= import models ======= */

import course.lecture.Lecture;	
import course.reference.Reference;	

/**
 * Course generated domain. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenCourse extends Domain {

	private static final long serialVersionUID = 1196717863883L;
	
	private Lecture lecture;
		
	private Reference reference;
		
	/**
	 * Creates the Course domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenCourse(DomainConfig domainConfig) {
		super(domainConfig);
		lecture = new Lecture(this);		
		reference = new Reference(this);		
	}

	public Lecture getLecture() {
		return lecture;
	}
		
	public Reference getReference() {
		return reference;
	}
		
}