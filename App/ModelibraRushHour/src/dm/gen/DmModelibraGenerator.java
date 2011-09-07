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
package dm.gen;

import org.modelibra.config.ConceptConfig;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.gen.DomainGenerator;
import org.modelibra.gen.DomainModelGenerator;
import org.modelibra.gen.ModelibraGenerator;

/**
 * Generates a domain based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-05
 */
public class DmModelibraGenerator {

	private DomainConfig domainConfig;

	private ModelibraGenerator modelibraGenerator;

	private String authors;

	private String sourceDirectoryPath;

	private String testDirectoryPath;

	/**
	 * Creates the dm Modelibra generator.
	 * 
	 * @param domainCode
	 *            domain code
	 * @param domainType
	 *            domain type
	 */
	public DmModelibraGenerator(String domainCode, String domainType) {
		DmConfig dmConfig = new DmConfig(domainCode, domainType);
		domainConfig = dmConfig.getDomainConfig();
		modelibraGenerator = new ModelibraGenerator(domainConfig);

		authors = modelibraGenerator.getAuthors();
		sourceDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		testDirectoryPath = modelibraGenerator.getTestDirectoryPath();
	}

	/**
	 * Gets domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

	/**
	 * Gets Modelibra generator.
	 * 
	 * @return Modelibra generator
	 */
	public ModelibraGenerator getModelibraGenerator() {
		return modelibraGenerator;
	}

	/**
	 * Gets authors.
	 * 
	 * @return authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Gets source directory path.
	 * 
	 * @return source directory path
	 */
	public String getSourceDirectoryPath() {
		return sourceDirectoryPath;
	}

	/**
	 * Generates Modelibra.
	 */
	public void generate() {
		modelibraGenerator.generate();
	}

	/**
	 * Generates Modelibra, but only generic classes (names start with Gen).
	 */
	public void generateModelibraGenClasses() {
		modelibraGenerator.generateGenClasses();
	}

	/**
	 * Generates Modelibra, but only partially if comments are used. Comment
	 * those generate methods that you do not need.
	 */
	public void generateModelibraPartially() {
		DomainGenerator domainGenerator = modelibraGenerator
				.getDomainGenerator();

		// domainGenerator.generateModelibraProperties();
		domainGenerator.generateDomainConfig();
		domainGenerator.generateGenDomain();
		domainGenerator.generateDomain();
		domainGenerator.generateDomainTest();
		domainGenerator.generatePersistentDomain();

		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			DomainModelGenerator modelGenerator = new DomainModelGenerator(
					modelConfig, sourceDirectoryPath, testDirectoryPath);
			modelGenerator.generateGenModel();
			modelGenerator.generateModel();
			modelGenerator.generateModelTest();

			for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
				modelGenerator.generateGenEntity(conceptConfig);
				modelGenerator.generateGenEntities(conceptConfig);
				modelGenerator.generateEntity(conceptConfig);
				modelGenerator.generateEntities(conceptConfig);
			}

			modelGenerator.generateEmptyXmlDataFiles();
		}
	}

}
