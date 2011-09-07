package dmeduc;

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
 * Creates the specific domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-11-24
 */
public class DmEducSpecificConfig {

	public static final String DOMAIN_CODE = "DmEduc";

	public static final String DOMAIN_TYPE = "Specific";

	private Config config;

	/**
	 * Constructs the specific domain configuration.
	 */
	public DmEducSpecificConfig() {
		ModelibraProperties modelibraProperties = new ModelibraProperties(getClass());
		config = new Config(modelibraProperties);
		DomainsConfig domainsConfig = config.getDomainsConfig();
		addSpecificDomainConfig(domainsConfig);
	}

	/**
	 * Gets the specific domain configuration.
	 * 
	 * @return specific domain configuration
	 */
	public DomainConfig getSpecificDomainConfig() {
		return config.getDomainConfig(DOMAIN_CODE, DOMAIN_TYPE);
	}

	/**
	 * Adds the specific domain configuration.
	 * 
	 * @param domainsConfig
	 *            domains configuration
	 */
	private void addSpecificDomainConfig(DomainsConfig domainsConfig) {
		DomainConfig domainConfig = new DomainConfig();
		domainConfig.setCode(DOMAIN_CODE);
		domainConfig.setType(DOMAIN_TYPE);

		domainsConfig.add(domainConfig);

		ModelsConfig modelsConfig = new ModelsConfig();
		domainConfig.setModelsConfig(modelsConfig);
		ModelConfig modelConfig = new ModelConfig();
		modelConfig.setCode("WebLink");
		modelConfig.setPersistent(false);
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
		propertiesConfig.add(linkPropertyConfig);

		PropertyConfig descriptionPropertyConfig = new PropertyConfig();
		descriptionPropertyConfig.setCode("description");
		propertiesConfig.add(descriptionPropertyConfig);
	}

}
