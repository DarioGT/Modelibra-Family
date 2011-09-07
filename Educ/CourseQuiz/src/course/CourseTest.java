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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.config.DomainConfig;
import org.modelibra.util.OutTester;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class CourseTest {

	private static Log log = LogFactory.getLog(CourseTest.class);

	private static CourseTest courseTest;

	private Course course;

	private PersistentCourse persistentCourse;

	public CourseTest() {
		super();
		try {
			begin();
		} catch (Exception e) {
			log.error("Error in CourseTest(): " + e.getMessage());
		}
	}

	public static CourseTest getSingleton() {
		if (courseTest == null) {
			courseTest = new CourseTest();
		}
		return courseTest;
	}

	private void begin() throws Exception {
		CourseConfig courseConfig = new CourseConfig();
		DomainConfig domainConfig = courseConfig.getDomainConfig();
		course = new Course(domainConfig);
		persistentCourse = new PersistentCourse(course);
	}

	public Course getCourse() {
		return course;
	}

	private void end() {
		if (persistentCourse != null) {
			persistentCourse.close();
		}
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private void test01() {
		outputMessage("????????? test01: test name ?????????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		CourseTest courseTest = null;
		try {
			courseTest = new CourseTest();
			courseTest.test();
		} catch (Exception e) {
			log.error("Error in CourseTest.main: " + e.getMessage());
		} finally {
			courseTest.end();
		}
	}

}