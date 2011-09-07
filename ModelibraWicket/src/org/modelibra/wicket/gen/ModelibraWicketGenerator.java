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
package org.modelibra.wicket.gen;

import org.modelibra.config.DomainConfig;
import org.modelibra.gen.Generator;

/**
 * Generates the Wicket application based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-26
 */
public class ModelibraWicketGenerator extends Generator {

	private String authors;

	private String sourceDirectoryPath;

	private DomainWicketGenerator domainWicketGenerator;

	/**
	 * Constructs the generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param authors
	 *            authors
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public ModelibraWicketGenerator(DomainConfig domainConfig, String authors,
			String sourceDirectoryPath) {
		this.authors = authors;
		this.sourceDirectoryPath = sourceDirectoryPath;

		domainWicketGenerator = new DomainWicketGenerator(domainConfig,
				authors, sourceDirectoryPath, false);
	}

	public ModelibraWicketGenerator(DomainConfig domainConfig, String authors,
			String sourceDirectoryPath, boolean minCodeGen) {
		this.authors = authors;
		this.sourceDirectoryPath = sourceDirectoryPath;

		domainWicketGenerator = new DomainWicketGenerator(domainConfig,
				authors, sourceDirectoryPath, minCodeGen);
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
	 * Get code directory path.
	 * 
	 * @return code directory path
	 */
	public String getCodeDirectoryPath() {
		return sourceDirectoryPath;
	}

	/**
	 * Get domain Wicket generator.
	 * 
	 * @return domain Wicket generator
	 */
	public DomainWicketGenerator getDomainWicketGenerator() {
		return domainWicketGenerator;
	}

	/**
	 * Generates all.
	 */
	public void generate() {
		domainWicketGenerator.generate();
	}

}
