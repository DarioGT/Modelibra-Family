package modelibra.swing.designer.metaneighbor;

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
import modelibra.designer.metaneighbor.MetaNeighbor;
import modelibra.designer.metaneighbor.MetaNeighbors;
import modelibra.swing.app.config.Para;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

/**
 * Neighbors window with a table of neighbors. You can remove the selected
 * neighbor and update the selected neighbor.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public class NeighborsFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(NeighborsFrame.class);

	public static final int NEIGHBORS_FRAME_WIDTH = 720;
	public static final int NEIGHBORS_FRAME_HEIGHT = 320;
	public static final Dimension NEIGHBORS_FRAME_SIZE = new Dimension(
			NEIGHBORS_FRAME_WIDTH, NEIGHBORS_FRAME_HEIGHT);

	private MetaNeighbor currentNeighbor;
	private JTable neighborTable;
	private int selectedNeighborRow = -1;
	private NeighborTableModel neighborTableModel;

	private JPanel buttonPanel = new JPanel();
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));

	private MetaConcept metaConcept;

	public NeighborsFrame(final MetaConcept metaConcept) {
		this.metaConcept = metaConcept;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(NEIGHBORS_FRAME_SIZE);
		setTitle(Para.getOne().getText("neighbors"));

		neighborTableModel = new NeighborTableModel();
		neighborTable = new JTable(neighborTableModel);
		neighborTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(neighborTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaNeighbors destinationNeighbors = metaConcept
						.getMetaDestinationNeighbors();
				if (currentNeighbor != null) {
					EntitiesAction action = new RemoveAction(designer
							.getSession(), destinationNeighbors,
							currentNeighbor);
					action.execute();
				}
			}
		});

	}

	public MetaNeighbor getCurrentNeighbor() {
		return currentNeighbor;
	}

	public void exit() {
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
			neighborTable.setRowSelectionInterval(ix, ix);
			neighborTable.scrollRectToVisible(neighborTable.getCellRect(ix, 0,
					true));
			selectedNeighborRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedNeighborRow;
	}

	private int getRowCount() {
		return neighborTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(neighborTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			currentNeighbor = metaConcept.getMetaDestinationNeighbors()
					.getMetaNeighbor(getSelectedRow());
		}
	}

	private class NeighborTableModel extends AbstractTableModel implements
			Observer {

		private static final int COLUMN_COUNT = 5;

		public NeighborTableModel() {
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			return metaConcept.getMetaDestinationNeighbors().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			MetaNeighbor destinationNeighbor = metaConcept
					.getMetaDestinationNeighbors().getMetaNeighbor(r);
			if (c == 0) {
				result = destinationNeighbor.getCode();
			} else if (c == 1) {
				result = destinationNeighbor.getId();
			} else if (c == 2) {
				result = destinationNeighbor.getMin();
			} else if (c == 3) {
				result = destinationNeighbor.getMax();
			} else if (c == 4) {
				result = destinationNeighbor.getInternal();
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
				return Para.getOne().getText("min");
			} else if (c == 3) {
				return Para.getOne().getText("max");
			} else if (c == 4) {
				return Para.getOne().getText("internal");
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
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaNeighbor destinationNeighbor = metaConcept
					.getMetaDestinationNeighbors().getMetaNeighbor(r);
			MetaNeighbor destinationNeighborCopy = destinationNeighbor.copy();
			if (c == 0) {
				destinationNeighborCopy.setCode((String) value);
			} else if (c == 1) {
				destinationNeighborCopy.setId((Boolean) value);
			} else if (c == 2) {
				destinationNeighborCopy.setMin((Integer) value);
			} else if (c == 3) {
				destinationNeighborCopy.setMax((String) value);
			} else if (c == 4) {
				destinationNeighborCopy.setInternal((Boolean) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					metaConcept.getMetaDestinationNeighbors(),
					destinationNeighbor, destinationNeighborCopy);
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
							if (entities instanceof MetaNeighbors) {
								MetaNeighbors metaDestinationNeighbors = (MetaNeighbors) entities;
								if (metaConcept.equals(metaDestinationNeighbors
										.getMetaSourceConcept())) {
									if (entitiesAction instanceof AddAction
											|| entitiesAction instanceof RemoveAction) {
										int ix = metaConcept
												.getMetaDestinationNeighbors()
												.size() - 1;
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
						if (entities instanceof MetaNeighbors) {
							MetaNeighbors metaDestinationNeighbors = (MetaNeighbors) entities;
							if (metaConcept.equals(metaDestinationNeighbors
									.getMetaSourceConcept())) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = metaConcept
											.getMetaDestinationNeighbors()
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
				log.error("Error in NeighborsFrame.NeighborTableModel.update: "
						+ e.getMessage());
			}
		}
	}

}