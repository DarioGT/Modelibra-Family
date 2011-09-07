package org.modelibra.modeler.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.modelibra.modeler.app.context.AppFrame;
import org.modelibra.modeler.app.context.DiagramFrame;
import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.gen.Transfer;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-30
 */
public class AppSection extends JPanel implements ActionListener, Observer,
		ListSelectionListener {

	static final long serialVersionUID = 7168319479760000020L;

	private static final Color DARK_COLOR = Color.GRAY;

	private static final int DIAGRAM_X = 20;

	private static final int DIAGRAM_Y = 20;

	private static final int NAME_FIELD_LENGTH = 150;

	private static final int FILE_FIELD_LENGTH = 600;

	private static final String UP_ARROW = "img/upArrow.gif";

	private static final String DOWN_ARROW = "img/downArrow.gif";

	private AppModel appModel = AppModel.getSingleton();

	private JPanel app = new JPanel();

	private JPanel appTop = new JPanel();

	private JPanel appBottom = new JPanel();

	private JCheckBox oidCreatedCheckBox = new JCheckBox(Para.getPara()
			.getText("oidCreated"), appModel.isOidCreated());

	private JCheckBox dirNameDisplayedCheckBox = new JCheckBox(Para.getPara()
			.getText("dirNameDisplayed"), appModel.isDirNameDisplayed());

	private JCheckBox blackAndWhiteCheckBox = new JCheckBox(Para.getPara()
			.getText("blackAndWhite"), false);

	private JLabel authorLabel = new JLabel(Para.getPara().getText("author"));

	private JLabel prefixLabel = new JLabel(Para.getPara().getText("prefix"));

	private JTextField authorField = new JTextField(appModel.getAuthor());

	private JTextField prefixField = new JTextField(appModel.getPrefix());

	private JLabel nameLabel = new JLabel(Para.getPara().getText("domainName"));

	private JTextField nameField = new JTextField(appModel.getName());

	private JLabel aliasLabel = new JLabel(Para.getPara().getText("file"));

	private JTextField aliasField = new JTextField(appModel.getAlias());

	private JTable jTable;

	private int selectedRow = -1;

	private DiagramsTableModel diagramsTableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton addButton = new JButton(Para.getPara().getText("add"));

	private JButton removeButton = new JButton(Para.getPara().getText("remove"));

	private JButton upButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(UP_ARROW));

	private JButton downButton = new JButton(Para.getPara()
			.getImageIconSiblingToClassFile(DOWN_ARROW));

	private JButton copyButton = new JButton(Para.getPara().getText("copy"));

	private JButton inheritAllButton = new JButton(Para.getPara().getText(
			"inheritAll"));

	private JButton inheritSomeButton = new JButton(Para.getPara().getText(
			"inheritSome"));

	private JButton diagramButton = new JButton(Para.getPara().getText(
			"diagram"));

	private AppFrame appFrame;

	public AppSection(AppFrame aFrame) {
		super();
		try {
			appFrame = aFrame;
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public AppModel getModel() {
		return appModel;
	}

	public void setModel(AppModel instance) {
		appModel = instance;
		this.installObservers();
		diagramsTableModel.setModel(instance);
	}

	public void installObservers() {
		appModel.addObserver(this);
		diagramsTableModel.installObservers();
	}

	public void deinstallObservers() {
		appModel.deleteObserver(this);
		diagramsTableModel.deinstallObservers();
	}

	private boolean isBlackAndWhite() {
		return this.getModel().isBlackAndWhite();
	}

	private void setBackgroundColor(JComponent component, Color color) {
		if (this.isBlackAndWhite()) {
			component.setBackground(DARK_COLOR);
		} else {
			component.setBackground(color);
		}
	}

	private void init() throws Exception {
		appModel.addObserver(this);

		this.setLayout(new BorderLayout());
		this.add(app, BorderLayout.NORTH);

		authorField.setPreferredSize(new Dimension(NAME_FIELD_LENGTH,
				authorField.getPreferredSize().height));
		prefixField.setPreferredSize(new Dimension(NAME_FIELD_LENGTH,
				prefixField.getPreferredSize().height));
		nameField.setPreferredSize(new Dimension(NAME_FIELD_LENGTH, nameField
				.getPreferredSize().height));
		aliasField.setPreferredSize(new Dimension(FILE_FIELD_LENGTH, aliasField
				.getPreferredSize().height));

		aliasField.setEnabled(false);

		app.setLayout(new BorderLayout());
		app.add(appTop, BorderLayout.NORTH);
		app.add(appBottom, BorderLayout.SOUTH);
		this.setBackgroundColor(appTop, appModel.getColor());
		this.setBackgroundColor(appBottom, appModel.getColor());
		appTop.add(authorLabel);
		appTop.add(authorField);
		appTop.add(prefixLabel);
		appTop.add(prefixField);
		appTop.add(nameLabel);
		appTop.add(nameField);
		appTop.add(oidCreatedCheckBox);
		appTop.add(dirNameDisplayedCheckBox);
		appTop.add(blackAndWhiteCheckBox);
		appBottom.add(aliasLabel);
		appBottom.add(aliasField);

		this.setBackgroundColor(oidCreatedCheckBox, appModel.getColor());
		oidCreatedCheckBox.addActionListener(this);
		this.setBackgroundColor(dirNameDisplayedCheckBox, appModel.getColor());
		dirNameDisplayedCheckBox.addActionListener(this);
		this.setBackgroundColor(blackAndWhiteCheckBox, appModel.getColor());
		blackAndWhiteCheckBox.addActionListener(this);
		authorField.addActionListener(this);
		prefixField.addActionListener(this);
		nameField.addActionListener(this);

		diagramsTableModel = new DiagramsTableModel();
		jTable = new JTable(diagramsTableModel);
		jTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		JScrollPane jScrollPane = new JScrollPane(jTable);
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);

		buttonPanel.add(addButton);
		addButton.addActionListener(this);

		buttonPanel.add(removeButton);
		removeButton.addActionListener(this);

		buttonPanel.add(upButton);
		upButton.setToolTipText(Para.getPara().getText("up"));
		upButton.addActionListener(this);

		buttonPanel.add(downButton);
		downButton.setToolTipText(Para.getPara().getText("down"));
		downButton.addActionListener(this);

		buttonPanel.add(copyButton);
		copyButton.addActionListener(this);

		buttonPanel.add(inheritAllButton);
		inheritAllButton.addActionListener(this);

		buttonPanel.add(inheritSomeButton);
		inheritSomeButton.addActionListener(this);

		buttonPanel.add(diagramButton);
		diagramButton.addActionListener(this);
	}

	// implemented from ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (appModel.typesCount() == 0) {
				Manager.getSingleton().startTransaction("Modelibra types import"); // Transaction
				Transfer.importTypes(Config.getConfig().getModelibraTypesUrl());
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
			Manager.getSingleton().startTransaction("new diagram"); // Transaction
			new DiagramModel(appModel);
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == removeButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				Manager.getSingleton().startTransaction("remove diagram"); // Transaction
				appModel.remove(appModel.getDiagram(getSelectedRow()));
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		} else if (e.getSource() == upButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				int ix = getSelectedRow();
				Manager.getSingleton().startTransaction("move diagram up"); // Transaction
				appModel.moveDiagramUp(ix);
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		} else if (e.getSource() == downButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				int ix = getSelectedRow();
				Manager.getSingleton().startTransaction("move diagram down"); // Transaction
				appModel.moveDiagramDown(ix);
				Manager.getSingleton().commit(); // Transaction
				// ------------------------------
			}
		} else if (e.getSource() == copyButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				DiagramModel diagramModel = appModel
						.getDiagram(getSelectedRow());
				if (diagramModel.hasUnnamedBox()) {
					JOptionPane.showMessageDialog(null, // context frame
							Para.getPara().getText("notNamedBoxes"), // message
							Para.getPara().getText("error"), // title
							JOptionPane.ERROR_MESSAGE); // message type
				} else {
					Manager.getSingleton()
							.startTransaction("deep copy diagram"); // Transaction
					AppModel.getSingleton().deepCopyDiagram(diagramModel);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		} else if (e.getSource() == inheritAllButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				DiagramModel diagramModel = appModel
						.getDiagram(getSelectedRow());
				if (diagramModel.hasUnnamedBox()) {
					JOptionPane.showMessageDialog(null, // context frame
							Para.getPara().getText("notNamedBoxes"), // message
							Para.getPara().getText("error"), // title
							JOptionPane.ERROR_MESSAGE); // message type
				} else {
					Manager.getSingleton().startTransaction(
							"inherit all in diagram"); // Transaction
					AppModel.getSingleton().inheritAll(diagramModel);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		} else if (e.getSource() == inheritSomeButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				DiagramModel diagramModel = appModel
						.getDiagram(getSelectedRow());
				if (diagramModel.hasUnnamedBox()) {
					JOptionPane.showMessageDialog(null, // context frame
							Para.getPara().getText("notNamedBoxes"), // message
							Para.getPara().getText("error"), // title
							JOptionPane.ERROR_MESSAGE); // message type
				} else {
					Manager.getSingleton().startTransaction(
							"inherit some in diagram"); // Transaction
					AppModel.getSingleton().inheritSome(diagramModel);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				}
			}
		} else if (e.getSource() == diagramButton) {
			if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
				DiagramModel diagramModel = appModel
						.getDiagram(getSelectedRow());
				DiagramFrame diagramFrame = new DiagramFrame(diagramModel,
						appFrame);
				diagramFrame.setLocation(DIAGRAM_X, DIAGRAM_Y);
				diagramFrame.setVisible(true);
			}
		} else if (e.getSource() == oidCreatedCheckBox) {
			Manager.getSingleton().startTransaction("set app oid created"); // Transaction
			appModel.setOidCreated(oidCreatedCheckBox.isSelected());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == dirNameDisplayedCheckBox) {
			Manager.getSingleton().startTransaction(
					"set app dir name displayed"); // Transaction
			appModel.setDirNameDisplayed(dirNameDisplayedCheckBox.isSelected());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == blackAndWhiteCheckBox) {
			Manager.getSingleton().startTransaction("set app black and white"); // Transaction
			appModel.setBlackAndWhite(blackAndWhiteCheckBox.isSelected());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == authorField) {
			Manager.getSingleton().startTransaction("set app author"); // Transaction
			appModel.setAuthor(authorField.getText());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == prefixField) {
			Manager.getSingleton().startTransaction("set app prefix"); // Transaction
			appModel.setPrefix(prefixField.getText());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		} else if (e.getSource() == nameField) {
			Manager.getSingleton().startTransaction("set app name"); // Transaction
			appModel.setName(nameField.getText());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
		}
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		aliasField.setText(appModel.getAlias());
		authorField.setText(appModel.getAuthor());
		prefixField.setText(appModel.getPrefix());
		nameField.setText(appModel.getName());
		oidCreatedCheckBox.setSelected(appModel.isOidCreated());
		dirNameDisplayedCheckBox.setSelected(appModel.isDirNameDisplayed());
		blackAndWhiteCheckBox.setSelected(appModel.isBlackAndWhite());
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
	private class DiagramsTableModel extends AbstractTableModel implements
			Observer {

		static final long serialVersionUID = 7168319479760000180L;

		private static final int COLUMN_COUNT = 11;

		public DiagramsTableModel() {
			super();
			this.installObservers();
		}

		public void setModel(AppModel instance) {
			appModel = instance;
			this.installObservers();
		}

		void installObservers() {
			if (appModel != null) {
				appModel.addObserver(this);
				Iterator i = appModel.getDiagrams().iterator();
				DiagramModel diagramModel = null;
				while (i.hasNext()) {
					diagramModel = (DiagramModel) i.next();
					diagramModel.addObserver(this);
				}
			}
		}

		void deinstallObservers() {
			if (appModel != null) {
				appModel.deleteObserver(this);
				Iterator i = appModel.getDiagrams().iterator();
				DiagramModel diagramModel = null;
				while (i.hasNext()) {
					diagramModel = (DiagramModel) i.next();
					diagramModel.deleteObserver(this);
				}
			}
		}

		// implemented from TableModel
		public int getRowCount() {
			if (appModel != null) {
				return appModel.getDiagrams().size();
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
				DiagramModel diagramModel = appModel.getDiagram(r);
				if (c == 0) {
					result = diagramModel.getName();
				} else if (c == 1) {
					result = diagramModel.getAuthor();
				} else if (c == 2) {
					result = diagramModel.getAlias();
				} else if (c == 3) {
					result = new Boolean(diagramModel.isExtension());
				} else if (c == 4) {
					result = new String(diagramModel.getExtensionName());
				} else if (c == 5) {
					result = new Boolean(diagramModel.isAbstractDef());
				} else if (c == 6) {
					result = new String(diagramModel.getPersistence());
				} else if (c == 7) {
					result = new Boolean(diagramModel.isOidCreated());
				} else if (c == 8) {
					result = new Boolean(diagramModel.isDirNameDisplayed());
				} else if (c == 9) {
					result = new Integer(diagramModel.getWidth());
				} else if (c == 10) {
					result = new Integer(diagramModel.getHeight());
				}
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getPara().getText("name");
			} else if (c == 1) {
				return Para.getPara().getText("author");
			} else if (c == 2) {
				return Para.getPara().getText("alias");
			} else if (c == 3) {
				return Para.getPara().getText("extension");
			} else if (c == 4) {
				return Para.getPara().getText("extensionName");
			} else if (c == 5) {
				return Para.getPara().getText("abstract");
			} else if (c == 6) {
				return Para.getPara().getText("persistence");
			} else if (c == 7) {
				return Para.getPara().getText("oidCreated");
			} else if (c == 8) {
				return Para.getPara().getText("dirNameDisplayed");
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
			if (appModel != null) {
				DiagramModel diagramModel = appModel.getDiagram(r);
				if (c == 0) {
					Manager.getSingleton().startTransaction("set diagram name"); // Transaction
					diagramModel.setName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 1) {
					Manager.getSingleton().startTransaction(
							"set diagram author"); // Transaction
					diagramModel.setAuthor((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 2) {
					Manager.getSingleton()
							.startTransaction("set diagram alias"); // Transaction
					diagramModel.setAlias((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 3) {
					Manager.getSingleton().startTransaction(
							"set diagram extension"); // Transaction
					diagramModel
							.setExtension(((Boolean) aValue).booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 4) {
					Manager.getSingleton().startTransaction(
							"set diagram extension name"); // Transaction
					diagramModel.setExtensionName((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 5) {
					Manager.getSingleton().startTransaction(
							"set diagram abstract def"); // Transaction
					diagramModel.setAbstractDef(((Boolean) aValue)
							.booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 6) {
					Manager.getSingleton().startTransaction(
							"set diagram persistence"); // Transaction
					diagramModel.setPersistence((String) aValue);
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 7) {
					Manager.getSingleton().startTransaction(
							"set diagram oid created"); // Transaction
					diagramModel.setOidCreated(((Boolean) aValue)
							.booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 8) {
					Manager.getSingleton().startTransaction(
							"set diagram dir name displayed"); // Transaction
					diagramModel.setDirNameDisplayed(((Boolean) aValue)
							.booleanValue());
					Manager.getSingleton().commit(); // Transaction
					// ------------------------------
				} else if (c == 9) {
					try {
						Manager.getSingleton().startTransaction(
								"set diagram width"); // Transaction
						diagramModel.setWidth(((Integer) aValue).intValue());
						Manager.getSingleton().commit(); // Transaction
						// ------------------------------
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
				} else if (c == 10) {
					try {
						Manager.getSingleton().startTransaction(
								"set diagram height"); // Transaction
						diagramModel.setHeight(((Integer) aValue).intValue());
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
					&& (opArgument instanceof DiagramModel)) {
				DiagramModel diagramModel = (DiagramModel) opArgument;
				diagramModel.addObserver(this);
				AppModel appModel = (AppModel) o;
				int ix = appModel.getDiagrams().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof AppModel) && (opType.equals("remove"))
					&& (opArgument instanceof DiagramModel)) {
				DiagramModel diagramModel = (DiagramModel) opArgument;
				diagramModel.deleteObserver(this);
				AppModel appModel = (AppModel) o;
				int ix = appModel.getDiagrams().size() - 1;
				setSelectedRow(ix);
			} else if ((o instanceof AppModel) && (opType.equals("moveDown"))
					&& (propertyName.equals("diagrams"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else if ((o instanceof AppModel) && (opType.equals("moveUp"))
					&& (propertyName.equals("diagrams"))
					&& (opArgument instanceof Integer)) {
				int ix = ((Integer) opArgument).intValue();
				setSelectedRow(ix);
			} else {
				setSelectedRow(0);
			}

			this.fireTableDataChanged();
		}

	} // end of private class DiagramsTableModel

}
