package org.modelibra.persistency.xml.notes.comment;

import java.util.Date;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Comment entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-23
 */
public class Comment extends Entity<Comment> {

	private String text;

	private String source;

	private Date creationDate = new Date();

	/**
	 * Constructs comment within the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Comment(IDomainModel model) {
		super(model);
	}

	/**
	 * Sets text.
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets source.
	 * 
	 * @param source
	 *            source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Gets source.
	 * 
	 * @return source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets creation date.
	 * 
	 * @param creationDate
	 *            creation date
	 */
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}

	/**
	 * Gets creation date.
	 * 
	 * @return creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

}