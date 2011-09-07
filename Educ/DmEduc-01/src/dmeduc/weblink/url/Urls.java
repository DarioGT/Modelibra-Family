package dmeduc.weblink.url;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/**
 * Url entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-07
 */
@SuppressWarnings("serial")
public class Urls extends Entities<Url> {

	/**
	 * Constructs urls within the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Urls(IDomainModel model) {
		super(model);
	}

	/**
	 * Retrieves the url with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return url
	 */
	public Url getUrl(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the url with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return url
	 */
	public Url getUrl(Long oidUniqueNumber) {
		return getUrl(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the url with a given link. Null if not found.
	 * 
	 * @param link
	 *            link
	 * @return url
	 */
	public Url getUrlByLink(String link) {
		return (Url) retrieveByProperty("link", link);
	}

	/**
	 * Creates url.
	 * 
	 * @param link
	 *            link
	 * @param category
	 *            category
	 * @return url
	 */
	public Url createUrl(String link, String category) {
		Url url = new Url(getModel());
		url.setLink(link);
		url.setCategory(category);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

}