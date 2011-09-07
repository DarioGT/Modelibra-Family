package dmeduc;

import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;

/**
 * Creates the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-03
 */
@SuppressWarnings("serial")
public class DmEducConfig extends Config {

	private DomainConfig domainConfig;

	/**
	 * Constructs the domain configuration.
	 */
	public DmEducConfig() {
		super();
		domainConfig = getDomainConfig("DmEduc", "Specific");
	}

	/**
	 * Gets the domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

}
