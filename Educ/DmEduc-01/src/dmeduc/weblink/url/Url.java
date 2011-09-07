package dmeduc.weblink.url;

import java.util.Date;

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

	private Date creationDate;

	private Date updateDate;

	private Boolean approved;

	private String category;

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

	/**
	 * Sets creation date.
	 * 
	 * @param creationDate
	 *            creationDate
	 */
	public void setCreationDate(Date date) {
		creationDate = date;
	}

	/**
	 * Gets creation date.
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets update date.
	 * 
	 * @param updateDate
	 *            updateDate
	 */
	public void setUpdateDate(Date date) {
		updateDate = date;
	}

	/**
	 * Gets update date.
	 * 
	 * @return updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
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

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

}