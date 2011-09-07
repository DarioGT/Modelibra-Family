package org.modelibra.config.woutxml;

import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ConceptsConfig;
import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.DomainsConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.ModelibraProperties;
import org.modelibra.config.ModelsConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;

public class WoutXmlConfig {

	public static final String DOMAIN_CODE = "DmEduc";

	public static final String DOMAIN_TYPE = "Reusable";

	private Config config;

	/**
	 * Constructs the domain configuration.
	 */
	public WoutXmlConfig() {
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
	 * Gets the configuration.
	 * 
	 * @return configuration
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Gets the reusable domain configuration.
	 * 
	 * @return reusable domain configuration
	 */
	public DomainConfig getDomainConfig() {
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

		// Category concept
		ConceptConfig categoryConceptConfig = new ConceptConfig();
		categoryConceptConfig.setCode("Category");
		categoryConceptConfig.setEntry(true);
		categoryConceptConfig.setEntitiesCode("Categories");
		conceptsConfig.add(categoryConceptConfig);

		PropertiesConfig categoryPropertiesConfig = new PropertiesConfig();
		categoryConceptConfig.setPropertiesConfig(categoryPropertiesConfig);

		PropertyConfig categoryNamePropertyConfig = new PropertyConfig();
		categoryNamePropertyConfig.setCode("name");
		categoryNamePropertyConfig.setRequired(true);
		categoryNamePropertyConfig.setUnique(true);
		categoryNamePropertyConfig.setMaxLength("32");
		categoryPropertiesConfig.add(categoryNamePropertyConfig);

		PropertyConfig categoryDescriptionPropertyConfig = new PropertyConfig();
		categoryDescriptionPropertyConfig.setCode("description");
		categoryDescriptionPropertyConfig.setMaxLength("510");
		categoryPropertiesConfig.add(categoryDescriptionPropertyConfig);

		NeighborsConfig categoryNeighborsConfig = new NeighborsConfig();
		categoryConceptConfig.setNeighborsConfig(categoryNeighborsConfig);

		NeighborConfig categoryUrlsNeighborConfig = new NeighborConfig();
		categoryUrlsNeighborConfig.setCode("urls");
		categoryUrlsNeighborConfig.setDestinationConcept("Url");
		categoryUrlsNeighborConfig.setInverseNeighbor("category");
		categoryUrlsNeighborConfig.setType("child");
		categoryUrlsNeighborConfig.setMin("0");
		categoryUrlsNeighborConfig.setMax("N");
		categoryNeighborsConfig.add(categoryUrlsNeighborConfig);

		// Url concept
		ConceptConfig urlConceptConfig = new ConceptConfig();
		urlConceptConfig.setCode("Url");
		urlConceptConfig.setEntry(false);
		conceptsConfig.add(urlConceptConfig);

		PropertiesConfig urlPropertiesConfig = new PropertiesConfig();
		urlConceptConfig.setPropertiesConfig(urlPropertiesConfig);

		PropertyConfig urlLinkPropertyConfig = new PropertyConfig();
		urlLinkPropertyConfig.setCode("link");
		urlLinkPropertyConfig.setRequired(true);
		urlLinkPropertyConfig.setUnique(true);
		urlLinkPropertyConfig.setValidateType(true);
		urlLinkPropertyConfig.setValidationType("java.net.URL");
		urlLinkPropertyConfig.setMaxLength("96");
		urlPropertiesConfig.add(urlLinkPropertyConfig);

		PropertyConfig urlDescriptionPropertyConfig = new PropertyConfig();
		urlDescriptionPropertyConfig.setCode("description");
		urlDescriptionPropertyConfig.setMaxLength("510");
		urlPropertiesConfig.add(urlDescriptionPropertyConfig);

		NeighborsConfig urlNeighborsConfig = new NeighborsConfig();
		urlConceptConfig.setNeighborsConfig(urlNeighborsConfig);

		NeighborConfig urlCategoryNeighborConfig = new NeighborConfig();
		urlCategoryNeighborConfig.setCode("category");
		urlCategoryNeighborConfig.setDestinationConcept("Category");
		urlCategoryNeighborConfig.setInverseNeighbor("urls");
		urlCategoryNeighborConfig.setType("parent");
		urlCategoryNeighborConfig.setMin("1");
		urlCategoryNeighborConfig.setMax("1");
		urlNeighborsConfig.add(urlCategoryNeighborConfig);
	}

}
