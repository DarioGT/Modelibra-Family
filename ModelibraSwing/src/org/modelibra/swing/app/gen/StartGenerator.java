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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.modelibra.config.DomainConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.gen.Generator;

/**
 * Generates Start class and properties.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-17
 */
public class StartGenerator extends Generator {

	private static Log log = LogFactory.getLog(StartGenerator.class);

	private DomainConfig domainConfig;

	private String sourceDirectoryPath;

	/**
	 * Constructs Start generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public StartGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath) {
		super();
		this.domainConfig = domainConfig;
		this.sourceDirectoryPath = sourceDirectoryPath;
		if (domainConfig == null) {
			String msg = "StartGenerator.constructor -- domain configuration is null.";
			throw new ConfigRuntimeException(msg);
		}
		createSwingAppDirectories();
	}

	/***************************************************************************
	 * Generates directories for Swing application.
	 **************************************************************************/

	/**
	 * Gets Swing application directory path.
	 * 
	 * @return Swing application directory path
	 */
	private String getSwingAppDirectoryPath() {
		String domainPackageCode = domainConfig.getPackageCode();
		String domainSwingAppPackageCode = domainPackageCode + ".swing.app";

		String domainSwingAppPackageCodeWithSlash = textHandler
				.replaceDotWithSlash(domainSwingAppPackageCode);

		String domainSwingAppDirectoryPath = sourceDirectoryPath + SEPARATOR
				+ domainSwingAppPackageCodeWithSlash;
		return domainSwingAppDirectoryPath;
	}

	/**
	 * Creates Swing application directories where the code will be generated.
	 */
	private void createSwingAppDirectories() {
		String domainSwingAppDirectoryPath = getSwingAppDirectoryPath();
		File domainSwingAppDirectory = new File(domainSwingAppDirectoryPath);
		if (!domainSwingAppDirectory.exists()) {
			domainSwingAppDirectory.mkdirs();
		}
	}

	/***************************************************************************
	 * Generates Start files.
	 **************************************************************************/

	/**
	 * Generates Start.java and Start.properties.
	 */
	public void generate() {
		generateStart();
		generateStartProperties();
	}

	/***************************************************************************
	 * Generates Start.java.
	 **************************************************************************/

	/**
	 * Generates the Start class.
	 */
	public void generateStart() {
		try {
			String swingAppTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app";
			String startTemplate = "Start.vm";
			String startTemplatePath = swingAppTemplateLocalContext + SEPARATOR
					+ startTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppPackageCode = domainPackageCode + ".swing.app";
			String domainCode = domainConfig.getCode();
			String domainCodeInLowerLetters = domainConfig
					.getCodeInLowerLetters();
			String domainCodeFirstLetterToLower = textHandler
					.firstLetterToLower(domainCode);
			String domainCodeConfig = domainConfig.getCode() + "Config";
			String domainCodeConfigFirstLetterToLower = textHandler
					.firstLetterToLower(domainCodeConfig);
			String persistentDomainCode = "Persistent" + domainConfig.getCode();
			String startJava = "Start.java";

			VelocityContext context = new VelocityContext();
			context.put("domainswingapppackagecode", domainSwingAppPackageCode);
			context.put("domainpackagecode", domainPackageCode);
			context.put("domaincode", domainCodeInLowerLetters);
			context.put("DomainCode", domainCode);
			context.put("domainCode", domainCodeFirstLetterToLower);
			context.put("DomainCodeConfig", domainCodeConfig);
			context.put("domainCodeConfig", domainCodeConfigFirstLetterToLower);
			context.put("PersistentDomainCode", persistentDomainCode);

			Template template = Velocity.getTemplate(startTemplatePath);
			String domainSwingAppPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppPackageCode);
			String domainSwingAppDirectoryPath = sourceDirectoryPath
					+ SEPARATOR + domainSwingAppPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					domainSwingAppDirectoryPath + SEPARATOR + startJava));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(startJava + " class generated.");
		} catch (Exception e) {
			log.error("Error in StartGenerator.generateStart: "
					+ e.getMessage());
		}
	}

	/***************************************************************************
	 * Generates Start.properties.
	 **************************************************************************/

	/**
	 * Generates Start properties.
	 */
	public void generateStartProperties() {
		try {
			String swingAppTemplateLocalContext = "template" + SEPARATOR
					+ "domain" + SEPARATOR + "swing" + SEPARATOR + "app";
			String startPropertiesTemplate = "StartProperties.vm";
			String startPropertiesTemplatePath = swingAppTemplateLocalContext
					+ SEPARATOR + startPropertiesTemplate;

			String domainPackageCode = domainConfig.getPackageCode();
			String domainSwingAppPackageCode = domainPackageCode + ".swing.app";
			String domainCodeInLowerLetters = domainConfig
					.getCodeInLowerLetters();
			String startProperties = "Start.properties";

			VelocityContext context = new VelocityContext();
			context.put("domaincode", domainCodeInLowerLetters);

			Template template = Velocity
					.getTemplate(startPropertiesTemplatePath);
			String wicketAppPackageCodeWithSlash = textHandler
					.replaceDotWithSlash(domainSwingAppPackageCode);
			String wicketAppDirectoryPath = sourceDirectoryPath + SEPARATOR
					+ wicketAppPackageCodeWithSlash;
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					wicketAppDirectoryPath + SEPARATOR + startProperties));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			log.info(startProperties + " properties file generated.");
		} catch (Exception e) {
			log
					.error("Error in StartGenerator.generateStartProperties: "
							+ e.getMessage());
		}
	}

}
