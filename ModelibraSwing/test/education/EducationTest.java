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
import org.modelibra.config.ModelConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class EducationTest {
	
	private static EducationTest educationTest;

	private Education education;

	private PersistentEducation persistentEducation;
	
	private EducationTest() {
		super();
		open();
	}
	
	public static EducationTest getSingleton() {
		if (educationTest == null) {
			educationTest = new EducationTest();
		}
		return educationTest;
	}
	
	private void open() {
		EducationConfig educationConfig = new EducationConfig();
		DomainConfig domainConfig = educationConfig.getDomainConfig();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			modelConfig.setPersistenceRelativePath(modelConfig
					.getPersistenceRelativePath()
					+ EducationConfig.SEPARATOR
					+ educationConfig.getModelibraProperties().getTestDirectoryName());
		}
		education = new Education(domainConfig);
		persistentEducation = new PersistentEducation(education);
	}

	public Education getEducation() {
		return education;
	}
	
	public void close() {
		if (persistentEducation != null) {
			persistentEducation.close();
		}
	}
	
}