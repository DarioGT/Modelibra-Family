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
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.ModelibraProperties;
import org.modelibra.util.Transformer;

/**
 * Generates domain classes and properties from the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-23
 */
public class DomainGenerator extends Generator {

	private static Log log = LogFactory.getLog(DomainGenerator.class);

	private DomainConfig domainConfig;

	private String authors;

	private String sourceDirectoryPath;

	private String testDirectoryPath;

	/**
	 * Constructs domain generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param authors
	 *            authors
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public DomainGenerator(DomainConfig domainConfig, String authors,
			String sourceDirectoryPath, String testDirectoryPath) {
		super();
		this.domainConfig = domainConfig;
		this.authors = authors;
		this.sourceDirectoryPath = sourceDirectoryPath;
		this.testDirectoryPath = testDirectoryPath;
		createDomainDirectories();
	}

	/***************************************************************************
	 * Generates domain directories.
	 **************************************************************************/

	/**
	 * Gets the domain code directory path.
	 * 
	 * @return domain code directory path
	 */
	private String getDomainCodeDirectoryPath() {
		String domainPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainConfig.getPackageCode());
		return sourceDirectoryPath + SEPARATOR + domainPackageCodeWithSlash;
	}

	/**
	 * Gets the domain test directory path.
	 * 
	 * @return domain test directory path
	 */
	private String getDomainTestDirectoryPath() {
		String domainPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainConfig.getPackageCode());
		return testDirectoryPath + SEPARATOR + domainPackageCodeWithSlash;
	}

	/**
	 * Creates directories for the domain where the source code and tests will
	 * be generated.
	 */
	private void createDomainDirectories() {
		String domainCodeDirectoryPath = getDomainCodeDirectoryPath();
		File domainCodeDirectory = new File(domainCodeDirectoryPath);
		if (!domainCodeDirectory.exists()) {
			domainCodeDirectory.mkdirs();
		}

		String domainTestDirectoryPath = getDomainTestDirectoryPath();
		File domainTestDirectory = new File(domainTestDirectoryPath);
		if (!domainTestDirectory.exists()) {
			domainTestDirectory.mkdirs();
		}
	}

	/***************************************************************************
	 * Generates domain.
	 **************************************************************************/

	/**
	 * Generates modelibra properties, domain configuration, domain, domain
	 * test, persistent domain, and all models.
	 */
	public void generate() {
		generateSpecificDomainConfig();
		generateModelibraProperties();
		generateDomainConfig();
		generatePersistentDomain();
		generateGenDomain();
		generateDomain();
		generateDomainModels();

		generateDomainTest();
	}

	/**
	 * Generates domain models.
	 */
	public void generateDomainModels() {
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			DomainModelGenerator modelGenerator = new DomainModelGenerator(
					modelConfig, sourceDirectoryPath, testDirectoryPath);
			modelGenerator.generate();
		}
	}

	/**
	 * Generates domain generic class and model generic classes.
	 */
	public void generateGenClasses() {
		generateGenDomain();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			DomainModelGenerator modelGenerator = new DomainModelGenerator(
					modelConfig, sourceDirectoryPath, testDirectoryPath);
			modelGenerator.generateGenClasses();
		}
	}

	/***************************************************************************
	 * Generates specific domain config file.
	 **************************************************************************/

	/**
	 * Generates specific domain configuration.
	 */
	public void generateSpecificDomainConfig() {
		try {
			String configTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String configTemplate = "SpecificDomainConfig.vm";
			String configTemplatePath = configTemplateLocalContext + SEPARATOR
					+ configTemplate;

			String specificDomainConfigFilePath = domainConfig.getConfig()
					.getSpecificDomainConfigFilePath();
			if (specificDomainConfigFilePath == null) {
				log.info("Specific domain configuration does not exist.");
			}

			String domainOid = domainConfig.getOid().toString();
			String domainCode = domainConfig.getCode();

			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getList();

			VelocityContext context = new VelocityContext();
			context.put("domainOid", domainOid);
			context.put("DomainCode", domainCode);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity.getTemplate(configTemplatePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					specificDomainConfigFilePath));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(specificDomainConfigFilePath
					+ " specific domain config file generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateSpecificDomainConfig: "
					+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates dmConfig properties file.
	 **************************************************************************/

	/**
	 * Generates Modelibra properties.
	 */
	public void generateModelibraProperties() {
		try {
			String propertiesTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String propertiesTemplate = "ModelibraProperties.vm";
			String propertiesTemplatePath = propertiesTemplateLocalContext
					+ SEPARATOR + propertiesTemplate;

			String propertiesFile = ModelibraProperties.MODELIBRA_PROPERTIES_FILE_NAME;

			VelocityContext context = new VelocityContext();

			Template template = Velocity.getTemplate(propertiesTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainConfig.getPackageCode());
			String domainDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainDirectoryPath + SEPARATOR + propertiesFile));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(propertiesFile + " properties file generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateModelibraProperties: "
					+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates classes for domain, domain configuration, domain test and
	 * persistent domain.
	 **************************************************************************/

	/**
	 * Generates domain configuration.
	 */
	public void generateDomainConfig() {
		try {
			String domainTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String domainConfigTemplate = "DomainConfig.vm";
			String domainConfigTemplatePath = domainTemplateLocalContext
					+ SEPARATOR + domainConfigTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainCode = domainConfig.getCode();
			String domainConfigCode = domainCode + "Config";
			String domainCodeConfigJava = domainConfigCode + ".java";
			String domainType = domainConfig.getType();

			VelocityContext context = new VelocityContext();
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("DomainCode", domainCode);
			context.put("DomainConfigCode", domainConfigCode);
			context.put("DomainType", domainType);

			Template template = Velocity.getTemplate(domainConfigTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainPackageCode);
			String domainCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainCodeDirectoryPath + SEPARATOR + domainCodeConfigJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(domainCodeConfigJava
					+ " domain configuration class generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateDomainConfig: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates generic domain.
	 */
	public void generateGenDomain() {
		try {
			String domainTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String domainTemplate = "GenDomain.vm";
			String domainTemplatePath = domainTemplateLocalContext + SEPARATOR
					+ domainTemplate;

			String domainCode = domainConfig.getCode();
			String domainCodeJava = "Gen" + domainCode + ".java";
			String domainPackageCode = domainConfig.getPackageCode();
			String serialNumber = Transformer.string(domainConfig.getOid()
					.getUniqueNumber());
			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getModelConfigList();

			VelocityContext context = new VelocityContext();
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("DomainCode", domainCode);
			context.put("serialNumber", serialNumber);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity.getTemplate(domainTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainPackageCode);
			String domainCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainCodeDirectoryPath + SEPARATOR + domainCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(domainCodeJava + " generic domain class generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateGenDomain: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates specific domain.
	 */
	public void generateDomain() {
		try {
			String domainTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String domainTemplate = "Domain.vm";
			String domainTemplatePath = domainTemplateLocalContext + SEPARATOR
					+ domainTemplate;

			String domainCode = domainConfig.getCode();
			String domainCodeJava = domainCode + ".java";
			String domainPackageCode = domainConfig.getPackageCode();
			Long uniqueNumber = domainConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 1L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("DomainCode", domainCode);
			context.put("serialNumber", serialNumber);

			Template template = Velocity.getTemplate(domainTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainPackageCode);
			String domainCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainCodeDirectoryPath + SEPARATOR + domainCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(domainCodeJava + " specific domain class generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateDomain: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates domain test.
	 */
	public void generateDomainTest() {
		try {
			String domainTestTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String domainTestTemplate = "DomainTest.vm";
			String domainTestTemplatePath = domainTestTemplateLocalContext
					+ SEPARATOR + domainTestTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainTestCode = domainConfig.getCode() + "Test";
			String domainTestCodeWithFirstLetterAsLower = textHandler
					.firstLetterToLower(domainTestCode);
			String domainTestCodeJava = domainTestCode + ".java";
			String domainCode = domainConfig.getCode();
			String domainCodeWithFirstLetterAsLower = domainConfig
					.getCodeWithFirstLetterAsLower();
			String persistentDomainCode = "Persistent" + domainConfig.getCode();
			String persistentDomainCodeWithFirstLetterAsLower = textHandler
					.firstLetterToLower(persistentDomainCode);
			String domainConfigCode = domainConfig.getCode() + "Config";
			String domainConfigCodeWithFirstLetterAsLower = textHandler
					.firstLetterToLower(domainConfigCode);

			VelocityContext context = new VelocityContext();
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("DomainTestCode", domainTestCode);
			context.put("domainTestCode", domainTestCodeWithFirstLetterAsLower);
			context.put("DomainCode", domainCode);
			context.put("domainCode", domainCodeWithFirstLetterAsLower);
			context.put("PersistentDomainCode", persistentDomainCode);
			context.put("persistentDomainCode",
					persistentDomainCodeWithFirstLetterAsLower);
			context.put("DomainConfigCode", domainConfigCode);
			context.put("domainConfigCode",
					domainConfigCodeWithFirstLetterAsLower);

			Template template = Velocity.getTemplate(domainTestTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainPackageCode);
			String domainTestDirectoryPath = testDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainTestDirectoryPath + SEPARATOR + domainTestCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(domainTestCodeJava + " domain test class generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generateDomainTest: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates persistent domain.
	 */
	public void generatePersistentDomain() {
		try {
			String domainTemplateLocalContext = "template" + SEPARATOR
					+ "domain";
			String domainTestTemplate = "PersistentDomain.vm";
			String domainTestTemplatePath = domainTemplateLocalContext
					+ SEPARATOR + domainTestTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String persistentDomainCode = "Persistent" + domainConfig.getCode();
			String persistentDomainCodeWithFirstLetterAsLower = textHandler
					.firstLetterToLower(persistentDomainCode);
			String persistentDomainCodeJava = persistentDomainCode + ".java";
			String domainCode = domainConfig.getCode();
			String domainCodeWithFirstLetterAsLower = domainConfig
					.getCodeWithFirstLetterAsLower();

			VelocityContext context = new VelocityContext();
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("PersistentDomainCode", persistentDomainCode);
			context.put("persistentDomainCode",
					persistentDomainCodeWithFirstLetterAsLower);
			context.put("DomainCode", domainCode);
			context.put("domainCode", domainCodeWithFirstLetterAsLower);

			Template template = Velocity.getTemplate(domainTestTemplatePath);
			String domainPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainPackageCode);
			String domainCodeDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ domainPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainCodeDirectoryPath + SEPARATOR
							+ persistentDomainCodeJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(persistentDomainCodeJava
					+ " persistent domain class generated.");
		} catch (Exception e) {
			log.error("Error in DomainGenerator.generatePersistentDomain: "
					+ e.getMessage());
		}
	}

}
