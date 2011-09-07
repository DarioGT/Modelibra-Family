package dmeduc;

import java.io.File;

import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ConceptsConfig;
import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.DomainsConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.ModelibraProperties;
import org.modelibra.config.ModelsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;

/**
 * Creates the reusable domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-11-25
 */
public class DmEducReusableConfig {

	public static final String DOMAIN_CODE = "DmEduc";

	public static final String DOMAIN_TYPE = "Reusable";
	
	public static final String SEPARATOR = File.separator;

	private Config config;

	/**
	 * Constructs the reusable domain configuration.
	 */
	public DmEducReusableConfig() {
		ModelibraProperties modelibraProperties = new ModelibraProperties(
				getClass());
		config = new Config(modelibraProperties);
		DomainsConfig domainsConfig = config.getDomainsConfig();
		addReusableDomainConfig(domainsConfig);
	}
	
	/**
	 * Gets Modelibra properties.
	 * 
	 * @return Modelibra properties
	 */
	public ModelibraProperties getModelibraProperties() {
		return config.getModelibraProperties();
	}

	/**
	 * Gets the reusable domain configuration.
	 * 
	 * @return reusable domain configuration
	 */
	public DomainConfig getReusableDomainConfig() {
		return config.getDomainConfig(DOMAIN_CODE, DOMAIN_TYPE);
	}

	/**
	 * Adds the reusable domain configuration.
	 * 
	 * @param domainsConfig
	 *            domains configuration
	 */
	private void addReusableDomainConfig(DomainsConfig domainsConfig) {
		DomainConfig domainConfig = new DomainConfig();
		domainConfig.setCode(DOMAIN_CODE);
		domainConfig.setType(DOMAIN_TYPE);

		domainsConfig.add(domainConfig);

		ModelsConfig modelsConfig = new ModelsConfig();
		domainConfig.setModelsConfig(modelsConfig);

		ModelConfig modelConfig = new ModelConfig();
		modelConfig.setCode("WebLink");
		modelConfig.setPersistent(true);
		modelsConfig.add(modelConfig);

		ConceptsConfig conceptsConfig = new ConceptsConfig();
		modelConfig.setConceptsConfig(conceptsConfig);

		ConceptConfig conceptConfig = new ConceptConfig();
		conceptConfig.setCode("Url");
		conceptConfig.setEntry(true);
		conceptsConfig.add(conceptConfig);

		PropertiesConfig propertiesConfig = new PropertiesConfig();
		conceptConfig.setPropertiesConfig(propertiesConfig);

		PropertyConfig linkPropertyConfig = new PropertyConfig();
		linkPropertyConfig.setCode("link");
		linkPropertyConfig.setRequired(true);
		linkPropertyConfig.setUnique(true);
		linkPropertyConfig.setValidateType(true);
		linkPropertyConfig.setValidationType("java.net.URL");
		linkPropertyConfig.setMaxLength("96");
		propertiesConfig.add(linkPropertyConfig);

		PropertyConfig descriptionPropertyConfig = new PropertyConfig();
		descriptionPropertyConfig.setCode("description");
		descriptionPropertyConfig.setMaxLength("510");
		propertiesConfig.add(descriptionPropertyConfig);
	}

}
