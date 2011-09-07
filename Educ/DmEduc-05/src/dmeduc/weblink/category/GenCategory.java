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
package dmeduc.weblink.category;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.question.Questions;
import dmeduc.weblink.url.Urls;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Category generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public abstract class GenCategory extends Entity<Category> {

	private static final long serialVersionUID = 1171894920503L;

	private static Log log = LogFactory.getLog(GenCategory.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String description;

	private Boolean approved;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Category category;

	/* ======= internal child neighbors ======= */

	private Urls urls;

	private Categories categories;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Questions questions;

	/* ======= base constructor ======= */

	/**
	 * Constructs category within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setUrls(new Urls((Category) this));
		setCategories(new Categories((Category) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs category within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public GenCategory(Category category) {
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

	/**
	 * Sets urls.
	 * 
	 * @param urls
	 *            urls
	 */
	public void setUrls(Urls urls) {
		this.urls = urls;
		if (urls != null) {
			urls.setCategory((Category) this);
		}
	}

	/**
	 * Gets urls.
	 * 
	 * @return urls
	 */
	public Urls getUrls() {
		return urls;
	}

	/**
	 * Sets categories.
	 * 
	 * @param categories
	 *            categories
	 */
	public void setCategories(Categories categories) {
		this.categories = categories;
		if (categories != null) {
			categories.setCategory((Category) this);
		}
	}

	/**
	 * Gets categories.
	 * 
	 * @return categories
	 */
	public Categories getCategories() {
		return categories;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets questions.
	 * 
	 * @param questions
	 *            questions
	 */
	public void setQuestions(Questions questions) {
		this.questions = questions;
		if (questions != null) {
			questions.setCategory((Category) this);
		}
	}

	/**
	 * Gets questions.
	 * 
	 * @return questions
	 */
	public Questions getQuestions() {
		if (questions == null) {
			WebLink webLink = (WebLink) getModel();
			Questions questions = webLink.getQuestions();
			setQuestions(questions.getCategoryQuestions((Category) this));
		}
		return questions;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}