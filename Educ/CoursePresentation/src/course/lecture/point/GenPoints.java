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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import course.lecture.slide.Slide;

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Point generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-16
 */
public abstract class GenPoints extends Entities<Point> {

	private static final long serialVersionUID = 1176413492940L;

	private static Log log = LogFactory.getLog(GenPoints.class);

	/* ======= internal parent neighbors ======= */

	private Slide slide;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs points within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPoints(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs points for the slide parent.
	 * 
	 * @param slide
	 *            slide
	 */
	public GenPoints(Slide slide) {
		this(slide.getModel());
		// parent
		setSlide(slide);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the point with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return point
	 */
	public Point getPoint(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the point with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return point
	 */
	public Point getPoint(Long oidUniqueNumber) {
		return getPoint(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first point whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return point
	 */
	public Point getPoint(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects points whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return points
	 */
	public Points getPoints(String propertyCode, Object property) {
		return (Points) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets points ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered points
	 */
	public Points getPoints(String propertyCode, boolean ascending) {
		return (Points) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets points selected by a selector. Returns empty points if there are no
	 * points that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected points
	 */
	public Points getPoints(ISelector selector) {
		return (Points) selectBySelector(selector);
	}

	/**
	 * Gets points ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered points
	 */
	public Points getPoints(Comparator comparator, boolean ascending) {
		return (Points) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets text points.
	 * 
	 * @param text
	 *            text
	 * @return text points
	 */
	public Points getTextPoints(String text) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineEqual(text);
		return getPoints(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets number point.
	 * 
	 * @param number
	 *            number
	 * @return number point
	 */
	public Point getNumberPoint(Integer number) {
		PropertySelector propertySelector = new PropertySelector("number");
		propertySelector.defineEqual(number);
		List<Point> list = getPoints(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets points ordered by number.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered points
	 */
	public Points getPointsOrderedByNumber(boolean ascending) {
		return getPoints("number", ascending);
	}

	/**
	 * Gets points ordered by text.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered points
	 */
	public Points getPointsOrderedByText(boolean ascending) {
		return getPoints("text", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

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

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates point.
	 * 
	 * @param slideParent
	 *            slide parent
	 * @param text
	 *            text
	 * @return point
	 */
	public Point createPoint(Slide slideParent, String text) {
		Point point = new Point(getModel());
		point.setSlide(slideParent);
		point.setText(text);
		if (!add(point)) {
			point = null;
		}
		return point;
	}

}