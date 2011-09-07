package org.modelibra.modeler.app.context;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.app.view.BoxSection;
import org.modelibra.modeler.app.view.form.DirectionsForm;
import org.modelibra.modeler.app.view.form.ItemsForm;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-03-12
 */
public class BoxPopupMenu extends JPopupMenu {

	static final long serialVersionUID = 7168319479760000100L;

	private static int clickedX = 0; // default

	private static int clickedY = 0; // default

	private JLabel extensionLabel = new JLabel("");

	private JCheckBoxMenuItem menuBoxAbstract = new JCheckBoxMenuItem(Para
			.getPara().getText("abstract"));

	private JCheckBoxMenuItem menuBoxEntry = new JCheckBoxMenuItem(Para
			.getPara().getText("entry"));

	private JMenuItem menuBoxItems = new JMenuItem(Para.getPara().getText(
			"items"));

	private JMenuItem menuBoxDirs1 = new JMenuItem(Para.getPara().getText(
			"directions")
			+ " 1");

	private JMenuItem menuBoxDirs2 = new JMenuItem(Para.getPara().getText(
			"directions")
			+ " 2");

	private static BoxPopupMenu instance;

	private static BoxSection boxView;

	private Manager manager = Manager.getSingleton();

	private BoxPopupMenu() {
		super();

		menuBoxAbstract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set abstract box"); // Transaction
				boxView.getModel().setAbstractDef(
						!boxView.getModel().isAbstractDef());
				manager.commit(); // Transaction
				// ------------------------------
			}
		});
		menuBoxEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set entry box"); // Transaction
				boxView.getModel().setEntry(!boxView.getModel().isEntry());
				manager.commit(); // Transaction
				// ------------------------------
			}
		});

		menuBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemsForm itemsForm = new ItemsForm(boxView.getModel());
				int x = clickedX;
				int y = clickedY;

				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				if (clickedX + itemsForm.getWidth() > screenSize.width) {
					x = screenSize.width - itemsForm.getWidth();
				} else if (clickedX - itemsForm.getWidth() / 2 < 0) {
					x = 0;
				}
				if (clickedY + itemsForm.getHeight() > screenSize.height) {
					y = screenSize.height - itemsForm.getHeight();
				} else if (clickedY - itemsForm.getHeight() / 2 < 0) {
					y = 0;
				}

				itemsForm.setLocation(x, y);
				itemsForm.setVisible(true);
			}
		});
		menuBoxDirs1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectionsForm dirs12Form = new DirectionsForm(boxView
						.getModel(), 12);
				dirs12Form.setLocation(20, 160);
				dirs12Form.setVisible(true);
			}
		});
		menuBoxDirs2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectionsForm dirs21Form = new DirectionsForm(boxView
						.getModel(), 21);
				dirs21Form.setLocation(80, 240);
				dirs21Form.setVisible(true);
			}
		});

		this.add(this.extensionLabel);
		this.addSeparator();
		this.add(this.menuBoxAbstract);
		this.add(this.menuBoxEntry);
		this.addSeparator();
		this.add(this.menuBoxItems);
		this.addSeparator();
		this.add(this.menuBoxDirs1);
		this.add(this.menuBoxDirs2);
	}

	public static BoxPopupMenu getSingleton(BoxSection aBoxView) {
		if (instance == null) {
			instance = new BoxPopupMenu();
		}
		boxView = aBoxView;
		String boxExtensionName = "";
		String boxModelExtensionName = boxView.getModel().getExtensionName();
		if (boxModelExtensionName != null) {
			boxExtensionName = boxModelExtensionName;
		}
		instance.extensionLabel.setText("  ==> " + boxExtensionName);
		return instance;
	}

	public void show(Component origin, int x, int y) {
		if (origin instanceof BoxSection && boxView != null) {
			if (boxView.getModel().isAbstractDef()) {
				menuBoxAbstract.setSelected(true);
			} else {
				menuBoxAbstract.setSelected(false);
			}
			if (boxView.getModel().isEntry()) {
				menuBoxEntry.setSelected(true);
			} else {
				menuBoxEntry.setSelected(false);
			}
			super.show(origin, x, y);

			clickedX = origin.getX();
			clickedY = origin.getY();
		}
	}

}
