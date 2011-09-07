package dmeduc.weblink;

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.url.Urls;

/**
 * Educ domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
@SuppressWarnings("serial")
public class WebLink extends DomainModel {

	private Urls urls;

	private Comments comments;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public WebLink(IDomain domain) {
		super(domain);
		urls = new Urls(this);
		comments = new Comments(this);
	}

	/**
	 * Gets Url entities.
	 * 
	 * @return Url entities
	 */
	public Urls getUrls() {
		return urls;
	}

	/**
	 * Gets Comment entities.
	 * 
	 * @return Comment entities
	 */
	public Comments getComments() {
		return comments;
	}

}
