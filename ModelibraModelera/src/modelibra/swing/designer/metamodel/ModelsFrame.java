package modelibra.swing.designer.metamodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
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
import modelibra.designer.metadomain.MetaDomain;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metamodel.MetaModels;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metadomain.DomainsFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;
import org.modelibra.config.ConceptConfig;
import org.modelibra.swing.domain.model.concept.ConceptTableFrame;

/**
 * Models window with a table of models. You can add a new model, remove the
 * selected model, and update the selected model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-26
 */
public class ModelsFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;

	public static final double FRAME_DISPLAY_INCREMENT = 64;

	private static Log log = LogFactory.getLog(ModelsFrame.class);

	public static final int MODELS_FRAME_WIDTH = DomainsFrame.DOMAINS_FRAME_WIDTH;
	public static final int MODELS_FRAME_HEIGHT = DomainsFrame.DOMAINS_FRAME_HEIGHT;
	public static final Dimension MODELS_FRAME_SIZE = new Dimension(
			MODELS_FRAME_WIDTH, MODELS_FRAME_HEIGHT);

	private MetaModel currentModel;
	private JTable modelTable;
	private int selectedModelRow = -1;
	private ModelTableModel modelTableModel;

	private JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton(Para.getOne().getText("add"));
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));
	private JButton diagramButton = new JButton(Para.getOne()
			.getText("diagram"));
	private JButton conceptsButton = new JButton(Para.getOne().getText(
			"concepts"));

	private DomainsFrame domainsFrame;
	private DiagramFrame diagramFrame;
	private ConceptTableFrame conceptTableFrame;

	private MetaDomain metaDomain;

	public ModelsFrame(final DomainsFrame domainsFrame,
			final MetaDomain metaDomain) {
		super();
		this.domainsFrame = domainsFrame;
		this.metaDomain = metaDomain;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(MODELS_FRAME_SIZE);
		setTitle(Para.getOne().getText("models"));

		modelTableModel = new ModelTableModel();
		modelTable = new JTable(modelTableModel);
		modelTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(modelTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaModels models = metaDomain.getMetaModels();
				MetaModel model = new MetaModel(metaDomain);
				model.setCode(Para.getOne().getText("model"));
				EntitiesAction action = new AddAction(designer.getSession(),
						models, model);
				action.execute();
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaModels models = metaDomain.getMetaModels();
				if (currentModel != null) {
					EntitiesAction action = new RemoveAction(designer
							.getSession(), models, currentModel);
					action.execute();
				}
			}
		});

		buttonPanel.add(diagramButton);
		diagramButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentModel != null) {
					diagramFrame = new DiagramFrame(ModelsFrame.this,
							currentModel);
					diagramFrame.setLocation(0, MODELS_FRAME_HEIGHT + 20);
					diagramFrame.setVisible(true);
				}
			}
		});

		buttonPanel.add(conceptsButton);
		conceptsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentModel != null) {
					List<ConceptConfig> entryConceptConfigList = currentModel
							.getModel().getModelConfig().getConceptsConfig()
							.getEntryConceptConfigList();
					conceptTableFrame = new ConceptTableFrame(getDomainsFrame()
							.getApp(), entryConceptConfigList);
					conceptTableFrame.setVisible(true);
					displayDownRight(conceptTableFrame);
				}
			}
		});
	}

	public DomainsFrame getDomainsFrame() {
		return domainsFrame;
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			modelTable.setRowSelectionInterval(ix, ix);
			modelTable.scrollRectToVisible(modelTable.getCellRect(ix, 0, true));
			selectedModelRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedModelRow;
	}

	private int getRowCount() {
		return modelTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(modelTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			MetaModel model = metaDomain.getMetaModels().getMetaModel(
					getSelectedRow());
			if (model != currentModel) {
				currentModel = model;
			}
		}
	}

	private class ModelTableModel extends AbstractTableModel implements
			Observer {

		private static final long serialVersionUID = 1L;

		private static final int COLUMN_COUNT = 3;

		public ModelTableModel() {
			super();
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			return metaDomain.getMetaModels().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaModel metaModel = metaDomain.getMetaModels().getMetaModel(r);
			if (c == 0) {
				result = metaModel.getCode();
			} else if (c == 1) {
				result = metaModel.getAuthor();
			} else if (c == 2) {
				result = metaModel.getDescription();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getOne().getText("name");
			} else if (c == 1) {
				return Para.getOne().getText("author");
			} else if (c == 2) {
				return Para.getOne().getText("description");
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
			MetaModel model = metaDomain.getMetaModels().getMetaModel(r);
			MetaModel modelCopy = model.copy();
			if (c == 0) {
				modelCopy.setCode((String) value);
			} else if (c == 1) {
				modelCopy.setAuthor((String) value);
			} else if (c == 2) {
				modelCopy.setDescription((String) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					metaDomain.getMetaModels(), model, modelCopy);
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
							if (entities instanceof MetaModels) {
								MetaModels metaModels = (MetaModels) entities;
								if (metaDomain.equals(metaModels
										.getMetaDomain())) {
									if (entitiesAction instanceof AddAction
											|| entitiesAction instanceof RemoveAction) {
										int ix = metaDomain.getMetaModels()
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
						if (entities instanceof MetaModels) {
							MetaModels metaModels = (MetaModels) entities;
							if (metaDomain.equals(metaModels.getMetaDomain())) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = metaDomain.getMetaModels().size() - 1;
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
				log.error("Error in ModelsFrame.ModelTableModel.update: "
						+ e.getMessage());
			}
		}

	}

	public void exit() {
		if (diagramFrame != null) {
			diagramFrame.exit();
		}
		if (conceptTableFrame != null) {
			conceptTableFrame.exit();
		}
		dispose();
	}

	/**
	 * Displays a child frame down and right of this frame.
	 * 
	 * @param child
	 *            frame
	 */
	public void displayDownRight(JFrame child) {
		Point locationOnScreen = getLocationOnScreen();
		double parentX = locationOnScreen.getX();
		double parentY = locationOnScreen.getY();

		double childX = parentX + FRAME_DISPLAY_INCREMENT;
		double childY = parentY + FRAME_DISPLAY_INCREMENT;

		Point childLocation = new Point();
		childLocation.setLocation(childX, childY);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

}