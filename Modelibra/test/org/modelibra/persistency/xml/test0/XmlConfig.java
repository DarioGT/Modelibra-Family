package org.modelibra.persistency.xml.test0;

import org.modelibra.config.Config;
import org.modelibra.config.DomainConfig;

public class XmlConfig extends Config {
	
	private DomainConfig domainConfig;

	/**
	 * Constructs the domain configuration.
	 */
	public XmlConfig() {
		super();
		domainConfig = getDomainConfig("Xml", "Specific");
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
