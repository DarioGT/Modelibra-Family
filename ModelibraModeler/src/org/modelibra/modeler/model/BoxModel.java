package org.modelibra.modeler.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.AddCommand;
import org.modelibra.modeler.model.action.AttachCommand;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.DetachCommand;
import org.modelibra.modeler.model.action.DownCommand;
import org.modelibra.modeler.model.action.RemoveCommand;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.action.UpCommand;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-05-24
 */
public class BoxModel extends ElementModel {

	static final long serialVersionUID = 7168319479760000090L;

	public static final Color ABSTRACT_COLOR = new Color(0, 153, 204);

	public static final Color ENTRY_COLOR = new Color(0, 153, 0);
	
	public static final Color MANY_TO_MANY_COLOR = new Color(153, 102, 0);

	public static final int DEFAULT_WIDTH = 128;

	public static final int DEFAULT_HEIGHT = 140;

	public static final String OID = "oid";

	protected boolean extension = false;

	protected String extensionName = "?";

	protected boolean abstractDef = false;

	protected boolean entry = false;
	
	protected boolean intersection = false;
	
	protected boolean validIntersection = false;
	
	protected boolean manyToMany = false;

	protected Rectangle rectangle = new Rectangle(0, 0, DEFAULT_WIDTH,
			DEFAULT_HEIGHT);

	protected DiagramModel diagramModel;

	protected Collection<ItemModel> items = new ArrayList<ItemModel>();

	// parent child
	protected Collection<LineModel> lines1 = new ArrayList<LineModel>();

	// child parent
	protected Collection<LineModel> lines2 = new ArrayList<LineModel>();

	public BoxModel(DiagramModel aDiagramModel) {
		super();
		this.attach(aDiagramModel);
	}

	public BoxModel(DiagramModel aDiagramModel, Point point, boolean oid) {
		super();
		rectangle.setLocation(point);
		this.attach(aDiagramModel);
		ItemModel item;
		if (oid) {
			item = new ItemModel(this);
			item.name = OID;
			item.oid = true;
			item.min = 1;
			TypeModel oidTypeModel = AppModel.getSingleton().getOidType();
			if (oidTypeModel != null) {
				item.attach(oidTypeModel);
			}
		}
	}

	public boolean isExtension() {
		return extension;
	}

	public void setExtension(boolean anExtension) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "extension", new Boolean(extension),
				new Boolean(anExtension));
		command.execute();
	}

	public String getExtensionName() {
		return extensionName;
	}

	public void setExtensionName(String anExtensionName) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "extensionName", extensionName,
				anExtensionName);
		command.execute();
	}

	public boolean isAbstractDef() {
		return abstractDef;
	}

	public void setAbstractDef(boolean anAbstract) {
		if (anAbstract) {
			this.setColor(ABSTRACT_COLOR);
		} else {
			this.setColor(EntityModel.DEFAULT_COLOR);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "abstractDef",
				new Boolean(abstractDef), new Boolean(anAbstract));
		command.execute();
	}

	public boolean isEntry() {
		return entry;
	}

	public void setEntry(boolean anEntry) {
		if (anEntry) {
			this.setColor(ENTRY_COLOR);
		} else {
			this.setColor(EntityModel.DEFAULT_COLOR);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "entry", new Boolean(entry),
				new Boolean(anEntry));
		command.execute();
	}

	public boolean isIntersection() {
		return intersection;
	}
	
	public void setIntersection() {
		boolean intersection = false;
		if (lines2.size() > 1) {
			// more than one parent neighbor
			intersection = true;
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "intersection", new Boolean(intersection),
				new Boolean(intersection));
		command.execute();
	}

	public boolean isValidIntersection() {
		return validIntersection;
	}
	
	public void setValidIntersection() {
		boolean validIntersection = false;
		if (isIntersection()) {
			int internalCount = 0;
			for (LineModel lineModel : lines2) {
				if (lineModel.isInternal()) {
					internalCount++;
				}
			}
			if (internalCount == 0 || internalCount == 1) {
				validIntersection = true;
			}
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "validIntersection", new Boolean(validIntersection),
				new Boolean(validIntersection));
		command.execute();
	}

	public boolean isManyToMany() {
		return manyToMany;
	}
	
	public void setManyToMany() {
		boolean manyToMany = false;
		if (isIntersection()) {
			int manyToManyCount = 0;
			for (LineModel lineModel : lines2) {
				if (lineModel.isPartOfManyToMany()) {
					manyToManyCount++;
				}
			}
			if (manyToManyCount > 1) {
				manyToMany = true;
			}
		}
		if (manyToMany) {
			this.setColor(MANY_TO_MANY_COLOR);
		} else {
			this.setColor(EntityModel.DEFAULT_COLOR);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "manyToMany", new Boolean(manyToMany),
				new Boolean(manyToMany));
		command.execute();
	}
	
	public void setManyToMany(boolean aManyToMany) {
		if (aManyToMany) {
			this.setColor(MANY_TO_MANY_COLOR);
		} else {
			this.setColor(EntityModel.DEFAULT_COLOR);
		}
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "manyToMany", new Boolean(manyToMany),
				new Boolean(aManyToMany));
		command.execute();
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle aRectangle) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "rectangle", rectangle, aRectangle);
		command.execute();
	}

	public void setRectangle(int aX, int aY, int aWidth, int aHeight) {
		if ((aX >= 0) && (aY >= 0) && (aWidth > 0) && (aHeight > 0)) {
			this.setRectangle(new Rectangle(aX, aY, aWidth, aHeight));
		}
	}

	public Dimension getSize() {
		return rectangle.getSize();
	}

	public void setSize(Dimension aDimension) {
		this.setRectangle(rectangle.x, rectangle.y, aDimension.width,
				aDimension.height);
	}

	public void setSize(int aWidth, int aHeight) {
		if ((aWidth > 0) && (aHeight > 0)) {
			this.setSize(new Dimension(aWidth, aHeight));
		}
	}

	public Point getLocation() {
		return rectangle.getLocation();
	}

	public void setLocation(Point aPoint) {
		this
				.setRectangle(aPoint.x, aPoint.y, rectangle.width,
						rectangle.height);
	}

	public void setLocation(int aX, int aY) {
		if ((aX >= 0) && (aY >= 0)) {
			this.setLocation(new Point(aX, aY));
		}
	}

	public int getX() {
		return rectangle.x;
	}

	public void setX(int aX) {
		if (aX >= 0) {
			this.setLocation(aX, rectangle.y);
		}
	}

	public int getY() {
		return rectangle.y;
	}

	public void setY(int aY) {
		if (aY >= 0) {
			this.setLocation(rectangle.x, aY);
		}
	}

	public int getWidth() {
		return rectangle.width;
	}

	public void setWidth(int aWidth) {
		if (aWidth > 0) {
			this.setSize(aWidth, rectangle.height);
		}
	}

	public int getHeight() {
		return rectangle.height;
	}

	public void setHeight(int aHeight) {
		if (aHeight > 0) {
			this.setSize(rectangle.width, aHeight);
		}
	}

	public void changeSize(int w, int h) {
		this.resizeWithTheSameCenter(this.getSize().width + w,
				this.getSize().height + h);
	}

	// Resize the box keeping the same center.
	public void resizeWithTheSameCenter(int aWidth, int aHeight) {
		int x, y;
		x = this.getLocation().x + (this.getSize().width - aWidth) / 2;
		y = this.getLocation().y + (this.getSize().height - aHeight) / 2;
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		this.setRectangle(x, y, aWidth, aHeight);
	}

	/*
	 * Return the intersection point of the line <x1,y1>, <x2,y2> with the box;
	 * <x1,y1> is inside the box, <x2,y2> may be inside or outside. Fast
	 * algorithm.
	 */
	public Point getIntersectionPoint(int x1, int y1, int x2, int y2) {
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

	public Point getCenter() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y + (this.getSize().height / 2);
		return new Point(cx, cy);
	}

	public Point getUpperHalfCenter() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y + (this.getSize().height / 4);
		return new Point(cx, cy);
	}

	public Point getLowerHalfCenter() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y + 3 * (this.getSize().height / 4);
		return new Point(cx, cy);
	}

	public Point getTopCenter() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y;
		return new Point(cx, cy);
	}

	public Point getBottomCenter() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y + this.getSize().height;
		return new Point(cx, cy);
	}

	public Point getTopCenterAbove() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y - 5 * (this.getSize().height / 8);
		return new Point(cx, cy);
	}

	public Point getBottomCenterBelow() {
		int cx = this.getLocation().x + (this.getSize().width / 2);
		int cy = this.getLocation().y + 13 * (this.getSize().height / 8);
		return new Point(cx, cy);
	}

	public DiagramModel getDiagramModel() {
		return diagramModel;
	}

	public void attach(DiagramModel aDiagramModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aDiagramModel, this,
				(aDiagramModel == null) ? null : aDiagramModel.getBoxes(),
				"boxes", "diagramModel");
		command.execute();
	}

	public void detach(DiagramModel aDiagramModel) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aDiagramModel, this,
				(aDiagramModel == null) ? null : aDiagramModel.getBoxes(),
				"boxes", "diagramModel");
		command.execute();
	}

	public Collection<ItemModel> getItems() {
		return items;
	}

	public List<ItemModel> getItemList() {
		return (List<ItemModel>) items;
	}

	public void add(ItemModel anItemModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, anItemModel, items, "items",
				"boxModel");
		command.execute();
	}

	public void remove(ItemModel anItemModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, anItemModel, items, "items",
				"boxModel");
		command.execute();
	}

	public ItemModel getItem(int ix) {
		return (ItemModel) getItemList().get(ix);
	}

	public ItemModel getItem(ItemModel item) {
		int ix = getItemList().indexOf(item);
		return this.getItem(ix);
	}

	public void moveItemToTop(ItemModel itemModel) {
		int index = getItemList().lastIndexOf(itemModel);
		for (int i = index; i > 0; i--) {
			this.moveItemUp(i);
		}
	}

	public void moveItemUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, items, "items",
				ix);
		command.execute();
	}

	public void moveItemDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, items, "items", ix);
		command.execute();
	}

	public void moveItemUp(ItemModel item) {
		int ix = getItemList().indexOf(item);
		this.moveItemUp(ix);
	}

	public void moveItemDown(ItemModel item) {
		int ix = getItemList().indexOf(item);
		this.moveItemDown(ix);
	}

	public void copy(BoxModel box) {
		if (box != null) {
			// must be before the setColor
			this.setExtension(box.isExtension());
			this.setExtensionName(box.getExtensionName());
			
			this.setAbstractDef(box.isAbstractDef());
			this.setEntry(box.isEntry());
			this.setRectangle(box.getRectangle());

			this.setVisible(box.isVisible());
			this.setSelected(box.isSelected());

			this.setName(box.getName());
			this.setNameInPlural(box.getNameInPlural());
			this.setAlias(box.getAlias());
			
			this.setColor(box.getColor());
		}
	}

	public void deepCopyItems(BoxModel box) {
		if (box != null) {
			ItemModel oldItem;
			ItemModel newItem;
			for (Iterator x = box.getItems().iterator(); x.hasNext();) {
				oldItem = (ItemModel) x.next();
				newItem = new ItemModel(this);
				newItem.attach(oldItem.getTypeModel());
				newItem.copy(oldItem);
			}
		}
	}

	public void deepCopyItemsToTop(BoxModel box) {
		if (box != null) {
			ItemModel oldItem;
			ItemModel newItem;
			ArrayList<ItemModel> boxItems = (ArrayList<ItemModel>) box
					.getItems();
			for (int i = boxItems.size() - 1; i >= 0; i--) {
				oldItem = (ItemModel) boxItems.get(i);
				newItem = new ItemModel(this);
				newItem.attach(oldItem.getTypeModel());
				newItem.copy(oldItem);
				this.moveItemToTop(newItem);
			}
		}
	}

	public Collection<LineModel> getLines1() {
		return lines1;
	}

	public List<LineModel> getLineOutList() {
		return (List<LineModel>) lines1;
	}

	public void add1(LineModel aLineModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines1, "lines1",
				"boxModel1");
		command.execute();
	}

	public void remove1(LineModel aLineModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines1, "lines1",
				"boxModel1");
		command.execute();
	}

	public LineModel getLine1(int ix) {
		return (LineModel) getLineOutList().get(ix);
	}

	public LineModel getLine1(LineModel line) {
		int ix = getLineOutList().indexOf(line);
		return this.getLine1(ix);
	}

	public void moveLine1Up(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, lines1,
				"lines1", ix);
		command.execute();
	}

	public void moveLine1Down(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, lines1, "lines1", ix);
		command.execute();
	}

	public void moveLine1Up(LineModel line) {
		int ix = getLineOutList().indexOf(line);
		this.moveLine1Up(ix);
	}

	public void moveLine1Down(LineModel line) {
		int ix = getLineOutList().indexOf(line);
		this.moveLine1Down(ix);
	}

	public Collection<LineModel> getLines2() {
		return lines2;
	}

	public List<LineModel> getLineInList() {
		return (List<LineModel>) lines2;
	}

	public List<LineModel> getExternalLineInList() {
		List<LineModel> externalLineInList = new ArrayList<LineModel>();
		for (Object lineInObject : getLineInList()) {
			LineModel lineIn = (LineModel) lineInObject;
			if (lineIn.isExternal()) {
				externalLineInList.add(lineIn);
			}
		}
		return externalLineInList;
	}

	public void add2(LineModel aLineModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines2, "lines2",
				"boxModel2");
		command.execute();
	}

	public void remove2(LineModel aLineModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines2, "lines2",
				"boxModel2");
		command.execute();
	}

	public LineModel getLine2(int ix) {
		return (LineModel) getLineInList().get(ix);
	}

	public LineModel getLine2(LineModel line) {
		int ix = getLineInList().indexOf(line);
		return this.getLine2(ix);
	}

	public void moveLine2Up(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, lines2,
				"lines2", ix);
		command.execute();
	}

	public void moveLine2Down(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, lines2, "lines2", ix);
		command.execute();
	}

	public void moveLine2Up(LineModel line) {
		int ix = getLineInList().indexOf(line);
		this.moveLine2Up(ix);
	}

	public void moveLine2Down(LineModel line) {
		int ix = getLineInList().indexOf(line);
		this.moveLine2Down(ix);
	}

	// overriden from EntityModel
	public void setName(String aName) {
		if (this.hasUniqueName(aName)) {
			super.setName(aName);
			this.updateLines();
		} else {
			this.notifyObservers(new ModelEvent("set", "name", aName));
		}
	}

	public boolean hasUniqueName(String aName) {
		BoxModel boxModel;
		Collection c = diagramModel.getBoxes();
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.getName().equals(aName)) {
				return false;
			}
		}
		return true;
	}

	public boolean hasInheritanceGeneralization() {
		LineModel lineModel;
		Collection c = lines2;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isInheritance()) {
				return true;
			}
		}
		return false;
	}

	public void selectLines() {
		LineModel lineModel;
		Collection c1 = lines1;
		for (Iterator x = c1.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.setSelected(true);
		}
		Collection c2 = lines2;
		for (Iterator x = c2.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.setSelected(true);
		}
	}

	private void updateLines() {
		LineModel lineModel;
		Collection c = lines1;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.triggerChange();
		}
		c = lines2;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.triggerChange();
		}
	}

	public boolean hasOid() {
		if (this.getOidItem() == null) {
			return false;
		}
		return true;
	}

	public ItemModel getOidItem() {
		ItemModel itemModel = null;
		Collection c = items;
		for (Iterator x = c.iterator(); x.hasNext();) {
			itemModel = (ItemModel) x.next();
			if (itemModel.isOid()) {
				return itemModel;
			}
		}
		return null;
	}

	public boolean hasId() {
		return (hasIdItem() || hasIdLine());
	}

	public boolean hasIdItem() {
		if (this.getIdItems().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasIdLine() {
		return (hasIdLine12() || hasIdLine21());
	}

	public boolean hasIdLine12() {
		if (this.getIdLines12().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasIdLine21() {
		if (this.getIdLines21().isEmpty()) {
			return false;
		}
		return true;
	}

	public Collection<ItemModel> getIdItems() {
		ItemModel itemModel = null;
		ArrayList<ItemModel> result = new ArrayList<ItemModel>();
		Collection c = items;
		for (Iterator x = c.iterator(); x.hasNext();) {
			itemModel = (ItemModel) x.next();
			if (itemModel.isId()) {
				result.add(itemModel);
			}
		}
		return result;
	}

	public Collection<LineModel> getIdLines12() {
		LineModel lineModel = null;
		ArrayList<LineModel> result = new ArrayList<LineModel>();
		Collection c = lines1;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isDir12Id()) {
				result.add(lineModel);
			}
		}
		return result;
	}

	public Collection<LineModel> getIdLines21() {
		LineModel lineModel = null;
		ArrayList<LineModel> result = new ArrayList<LineModel>();
		Collection c = lines2;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isDir21Id()) {
				result.add(lineModel);
			}
		}
		return result;
	}

	public boolean hasLine() {
		if (lines1.isEmpty() && lines2.isEmpty()) {
			return false;
		}
		return true;
	}

	public Collection<LineModel> getChildren12() {
		LineModel lineModel = null;
		ArrayList<LineModel> result = new ArrayList<LineModel>();
		Collection c = lines1;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isDir12Child()) {
				result.add(lineModel);
			}
		}
		return result;
	}

	public Collection<LineModel> getChildren21() {
		LineModel lineModel = null;
		ArrayList<LineModel> result = new ArrayList<LineModel>();
		Collection c = lines2;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isDir21Child()) {
				result.add(lineModel);
			}
		}
		return result;
	}

}
