package modelibra.swing.designer.metatype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
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

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metatype.MetaType;
import modelibra.designer.metatype.MetaTypes;
import modelibra.swing.app.config.Para;
import modelibra.swing.app.util.FileSelector;
import modelibra.swing.designer.metadomain.DomainsFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

/**
 * Types window with a table of types. You can add a new type, remove the
 * selected type, and update the selected type.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-21
 */
public class TypesFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(TypesFrame.class);

	public static final int TYPES_FRAME_WIDTH = DomainsFrame.DOMAINS_FRAME_WIDTH;
	public static final int TYPES_FRAME_HEIGHT = DomainsFrame.DOMAINS_FRAME_HEIGHT * 3;
	public static final Dimension TYPES_FRAME_SIZE = new Dimension(
			TYPES_FRAME_WIDTH, TYPES_FRAME_HEIGHT);

	private MetaType currentType;
	protected JTable typeTable;
	private int selectedTypeRow = -1;
	private TypeTableModel typeTableModel;

	protected JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton(Para.getOne().getText("add"));
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));

	private TypesMenuBar typesMenuBar = new TypesMenuBar(this);

	private String selectedFile;
	private FileSelector fileSelector;

	public TypesFrame() {
		super();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(TYPES_FRAME_SIZE);

		setJMenuBar(typesMenuBar);
		setTitle(Para.getOne().getText("types"));

		typeTableModel = new TypeTableModel();
		typeTable = new JTable(typeTableModel);
		typeTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(typeTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaTypes types = designer.getMetaTypes();
				MetaType type = new MetaType(designer);
				type.setCode(Para.getOne().getText("type"));
				EntitiesAction action = new AddAction(designer.getSession(),
						types, type);
				action.execute();
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaTypes types = designer.getMetaTypes();
				if (currentType != null) {
					if (currentType.getMetaProperties().size() == 0) {
						EntitiesAction action = new RemoveAction(designer
								.getSession(), types, currentType);
						action.execute();
					}
				}
			}
		});

		fileSelector = new FileSelector();
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			typeTable.setRowSelectionInterval(ix, ix);
			typeTable.scrollRectToVisible(typeTable.getCellRect(ix, 0, true));
			selectedTypeRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedTypeRow;
	}

	private int getRowCount() {
		return typeTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(typeTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaType type = designer.getMetaTypes().getMetaType(
					getSelectedRow());
			if (type != currentType) {
				currentType = type;
			}
		}
	}

	private class TypeTableModel extends AbstractTableModel implements Observer {

		private static final long serialVersionUID = 1L;

		private static final int COLUMN_COUNT = 3;

		public TypeTableModel() {
			super();
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			Designer designer = ModelibraData.getOne().getDesigner();
			return designer.getMetaTypes().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaType metaModel = designer.getMetaTypes().getMetaType(r);
			if (c == 0) {
				result = metaModel.getCode();
			} else if (c == 1) {
				result = metaModel.getBase();
			} else if (c == 2) {
				result = metaModel.getLength();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getOne().getText("name");
			} else if (c == 1) {
				return Para.getOne().getText("baseType");
			} else if (c == 2) {
				return Para.getOne().getText("length");
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			return true;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			if (getValueAt(0, c) != null) {
				return getValueAt(0, c).getClass();
			} else if (c == 2) {
				return Integer.class;
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaType type = designer.getMetaTypes().getMetaType(r);
			MetaType typeCopy = type.copy();
			if (c == 0) {
				typeCopy.setCode((String) value);
			} else if (c == 1) {
				typeCopy.setBase((String) value);
			} else if (c == 2) {
				typeCopy.setLength((Integer) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					designer.getMetaTypes(), type, typeCopy);
			action.execute();
		}

		// implemented from Observer
		public void update(Observable o, Object arg) {
			try {
				Designer designer = ModelibraData.getOne().getDesigner();
				if (o == designer) {
					if (arg instanceof Transaction) {
						Transaction transaction = (Transaction) arg;
						List<EntitiesAction> entitiesActions = transaction
								.getEntitiesActions();
						for (EntitiesAction entitiesAction : entitiesActions) {
							IEntities<?> entities = entitiesAction
									.getEntities();
							if (entities instanceof MetaTypes) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = designer.getMetaTypes().size() - 1;
									setSelectedRow(ix);
									fireTableDataChanged();
								} else if (entitiesAction instanceof UpdateAction) {
									fireTableDataChanged();
								}
							}
						}
					}
					if (arg instanceof EntitiesAction) {
						EntitiesAction entitiesAction = (EntitiesAction) arg;
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaTypes) {
							if (entitiesAction instanceof AddAction
									|| entitiesAction instanceof RemoveAction) {
								int ix = designer.getMetaTypes().size() - 1;
								setSelectedRow(ix);
								fireTableDataChanged();
							} else if (entitiesAction instanceof UpdateAction) {
								fireTableDataChanged();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("Error in TypesFrame.TypeTableModel.update: "
						+ e.getMessage());
			}
		}

	}

	private void loadTypes(String filePath) {
		try {
			ModelibraData.getOne().loadTypes(filePath);
			typeTableModel.fireTableDataChanged();
		} catch (Exception e) {
			log.error("Load data error from TypesFrame.loadData: "
					+ e.getMessage());
		}
	}

	private void saveTypes(String filePath) {
		try {
			ModelibraData.getOne().saveTypes(filePath);
		} catch (Exception e) {
			log.error("Save data error from TypesFrame.saveData: "
					+ e.getMessage());
		}
	}

	private void emptyTypes() {
		ModelibraData modelibraData = ModelibraData.getOne();
		modelibraData.getDesigner().emptyTypes();
		modelibraData.getDesigner().getSession().getHistory().empty();
	}

	void neww() {
		close();
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
	}

	void open() {
		close();
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
		loadTypes(selectedFile);
		setSelectedRow(0);
	}

	void close() {
		emptyTypes();
		currentType = null;
		selectedFile = null;
	}

	void save() {
		if (selectedFile != null) {
			saveTypes(selectedFile);
		} else {
			saveAs();
		}
	}

	void saveAs() {
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
		if (selectedFile != null) {
			save();
		}
	}

	public void exit() {
		dispose();
	}

}