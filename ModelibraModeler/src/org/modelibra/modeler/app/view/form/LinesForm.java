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
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-07
 */
public class LinesForm extends JFrame implements ListSelectionListener {
	
	static final long serialVersionUID = 7168319479760000300L;
	
	public static final int FORM_WIDTH = 1000;

	public static final int FORM_HEIGHT = 600;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private static final int COLUMN_COUNT = 16;

	private JTable jTable;

	private int selectedRow = -1;

	private LinesTableModel tableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton removeButton = new JButton(Para.getPara().getText("remove"));

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara().getImageIconSiblingToClassFile(
			DOWN_ARROW));

	private JButton rulesButton = new JButton(Para.getPara().getText("rules"));

	private LineForm lineForm;

	private DiagramModel diagramModel;

	public LinesForm(DiagramModel aDiagramModel) {
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
		this.setTitle(Para.getPara().getText("lines"));
		this.setSize(FORM_WIDTH, FORM_HEIGHT);

		tableModel = new LinesTableModel(diagramModel);
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

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					Manager.getSingleton().startTransaction("remove line"); // Transaction
					LineModel lineModel = diagramModel
							.getLine(getSelectedRow());
					lineModel.detach(diagramModel);
					// diagramModel.remove(diagramModel.getLine(getSelectedRow()));
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
					Manager.getSingleton().startTransaction("move line up"); // Transaction
					diagramModel.moveLineUp(ix);
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
					Manager.getSingleton().startTransaction("move line down"); // Transaction
					diagramModel.moveLineDown(ix);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		});

		buttonPanel.add(rulesButton);
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
					LineModel lineModel = diagramModel
							.getLine(getSelectedRow());
					lineForm = new LineForm(lineModel);
					lineForm.setLocation(120, 120);
					lineForm.setVisible(true);
				}
			}
		});
	}

	public void exit() {
		History.getSingleton().reset();
		tableModel.deinstallObservers();
		if (lineForm != null)
			lineForm.exit();
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
		if (lineForm != null) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				LineModel lineModel = diagramModel.getLine(getSelectedRow());
				lineForm.setEntityModel(lineModel);
			}
		}
	}

	// ---------------------------------------------------------------------------
	private class LinesTableModel extends AbstractTableModel implements
			Observer {
		
		static final long serialVersionUID = 7168319479760000310L;

		public LinesTableModel(DiagramModel aDiagramModel) {
			super();
			this.installObservers();
		}

		private void installObservers() {
			if (diagramModel != null) {
				diagramModel.addObserver(this);
				Iterator i = diagramModel.getLines().iterator();
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
			if (diagramModel != null) {
				diagramModel.deleteObserver(this);
				Iterator i = diagramModel.getLines().iterator();
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
			if (diagramModel != null) {
				return diagramModel.getLines().size();
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
				LineModel lineModel = diagramModel.getLine(r);
				if (c == 0) {
					result = new Boolean(lineModel.isInheritance());
				} else if (c == 1) {
					result = new Boolean(lineModel.isInternal());
				} else if (c == 2) {
					result = new Boolean(lineModel.isSelected());
				} else if (c == 3) {
					result = new Boolean(lineModel.isVisible());
				} else if (c == 4) {
					result = lineModel.getBoxModel1().getName();
				} else if (c == 5) {
					result = lineModel.getBoxModel2().getName();
				} else if (c == 6) {
					result = lineModel.getDir12Name();
				} else if (c == 7) {
					result = new Boolean(lineModel.isDir12NameVisible());
				} else if (c == 8) {
					result = lineModel.getDir12Min();
				} else if (c == 9) {
					result = lineModel.getDir12Max();
				} else if (c == 10) {
					result = new Boolean(lineModel.isDir12Id());
				} else if (c == 11) {
					result = lineModel.getDir21Name();
				} else if (c == 12) {
					result = new Boolean(lineModel.isDir21NameVisible());
				} else if (c == 13) {
					result = lineModel.getDir21Min();
				} else if (c == 14) {
					result = lineModel.getDir21Max();
				} else if (c == 15) {
					result = new Boolean(lineModel.isDir21Id());
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("inheritance");
			} else if (c == 1) {
				return Para.getPara().getText("internal");
			} else if (c == 2) {
				return Para.getPara().getText("selected");
			} else if (c == 3) {
				return Para.getPara().getText("visible");
			} else if (c == 4) {
				return Para.getPara().getText("box") + "1";
			} else if (c == 5) {
				return Para.getPara().getText("box") + "2";
			} else if (c == 6) {
				return Para.getPara().getText("dir") + "1";
			} else if (c == 7) {
				return Para.getPara().getText("nameVisible");
			} else if (c == 8) {
				return Para.getPara().getText("min") + "1";
			} else if (c == 9) {
				return Para.getPara().getText("max") + "1";
			} else if (c == 10) {
				return Para.getPara().getText("id") + "1";
			} else if (c == 11) {
				return Para.getPara().getText("dir") + "2";
			} else if (c == 12) {
				return Para.getPara().getText("nameVisible");
			} else if (c == 13) {
				return Para.getPara().getText("min") + "2";
			} else if (c == 14) {
				return Para.getPara().getText("max") + "2";
			} else if (c == 15) {
				return Para.getPara().getText("id") + "2";
			}
			return "";
		}

		// overriden from AbstractTableModel
		public boolean isCellEditable(int r, int c) {
			if (c == 4 || c == 5) {
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
			if (diagramModel != null) {
				LineModel lineModel = diagramModel.getLine(r);
				if (c == 0) {
					Manager.getSingleton().startTransaction(
							"set line inheritance"); // Transaction
					lineModel.setInheritance(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 1) {
					Manager.getSingleton()
							.startTransaction("set line internal"); // Transaction
					lineModel.setInternal(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 2) {
					Manager.getSingleton().startTransaction(
							"set line selection"); // Transaction
					lineModel.setSelected(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 3) {
					Manager.getSingleton().startTransaction(
							"set line visibility"); // Transaction
					lineModel.setVisible(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 6) {
					Manager.getSingleton().startTransaction(
							"set line dir12 name"); // Transaction
					lineModel.setDir12Name((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 7) {
					Manager.getSingleton().startTransaction(
							"set line dir12 name visibility"); // Transaction
					lineModel.setDir12NameVisible(((Boolean) aValue)
							.booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 8) {
					Manager.getSingleton().startTransaction(
							"set line dir12 min"); // Transaction
					lineModel.setDir12Min((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 9) {
					Manager.getSingleton().startTransaction(
							"set line dir12 max"); // Transaction
					lineModel.setDir12Max((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 10) {
					Manager.getSingleton()
							.startTransaction("set line dir12 id"); // Transaction
					lineModel.setDir12Id(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 11) {
					Manager.getSingleton().startTransaction(
							"set line dir21 name"); // Transaction
					lineModel.setDir21Name((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 12) {
					Manager.getSingleton().startTransaction(
							"set line dir21 name visibility"); // Transaction
					lineModel.setDir21NameVisible(((Boolean) aValue)
							.booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 13) {
					Manager.getSingleton().startTransaction(
							"set line dir21 min"); // Transaction
					lineModel.setDir21Min((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 14) {
					Manager.getSingleton().startTransaction(
							"set line dir21 max"); // Transaction
					lineModel.setDir21Max((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 15) {
					Manager.getSingleton()
							.startTransaction("set line dir21 id"); // Transaction
					lineModel.setDir21Id(((Boolean) aValue).booleanValue());
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

			if ((o instanceof DiagramModel) && (opType.equals("remove"))
					&& (opArgument instanceof LineModel)) {
				LineModel lineModel = (LineModel) opArgument;
				lineModel.deleteObserver(this);
				DiagramModel diagramModel = (DiagramModel) o;

				// lineModel.detach(diagramModel);

				int ix = diagramModel.getLines().size() - 1;
				setSelectedRow(ix);
				// if (ix < 0) //lineForm appears to always be null
				// lineForm.setEntityModel(null);
			} else if ((o instanceof DiagramModel)
					&& (opType.equals("moveDown"))
					&& (propertyName.equals("lines"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof DiagramModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("lines"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			}

			this.fireTableDataChanged();
		}

	} // end of private class LinesTableModel

}
