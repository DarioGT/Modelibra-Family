package modelibra.swing.designer.metaproperty;

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
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaproperty.MetaProperties;
import modelibra.designer.metaproperty.MetaProperty;
import modelibra.designer.metatype.MetaType;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metatype.TypeLookupFrame;
import modelibra.swing.designer.metatype.TypesFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

/**
 * Properties window with a table of properties. You can add a new property,
 * remove the selected property, and update the selected property.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-20
 */
public class PropertiesFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(PropertiesFrame.class);

	public static final int PROPERTIES_FRAME_WIDTH = 720;
	public static final int PROPERTIES_FRAME_HEIGHT = 320;
	public static final Dimension PROPERTIES_FRAME_SIZE = new Dimension(
			PROPERTIES_FRAME_WIDTH, PROPERTIES_FRAME_HEIGHT);

	private MetaProperty currentProperty;
	private JTable propertyTable;
	private int selectedPropertyRow = -1;
	private PropertyTableModel propertyTableModel;

	private JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton(Para.getOne().getText("add"));
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));
	private JButton attachButton = new JButton(Para.getOne().getText("type"));

	private TypeLookupFrame typeLookupFrame;

	private MetaConcept metaConcept;

	public PropertiesFrame(final MetaConcept metaConcept) {
		this.metaConcept = metaConcept;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(PROPERTIES_FRAME_SIZE);
		setTitle(Para.getOne().getText("properties"));

		propertyTableModel = new PropertyTableModel();
		propertyTable = new JTable(propertyTableModel);
		propertyTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(propertyTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaProperties properties = metaConcept.getMetaProperties();
				MetaProperty property = new MetaProperty(metaConcept);
				property.setCode(Para.getOne().getText("propertyName"));
				EntitiesAction action = new AddAction(designer.getSession(),
						properties, property);
				action.execute();
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaProperties properties = metaConcept.getMetaProperties();
				if (currentProperty != null) {
					EntitiesAction action = new RemoveAction(designer
							.getSession(), properties, currentProperty);
					action.execute();
				}
			}
		});

		buttonPanel.add(attachButton);
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaProperties properties = metaConcept.getMetaProperties();
				if (currentProperty != null) {
					typeLookupFrame = new TypeLookupFrame(PropertiesFrame.this,
							currentProperty);
					int x = (int) PropertiesFrame.this.getLocation().getX();
					int y = (int) PropertiesFrame.this.getLocation().getY();
					int newY = y - TypesFrame.TYPES_FRAME_HEIGHT;
					if (newY < 0) {
						newY = 0;
					}
					typeLookupFrame.setLocation(x, newY);
					typeLookupFrame.setVisible(true);
				}
			}
		});

	}

	public MetaProperty getCurrentProperty() {
		return currentProperty;
	}

	public void exit() {
		if (typeLookupFrame != null) {
			typeLookupFrame.exit();
		}
		dispose();
	}

	public void selectNextRow() {
		int ix = getSelectedRow();
		setSelectedRow(ix + 1);
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			propertyTable.setRowSelectionInterval(ix, ix);
			propertyTable.scrollRectToVisible(propertyTable.getCellRect(ix, 0,
					true));
			selectedPropertyRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedPropertyRow;
	}

	private int getRowCount() {
		return propertyTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(propertyTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			currentProperty = metaConcept.getMetaProperties().getMetaProperty(
					getSelectedRow());
		}
	}

	private class PropertyTableModel extends AbstractTableModel implements
			Observer {

		private static final int COLUMN_COUNT = 6;

		public PropertyTableModel() {
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			return metaConcept.getMetaProperties().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			MetaProperty property = metaConcept.getMetaProperties()
					.getMetaProperty(r);
			if (c == 0) {
				result = property.getCode();
			} else if (c == 1) {
				result = property.getId();
			} else if (c == 2) {
				result = property.getIncrement();
			} else if (c == 3) {
				result = property.getValue();
			} else if (c == 4) {
				result = property.getInit();
			} else if (c == 5) {
				MetaType metaType = property.getMetaType();
				if (metaType != null) {
					result = metaType.getCode();
				} else {
					result = null;
				}
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getOne().getText("name");
			} else if (c == 1) {
				return Para.getOne().getText("id");
			} else if (c == 2) {
				return Para.getOne().getText("increment");
			} else if (c == 3) {
				return Para.getOne().getText("value");
			} else if (c == 4) {
				return Para.getOne().getText("init");
			} else if (c == 5) {
				return Para.getOne().getText("type");
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			if (c == 5) {
				return false;
			}
			return true;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			if (getValueAt(0, c) != null) {
				return getValueAt(0, c).getClass();
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaProperty property = metaConcept.getMetaProperties()
					.getMetaProperty(r);
			MetaProperty propertyCopy = property.copy();
			if (c == 0) {
				propertyCopy.setCode((String) value);
			} else if (c == 1) {
				propertyCopy.setId((Boolean) value);
			} else if (c == 2) {
				propertyCopy.setIncrement((Boolean) value);
			} else if (c == 3) {
				propertyCopy.setValue((Boolean) value);
			} else if (c == 4) {
				propertyCopy.setInit((String) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					metaConcept.getMetaProperties(), property, propertyCopy);
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
							if (entities instanceof MetaProperties) {
								MetaProperties metaProperties = (MetaProperties) entities;
								if (metaConcept.equals(metaProperties
										.getMetaConcept())) {
									if (entitiesAction instanceof AddAction
											|| entitiesAction instanceof RemoveAction) {
										int ix = metaConcept
												.getMetaProperties().size() - 1;
										setSelectedRow(ix);
										fireTableDataChanged();
									} else if (entitiesAction instanceof UpdateAction) {
										fireTableDataChanged();
									}
								}
							}
						}
					} else if (arg instanceof EntitiesAction) {
						EntitiesAction entitiesAction = (EntitiesAction) arg;
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaProperties) {
							MetaProperties metaProperties = (MetaProperties) entities;
							if (metaConcept.equals(metaProperties
									.getMetaConcept())) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = metaConcept.getMetaProperties()
											.size() - 1;
									setSelectedRow(ix);
									fireTableDataChanged();
								} else if (entitiesAction instanceof UpdateAction) {
									fireTableDataChanged();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log
						.error("Error in PropertiesFrame.PropertyTableModel.update: "
								+ e.getMessage());
			}
		}
	}

}