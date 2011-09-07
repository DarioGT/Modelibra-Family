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
 * Source (code) generation is based on the configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-23
 */
public class SourceDirectoryGenerator extends Generator {

	private String sourceDirectoryPath;

	/**
	 * Constructs source directory generator.
	 * 
	 * @param config
	 *            configuration
	 */
	public SourceDirectoryGenerator(Config config) {
		super();
		sourceDirectoryPath = config.getSourceDirectoryPath();
		createSourceDirectory(sourceDirectoryPath);
	}

	/**
	 * Creates the source code directory where the source code will be
	 * generated.
	 * 
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	private void createSourceDirectory(String sourceDirectoryPath) {
		File sourceDirectory = new File(sourceDirectoryPath);
		if (!sourceDirectory.exists()) {
			sourceDirectory.mkdir();
		}
	}

	/**
	 * Gets source directory path.
	 * 
	 * @return source directory path
	 */
	public String getSourceDirectoryPath() {
		return sourceDirectoryPath;
	}

}
