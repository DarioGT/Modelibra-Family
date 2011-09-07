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

import org.modelibra.config.DomainConfig;
import org.modelibra.exception.ConfigRuntimeException;

/**
 * Generates the domain based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-23
 */
public class ModelibraGenerator extends Generator {

	private String authors;

	private String sourceDirectoryPath;

	private String testDirectoryPath;

	private DomainGenerator domainGenerator;

	/**
	 * Constructs the generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public ModelibraGenerator(DomainConfig domainConfig) {
		if (domainConfig == null) {
			throw new ConfigRuntimeException("There is no domain configuration.");
		}
		authors = generateDomainAuthors(domainConfig);
		sourceDirectoryPath = generateSourceDirectory(domainConfig);
		testDirectoryPath = generateTestDirectory(domainConfig);
		domainGenerator = new DomainGenerator(domainConfig, authors,
				sourceDirectoryPath, testDirectoryPath);
	}

	/**
	 * Generates domain authors from model authors.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @return domain authors
	 */
	private String generateDomainAuthors(DomainConfig domainConfig) {
		DomainAuthorsGenerator domainAuthorsGenerator = new DomainAuthorsGenerator(
				domainConfig);
		return domainAuthorsGenerator.getAuthors();
	}

	/**
	 * Generates source (code) directory path if it does not exist.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @return source directory path
	 */
	private String generateSourceDirectory(DomainConfig domainConfig) {
		SourceDirectoryGenerator codeDirectoryGenerator = new SourceDirectoryGenerator(
				domainConfig.getConfig());
		return codeDirectoryGenerator.getSourceDirectoryPath();
	}

	/**
	 * Generates test directory path if it does not exist.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @return test directory path
	 */
	private String generateTestDirectory(DomainConfig domainConfig) {
		TestDirectoryGenerator testDirectoryGenerator = new TestDirectoryGenerator(
				domainConfig.getConfig());
		return testDirectoryGenerator.getTestDirectoryPath();
	}

	/**
	 * Get authors.
	 * 
	 * @return authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Get source directory path.
	 * 
	 * @return source directory path
	 */
	public String getSourceDirectoryPath() {
		return sourceDirectoryPath;
	}

	/**
	 * Get test directory path.
	 * 
	 * @return test directory path
	 */
	public String getTestDirectoryPath() {
		return testDirectoryPath;
	}

	/**
	 * Get domain generator.
	 * 
	 * @return domain generator
	 */
	public DomainGenerator getDomainGenerator() {
		return domainGenerator;
	}

	/**
	 * Generates all.
	 */
	public void generate() {
		domainGenerator.generate();
	}

	/**
	 * Generates all generic classes.
	 */
	public void generateGenClasses() {
		domainGenerator.generateGenClasses();
	}

}
