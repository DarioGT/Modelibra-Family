package org.modelibra.modeler.app.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
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
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-28
 */
public class TypesForm extends JFrame implements ListSelectionListener {
	
	static final long serialVersionUID = 7168319479760000400L;
	
	public static final int FORM_WIDTH = 600;

	public static final int FORM_HEIGHT = 700;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private static final int COLUMN_COUNT = 5;

	protected JTable jTable;

	private int selectedRow = -1;

	protected TypesTableModel tableModel;

	protected JPanel buttonPanel = new JPanel();

	private JButton addButton = new JButton(Para.getPara().getText("add"));

	private JButton removeButton = new JButton(Para.getPara().getText("remove"));

	private JButton removeAllButton = new JButton(Para.getPara().getText(
			"removeAll"));

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			DOWN_ARROW));

	private AppModel appModel = AppModel.getSingleton();

	public TypesForm() {
		super();
		try {
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
		this.setTitle(Para.getPara().getText("types"));
		this.setSize(FORM_WIDTH, FORM_HEIGHT);

		tableModel = new TypesTableModel();
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
				Manager.getSingleton().startTransaction("new type"); // Transaction
				TypeModel typeModel = new TypeModel(appModel);
				appModel.add(typeModel);
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					TypeModel typeModel = appModel.getType(getSelectedRow());
					Manager.getSingleton().startTransaction(
							"detach type from app"); // Transaction
					typeModel.detach(appModel);
					typeModel.removeAllItems();
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(removeAllButton);
		removeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getRowCount() > 0) {
					Collection types = appModel.getTypes();
					TypeModel typeModel = null;
					Manager.getSingleton().startTransaction("remove all types"); // Transaction
					for (Iterator y = types.iterator(); y.hasNext();) {
						typeModel = (TypeModel) y.next();
						typeModel.removeAllItems();
					}
					appModel.removeAllTypes();
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
					Manager.getSingleton().startTransaction("move type up"); // Transaction
					appModel.moveTypeUp(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(downButton);
		downButton.setToolTipText(Para.getPara().getText("down"));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					Manager.getSingleton().startTransaction("move type down"); // Transaction
					appModel.moveTypeDown(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});
	}

	public void exit() {
		History.getSingleton().reset();
		tableModel.deinstallObservers();
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
	}

	// ---------------------------------------------------------------------------
	private class TypesTableModel extends AbstractTableModel implements
			Observer {
		
		static final long serialVersionUID = 7168319479760000410L;

		public TypesTableModel() {
			super();
			this.installObservers();
		}

		void installObservers() {
			if (appModel != null) {
				appModel.addObserver(this);
				Iterator i = appModel.getTypes().iterator();
				TypeModel typeModel = null;
				while (i.hasNext()) {
					typeModel = (TypeModel) i.next();
					typeModel.addObserver(this);
				}
			}
		}

		void deinstallObservers() {
			if (appModel != null) {
				appModel.deleteObserver(this);
				Iterator i = appModel.getTypes().iterator();
				TypeModel typeModel = null;
				while (i.hasNext()) {
					typeModel = (TypeModel) i.next();
					typeModel.deleteObserver(this);
				}
			}
		}

		// implemented from TableModel
		public int getRowCount() {
			if (appModel != null) {
				return appModel.getTypes().size();
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
			if (appModel != null) {
				TypeModel typeModel = appModel.getType(r);
				if (c == 0) {
					result = typeModel.getName();
				} else if (c == 1) {
					result = typeModel.getAlias();
				} else if (c == 2) {
					result = typeModel.getDbmsType();
				} else if (c == 3) {
					result = new Integer(typeModel.getLength());
				} else if (c == 4) {
					result = new Integer(typeModel.getNoOfDec());
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("name");
			} else if (c == 1) {
				return Para.getPara().getText("java");
			} else if (c == 2) {
				return Para.getPara().getText("dbms");
			} else if (c == 3) {
				return Para.getPara().getText("length");
			} else if (c == 4) {
				return Para.getPara().getText("noOfDec");
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
			if (appModel != null) {
				TypeModel typeModel = appModel.getType(r);
				if (c == 0) {
					Manager.getSingleton().startTransaction("set type name"); // Transaction
					typeModel.setName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 1) {
					Manager.getSingleton().startTransaction("set type alias"); // Transaction
					typeModel.setAlias((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 2) {
					Manager.getSingleton().startTransaction(
							"set type base type"); // Transaction
					typeModel.setDbmsType((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 3) {
					try {
						Manager.getSingleton().startTransaction(
								"set type length"); // Transaction
						typeModel.setLength(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				} else if (c == 4) {
					try {
						Manager.getSingleton().startTransaction(
								"set type number of decimals"); // Transaction
						typeModel.setNoOfDec(((Integer) aValue).intValue());
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

			if ((o instanceof AppModel) && (opType.equals("add"))
					&& (opArgument instanceof TypeModel)) {
				TypeModel typeModel = (TypeModel) opArgument;
				typeModel.addObserver(this);
				AppModel appModel = (AppModel) o;
				int ix = appModel.getTypes().size() - 1;
				setSelectedRow(ix);
				this.fireTableRowsInserted(ix, ix);
			} else if ((o instanceof AppModel) && (opType.equals("remove"))
					&& (opArgument instanceof TypeModel)) {
				TypeModel typeModel = (TypeModel) opArgument;
				typeModel.deleteObserver(this);
				AppModel appModel = (AppModel) o;
				int ix = appModel.getTypes().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof AppModel) && (opType.equals("moveDown"))
					&& (propertyName.equals("types"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof AppModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("types"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			}

			this.fireTableDataChanged();
		}

	} // end of private class TypesTableModel

}
