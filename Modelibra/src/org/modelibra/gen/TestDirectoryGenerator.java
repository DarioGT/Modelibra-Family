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
package org.modelibra.gen;

import java.io.File;

import org.modelibra.config.Config;

/**
 * Test generation is based on the configuration. Test code is generated in the
 * test directory.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-23
 */
public class TestDirectoryGenerator extends Generator {

	private String testDirectoryPath;

	/**
	 * Constructs test directory generator.
	 * 
	 * @param config
	 *            configuration
	 */
	public TestDirectoryGenerator(Config config) {
		super();
		testDirectoryPath = config.getTestDirectoryPath();
		createTestDirectory(testDirectoryPath);
	}

	/**
	 * Creates the test code directory where the test code will be generated.
	 * 
	 * @param testDirectoryPath
	 *            test directory path
	 */
	private void createTestDirectory(String testDirectoryPath) {
		File testDirectory = new File(testDirectoryPath);
		if (!testDirectory.exists()) {
			testDirectory.mkdir();
		}
	}

	/**
	 * Gets test directory path.
	 * 
	 * @return test directory path
	 */
	public String getTestDirectoryPath() {
		return testDirectoryPath;
	}

}
