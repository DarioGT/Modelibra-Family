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
package dmeduc.weblink.interest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.member.Member;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Interest generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-07
 */
public abstract class GenInterest extends Entity<Interest> {

	private static final long serialVersionUID = 1194454872532L;

	private static Log log = LogFactory.getLog(GenInterest.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String description;

	/* ======= reference properties ======= */

	private Long categoryOid;

	/* ======= internal parent neighbors ======= */

	private Member member;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	transient private Category category;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs interest within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInterest(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs interest within its parent(s).
	 * 
	 * @param Member
	 *            member
	 * @param Category
	 *            category
	 */
	public GenInterest(Member member, Category category) {
		this(category.getModel());
		// parents
		setMember(member);
		setCategory(category);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

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

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		return member;
	}

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