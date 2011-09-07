package dmeduc.weblink.url;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

/**
 * Url entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-15
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
	 * Creates url.
	 * 
	 * @param link
	 *            link
	 * @return url
	 */
	public Url createUrl(String link) {
		Url url = new Url(getModel());
		url.setLink(link);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

	/**
	 * Creates url.
	 * 
	 * @param link
	 *            link
	 * @param description
	 *            description
	 * @return url
	 */
	public Url createUrl(String link, String description) {
		Url url = new Url(getModel());
		url.setLink(link);
		url.setDescription(description);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

}