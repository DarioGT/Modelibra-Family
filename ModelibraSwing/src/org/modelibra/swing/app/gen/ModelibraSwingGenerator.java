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
package org.modelibra.swing.app.gen;

import org.modelibra.config.DomainConfig;
import org.modelibra.gen.Generator;

/**
 * Generates the Swing application based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-17
 */
public class ModelibraSwingGenerator extends Generator {

	private String sourceDirectoryPath;

	private StartGenerator startGenerator;

	private TextResGenerator textResGenerator;

	/**
	 * Constructs the generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public ModelibraSwingGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath) {
		this.sourceDirectoryPath = sourceDirectoryPath;

		startGenerator = new StartGenerator(domainConfig, sourceDirectoryPath);
		textResGenerator = new TextResGenerator(domainConfig,
				sourceDirectoryPath);
	}

	/**
	 * Constructs the generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 * @param minCodeGen
	 *            <code>true</code> if it is a minCodeGen
	 */
	public ModelibraSwingGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath, boolean minCodeGen) {
		this.sourceDirectoryPath = sourceDirectoryPath;

		startGenerator = new StartGenerator(domainConfig, sourceDirectoryPath);
		textResGenerator = new TextResGenerator(domainConfig,
				sourceDirectoryPath, minCodeGen);
	}

	/**
	 * Get code directory path.
	 * 
	 * @return code directory path
	 */
	public String getCodeDirectoryPath() {
		return sourceDirectoryPath;
	}

	/**
	 * Get Start generator.
	 * 
	 * @return Start generator
	 */
	public StartGenerator getStartGenerator() {
		return startGenerator;
	}

	/**
	 * Get TextRes generator.
	 * 
	 * @return TextRes generator
	 */
	public TextResGenerator getTextResGenerator() {
		return textResGenerator;
	}

	/**
	 * Generates all.
	 */
	public void generate() {
		startGenerator.generate();
		textResGenerator.generate();
	}

}
