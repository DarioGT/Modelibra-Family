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
package course.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import course.Course;
import course.CourseConfig;
import course.PersistentCourse;

/**
 * Course domain web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class CourseApp extends DomainApp {

	/**
	 * Constructs the domain web application.
	 */
	public CourseApp() {
		super(new PersistentCourse(new Course(new CourseConfig().getDomainConfig())));
	}

	/**
	 * Gets the Course domain.
	 * 
	 * @return Course domain
	 */
	public Course getCourse() {
		return (Course) super.getDomain();
	}
	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}
