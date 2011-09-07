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
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.gen.Generator;

/**
 * Generates TextRes classes and properties.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-17
 */
public class TextResGenerator extends Generator {

	private static Log log = LogFactory.getLog(TextResGenerator.class);

	private DomainConfig domainConfig;

	private String sourceDirectoryPath;

	private boolean minCodeGen = true;

	/**
	 * Constructs TextRes generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public TextResGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath) {
		super();
		this.domainConfig = domainConfig;
		this.sourceDirectoryPath = sourceDirectoryPath;
		if (domainConfig == null) {
			String msg = "TextResGenerator.constructor -- domain configuration is null.";
			throw new ConfigRuntimeException(msg);
		}
		createSwingAppI18nDirectories();
	}

	/**
	 * Constructs TextRes generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 * @param minCodeGen
	 *            <code>true</code> if it is a minCodeGen
	 */
	public TextResGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath, boolean minCodeGen) {
		super();
		this.domainConfig = domainConfig;
		this.sourceDirectoryPath = sourceDirectoryPath;
		this.minCodeGen = minCodeGen;
		if (domainConfig == null) {
			String msg = "TextResGenerator.constructor -- domain configuration is null.";
			throw new ConfigRuntimeException(msg);
		}
		createSwingAppI18nDirectories();
	}

	/***************************************************************************
	 * Generates directories for Swing i18n application.
	 **************************************************************************/

	/**
	 * Gets Swing application i18n directory path.
	 * 
	 * @return Swing application i18n directory path
	 */
	private String getSwingAppI18nDirectoryPath() {
		String domainPackageCode = domainConfig.getPackageCode();
		String domainSwingAppI18nPackageCode = domainPackageCode
				+ ".swing.app.i18n";

		String domainSwingAppI18nPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainSwingAppI18nPackageCode);

		String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
				+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
		return domainSwingAppI18nDirectoryPath;
	}

	/**
	 * Creates Swing application directories where the code will be generated.
	 */
	private void createSwingAppI18nDirectories() {
		String domainSwingAppI18nDirectoryPath = getSwingAppI18nDirectoryPath();
		File domainSwingAppI18nDirectory = new File(
				domainSwingAppI18nDirectoryPath);
		if (!domainSwingAppI18nDirectory.exists()) {
			domainSwingAppI18nDirectory.mkdirs();
		}
	}

	/***************************************************************************
	 * Generates TextRes files.
	 **************************************************************************/

	/**
	 * Generates TextRes.java and TextRes.properties.
	 */
	public void generate() {
		generateTextRes();
		generateTextResProperties();
		generateTextResProperties_en();
		if (!minCodeGen) {
			generateTextRes_fr();
			generateTextResProperties_fr();

			generateTextRes_ba();
			generateTextResProperties_ba();
		}
	}

	/***************************************************************************
	 * Generates TextRes classes.
	 **************************************************************************/

	/**
	 * Generates the TextRes class.
	 */
	public void generateTextRes() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextRes.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String textResJava = "TextRes.java";

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR + textResJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResJava + " class generated.");
		} catch (Exception e) {
			log.error("Error in TextResGenerator.generateTextRes: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates the TextRes_fr class.
	 */
	public void generateTextRes_fr() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextRes_fr.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String textResJava = "TextRes_fr.java";

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR + textResJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResJava + " class generated.");
		} catch (Exception e) {
			log.error("Error in TextResGenerator.generateTextRes_fr: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates the TextRes_fr class.
	 */
	public void generateTextRes_ba() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextRes_ba.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String textResJava = "TextRes_ba.java";

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR + textResJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResJava + " class generated.");
		} catch (Exception e) {
			log.error("Error in TextResGenerator.generateTextRes_ba: "
					+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates TextRes property files.
	 **************************************************************************/

	/**
	 * Generates TextRes properties.
	 */
	public void generateTextResProperties() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextResProperties.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String textResProperties = "TextRes.properties";

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR
							+ textResProperties));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResProperties + " properties file generated.");
		} catch (Exception e) {
			log.error("Error in TextResGenerator.generateTextResProperties: "
					+ e.getMessage());
		}
	}

	/**
	 * Generates TextRes_en properties.
	 */
	public void generateTextResProperties_en() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextResProperties_en.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String domainCode = domainConfig.getCode();
			String textResProperties_en = "TextRes_en.properties";

			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getModelConfigList();

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);
			context.put("DomainCode", domainCode);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR
							+ textResProperties_en));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResProperties_en + " properties file generated.");
		} catch (Exception e) {
			log
					.error("Error in TextResGenerator.generateTextResProperties_en: "
							+ e.getMessage());
		}
	}

	/**
	 * Generates TextRes_fr properties.
	 */
	public void generateTextResProperties_fr() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextResProperties_fr.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String domainCode = domainConfig.getCode();
			String textResProperties_fr = "TextRes_fr.properties";

			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getModelConfigList();

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);
			context.put("DomainCode", domainCode);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR
							+ textResProperties_fr));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResProperties_fr + " properties file generated.");
		} catch (Exception e) {
			log
					.error("Error in TextResGenerator.generateTextResProperties_fr: "
							+ e.getMessage());
		}
	}

	/**
	 * Generates TextRes_ba properties.
	 */
	public void generateTextResProperties_ba() {
		try {
			String swingAppI18nTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app"
					+ SEPARATOR + "i18n";
			String startTemplate = "TextResProperties_ba.vm";
			String startTemplatePath = swingAppI18nTemplateLocalContext
					+ SEPARATOR + startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppI18nPackageCode = domainPackageCode
					+ ".swing.app.i18n";
			String domainCode = domainConfig.getCode();
			String textResProperties_ba = "TextRes_ba.properties";

			List<ModelConfig> modelConfigList = domainConfig.getModelsConfig()
					.getModelConfigList();

			VelocityContext context = new VelocityContext();
			context.put("domainswingappi18npackagecode",
					domainSwingAppI18nPackageCode);
			context.put("DomainCode", domainCode);
			context.put("modelConfigList", modelConfigList);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppI18nPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppI18nPackageCode);
			String domainSwingAppI18nDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppI18nPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppI18nDirectoryPath + SEPARATOR
							+ textResProperties_ba));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(textResProperties_ba + " properties file generated.");
		} catch (Exception e) {
			log
					.error("Error in TextResGenerator.generateTextResProperties_ba: "
							+ e.getMessage());
		}
	}

}
