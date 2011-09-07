package modelibra.swing.designer.metaconcept;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconcept.MetaConcepts;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphic;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphics;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaproperty.MetaProperties;
import modelibra.designer.metaproperty.MetaProperty;
import modelibra.swing.designer.metamodel.DiagramPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

public class EntityPanel extends JPanel implements ActionListener,
		ListSelectionListener, Observer {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(EntityPanel.class);

	public static final Color ENTRY_COLOR = new Color(180, 66, 0);
	public static final Color NON_ENTRY_COLOR = new Color(0, 153, 0);
	public static final Color DEFAULT_COLOR = NON_ENTRY_COLOR;

	public static final Color SELECT_COLOR = Color.BLACK;
	private static final Color TITLE_BACKGROUND_COLOR = Color.WHITE;
	// public static final String DEFAULT_TITLE =
	// Para.getOne().getText("concept");
	public static final String DEFAULT_TITLE = "";
	public static final String DEFAULT_TITLE_APPENDIX = "?";
	public static final int ENTITY_WIDTH = 140;
	public static final int ENTITY_HEIGHT = 180;
	public static final Dimension ENTITY_SIZE = new Dimension(ENTITY_WIDTH,
			ENTITY_HEIGHT);
	public static final int BORDER = 1; // width in pixels
	public static final int LEFT_COLUMN = 20; // width in pixels
	public static final String ID = "id";
	public static final String REQUIRED = "\u03D5";
	public static final String OPTIONAL = "o";

	private Color currentColor = DEFAULT_COLOR;
	private boolean selected = false;

	private JTextField titleField = new JTextField();

	private MetaProperty currentProperty;
	private JTable propertyTable;
	private int selectedPropertyRow = -1;
	private PropertyTableModel propertyTableModel;

	private JTextField propertyField = new JTextField();
	private DiagramPanel diagramPanel;

	private MetaConceptGraphic metaConceptGraphic;

	public EntityPanel(final DiagramPanel diagramPanel,
			final MetaConceptGraphic metaConceptGraphic) {
		try {
			this.diagramPanel = diagramPanel;
			this.metaConceptGraphic = metaConceptGraphic;

			setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,
					BORDER));
			setLayout(new BorderLayout());

			titleField.addFocusListener(new TitleHandler());
			titleField.setBackground(TITLE_BACKGROUND_COLOR);
			titleField.addMouseListener(new TitleMouseAdapter(diagramPanel));
			titleField.addMouseMotionListener(new TitleMouseMotionAdapter(
					diagramPanel));
			add(titleField, BorderLayout.NORTH);

			propertyTableModel = new PropertyTableModel(getMetaConcept());
			propertyTable = new JTable(propertyTableModel);
			propertyTable.getColumnModel().getColumn(0)
					.setMinWidth(LEFT_COLUMN);
			propertyTable.getColumnModel().getColumn(0)
					.setMaxWidth(LEFT_COLUMN);
			propertyTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getX() < LEFT_COLUMN)) {
						int r = propertyTable.getSelectionModel()
								.getMaxSelectionIndex();
						MetaConcept metaConcept = getMetaConcept();
						MetaProperty property = metaConcept.getMetaProperties()
								.getMetaProperty(r);
						MetaProperty propertyCopy = property.copy();
						propertyCopy.setId(!propertyCopy.getId());
						Designer designer = ModelibraData.getOne()
								.getDesigner();

						EntitiesAction action = new UpdateAction(designer
								.getSession(), metaConcept.getMetaProperties(),
								property, propertyCopy);
						action.execute();
					}
				}
			});
			propertyTable.getSelectionModel().addListSelectionListener(this);
			setSelectedRow(0);
			JScrollPane jScrollPane = new JScrollPane(propertyTable);
			add(jScrollPane, BorderLayout.CENTER);

			propertyField.addActionListener(this);
			add(propertyField, BorderLayout.SOUTH);

			setLocation(metaConceptGraphic.getLocation());
			setSize(metaConceptGraphic.getSize());
			setTitle(metaConceptGraphic.getTitle());

			if (getMetaConcept().isEntry()) {
				setEntry(true);
			} else {
				setEntry(false);
			}

			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);

			doLayout(); // no title showgetMetaConcept()n without doLayout
		} catch (Exception e) {
			log.error("Error in EntityPanel.constructor: " + e.getMessage());
		}
	}

	public DiagramPanel getDiagramPanel() {
		return diagramPanel;
	}

	public MetaModel getMetaModel() {
		return getDiagramPanel().getMetaModel();
	}

	public MetaConcept getMetaConcept() {
		return getMetaConceptGraphic().getMetaConcept();
	}

	public void setMetaConceptGraphic(MetaConceptGraphic metaConceptGraphic) {
		this.metaConceptGraphic = metaConceptGraphic;
	}

	public MetaConceptGraphic getMetaConceptGraphic() {
		return metaConceptGraphic;
	}

	public void setTitle(String title) {
		titleField.setText(title);
	}

	public String getTitle() {
		return titleField.getText();
	}

	public void selectTitle() {
		titleField.selectAll();
	}

	public Point getCenter() {
		int cx = getLocation().x + (getSize().width / 2);
		int cy = getLocation().y + getSize().height / 2;
		return new Point(cx, cy);
	}

	public void setCurrentColor(Color color) {
		if (color != SELECT_COLOR) {
			currentColor = color;
			setBackground(color);
		}
		repaint();
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	private void setEntry(boolean entry) {
		if (entry) {
			setCurrentColor(ENTRY_COLOR);
		} else {
			setCurrentColor(DEFAULT_COLOR);
		}
	}

	private boolean isEntry() {
		if (getCurrentColor().equals(ENTRY_COLOR)) {
			return true;
		}
		return false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected) {
			setBorder(BorderFactory.createLineBorder(SELECT_COLOR, BORDER));
		} else {
			setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,
					BORDER));
			if (getMetaConcept().isEntry()) {
				setEntry(true);
			} else {
				setEntry(false);
			}
		}
	}

	private void setLocationAndSize(MetaConceptGraphic metaConceptGraphic) {
		setLocation(metaConceptGraphic.getLocation());
		setSize(metaConceptGraphic.getSize());
		validate();
	}

	private void setLocationAndSize(Point location, Dimension size) {
		MetaConceptGraphic metaConceptGraphic = getMetaConceptGraphic();
		MetaConceptGraphics metaConceptGraphics = metaConceptGraphic
				.getMetaConcept().getMetaConceptGraphics();
		MetaConceptGraphic metaConceptGraphicCopy = metaConceptGraphic.copy();
		metaConceptGraphicCopy.setLocation(location);
		metaConceptGraphicCopy.setSize(size);
		Designer designer = ModelibraData.getOne().getDesigner();
		EntitiesAction action = new UpdateAction(designer.getSession(),
				metaConceptGraphics, metaConceptGraphic, metaConceptGraphicCopy);
		action.execute();
	}

	public void memorizeLocationAndSize() {
		MetaConceptGraphic metaConceptGraphic = getMetaConceptGraphic();
		MetaConceptGraphics metaConceptGraphics = metaConceptGraphic
				.getMetaConcept().getMetaConceptGraphics();
		MetaConceptGraphic metaConceptGraphicCopy = metaConceptGraphic.copy();
		metaConceptGraphicCopy.setLocation(getLocation());
		metaConceptGraphicCopy.setSize(getSize());
		Designer designer = ModelibraData.getOne().getDesigner();
		EntitiesAction action = new UpdateAction(designer.getSession(),
				metaConceptGraphics, metaConceptGraphic, metaConceptGraphicCopy);
		action.execute();
	}

	private void unzoom(float oldZoomFactor) {
		Rectangle r = getBounds();
		setBounds((int) (r.x / oldZoomFactor), (int) (r.y / oldZoomFactor),
				(int) (r.width / oldZoomFactor),
				(int) (r.height / oldZoomFactor));
	}

	public void zoom(float oldZoomFactor, float newZoomFactor) {
		unzoom(oldZoomFactor);
		Rectangle r = getBounds();
		setBounds((int) (r.x * newZoomFactor), (int) (r.y * newZoomFactor),
				(int) (r.width * newZoomFactor),
				(int) (r.height * newZoomFactor));
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
			currentProperty = getMetaConcept().getMetaProperties()
					.getMetaProperty(getSelectedRow());
		}
	}

	// implemented from ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == propertyField) {
			String propertyName = propertyField.getText();
			if (!propertyName.equals("")) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaConcept metaConcept = getMetaConcept();
				MetaProperties metaProperties = metaConcept.getMetaProperties();
				MetaProperty metaProperty = new MetaProperty(metaConcept);
				metaProperty.setCode(propertyName);
				EntitiesAction action = new AddAction(designer.getSession(),
						metaProperties, metaProperty);
				action.execute();
				if (action.isExecuted()) {
					propertyField.setText("");
				}
			}
		}
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		try {
			Designer designer = ModelibraData.getOne().getDesigner();
			if (o == designer && designer != null) {
				if (arg instanceof Transaction) {
					Transaction transaction = (Transaction) arg;
					List<EntitiesAction> entitiesActions = transaction
							.getEntitiesActions();
					for (EntitiesAction entitiesAction : entitiesActions) {
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaConceptGraphics) {
							if (entitiesAction instanceof UpdateAction) {
								UpdateAction updateAction = (UpdateAction) entitiesAction;
								if (transaction.isExecuted()) {
									MetaConceptGraphic metaConceptGraphicAfterUpdate = (MetaConceptGraphic) updateAction
											.getUpdateEntity();
									if (metaConceptGraphicAfterUpdate
											.equals(metaConceptGraphic)) {
										setLocationAndSize(metaConceptGraphic);
									}
								} else if (transaction.isUndone()) {
									MetaConceptGraphic metaConceptGraphicBeforeUpdate = (MetaConceptGraphic) updateAction
											.getEntity();
									if (metaConceptGraphicBeforeUpdate
											.equals(metaConceptGraphic)) {
										setLocationAndSize(metaConceptGraphic);
									}
								}
							}
						}
					}
				} else if (arg instanceof EntitiesAction) {
					EntitiesAction entitiesAction = (EntitiesAction) arg;
					IEntities<?> entities = entitiesAction.getEntities();
					if (entities instanceof MetaConceptGraphics) {
						if (entitiesAction instanceof UpdateAction) {
							UpdateAction updateAction = (UpdateAction) entitiesAction;
							if (updateAction.isExecuted()) {
								MetaConceptGraphic metaConceptGraphicAfterUpdate = (MetaConceptGraphic) updateAction
										.getUpdateEntity();
								if (metaConceptGraphicAfterUpdate
										.equals(metaConceptGraphic)) {
									setLocationAndSize(metaConceptGraphic);
								}
							} else if (updateAction.isUndone()) {
								MetaConceptGraphic metaConceptGraphicBeforeUpdate = (MetaConceptGraphic) updateAction
										.getEntity();
								if (metaConceptGraphicBeforeUpdate
										.equals(metaConceptGraphic)) {
									setLocationAndSize(metaConceptGraphic);
								}
							}
						}
					} else if (entities instanceof MetaConcepts) {
						if (entitiesAction instanceof UpdateAction) {
							UpdateAction updateAction = (UpdateAction) entitiesAction;
							if (updateAction.isExecuted()) {
								MetaConcept metaConcept = (MetaConcept) updateAction
										.getEntity();
								if (metaConcept.equals(metaConceptGraphic
										.getMetaConcept())) {
									if (!getTitle().equals(
											metaConcept.getCode())) {
										setTitle(metaConcept.getCode());
									}
								}
							} else if (updateAction.isUndone()) {
								MetaConcept metaConceptBeforeUpdate = (MetaConcept) updateAction
										.getEntity();
								if (metaConceptBeforeUpdate
										.equals(metaConceptGraphic
												.getMetaConcept())) {
									if (!getTitle().equals(
											metaConceptBeforeUpdate.getCode())) {
										setTitle(metaConceptBeforeUpdate
												.getCode());
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Error in EntityPanel.update: " + e.getMessage());
		}
	}

	private class TitleMouseAdapter extends MouseAdapter {
		DiagramPanel adaptee;

		TitleMouseAdapter(DiagramPanel adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			e.setSource(EntityPanel.this);
			adaptee.mousePressed(e);

			if (e.isPopupTrigger()) {
				EntityPopupMenu popupMenu = EntityPopupMenu
						.getOne(EntityPanel.this);
				popupMenu.show(EntityPanel.this, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			e.setSource(EntityPanel.this);
			adaptee.mouseReleased(e);
		}
	}

	private class TitleMouseMotionAdapter extends MouseMotionAdapter {
		DiagramPanel adaptee;

		TitleMouseMotionAdapter(DiagramPanel adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			e.setSource(EntityPanel.this);
			adaptee.mouseDragged(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			e.setSource(EntityPanel.this);
			adaptee.mouseMoved(e);
		}

	}

	private class TitleHandler implements FocusListener {

		// implemented from FocusListener
		public void focusGained(FocusEvent e) {
			// selectTitle(); // selected but you cannot add a text to the
			// current one
		}

		// implemented from FocusListener
		public void focusLost(FocusEvent e) {
			String title = getTitle();
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaConcepts metaConcepts = getMetaModel().getMetaConcepts();
			MetaConcept metaConcept = getMetaConcept();
			if (!title.equals(metaConcept.getCode())) {
				MetaConcept metaConceptCopy = metaConcept.copy();
				metaConceptCopy.setCode(title);
				EntitiesAction action = new UpdateAction(designer.getSession(),
						metaConcepts, metaConcept, metaConceptCopy);
				action.execute();
				if (action.isExecuted()) {
					selectTitle();
				}
			}
		}
	}

	private class PropertyTableModel extends AbstractTableModel implements
			Observer {

		private static final int COLUMN_COUNT = 2;

		private MetaConcept metaConcept;

		public PropertyTableModel(MetaConcept metaConcept) {
			this.metaConcept = metaConcept;
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
				if (property.isId()) {
					result = EntityPanel.ID;
				} else if (property.isValue()) {
					result = EntityPanel.REQUIRED;
				} else if (!property.isValue()) {
					result = EntityPanel.OPTIONAL;
				} else {
					result = "";
				}
			} else if (c == 1) {
				result = property.getCode();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			return null;
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			if (c == 0) {
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
			if (c == 1) {
				propertyCopy.setCode((String) value);
				EntitiesAction action = new UpdateAction(designer.getSession(),
						metaConcept.getMetaProperties(), property, propertyCopy);
				action.execute();
			}
		}

		// implemented from Observer
		public void update(Observable o, Object arg) {
			try {
				Designer designer = ModelibraData.getOne().getDesigner();
				if (o == designer) {
					if (arg instanceof EntitiesAction) {
						EntitiesAction entitiesAction = (EntitiesAction) arg;
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaProperties) {
							MetaProperties properties = (MetaProperties) entities;
							if (metaConcept.equals(properties.getMetaConcept())) {
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
						} else if (entities instanceof MetaConcepts) {
							if (entitiesAction instanceof UpdateAction) {
								UpdateAction updateAction = (UpdateAction) entitiesAction;
								MetaConcept concept = (MetaConcept) updateAction
										.getEntity();
								if (metaConcept.equals(concept)) {
									if (EntityPanel.this.isEntry()) {
										if (!metaConcept.isEntry()) {
											EntityPanel.this.setEntry(false);
										}
									} else if (metaConcept.isEntry()) {
										EntityPanel.this.setEntry(true);
									}
								}
							}
						} else if (entities instanceof MetaConceptGraphics) {
							EntityPanel.this.getDiagramPanel().repaint();
						}
					}
				}
			} catch (Exception e) {
				log.error("Error in EntityPanel.PropertyTableModel.update: "
						+ e.getMessage());
			}
		}

	}

}
