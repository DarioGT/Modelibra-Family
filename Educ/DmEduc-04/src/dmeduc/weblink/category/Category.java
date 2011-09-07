package dmeduc.weblink.category;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dmeduc.weblink.url.Urls;

/**
 * Category entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-09
 */
@SuppressWarnings("serial")
public class Category extends Entity<Category> {

	private String name;

	private String description;

	private Boolean approved = Boolean.FALSE;

	// Urls child neighbor (internal)
	private Urls urls;

	/**
	 * Constructs a category within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Category(IDomainModel domainModel) {
		super(domainModel);
		// internal child neighbors only
		urls = new Urls(this);
	}

	/**
	 * Sets a name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets a description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets approved.
	 * 
	 * @param approved
	 *            approved
	 */
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	/**
	 * Gets approved.
	 * 
	 * @return approved
	 */
	public Boolean getApproved() {
		return approved;
	}

	/**
	 * Sets approved.
	 * 
	 * @param approved
	 *            approved
	 */
	public void setApproved(boolean approved) {
		setApproved(new Boolean(approved));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isApproved() {
		return getApproved().booleanValue();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets urls.
	 * 
	 * @param urls
	 *            urls
	 */
	public void setUrls(Urls urls) {
		this.urls = urls;
		urls.setCategory(this);
	}

	/**
	 * Gets urls.
	 * 
	 * @return urls
	 */
	public Urls getUrls() {
		return urls;
	}

}