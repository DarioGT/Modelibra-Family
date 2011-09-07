package dmeduc.weblink.url;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Url entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
@SuppressWarnings("serial")
public class Url extends Entity<Url> {

	private String link;

	private String description;

	/**
	 * Constructs url within the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Url(IDomainModel model) {
		super(model);
	}

	/**
	 * Sets link.
	 * 
	 * @param link
	 *            link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets link.
	 * 
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

}