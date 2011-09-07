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
package dmeduc.weblink.question;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Question generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-07
 */
public abstract class GenQuestion extends Entity<Question> {

	private static final long serialVersionUID = 1171896744338L;

	private static Log log = LogFactory.getLog(GenQuestion.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String type;

	private String text;

	private String response;

	private Date creationDate;

	private Float points;

	/* ======= reference properties ======= */

	private Long categoryOid;

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	transient private Category category;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs question within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestion(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs question within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public GenQuestion(Category category) {
		this(category.getModel());
		// parents
		setCategory(category);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets number.
	 * 
	 * @param number
	 *            number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets number.
	 * 
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets type.
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
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
	 * Sets response.
	 * 
	 * @param response
	 *            response
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * Gets response.
	 * 
	 * @return response
	 */
	public String getResponse() {
		return response;
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
	 * Sets points.
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(Float points) {
		this.points = points;
	}

	/**
	 * Gets points.
	 * 
	 * @return points
	 */
	public Float getPoints() {
		return points;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets categoryOid.
	 * 
	 * @param categoryOid
	 *            categoryOid
	 */
	public void setCategoryOid(Long categoryOid) {
		this.categoryOid = categoryOid;
		category = null;
	}

	/**
	 * Gets categoryOid.
	 * 
	 * @return categoryOid
	 */
	public Long getCategoryOid() {
		return categoryOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			categoryOid = category.getOid().getUniqueNumber();
		} else {
			categoryOid = null;
		}
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		if (category == null) {
			WebLink webLink = (WebLink) getModel();
			Categories categories = webLink.getCategories();
			if (categoryOid != null) {
				category = categories.getReflexiveCategory(categoryOid);
			}
		}
		return category;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}