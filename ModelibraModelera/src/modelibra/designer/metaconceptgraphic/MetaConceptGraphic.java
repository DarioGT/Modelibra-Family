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
package modelibra.designer.metaconceptgraphic;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import modelibra.designer.metaconcept.MetaConcept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaConceptGraphic specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaConceptGraphic extends GenMetaConceptGraphic {

	private static final long serialVersionUID = 1208025959033L;

	private static Log log = LogFactory.getLog(MetaConceptGraphic.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConceptGraphic within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaConceptGraphic(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaConceptGraphic within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaConcept
	 */
	public MetaConceptGraphic(MetaConcept metaConcept) {
		super(metaConcept);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Rectangle getRectangle() {
		return new Rectangle(getLocation(), getSize());
	}

	public void setLocation(Point point) {
		Double xDouble = point.getX();
		Integer x = new Integer(xDouble.intValue());
		setX(x);

		Double yDouble = point.getY();
		Integer y = new Integer(yDouble.intValue());
		setY(y);
	}

	public Point getLocation() {
		Integer xInteger = getX();
		Integer yInteger = getY();
		return new Point(xInteger, yInteger);
	}

	public Point getCenter() {
		int cx = getLocation().x + (getSize().width / 2);
		int cy = getLocation().y + getSize().height / 2;
		return new Point(cx, cy);
	}

	public void setSize(Dimension dimension) {
		Double widthDouble = dimension.getWidth();
		Integer width = new Integer(widthDouble.intValue());
		setWidth(width);

		Double heightDouble = dimension.getWidth();
		Integer height = new Integer(heightDouble.intValue());
		setHeight(height);
	}

	public Dimension getSize() {
		Integer widthInteger = getWidth();
		Integer heightInteger = getHeight();
		return new Dimension(widthInteger, heightInteger);
	}

	public void increaseWidth(int increment) {
		int width = getWidth().intValue();
		setWidth(new Integer(width + increment));
	}

	public void increaseHeight(int increment) {
		int height = getHeight().intValue();
		setHeight(new Integer(height + increment));
	}

	public void increaseSize(int increment) {
		increaseWidth(increment);
		increaseHeight(increment);
	}

	public void decreaseWidth(int increment) {
		int width = getWidth().intValue();
		int decreasedWidth = width - increment;
		if (decreasedWidth < 64) {
			decreasedWidth = 64;
		}
		setWidth(new Integer(decreasedWidth));
	}

	public void decreaseHeight(int increment) {
		int height = getHeight().intValue();
		int decreasedHeight = height - increment;
		if (decreasedHeight < 64) {
			decreasedHeight = 64;
		}
		setHeight(new Integer(decreasedHeight));
	}

	public void decreaseSize(int increment) {
		decreaseWidth(increment);
		decreaseHeight(increment);
	}

	public String getTitle() {
		return getMetaConcept().getCode();
	}

	/*
	 * Return the intersection point of the line <x1,y1>, <x2,y2> with the box;
	 * <x1,y1> is inside the box, <x2,y2> may be inside or outside. Fast
	 * algorithm.
	 */
	private Point getIntersectionPoint(int x1, int y1, int x2, int y2) {
		Rectangle rect = this.getRectangle();
		if (x2 == x1) /* vertical line */
			return new Point(x2, (y2 < y1 ? rect.y : rect.y + rect.height));
		if (y2 == y1) /* horizontal line */
			return new Point((x2 < x1 ? rect.x : rect.x + rect.width), y2);

		double m = (double) (y2 - y1) / (x2 - x1);
		int x = (x2 < x1 ? rect.x : rect.x + rect.width);
		double fy = m * (x - x2) + y2;
		int y;
		/* float comparison, because fy may be bigger than the biggest integer */
		if (fy >= rect.y && fy <= rect.y + rect.height)
			y = (int) fy;
		else {
			y = (y2 < y1 ? rect.y : rect.y + rect.height);
			x = (int) ((y - y2) / m) + x2;
		}
		return new Point(x, y);
	}

	public Point getIntersectionPoint(Point beginPoint, Point endPoint) {
		return getIntersectionPoint(beginPoint.x, beginPoint.y, endPoint.x,
				endPoint.y);
	}

}