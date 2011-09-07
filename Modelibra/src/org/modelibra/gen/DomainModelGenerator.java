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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.util.Transformer;

/**
 * Generates domain model directories, classes and data files from the model
 * configuration to the given source code directory.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-09-23
 */
public class DomainModelGenerator extends Generator {

	private static Log log = LogFactory.getLog(DomainModelGenerator.class);

	private ModelConfig modelConfig;

	private String sourceDirectoryPath;

	private String testDirectoryPath;

	private String dataDirectoryPath;

	private String testDataDirectoryPath;

	/**
	 * Constructs domain model generator.
	 * 
	 * @param modelConfig
	 *            model configuration
	 * @param sourceDirectoryPath
	 *            code directory path
	 */
	public DomainModelGenerator(ModelConfig modelConfig,
			String sourceDirectoryPath, String testDirectoryPath) {
		super();
		this.modelConfig = modelConfig;
		Config config = modelConfig.getDomainConfig().getConfig();
		this.sourceDirectoryPath = sourceDirectoryPath;
		this.testDirectoryPath = testDirectoryPath;
		createModelCodeDirectories();
		createModelTestDirectories();

		dataDirectoryPath = modelConfig.getDomainConfig().getConfig()
				.getDataDirectoryPath(modelConfig);
		createDataDirectories(dataDirectoryPath);
		testDataDirectoryPath = dataDirectoryPath + SEPARATOR
				+ config.getModelibraProperties().getTestDirectoryName();
		createTestDataDirectories(testDataDirectoryPath);
	}

	/***************************************************************************
	 * Generates directories for model, model test and model concepts, and empty
	 * xml files for concepts' data.
	 **************************************************************************/

	/**
	 * Gets model concept code directory path for a concept code.
	 * 
	 * @param conceptCode
	 *            concept code
	 * @return model concept code directory path
	 */
	private String getModelConceptCodeDirectoryPath(String conceptCode) {
		String modelPackageCode = modelConfig.getPackageCode();
		String modelPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(modelPackageCode);
		String conceptCodeAllLowerLetters = textHandler
				.allLettersToLower(conceptCode);

		String modelConceptCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
				+ modelPackageCodeWithSlash + SEPARATOR
				+ conceptCodeAllLowerLetters;
		return modelConceptCodeDirectoryPath;
	}

	/**
	 * Gets model concept test directory path for a concept code.
	 * 
	 * @param conceptCode
	 *            concept code
	 * @return model concept test directory path
	 */
	private String getModelConceptTestDirectoryPath(String conceptCode) {
		String modelPackageCode = modelConfig.getPackageCode();
		String modelPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(modelPackageCode);
		String conceptCodeAllLowerLetters = textHandler
				.allLettersToLower(conceptCode);

		String modelConceptTestDirectoryPath = testDirectoryPath + SEPARATOR
				+ modelPackageCodeWithSlash + SEPARATOR
				+ conceptCodeAllLowerLetters;
		return modelConceptTestDirectoryPath;
	}

	/**
	 * Creates directories for the model where the source code will be
	 * generated.
	 */
	private void createModelCodeDirectories() {
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			String conceptCode = conceptConfig.getCode();
			String modelConcepCodetDirectoryPath = getModelConceptCodeDirectoryPath(conceptCode);
			File conceptCodeDirectory = new File(modelConcepCodetDirectoryPath);
			if (!conceptCodeDirectory.exists()) {
				conceptCodeDirectory.mkdirs();
			}
		}
	}

	/**
	 * Creates directories for the model where the test code will be generated.
	 */
	private void createModelTestDirectories() {
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			String conceptCode = conceptConfig.getCode();
			String modelConceptTestDirectoryPath = getModelConceptTestDirectoryPath(conceptCode);
			File conceptTestDirectory = new File(modelConceptTestDirectoryPath);
			if (!conceptTestDirectory.exists()) {
				conceptTestDirectory.mkdirs();
			}
		}
	}

	/**
	 * Creates the data directories.
	 * 
	 * @param dataDirectoryPath
	 *            data directory path
	 */
	private void createDataDirectories(String dataDirectoryPath) {
		File dataDirectory = new File(dataDirectoryPath);
		if (!dataDirectory.exists()) {
			dataDirectory.mkdirs();
		}
	}

	/**
	 * Creates the test data directories.
	 * 
	 * @param testDataDirectoryPath
	 *            test data directory path
	 */
	private void createTestDataDirectories(String testDataDirectoryPath) {
		File testDataDirectory = new File(testDataDirectoryPath);
		if (!testDataDirectory.exists()) {
			testDataDirectory.mkdirs();
		}
	}

	/***************************************************************************
	 * Generates model.
	 **************************************************************************/

	/**
	 * Generates model, model concepts and entry concept data files.
	 */
	public void generate() {
		generateGenModel();
		generateModel();
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			generateConcept(conceptConfig);
		}
		if (modelConfig.isPersistent()
				&& modelConfig.getPersistenceType().equals("xml")) {
			generateEmptyXmlDataFiles();
			generateEmptyXmlTestDataFiles();
		}

		// generateModelTest();
	}

	/**
	 * Generates model generic classes.
	 */
	public void generateGenClasses() {
		generateGenModel();
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			generateGenConcept(conceptConfig);
		}
	}

	/***************************************************************************
	 * Generates classes for model, model test and model concepts, and empty xml
	 * files for concepts' data.
	 **************************************************************************/

	/**
	 * Generates generic model.
	 */
	public void generateGenModel() {
		try {
			String modelTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model";
			String modelTemplate = "GenModel.vm";
			String modelTemplatePath = modelTemplateLocalContext + SEPARATOR
					+ modelTemplate;

			String modelCode = modelConfig.getCode();
			String modelCodeJava = "Gen" + modelCode + ".java";
			String modelPackageCode = modelConfig.getPackageCode();
			String serialNumber = Transformer.string(modelConfig.getOid()
					.getUniqueNumber());
			List<ConceptConfig> entryConceptConfigList = modelConfig
					.getConceptsConfig().getEntryConceptConfigList();

			List<ConceptConfig> requiredGetterNonEntryConceptConfigList = modelConfig
					.getRequiredGetterNonEntryConceptConfigList();

			VelocityContext context = new VelocityContext();
			context.put("modelpackagecode", modelPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("ModelCode", modelCode);
			context.put("serialNumber", serialNumber);
			context.put("entryConceptConfigList", entryConceptConfigList);
			context.put("requiredGetterNonEntryConceptConfigList",
					requiredGetterNonEntryConceptConfigList);

			Template template = Velocity.getTemplate(modelTemplatePath);
			String modelPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(modelConfig.getPackageCode());
			String modelCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ modelPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					modelCodeDirectoryPath + SEPARATOR + modelCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(modelCodeJava + " generic model class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateGenModel: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates specific model.
	 */
	public void generateModel() {
		try {
			String modelTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model";
			String modelTemplate = "Model.vm";
			String modelTemplatePath = modelTemplateLocalContext + SEPARATOR
					+ modelTemplate;

			String modelCode = modelConfig.getCode();
			String modelCodeJava = modelCode + ".java";
			String modelPackageCode = modelConfig.getPackageCode();
			Long uniqueNumber = modelConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 1L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("modelpackagecode", modelPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("ModelCode", modelCode);
			context.put("serialNumber", serialNumber);

			Template template = Velocity.getTemplate(modelTemplatePath);
			String modelPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(modelConfig.getPackageCode());
			String modelCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ modelPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					modelCodeDirectoryPath + SEPARATOR + modelCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(modelCodeJava + " specific model class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateModel: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates model test.
	 */
	public void generateModelTest() {
		try {
			String modelTestTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model";
			String modelTestTemplate = "ModelTest.vm";
			String modelTestTemplatePath = modelTestTemplateLocalContext
					+ SEPARATOR + modelTestTemplate;

			DomainConfig domainConfig = modelConfig.getDomainConfig();
			String domainCode = domainConfig.getCode();
			String domainTestCode = domainCode + "Test";
			String domainPackageCode = domainConfig.getPackageCode();
			String modelCode = modelConfig.getCode();
			String modelCodeWithFirstLetterAsLower = modelConfig
					.getCodeWithFirstLetterAsLower();
			String modelTestCode = modelCode + "Test";
			String modelTestCodeWithFirstLetterAsLower = textHandler
					.firstLetterToLower(modelTestCode);

			String modelCodeTestJava = modelTestCode + ".java";
			String modelPackageCode = modelConfig.getPackageCode();

			List<ConceptConfig> conceptConfigList = modelConfig
					.getConceptsConfig().getConceptConfigList();
			List<ConceptConfig> entryConceptConfigList = modelConfig
					.getConceptsConfig().getEntryConceptConfigList();

			VelocityContext context = new VelocityContext();
			context.put("modelpackagecode", modelPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("domainpackagecode", domainPackageCode);
			context.put("DomainCode", domainCode);
			context.put("DomainTestCode", domainTestCode);
			context.put("ModelTestCode", modelTestCode);
			context.put("modelTestCode", modelTestCodeWithFirstLetterAsLower);
			context.put("ModelCode", modelCode);
			context.put("modelCode", modelCodeWithFirstLetterAsLower);
			context.put("entryConceptConfigList", entryConceptConfigList);
			context.put("conceptConfigList", conceptConfigList);

			Template template = Velocity.getTemplate(modelTestTemplatePath);
			String modelPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(modelConfig.getPackageCode());
			String modelTestDirectoryPath = testDirectoryPath + SEPARATOR
					+ modelPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					modelTestDirectoryPath + SEPARATOR + modelCodeTestJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(modelCodeTestJava + " model test class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateModelTest: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates concept classes.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateConcept(ConceptConfig conceptConfig) {
		generateGenEntity(conceptConfig);
		generateGenEntities(conceptConfig);
		generateEntity(conceptConfig);
		generateEntities(conceptConfig);

		generateEntitiesTest(conceptConfig);
	}

	/**
	 * Generates concept generic classes.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateGenConcept(ConceptConfig conceptConfig) {
		generateGenEntity(conceptConfig);
		generateGenEntities(conceptConfig);
	}

	/**
	 * Generates generic entity class.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateGenEntity(ConceptConfig conceptConfig) {
		try {
			String entityTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String entityTemplate = "GenEntity.vm";
			String entityTemplatePath = entityTemplateLocalContext + SEPARATOR
					+ entityTemplate;

			String entityCode = conceptConfig.getCode();
			String entityCodeJava = "Gen" + entityCode + ".java";
			String entityPackageCode = conceptConfig.getPackageCode();
			String entityCodeWithFirstLetterAsLower = conceptConfig
					.getCodeWithFirstLetterAsLower();

			ModelConfig modelConfig = conceptConfig.getModelConfig();
			String serialNumber = Transformer.string(conceptConfig.getOid()
					.getUniqueNumber());

			PropertiesConfig propertiesConfig = conceptConfig
					.getPropertiesConfig();

			List<PropertyConfig> entityPropertyConfigList = propertiesConfig
					.getEntityPropertyConfigList();
			List<PropertyConfig> referencePropertyConfigList = propertiesConfig
					.getReferencePropertyConfigList();
			List<PropertyConfig> notBaseClassPropertyConfigList = propertiesConfig
					.getNotBaseClassPropertyConfigList();
			List<PropertyConfig> derivedPropertyConfigList = propertiesConfig
					.getDerivedPropertyConfigList();

			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();

			List<NeighborConfig> parentConfigList = neighborsConfig
					.getParentConfigList();
			NeighborConfig lastParentConfig = neighborsConfig
					.getLastParentConfig();

			List<NeighborConfig> childConfigList = neighborsConfig
					.getChildConfigList();

			NeighborConfig internalParentConfig = neighborsConfig
					.getInternalParentConfig();

			List<NeighborConfig> internalChildConfigList = neighborsConfig
					.getInternalChildConfigList();

			List<NeighborConfig> externalParentConfigList = neighborsConfig
					.getExternalParentConfigList();
			List<NeighborConfig> externalChildConfigList = neighborsConfig
					.getExternalChildConfigList();
			List<NeighborConfig> externalOneToManyChildConfigList = neighborsConfig
					.getExternalOneToManyChildConfigList();
			List<NeighborConfig> externalManyToManyChildConfigList = neighborsConfig
					.getExternalManyToManyChildConfigList();

			VelocityContext context = new VelocityContext();
			context.put("entitypackagecode", entityPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("conceptConfig", conceptConfig);
			context.put("EntityCode", entityCode);
			context.put("entityCode", entityCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);
			context.put("modelConfig", modelConfig);

			context.put("entityPropertyConfigList", entityPropertyConfigList);
			context.put("referencePropertyConfigList",
					referencePropertyConfigList);
			context.put("notBaseClassPropertyConfigList",
					notBaseClassPropertyConfigList);
			context.put("derivedPropertyConfigList", derivedPropertyConfigList);

			context.put("parentConfigList", parentConfigList);
			context.put("lastParentConfig", lastParentConfig);

			context.put("childConfigList", childConfigList);

			context.put("internalParentConfig", internalParentConfig);
			context.put("internalChildConfigList", internalChildConfigList);

			context.put("externalParentConfigList", externalParentConfigList);
			context.put("externalChildConfigList", externalChildConfigList);
			context.put("externalOneToManyChildConfigList",
					externalOneToManyChildConfigList);
			context.put("externalManyToManyChildConfigList",
					externalManyToManyChildConfigList);

			Template template = Velocity.getTemplate(entityTemplatePath);
			String conceptCodeDirectoryPath = getModelConceptCodeDirectoryPath(conceptConfig
					.getCode());
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					conceptCodeDirectoryPath + SEPARATOR + entityCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(entityCodeJava + " generic entity class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateGenEntity: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates specific entity class.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateEntity(ConceptConfig conceptConfig) {
		try {
			String entityTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String entityTemplate = "Entity.vm";
			String entityTemplatePath = entityTemplateLocalContext + SEPARATOR
					+ entityTemplate;

			String entityCode = conceptConfig.getCode();
			String entityCodeJava = entityCode + ".java";
			String entityPackageCode = conceptConfig.getPackageCode();
			String entityCodeWithFirstLetterAsLower = conceptConfig
					.getCodeWithFirstLetterAsLower();

			ModelConfig modelConfig = conceptConfig.getModelConfig();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 2L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			List<NeighborConfig> parentConfigList = neighborsConfig
					.getParentConfigList();
			NeighborConfig lastParentConfig = neighborsConfig
					.getLastParentConfig();
			NeighborConfig internalParentConfig = neighborsConfig
					.getInternalParentConfig();
			List<NeighborConfig> internalChildConfigList = neighborsConfig
					.getInternalChildConfigList();
			List<NeighborConfig> externalParentConfigList = neighborsConfig
					.getExternalParentConfigList();
			List<NeighborConfig> externalChildConfigList = neighborsConfig
					.getExternalChildConfigList();
			List<NeighborConfig> externalOneToManyChildConfigList = neighborsConfig
					.getExternalOneToManyChildConfigList();
			List<NeighborConfig> externalManyToManyChildConfigList = neighborsConfig
					.getExternalManyToManyChildConfigList();

			VelocityContext context = new VelocityContext();
			context.put("entitypackagecode", entityPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("EntityCode", entityCode);
			context.put("entityCode", entityCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);
			context.put("modelConfig", modelConfig);

			context.put("parentConfigList", parentConfigList);
			context.put("lastParentConfig", lastParentConfig);
			context.put("internalParentConfig", internalParentConfig);
			context.put("internalChildConfigList", internalChildConfigList);
			context.put("externalParentConfigList", externalParentConfigList);
			context.put("externalChildConfigList", externalChildConfigList);
			context.put("externalOneToManyChildConfigList",
					externalOneToManyChildConfigList);
			context.put("externalManyToManyChildConfigList",
					externalManyToManyChildConfigList);

			Template template = Velocity.getTemplate(entityTemplatePath);
			String conceptCodeDirectoryPath = getModelConceptCodeDirectoryPath(conceptConfig
					.getCode());
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					conceptCodeDirectoryPath + SEPARATOR + entityCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(entityCodeJava + " specific entity class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateEntity: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates generic entities class.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateGenEntities(ConceptConfig conceptConfig) {
		try {
			String entitiesTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String entitiesTemplate = "GenEntities.vm";
			String entitiesTemplatePath = entitiesTemplateLocalContext
					+ SEPARATOR + entitiesTemplate;

			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeJava = "Gen" + entitiesCode + ".java";
			String entitiesPackageCode = conceptConfig.getPackageCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			String entityCode = conceptConfig.getCode();
			String entityCodeWithFirstLetterAsLower = conceptConfig
					.getCodeWithFirstLetterAsLower();

			ModelConfig modelConfig = conceptConfig.getModelConfig();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 1L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			PropertiesConfig propertiesConfig = conceptConfig
					.getPropertiesConfig();
			List<PropertyConfig> essentialPropertyConfigList = propertiesConfig
					.getEssentialPropertyConfigList();
			List<PropertyConfig> notBaseClassEssentialPropertyConfigList = propertiesConfig
					.getNotBaseClassEssentialPropertyConfigList();
			List<PropertyConfig> notUniqueEssentialPropertyConfigListWithoutEmailAndUrl = propertiesConfig
					.getNotUniqueEssentialPropertyConfigListWithoutEmailAndUrl();
			List<PropertyConfig> uniquePropertyConfigList = propertiesConfig
					.getUniquePropertyConfigList();
			List<PropertyConfig> mustHavePropertyConfigList = propertiesConfig
					.getMustHavePropertyConfigList();
			PropertyConfig lastMustHavePropertyConfig = propertiesConfig
					.getLastMustHavePropertyConfig();

			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();

			List<NeighborConfig> parentConfigList = neighborsConfig
					.getParentConfigList();
			NeighborConfig lastParentConfig = neighborsConfig
					.getLastParentConfig();

			NeighborConfig internalParentConfig = neighborsConfig
					.getInternalParentConfig();

			List<NeighborConfig> internalManyToManyChildConfigList = neighborsConfig
					.getInternalManyToManyChildConfigList();

			List<NeighborConfig> externalParentConfigList = neighborsConfig
					.getExternalParentConfigList();

			List<NeighborConfig> manyToManyParentConfigList = neighborsConfig
					.getManyToManyParentConfigList();

			VelocityContext context = new VelocityContext();
			context.put("entitiespackagecode", entitiesPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("conceptConfig", conceptConfig);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("EntityCode", entityCode);
			context.put("entityCode", entityCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);
			context.put("modelConfig", modelConfig);

			context.put("essentialPropertyConfigList",
					essentialPropertyConfigList);
			context.put("notBaseClassEssentialPropertyConfigList",
					notBaseClassEssentialPropertyConfigList);
			context.put(
					"notUniqueEssentialPropertyConfigListWithoutEmailAndUrl",
					notUniqueEssentialPropertyConfigListWithoutEmailAndUrl);
			context.put("uniquePropertyConfigList", uniquePropertyConfigList);
			context.put("mustHavePropertyConfigList",
					mustHavePropertyConfigList);
			context.put("lastMustHavePropertyConfig",
					lastMustHavePropertyConfig);

			context.put("parentConfigList", parentConfigList);
			context.put("lastParentConfig", lastParentConfig);

			context.put("internalParentConfig", internalParentConfig);
			context.put("internalManyToManyChildConfigList",
					internalManyToManyChildConfigList);

			context.put("externalParentConfigList", externalParentConfigList);

			context.put("manyToManyParentConfigList",
					manyToManyParentConfigList);

			Template template = Velocity.getTemplate(entitiesTemplatePath);
			String conceptCodeDirectoryPath = getModelConceptCodeDirectoryPath(conceptConfig
					.getCode());
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					conceptCodeDirectoryPath + SEPARATOR + entitiesCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(entitiesCodeJava + " generic entities class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateGenEntities: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates specific entities class.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateEntities(ConceptConfig conceptConfig) {
		try {
			String entitiesTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String entitiesTemplate = "Entities.vm";
			String entitiesTemplatePath = entitiesTemplateLocalContext
					+ SEPARATOR + entitiesTemplate;

			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeJava = entitiesCode + ".java";
			String entitiesPackageCode = conceptConfig.getPackageCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			String entityCode = conceptConfig.getCode();

			ModelConfig modelConfig = conceptConfig.getModelConfig();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 3L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			List<NeighborConfig> parentConfigList = neighborsConfig
					.getParentConfigList();
			NeighborConfig internalParentConfig = neighborsConfig
					.getInternalParentConfig();
			List<NeighborConfig> externalParentConfigList = neighborsConfig
					.getExternalParentConfigList();

			VelocityContext context = new VelocityContext();
			context.put("entitiespackagecode", entitiesPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("EntityCode", entityCode);
			context.put("serialNumber", serialNumber);

			context.put("parentConfigList", parentConfigList);
			context.put("internalParentConfig", internalParentConfig);
			context.put("externalParentConfigList", externalParentConfigList);

			Template template = Velocity.getTemplate(entitiesTemplatePath);
			String conceptCodeDirectoryPath = getModelConceptCodeDirectoryPath(conceptConfig
					.getCode());
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					conceptCodeDirectoryPath + SEPARATOR + entitiesCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(entitiesCodeJava + " specific entities class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateEntities: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates entities test.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void generateEntitiesTest(ConceptConfig conceptConfig) {
		try {
			String entitiesTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String entitiesTestTemplate = "EntitiesTest.vm";
			String entitiesTemplatePath = entitiesTemplateLocalContext
					+ SEPARATOR + entitiesTestTemplate;

			DomainConfig domainConfig = modelConfig.getDomainConfig();
			String domainCode = domainConfig.getCode();
			String domainTestCode = domainCode + "Test";
			String domainPackageCode = domainConfig.getPackageCode();
			String modelCode = modelConfig.getCode();

			String entityCode = conceptConfig.getCode();
			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesTestCode = entitiesCode + "Test";
			String entitiesCodeTestJava = entitiesCode + "Test.java";
			String entitiesPackageCode = conceptConfig.getPackageCode();
			String entityCodeWithFirstLetterAsLower = conceptConfig
					.getCodeWithFirstLetterAsLower();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();

			VelocityContext context = new VelocityContext();
			context.put("entitiespackagecode", entitiesPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("domainpackagecode", domainPackageCode);
			context.put("DomainCode", domainCode);
			context.put("DomainTestCode", domainTestCode);
			context.put("ModelCode", modelCode);
			context.put("EntityCode", entityCode);
			context.put("entityCode", entityCodeWithFirstLetterAsLower);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("EntitiesTestCode", entitiesTestCode);

			Template template = Velocity.getTemplate(entitiesTemplatePath);
			String conceptTestDirectoryPath = getModelConceptTestDirectoryPath(conceptConfig
					.getCode());
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(conceptTestDirectoryPath + SEPARATOR
							+ entitiesCodeTestJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(entitiesCodeTestJava + " entities test class generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateEntitiesTest: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates empty xml data files for entry concepts.
	 */
	public void generateEmptyXmlDataFiles() {
		List<ConceptConfig> entryConceptConfigList = modelConfig
				.getConceptsConfig().getEntryConceptConfigList();
		for (ConceptConfig entryConceptConfig : entryConceptConfigList) {
			generateEmptyXmlDataFile(entryConceptConfig);
		}
	}

	/**
	 * Generates empty xml test data files for entry concepts.
	 */
	public void generateEmptyXmlTestDataFiles() {
		List<ConceptConfig> entryConceptConfigList = modelConfig
				.getConceptsConfig().getEntryConceptConfigList();
		for (ConceptConfig entryConceptConfig : entryConceptConfigList) {
			generateEmptyXmlTestDataFile(entryConceptConfig);
		}
	}

	/**
	 * Generates an empty xml data file for the entry concept.
	 * 
	 * @param entryConceptConfig
	 *            entry concept config
	 */
	public void generateEmptyXmlDataFile(ConceptConfig entryConceptConfig) {
		try {
			String xmlDataFileTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String xmlDataFileTemplate = "XmlDataFile.vm";
			String xmlDataFileTemplatePath = xmlDataFileTemplateLocalContext
					+ SEPARATOR + xmlDataFileTemplate;

			String filename = entryConceptConfig.getFileName();
			String xmlDataFilePath = dataDirectoryPath + SEPARATOR + filename;

			String conceptsCode = entryConceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();

			VelocityContext context = new VelocityContext();
			context.put("conceptsCode", conceptsCode);

			Template template = Velocity.getTemplate(xmlDataFileTemplatePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					xmlDataFilePath));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(xmlDataFilePath + " XML data file generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateXmlDataFile: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates an empty xml test data file for the entry concept.
	 * 
	 * @param entryConceptConfig
	 *            entry concept config
	 */
	public void generateEmptyXmlTestDataFile(ConceptConfig entryConceptConfig) {
		try {
			String xmlDataFileTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "model" + SEPARATOR + "concept";
			String xmlDataFileTemplate = "XmlDataFile.vm";
			String xmlDataFileTemplatePath = xmlDataFileTemplateLocalContext
					+ SEPARATOR + xmlDataFileTemplate;

			String filename = entryConceptConfig.getFileName();
			String xmlTestDataFilePath = testDataDirectoryPath + SEPARATOR
					+ filename;

			String conceptsCode = entryConceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();

			VelocityContext context = new VelocityContext();
			context.put("conceptsCode", conceptsCode);

			Template template = Velocity.getTemplate(xmlDataFileTemplatePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					xmlTestDataFilePath));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(xmlTestDataFilePath + " XML test data file generated.");
		} catch (Exception e) {
			log.error("Error in DomainModelGenerator.generateXmlTestDataFile: "
					+ e.getMessage());
		}
	}

}
