package org.modelibra.swing.app;

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

import org.modelibra.IDomain;
import org.modelibra.ModelSession;
import org.modelibra.action.IHistoryObserver;
import org.modelibra.swing.domain.model.ModelEntryConceptsTableFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar implements IHistoryObserver {

	private JMenu menuFile;
	private JMenuItem menuFileExit;

	private JMenu menuEdit;
	private JMenuItem menuEditUndo;

	private JMenu menuModel;
	private JMenuItem menuModels;

	private JMenu menuHelp;
	private JMenuItem menuHelpAbout;

	private MainFrame mainFrame;

	public MainMenuBar(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		NatLang natLang = mainFrame.getApp().getNatLang();

		menuFile = new JMenu(natLang.getText("file"));
		menuFileExit = new JMenuItem(natLang.getText("exit"));

		menuEdit = new JMenu(natLang.getText("edit"));
		menuEditUndo = new JMenuItem(natLang.getText("undo"));

		menuModel = new JMenu(natLang.getText("data"));
		menuModels = new JMenuItem(natLang.getText("Models") + "...");

		menuHelp = new JMenu(natLang.getText("help"));
		menuHelpAbout = new JMenuItem(natLang.getText("about") + "...");

		menuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.exit();
			}
		});

		menuEditUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelSession modelSession = mainFrame.getApp()
						.getModelSession();
				if (modelSession != null) {
					modelSession.getHistory().undo();
				}
			}
		});
		menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));

		menuModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App app = mainFrame.getApp();
				IDomain domain = app.getDomain();
				ModelEntryConceptsTableFrame modelEntryConceptsDisplayTableFrame = new ModelEntryConceptsTableFrame(
						app, getMainFrame(), domain.getDomainConfig()
								.getModelConfigList());
				displayDownRight(mainFrame, modelEntryConceptsDisplayTableFrame);
			}
		});

		menuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCenter(mainFrame, new AboutDialog(mainFrame));
			}
		});

		menuFile.add(menuFileExit);
		menuEdit.add(menuEditUndo);
		menuModel.add(menuModels);
		menuHelp.add(menuHelpAbout);

		add(menuFile);
		add(menuEdit);
		add(menuModel);
		add(menuHelp);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * Displays a dialog within a center of the app min window. mainFrame
	 * 
	 * @param child
	 *            dialog
	 */
	private void displayCenter(JFrame parent, JDialog child) {
		Dimension childSize = child.getPreferredSize();
		Point childLocation = getCentralLocation(parent.getLocation(), parent
				.getSize(), childSize);
		child.setLocation(childLocation);
		child.setModal(true);
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
	 * Displays a child frame down and right of the parent frame.
	 * 
	 * @param parent
	 *            frame
	 * @param child
	 *            frame
	 */
	private void displayDownRight(JFrame parent, JFrame child) {
		Point parentLocation = parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();

		int parentWidth = parent.getWidth();
		double childX = parentX + parentWidth;
		double childY = parentY + ModelibraPanel.FRAME_DISPLAY_INCREMENT;

		Point childLocation = new Point();
		childLocation.setLocation(childX, childY);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

	public void setSession(ModelSession session) {
		if (session != null) {
			session.getHistory().addHistoryObserver(this);
		}
	}

	public void noHistory() {
		menuEditUndo.setEnabled(false);
	}

	public void yesHistory() {
		menuEditUndo.setEnabled(true);
	}

}
