package dmeduc.weblink.url;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

/**
 * Url entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
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
		add(url);
		return url;
	}

}