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
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.gen.Generator;
import org.modelibra.util.Transformer;

/**
 * Generates domain Wicket classes, properties and configurations from the
 * domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-19
 */
public class DomainWicketGenerator extends Generator {

	private static Log log = LogFactory.getLog(DomainWicketGenerator.class);

	private DomainConfig domainConfig;

	private String authors;

	private String sourceDirectoryPath;

	private String webDirectoryPath;

	private boolean minCodeGen = false;

	/**
	 * Constructs domain Wicket generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param authors
	 *            authors
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public DomainWicketGenerator(DomainConfig domainConfig, String authors,
			String sourceDirectoryPath, boolean minCodeGen) {
		super();
		this.domainConfig = domainConfig;
		this.authors = authors;
		this.sourceDirectoryPath = sourceDirectoryPath;
		this.minCodeGen = minCodeGen;
		if (domainConfig == null) {
			String msg = "DomainWicketGenerator.constructor -- domain configuration is null.";
			throw new ConfigRuntimeException(msg);
		}
		// WEB-INF
		webDirectoryPath = domainConfig.getConfig().getWebInfDirectoryPath();

		createDomainWicketAppDirectories();
		if (!minCodeGen) {
			createDomainWicketModelConceptDirectories();
		}
	}

	/***************************************************************************
	 * Generates directories for domain application.
	 **************************************************************************/

	/**
	 * Gets domain Wicket application directory path.
	 * 
	 * @return domain Wicket application directory path
	 */
	private String getDomainWicketAppDirectoryPath() {
		String domainPackageCode = domainConfig.getPackageCode();
		String domainWicketAppPackageCode = domainPackageCode + ".wicket.app";

		String domainWicketAppPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainWicketAppPackageCode);

		String domainWicketAppDirectoryPath = sourceDirectoryPath + SEPARATOR
				+ domainWicketAppPackageCodeWithSlash;
		return domainWicketAppDirectoryPath;
	}

	/**
	 * Gets domain Wicket model concept directory path based on a concept
	 * configuration.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 * @return domain Wicket model concept directory path
	 */
	private String getDomainWicketModelConceptDirectoryPath(
			ConceptConfig conceptConfig) {
		String domainPackageCode = domainConfig.getPackageCode();
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		String modelCodeInLowerLetters = modelConfig.getCodeInLowerLetters();
		String conceptCodeInLowerLetters = conceptConfig
				.getCodeInLowerLetters();
		String domainWicketModelConceptPackageCode = domainPackageCode
				+ ".wicket." + modelCodeInLowerLetters + "."
				+ conceptCodeInLowerLetters;

		String domainWicketModelConceptPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainWicketModelConceptPackageCode);

		String domainWicketModelConceptDirectoryPath = sourceDirectoryPath
				+ SEPARATOR + domainWicketModelConceptPackageCodeWithSlash;
		return domainWicketModelConceptDirectoryPath;
	}

	/**
	 * Creates domain Wicket application directories where the code will be
	 * generated.
	 */
	private void createDomainWicketAppDirectories() {
		String domainWicketAppDirectoryPath = getDomainWicketAppDirectoryPath();
		File domainWicketAppDirectory = new File(domainWicketAppDirectoryPath);
		if (!domainWicketAppDirectory.exists()) {
			domainWicketAppDirectory.mkdirs();
		}
	}

	/**
	 * Creates domain Wicket model concept directories where the code will be
	 * generated.
	 */
	private void createDomainWicketModelConceptDirectories() {
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
				String domainWicketModelConceptDirectoryPath = getDomainWicketModelConceptDirectoryPath(conceptConfig);
				File conceptDirectory = new File(
						domainWicketModelConceptDirectoryPath);
				if (!conceptDirectory.exists()) {
					conceptDirectory.mkdirs();
				}
			}
		}
	}

	/***************************************************************************
	 * Generates domain Wicket application.
	 **************************************************************************/

	/**
	 * Generates web xml configuration, domain application properties, start
	 * class, domain application class and domain model concept classes.
	 */
	public void generate() {
		generateWebXml();
		generateDomainAppProperties();
		generateStart();
		generateDomainApp();
		if (!minCodeGen) {
			for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
				for (ConceptConfig conceptConfig : modelConfig
						.getConceptsConfig()) {
					generateDomainModelConcept(conceptConfig);
				}
			}
		}
	}

	/***************************************************************************
	 * Generates configuration.
	 **************************************************************************/

	/**
	 * Generates web xml configuration.
	 */
	public void generateWebXml() {
		try {
			String webTemplateLocalContext = "template" + SEPARATOR + "web";
			String webXmlTemplate = "WebXml.vm";
			String webXmlTemplatePath = webTemplateLocalContext + SEPARATOR
					+ webXmlTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String wicketAppPackageCode = domainPackageCode + ".wicket.app";
			String webXml = "web.xml";
			String domainCode = domainConfig.getCode();
			String domainAppCode = domainCode + "App";

			VelocityContext context = new VelocityContext();
			context.put("wicketapppackagecode", wicketAppPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("domainAppCode", domainAppCode);

			Template template = Velocity.getTemplate(webXmlTemplatePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					webDirectoryPath + SEPARATOR + webXml));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(webXml + " web XML configuration generated.");
		} catch (Exception e) {
			log.error("Error in DomainWicketGenerator.generateWebXml: "
					+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates properties files.
	 **************************************************************************/

	/**
	 * Generates domain properties.
	 */
	public void generateDomainAppProperties() {
		try {
			String wicketAppTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "wicket" + SEPARATOR + "app";
			String domainAppPropertiesTemplate = "DomainAppProperties.vm";
			String domainAppPropertiesTemplatePath = wicketAppTemplateLocalContext
					+ SEPARATOR + domainAppPropertiesTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String wicketAppPackageCode = domainPackageCode + ".wicket.app";
			String domainCode = domainConfig.getCode();
			String domainAppProperties = domainCode + "App.properties";
			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getModelConfigList();

			VelocityContext context = new VelocityContext();
			context.put("DomainCode", domainCode);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity
					.getTemplate(domainAppPropertiesTemplatePath);
			String wicketAppPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(wicketAppPackageCode);
			String wicketAppDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ wicketAppPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					wicketAppDirectoryPath + SEPARATOR + domainAppProperties));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(domainAppProperties
					+ " domain application properties file generated.");
		} catch (Exception e) {
			log
					.error("Error in DomainWicketGenerator.generateDomainAppProperties: "
							+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates classes for domain Wicket application.
	 **************************************************************************/

	/**
	 * Generates the Start Jetty web server class.
	 */
	public void generateStart() {
		try {
			String wicketAppTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "wicket" + SEPARATOR + "app";
			String startTemplate = "Start.vm";
			String startTemplatePath = wicketAppTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketAppPackageCode = domainPackageCode
					+ ".wicket.app";
			String startJava = "Start.java";

			VelocityContext context = new VelocityContext();
			context.put("domainwicketapppackagecode",
					domainWicketAppPackageCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainWicketAppPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketAppPackageCode);
			String domainWicketAppDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainWicketAppPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainWicketAppDirectoryPath + SEPARATOR + startJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(startJava + " start Jetty web server class generated.");
		} catch (Exception e) {
			log.error("Error in DomainWicketGenerator.generateStart: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates domain application.
	 */
	public void generateDomainApp() {
		try {
			String wicketAppTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "wicket" + SEPARATOR + "app";
			String domainAppTemplate = "DomainApp.vm";
			String domainAppTemplatePath = wicketAppTemplateLocalContext
					+ SEPARATOR + domainAppTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketAppPackageCode = domainPackageCode
					+ ".wicket.app";
			String domainCode = domainConfig.getCode();
			String domainAppCode = domainCode + "App";
			String domainCodeAppJava = domainAppCode + ".java";
			String persistentDomainCode = "Persistent" + domainConfig.getCode();
			String domainConfigCode = domainConfig.getCode() + "Config";

			VelocityContext context = new VelocityContext();
			context.put("domainwicketapppackagecode",
					domainWicketAppPackageCode);
			context.put("domainpackagecode", domainPackageCode);
			context.put("author", authors);
			context.put("today", today);
			context.put("DomainAppCode", domainAppCode);
			context.put("DomainCode", domainCode);
			context.put("PersistentDomainCode", persistentDomainCode);
			context.put("DomainConfigCode", domainConfigCode);

			Template template = Velocity.getTemplate(domainAppTemplatePath);
			String wicketAppPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketAppPackageCode);
			String wicketAppDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ wicketAppPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					wicketAppDirectoryPath + SEPARATOR + domainCodeAppJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log
					.info(domainCodeAppJava
							+ " domain application class generated.");
		} catch (Exception e) {
			log.error("Error in DomainWicketGenerator.generateDomainApp: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates domain model concept.
	 * 
	 * @param conceptConfig
	 *            concept config
	 */
	public void generateDomainModelConcept(ConceptConfig conceptConfig) {
		generateDomainModelConceptUpdateTablePage(conceptConfig);
		generateDomainModelConceptDisplayTablePage(conceptConfig);
		generateDomainModelConceptDisplayListPage(conceptConfig);
		generateDomainModelConceptDisplaySlidePage(conceptConfig);
	}

	/**
	 * Generates domain model concept update table page.
	 * 
	 * @param conceptConfig
	 *            concept config
	 */
	public void generateDomainModelConceptUpdateTablePage(
			ConceptConfig conceptConfig) {
		try {
			String domainWicketModelConceptTemplateLocalContext = "template"
					+ SEPARATOR + "domain" + SEPARATOR + "wicket" + SEPARATOR
					+ "model" + SEPARATOR + "concept";
			String domainModelConceptUpdateTablePageTemplate = "EntityUpdateTablePage.vm";
			String domainModelConceptUpdateTablePageTemplatePath = domainWicketModelConceptTemplateLocalContext
					+ SEPARATOR + domainModelConceptUpdateTablePageTemplate;
			String domainModelConceptUpdateTablePageJava = "EntityUpdateTablePage.java";

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketPackageCode = domainPackageCode + ".wicket";
			ModelConfig modelConfig = conceptConfig.getModelConfig();
			String modelCodeInLowerLetters = modelConfig
					.getCodeInLowerLetters();
			String domainWicketModelPackageCode = domainWicketPackageCode + "."
					+ modelCodeInLowerLetters;
			String conceptCodeInLowerLetters = conceptConfig
					.getCodeInLowerLetters();
			String domainWicketModelConceptPackageCode = domainWicketModelPackageCode
					+ "." + conceptCodeInLowerLetters;

			String entitiesClass = conceptConfig.getEntitiesClass();
			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 5L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("domainwicketmodelconceptpackagecode",
					domainWicketModelConceptPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("entitiesClass", entitiesClass);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);

			Template template = Velocity
					.getTemplate(domainModelConceptUpdateTablePageTemplatePath);
			String domainWicketModelConceptPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketModelConceptPackageCode);
			String domainWicketModelConceptDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainWicketModelConceptPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainWicketModelConceptDirectoryPath + SEPARATOR
							+ domainModelConceptUpdateTablePageJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log
					.info(domainModelConceptUpdateTablePageJava
							+ " domain model concept update table page class generated.");
		} catch (Exception e) {
			log
					.error("Error in DomainWicketGenerator.generateDomainModelConceptUpdateTablePage: "
							+ e.getMessage());
		}
	}

	/**
	 * Generates domain model concept display table page.
	 * 
	 * @param conceptConfig
	 *            concept config
	 */
	public void generateDomainModelConceptDisplayTablePage(
			ConceptConfig conceptConfig) {
		try {
			String domainWicketModelConceptTemplateLocalContext = "template"
					+ SEPARATOR + "domain" + SEPARATOR + "wicket" + SEPARATOR
					+ "model" + SEPARATOR + "concept";
			String domainModelConceptDisplayTablePageTemplate = "EntityDisplayTablePage.vm";
			String domainModelConceptDisplayTablePageTemplatePath = domainWicketModelConceptTemplateLocalContext
					+ SEPARATOR + domainModelConceptDisplayTablePageTemplate;
			String domainModelConceptDisplayTablePageJava = "EntityDisplayTablePage.java";

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketPackageCode = domainPackageCode + ".wicket";
			ModelConfig modelConfig = conceptConfig.getModelConfig();
			String modelCodeInLowerLetters = modelConfig
					.getCodeInLowerLetters();
			String domainWicketModelPackageCode = domainWicketPackageCode + "."
					+ modelCodeInLowerLetters;
			String conceptCodeInLowerLetters = conceptConfig
					.getCodeInLowerLetters();
			String domainWicketModelConceptPackageCode = domainWicketModelPackageCode
					+ "." + conceptCodeInLowerLetters;

			String entitiesClass = conceptConfig.getEntitiesClass();
			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 5L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("domainwicketmodelconceptpackagecode",
					domainWicketModelConceptPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("entitiesClass", entitiesClass);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);

			Template template = Velocity
					.getTemplate(domainModelConceptDisplayTablePageTemplatePath);
			String domainWicketModelConceptPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketModelConceptPackageCode);
			String domainWicketModelConceptDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainWicketModelConceptPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainWicketModelConceptDirectoryPath + SEPARATOR
							+ domainModelConceptDisplayTablePageJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log
					.info(domainModelConceptDisplayTablePageJava
							+ " domain model concept display table page class generated.");
		} catch (Exception e) {
			log
					.error("Error in DomainWicketGenerator.generateDomainModelConceptDisplayTablePage: "
							+ e.getMessage());
		}
	}

	/**
	 * Generates domain model concept display list page.
	 * 
	 * @param conceptConfig
	 *            concept config
	 */
	public void generateDomainModelConceptDisplayListPage(
			ConceptConfig conceptConfig) {
		try {
			String domainWicketModelConceptTemplateLocalContext = "template"
					+ SEPARATOR + "domain" + SEPARATOR + "wicket" + SEPARATOR
					+ "model" + SEPARATOR + "concept";
			String domainModelConceptDisplayListPageTemplate = "EntityDisplayListPage.vm";
			String domainModelConceptDisplayListPageTemplatePath = domainWicketModelConceptTemplateLocalContext
					+ SEPARATOR + domainModelConceptDisplayListPageTemplate;
			String domainModelConceptDisplayListPageJava = "EntityDisplayListPage.java";

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketPackageCode = domainPackageCode + ".wicket";
			ModelConfig modelConfig = conceptConfig.getModelConfig();
			String modelCodeInLowerLetters = modelConfig
					.getCodeInLowerLetters();
			String domainWicketModelPackageCode = domainWicketPackageCode + "."
					+ modelCodeInLowerLetters;
			String conceptCodeInLowerLetters = conceptConfig
					.getCodeInLowerLetters();
			String domainWicketModelConceptPackageCode = domainWicketModelPackageCode
					+ "." + conceptCodeInLowerLetters;

			String entitiesClass = conceptConfig.getEntitiesClass();
			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 5L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("domainwicketmodelconceptpackagecode",
					domainWicketModelConceptPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("entitiesClass", entitiesClass);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);

			Template template = Velocity
					.getTemplate(domainModelConceptDisplayListPageTemplatePath);
			String domainWicketModelConceptPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketModelConceptPackageCode);
			String domainWicketModelConceptDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainWicketModelConceptPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainWicketModelConceptDirectoryPath + SEPARATOR
							+ domainModelConceptDisplayListPageJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log
					.info(domainModelConceptDisplayListPageJava
							+ " domain model concept display list page class generated.");
		} catch (Exception e) {
			log
					.error("Error in DomainWicketGenerator.generateDomainModelConceptDisplayListPage: "
							+ e.getMessage());
		}
	}

	/**
	 * Generates domain model concept display slide page.
	 * 
	 * @param conceptConfig
	 *            concept config
	 */
	public void generateDomainModelConceptDisplaySlidePage(
			ConceptConfig conceptConfig) {
		try {
			String domainWicketModelConceptTemplateLocalContext = "template"
					+ SEPARATOR + "domain" + SEPARATOR + "wicket" + SEPARATOR
					+ "model" + SEPARATOR + "concept";
			String domainModelConceptDisplaySlidePageTemplate = "EntityDisplaySlidePage.vm";
			String domainModelConceptDisplaySlidePageTemplatePath = domainWicketModelConceptTemplateLocalContext
					+ SEPARATOR + domainModelConceptDisplaySlidePageTemplate;
			String domainModelConceptDisplaySlidePageJava = "EntityDisplaySlidePage.java";

			String domainPackageCode = domainConfig.getPackageCode();
			String domainWicketPackageCode = domainPackageCode + ".wicket";
			ModelConfig modelConfig = conceptConfig.getModelConfig();
			String modelCodeInLowerLetters = modelConfig
					.getCodeInLowerLetters();
			String domainWicketModelPackageCode = domainWicketPackageCode + "."
					+ modelCodeInLowerLetters;
			String conceptCodeInLowerLetters = conceptConfig
					.getCodeInLowerLetters();
			String domainWicketModelConceptPackageCode = domainWicketModelPackageCode
					+ "." + conceptCodeInLowerLetters;

			String entitiesClass = conceptConfig.getEntitiesClass();
			String entitiesCode = conceptConfig.getEntitiesCode();
			String entitiesCodeWithFirstLetterAsLower = conceptConfig
					.getEntitiesCodeWithFirstLetterAsLower();
			Long uniqueNumber = conceptConfig.getOid().getUniqueNumber();
			long number = uniqueNumber.longValue();
			long nextNumber = number + 5L;
			String serialNumber = Transformer.string(new Long(nextNumber));

			VelocityContext context = new VelocityContext();
			context.put("domainwicketmodelconceptpackagecode",
					domainWicketModelConceptPackageCode);
			context.put("author", modelConfig.getAuthor());
			context.put("today", today);
			context.put("entitiesClass", entitiesClass);
			context.put("EntitiesCode", entitiesCode);
			context.put("entitiesCode", entitiesCodeWithFirstLetterAsLower);
			context.put("serialNumber", serialNumber);

			Template template = Velocity
					.getTemplate(domainModelConceptDisplaySlidePageTemplatePath);
			String domainWicketModelConceptPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainWicketModelConceptPackageCode);
			String domainWicketModelConceptDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainWicketModelConceptPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainWicketModelConceptDirectoryPath + SEPARATOR
							+ domainModelConceptDisplaySlidePageJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log
					.info(domainModelConceptDisplaySlidePageJava
							+ " domain model concept display slide page class generated.");
		} catch (Exception e) {
			log
					.error("Error in DomainWicketGenerator.generateDomainModelConceptDisplaySlidePage: "
							+ e.getMessage());
		}
	}

}
