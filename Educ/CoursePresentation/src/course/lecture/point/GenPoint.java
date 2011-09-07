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
package course.lecture.point;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.lecture.slide.Slide;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Point generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-16
 */
public abstract class GenPoint extends Entity<Point> {

	private static final long serialVersionUID = 1176413492939L;

	private static Log log = LogFactory.getLog(GenPoint.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String text;

	private String explanation;

	private String url;

	private String image;

	private String comment;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Slide slide;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs point within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPoint(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs point within its parent(s).
	 * 
	 * @param Slide
	 *            slide
	 */
	public GenPoint(Slide slide) {
		this(slide.getModel());
		// parents
		setSlide(slide);
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
	 * Sets explanation.
	 * 
	 * @param explanation
	 *            explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * Gets explanation.
	 * 
	 * @return explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Sets url.
	 * 
	 * @param url
	 *            url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets url.
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets image.
	 * 
	 * @param image
	 *            image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets image.
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets comment.
	 * 
	 * @param comment
	 *            comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets slide.
	 * 
	 * @param slide
	 *            slide
	 */
	public void setSlide(Slide slide) {
		this.slide = slide;
	}

	/**
	 * Gets slide.
	 * 
	 * @return slide
	 */
	public Slide getSlide() {
		return slide;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}