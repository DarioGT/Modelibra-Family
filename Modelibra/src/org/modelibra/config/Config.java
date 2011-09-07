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
package org.modelibra.config;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.util.EmailConfig;
import org.modelibra.util.EmailsConfig;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.PathLocator;

/**
 * <p>
 * Uses the org.modelibra.config.ModelibraProperties class to obtain names of
 * directories and configuration files. Those names are either default constants
 * or custom names provided by the modelibra.properties file that, if used, must
 * exist in the same directory where the custom configuration class is located.
 * </p>
 * 
 * <p>
 * Classes are kept in the classes directory. All other directories and files
 * are located relative to the classes directory. XML configuration files are
 * located in the config directory. Email is configured in the email-config.xml
 * file. Log messages are configured in the log4j.xml file. db4o user
 * configuration is in the db4o-config.xml file.
 * </p>
 * 
 * <p>
 * Modelibra configuration has three XML configuration files. By default, they
 * are located in the config directory as a twin directory to the classes
 * directory. The specific-domain-config.xml file is used to configure the
 * domain with its model(s). The specific configuration may inherit
 * configurations from the reusable-domain-config.xml file. The reusable
 * configuration file is a place where user oriented reusable configurations are
 * kept. The reusable configuration may inherit the Modelibra predefined
 * configurations from the modelibra-domain-config.xml file. At least one of the
 * three configuration file must exist. There may be more than one model
 * configured in a configuration file.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-12-08
 */
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(Config.class);

	public static final String SEPARATOR = File.separator;

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	private ModelibraProperties modelibraProperties;

	private DomainsConfig domainsConfig; // children

	/**
	 * Constructs the Modelibra configuration from the modelibra, reusable and
	 * specific domain XML configurations. Used in inheritance.
	 */
	public Config() {
		modelibraProperties = new ModelibraProperties(getClass());
		configureLog();
		domainsConfig = new DomainsConfig();
		domainsConfig.setConfig(this);
		domainsConfig.loadFromXmlConfigFiles();
		domainsConfig.extend();
	}

	/**
	 * Constructs the Modelibra configuration from Java. Used in composition.
	 * 
	 * @param modelibraProperties
	 *            Modelibra properties
	 */
	public Config(ModelibraProperties modelibraProperties) {
		this.modelibraProperties = modelibraProperties;
		configureLog();
		domainsConfig = new DomainsConfig();
		domainsConfig.setConfig(this);
	}

	/**
	 * Gets the Modelibra properties.
	 * 
	 * @return Modelibra properties
	 */
	public ModelibraProperties getModelibraProperties() {
		return modelibraProperties;
	}

	/**
	 * Gets the domains configuration.
	 * 
	 * @return domains configuration
	 */
	public DomainsConfig getDomainsConfig() {
		return domainsConfig;
	}

	/**
	 * Gets the domain configuration based on the domain code.
	 * 
	 * @param domainCode
	 *            domain code
	 * @param domainType
	 *            domain type
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig(String domainCode, String domainType) {
		return domainsConfig.getDomainConfig(domainCode, domainType);
	}

	/**
	 * Gets the list of domain configurations.
	 * 
	 * @return list of domain configurations
	 */
	public List<DomainConfig> getDomainConfigList() {
		return domainsConfig.getList();
	}

	/**
	 * Gets email configuration.
	 * 
	 * @return email configuration
	 */
	public EmailConfig getEmailConfig() {
		EmailConfig emailConfig = null;
		String emailConfigFileName = modelibraProperties
				.getEmailConfigFileName();
		String classDirName = modelibraProperties.getClassDirectoryName();
		String configDirName = modelibraProperties.getConfigDirectoryName();
		PathLocator pathLocator = new PathLocator();
		String emailConfigFilePath = pathLocator.findAbsolutePath(
				modelibraProperties.getReferenceClass(), classDirName,
				configDirName + SEPARATOR + emailConfigFileName);
		File emailConfigFile = new File(emailConfigFilePath);
		if (!emailConfigFile.exists()) {
			log.info("Email configuration file does not exist: "
					+ emailConfigFilePath);
		} else {
			EmailsConfig emailsConfig = new EmailsConfig(emailConfigFilePath);
			emailConfig = emailsConfig.getLastEmailConfig();
		}
		return emailConfig;
	}

	/**
	 * Configures log.
	 */
	private void configureLog() {
		String logConfigFilePath = getLogConfigFilePath();
		Log4jConfigurator logConfigurator = new Log4jConfigurator(
				logConfigFilePath);
		logConfigurator.configure();
	}

	/**
	 * Gets configuration directory (absolute) path.
	 * 
	 * @return configuration directory path
	 */
	public String getConfigDirectoryPath() {
		String configDirectoryPath = null;
		String classDirectoryName = modelibraProperties.getClassDirectoryName();
		String configDirectoryName = modelibraProperties
				.getConfigDirectoryName();
		PathLocator pathLocator = new PathLocator();
		configDirectoryPath = pathLocator.findAbsolutePath(modelibraProperties
				.getReferenceClass(), classDirectoryName, configDirectoryName);
		return configDirectoryPath;
	}

	/**
	 * Gets log configuration file (absolute) path.
	 * 
	 * @return log configuration file path
	 */
	private String getLogConfigFilePath() {
		String logConfigFileName = modelibraProperties.getLogConfigFileName();
		String logConfigFilePath = getConfigDirectoryPath() + SEPARATOR
				+ logConfigFileName;
		return logConfigFilePath;
	}

	/**
	 * Gets modelibra domain configuration file (absolute) path.
	 * 
	 * @return modelibra domain configuration file path
	 */
	public String getModelibraDomainConfigFilePath() {
		String modelibraDomainConfigFileName = modelibraProperties
				.getModelibraDomainConfigFileName();
		String modelibraDomainConfigFilePath = getConfigDirectoryPath()
				+ SEPARATOR + modelibraDomainConfigFileName;
		return modelibraDomainConfigFilePath;
	}

	/**
	 * Gets reusable domain configuration file (absolute) path.
	 * 
	 * @return reusable domain configuration file path
	 */
	public String getReusableDomainConfigFilePath() {
		String reusableDomainConfigFileName = modelibraProperties
				.getReusableDomainConfigFileName();
		String reusableDomainConfigFilePath = getConfigDirectoryPath()
				+ SEPARATOR + reusableDomainConfigFileName;
		return reusableDomainConfigFilePath;
	}

	/**
	 * Gets specific domain configuration file (absolute) path.
	 * 
	 * @return specific domain configuration file path
	 */
	public String getSpecificDomainConfigFilePath() {
		String specificDomainConfigFileName = modelibraProperties
				.getSpecificDomainConfigFileName();
		String specificDomainConfigFilePath = getConfigDirectoryPath()
				+ SEPARATOR + specificDomainConfigFileName;
		return specificDomainConfigFilePath;
	}

	/**
	 * Gets db4o configuration file (absolute) path.
	 * 
	 * @return db4o configuration file path
	 */
	public String getDb4oConfigFilePath() {
		String db4oConfigFileName = modelibraProperties.getDb4oConfigFileName();
		String db4oConfigFilePath = getConfigDirectoryPath() + SEPARATOR
				+ db4oConfigFileName;
		return db4oConfigFilePath;
	}

	/**
	 * Gets jdbc configuration file (absolute) path for a model configuration.
	 * 
	 * @param modelConfig
	 *            model configuration
	 * @return jdbc configuration file path
	 */
	public String getJdbcConfigFilePath(ModelConfig modelConfig) {
		String jdbcConfigFileName = modelConfig.getPersistenceConfig();
		String jdbcConfigFilePath = getConfigDirectoryPath() + SEPARATOR
				+ jdbcConfigFileName;
		return jdbcConfigFilePath;
	}

	/**
	 * Gets data directory (absolute) path.
	 * 
	 * @param modelConfig
	 *            model configuration
	 * @return data directory path
	 */
	public String getDataDirectoryPath(ModelConfig modelConfig) {
		String persistenceRelativePath = modelConfig
				.getPersistenceRelativePath();
		String classDirectoryName = modelibraProperties.getClassDirectoryName();
		PathLocator pathLocator = new PathLocator();
		return pathLocator.findAbsolutePath(modelibraProperties
				.getReferenceClass(), classDirectoryName,
				persistenceRelativePath);
	}

	/**
	 * Gets test directory (absolute) path.
	 * 
	 * @return test directory path
	 */
	public String getTestDirectoryPath() {
		String testDirectoryName = modelibraProperties.getTestDirectoryName();
		String anchoDirectoryName = modelibraProperties
				.getAnchorDirectoryName();
		PathLocator pathLocator = new PathLocator();
		return pathLocator.findAbsolutePath(modelibraProperties
				.getReferenceClass(), anchoDirectoryName, testDirectoryName);
	}

	/**
	 * Gets source directory (absolute) path.
	 * 
	 * @return source directory path
	 */
	public String getSourceDirectoryPath() {
		String sourceDirectoryName = modelibraProperties
				.getSourceDirectoryName();
		String anchoDirectoryName = modelibraProperties
				.getAnchorDirectoryName();
		PathLocator pathLocator = new PathLocator();
		return pathLocator.findAbsolutePath(modelibraProperties
				.getReferenceClass(), anchoDirectoryName, sourceDirectoryName);
	}

	/**
	 * Gets WEB-INF directory (absolute) path.
	 * 
	 * @return WEB-INF directory path
	 */
	public String getWebInfDirectoryPath() {
		String webInfDirectoryName = "WEB-INF";
		String anchoDirectoryName = modelibraProperties
				.getAnchorDirectoryName();
		PathLocator pathLocator = new PathLocator();
		return pathLocator.findAbsolutePath(modelibraProperties
				.getReferenceClass(), anchoDirectoryName, webInfDirectoryName);
	}

	/**
	 * Outputs the configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("--- " + title + " ---");
		domainsConfig.output("Modelibra Configuration");
	}

}
