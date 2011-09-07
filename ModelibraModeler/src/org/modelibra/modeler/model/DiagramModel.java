package org.modelibra.modeler.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
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
import org.modelibra.modeler.model.ref.Oid;
import org.modelibra.modeler.util.MathPlus;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-30
 */
public class DiagramModel extends EntityModel {

	static final long serialVersionUID = 7168319479760000170L;

	private static final Color DIAGRAM_BACKGROUND_COLOR = Color.WHITE;

	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	
	public static final String DEFAULT_PERSISTENCE = "xml";
	
	protected String author = "?";

	protected boolean extension = false;

	protected String extensionName = "?";
	
	protected boolean abstractDef = false;
	
	protected String persistence = DEFAULT_PERSISTENCE;

	protected boolean oidCreated = AppModel.getSingleton().isOidCreated();

	protected boolean dirNameDisplayed = AppModel.getSingleton()
			.isDirNameDisplayed();

	protected Dimension size = DEFAULT_SIZE;

	protected AppModel appModel;

	protected Collection<BoxModel> boxes = new ArrayList<BoxModel>();

	protected Collection<LineModel> lines = new ArrayList<LineModel>();

	public DiagramModel(AppModel anAppModel) {
		super();
		this.setColor(DIAGRAM_BACKGROUND_COLOR);
		this.attach(anAppModel);
		this.setName(anAppModel.getName());
		this.setAuthor(anAppModel.getAuthor());
		this.setAlias(this.allLettersToLower(anAppModel.getName()));
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String anAuthor) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "author", author, anAuthor);
		command.execute();
	}

	public boolean isExtension() {
		return extension;
	}
	
	public String getExtensionString() {
		return new Boolean(extension).toString();
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
	
	public String getAbstractDefString() {
		return new Boolean(abstractDef).toString();
	}

	public void setAbstractDef(boolean anAbstract) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "abstractDef",
				new Boolean(abstractDef), new Boolean(anAbstract));
		command.execute();
	}
	
	public String getPersistence() {
		return persistence;
	}

	public void setPersistence(String aPersistence) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "persistence", persistence, aPersistence);
		command.execute();
	}

	public boolean isOidCreated() {
		return oidCreated;
	}

	public void setOidCreated(boolean aValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "oidCreated", new Boolean(oidCreated),
				new Boolean(aValue));
		command.execute();
	}

	public boolean isDirNameDisplayed() {
		return dirNameDisplayed;
	}

	public void setDirNameDisplayed(boolean aValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dirNameDisplayed", new Boolean(
				dirNameDisplayed), new Boolean(aValue));
		command.execute();
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension dimension) {
		if ((dimension.width > 0) && (dimension.height > 0)) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "size", size, dimension);
			command.execute();
		}
	}

	public void setSize(int width, int height) {
		this.setSize(new Dimension(width, height));
	}

	public int getWidth() {
		return size.width;
	}

	public void setWidth(int width) {
		this.setSize(width, size.height);
	}

	public int getHeight() {
		return size.height;
	}

	public void setHeight(int height) {
		this.setSize(size.width, height);
	}

	public AppModel getAppModel() {
		return appModel;
	}

	public Collection<BoxModel> getBoxes() {
		return boxes;
	}

	public List<BoxModel> getBoxList() {
		return (List<BoxModel>) boxes;
	}

	public ArrayList<BoxModel> getBoxArrayList() {
		return (ArrayList<BoxModel>) boxes;
	}

	public void add(BoxModel aBoxModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, aBoxModel, boxes, "boxes",
				"diagramModel");
		command.execute();
	}

	public void remove(BoxModel aBoxModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, aBoxModel, boxes, "boxes",
				"diagramModel");
		command.execute();
	}

	public void copy(DiagramModel diagramModel) {
		if (diagramModel != null) {
			this.setAuthor(diagramModel.getAuthor());
			this.setExtension(diagramModel.isExtension());
			this.setExtensionName(diagramModel.getExtensionName());
			this.setAbstractDef(diagramModel.isAbstractDef());
			this.setPersistence(diagramModel.getPersistence());
			this.setOidCreated(diagramModel.isOidCreated());
			this.setDirNameDisplayed(diagramModel.isDirNameDisplayed());
			this.setSize(diagramModel.getSize());

			this.setName(diagramModel.getName() + "?");
			this.setNameInPlural(diagramModel.getNameInPlural());
			this.setAlias(diagramModel.getAlias());
			this.setColor(diagramModel.getColor());
		}
	}

	public BoxModel getBox(String name) {
		if (name.equals(EntityModel.DEFAULT_NAME)) {
			return null;
		}
		BoxModel box = null;
		Collection c = boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			box = (BoxModel) x.next();
			if (box.getName().equals(name)) {
				return box;
			}
		}
		return null;
	}

	public BoxModel getBox(int ix) {
		return (BoxModel) getBoxList().get(ix);
	}

	public BoxModel getBox(Oid oid) {
		BoxModel boxModel;
		Collection c = this.boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.getOid().equals(oid)) {
				return boxModel;
			}
		}
		return null;
	}

	public void moveBoxUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, boxes, "boxes",
				ix);
		command.execute();
	}

	public void moveBoxDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, boxes, "boxes", ix);
		command.execute();
	}

	public void moveBoxUp(BoxModel box) {
		int ix = getBoxList().indexOf(box);
		this.moveBoxUp(ix);
	}

	public void moveBoxDown(BoxModel box) {
		int ix = getBoxList().indexOf(box);
		this.moveBoxDown(ix);
	}

	public boolean hasBox(BoxModel box) {
		BoxModel boxModel;
		for (Iterator x = boxes.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (box == boxModel) {
				return true;
			}
		}
		return false;
	}

	public boolean hasUnnamedBox() {
		BoxModel boxModel;
		for (Iterator x = boxes.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.getName().equals(EntityModel.DEFAULT_NAME)) {
				return true;
			}
		}
		return false;
	}

	// overriden from EntityModel
	public void setName(String aName) {
		if (this.hasUniqueName(aName)) {
			super.setName(aName);
		}
	}

	public boolean hasUniqueName(String aName) {
		DiagramModel diagramModel;
		Collection c = appModel.getDiagrams();
		for (Iterator x = c.iterator(); x.hasNext();) {
			diagramModel = (DiagramModel) x.next();
			if (diagramModel.getName().equals(aName)) {
				return false;
			}
		}
		return true;
	}

	public Collection getLines() {
		return lines;
	}

	public List<LineModel> getLineList() {
		return (List<LineModel>) lines;
	}

	public void add(LineModel aLineModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines, "lines",
				"diagramModel");
		command.execute();
	}

	public void remove(LineModel aLineModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, aLineModel, lines, "lines",
				"diagramModel");
		command.execute();
	}

	public LineModel getLine(int ix) {
		return (LineModel) getLineList().get(ix);
	}

	public LineModel getLine(LineModel line) {
		int ix = getLineList().indexOf(line);
		return this.getLine(ix);
	}

	public void moveLineUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, lines, "lines",
				ix);
		command.execute();
	}

	public void moveLineDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, lines, "lines", ix);
		command.execute();
	}

	public void moveLineUp(LineModel line) {
		int ix = getLineList().indexOf(line);
		this.moveLineUp(ix);
	}

	public void moveLineDown(LineModel line) {
		int ix = getLineList().indexOf(line);
		this.moveLineDown(ix);
	}

	public void reinit() {
		this.removeAllLines();
		this.removeAllBoxes();
		this.setName("");
		this.setColor(EntityModel.DEFAULT_COLOR);
		this.notifyObservers(new ModelEvent("reinit", null, null));
	}

	public void hideSelectedLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				lineModel.setVisible(false);
			}
		}
	}

	public void showSelectedLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				lineModel.setVisible(true);
			}
		}
	}

	public void removeAllLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			x.remove();
			lineModel.detach(this);
		}
	}

	public void selectAllLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (!lineModel.isSelected()) {
				lineModel.setSelected(true);
				;
			}
		}
	}

	public void deselectAllLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				lineModel.setSelected(false);
			}
		}
	}

	public void deepCopyLines(DiagramModel diagram) {
		if (appModel.hasDiagram(diagram)) {
			LineModel oldLine;
			LineModel newLine;
			BoxModel oldBox1;
			BoxModel newBox1;
			BoxModel oldBox2;
			BoxModel newBox2;
			for (Iterator x = diagram.getLines().iterator(); x.hasNext();) {
				oldLine = (LineModel) x.next();
				oldBox1 = oldLine.getBoxModel1();
				oldBox2 = oldLine.getBoxModel2();
				newBox1 = this.getBox(oldBox1.getName());
				newBox2 = this.getBox(oldBox2.getName());
				if ((newBox1 != null) && (newBox2 != null)) {
					newLine = new LineModel(this, newBox1, newBox2);
					newLine.copy(oldLine);
				}
			}
		}
	}

	public void colorSelectedLines(Color aColor) {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				lineModel.setColor(aColor);
			}
		}
	}

	public void removeSelectedLines() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				x.remove();
				lineModel.detach(this);
			}
		}
	}

	public void copyItemsFromParentToChild() {
		LineModel lineModel;
		Collection c = lines;
		for (Iterator x = c.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (lineModel.isSelected()) {
				lineModel.copyItemsFromParentToChild();
			}
		}
	}

	public void tileBoxes() {
		int nbBoxesOnOneEdge = (int) Math.sqrt(boxes.size());
		int i = 0;
		BoxModel boxModel;
		Collection c = boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			boxModel.setLocation(MathPlus.mod(i, nbBoxesOnOneEdge)
					* (boxModel.getWidth() + 80) + 40,
					((int) i / nbBoxesOnOneEdge) * (boxModel.getHeight() + 80)
							+ 40);
			i++;
		}
	}

	public void createBoxes(int no) {
		for (int i = 0; i < no; i++) {
			if (this.isOidCreated()) {
				new BoxModel(this, new Point(0, 0), true);
			} else {
				new BoxModel(this, new Point(0, 0), false);
			}
		}
		this.tileBoxes();
	}

	public void createSequence() {
		if (this.isOidCreated()) {
			BoxModel sequenceBox = this.getBox("DrdbSequence");
			if (sequenceBox == null) {
				sequenceBox = new BoxModel(this, new Point(10, 10), false);
				sequenceBox.setName("DrdbSequence");
				sequenceBox.setHeight(88);
				add(sequenceBox);
				ItemModel classNameItem = new ItemModel(sequenceBox);
				classNameItem.setName("seqName");
				classNameItem.setMin(1);
				classNameItem.setOid(true);
				TypeModel classNameType = AppModel.getSingleton().getType(
						"Varchar");
				classNameItem.attach(classNameType);
				ItemModel seqItem = new ItemModel(sequenceBox);
				seqItem.setName("seq");
				seqItem.setMin(1);
				TypeModel seqType = AppModel.getSingleton().getType("Integer");
				seqItem.attach(seqType);
			}
		}
	}

	public int createRules() {
		LineModel line;
		Collection c = this.getLines();
		int counter = 0;
		for (Iterator x = c.iterator(); x.hasNext();) {
			line = (LineModel) x.next();
			if ((!line.getBoxModel1().isAbstractDef())
					&& (!line.getBoxModel2().isAbstractDef())) {
				if (line.isDir21Child()) {
					// dir12 parent; dir21 child
					if (line.getDir12DeleteRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir12DeleteRule("RESTRICT");
						counter++;
					}
					if (line.getDir21InsertRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir21InsertRule("RESTRICT");
						counter++;
					}
					if (line.getDir21UpdateRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir21UpdateRule("RESTRICT");
						counter++;
					}
				} else if (line.isDir12Child()) {
					// dir21 parent; dir12 child
					if (line.getDir21DeleteRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir21DeleteRule("CASCADE");
						counter++;
					}
					if (line.getDir12InsertRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir12InsertRule("RESTRICT");
						counter++;
					}
					if (line.getDir12UpdateRule()
							.equals(LineModel.DEFAULT_RULE)) {
						line.setDir12UpdateRule("RESTRICT");
						counter++;
					}
				}
			}
		} // end of for
		return counter;
	}

	public void removeAllBoxes() {
		BoxModel boxModel;
		Collection c = (Collection) getBoxArrayList().clone();
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			this.remove(boxModel);
		}
	}

	public void selectAllBoxes() {
		BoxModel boxModel;
		Collection c = boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (!boxModel.isSelected()) {
				boxModel.setSelected(true);
				;
			}
		}
	}

	public void deselectAllBoxes() {
		BoxModel boxModel;
		Collection c = boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.isSelected()) {
				boxModel.setSelected(false);
			}
		}
	}

	public void deepCopyBoxes(DiagramModel diagram) {
		if (appModel.hasDiagram(diagram)) {
			BoxModel oldBox;
			BoxModel newBox;
			for (Iterator x = diagram.getBoxes().iterator(); x.hasNext();) {
				oldBox = (BoxModel) x.next();
				newBox = new BoxModel(this, new Point(0, 0), false);
				newBox.deepCopyItems(oldBox);
				newBox.copy(oldBox);
			}
		}
	}

	public void copyBoxesWithNamesOnly(DiagramModel diagram) {
		if (appModel.hasDiagram(diagram)) {
			BoxModel oldBox;
			BoxModel newBox;
			for (Iterator x = diagram.getBoxes().iterator(); x.hasNext();) {
				oldBox = (BoxModel) x.next();
				newBox = new BoxModel(this, new Point(0, 0), false);
				newBox.copy(oldBox);
				newBox.setExtension(true);
				newBox.setExtensionName(oldBox.getName());
			}
		}
	}

	public void colorSelectedBoxes(Color aColor) {
		BoxModel boxModel;
		Collection c = boxes;
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.isSelected()) {
				boxModel.setColor(aColor);
			}
		}
	}

	public void removeSelectedBoxes() {
		BoxModel boxModel;
		Collection c = (Collection) getBoxArrayList().clone();
		for (Iterator x = c.iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			if (boxModel.isSelected()) {
				this.remove(boxModel);
			}
		}
	}

	public void selectLinesBetweenBoxes(Collection boxes) {
		LineModel lineModel;
		for (Iterator x = lines.iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			if (boxes.contains(lineModel.getBoxModel1())
					&& boxes.contains(lineModel.getBoxModel2())) {
				lineModel.setSelected(true);
			}
		}
	}

	public void attach(AppModel anAppModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), anAppModel, this,
				(anAppModel == null) ? null : anAppModel.getDiagrams(),
				"diagrams", "appModel");
		command.execute();
	}

	public void detach(AppModel anAppModel) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), anAppModel, this, anAppModel.getDiagrams(),
				"diagrams", "appModel");
		command.execute();
	}

}
