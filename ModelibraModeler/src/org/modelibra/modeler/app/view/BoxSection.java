package org.modelibra.modeler.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.modelibra.modeler.app.context.BoxPopupMenu;
import org.modelibra.modeler.app.context.DiagramToolBar;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-05-24
 */
public class BoxSection extends JPanel implements ActionListener, Observer {

	static final long serialVersionUID = 7168319479760000060L;

	private static final Color DARK_COLOR = Color.GRAY;

	private static final Color LIGHT_COLOR = Color.WHITE;

	public static final Color TITLE_BACKGROUND_COLOR = new Color(255, 255, 153);

	public static final Color CATEGORY_BACKGROUND_COLOR = new Color(255, 204,
			102);

	public static final int BORDER = 3; // width in pixels

	public static final int COLUMN1 = 24; // width in pixels

	public static final int COLUMN2 = 270; // width in pixels

	public static final Cursor DEFAULT_CURSOR = new Cursor(
			Cursor.DEFAULT_CURSOR);

	public static final Cursor NW_RESIZE_CURSOR = new Cursor(
			Cursor.NW_RESIZE_CURSOR);

	public static final Cursor N_RESIZE_CURSOR = new Cursor(
			Cursor.N_RESIZE_CURSOR);

	public static final Cursor NE_RESIZE_CURSOR = new Cursor(
			Cursor.NE_RESIZE_CURSOR);

	public static final Cursor E_RESIZE_CURSOR = new Cursor(
			Cursor.E_RESIZE_CURSOR);

	public static final Cursor SE_RESIZE_CURSOR = new Cursor(
			Cursor.SE_RESIZE_CURSOR);

	public static final Cursor S_RESIZE_CURSOR = new Cursor(
			Cursor.S_RESIZE_CURSOR);

	public static final Cursor SW_RESIZE_CURSOR = new Cursor(
			Cursor.SW_RESIZE_CURSOR);

	public static final Cursor W_RESIZE_CURSOR = new Cursor(
			Cursor.W_RESIZE_CURSOR);

	public static final String OID = "oid";

	public static final String ID = "id";

	public static final String REQUIRED = "\u03D5";

	public static final String OPTIONAL = "o";

	public static final String REGULAR_BOX = "";

	public static final String ABSTRACT_BOX = "@";

	public static final String ENTRY_BOX = "||";

	public static final String VALID_INTERSECTION_BOX = "V";

	public static final String NOT_VALID_INTERSECTION_BOX = "?";

	public static final String MANY_TO_MANY_BOX = "X";

	private Point mousePressed = new Point(0, 0);

	private Dimension sizeBeforeDrag = new Dimension(0, 0);

	private Point locationBeforeDrag = new Point(0, 0);

	private JPanel titleSection = new JPanel();

	private JLabel entry = new JLabel();

	private JLabel intersection = new JLabel();

	private JLabel abstraction = new JLabel();

	private JTextField title = new JTextField();

	private JTable jTable;

	private ItemsTableModel tableModel;

	private JTextField itemText = new JTextField("");

	private DiagramSection diagramView;

	private BoxModel boxModel;

	public BoxSection(DiagramSection aDiagramView, BoxModel aBoxModel) {
		super();
		diagramView = aDiagramView;
		this.setModel(aBoxModel);
		this.setBounds(boxModel.getRectangle());
		this.setForegroundColor(this, boxModel.getColor());
		this.setSelected(boxModel.isSelected());
		this.setVisible(boxModel.isVisible());
		this.addMouseListener(new BoxMouseAdapter(this));
		this.addMouseMotionListener(new BoxMouseMotionAdapter(this));

		this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,
				BORDER));
		this.setLayout(new BorderLayout());
		this.add(titleSection, BorderLayout.NORTH);
		titleSection
				.setLayout(new BoxLayout(titleSection, BoxLayout.LINE_AXIS));
		this.setBackgroundColor(titleSection, CATEGORY_BACKGROUND_COLOR);
		entry.setText("");
		titleSection.add(entry);
		abstraction.setText("");
		titleSection.add(abstraction);
		titleSection.add(title);
		intersection.setText("");
		titleSection.add(intersection);

		this.add(itemText, BorderLayout.SOUTH);

		this.setLocationBeforeDrag(this.getLocation().x, this.getLocation().y);
		this.setSizeBeforeDrag(this.getSize().width, this.getSize().height);

		title.setFont(DiagramSection.DEFAULT_FONT);
		this.setBackgroundColor(title, TITLE_BACKGROUND_COLOR);
		title.addActionListener(this);
		title.addMouseListener(new TitleMouseAdapter(this));
		title.addMouseMotionListener(new TitleMouseMotionAdapter(this));

		itemText.addActionListener(this);

		tableModel = new ItemsTableModel(this.getModel());
		jTable = new JTable(tableModel);
		jTable.getColumnModel().getColumn(0).setMinWidth(COLUMN1);
		jTable.getColumnModel().getColumn(0).setMaxWidth(COLUMN1);
		jTable.getColumnModel().getColumn(1).setMinWidth(COLUMN2);
		jTable.getColumnModel().getColumn(1).setMaxWidth(COLUMN2);
		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if ((e.getClickCount() == 2) && (e.getX() < COLUMN1)) {
					int r = jTable.getSelectionModel().getMaxSelectionIndex();
					ArrayList list = (ArrayList) boxModel.getItems();
					ItemModel itemModel = (ItemModel) list.get(r);
					Manager.getSingleton().startTransaction("update id"); // Transaction
					itemModel.setId(!itemModel.isId());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		JScrollPane jScrollPane = new JScrollPane(jTable);
		this.add(jScrollPane, BorderLayout.CENTER);

		this.doLayout(); // no title shown without doLayout
		// this.validate(); // does not work here!
		// this.invalidate(); // does not work here!
	}

	public DiagramSection getDiagramView() {
		return diagramView;
	}

	public BoxModel getModel() {
		return boxModel;
	}

	public void setModel(BoxModel aBoxModel) {
		boxModel = aBoxModel;
		if (boxModel != null) {
			boxModel.addObserver(this);
		}
	}

	public boolean isSelected() {
		return boxModel.isSelected();
	}

	public void setSelected(boolean selected) {
		if (selected) {
			this.setBorder(BorderFactory.createLineBorder(
					DiagramSection.SELECTION_COLOR, BoxSection.BORDER));
		} else {
			this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER,
					BORDER, BORDER));
		}
	}

	public String getEntry() {
		return entry.getText();
	}

	public void setEntry(String anEntry) {
		entry.setText(anEntry);
	}

	public String getIntersection() {
		return intersection.getText();
	}

	public void setIntersection(String anIntersection) {
		intersection.setText(anIntersection);
	}

	public String getAbstraction() {
		return abstraction.getText();
	}

	public void setAbstraction(String anAbstraction) {
		abstraction.setText(anAbstraction);
	}

	public String getTitle() {
		return title.getText();
	}

	public void setTitle(String aTitle) {
		title.setText(aTitle);
	}

	private boolean isBlackAndWhite() {
		return this.getModel().getDiagramModel().getAppModel()
				.isBlackAndWhite();
	}

	private void setBackgroundColor(JComponent component, Color color) {
		if (this.isBlackAndWhite()) {
			component.setBackground(LIGHT_COLOR);
		} else {
			component.setBackground(color);
		}
	}

	private void setForegroundColor(JComponent component, Color color) {
		if (this.isBlackAndWhite()) {
			component.setBackground(DARK_COLOR);
		} else {
			component.setBackground(color);
		}
	}

	public void erase() {
		if (diagramView != null) {
			boxModel.deleteObserver(this);
			tableModel.deinstallObservers();
			diagramView.remove(this);
			diagramView = null;

		}
	}

	private void unzoom(float oldZoomFactor) {
		Rectangle r = boxModel.getRectangle();
		boxModel.setRectangle((int) (r.x / oldZoomFactor),
				(int) (r.y / oldZoomFactor), (int) (r.width / oldZoomFactor),
				(int) (r.height / oldZoomFactor));
	}

	public void zoom(float oldZoomFactor, float newZoomFactor) {
		this.unzoom(oldZoomFactor);
		Rectangle r = boxModel.getRectangle();
		boxModel.setRectangle((int) (r.x * newZoomFactor),
				(int) (r.y * newZoomFactor), (int) (r.width * newZoomFactor),
				(int) (r.height * newZoomFactor));
	}

	// implemented from ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == title) {
			if (!title.getText().equals("")) {
				Manager.getSingleton().startTransaction("set box name"); // Transaction
				boxModel.setName(title.getText());
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
				title.selectAll();
			} else {
				title.setText("?");
			}
		} else if (e.getSource() == itemText) {
			if (!itemText.getText().equals("")) {
				Manager.getSingleton().startTransaction("create item"); // Transaction
				ItemModel itemModel = new ItemModel(this.getModel());
				itemModel.setName(itemText.getText());
				TypeModel stringTypeModel = AppModel.getSingleton()
						.getStringType();
				if (stringTypeModel != null) {
					itemModel.attach(stringTypeModel);
				}
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
				itemText.setText("");
			}
		}
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		if (o instanceof BoxModel) {
			BoxModel boxModel = (BoxModel) o;
			ModelEvent modelEvent = (ModelEvent) arg;
			String opType = modelEvent.getOpType();
			String propertyName = modelEvent.getPropertyName();
			// System.out.println("update box view: " + boxModel.getName() + ",
			// " +
			// opType + " " + propertyName);
			if (opType.equals("set")) {
				if (propertyName.equals("name")) {
					this.setTitle(boxModel.getName());
				} else if (propertyName.equals("color")) {
					this.setForegroundColor(this, boxModel.getColor());
				} else if (propertyName.equals("selected")) {
					this.setSelected(boxModel.isSelected());
				} else if (propertyName.equals("visible")) {
					this.setVisible(boxModel.isVisible());
					diagramView.repaint();
				} else if (propertyName.equals("rectangle")) {
					this.setBounds(boxModel.getRectangle());
					this.validate();
					diagramView.repaint();
				} else if (propertyName.equals("abstractDef")) {
					this.setAbstraction(boxModel);
					this.repaint();
					diagramView.repaint();
				} else if (propertyName.equals("entry")) {
					this.setEntry(boxModel);
					this.repaint();
					diagramView.repaint();
				} else if (propertyName.equals("intersection")) {
					this.setIntersection(boxModel);
					this.repaint();
					diagramView.repaint();
				} else if (propertyName.equals("validIntersection")) {
					this.setIntersection(boxModel);
					this.repaint();
					diagramView.repaint();
				} else if (propertyName.equals("manyToMany")) {
					this.setIntersection(boxModel);
					this.repaint();
					diagramView.repaint();
				}
			} else if (opType.equals("detach")) {
				this.erase();
			}
		}
	}

	public void setEntry(BoxModel aBoxModel) {
		if (aBoxModel.isEntry()) {
			setEntry(ENTRY_BOX);
		} else {
			setEntry(REGULAR_BOX);
		}
	}

	public void setAbstraction(BoxModel aBoxModel) {
		if (aBoxModel.isAbstractDef()) {
			setAbstraction(ABSTRACT_BOX);
		} else {
			setAbstraction(REGULAR_BOX);
		}
	}

	public void setIntersection(BoxModel aBoxModel) {
		if (aBoxModel.isIntersection()) {
			if (!aBoxModel.isValidIntersection()) {
				setIntersection(NOT_VALID_INTERSECTION_BOX);
			} else if (aBoxModel.isManyToMany()) {
				setIntersection(MANY_TO_MANY_BOX);
			} else {
				setIntersection(VALID_INTERSECTION_BOX);
			}
		} else {
			setIntersection(REGULAR_BOX);
		}
	}

	public void setSizeBeforeDrag(int w, int h) {
		sizeBeforeDrag.width = w;
		sizeBeforeDrag.height = h;
	}

	public void setLocationBeforeDrag(int x, int y) {
		locationBeforeDrag.x = x;
		locationBeforeDrag.y = y;
	}

	void boxMousePressed(MouseEvent e, boolean fromBoxTitle) {
		if (diagramView.getDiagramFrame().getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
			boxModel.setSelected(!(boxModel.isSelected()));
			mousePressed = e.getPoint();
			if (!boxModel.getSize().equals(sizeBeforeDrag)) {
				this.setSizeBeforeDrag(boxModel.getSize().width, boxModel
						.getSize().height);
			}
			if (!boxModel.getLocation().equals(locationBeforeDrag)) {
				this.setLocationBeforeDrag(boxModel.getLocation().x, boxModel
						.getLocation().y);
			}
		} else if (diagramView.getDiagramFrame().getDiagramToolBar()
				.getSelectedTool() == DiagramToolBar.LINE) {
			mousePressed = e.getPoint();
			if (fromBoxTitle) {
				this.getDiagramView().mousePressed(e);
			}
		}
	}

	void boxMouseReleased(MouseEvent e) {
		if (diagramView.getDiagramFrame().getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
			if (!this.getBounds().equals(boxModel.getRectangle())) {
				Manager.getSingleton().startTransaction("move box"); // Transaction
				boxModel.setRectangle(this.getBounds()); // for view only
				// dragging
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			//} else if (e.isPopupTrigger()) { // bug na Ubuntu: http://forums.java.net/jive/thread.jspa?threadID=35178
			} else	if ((e.getButton() == MouseEvent.BUTTON3) && (e.getID() == MouseEvent.MOUSE_RELEASED) ) {
				BoxPopupMenu.getSingleton(BoxSection.this).show(
						BoxSection.this, e.getX(), e.getY());
			}
		}
	}

	void boxMouseEntered(MouseEvent e) {
		if (diagramView.getDiagramFrame().getDiagramToolBar().getSelectedTool() != DiagramToolBar.SELECT) {
			this.setCursor(DEFAULT_CURSOR);
		}
	}

	void boxMouseMoved(MouseEvent e) {
		if (diagramView.getDiagramFrame().getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
			this.setCursor(DEFAULT_CURSOR); // cursor type
			Dimension d = this.getSize();
			if (((e.getX() > 0) && (e.getX() < BORDER))
					&& ((e.getY() > 0) && (e.getY() < BORDER)))
				this.setCursor(NW_RESIZE_CURSOR); // \
			else if (((e.getX() > BORDER) && (e.getX() < d.width - BORDER))
					&& ((e.getY() > 0) && (e.getY() < BORDER)))
				this.setCursor(N_RESIZE_CURSOR); // |
			else if (((e.getX() > d.width - BORDER) && (e.getX() < d.width))
					&& ((e.getY() > 0) && (e.getY() < BORDER)))
				this.setCursor(NE_RESIZE_CURSOR); // /
			else if (((e.getX() > d.width - BORDER) && (e.getX() < d.width))
					&& ((e.getY() > BORDER) && (e.getY() < d.height - BORDER)))
				this.setCursor(E_RESIZE_CURSOR); // -
			else if (((e.getX() > d.width - BORDER) && (e.getX() < d.width))
					&& ((e.getY() > d.height - BORDER) && (e.getY() < d.height)))
				this.setCursor(SE_RESIZE_CURSOR); // \
			else if (((e.getX() > BORDER) && (e.getX() < d.width - BORDER))
					&& ((e.getY() > d.height - BORDER) && (e.getY() < d.height)))
				this.setCursor(S_RESIZE_CURSOR); // |
			else if (((e.getX() > 0) && (e.getX() < BORDER))
					&& ((e.getY() > d.height - BORDER) && (e.getY() < d.height)))
				this.setCursor(SW_RESIZE_CURSOR); // /
			else if (((e.getX() > 0) && (e.getX() < BORDER))
					&& ((e.getY() > BORDER) && (e.getY() < d.height - BORDER)))
				this.setCursor(W_RESIZE_CURSOR); // -
		}
	}

	void boxMouseDragged(MouseEvent e) {
		if (diagramView.getDiagramFrame().getDiagramToolBar().getSelectedTool() == DiagramToolBar.SELECT) {
			int ct = this.getCursor().getType();
			Rectangle r = this.getBounds();
			switch (ct) {
			// for view only dragging: replace boxModel with this and
			// after this.setSize(...) use this.validate();
			case Cursor.DEFAULT_CURSOR:
				// box location (x, y) and size (r) before the box has been
				// dragged
				// mousePressed is the old cursor position
				// e.getX() and e.getY() is a new cursor position
				int x = r.x + e.getX() - mousePressed.x;
				int y = r.y + e.getY() - mousePressed.y;
				if (x < 0)
					x = 0;
				if (y < 0)
					y = 0;
				this.setLocation(x, y);
				// boxModel.setLocation(x, y);
				break;
			case Cursor.NW_RESIZE_CURSOR:
				this.setSize(
						// boxModel.setSize(
						sizeBeforeDrag.width + locationBeforeDrag.x - r.x,
						sizeBeforeDrag.height + locationBeforeDrag.y - r.y);
				this.setLocation(
				// boxModel.setLocation (
						r.x + e.getX() - mousePressed.x, r.y + e.getY()
								- mousePressed.y);
				break;
			case Cursor.N_RESIZE_CURSOR:
				this.setSize(
				// boxModel.setSize(
						sizeBeforeDrag.width, sizeBeforeDrag.height
								+ locationBeforeDrag.y - r.y);
				this.setLocation(
				// boxModel.setLocation (
						locationBeforeDrag.x, r.y + e.getY() - mousePressed.y);
				break;
			case Cursor.NE_RESIZE_CURSOR:
				this.setSize(
				// boxModel.setSize(
						e.getX(), sizeBeforeDrag.height + locationBeforeDrag.y
								- r.y);
				this.setLocation(
				// boxModel.setLocation (
						locationBeforeDrag.x, r.y + e.getY() - mousePressed.y);
				break;
			case Cursor.E_RESIZE_CURSOR:
				this.setSize(e.getX(), r.height);
				// boxModel.setSize(e.getX(), r.height);
				break;
			case Cursor.SE_RESIZE_CURSOR:
				this.setSize(e.getX(), e.getY());
				// boxModel.setSize(e.getX(), e.getY());
				break;
			case Cursor.S_RESIZE_CURSOR:
				this.setSize(r.width, e.getY());
				// boxModel.setSize(r.width, e.getY());
				break;
			case Cursor.SW_RESIZE_CURSOR:
				this.setSize(
				// boxModel.setSize(
						sizeBeforeDrag.width + locationBeforeDrag.x - r.x, e
								.getY());
				this.setLocation(
				// boxModel.setLocation (
						r.x + e.getX() - mousePressed.x, locationBeforeDrag.y);
				break;
			case Cursor.W_RESIZE_CURSOR:
				this.setSize(
						// boxModel.setSize(
						sizeBeforeDrag.width + locationBeforeDrag.x - r.x,
						sizeBeforeDrag.height);
				this.setLocation(
				// boxModel.setLocation (
						r.x + e.getX() - mousePressed.x, locationBeforeDrag.y);
				break;
			} // end of switch
			this.validate(); // for view only dragging
		}
	}

}

class ItemsTableModel extends AbstractTableModel implements Observer {

	static final long serialVersionUID = 7168319479760000250L;

	private BoxModel boxModel;

	public ItemsTableModel(BoxModel aBoxModel) {
		super();
		boxModel = aBoxModel;
		this.installObservers();
	}

	private void installObservers() {
		if (boxModel != null) {
			boxModel.addObserver(this);
			ArrayList list = (ArrayList) boxModel.getItems();
			Iterator i = list.iterator();
			while (i.hasNext()) {
				ItemModel itemModel = (ItemModel) i.next();
				itemModel.addObserver(this);
			}
		}
	}

	void deinstallObservers() {
		if (boxModel != null) {
			boxModel.deleteObserver(this);
			ArrayList list = (ArrayList) boxModel.getItems();
			Iterator i = list.iterator();
			while (i.hasNext()) {
				ItemModel itemModel = (ItemModel) i.next();
				itemModel.deleteObserver(this);
			}
		}
	}

	// implemented from TableModel
	public int getRowCount() {
		if (boxModel != null) {
			return boxModel.getItems().size();
		}
		return 0;
	}

	// implemented from TableModel
	public int getColumnCount() {
		return 2;
	}

	// implemented from TableModel
	public Object getValueAt(int r, int c) {
		Object result = null;
		if (boxModel != null) {
			ArrayList list = (ArrayList) boxModel.getItems();
			ItemModel itemModel = (ItemModel) list.get(r);
			if (c == 0) {
				if (itemModel.isOid()) {
					result = BoxSection.OID;
				} else if (itemModel.isId()) {
					result = BoxSection.ID;
				} else if (itemModel.getMin() > 0) {
					result = BoxSection.REQUIRED;
				} else if (itemModel.getMin() == 0) {
					result = BoxSection.OPTIONAL;
				} else {
					result = "";
				}
			} else if (c == 1) {
				result = itemModel.getName();
			}
		}
		return result;
	}

	// overriden from AbstractTableModel
	public String getColumnName(int c) {
		return null;
	}

	// overriden from AbstractTableModel
	public boolean isCellEditable(int r, int c) {
		if (c == 0) {
			return false;
		}
		return true;
	}

	// overriden from AbstractTableModel
	public void setValueAt(Object aValue, int r, int c) {
		if (boxModel != null) {
			ArrayList list = (ArrayList) boxModel.getItems();
			ItemModel itemModel = (ItemModel) list.get(r);
			if (c == 1) {
				Manager.getSingleton().startTransaction("set item name"); // Transaction
				itemModel.setName((String) aValue);
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		}
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		ModelEvent modelEvent = (ModelEvent) arg;
		String opType = modelEvent.getOpType();
		Object opArgument = modelEvent.getOpArgument();

		if ((o instanceof BoxModel) && (opType.equals("add"))
				&& (opArgument instanceof ItemModel)) {
			ItemModel itemModel = (ItemModel) opArgument;
			itemModel.addObserver(this);
		}

		this.fireTableDataChanged();
	}

}

class BoxMouseAdapter extends java.awt.event.MouseAdapter {
	BoxSection adaptee;

	BoxMouseAdapter(BoxSection adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.boxMousePressed(e, false); // false for title as the event
		// source
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.boxMouseReleased(e);
	}

	public void mouseEntered(MouseEvent e) {
		adaptee.boxMouseEntered(e);
	}

}

class BoxMouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	BoxSection adaptee;

	BoxMouseMotionAdapter(BoxSection adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.boxMouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		adaptee.boxMouseMoved(e);
	}

}

class TitleMouseAdapter extends java.awt.event.MouseAdapter {
	BoxSection adaptee;

	TitleMouseAdapter(BoxSection adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		MouseEvent newE = new MouseEvent(adaptee, e.getID(), e.getWhen(), e
				.getModifiers(), e.getX(), e.getY(), e.getClickCount(), e
				.isPopupTrigger());
		adaptee.boxMousePressed(newE, true);
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.boxMouseReleased(e);
	}
}

class TitleMouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	BoxSection adaptee;

	TitleMouseMotionAdapter(BoxSection adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.boxMouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		adaptee.boxMouseMoved(e);
	}

}
