package modelibra.swing.designer.metamodel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metaconcept.ConceptsFrame;
import modelibra.swing.designer.metaconcept.EntityPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.ModelSession;
import org.modelibra.action.IHistoryObserver;

public class DiagramMenuBar extends JMenuBar implements IHistoryObserver {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(DiagramMenuBar.class);

	private JMenu menuFile = new JMenu(Para.getOne().getText("file"));

	private JMenuItem menuFileSave = new JMenuItem(Para.getOne()
			.getText("save"));

	private JMenuItem menuFileExit = new JMenuItem(Para.getOne()
			.getText("exit"));

	private JMenu menuEdit = new JMenu(Para.getOne().getText("edit"));

	private JMenuItem menuEditUndo = new JMenuItem(Para.getOne()
			.getText("undo"));

	private JMenuItem menuEditDelete = new JMenuItem(Para.getOne().getText(
			"deleteSelectedEntities"));
	private JMenuItem menuEditCreate = new JMenuItem(Para.getOne().getText(
			"createEntities")
			+ "...");

	private JMenu menuView = new JMenu(Para.getOne().getText("view"));

	private JMenuItem menuViewFind = new JMenuItem(Para.getOne().getText(
			"findEntity")
			+ "...");
	private JMenuItem menuViewSelect = new JMenuItem(Para.getOne().getText(
			"selectEntities"));
	private JMenuItem menuViewDeselect = new JMenuItem(Para.getOne().getText(
			"deselectEntities"));
	private JMenuItem menuViewIncreaseHeight = new JMenuItem(Para.getOne()
			.getText("increaseHeightSelectedEntities"));
	private JMenuItem menuViewDecreaseHeight = new JMenuItem(Para.getOne()
			.getText("decreaseHeightSelectedEntities"));
	private JMenuItem menuViewIncreaseWidth = new JMenuItem(Para.getOne()
			.getText("increaseWidthSelectedEntities"));
	private JMenuItem menuViewDecreaseWidth = new JMenuItem(Para.getOne()
			.getText("decreaseWidthSelectedEntities"));
	private JMenuItem menuViewIncreaseSize = new JMenuItem(Para.getOne()
			.getText("increaseSizeSelectedEntities"));
	private JMenuItem menuViewDecreaseSize = new JMenuItem(Para.getOne()
			.getText("decreaseSizeSelectedEntities"));
	private JMenuItem menuViewZoomIn = new JMenuItem(Para.getOne().getText(
			"zoomIn"));
	private JMenuItem menuViewZoomOut = new JMenuItem(Para.getOne().getText(
			"zoomOut"));
	private JMenuItem menuViewHide = new JMenuItem(Para.getOne().getText(
			"hideSelectedEntities"));
	private JMenuItem menuViewShow = new JMenuItem(Para.getOne().getText(
			"showHiddenEntities"));
	private JMenuItem menuViewColor = new JMenuItem(Para.getOne().getText(
			"colorEntities")
			+ "...");
	private JMenuItem menuViewArrange = new JMenuItem(Para.getOne().getText(
			"arrangeEntities"));

	private JMenu menuDictionary = new JMenu(Para.getOne()
			.getText("dictionary"));

	private JMenuItem menuDictionaryConcepts = new JMenuItem(Para.getOne()
			.getText("concepts")
			+ "...");

	private DiagramFrame diagramFrame;

	private ConceptsFrame conceptsFrame;

	private ModelSession session;

	public DiagramMenuBar(final DiagramFrame diagramFrame) {
		this.diagramFrame = diagramFrame;
		try {
			menuFileSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.save();
				}
			});

			menuFileExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.exit();
				}
			});

			menuEditUndo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (session != null) {
						session.getHistory().undo();
					}
				}
			});

			menuEditDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.removeSelectedMetaConceptGraphics();
				}
			});

			menuEditCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().createMetaConceptGraphics(
							inputNumber());
				}
			});

			menuViewFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EntityPanel entityPanel = diagramFrame.getDiagramPanel()
							.findEntityPanel(inputName());
					if (entityPanel != null) {
						entityPanel.setSelected(true);
						Point location = entityPanel.getLocation();
						diagramFrame.setScrollBars(location.getX(), location
								.getY());
					}
				}
			});

			menuViewSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().selectEntityPanels();
				}
			});

			menuViewDeselect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().deselectEntityPanels();
				}
			});

			menuViewIncreaseHeight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(true, false, 20,
									true);
				}
			});

			menuViewDecreaseHeight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(true, false, 20,
									false);
				}
			});

			menuViewIncreaseWidth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(false, true, 20,
									true);
				}
			});

			menuViewDecreaseWidth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(false, true, 20,
									false);
				}
			});

			menuViewIncreaseSize.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(true, true, 20,
									true);
				}
			});

			menuViewDecreaseSize.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel()
							.resizeSelectedMetaConceptGraphics(true, true, 20,
									false);
				}
			});

			menuViewZoomIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().zoomIn(true);
				}
			});

			menuViewZoomOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().zoomIn(false);
				}
			});

			menuViewHide.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().hideSelectedEntityPanels();
				}
			});

			menuViewShow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().showSelectedEntityPanels();
				}
			});

			menuViewColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().colorEntityPanels();
				}
			});

			menuViewArrange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					diagramFrame.getDiagramPanel().getMetaModel()
							.getMetaConcepts().tile();
				}
			});

			menuDictionaryConcepts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conceptsFrame = new ConceptsFrame(diagramFrame);
					display(conceptsFrame);
				}
			});

			menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					ActionEvent.CTRL_MASK));
			menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
					ActionEvent.CTRL_MASK));
			menuViewIncreaseHeight.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
			menuViewIncreaseWidth.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_W, ActionEvent.CTRL_MASK));
			menuViewIncreaseSize.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_G, ActionEvent.CTRL_MASK));
			menuViewZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
					ActionEvent.CTRL_MASK));
			menuViewZoomOut.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_O, ActionEvent.CTRL_MASK));

			menuFile.add(menuFileSave);
			menuFile.addSeparator();
			menuFile.add(menuFileExit);

			menuEdit.add(menuEditUndo);
			menuEdit.addSeparator();
			menuEdit.add(menuEditDelete);
			menuEdit.addSeparator();
			menuEdit.add(menuEditCreate);

			menuView.add(menuViewFind);
			menuView.addSeparator();
			menuView.add(menuViewSelect);
			menuView.add(menuViewDeselect);
			menuView.addSeparator();
			menuView.add(menuViewIncreaseHeight);
			menuView.add(menuViewDecreaseHeight);
			menuView.add(menuViewIncreaseWidth);
			menuView.add(menuViewDecreaseWidth);
			menuView.add(menuViewIncreaseSize);
			menuView.add(menuViewDecreaseSize);
			menuView.addSeparator();
			menuView.add(menuViewZoomIn);
			menuView.add(menuViewZoomOut);
			menuView.addSeparator();
			menuView.add(menuViewHide);
			menuView.add(menuViewShow);
			menuView.addSeparator();
			menuView.add(menuViewColor);
			menuView.addSeparator();
			menuView.add(menuViewArrange);

			menuDictionary.add(menuDictionaryConcepts);

			add(menuFile);
			add(menuEdit);
			add(menuView);
			add(menuDictionary);

			noHistory();
		} catch (Exception e) {
			log.error("Error in DiagramMenuBar.constructor: " + e.getMessage());
		}

	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	public ConceptsFrame getConceptsFrame() {
		return conceptsFrame;
	}

	/**
	 * Displays a dialog within a center of the app min window.
	 * 
	 * @param child
	 *            dialog
	 */
	private void display(JDialog child) {
		Dimension childSize = child.getPreferredSize();
		Point childLocation = getCentralLocation(diagramFrame.getLocation(),
				diagramFrame.getSize(), childSize);
		child.setLocation(childLocation);
		child.setModal(true);
		child.setVisible(true);
	}

	/**
	 * Displays a frame within a center of the app min window.
	 * 
	 * @param child
	 *            frame
	 */
	private void display(JFrame child) {
		Dimension childSize = child.getPreferredSize();
		Point childLocation = getCentralLocation(diagramFrame.getLocation(),
				diagramFrame.getSize(), childSize);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

	/**
	 * Gets a central child location with respect to the given parent location
	 * and size.
	 * 
	 * @param parentLocation
	 *            parent location
	 * @param parentSize
	 *            parent size
	 * @param childSize
	 *            child size
	 */
	private Point getCentralLocation(Point parentLocation,
			Dimension parentSize, Dimension childSize) {
		Point point = null;
		int x = ((parentSize.width - childSize.width) / 2) + parentLocation.x;
		int y = ((parentSize.height - childSize.height) / 2) + parentLocation.y;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		point = new Point(x, y);
		return point;
	}

	private int inputNumber() {
		int number = 0;
		String s = (String) JOptionPane.showInputDialog(diagramFrame, Para
				.getOne().getText("number"), Para.getOne()
				.getText("generation"), JOptionPane.PLAIN_MESSAGE, null, null,
				null);

		if ((s != null) && (s.length() > 0)) {
			Integer integerNumber = new Integer(s);
			if (integerNumber != null) {
				number = integerNumber.intValue();
			}
		}

		return number;
	}

	private String inputName() {
		String name = "?";
		String s = (String) JOptionPane.showInputDialog(diagramFrame, Para
				.getOne().getText("name"), Para.getOne().getText("search"),
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		if ((s != null) && (s.length() > 0)) {
			return s;
		}

		return name;
	}

	/**
	 * Sets the domain model session.
	 * 
	 * @param session
	 *            session
	 */
	public void setSession(ModelSession session) {
		this.session = session;
		if (session != null) {
			session.getHistory().addHistoryObserver(this);
		}
	}

	/**
	 * No action history left.
	 */
	public void noHistory() {
		menuEditUndo.setEnabled(false);
	}

	/**
	 * Yes, the action history is not empty.
	 */
	public void yesHistory() {
		menuEditUndo.setEnabled(true);
	}

}
