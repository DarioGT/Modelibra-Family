package org.modelibra.modeler.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.AttachCommand;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.DetachCommand;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-05-24
 */
public class LineModel extends ElementModel {
	
	static final long serialVersionUID = 7168319479760000280L;

	public static final Color INHERITANCE_COLOR = new Color(0, 153, 204);

	public static final Color EXTERNAL_COLOR = new Color(153, 102, 0);

	public static final String INHERITANCE_NAME = "isA";

	public static final String DEFAULT_DIRECTION_NAME = "?";

	public static final String DEFAULT_RULE = "NONE";

	protected boolean inheritance = false;

	protected boolean internal = true;
	
	protected boolean partOfManyToMany = false;

	protected String dir12Min = "0";

	protected String dir12Max = "N";

	protected boolean dir12Id = false;

	protected String dir12Name = DEFAULT_DIRECTION_NAME;

	protected boolean dir12NameVisible = true;

	protected String dir12InsertRule = DEFAULT_RULE;

	protected String dir12DeleteRule = DEFAULT_RULE;

	protected String dir12UpdateRule = DEFAULT_RULE;

	protected String dir21Min = "1";

	protected String dir21Max = "1";

	protected boolean dir21Id = false;

	protected String dir21Name = DEFAULT_DIRECTION_NAME;

	protected boolean dir21NameVisible = true;

	protected String dir21InsertRule = DEFAULT_RULE;

	protected String dir21DeleteRule = DEFAULT_RULE;

	protected String dir21UpdateRule = DEFAULT_RULE;

	protected DiagramModel diagramModel;

	protected BoxModel boxModel1;

	protected BoxModel boxModel2;

	private boolean upper = false; // for twin lines: twin1

	private boolean lower = false; // for twin lines: twin2

	public LineModel(DiagramModel aDiagramModel, BoxModel aBox1, BoxModel aBox2) {
		super();
		this.setColor(EntityModel.DEFAULT_COLOR);
		if (aBox1 == aBox2) {
			dir21Min = "0";
		}
		this.attach(aDiagramModel);
		this.attach1(aBox1);
		this.attach2(aBox2);

		if (!aBox2.getName().trim().equals("?")) {
			String box2NameInPlural = aBox2.getNameInPlural();
			this.setDir12Name(this.firstLetterToLower(box2NameInPlural));
		}
		if (!aBox1.getName().trim().equals("?")) {
			String box1Name = aBox1.getName();
			this.setDir21Name(this.firstLetterToLower(box1Name));
		}
		
		aBox2.setIntersection();
		aBox2.setValidIntersection();
	}

	public void triggerChange() {
		this.notifyObservers(new ModelEvent("triggerChange", null, null));
	}

	public boolean isInheritance() {
		return inheritance;
	}

	public void setInheritance(boolean anInheritance) {
		if (anInheritance) {
			if (boxModel2.hasInheritanceGeneralization()) {
				return;
			}
			this.setColor(INHERITANCE_COLOR);

			this.setDir12Min("0");
			this.setDir12Max("1");
			this.setDir21Min("1");
			this.setDir21Max("1");
			this.setDir21Name(INHERITANCE_NAME);
			if (!boxModel1.isAbstractDef()) {
				this.setDir21Id(true);
			}
		} else {
			this.setColor(EntityModel.DEFAULT_COLOR);
			this.setDir21Name(DEFAULT_NAME);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "inheritance",
				new Boolean(inheritance), new Boolean(anInheritance));
		command.execute();
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean anInternal) {
		if (anInternal) {
			this.setColor(EntityModel.DEFAULT_COLOR);
		} else {
			this.setColor(EXTERNAL_COLOR);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "internal", new Boolean(internal),
				new Boolean(anInternal));
		command.execute();
		BoxModel boxModel2 = getBoxModel2();
		boxModel2.setIntersection();
		boxModel2.setValidIntersection();
	}

	public boolean isExternal() {
		return !internal;
	}

	public void setExternal(boolean anExternal) {
		setInternal(!anExternal);
	}
	
	public boolean isPartOfManyToMany() {
		return partOfManyToMany;
	}

	public void setPartOfManyToMany(boolean aPartOfManyToMany) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "partOfManyToMany", new Boolean(partOfManyToMany),
				new Boolean(aPartOfManyToMany));
		command.execute();
		BoxModel boxModel2 = getBoxModel2();
		boxModel2.setManyToMany(aPartOfManyToMany);
	}

	public String getDir12Min() {
		return dir12Min;
	}

	public void setDir12Min(String aMin) {
		if (this.isReflexive()) {
			if (aMin.equals("0")) {
				Command command = new SetCommand(Manager.getSingleton()
						.getTransaction(), this, "dir12Min", dir12Min, aMin);
				command.execute();
			}
		}
		// Id 0..1 is allowed
		else if ((aMin.equals("1")) || (aMin.equals("0"))) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir12Min", dir12Min, aMin);
			command.execute();
		} else if (!this.isDir12Id()) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir12Min", dir12Min, aMin);
			command.execute();
		}
	}

	public String getDir12Max() {
		return dir12Max;
	}

	public void setDir12Max(String aMax) {
		if (this.isDir12Id()) {
			if (aMax.equals("1")) {
				Command command = new SetCommand(Manager.getSingleton()
						.getTransaction(), this, "dir12Max", dir12Max, aMax);
				command.execute();
			}
		}
		// ?..N ?..N is allowed
		else {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir12Max", dir12Max, aMax);
			command.execute();
		}
	}

	public boolean isDir12Id() {
		return dir12Id;
	}

	public void setDir12Id(boolean aDir12Id) {
		if (this.isReflexive()) {
			return;
		}
		if (!aDir12Id) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir12Id", new Boolean(dir12Id),
					new Boolean(aDir12Id));
			command.execute();
		} else if ((dir12Max.equals("1")) && (!isDir21Id())) {
			// id 0..1 is allowed
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir12Id", new Boolean(dir12Id),
					new Boolean(aDir12Id));
			command.execute();
		}
	}

	public String getDir12Name() {
		return dir12Name;
	}

	public void setDir12Name(String aName) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir12Name", dir12Name, aName);
		command.execute();
	}

	public boolean isDir12NameVisible() {
		return dir12NameVisible;
	}

	public void setDir12NameVisible(boolean aDir12NameVisible) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir12NameVisible", new Boolean(
				dir12NameVisible), new Boolean(aDir12NameVisible));
		command.execute();
	}

	public String getDir12InsertRule() {
		return dir12InsertRule;
	}

	public void setDir12InsertRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir12InsertRule", dir12InsertRule,
				aRule);
		command.execute();
	}

	public String getDir12DeleteRule() {
		return dir12DeleteRule;
	}

	public void setDir12DeleteRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir12DeleteRule", dir12DeleteRule,
				aRule);
		command.execute();
	}

	public String getDir12UpdateRule() {
		return dir12UpdateRule;
	}

	public void setDir12UpdateRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir12UpdateRule", dir12UpdateRule,
				aRule);
		command.execute();
	}

	public String getDir21Min() {
		return dir21Min;
	}

	public void setDir21Min(String aMin) {
		if (this.isReflexive()) {
			if (aMin.equals("0")) {
				Command command = new SetCommand(Manager.getSingleton()
						.getTransaction(), this, "dir21Min", dir21Min, aMin);
				command.execute();
			}
		}
		// id 0..1 is allowed
		else if ((aMin.equals("1")) || (aMin.equals("0"))) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir21Min", dir21Min, aMin);
			command.execute();
		} else if (!this.isDir21Id()) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir21Min", dir21Min, aMin);
			command.execute();
		}
	}

	public String getDir21Max() {
		return dir21Max;
	}

	public void setDir21Max(String aMax) {
		if (this.isDir21Id()) {
			if (aMax.equals("1")) {
				Command command = new SetCommand(Manager.getSingleton()
						.getTransaction(), this, "dir21Max", dir21Max, aMax);
				command.execute();
			}
		}
		// ?..N ?..N is allowed
		else {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir21Max", dir21Max, aMax);
			command.execute();
		}
	}

	public boolean isDir21Id() {
		return dir21Id;
	}

	public void setDir21Id(boolean aDir21Id) {
		if (this.isReflexive()) {
			return;
		}
		if (!aDir21Id) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir21Id", new Boolean(dir21Id),
					new Boolean(aDir21Id));
			command.execute();
		} else if ((dir21Max.equals("1")) && (!isDir12Id())) {
			// id 0..1 is allowed
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "dir21Id", new Boolean(dir21Id),
					new Boolean(aDir21Id));
			command.execute();
		}
	}

	public String getDir21Name() {
		return dir21Name;
	}

	public void setDir21Name(String aName) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir21Name", dir21Name, aName);
		command.execute();
	}

	public boolean isDir21NameVisible() {
		return dir21NameVisible;
	}

	public void setDir21NameVisible(boolean aDir21NameVisible) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir21NameVisible", new Boolean(
				dir21NameVisible), new Boolean(aDir21NameVisible));
		command.execute();
	}

	public String getDir21InsertRule() {
		return dir21InsertRule;
	}

	public void setDir21InsertRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir21InsertRule", dir21InsertRule,
				aRule);
		command.execute();
	}

	public String getDir21DeleteRule() {
		return dir21DeleteRule;
	}

	public void setDir21DeleteRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir21DeleteRule", dir21DeleteRule,
				aRule);
		command.execute();
	}

	public String getDir21UpdateRule() {
		return dir21UpdateRule;
	}

	public void setDir21UpdateRule(String aRule) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dir21UpdateRule", dir21UpdateRule,
				aRule);
		command.execute();
	}

	public boolean isDir12Child() {
		if (this.getDir12Max().equals("1")) {
			return true;
		}
		return false;
	}

	public boolean isDir21Child() {
		if (this.getDir21Max().equals("1")) {
			return true;
		}
		return false;
	}

	/*
	 * public boolean isDir12Child() { if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("1") ) && (this.getDir21Min().equals("1") ) &&
	 * (this.getDir21Max().equals("1") ) ) { if (this.isDir12Id()) { return
	 * true; // dir12 child; dir21 parent } else { return false; // dir12
	 * parent; dir21 child } } else if (this.getDir12Max().equals("1")){ return
	 * true; } return false; }
	 * 
	 * public boolean isDir21Child() { if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("1") ) && (this.getDir21Min().equals("1") ) &&
	 * (this.getDir21Max().equals("1") ) ) { if (this.isDir12Id()) { return
	 * false; // dir12 child; dir21 parent } else { return true; // dir12
	 * parent; dir21 child } } else if (this.getDir21Max().equals("1")){ return
	 * true; } return false; }
	 */

	/*
	 * public boolean isDir12Child() { return !this.isDir21Child(); }
	 * 
	 * public boolean isDir21Child() { if ( (!this.getDir12Max().equals("0") ) &&
	 * (!this.getDir12Max().equals("1") ) && (this.getDir21Max().equals("1") ) ) {
	 * return true; // dir12 parent; dir21 child } else if (
	 * (this.getDir12Max().equals("1") ) && (!this.getDir21Max().equals("0") ) &&
	 * (!this.getDir21Max().equals("1") ) ) { return false; // dir12 child;
	 * dir21 parent } else if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("0") ) && (this.getDir21Min().equals("1") ) &&
	 * (this.getDir21Max().equals("1") ) ) { return true; // dir12 parent; dir21
	 * child } else if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("1") ) && (this.getDir21Min().equals("0") ) &&
	 * (this.getDir21Max().equals("1") ) ) { return false; // dir12 child; dir21
	 * parent } else if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("0") ) && (this.getDir21Min().equals("0") ) &&
	 * (this.getDir21Max().equals("1") ) ) { return true; // dir12 parent; dir21
	 * child (by default) } else if ( (this.getDir12Max().equals("1") ) &&
	 * (this.getDir12Min().equals("1") ) && (this.getDir21Min().equals("1") ) &&
	 * (this.getDir21Max().equals("1") ) ) { if (this.isDir12Id()) { return
	 * false; // dir12 child; dir21 parent } else { return true; // dir12
	 * parent; dir21 child } } return false; }
	 */
	
	public void copy(LineModel line) {
		if (line != null) {
			// must be before the setColor
			this.setInheritance(line.isInheritance());
			this.setInternal(line.isInternal());
			this.setPartOfManyToMany(line.isPartOfManyToMany());
			this.setDir12Min(line.getDir12Min());
			this.setDir12Max(line.getDir12Max());
			this.setDir12Id(line.isDir12Id());
			this.setDir12Name(line.getDir12Name());
			this.setDir12NameVisible(line.isDir12NameVisible());
			this.setDir12InsertRule(line.getDir12InsertRule());
			this.setDir12DeleteRule(line.getDir12DeleteRule());
			this.setDir12UpdateRule(line.getDir12UpdateRule());
			this.setDir21Min(line.getDir21Min());
			this.setDir21Max(line.getDir21Max());
			this.setDir21Id(line.isDir21Id());
			this.setDir21Name(line.getDir21Name());
			this.setDir21NameVisible(line.isDir21NameVisible());
			this.setDir21InsertRule(line.getDir21InsertRule());
			this.setDir21DeleteRule(line.getDir21DeleteRule());
			this.setDir21UpdateRule(line.getDir21UpdateRule());

			this.setVisible(line.isVisible());
			this.setSelected(line.isSelected());

			this.setName(line.getName());
			this.setAlias(line.getAlias());
			this.setNameInPlural(line.getNameInPlural());
			this.setColor(line.getColor());
		}
	}

	public void copyItemsFromParentToChild() {
		if ((this.getBoxModel1() != null) && (this.getBoxModel2() != null)) {
			if (this.isDir21Child()) {
				this.getBoxModel2().deepCopyItemsToTop(this.getBoxModel1());
			} else {
				this.getBoxModel1().deepCopyItemsToTop(this.getBoxModel2());
			}
		}
	}

	public boolean isUpper() {
		return upper;
	}

	public void setUpper(boolean aValue) {
		upper = aValue;
	}

	public boolean isLower() {
		return lower;
	}

	public void setLower(boolean aValue) {
		lower = aValue;
	}

	public boolean clicked(Point beginPoint, Point endPoint,
			Point clickedPoint, Point pDelta) {
		// clicked returns true if clickedPoint is on the line between
		// beginPoint and endPoint with the error of pDelta in pixels
		Point pointDif = new Point(0, 0);
		boolean inLineRectX, inLineRectY, inLineRect;
		double coord;

		pointDif.x = endPoint.x - beginPoint.x;
		pointDif.y = endPoint.y - beginPoint.y;

		// Rapid test: Verify if the clickedPoint is in the line rectangle.
		if (pointDif.x > 0) {
			inLineRectX = (clickedPoint.x >= (beginPoint.x - pDelta.x))
					&& (clickedPoint.x <= (endPoint.x + pDelta.x));
		} else {
			inLineRectX = (clickedPoint.x >= (endPoint.x - pDelta.x))
					&& (clickedPoint.x <= (beginPoint.x + pDelta.x));
		}
		if (pointDif.y > 0) {
			inLineRectY = (clickedPoint.y >= (beginPoint.y - pDelta.y))
					&& (clickedPoint.y <= (endPoint.y + pDelta.y));
		} else {
			inLineRectY = (clickedPoint.y >= (endPoint.y - pDelta.y))
					&& (clickedPoint.y <= (beginPoint.y + pDelta.y));
		}
		inLineRect = inLineRectX && inLineRectY;
		if (!inLineRect) {
			return false;
		}

		// If the line is horizontal or vertical there is no need to continue.
		if ((pointDif.x == 0) || (pointDif.y == 0)) {
			return true;
		}

		if (Math.abs(pointDif.x) > Math.abs(pointDif.y)) {
			coord = beginPoint.y
					+ (((clickedPoint.x - beginPoint.x) * pointDif.y) / pointDif.x)
					- clickedPoint.y;
			return Math.abs(coord) <= pDelta.y;
		} else {
			coord = beginPoint.x
					+ (((clickedPoint.y - beginPoint.y) * pointDif.x) / pointDif.y)
					- clickedPoint.x;
			return Math.abs(coord) <= pDelta.x;
		}
	}

	public LineModel getTwin() {
		LineModel lineModel = null;
		Iterator i = diagramModel.getLines().iterator();
		while (i.hasNext()) {
			lineModel = (LineModel) i.next();
			if (lineModel != this) {
				if (((lineModel.getBoxModel1() == this.getBoxModel1()) && (lineModel
						.getBoxModel2() == this.getBoxModel2()))
						|| ((lineModel.getBoxModel1() == this.getBoxModel2()) && (lineModel
								.getBoxModel2() == this.getBoxModel1()))) {
					return lineModel;
				}
			}
		}
		return null;
	}

	public boolean isTwin() {
		if (this.getTwin() != null) {
			return true;
		}
		return false;
	}

	public boolean isReflexive() {
		if (this.getBoxModel1() == this.getBoxModel2()) {
			return true;
		}
		return false;
	}

	public Point getBeginPoint() {
		LineModel twinLine = this.getTwin();
		if (twinLine == null) {
			return boxModel1.getCenter();
		}
		if (this.isUpper()) {
			return boxModel1.getUpperHalfCenter();
		}
		if (this.isLower()) {
			return boxModel1.getLowerHalfCenter();
		}
		if (!twinLine.isUpper()) {
			this.setUpper(true);
			return boxModel1.getUpperHalfCenter();
		}
		this.setLower(true);
		return boxModel1.getLowerHalfCenter();
	}

	public Point getEndPoint() {
		LineModel twinLine = this.getTwin();
		if (twinLine == null) {
			return boxModel2.getCenter();
		}
		if (this.isUpper()) {
			return boxModel2.getUpperHalfCenter();
		}
		if (this.isLower()) {
			return boxModel2.getLowerHalfCenter();
		}
		if (!twinLine.isUpper()) {
			this.setUpper(true);
			return boxModel2.getUpperHalfCenter();
		}
		this.setLower(true);
		return boxModel2.getLowerHalfCenter();
	}

	public Point getPoint1() {
		if (boxModel1 != null) {
			if (this.isReflexive()) {
				LineModel twinLine = this.getTwin();
				if (twinLine != null) {
					if (this.isUpper()) {
						return boxModel1.getTopCenter();
					}
					if (this.isLower()) {
						return boxModel1.getBottomCenter();
					}
					if (!twinLine.isUpper()) {
						this.setUpper(true);
						return boxModel1.getTopCenter();
					}
					this.setLower(true);
					return boxModel1.getBottomCenter();
				}
				return boxModel1.getTopCenter();
			}
			return boxModel1.getIntersectionPoint(this.getBeginPoint().x, this
					.getBeginPoint().y, this.getEndPoint().x, this
					.getEndPoint().y);
		}
		return new Point(0, 0);
	}

	public Point getPoint2() {
		if (boxModel2 != null) {
			if (this.isReflexive()) {
				LineModel twinLine = this.getTwin();
				if (twinLine != null) {
					if (this.isUpper()) {
						return boxModel1.getTopCenterAbove();
					}
					if (this.isLower()) {
						return boxModel1.getBottomCenterBelow();
					}
					if (!twinLine.isUpper()) {
						this.setUpper(true);
						return boxModel1.getTopCenterAbove();
					}
					this.setLower(true);
					return boxModel1.getBottomCenterBelow();
				}
				return boxModel1.getTopCenterAbove();
			}
			return boxModel2.getIntersectionPoint(this.getEndPoint().x, this
					.getEndPoint().y, this.getBeginPoint().x, this
					.getBeginPoint().y);
		}
		return new Point(0, 0);
	}

	public Point getDir12PointMinMax() {
		int x = 0;
		int y = 0;
		if ((boxModel1 != null) && (boxModel2 != null)) {
			int x1 = this.getPoint1().x;
			int x2 = this.getPoint2().x;
			int y1 = this.getPoint1().y;
			int y2 = this.getPoint2().y;
			if (x1 <= x2) {
				x = x1 + 1 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 1 * ((y2 - y1) / 8);
				} else {
					y = y2 + 7 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 7 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 1 * ((y2 - y1) / 8);
				} else {
					y = y2 + 7 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

	public Point getDir12PointName() {
		int x = 0;
		int y = 0;
		if ((boxModel1 != null) && (boxModel2 != null)) {
			int x1 = this.getPoint1().x;
			int x2 = this.getPoint2().x;
			int y1 = this.getPoint1().y;
			int y2 = this.getPoint2().y;
			if (x1 <= x2) {
				x = x1 + 3 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 3 * ((y2 - y1) / 8);
				} else {
					y = y2 + 5 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 5 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 3 * ((y2 - y1) / 8);
				} else {
					y = y2 + 5 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

	public Point getCenterPoint() {
		int x = 0;
		int y = 0;
		if ((boxModel1 != null) && (boxModel2 != null)) {
			int x1 = this.getPoint1().x;
			int x2 = this.getPoint2().x;
			int y1 = this.getPoint1().y;
			int y2 = this.getPoint2().y;
			if (x1 <= x2) {
				x = x1 + ((x2 - x1) / 2);
				if (y1 <= y2) {
					y = y1 + ((y2 - y1) / 2);
				} else {
					y = y2 + ((y1 - y2) / 2);
				}
			} else {
				x = x2 + ((x1 - x2) / 2);
				if (y1 <= y2) {
					y = y1 + ((y2 - y1) / 2);
				} else {
					y = y2 + ((y1 - y2) / 2);
				}
			}
		}
		return new Point(x, y);
	}

	public Point getDir21PointMinMax() {
		int x = 0;
		int y = 0;
		if ((boxModel1 != null) && (boxModel2 != null)) {
			int x1 = this.getPoint1().x;
			int x2 = this.getPoint2().x;
			int y1 = this.getPoint1().y;
			int y2 = this.getPoint2().y;
			if (x1 <= x2) {
				x = x1 + 7 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 7 * ((y2 - y1) / 8);
				} else {
					y = y2 + 1 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 1 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 7 * ((y2 - y1) / 8);
				} else {
					y = y2 + 1 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

	public Point getDir21PointName() {
		int x = 0;
		int y = 0;
		if ((boxModel1 != null) && (boxModel2 != null)) {
			int x1 = this.getPoint1().x;
			int x2 = this.getPoint2().x;
			int y1 = this.getPoint1().y;
			int y2 = this.getPoint2().y;
			if (x1 <= x2) {
				x = x1 + 5 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 5 * ((y2 - y1) / 8);
				} else {
					y = y2 + 3 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 3 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 5 * ((y2 - y1) / 8);
				} else {
					y = y2 + 3 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

	public DiagramModel getDiagramModel() {
		return diagramModel;
	}

	public void attach(DiagramModel aDiagramModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aDiagramModel, this,
				(aDiagramModel == null) ? null : aDiagramModel.getLines(),
				"lines", "diagramModel");
		command.execute();
	}

	public void detach(DiagramModel aDiagramModel) {
		this.detach1(this.getBoxModel1());
		this.detach2(this.getBoxModel2());
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aDiagramModel, this,
				(aDiagramModel == null) ? null : aDiagramModel.getLines(),
				"lines", "diagramModel");
		command.execute();
	}

	public BoxModel getBoxModel1() {
		return boxModel1;
	}

	public void attach1(BoxModel aBoxModel1) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel1, this,
				(aBoxModel1 == null) ? null : aBoxModel1.getLines1(), "lines1",
				"boxModel1");
		command.execute();
	}

	public void detach1(BoxModel aBoxModel1) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel1, this,
				(aBoxModel1 == null) ? null : aBoxModel1.getLines1(), "lines1",
				"boxModel1");
		command.execute();
		// this.detach(this.getDiagramModel());
		// this.detach2(this.getBoxModel2());
	}

	public BoxModel getBoxModel2() {
		return boxModel2;
	}

	public void attach2(BoxModel aBoxModel2) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel2, this,
				(aBoxModel2 == null) ? null : aBoxModel2.getLines2(), "lines2",
				"boxModel2");
		command.execute();
	}

	public void detach2(BoxModel aBoxModel2) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel2, this,
				(aBoxModel2 == null) ? null : aBoxModel2.getLines2(), "lines2",
				"boxModel2");
		command.execute();
		// this.detach(this.getDiagramModel());
		// this.detach1(this.getBoxModel1());
		aBoxModel2.setIntersection();
		aBoxModel2.setValidIntersection();
	}

}
