/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package dmeduc.weblink.url;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dmeduc.weblink.category.Category;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Url generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public abstract class GenUrl extends Entity<Url> {

	private static final long serialVersionUID = 1171894920490L;

	private static Log log = LogFactory.getLog(GenUrl.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String link;

	private String description;

	private Date creationDate;

	private Date updateDate;

	private Boolean approved;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Category category;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs url within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenUrl(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs url within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public GenUrl(Category category) {
		this(category.getModel());
		// parents
		setCategory(category);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
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
	 * Sets creationDate.
	 * 
	 * @param creationDate
	 *            creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets creationDate.
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets updateDate.
	 * 
	 * @param updateDate
	 *            updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Gets updateDate.
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

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}