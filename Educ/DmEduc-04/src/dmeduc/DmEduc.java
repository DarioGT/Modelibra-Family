package dmeduc;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

import dmeduc.weblink.WebLink;

/**
 * Creates the domain and its model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-02
 */
@SuppressWarnings("serial")
public class DmEduc extends Domain {

	private WebLink webLink;

	/**
	 * Constructs the domain and its model.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public DmEduc(DomainConfig domainConfig) {
		super(domainConfig);
		webLink = new WebLink(this);
	}

	/**
	 * Gets the WebLink model.
	 * 
	 * @return WebLink model
	 */
	public WebLink getWebLink() {
		return webLink;
	}

}
