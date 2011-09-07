package modelibra.swing.designer.metaconcept;

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
import modelibra.designer.metaconcept.MetaConcepts;
import modelibra.designer.metamodel.MetaModel;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metamodel.DiagramFrame;
import modelibra.swing.designer.metaneighbor.NeighborsFrame;
import modelibra.swing.designer.metaproperty.PropertiesFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

/**
 * Concepts window with a table of concepts. You can add a new concept, remove
 * the selected concept, and update the selected concept.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public class ConceptsFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ConceptsFrame.class);

	public static final int CONCEPTS_FRAME_WIDTH = 400;
	public static final int CONCEPT_FRAME_HEIGHT = 320;
	public static final Dimension CONCEPT_FRAME_SIZE = new Dimension(
			CONCEPTS_FRAME_WIDTH, CONCEPT_FRAME_HEIGHT);

	private MetaConcept currentConcept;
	private JTable conceptTable;
	private int selectedConceptRow = -1;
	private ConceptTableModel conceptTableModel;

	private JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton(Para.getOne().getText("add"));
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));
	private JButton propertiesButton = new JButton(Para.getOne().getText(
			"properties"));
	private JButton neighborsButton = new JButton(Para.getOne().getText(
			"neighbors"));

	private DiagramFrame diagramFrame;
	private PropertiesFrame propertiesFrame;
	private NeighborsFrame neighborsFrame;

	private MetaModel metaModel;

	public ConceptsFrame(final DiagramFrame diagramFrame) {
		this.diagramFrame = diagramFrame;
		metaModel = diagramFrame.getMetaModel();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(CONCEPT_FRAME_SIZE);
		setTitle(Para.getOne().getText("concepts"));

		conceptTableModel = new ConceptTableModel();
		conceptTable = new JTable(conceptTableModel);
		conceptTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(conceptTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramPanel().addMetaConceptGraphic();

			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramPanel().removeMetaConceptGraphic(
						currentConcept.getMetaConceptGraphics().first());
			}
		});

		buttonPanel.add(propertiesButton);
		propertiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentConcept != null) {
					propertiesFrame = new PropertiesFrame(currentConcept);
					int x = (int) ConceptsFrame.this.getLocation().getX();
					int y = (int) ConceptsFrame.this.getLocation().getY();
					propertiesFrame.setLocation(x, y + CONCEPT_FRAME_HEIGHT);
					propertiesFrame.setVisible(true);
				}
			}
		});

		buttonPanel.add(neighborsButton);
		neighborsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentConcept != null) {
					neighborsFrame = new NeighborsFrame(currentConcept);
					int x = (int) ConceptsFrame.this.getLocation().getX();
					int y = (int) ConceptsFrame.this.getLocation().getY();
					neighborsFrame.setLocation(x, y + CONCEPT_FRAME_HEIGHT);
					neighborsFrame.setVisible(true);
				}
			}
		});
	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	public void exit() {
		if (propertiesFrame != null) {
			propertiesFrame.exit();
		}
		if (neighborsFrame != null) {
			neighborsFrame.exit();
		}
		dispose();
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			conceptTable.setRowSelectionInterval(ix, ix);
			conceptTable.scrollRectToVisible(conceptTable.getCellRect(ix, 0,
					true));
			selectedConceptRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedConceptRow;
	}

	private int getRowCount() {
		return conceptTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(conceptTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			currentConcept = metaModel.getMetaConcepts().getMetaConcept(
					getSelectedRow());
		}
	}

	private class ConceptTableModel extends AbstractTableModel implements
			Observer {

		private static final int COLUMN_COUNT = 2;

		public ConceptTableModel() {
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			return metaModel.getMetaConcepts().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			MetaConcept concept = metaModel.getMetaConcepts().getMetaConcept(r);
			if (c == 0) {
				result = concept.getCode();
			} else if (c == 1) {
				result = concept.getEntry();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getOne().getText("name");
			} else if (c == 1) {
				return Para.getOne().getText("entry");
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
			MetaConcept concept = metaModel.getMetaConcepts().getMetaConcept(r);
			MetaConcept conceptCopy = concept.copy();
			if (c == 0) {
				conceptCopy.setCode((String) value);
			} else if (c == 1) {
				conceptCopy.setEntry((Boolean) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					metaModel.getMetaConcepts(), concept, conceptCopy);
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
							if (entities instanceof MetaConcepts) {
								MetaConcepts metaConcepts = (MetaConcepts) entities;
								if (metaModel.equals(metaConcepts
										.getMetaModel())) {
									if (entitiesAction instanceof AddAction
											|| entitiesAction instanceof RemoveAction) {
										int ix = metaModel.getMetaConcepts()
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
						if (entities instanceof MetaConcepts) {
							MetaConcepts metaConcepts = (MetaConcepts) entities;
							if (metaModel.equals(metaConcepts.getMetaModel())) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = metaModel.getMetaConcepts().size() - 1;
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
				log.error("Error in ConceptsFrame.ConceptTableModel.update: "
						+ e.getMessage());
			}
		}

	}

}