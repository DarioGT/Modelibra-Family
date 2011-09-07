package org.modelibra.modeler.app.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-12
 */
public class BoxesForm extends JFrame implements ListSelectionListener {

	static final long serialVersionUID = 7168319479760000070L;
	
	public static final int FORM_WIDTH = 920;

	public static final int FORM_HEIGHT = 600;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private static final int COLUMN_COUNT = 11;

	private JTable jTable;

	private int selectedRow = -1;

	private BoxesTableModel tableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton addButton = new JButton(Para.getPara().getText("add"));

	private JButton removeButton = new JButton(Para.getPara().getText("remove"));

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			DOWN_ARROW));

	private JButton itemsButton = new JButton(Para.getPara().getText("items"));

	private DiagramModel diagramModel;

	private ItemsForm itemsForm;

	public BoxesForm(DiagramModel aDiagramModel) {
		super();
		try {
			diagramModel = aDiagramModel;
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// this.pack();
	}

	private void init() throws Exception {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		this.setTitle(Para.getPara().getText("boxes"));
		this.setSize(FORM_WIDTH, FORM_HEIGHT);

		tableModel = new BoxesTableModel(diagramModel);
		jTable = new JTable(tableModel);
		jTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(jTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager.getSingleton().startTransaction("new box"); // Transaction
				if (diagramModel.isOidCreated()) {
					new BoxModel(diagramModel, new Point(0, 0), true);
				} else {
					new BoxModel(diagramModel, new Point(0, 0), false);
				}
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					Manager.getSingleton().startTransaction("remove box"); // Transaction
					diagramModel.remove(diagramModel.getBox(getSelectedRow()));
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(upButton);
		upButton.setToolTipText(Para.getPara().getText("up"));
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					Manager.getSingleton().startTransaction("move box up"); // Transaction
					diagramModel.moveBoxUp(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(downButton);
		upButton.setToolTipText(Para.getPara().getText("up"));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					Manager.getSingleton().startTransaction("move box down"); // Transaction
					diagramModel.moveBoxDown(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(itemsButton);
		itemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					BoxModel boxModel = diagramModel.getBox(getSelectedRow());
					itemsForm = new ItemsForm(boxModel);
					itemsForm.setLocation(80, 120);
					itemsForm.setVisible(true);
				}
			}
		});
	}

	public void exit() {
		History.getSingleton().reset();
		tableModel.deinstallObservers();
		if (itemsForm != null)
			itemsForm.exit();
		this.dispose();
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			jTable.setRowSelectionInterval(ix, ix);
			jTable.scrollRectToVisible(jTable.getCellRect(ix, 0, true));
			selectedRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedRow;
	}

	public void selectNextRow() {
		int ix = getSelectedRow();
		setSelectedRow(ix + 1);
	}

	public void selectPreviousRow() {
		int ix = getSelectedRow();
		setSelectedRow(ix - 1);
	}

	private int getRowCount() {
		return jTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(jTable.getSelectedRow());
		if (itemsForm != null) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				BoxModel entity = diagramModel.getBox(getSelectedRow());
				itemsForm.setParentModel(entity);
			}
		}
	}

	// ---------------------------------------------------------------------------
	private class BoxesTableModel extends AbstractTableModel implements
			Observer {

		static final long serialVersionUID = 7168319479760000080L;

		public BoxesTableModel(DiagramModel aDiagramModel) {
			super();
			this.installObservers();
		}

		private void installObservers() {
			if (diagramModel != null) {
				diagramModel.addObserver(this);
				Iterator i = diagramModel.getBoxes().iterator();
				BoxModel boxModel = null;
				while (i.hasNext()) {
					boxModel = (BoxModel) i.next();
					boxModel.addObserver(this);
				}
			}
		}

		void deinstallObservers() {
			if (diagramModel != null) {
				diagramModel.deleteObserver(this);
				Iterator i = diagramModel.getBoxes().iterator();
				BoxModel boxModel = null;
				while (i.hasNext()) {
					boxModel = (BoxModel) i.next();
					boxModel.deleteObserver(this);
				}
			}
		}

		// implemented from TableModel
		public int getRowCount() {
			if (diagramModel != null) {
				return diagramModel.getBoxes().size();
			}
			return 0;
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			if (diagramModel != null) {
				BoxModel boxModel = diagramModel.getBox(r);
				if (c == 0) {
					result = boxModel.getName();
				} else if (c == 1) {
					result = boxModel.getNameInPlural();
				} else if (c == 2) {
					result = boxModel.getAlias();
				} else if (c == 3) {
					result = new Boolean(boxModel.isExtension());
				} else if (c == 4) {
					result = new String(boxModel.getExtensionName());
				} else if (c == 5) {
					result = new Boolean(boxModel.isAbstractDef());
				} else if (c == 6) {
					result = new Boolean(boxModel.isEntry());
				} else if (c == 7) {
					result = new Boolean(boxModel.isSelected());
				} else if (c == 8) {
					result = new Boolean(boxModel.isVisible());
				} else if (c == 9) {
					result = new Integer(boxModel.getWidth());
				} else if (c == 10) {
					result = new Integer(boxModel.getHeight());
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("name");
			} else if (c == 1) {
				return Para.getPara().getText("nameInPlural");
			} else if (c == 2) {
				return Para.getPara().getText("alias");
			} else if (c == 3) {
				return Para.getPara().getText("extension");
			} else if (c == 4) {
				return Para.getPara().getText("extensionName");
			} else if (c == 5) {
				return Para.getPara().getText("abstract");
			} else if (c == 6) {
				return Para.getPara().getText("entry");
			} else if (c == 7) {
				return Para.getPara().getText("selected");
			} else if (c == 8) {
				return Para.getPara().getText("visible");
			} else if (c == 9) {
				return Para.getPara().getText("width");
			} else if (c == 10) {
				return Para.getPara().getText("height");
			}
			return "";
		}

		// overriden from AbstractTableModel
		public boolean isCellEditable(int r, int c) {
			return true;
		}

		// overriden from AbstractTableModel
		public Class getColumnClass(int c) {
			return this.getValueAt(0, c).getClass();
		}

		// overriden from AbstractTableModel
		public void setValueAt(Object aValue, int r, int c) {
			if (diagramModel != null) {
				BoxModel boxModel = diagramModel.getBox(r);
				if (c == 0) {
					Manager.getSingleton().startTransaction("set box name"); // Transaction
					boxModel.setName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 1) {
					Manager.getSingleton().startTransaction(
							"set box name in plural"); // Transaction
					boxModel.setNameInPlural((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 2) {
					Manager.getSingleton().startTransaction("set box alias"); // Transaction
					boxModel.setAlias((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 3) {
					Manager.getSingleton()
							.startTransaction("set box extension"); // Transaction
					boxModel.setExtension(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 4) {
					Manager.getSingleton().startTransaction(
							"set box extension name"); // Transaction
					boxModel.setExtensionName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 5) {
					Manager.getSingleton().startTransaction(
							"set box abstract definition"); // Transaction
					boxModel.setAbstractDef(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 6) {
					Manager.getSingleton().startTransaction("set box entry"); // Transaction
					boxModel.setEntry(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 7) {
					Manager.getSingleton()
							.startTransaction("set box selection"); // Transaction
					boxModel.setSelected(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 8) {
					Manager.getSingleton().startTransaction(
							"set box visibility"); // Transaction
					boxModel.setVisible(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 9) {
					try {
						Manager.getSingleton()
								.startTransaction("set box width"); // Transaction
						boxModel.setWidth(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				} else if (c == 10) {
					try {
						Manager.getSingleton().startTransaction(
								"set box height"); // Transaction
						boxModel.setHeight(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}

		// implemented from Observer
		public void update(Observable o, Object arg) {
			ModelEvent modelEvent = (ModelEvent) arg;
			String opType = modelEvent.getOpType();
			String propertyName = modelEvent.getPropertyName();
			Object opArgument = modelEvent.getOpArgument();

			if ((o instanceof DiagramModel) && (opType.equals("add"))
					&& (opArgument instanceof BoxModel)) {
				BoxModel boxModel = (BoxModel) opArgument;
				boxModel.addObserver(this);
				DiagramModel diagramModel = (DiagramModel) o;
				int ix = diagramModel.getBoxes().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof DiagramModel) && (opType.equals("remove"))
					&& (opArgument instanceof BoxModel)) {
				BoxModel boxModel = (BoxModel) opArgument;
				boxModel.deleteObserver(this);
				DiagramModel diagramModel = (DiagramModel) o;
				int ix = diagramModel.getBoxes().size() - 1;
				setSelectedRow(ix);
				if (ix < 0) {
					itemsForm.setParentModel(null);
					itemsForm.repaint(); // for the last box deleted
				}
			} else if ((o instanceof DiagramModel)
					&& (opType.equals("moveDown"))
					&& (propertyName.equals("boxes"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof DiagramModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("boxes"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			}

			this.fireTableDataChanged();
		}

	} // end of private class BoxesTableModel

}
