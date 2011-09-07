package org.modelibra.modeler.app.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.modelibra.modeler.app.context.DiagramFrame;
import org.modelibra.modeler.app.context.DiagramToolBar;
import org.modelibra.modeler.app.context.LinePopupMenu;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.event.ModelEvent;
import org.modelibra.modeler.util.ImageHandling;

/**
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-02
 */
public class DiagramSection extends JPanel implements MouseListener, Observer {

	static final long serialVersionUID = 7168319479760000140L;

	private static final Color DARK_COLOR = Color.GRAY;

	private static final Color LIGHT_COLOR = Color.WHITE;

	public static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);

	public static final String DEFAULT_FONT_NAME = "Helvetica";

	public static final int DEFAULT_FONT_STYLE = Font.PLAIN;

	public static final int DEFAULT_FONT_STYLE_IN_BOLD = Font.BOLD;

	public static final int DEFAULT_FONT_SIZE = 12;

	public static final Font DEFAULT_FONT = new Font(DEFAULT_FONT_NAME,
			DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);

	public static final Font DEFAULT_FONT_IN_BOLD = new Font(DEFAULT_FONT_NAME,
			DEFAULT_FONT_STYLE_IN_BOLD, DEFAULT_FONT_SIZE);

	public static final Color SELECTION_COLOR = Color.BLACK;

	public static final Color LABEL_COLOR = Color.BLACK;

	public static final int LINE_SELECTION = 3; // width in pixels

	public static final int INCREMENT = 20; // size in pixels

	private static final BasicStroke SELECTION_STROKE = new BasicStroke(2);

	public static final float MINIMUM_ZOOM_FACTOR = 0.25f;

	public static final float MAXIMUM_ZOOM_FACTOR = 2f;

	private static final int X1 = 4; // title draw, item oid, item id,
	// abstraction sign, entry sign x start

	private static final int X2 = 26; // item name draw x start

	private static final int X3 = 10; // intersection sign draw x start from
	// right

	private static final int Y = 16; // text line y start

	private static float zoomFactor = 1f;

	private static float zoomStep = 0.25f;

	private BoxSection box1;

	private DiagramFrame diagramFrame;

	private DiagramModel diagramModel;

	public DiagramSection(DiagramFrame aFrame, DiagramModel aDiagramModel) {
		super();
		diagramFrame = aFrame;
		this.setModel(aDiagramModel);
		this.addMouseListener(this);
		this.setBackgroundColor(this, diagramModel.getColor());
		this.setLayout(null);
		this.drawElements();
	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	public DiagramModel getModel() {
		return diagramModel;
	}

	public void setModel(DiagramModel aDiagramModel) {
		diagramModel = aDiagramModel;
		if (diagramModel != null) {
			diagramModel.addObserver(this);
		}
	}

	public void setTitle(String aTitle) {
		diagramFrame.setTitle(aTitle);
	}

	private boolean isBlackAndWhite() {
		try {
			return this.getModel().getAppModel().isBlackAndWhite();
		} catch (Exception e) {

		}
		return false;
	}

	private void setBackgroundColor(JComponent component, Color color) {
		if (this.isBlackAndWhite()) {
			component.setBackground(LIGHT_COLOR);
		} else {
			component.setBackground(color);
		}
	}

	private void setLineColor(Graphics g, Color color) {
		if (this.isBlackAndWhite()) {
			g.setColor(DARK_COLOR);
		} else {
			g.setColor(color);
		}
	}

	private void setSelectedLineColor(Graphics g) {
		g.setColor(SELECTION_COLOR);
	}

	public static float getZoomFactor() {
		return zoomFactor;
	}

	public static void setZoomFactor(float newZoomFactor) {
		if (newZoomFactor < MINIMUM_ZOOM_FACTOR) {
			newZoomFactor = MINIMUM_ZOOM_FACTOR;
		}
		if (newZoomFactor > MAXIMUM_ZOOM_FACTOR) {
			newZoomFactor = MAXIMUM_ZOOM_FACTOR;
		}
		zoomFactor = newZoomFactor;
	}

	public static float getZoomStep() {
		return zoomStep;
	}

	public static void setZoomStep(float newZoomStep) {
		zoomStep = newZoomStep;
	}

	public void zoomIn(boolean zoomIn) {
		Component[] components = this.getComponents();
		BoxSection boxView;
		float oldZf = getZoomFactor();
		if (zoomIn) {
			setZoomFactor(getZoomFactor() + getZoomStep());
		} else {
			setZoomFactor(getZoomFactor() - getZoomStep());
		}
		float newZf = getZoomFactor();
		Manager.getSingleton().startTransaction("zoom"); // Transaction
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				boxView.zoom(oldZf, newZf);
			}
		}
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	private void addNewBoxView(BoxModel aBoxModel) {
		BoxSection newBox = new BoxSection(this, aBoxModel);
		newBox.setLocation(aBoxModel.getLocation());
		newBox.setTitle(aBoxModel.getName());
		newBox.setEntry(aBoxModel);
		newBox.setIntersection(aBoxModel);
		newBox.setAbstraction(aBoxModel);
		newBox.setSelected(aBoxModel.isSelected()); // when off box selection
		// not on
		newBox.addMouseListener(this);
		this.add(newBox);
		this.validate();
		this.repaint();
	}

	public void selectLinesBetweenSelectedBoxes() {
		// manager.startTransaction("select lines between selected boxes"); //
		// Transaction
		Component[] components = this.getComponents();
		BoxSection boxView;
		Collection<BoxModel> boxes = new ArrayList<BoxModel>();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					boxes.add(boxView.getModel());
				}
			}
		}
		diagramModel.selectLinesBetweenBoxes(boxes);
		// manager.commit(); // Transaction ------------------------------
	}

	public void selectLinesForSelectedBoxes() {
		// manager.startTransaction("select lines for selected boxes"); //
		// Transaction
		Component[] components = this.getComponents();
		BoxSection boxView;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					boxView.getModel().selectLines();
				}
			}
		}
		// manager.commit(); // Transaction ------------------------------
	}

	public BoxSection getFirstSelectedBox() {
		Component[] components = this.getComponents();
		BoxSection boxView;
		// Collection boxes = new ArrayList();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					return boxView;
				}
			}
		}
		return null;
	}

	public void selectAll() {
		// manager.startTransaction("select all "); // Transaction
		this.selectAllBoxes();
		this.selectAllLines();
		this.repaint();
		// manager.commit(); // Transaction ------------------------------
	}

	private void selectAllBoxes() {
		diagramModel.selectAllBoxes();
	}

	private void selectAllLines() {
		diagramModel.selectAllLines();
	}

	public void deselectAll() {
		// manager.startTransaction("deselect all "); // Transaction
		this.deselectAllBoxes();
		this.deselectAllLines();
		this.repaint();
		// manager.commit(); // Transaction ------------------------------
	}

	private void deselectAllBoxes() {
		diagramModel.deselectAllBoxes();
	}

	private void deselectAllLines() {
		diagramModel.deselectAllLines();
	}

	public void deleteSelection() {
		Manager.getSingleton().startTransaction("delete selection"); // Transaction
		this.deleteSelectedBoxes();
		this.deleteSelectedLines();
		this.repaint();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	private void deleteSelectedBoxes() {
		diagramModel.removeSelectedBoxes();
	}

	private void deleteSelectedLines() {
		diagramModel.removeSelectedLines();
	}

	public void colorSelection() {
		Manager.getSingleton().startTransaction("color selection"); // Transaction
		Color newColor = JColorChooser.showDialog(this, Para.getPara().getText(
				"colorSelection"), BoxModel.DEFAULT_COLOR);
		this.colorSelectedBoxes(newColor);
		this.colorSelectedLines(newColor);
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	public void colorSelectedBoxes(Color aColor) {
		if (aColor != null) {
			diagramModel.colorSelectedBoxes(aColor);
		}
	}

	public void colorSelectedLines(Color aColor) {
		if (aColor != null) {
			diagramModel.colorSelectedLines(aColor);
		}
	}

	public void erase() {
		diagramModel.deleteObserver(this);
		this.deinstallLineObservers();
		this.eraseBoxes();
	}

	public void eraseBoxes() {
		Component[] components = this.getComponents();
		BoxSection boxView;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				boxView.erase();
			}
		}
		this.repaint();
	}

	public void drawElements() {
		this.drawBoxes();
		this.installLineObservers();
	}

	public void drawBoxes() {
		BoxModel boxModel;
		for (Iterator x = diagramModel.getBoxes().iterator(); x.hasNext();) {
			boxModel = (BoxModel) x.next();
			this.addNewBoxView(boxModel);
		}
		this.repaint();
	}

	public void installLineObservers() {
		LineModel lineModel;
		for (Iterator x = diagramModel.getLines().iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.addObserver(this);
		}
	}

	public void deinstallLineObservers() {
		LineModel lineModel;
		for (Iterator x = diagramModel.getLines().iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			lineModel.deleteObserver(this);
		}
	}

	public void changeSelectedBoxesSize(String anOp, String aDim) {
		Manager.getSingleton().startTransaction("change selected boxes size"); // Transaction
		Component[] components = this.getComponents();
		BoxSection boxView;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					if (anOp.equals("increase")) {
						if (aDim.equals("width")) {
							boxView.getModel().changeSize(INCREMENT, 0);
						} else if (aDim.equals("height")) {
							boxView.getModel().changeSize(0, INCREMENT);
						} else {
							boxView.getModel().changeSize(INCREMENT, INCREMENT);
						}
					} else if (anOp.equals("decrease")) {
						if (aDim.equals("width")) {
							boxView.getModel().changeSize(-INCREMENT, 0);
						} else if (aDim.equals("height")) {
							boxView.getModel().changeSize(0, -INCREMENT);
						} else {
							boxView.getModel().changeSize(-INCREMENT,
									-INCREMENT);
						}
					}
				}
			}
		}
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	public void hideSelection() {
		Manager.getSingleton().startTransaction("hide selection"); // Transaction
		this.hideSelectedBoxes();
		this.hideSelectedLines();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	private void hideSelectedBoxes() {
		Component[] components = this.getComponents();
		BoxSection boxView;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					boxView.getModel().setVisible(false);
				}
			}
		}
	}

	private void hideSelectedLines() {
		diagramModel.hideSelectedLines();
	}

	public void showSelection() {
		Manager.getSingleton().startTransaction("show selection"); // Transaction
		this.showSelectedBoxes();
		this.showSelectedLines();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	private void showSelectedBoxes() {
		Component[] components = this.getComponents();
		BoxSection boxView;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BoxSection) {
				boxView = (BoxSection) components[i];
				if (boxView.isSelected()) {
					boxView.getModel().setVisible(true);
				}
			}
		}
	}

	private void showSelectedLines() {
		diagramModel.showSelectedLines();
	}

	public void tileBoxes() {
		Manager.getSingleton().startTransaction("tile boxes"); // Transaction
		diagramModel.tileBoxes();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	public void copyItemsFromParentToChild() {
		Manager.getSingleton().startTransaction(
				"copy items from parent to child"); // Transaction
		diagramModel.copyItemsFromParentToChild();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	public void createBoxes() {
		this.setCursor(WAIT_CURSOR);
		String answer = JOptionPane.showInputDialog(null, // context frame
				Para.getPara().getText("enterNumber"), // message
				Para.getPara().getText("genBoxes"), // title
				JOptionPane.QUESTION_MESSAGE); // message type
		try {
			Integer i = new Integer(answer);
			int no = i.intValue();
			Manager.getSingleton().startTransaction("create boxes"); // Transaction
			diagramModel.createBoxes(no);
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} finally {
			this.setCursor(BoxSection.DEFAULT_CURSOR);
		}
	}

	public void createSequence() {
		Manager.getSingleton().startTransaction("create Sequence"); // Transaction
		diagramModel.createSequence();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
	}

	public void createRules() {
		int noOfGenBoxes = 0;
		Manager.getSingleton().startTransaction("create rules"); // Transaction
		noOfGenBoxes = diagramModel.createRules();
		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
		JOptionPane.showMessageDialog(null, // context frame
				noOfGenBoxes + " " + Para.getPara().getText("rulesGenerated"), // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

	// overriden from JComponent
	public Dimension getPreferredSize() {
		return diagramModel.getSize();
	}

	private LineModel clickedOnLine(MouseEvent e) {
		Point clickedPoint = e.getPoint();
		Point p1, p2;
		Point delta = new Point(8, 8);
		LineModel lineModel;
		for (Iterator x = diagramModel.getLines().iterator(); x.hasNext();) {
			lineModel = (LineModel) x.next();
			p1 = lineModel.getPoint1();
			p2 = lineModel.getPoint2();
			if (lineModel.clicked(p1, p2, clickedPoint, delta)) {

				return lineModel;
			}
		}
		return null;
	}

	// implemented from MouseListener
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this) {
			if (diagramFrame.getDiagramToolBar().getSelectedTool() == DiagramToolBar.BOX) {
				Manager.getSingleton().startTransaction("create box"); // Transaction
				if (diagramModel.isOidCreated()) {
					new BoxModel(diagramModel, e.getPoint(), true);
				} else {
					new BoxModel(diagramModel, e.getPoint(), false);
				}
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
				if (diagramFrame.getDiagramToolBar().isSelectDefault()) {
					diagramFrame.getDiagramToolBar().setSelectedTool(
							DiagramToolBar.SELECT);
				}
			} else if (diagramFrame.getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
				LineModel clickedLine = this.clickedOnLine(e);
				if (clickedLine != null) {
					clickedLine.setSelected(!clickedLine.isSelected()); // is
					// not a
					// command
					if ((e.getClickCount() == 2)) {
						// LineForm dirsForm = new LineForm(clickedLine);
						// dirsForm.setLocation(e.getPoint());
						// dirsForm.setVisible(true);
						Manager.getSingleton().startTransaction("set line id"); // Transaction
						if (clickedLine.isDir12Child()) {
							clickedLine.setDir12Id(!clickedLine.isDir12Id());
						} else if (clickedLine.isDir21Child()) {
							clickedLine.setDir21Id(!clickedLine.isDir21Id());
						}
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------

						/*
						 * Manager.getSingleton().startTransaction("select
						 * line"); // Transaction if
						 * (clickedLine.isDir12Child()) {
						 * clickedLine.setDir12Id(!clickedLine.isDir12Id()); }
						 * else if (clickedLine.isDir21Child()) {
						 * clickedLine.setDir21Id(!clickedLine.isDir21Id()); }
						 * Manager.getSingleton().commit(); // Transaction
						 * ------------------------------
						 */
					}

					this.repaint(); // paint selected line
				} else {
					this.deselectAll();
				}
			} else { // ToolBar.LINE
				box1 = null;
				if (diagramFrame.getDiagramToolBar().isSelectDefault()) {
					diagramFrame.getDiagramToolBar().setSelectedTool(
							DiagramToolBar.SELECT);
				}
			}
		} else if (e.getSource() instanceof BoxSection) {
			if (diagramFrame.getDiagramToolBar().getSelectedTool() == DiagramToolBar.LINE) {
				BoxSection boxView = (BoxSection) (e.getSource());
				if (box1 == null) {
					box1 = boxView;
				} else {
					Manager.getSingleton().startTransaction("create line"); // Transaction
					LineModel lineModel = new LineModel(diagramModel, box1
							.getModel(), boxView.getModel());
					lineModel.addObserver(this);
					if ((box1.getModel().isAbstractDef())
							|| (boxView.getModel().isAbstractDef())) {
						lineModel.setInheritance(true);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
					box1 = null;
					if (diagramFrame.getDiagramToolBar().isSelectDefault()) {
						diagramFrame.getDiagramToolBar().setSelectedTool(
								DiagramToolBar.SELECT);
					}
				}
			}
		}
	}

	// implemented from MouseListener
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (diagramFrame.getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
			LineModel clickedLine = this.clickedOnLine(e);
			if (clickedLine != null) {
				//if (e.isPopupTrigger()) { // bug na Ubuntu: http://forums.java.net/jive/thread.jspa?threadID=35178
				if ((e.getButton() == MouseEvent.BUTTON3) && (e.getID() == MouseEvent.MOUSE_RELEASED) ) {
					LinePopupMenu.getSingleton(clickedLine).show(this,
							clickedLine, e.getX(), e.getY());
				}
				this.repaint(); // paint selected line
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		if (o instanceof DiagramModel) {
			DiagramModel diagramModel = (DiagramModel) o;
			ModelEvent modelEvent = (ModelEvent) arg;
			String opType = modelEvent.getOpType();
			String propertyName = modelEvent.getPropertyName();
			// System.out.println("update diagram view: " +
			// diagramModel.getName() + ", " +
			// opType + " " + propertyName);
			if (opType.equals("add")) {
				if (modelEvent.getOpArgument() instanceof BoxModel) {
					BoxModel boxModel = (BoxModel) modelEvent.getOpArgument();
					this.addNewBoxView(boxModel);
				} else if (modelEvent.getOpArgument() instanceof LineModel) {
					this.repaint();
				}
			} else if (opType.equals("set")) {
				if (propertyName.equals("name")) {
					this.setTitle(diagramModel.getName());
				} else if (propertyName.equals("color")) {
					this.setBackgroundColor(this, diagramModel.getColor());
				} else if (propertyName.equals("size")) {
					this.setPreferredSize(diagramModel.getSize());
				}
			} else if (opType.equals("detach")) { // from App
				this.erase();
			} else {
				this.repaint();
			}
		} else if (o instanceof LineModel) {
			this.repaint();
		} else if (o instanceof BoxModel) {
			this.repaint();
		}
	}

	private void paintLine(LineModel lm, Graphics g) {
		this.setLineColor(g, lm.getColor());
		g.drawLine(lm.getPoint1().x, lm.getPoint1().y, lm.getPoint2().x, lm
				.getPoint2().y);
	}

	private void paintSelectedLine(LineModel lm, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(SELECTION_STROKE);
		this.setSelectedLineColor(g2);
		g2.drawLine(lm.getPoint1().x, lm.getPoint1().y, lm.getPoint2().x, lm
				.getPoint2().y);
		g2.setStroke(oldStroke);
	}

	private void paintLineName(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String lineName = lm.getName();
		if (!lineName.equals(EntityModel.DEFAULT_NAME)) {
			g
					.drawString(lineName, lm.getCenterPoint().x, lm
							.getCenterPoint().y);
		}
	}

	private void paintSelectedLineName(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String lineName = lm.getName();
		if (!lineName.equals(EntityModel.DEFAULT_NAME)) {
			AttributedString as = new AttributedString(lineName);
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), lm.getCenterPoint().x, lm
					.getCenterPoint().y);
		}
	}

	private void paintDir12MinMax(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String mm = lm.getDir12Min() + ".." + lm.getDir12Max();
		g
				.drawString(mm, lm.getDir12PointMinMax().x, lm
						.getDir12PointMinMax().y);
	}

	private void paintSelectedDir12MinMax(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String mm = lm.getDir12Min() + ".." + lm.getDir12Max();
		AttributedString as = new AttributedString(mm);
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), lm.getDir12PointMinMax().x, lm
				.getDir12PointMinMax().y);
	}

	private void paintDir21MinMax(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String mm = lm.getDir21Min() + ".." + lm.getDir21Max();
		g
				.drawString(mm, lm.getDir21PointMinMax().x, lm
						.getDir21PointMinMax().y);
	}

	private void paintSelectedDir21MinMax(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String mm = lm.getDir21Min() + ".." + lm.getDir21Max();
		AttributedString as = new AttributedString(mm);
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), lm.getDir21PointMinMax().x, lm
				.getDir21PointMinMax().y);
	}

	private void paintDir12Name(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String name = lm.getDir12Name();
		String id = "";
		if (lm.isDir12Id()) {
			id = "id  ";
		}
		if (lm.isTwin()) { // && lm.isDir12Child()
			g.drawString(id + name, lm.getDir12PointName().x, lm
					.getDir12PointName().y);
		} else if (!name.equals(LineModel.DEFAULT_DIRECTION_NAME)) {
			if (lm.isDir12NameVisible()) {
				if (diagramModel.isDirNameDisplayed()) {
					g.drawString(id + name, lm.getDir12PointName().x, lm
							.getDir12PointName().y);
				}
			} else if (lm.isDir12Id()) {
				g.drawString(id, lm.getDir12PointName().x, lm
						.getDir12PointName().y);
			}
		} else if (lm.isDir12Id()) {
			g
					.drawString(id, lm.getDir12PointName().x, lm
							.getDir12PointName().y);
		}
	}

	private void paintSelectedDir12Name(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String name = lm.getDir12Name();
		String id = "";
		if (lm.isDir12Id()) {
			id = "id  ";
		}
		if (lm.isTwin()) { // && lm.isDir12Child()
			AttributedString as = new AttributedString(id + name);
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), lm.getDir12PointName().x, lm
					.getDir12PointName().y);
		} else if (!name.equals(LineModel.DEFAULT_DIRECTION_NAME)) {
			if (lm.isDir12NameVisible()) {
				if (diagramModel.isDirNameDisplayed()) {
					AttributedString as = new AttributedString(id + name);
					as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
					g.drawString(as.getIterator(), lm.getDir12PointName().x, lm
							.getDir12PointName().y);
				}
			} else if (lm.isDir12Id()) {
				AttributedString as = new AttributedString(id);
				as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
				g.drawString(as.getIterator(), lm.getDir12PointName().x, lm
						.getDir12PointName().y);
			}
		} else if (lm.isDir12Id()) {
			AttributedString as = new AttributedString(id);
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), lm.getDir12PointName().x, lm
					.getDir12PointName().y);
		}
	}

	private void paintDir21Name(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String name = lm.getDir21Name();
		String id = "";
		if (lm.isDir21Id()) {
			id = "id  ";
		}
		if (lm.isTwin()) { // && lm.isDir21Child()
			g.drawString(id + name, lm.getDir21PointName().x, lm
					.getDir21PointName().y);
		} else if (!name.equals(LineModel.DEFAULT_DIRECTION_NAME)) {
			if (lm.isDir21NameVisible()) {
				if (diagramModel.isDirNameDisplayed()) {
					g.drawString(id + name, lm.getDir21PointName().x, lm
							.getDir21PointName().y);
				}
			} else if (lm.isDir21Id()) {
				g.drawString(id, lm.getDir21PointName().x, lm
						.getDir21PointName().y);
			}
		} else if (lm.isDir21Id()) {
			g
					.drawString(id, lm.getDir21PointName().x, lm
							.getDir21PointName().y);
		}
	}

	private void paintSelectedDir21Name(LineModel lm, Graphics g) {
		this.setLineColor(g, LABEL_COLOR);
		String name = lm.getDir21Name();
		String id = "";
		if (lm.isDir21Id()) {
			id = "id  ";
		}
		if (lm.isTwin()) { // && lm.isDir21Child()
			AttributedString as = new AttributedString(id + name);
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), lm.getDir21PointName().x, lm
					.getDir21PointName().y);
		} else if (!name.equals(LineModel.DEFAULT_DIRECTION_NAME)) {
			if (lm.isDir21NameVisible()) {
				if (diagramModel.isDirNameDisplayed()) {
					AttributedString as = new AttributedString(id + name);
					as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
					g.drawString(as.getIterator(), lm.getDir21PointName().x, lm
							.getDir21PointName().y);
				}
			} else if (lm.isDir21Id()) {
				AttributedString as = new AttributedString(id);
				as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
				g.drawString(as.getIterator(), lm.getDir21PointName().x, lm
						.getDir21PointName().y);
			}
		} else if (lm.isDir21Id()) {
			AttributedString as = new AttributedString(id);
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), lm.getDir21PointName().x, lm
					.getDir21PointName().y);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLines(g);
	}

	private void paintLines(Graphics g) {
		Collection c = diagramModel.getLines();
		for (Iterator x = c.iterator(); x.hasNext();) {
			LineModel lineModel = (LineModel) x.next();
			if (lineModel.isVisible()) {
				g.setFont(DEFAULT_FONT);
				if (lineModel.isSelected()) {
					this.paintSelectedLine(lineModel, g);
					this.paintSelectedDir12MinMax(lineModel, g);
					this.paintSelectedDir12Name(lineModel, g);
					this.paintSelectedLineName(lineModel, g);
					this.paintSelectedDir21Name(lineModel, g);
					this.paintSelectedDir21MinMax(lineModel, g);
				} else {
					this.paintLine(lineModel, g);
					this.paintDir12MinMax(lineModel, g);
					this.paintDir12Name(lineModel, g);
					this.paintLineName(lineModel, g);
					this.paintDir21Name(lineModel, g);
					this.paintDir21MinMax(lineModel, g);
				}
			}
		}
	}

	private void paintBox(BoxModel bm, Graphics g) {
		paintBoxFrame(bm, g);
		paintBoxLeftCategory(bm, g);
		paintBoxTitle(bm, g);
		paintBoxRightCategory(bm, g);
		paintBoxItems(bm, g);
	}

	private void paintSelectedBox(BoxModel bm, Graphics g) {
		paintSelectedBoxFrame(bm, g);
		paintBoxLeftCategory(bm, g);
		paintBoxTitle(bm, g);
		paintBoxRightCategory(bm, g);
		paintSelectedBoxItems(bm, g);
	}

	private void paintBoxFrame(BoxModel bm, Graphics g) {
		int lineY = Y + 4;
		this.setLineColor(g, bm.getColor());
		g.drawRect(bm.getX(), bm.getY(), bm.getWidth(), bm.getHeight());
		g.drawLine(bm.getX(), bm.getY() + lineY, bm.getX() + bm.getWidth(), bm
				.getY()
				+ lineY);
	}

	private void paintSelectedBoxFrame(BoxModel bm, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(SELECTION_STROKE);
		this.setSelectedLineColor(g2);
		g2.drawRect(bm.getX(), bm.getY(), bm.getWidth(), bm.getHeight());
		int lineY = Y + 4;
		g2.drawLine(bm.getX(), bm.getY() + lineY, bm.getX() + bm.getWidth(), bm
				.getY()
				+ lineY);
		g2.setStroke(oldStroke);
	}

	private void paintBoxLeftCategory(BoxModel bm, Graphics g) {
		String category = " ";
		if (bm.isAbstractDef()) {
			category = BoxSection.ABSTRACT_BOX;
		} else if (bm.isEntry()) {
			category = BoxSection.ENTRY_BOX;
		}
		this.setLineColor(g, Color.BLACK);
		AttributedString as = new AttributedString(category);
		// Cannot add an attribute to an empty string.
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), bm.getX() + X1, bm.getY() + Y);
	}

	private void paintBoxRightCategory(BoxModel bm, Graphics g) {
		String category = " ";
		if (bm.isIntersection()) {
			if (!bm.isValidIntersection()) {
				category = BoxSection.NOT_VALID_INTERSECTION_BOX;
			} else if (bm.isManyToMany()) {
				category = BoxSection.MANY_TO_MANY_BOX;
			} else {
				category = BoxSection.VALID_INTERSECTION_BOX;
			}
		}
		this.setLineColor(g, Color.BLACK);
		AttributedString as = new AttributedString(category);
		// Cannot add an attribute to an empty string.
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), bm.getX() + bm.getWidth() - X3, bm
				.getY()
				+ Y);
	}

	private void paintBoxTitle(BoxModel bm, Graphics g) {
		this.setLineColor(g, Color.BLACK);
		AttributedString as = new AttributedString(bm.getName());
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), bm.getX() + X2, bm.getY() + Y);
	}

	private void paintBoxItems(BoxModel bm, Graphics g) {
		this.setLineColor(g, Color.BLACK);
		Collection c = bm.getItems();
		int order = 1;
		for (Iterator x = c.iterator(); x.hasNext();) {
			ItemModel itemModel = (ItemModel) x.next();
			paintBoxItem(bm, itemModel, g, order++);
		}
	}

	private void paintSelectedBoxItems(BoxModel bm, Graphics g) {
		this.setLineColor(g, Color.BLACK);
		Collection c = bm.getItems();
		int order = 1;
		for (Iterator x = c.iterator(); x.hasNext();) {
			ItemModel itemModel = (ItemModel) x.next();
			paintSelectedBoxItem(bm, itemModel, g, order++);
		}
	}

	private void paintBoxItem(BoxModel bm, ItemModel im, Graphics g, int order) {
		if (im.isOid()) {
			g.drawString(BoxSection.OID, bm.getX() + X1, bm.getY() + Y + order
					* Y);
		} else if (im.isId()) {
			g.drawString(BoxSection.ID, bm.getX() + X1, bm.getY() + Y + order
					* Y);
		} else if (im.getMin() > 0) {
			g.drawString(BoxSection.REQUIRED, bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		} else if (im.getMin() == 0) {
			g.drawString(BoxSection.OPTIONAL, bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		}
		g.drawString(im.getName(), bm.getX() + X2, bm.getY() + Y + order * Y);
	}

	private void paintSelectedBoxItem(BoxModel bm, ItemModel im, Graphics g,
			int order) {
		if (im.isOid()) {
			AttributedString as = new AttributedString("oid");
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		} else if (im.isId()) {
			AttributedString as = new AttributedString("id");
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		} else if (im.getMin() > 0) {
			AttributedString as = new AttributedString("\u03D5");
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		} else if (im.getMin() == 0) {
			AttributedString as = new AttributedString("o");
			as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
			g.drawString(as.getIterator(), bm.getX() + X1, bm.getY() + Y
					+ order * Y);
		}
		AttributedString as = new AttributedString(im.getName());
		as.addAttribute(TextAttribute.FONT, DEFAULT_FONT_IN_BOLD);
		g.drawString(as.getIterator(), bm.getX() + X2, bm.getY() + Y + order
				* Y);
	}

	private void paintBoxes(Graphics g) {
		Collection c = diagramModel.getBoxes();
		for (Iterator x = c.iterator(); x.hasNext();) {
			BoxModel boxModel = (BoxModel) x.next();
			if (boxModel.isVisible()) {
				if (boxModel.isSelected()) {
					this.paintSelectedBox(boxModel, g);
				} else {
					this.paintBox(boxModel, g);
				}
			}
		}
	}

	public BufferedImage getBufferedImage() {
		Image image = createImage();
		return ImageHandling.toBufferedImage(image);
	}

	private Image createImage() {
		Image image = createImage(getWidth(), getHeight());
		Graphics g = image.getGraphics();
		paintLines(g);
		paintBoxes(g);
		return image;
	}

}