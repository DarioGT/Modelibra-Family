package org.modelibra.modeler.app.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-06
 */
public class ItemsForm extends JFrame implements ListSelectionListener {

	static final long serialVersionUID = 7168319479760000240L;

	public static final int FORM_WIDTH = 800;

	public static final int FORM_HEIGHT = 400;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private static final int COLUMN_COUNT = 9;

	private JTable jTable;

	private int selectedRow = -1;

	private ItemsTableModel tableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton addButton = new JButton(Para.getPara().getText("add"));

	private JButton removeButton = new JButton(Para.getPara().getText("remove"));

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			DOWN_ARROW));

	private JButton typesButton = new JButton(Para.getPara().getText("types"));

	private BoxModel boxModel;

	private TypeLookupForm typeLookupForm;

	public ItemsForm(BoxModel aBoxModel) {
		super();
		try {
			boxModel = aBoxModel;
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
		this.setTitle(Para.getPara().getText("items"));
		this.setSize(FORM_WIDTH, FORM_HEIGHT);

		tableModel = new ItemsTableModel(boxModel);
		jTable = new JTable(tableModel);
		jTable.getSelectionModel().addListSelectionListener(this);
		if (jTable.getRowCount() > 1) {
			if (boxModel.getDiagramModel().isOidCreated())
				setSelectedRow(1);
			else
				setSelectedRow(0);
		} else if (jTable.getRowCount() == 1)
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
				Manager.getSingleton().startTransaction("new item"); // Transaction
				ItemModel itemModel = new ItemModel(boxModel);
				TypeModel stringTypeModel = AppModel.getSingleton()
						.getStringType();
				if (stringTypeModel != null) {
					itemModel.attach(stringTypeModel);
				}
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					Manager.getSingleton().startTransaction("remove item"); // Transaction
					boxModel.remove(boxModel.getItem(getSelectedRow()));
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
					Manager.getSingleton().startTransaction("move item up"); // Transaction
					boxModel.moveItemUp(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(downButton);
		upButton.setToolTipText(Para.getPara().getText("down"));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					Manager.getSingleton().startTransaction("move item down"); // Transaction
					boxModel.moveItemDown(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(typesButton);
		typesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					displayTypes(boxModel.getItem(getSelectedRow()));
				}
			}
		});
	}

	public void setParentModel(BoxModel parent) {
		tableModel.deinstallObservers();
		jTable.removeAll();
		boxModel = parent;
		if (boxModel != null) {
			tableModel = new ItemsTableModel(boxModel);
			jTable.setModel(tableModel);
		}
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
		if (typeLookupForm != null) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				typeLookupForm.setItemModel(boxModel.getItem(getSelectedRow()));
			}
		}
	}

	private void displayTypes(ItemModel anItemModel) {
		typeLookupForm = new TypeLookupForm(this, anItemModel);
		typeLookupForm.setLocation(0, 20);
		typeLookupForm.setVisible(true);
	}

	public void exit() {
		History.getSingleton().reset();
		tableModel.deinstallObservers();
		if (typeLookupForm != null)
			typeLookupForm.exit();
		this.dispose();
	}

	// ---------------------------------------------------------------------------
	private class ItemsTableModel extends AbstractTableModel implements
			Observer {

		static final long serialVersionUID = 7168319479760000260L;

		public ItemsTableModel(BoxModel aBoxModel) {
			super();
			boxModel = aBoxModel;
			this.installObservers();
		}

		private void installObservers() {
			if (boxModel != null) {
				boxModel.addObserver(this);
				Iterator i = boxModel.getItems().iterator();
				ItemModel itemModel = null;
				while (i.hasNext()) {
					itemModel = (ItemModel) i.next();
					itemModel.addObserver(this);
					if (itemModel.getTypeModel() != null) {
						itemModel.getTypeModel().addObserver(this);
					}
				}
			}
		}

		void deinstallObservers() {
			if (boxModel != null) {
				boxModel.deleteObserver(this);
				Iterator i = boxModel.getItems().iterator();
				ItemModel itemModel = null;
				while (i.hasNext()) {
					itemModel = (ItemModel) i.next();
					itemModel.deleteObserver(this);
					if (itemModel.getTypeModel() != null) {
						itemModel.getTypeModel().deleteObserver(this);
					}
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
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			if (boxModel != null) {
				ItemModel itemModel = boxModel.getItem(r);
				if (c == 0) {
					result = itemModel.getName();
				} else if (c == 1) {
					result = itemModel.getAlias();
				} else if (c == 2) {
					result = new Integer(itemModel.getMin());
				} else if (c == 3) {
					result = new Integer(itemModel.getMax());
				} else if (c == 4) {
					result = new Boolean(itemModel.isOid());
				} else if (c == 5) {
					result = new Boolean(itemModel.isId());
				} else if (c == 6) {
					result = itemModel.getDefaultValue();
				} else if (c == 7) {
					result = new Boolean(itemModel.isIncrement());
				} else if (c == 8) {
					TypeModel tm = itemModel.getTypeModel();
					if (tm == null) {
						result = "";
					} else {
						result = tm.getName();
					}
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("name");
			} else if (c == 1) {
				return Para.getPara().getText("alias");
			} else if (c == 2) {
				return Para.getPara().getText("min");
			} else if (c == 3) {
				return Para.getPara().getText("max");
			} else if (c == 4) {
				return Para.getPara().getText("oid");
			} else if (c == 5) {
				return Para.getPara().getText("id");
			} else if (c == 6) {
				return Para.getPara().getText("default");
			} else if (c == 7) {
				return Para.getPara().getText("increment");
			} else if (c == 8) {
				return Para.getPara().getText("type");
			}
			return "";
		}

		// overriden from AbstractTableModel
		public boolean isCellEditable(int r, int c) {
			if (c == 8) {
				return false;
			}
			return true;
		}

		// overriden from AbstractTableModel
		public Class getColumnClass(int c) {
			return this.getValueAt(0, c).getClass();
		}

		// overriden from AbstractTableModel
		public void setValueAt(Object aValue, int r, int c) {
			if (boxModel != null) {
				ItemModel itemModel = boxModel.getItem(r);
				if (c == 0) {
					Manager.getSingleton().startTransaction("set item name"); // Transaction
					itemModel.setName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 1) {
					Manager.getSingleton().startTransaction("set item alias"); // Transaction
					itemModel.setAlias((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 2) {
					try {
						Manager.getSingleton().startTransaction("set item min"); // Transaction
						itemModel.setMin(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				} else if (c == 3) {
					try {
						Manager.getSingleton().startTransaction("set item max"); // Transaction
						itemModel.setMax(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				} else if (c == 4) {
					Manager.getSingleton().startTransaction("set item oid"); // Transaction
					itemModel.setOid(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 5) {
					Manager.getSingleton().startTransaction("set item id"); // Transaction
					itemModel.setId(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 6) {
					Manager.getSingleton().startTransaction(
							"set item default value"); // Transaction
					itemModel.setDefaultValue((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 7) {
					Manager.getSingleton().startTransaction(
							"set item increment"); // Transaction
					itemModel.setIncrement(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		}

		// implemented from Observer
		public void update(Observable o, Object arg) {
			ModelEvent modelEvent = (ModelEvent) arg;
			String opType = modelEvent.getOpType();
			String propertyName = modelEvent.getPropertyName();
			Object opArgument = modelEvent.getOpArgument();

			if ((o instanceof BoxModel) && (opType.equals("add"))
					&& (opArgument instanceof ItemModel)) {
				ItemModel itemModel = (ItemModel) opArgument;
				itemModel.addObserver(this);
				BoxModel boxModel = (BoxModel) o;
				int ix = boxModel.getItems().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof BoxModel) && (opType.equals("remove"))
					&& (opArgument instanceof ItemModel)) {
				ItemModel itemModel = (ItemModel) opArgument;
				itemModel.deleteObserver(this);
				BoxModel boxModel = (BoxModel) o;
				int ix = boxModel.getItems().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof ItemModel) && (opType.equals("attach"))
					&& (opArgument instanceof TypeModel)) {
				TypeModel typeModel = (TypeModel) opArgument;
				typeModel.addObserver(this);
			} else if ((o instanceof BoxModel) && (opType.equals("moveDown"))
					&& (propertyName.equals("items"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof BoxModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("items"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			}

			this.fireTableDataChanged();
			repaint(); // to avoid a bad refresh after ataching an item to a
			// type
			// still the same problem when undoing the attachment
		}

	} // end of private class ItemsTableModel

}
