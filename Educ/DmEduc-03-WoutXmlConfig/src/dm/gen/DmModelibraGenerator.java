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

import dmeduc.DmEducSpecificConfig;

/**
 * Generates a domain based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-12-02
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
		// DmConfig dmConfig = new DmConfig(domainCode, domainType);
		DmEducSpecificConfig dmConfig = new DmEducSpecificConfig();
		domainConfig = dmConfig.getSpecificDomainConfig();
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
	 * Gets test directory path.
	 * 
	 * @return test directory path
	 */
	public String getTestDirectoryPath() {
		return testDirectoryPath;
	}

	/**
	 * Generates Modelibra.
	 */
	public void generate() {
		modelibraGenerator.generate();
	}

	/**
	 * Generates domain Gen class.
	 */
	public void generateDomainGenClass() {
		modelibraGenerator.getDomainGenerator().generateGenDomain();
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
		domainGenerator.generatePersistentDomain();
		domainGenerator.generateDomain();
		domainGenerator.generateDomainTest();
		domainGenerator.generateSpecificDomainConfig();
		domainGenerator.generateGenDomain();

		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			DomainModelGenerator modelGenerator = new DomainModelGenerator(
					modelConfig, sourceDirectoryPath, testDirectoryPath);
			modelGenerator.generateModel();
			modelGenerator.generateModelTest();
			modelGenerator.generateGenModel();

			for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
				modelGenerator.generateEntity(conceptConfig);
				modelGenerator.generateEntities(conceptConfig);
				modelGenerator.generateEntitiesTest(conceptConfig);
				modelGenerator.generateGenEntity(conceptConfig);
				modelGenerator.generateGenEntities(conceptConfig);
			}

			modelGenerator.generateEmptyXmlDataFiles();
		}
	}

	/**
	 * Generates specific domain configuration.
	 */
	public void generateSpecificDomainConfig() {
		modelibraGenerator.getDomainGenerator().generateSpecificDomainConfig();
	}

	/**
	 * Generates all for a new model.
	 * 
	 * @param modelCode
	 *            model code
	 */
	public void generateModel(String modelCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, sourceDirectoryPath, testDirectoryPath);
		modelGenerator.generate();
	}

	/**
	 * Generates model Gen class.
	 * 
	 * @param modelCode
	 *            model code
	 */
	public void generateModelGenClass(String modelCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		String codeDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, codeDirectoryPath, testDirectoryPath);
		modelGenerator.generateGenModel();
	}

	/**
	 * Generates concept classes.
	 * 
	 * @param modelCode
	 *            model code
	 * @param conceptCode
	 *            concept code
	 */
	public void generateConceptClasses(String modelCode, String conceptCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		String codeDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, codeDirectoryPath, testDirectoryPath);
		ConceptConfig conceptConfig = modelConfig.getConceptConfig(conceptCode);
		modelGenerator.generateConcept(conceptConfig);
	}

	/**
	 * Generates concept Gen classes.
	 * 
	 * @param modelCode
	 *            model code
	 * @param conceptCode
	 *            concept code
	 */
	public void generateConceptGenClasses(String modelCode, String conceptCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		String codeDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, codeDirectoryPath, testDirectoryPath);
		ConceptConfig conceptConfig = modelConfig.getConceptConfig(conceptCode);
		modelGenerator.generateGenConcept(conceptConfig);
	}

	/**
	 * Generates entry concept empty XML data files for a model.
	 * 
	 * @param modelCode
	 *            model code
	 */
	public void generateEntryConceptEmptyXmlDataFiles(String modelCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		String codeDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, codeDirectoryPath, testDirectoryPath);
		modelGenerator.generateEmptyXmlDataFiles();
	}

	/**
	 * Generates entry concept empty XML data file for a concept.
	 * 
	 * @param modelCode
	 *            model code
	 * @param conceptCode
	 *            concept code
	 */
	public void generateEntryConceptEmptyXmlDataFile(String modelCode,
			String conceptCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		String codeDirectoryPath = modelibraGenerator.getSourceDirectoryPath();
		DomainModelGenerator modelGenerator = new DomainModelGenerator(
				modelConfig, codeDirectoryPath, testDirectoryPath);
		ConceptConfig conceptConfig = modelConfig.getConceptConfig(conceptCode);
		modelGenerator.generateEmptyXmlDataFile(conceptConfig);
	}

}
