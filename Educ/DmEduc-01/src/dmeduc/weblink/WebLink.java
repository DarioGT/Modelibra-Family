package dmeduc.weblink;

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import dmeduc.weblink.url.Urls;

/**
 * WebLink domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
@SuppressWarnings("serial")
public class WebLink extends DomainModel {

	private Urls urls;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public WebLink(IDomain domain) {
		super(domain);
		urls = new Urls(this);
	}

	/**
	 * Gets Url entities.
	 * 
	 * @return Url entities
	 */
	public Urls getUrls() {
		return urls;
	}

}
