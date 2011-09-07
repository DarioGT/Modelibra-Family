package modelibra.swing.designer.metadomain;

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
import javax.swing.KeyStroke;

import modelibra.swing.app.About;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metatype.TypesFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.ModelSession;
import org.modelibra.action.IHistoryObserver;

public class DomainsMenuBar extends JMenuBar implements IHistoryObserver {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(DomainsMenuBar.class);

	private JMenu menuFile = new JMenu(Para.getOne().getText("file"));

	private JMenuItem menuFileNew = new JMenuItem(Para.getOne().getText("new")
			+ "...");

	private JMenuItem menuFileOpen = new JMenuItem(Para.getOne()
			.getText("open")
			+ "...");

	private JMenuItem menuFileClose = new JMenuItem(Para.getOne().getText(
			"close"));

	private JMenuItem menuFileSave = new JMenuItem(Para.getOne()
			.getText("save"));

	private JMenuItem menuFileSaveAs = new JMenuItem(Para.getOne().getText(
			"saveAs")
			+ "...");

	private JMenuItem menuFileExit = new JMenuItem(Para.getOne()
			.getText("exit"));

	private JMenu menuEdit = new JMenu(Para.getOne().getText("edit"));

	private JMenuItem menuEditUndo = new JMenuItem(Para.getOne()
			.getText("undo"));

	private JMenu menuDictionary = new JMenu(Para.getOne()
			.getText("dictionary"));

	private JMenuItem menuDictionaryTypes = new JMenuItem(Para.getOne()
			.getText("types")
			+ "...");

	private JMenu menuHelp = new JMenu(Para.getOne().getText("help"));

	private JMenuItem menuHelpAbout = new JMenuItem(Para.getOne().getText(
			"about")
			+ "...");

	private DomainsFrame domainsFrame;

	private ModelSession modelSession;

	public DomainsMenuBar(final DomainsFrame domainsFrame) {
		this.domainsFrame = domainsFrame;
		try {
			menuFileNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.neww();
				}
			});
			menuFileOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.open();
				}
			});
			menuFileClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.close();
				}
			});
			menuFileSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.save();
				}
			});
			menuFileSaveAs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.saveAs();
				}
			});
			menuFileExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					domainsFrame.exit();
				}
			});

			menuEditUndo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (modelSession != null) {
						modelSession.getHistory().undo();
					}
				}
			});

			menuDictionaryTypes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					display(new TypesFrame());
				}
			});

			menuHelpAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					display(new About(domainsFrame));
				}
			});

			menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					ActionEvent.CTRL_MASK));
			menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
					ActionEvent.CTRL_MASK));

			menuFile.add(menuFileNew);
			menuFile.add(menuFileOpen);
			menuFile.add(menuFileClose);
			menuFile.addSeparator();
			menuFile.add(menuFileSave);
			menuFile.add(menuFileSaveAs);
			menuFile.addSeparator();
			menuFile.add(menuFileExit);

			menuEdit.add(menuEditUndo);

			menuDictionary.add(menuDictionaryTypes);

			menuHelp.add(menuHelpAbout);

			add(menuFile);
			add(menuEdit);
			add(menuDictionary);
			add(menuHelp);

			noHistory();
		} catch (Exception e) {
			log.error("Error in DomainsMenuBar.constructor: " + e.getMessage());
		}

	}

	public DomainsFrame getDomainsFrame() {
		return domainsFrame;
	}

	/**
	 * Displays a dialog within a center of the app min window.
	 * 
	 * @param child
	 *            dialog
	 */
	private void display(JDialog child) {
		Dimension childSize = child.getPreferredSize();
		Point childLocation = getCentralLocation(domainsFrame.getLocation(),
				domainsFrame.getSize(), childSize);
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
		Point childLocation = getCentralLocation(domainsFrame.getLocation(),
				domainsFrame.getSize(), childSize);
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

	/**
	 * Sets the domain model session.
	 * 
	 * @param modelSession
	 *            model session
	 */
	public void setModelSession(ModelSession modelSession) {
		this.modelSession = modelSession;
		if (modelSession != null) {
			modelSession.getHistory().addHistoryObserver(this);
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
