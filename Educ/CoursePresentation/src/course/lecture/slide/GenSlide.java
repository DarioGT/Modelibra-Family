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
package course.lecture.slide;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.lecture.point.Points;
import course.lecture.presentation.Presentation;

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Slide generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenSlide extends Entity<Slide> {

	private static final long serialVersionUID = 1176413490734L;

	private static Log log = LogFactory.getLog(GenSlide.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String title;

	private String subTitle;

	private String purpose;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Presentation presentation;

	/* ======= internal child neighbors ======= */

	private Points points;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs slide within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSlide(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setPoints(new Points((Slide) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs slide within its parent(s).
	 * 
	 * @param Presentation
	 *            presentation
	 */
	public GenSlide(Presentation presentation) {
		this(presentation.getModel());
		// parents
		setPresentation(presentation);
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
	 * Sets subTitle.
	 * 
	 * @param subTitle
	 *            subTitle
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * Gets subTitle.
	 * 
	 * @return subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * Sets purpose.
	 * 
	 * @param purpose
	 *            purpose
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * Gets purpose.
	 * 
	 * @return purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets presentation.
	 * 
	 * @param presentation
	 *            presentation
	 */
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	/**
	 * Gets presentation.
	 * 
	 * @return presentation
	 */
	public Presentation getPresentation() {
		return presentation;
	}

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets points.
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(Points points) {
		this.points = points;
		if (points != null) {
			points.setSlide((Slide) this);
		}
	}

	/**
	 * Gets points.
	 * 
	 * @return points
	 */
	public Points getPoints() {
		return points;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}