package modelibra.swing.designer.metamodel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconcept.MetaConcepts;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphic;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphics;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaneighbor.MetaNeighbor;
import modelibra.designer.metaneighbor.MetaNeighbors;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metaconcept.EntityPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.ModelSession;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

public class DiagramPanel extends JPanel implements MouseListener,
		MouseMotionListener, Observer {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(DiagramPanel.class);

	public static final Color INTERNAL_LINE_COLOR = EntityPanel.NON_ENTRY_COLOR;
	public static final Color EXTERNAL_LINE_COLOR = Color.GRAY;

	public static final Color DIAGRAM_COLOR = Color.WHITE;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;
	public static final Point MIN_POINT = new Point(MIN_X, MIN_Y);
	public static final int DIAGRAM_WIDTH = 4800;
	public static final int DIAGRAM_HEIGHT = 7200;
	public static final Dimension DIAGRAM_SIZE = new Dimension(DIAGRAM_WIDTH,
			DIAGRAM_HEIGHT);
	public static final int DIAGRAM_COLUMN_LIMIT = 9;
	// public static final int LINE_SELECTION = 2; // width in pixels
	public static final float MINIMUM_ZOOM_FACTOR = 0.25f;
	public static final float MAXIMUM_ZOOM_FACTOR = 2f;

	private static float zoomFactor = 1f;
	private static float zoomStep = 0.25f;

	private EntityPanel rshipFirstClickedEntity;

	private String entityTitlePrefix = Para.getOne().getText("concept");

	private Point mousePressed = MIN_POINT;

	private DiagramToolBar diagramToolBar;

	private MetaModel metaModel;

	public DiagramPanel(final DiagramToolBar diagramToolBar,
			final MetaModel metaModel) {
		try {
			this.diagramToolBar = diagramToolBar;
			this.metaModel = metaModel;
			addMouseListener(this);
			setBackground(DIAGRAM_COLOR);
			setLayout(null);
			setDesigner();
		} catch (Exception e) {
			log.error("Error in DiagramPanel.constructor: " + e.getMessage());
		}
	}

	public MetaModel getMetaModel() {
		return metaModel;
	}

	public DiagramFrame getDiagramFrame() {
		return diagramToolBar.getDiagramFrame();
	}

	@Override
	public Dimension getPreferredSize() {
		return DIAGRAM_SIZE;
	}

	private void setDesigner() {
		Designer designer = ModelibraData.getOne().getDesigner();
		displayEntityPanels();
		designer.addObserver(this);
	}

	void displayEntityPanels() {
		MetaConcepts metaConcepts = metaModel.getMetaConcepts();
		for (MetaConcept metaConcept : metaConcepts) {
			MetaConceptGraphics metaConceptGraphics = metaConcept
					.getMetaConceptGraphics();
			if (metaConceptGraphics.isEmpty()) {
				addMetaConceptGraphic(metaConcept.getCode());
			} else {
				for (MetaConceptGraphic metaConceptGraphic : metaConceptGraphics) {
					addEntityPanel(metaConceptGraphic);
				}
			}
		}
	}

	private void addEntityPanel(MetaConceptGraphic metaConceptGraphic) {
		EntityPanel entityPanel = new EntityPanel(this, metaConceptGraphic);
		entityPanel.selectTitle();
		add(entityPanel);
		repaint();
	}

	void empty() {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				remove(entityPanel);
			}
		}
		repaint();
	}

	private void removeEntityPanel(MetaConceptGraphic metaConceptGraphic) {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.getMetaConceptGraphic().equals(
						metaConceptGraphic)) {
					remove(entityPanel);
					repaint();
					break;
				}
			}
		}
	}

	EntityPanel findEntityPanel(String title) {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.getTitle().equals(title)) {
					return entityPanel;
				}
			}
		}
		return null;
	}

	void selectEntityPanels() {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				entityPanel.setSelected(true);
			}
		}
	}

	void deselectEntityPanels() {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				entityPanel.setSelected(false);
			}
		}
	}

	void hideSelectedEntityPanels() {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.isSelected()) {
					entityPanel.setVisible(false);
				}
			}
		}
	}

	void showSelectedEntityPanels() {
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.isSelected()) {
					entityPanel.setVisible(true);
				}
			}
		}
	}

	void colorEntityPanels() {
		Color selectedColor = JColorChooser.showDialog(this, Para.getOne()
				.getText("colorChoice"), EntityPanel.DEFAULT_COLOR);
		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				entityPanel.setCurrentColor(selectedColor);
			}
		}
	}

	static float getZoomFactor() {
		return zoomFactor;
	}

	static void setZoomFactor(float newZoomFactor) {
		if (newZoomFactor < MINIMUM_ZOOM_FACTOR) {
			newZoomFactor = MINIMUM_ZOOM_FACTOR;
		}
		if (newZoomFactor > MAXIMUM_ZOOM_FACTOR) {
			newZoomFactor = MAXIMUM_ZOOM_FACTOR;
		}
		zoomFactor = newZoomFactor;
	}

	static float getZoomStep() {
		return zoomStep;
	}

	static void setZoomStep(float newZoomStep) {
		zoomStep = newZoomStep;
	}

	void zoomIn(boolean zoomIn) {
		Component[] components = this.getComponents();
		EntityPanel entityPanel;
		float oldZf = getZoomFactor();
		if (zoomIn) {
			setZoomFactor(getZoomFactor() + getZoomStep());
		} else {
			setZoomFactor(getZoomFactor() - getZoomStep());
		}
		float newZf = getZoomFactor();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				entityPanel.zoom(oldZf, newZf);
			}
		}
		validate();
	}

	public void addMetaConceptGraphic() {
		addMetaConceptGraphic(EntityPanel.DEFAULT_TITLE, MIN_POINT,
				EntityPanel.ENTITY_SIZE);
	}

	private void addMetaConceptGraphic(Point point) {
		addMetaConceptGraphic(EntityPanel.DEFAULT_TITLE, point,
				EntityPanel.ENTITY_SIZE);
	}

	private void addMetaConceptGraphic(String title) {
		addMetaConceptGraphic(title, MIN_POINT, EntityPanel.ENTITY_SIZE);
	}

	private void addMetaConceptGraphic(String title, Point point,
			Dimension dimension) {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		MetaConcept metaConcept = new MetaConcept(metaModel);
		metaConcept.setCode(title);
		// metaConcept.setEntry(true); // default value in the specific
		// config
		MetaConcepts metaConcepts = metaModel.getMetaConcepts();
		new AddAction(transaction, metaConcepts, metaConcept);

		MetaConceptGraphic metaConceptGraphic = new MetaConceptGraphic(
				metaConcept);
		metaConceptGraphic.setLocation(point);
		metaConceptGraphic.setSize(dimension);
		MetaConceptGraphics metaConceptGraphics = metaConcept
				.getMetaConceptGraphics();
		new AddAction(transaction, metaConceptGraphics, metaConceptGraphic);

		transaction.execute();
		if (!transaction.isExecuted()) {
			String newTitle = metaConcept.getCode()
					+ EntityPanel.DEFAULT_TITLE_APPENDIX;
			addMetaConceptGraphic(newTitle, point, dimension);
		}
	}

	public void removeMetaConceptGraphic(MetaConceptGraphic metaConceptGraphic) {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		MetaConceptGraphics metaConceptGraphics = metaConceptGraphic
				.getMetaConcept().getMetaConceptGraphics();
		new RemoveAction(transaction, metaConceptGraphics, metaConceptGraphic);

		// What if there is another graphic for this concept?
		MetaConcepts metaConcepts = metaModel.getMetaConcepts();
		new RemoveAction(transaction, metaConcepts, metaConceptGraphic
				.getMetaConcept());

		transaction.execute();
	}

	void removeSelectedMetaConceptGraphics() {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.isSelected()) {
					MetaConceptGraphic metaConceptGraphic = entityPanel
							.getMetaConceptGraphic();
					MetaConcept metaConcept = metaConceptGraphic
							.getMetaConcept();
					MetaConceptGraphics metaConceptGraphics = metaConcept
							.getMetaConceptGraphics();
					new RemoveAction(transaction, metaConceptGraphics,
							metaConceptGraphic);

					// What if there is another graphic for this concept?
					MetaConcepts metaConcepts = metaModel.getMetaConcepts();
					new RemoveAction(transaction, metaConcepts, metaConcept);
				}
			}
		}

		transaction.execute();
	}

	void resizeSelectedMetaConceptGraphics(boolean height, boolean width,
			int increment, boolean increase) {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		Component[] components = getComponents();
		EntityPanel entityPanel;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				entityPanel = (EntityPanel) components[i];
				if (entityPanel.isSelected()) {
					MetaConceptGraphic metaConceptGraphic = entityPanel
							.getMetaConceptGraphic();
					MetaConcept metaConcept = metaConceptGraphic
							.getMetaConcept();
					MetaConceptGraphics metaConceptGraphics = metaConcept
							.getMetaConceptGraphics();
					MetaConceptGraphic metaConceptGraphicCopy = metaConceptGraphic
							.copy();
					if (increase) {
						if (height) {
							metaConceptGraphicCopy.increaseHeight(increment);
						}
						if (width) {
							metaConceptGraphicCopy.increaseWidth(increment);
						}
					} else {
						if (height) {
							metaConceptGraphicCopy.decreaseHeight(increment);
						}
						if (width) {
							metaConceptGraphicCopy.decreaseWidth(increment);
						}
					}
					new UpdateAction(transaction, metaConceptGraphics,
							metaConceptGraphic, metaConceptGraphicCopy);
				}
			}
		}

		transaction.execute();
	}

	void createMetaConceptGraphics(int count) {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		int x = 0;
		int y = 0;
		for (int i = 1; i <= count; i++) {
			String title = entityTitlePrefix + i;
			Point point = new Point(x, y);

			MetaConcept metaConcept = new MetaConcept(metaModel);
			metaConcept.setCode(title);
			MetaConcepts metaConcepts = metaModel.getMetaConcepts();
			new AddAction(transaction, metaConcepts, metaConcept);

			MetaConceptGraphic metaConceptGraphic = new MetaConceptGraphic(
					metaConcept);
			metaConceptGraphic.setLocation(point);
			metaConceptGraphic.setSize(EntityPanel.ENTITY_SIZE);
			MetaConceptGraphics metaConceptGraphics = metaConcept
					.getMetaConceptGraphics();
			new AddAction(transaction, metaConceptGraphics, metaConceptGraphic);

			x = x + EntityPanel.ENTITY_WIDTH;
			y = y + EntityPanel.ENTITY_HEIGHT / 2;
		}

		transaction.execute();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLines(g);
	}

	private void paintLines(Graphics g) {
		Component[] components = getComponents();
		EntityPanel sourceEntity;
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof EntityPanel) {
				sourceEntity = (EntityPanel) components[i];
				MetaConcept sourceConcept = sourceEntity.getMetaConcept();
				if (sourceConcept != null) {
					MetaNeighbors destinationNeighbors = sourceConcept
							.getMetaDestinationNeighbors();
					for (MetaNeighbor metaNeighbor : destinationNeighbors) {
						if (metaNeighbor.isChild()) {
							MetaConcept destinationConcept = metaNeighbor
									.getMetaDestinationConcept();
							EntityPanel destinationEntity = findEntityPanel(destinationConcept
									.getCode());
							if (metaNeighbor.isInternal()) {
								paintLine(g, sourceEntity, destinationEntity,
										INTERNAL_LINE_COLOR);
							} else {
								paintLine(g, sourceEntity, destinationEntity,
										EXTERNAL_LINE_COLOR);
							}
							// paintLine(g, metaNeighbor); // line does not move
							paintMinMax(g, metaNeighbor);
							paintName(g, metaNeighbor);
						} else if (metaNeighbor.isParent()) {
							paintMinMax(g, metaNeighbor);
							paintName(g, metaNeighbor);
						}
					}
				}
			}
		}
	}

	private void paintLine(Graphics g, EntityPanel sourceEntity,
			EntityPanel destinationEntity, Color color) {
		if (sourceEntity != null && destinationEntity != null) {
			paintLine(g, sourceEntity.getCenter(), destinationEntity
					.getCenter(), color);
		}
	}

	private void paintLine(Graphics g, MetaNeighbor metaNeighbor) {
		MetaConcept sourceConcept = metaNeighbor.getMetaSourceConcept();
		MetaConcept destinationConcept = metaNeighbor
				.getMetaDestinationConcept();
		MetaConceptGraphic sourceConceptGraphic = sourceConcept
				.getMetaConceptGraphic();
		MetaConceptGraphic destinationConceptGraphic = destinationConcept
				.getMetaConceptGraphic();
		if (sourceConceptGraphic != null && destinationConceptGraphic != null) {
			if (metaNeighbor.isInternal()) {
				paintLine(g, sourceConceptGraphic.getCenter(),
						destinationConceptGraphic.getCenter(),
						INTERNAL_LINE_COLOR);
			} else {
				paintLine(g, sourceConceptGraphic.getCenter(),
						destinationConceptGraphic.getCenter(),
						EXTERNAL_LINE_COLOR);
			}
		}
	}

	private void paintMinMax(Graphics g, MetaNeighbor metaNeighbor) {
		String min = metaNeighbor.getMinString();
		String max = metaNeighbor.getMax();
		String minMax = min + ".." + max;
		if (metaNeighbor.isInternal()) {
			g.setColor(INTERNAL_LINE_COLOR);
		} else {
			g.setColor(EXTERNAL_LINE_COLOR);
		}
		g.drawString(minMax, metaNeighbor.getMinMaxPoint().x, metaNeighbor
				.getMinMaxPoint().y);
	}

	private void paintName(Graphics g, MetaNeighbor metaNeighbor) {
		String name = metaNeighbor.getCode();
		if (metaNeighbor.isInternal()) {
			g.setColor(INTERNAL_LINE_COLOR);
		} else {
			g.setColor(EXTERNAL_LINE_COLOR);
		}
		if (metaNeighbor.isId()) {
			g.drawString("id " + name, metaNeighbor.getNamePoint().x,
					metaNeighbor.getNamePoint().y);
		} else {
			g.drawString(name, metaNeighbor.getNamePoint().x, metaNeighbor
					.getNamePoint().y);
		}
	}

	private void paintLine(Graphics g, Point point1, Point point2, Color color) {
		setLineColor(g, color);
		g.drawLine(point1.x, point1.y, point2.x, point2.y);
	}

	private void setLineColor(Graphics g, Color color) {
		g.setColor(color);
	}

	private void setSelectedLineColor(Graphics g) {
		g.setColor(EntityPanel.SELECT_COLOR);
	}

	// implemented from MouseListener
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this) {
			if (diagramToolBar.getSelectedTool() == DiagramToolBar.ENTITY) {
				addMetaConceptGraphic(e.getPoint());
				if (diagramToolBar.isSelectDefault()) {
					diagramToolBar.setSelectedTool(DiagramToolBar.SELECT);
				}
			} else if (diagramToolBar.getSelectedTool() == DiagramToolBar.SELECT) {
				deselectEntityPanels();
			} else if (diagramToolBar.getSelectedTool() == DiagramToolBar.RSHIP) {
				rshipFirstClickedEntity = null;
				if (diagramToolBar.isSelectDefault()) {
					diagramToolBar.setSelectedTool(DiagramToolBar.SELECT);
				}
			}
		} else if (e.getSource() instanceof EntityPanel) {
			EntityPanel clickedEntity = (EntityPanel) (e.getSource());
			if (diagramToolBar.getSelectedTool() == DiagramToolBar.SELECT) {
				clickedEntity.setSelected(!(clickedEntity.isSelected()));
				mousePressed = e.getPoint();
			} else if (diagramToolBar.getSelectedTool() == DiagramToolBar.RSHIP) {
				if (rshipFirstClickedEntity == null) {
					rshipFirstClickedEntity = clickedEntity;
				} else {
					Designer designer = ModelibraData.getOne().getDesigner();
					ModelSession session = designer.getSession();
					Transaction transaction = new Transaction(session);

					MetaConcept firstConcept = rshipFirstClickedEntity
							.getMetaConcept();
					MetaConcept secondConcept = clickedEntity.getMetaConcept();

					MetaNeighbors firstConceptDestinationNeighbors = firstConcept
							.getMetaDestinationNeighbors();
					MetaNeighbor firstSecondNeighbor = new MetaNeighbor(
							firstConcept, secondConcept);
					firstSecondNeighbor.setCode(secondConcept
							.getCodeInPluralWithLowerFirstLetter());
					firstSecondNeighbor.setMin(new Integer(0));
					firstSecondNeighbor.setMax("N");
					new AddAction(transaction,
							firstConceptDestinationNeighbors,
							firstSecondNeighbor);

					MetaNeighbors secondConceptSourceNeighbors = secondConcept
							.getMetaSourceNeighbors();
					MetaNeighbor secondFirstNeighbor = new MetaNeighbor(
							secondConcept, firstConcept);
					secondFirstNeighbor.setCode(firstConcept
							.getCodeWithLowerFirstLetter());
					secondFirstNeighbor.setMin(new Integer(1));
					secondFirstNeighbor.setMax("1");
					new AddAction(transaction, secondConceptSourceNeighbors,
							secondFirstNeighbor);

					transaction.execute();

					rshipFirstClickedEntity = null;
					if (diagramToolBar.isSelectDefault()) {
						diagramToolBar.setSelectedTool(DiagramToolBar.SELECT);
					}
				}
			}
		}
	}

	// implemented from MouseListener
	public void mouseClicked(MouseEvent e) {
	}

	// implemented from MouseListener
	public void mouseReleased(MouseEvent e) {
		if (diagramToolBar.getSelectedTool() == DiagramToolBar.SELECT) {
			if (e.getSource() instanceof EntityPanel) {
				EntityPanel entityPanel = (EntityPanel) e.getSource();
				entityPanel.memorizeLocationAndSize();
			}
		}
	}

	// implemented from MouseListener
	public void mouseEntered(MouseEvent e) {
	}

	// implemented from MouseListener
	public void mouseExited(MouseEvent e) {
	}

	// implemented from MouseMotionListener
	public void mouseDragged(MouseEvent e) {
		if (diagramToolBar.getSelectedTool() == DiagramToolBar.SELECT) {
			if (e.getSource() instanceof EntityPanel) {
				EntityPanel entityPanel = (EntityPanel) e.getSource();
				// entity location (x, y) and size (r) before the entity has
				// been dragged
				Rectangle r = entityPanel.getBounds();
				int x = r.x + e.getX() - mousePressed.x;
				int y = r.y + e.getY() - mousePressed.y;
				if (x < 0)
					x = 0;
				if (y < 0)
					y = 0;
				entityPanel.setLocation(x, y);
				repaint();
			}
		}
	}

	// implemented from MouseMotionListener
	public void mouseMoved(MouseEvent e) {
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
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaConceptGraphics) {
							if (entitiesAction instanceof AddAction) {
								AddAction addAction = (AddAction) entitiesAction;
								MetaConceptGraphic metaConceptGraphic = (MetaConceptGraphic) addAction
										.getEntity();
								if (transaction.isExecuted()) {
									addEntityPanel(metaConceptGraphic);
								} else if (transaction.isUndone()) {
									removeEntityPanel(metaConceptGraphic);
								}

							} else if (entitiesAction instanceof RemoveAction) {
								RemoveAction removeAction = (RemoveAction) entitiesAction;
								MetaConceptGraphic metaConceptGraphic = (MetaConceptGraphic) removeAction
										.getEntity();
								if (transaction.isExecuted()) {
									removeEntityPanel(metaConceptGraphic);
								} else if (transaction.isUndone()) {
									addEntityPanel(metaConceptGraphic);
								}
							}
						} else if (entities instanceof MetaNeighbors) {
							// repaint();
						}
					} // end for
					repaint();
				} else if (arg instanceof EntitiesAction) {
					EntitiesAction entitiesAction = (EntitiesAction) arg;
					IEntities<?> entities = entitiesAction.getEntities();
					if (entities instanceof MetaConceptGraphics) {
						if (entitiesAction instanceof AddAction) {
							AddAction addAction = (AddAction) entitiesAction;
							MetaConceptGraphic metaConceptGraphic = (MetaConceptGraphic) addAction
									.getEntity();
							if (addAction.isExecuted()) {
								addEntityPanel(metaConceptGraphic);
							} else if (addAction.isUndone()) {
								removeEntityPanel(metaConceptGraphic);
							}
						} else if (entitiesAction instanceof RemoveAction) {
							RemoveAction removeAction = (RemoveAction) entitiesAction;
							MetaConceptGraphic metaConceptGraphic = (MetaConceptGraphic) removeAction
									.getEntity();
							if (removeAction.isExecuted()) {
								removeEntityPanel(metaConceptGraphic);
							} else if (removeAction.isUndone()) {
								addEntityPanel(metaConceptGraphic);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Error in DiagramPanel.update: " + e.getMessage());
		}
	}
}