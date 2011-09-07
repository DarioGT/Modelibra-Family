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
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-11
 */
public class DirectionsForm extends JFrame implements ListSelectionListener {

	static final long serialVersionUID = 7168319479760000200L;

	public static final int FORM_WIDTH = 800;

	public static final int FORM_HEIGHT = 400;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private static final int COLUMN_COUNT = 10;

	private JTable jTable;

	private int selectedRow = -1;

	private DirectionsTableModel tableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			DOWN_ARROW));

	private BoxModel boxModel;

	private int dir;

	public DirectionsForm(BoxModel aBoxModel, int aDir) {
		super();
		try {
			boxModel = aBoxModel;
			dir = aDir;
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
		this.setTitle(Para.getPara().getText("directions"));
		this.setSize(FORM_WIDTH, FORM_HEIGHT);

		tableModel = new DirectionsTableModel();
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

		buttonPanel.add(upButton);
		upButton.setToolTipText(Para.getPara().getText("up"));
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					if (dir == 12) {
						Manager.getSingleton()
								.startTransaction("move line1 up"); // Transaction
						boxModel.moveLine1Up(ix);
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} else if (dir == 21) {
						Manager.getSingleton()
								.startTransaction("move line2 up"); // Transaction
						boxModel.moveLine2Up(ix);
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					}
				}
			}
		});

		buttonPanel.add(downButton);
		downButton.setToolTipText(Para.getPara().getText("down"));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					int ix = getSelectedRow();
					if (dir == 12) {
						Manager.getSingleton().startTransaction(
								"move line1 down"); // Transaction
						boxModel.moveLine1Down(ix);
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} else if (dir == 21) {
						Manager.getSingleton().startTransaction(
								"move line2 down"); // Transaction
						boxModel.moveLine2Down(ix);
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					}
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
	private class DirectionsTableModel extends AbstractTableModel implements
			Observer {

		static final long serialVersionUID = 7168319479760000210L;

		public DirectionsTableModel() {
			super();
			this.installObservers();
		}

		private void installObservers() {
			if (boxModel != null) {
				boxModel.addObserver(this);
				Iterator i = null;
				if (dir == 12) {
					i = boxModel.getLines1().iterator();
				} else {
					i = boxModel.getLines2().iterator();
				}
				LineModel lineModel = null;
				while (i.hasNext()) {
					lineModel = (LineModel) i.next();
					lineModel.addObserver(this);
					lineModel.getBoxModel1().addObserver(this);
					lineModel.getBoxModel2().addObserver(this);
				}
			}
		}

		void deinstallObservers() {
			if (boxModel != null) {
				boxModel.deleteObserver(this);
				Iterator i = null;
				if (dir == 12) {
					i = boxModel.getLines1().iterator();
				} else {
					i = boxModel.getLines2().iterator();
				}
				LineModel lineModel = null;
				while (i.hasNext()) {
					lineModel = (LineModel) i.next();
					lineModel.deleteObserver(this);
					lineModel.getBoxModel1().deleteObserver(this);
					lineModel.getBoxModel2().deleteObserver(this);
				}
			}
		}

		// implemented from TableModel
		public int getRowCount() {
			if (boxModel != null) {
				if (dir == 12) {
					return boxModel.getLines1().size();
				} else {
					return boxModel.getLines2().size();
				}
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
				LineModel lineModel = null;
				if (dir == 12) {
					lineModel = boxModel.getLine1(r);
				} else {
					lineModel = boxModel.getLine2(r);
				}
				if (c == 0) {
					if (dir == 12) {
						result = lineModel.getBoxModel1().getName();
					} else {
						result = lineModel.getBoxModel2().getName();
					}
				} else if (c == 1) {
					if (dir == 12) {
						result = lineModel.getBoxModel2().getName();
					} else {
						result = lineModel.getBoxModel1().getName();
					}
				} else if (c == 2) {
					result = new Boolean(lineModel.isInheritance());
				} else if (c == 3) {
					if (dir == 12) {
						result = lineModel.getDir12Min();
					} else {
						result = lineModel.getDir21Min();
					}
				} else if (c == 4) {
					if (dir == 12) {
						result = lineModel.getDir12Max();
					} else {
						result = lineModel.getDir21Max();
					}
				} else if (c == 5) {
					if (dir == 12) {
						result = new Boolean(lineModel.isDir12Id());
					} else {
						result = new Boolean(lineModel.isDir21Id());
					}
				} else if (c == 6) {
					if (dir == 12) {
						result = lineModel.getDir12Name();
					} else {
						result = lineModel.getDir21Name();
					}
				} else if (c == 7) {
					if (dir == 12) {
						result = lineModel.getDir12InsertRule();
					} else {
						result = lineModel.getDir21InsertRule();
					}
				} else if (c == 8) {
					if (dir == 12) {
						result = lineModel.getDir12DeleteRule();
					} else {
						result = lineModel.getDir21DeleteRule();
					}
				} else if (c == 9) {
					if (dir == 12) {
						result = lineModel.getDir12UpdateRule();
					} else {
						result = lineModel.getDir21UpdateRule();
					}
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("box");
			} else if (c == 1) {
				return Para.getPara().getText("box");
			} else if (c == 2) {
				return Para.getPara().getText("inheritance");
			} else if (c == 3) {
				return Para.getPara().getText("min");
			} else if (c == 4) {
				return Para.getPara().getText("max");
			} else if (c == 5) {
				return Para.getPara().getText("id");
			} else if (c == 6) {
				return Para.getPara().getText("dir");
			} else if (c == 7) {
				return Para.getPara().getText("insert");
			} else if (c == 8) {
				return Para.getPara().getText("delete");
			} else if (c == 9) {
				return Para.getPara().getText("update");
			}
			return "";
		}

		// overriden from AbstractTableModel
		public boolean isCellEditable(int r, int c) {
			if (c == 0 || c == 1) {
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
				LineModel lineModel = null;
				if (dir == 12) {
					lineModel = boxModel.getLine1(r);
				} else {
					lineModel = boxModel.getLine2(r);
				}
				if (c == 2) {
					Manager.getSingleton().startTransaction(
							"set line inheritance"); // Transaction
					lineModel.setInheritance(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 3) {
					Manager.getSingleton().startTransaction("set line dir min"); // Transaction
					if (dir == 12) {
						lineModel.setDir12Min((String) aValue);
					} else {
						lineModel.setDir21Min((String) aValue);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 4) {
					Manager.getSingleton().startTransaction("set line dir max"); // Transaction
					if (dir == 12) {
						lineModel.setDir12Max((String) aValue);
					} else {
						lineModel.setDir21Max((String) aValue);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 5) {
					Manager.getSingleton().startTransaction("set line dir id"); // Transaction
					if (dir == 12) {
						lineModel.setDir12Id(((Boolean) aValue).booleanValue());
					} else {
						lineModel.setDir21Id(((Boolean) aValue).booleanValue());
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 6) {
					Manager.getSingleton()
							.startTransaction("set line dir name"); // Transaction
					if (dir == 12) {
						lineModel.setDir12Name((String) aValue);
					} else {
						lineModel.setDir21Name((String) aValue);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 7) {
					Manager.getSingleton().startTransaction(
							"set line dir insert rule"); // Transaction
					if (dir == 12) {
						lineModel.setDir12InsertRule((String) aValue);
					} else {
						lineModel.setDir21InsertRule((String) aValue);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 8) {
					Manager.getSingleton().startTransaction(
							"set line dir delete rule"); // Transaction
					if (dir == 12) {
						lineModel.setDir12DeleteRule((String) aValue);
					} else {
						lineModel.setDir21DeleteRule((String) aValue);
					}
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 9) {
					Manager.getSingleton().startTransaction(
							"set line dir update rule"); // Transaction
					if (dir == 12) {
						lineModel.setDir12UpdateRule((String) aValue);
					} else {
						lineModel.setDir21UpdateRule((String) aValue);
					}
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

			if ((o instanceof BoxModel) && (opType.equals("moveDown"))
					&& (propertyName.equals("lines1"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof BoxModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("lines1"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof BoxModel) && (opType.equals("moveDown"))
					&& (propertyName.equals("lines2"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof BoxModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("lines2"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			}

			this.fireTableDataChanged();
		}

	} // end of private class DirectionsTableModel

}
