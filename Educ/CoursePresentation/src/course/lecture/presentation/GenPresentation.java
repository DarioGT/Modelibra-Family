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
package course.lecture.presentation;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.lecture.slide.Slides;

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Presentation generated entity. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenPresentation extends Entity<Presentation> {

	private static final long serialVersionUID = 1176413488810L;

	private static Log log = LogFactory.getLog(GenPresentation.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String title;

	private String author;

	private String organisation;

	private Date creationDate;

	private String objective;

	private String description;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Slides slides;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs presentation within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPresentation(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setSlides(new Slides((Presentation) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets title.
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets author.
	 * 
	 * @param author
	 *            author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets author.
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets organisation.
	 * 
	 * @param organisation
	 *            organisation
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	/**
	 * Gets organisation.
	 * 
	 * @return organisation
	 */
	public String getOrganisation() {
		return organisation;
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
	 * Sets objective.
	 * 
	 * @param objective
	 *            objective
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * Gets objective.
	 * 
	 * @return objective
	 */
	public String getObjective() {
		return objective;
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

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets slides.
	 * 
	 * @param slides
	 *            slides
	 */
	public void setSlides(Slides slides) {
		this.slides = slides;
		if (slides != null) {
			slides.setPresentation((Presentation) this);
		}
	}

	/**
	 * Gets slides.
	 * 
	 * @return slides
	 */
	public Slides getSlides() {
		return slides;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}