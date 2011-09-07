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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;

/**
 * Collection of domain configurations.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-13
 */
public class DomainsConfig extends Entities<DomainConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 2040L;

	private static Log log = LogFactory.getLog(DomainsConfig.class);

	private Config config; // context

	private XmlDomainsConfig xmlDomainsConfig;

	/**
	 * Constructs the domains configuration.
	 */
	public DomainsConfig() {
		super();
		xmlDomainsConfig = new XmlDomainsConfig(this);
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param config
	 *            configuration
	 */
	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return configuration
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Gets the domain configuration based on the domain configuration code and
	 * the domain type.
	 * 
	 * @param domainCode
	 *            domain code
	 * @param domainType
	 *            domain type
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig(String domainCode, String domainType) {
		List<DomainConfig> list = getList();
		for (DomainConfig domainConfig : list) {
			String code = domainConfig.getCode();
			String type = domainConfig.getType();
			if (code.equals(domainCode) && type.equals(domainType)) {
				return domainConfig;
			}
		}
		return null;
	}

	/**
	 * Loads domain configurations from XML files.
	 */
	public void loadFromXmlConfigFiles() {
		String modelibraDomainConfigFilePath = getConfig()
				.getModelibraDomainConfigFilePath();
		String reusableDomainConfigFilePath = getConfig()
				.getReusableDomainConfigFilePath();
		String specificDomainConfigFilePath = getConfig()
				.getSpecificDomainConfigFilePath();
		loadFromXmlConfigFile(modelibraDomainConfigFilePath);
		loadFromXmlConfigFile(reusableDomainConfigFilePath);
		loadFromXmlConfigFile(specificDomainConfigFilePath);
	}

	/**
	 * Loads domain configuration from an XML file determined by its path.
	 * 
	 * @param configFilePath
	 *            configuration file path
	 */
	private void loadFromXmlConfigFile(String configFilePath) {
		File configFile = new File(configFilePath);
		if (configFile.exists()) {
			getXmlDomainsConfig().setDataFilePath(configFilePath);
			load();
		}
	}

	/**
	 * Extends all domain configurations.
	 */
	public void extend() {
		for (DomainConfig domainConfig : this) {
			domainConfig.extend();
		}
	}

	/**
	 * Checks if the new domain code is unique within the domain configuration.
	 * 
	 * @param newDomainConfig
	 *            domain configuration entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(DomainConfig newDomainConfig) {
		boolean validation = true;
		String newDomainCode = newDomainConfig.getCode();
		String newDomainType = newDomainConfig.getType();
		if (newDomainCode != null && newDomainType != null) {
			for (DomainConfig domainConfig : this) {
				if (domainConfig.getCode().equals(newDomainCode)
						&& domainConfig.getType().equals(newDomainType)) {
					validation = false;
					log
							.info(newDomainCode
									+ "+"
									+ newDomainType
									+ " domain code+type must be unique within the configuration.");
					break;
				}
			}
		} else {
			validation = false;
			log.info("Domain code+type is required.");
		}
		return validation;
	}

	/**
	 * Sets the dmLite configuration for a new model configuration.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(DomainConfig domainConfig) {
		domainConfig.setConfig(config);
		return true;
	}

	/**
	 * Outputs the domain configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		List<DomainConfig> list = getList();
		log.info("*** " + title + " ***");
		for (DomainConfig domainConfig : list) {
			String classSimpleName = domainConfig.getClass().getSimpleName();
			domainConfig.output(classSimpleName);
		}
	}

	/**
	 * Gets XML domains.
	 * 
	 * @return XML domains
	 */
	public XmlDomainsConfig getXmlDomainsConfig() {
		return xmlDomainsConfig;
	}

	/**
	 * Gets the persistent domain.
	 * 
	 * @return persistent domain
	 */
	public IPersistentModel getPersistentModel() {
		return xmlDomainsConfig.getPersistentModel();
	}

	/**
	 * Gets the domain entities.
	 * 
	 * @return domain entities
	 */
	public IEntities<?> getEntities() {
		return xmlDomainsConfig.getEntities();
	}

	/**
	 * Loads domains.
	 */
	public void load() {
		xmlDomainsConfig.load();
	}

	/**
	 * Saves domains.
	 */
	public void save() {
		xmlDomainsConfig.save();
	}

	/**
	 * Gets the list of domain configurations.
	 * 
	 * @return list of domain configurations
	 */
	public List<DomainConfig> getDomainConfigList() {
		List<DomainConfig> domainConfigList = new ArrayList<DomainConfig>();
		for (DomainConfig domainConfig : getList()) {
			domainConfigList.add(domainConfig);
		}
		return domainConfigList;
	}

	/**
	 * Gets the list of specific domain configurations.
	 * 
	 * @return list of specific domain configurations
	 */
	public List<DomainConfig> getSpecificDomainConfigList() {
		List<DomainConfig> specificDomainConfigList = new ArrayList<DomainConfig>();
		for (DomainConfig domainConfig : getList()) {
			if (domainConfig.getType().equals(DomainConfig.SPECIFIC_TYPE)) {
				specificDomainConfigList.add(domainConfig);
			}
		}
		return specificDomainConfigList;
	}

}
