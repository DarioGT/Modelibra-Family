package dmeduc;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.DomainsConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.ModelsConfig;

/**
 * Creates the specific domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-12-03
 */
public class DmEducSpecificConfig extends DmEducReusableConfig {

	public static final String DOMAIN_CODE = "DmEduc";

	public static final String DOMAIN_TYPE = "Specific";

	/**
	 * Constructs the domain configuration.
	 */
	public DmEducSpecificConfig() {
		DomainsConfig domainsConfig = getConfig().getDomainsConfig();
		addSpecificDomainConfig(domainsConfig);
		domainsConfig.extend();
	}

	/**
	 * Gets the specific domain configuration.
	 * 
	 * @return specific domain configuration
	 */
	public DomainConfig getSpecificDomainConfig() {
		return getConfig().getDomainConfig(DOMAIN_CODE, DOMAIN_TYPE);
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

		domainConfig.setShortTextDefaultLength("255");

		domainsConfig.add(domainConfig);

		ModelsConfig modelsConfig = new ModelsConfig();
		domainConfig.setModelsConfig(modelsConfig);

		ModelConfig modelConfig = new ModelConfig();
		modelConfig.setCode("WebLink");
		modelConfig.setExtension(true);
		modelConfig.setExtensionDomain(DOMAIN_CODE);
		modelConfig.setExtensionDomainType("Reusable");
		modelConfig.setExtensionModel("WebLink");
		modelsConfig.add(modelConfig);
	}

}
