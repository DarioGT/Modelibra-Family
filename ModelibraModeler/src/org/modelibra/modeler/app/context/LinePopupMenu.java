package org.modelibra.modeler.app.context;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.app.view.DiagramSection;
import org.modelibra.modeler.app.view.form.LineForm;
import org.modelibra.modeler.model.LineModel;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2007-06-05
 */
public class LinePopupMenu extends JPopupMenu {

	static final long serialVersionUID = 7168319479760000290L;

	private static LineModel line;

	private static int clickedX = 0; // default

	private static int clickedY = 0; // default

	private JCheckBoxMenuItem menuInternal = new JCheckBoxMenuItem(Para
			.getPara().getText("internal"));

	private JCheckBoxMenuItem menuPartOfManyToMany = new JCheckBoxMenuItem(Para
			.getPara().getText("partOfManyToMany"));

	private JMenuItem menuLineDirs = new JMenuItem(Para.getPara().getText(
			"directions"));

	private JCheckBoxMenuItem menuDir1Id = new JCheckBoxMenuItem(Para.getPara()
			.getText("dir1Id"));

	private JCheckBoxMenuItem menuDir2Id = new JCheckBoxMenuItem(Para.getPara()
			.getText("dir2Id"));

	private JCheckBoxMenuItem menuDir1NameVisible = new JCheckBoxMenuItem(Para
			.getPara().getText("dir1NameVisible"));

	private JCheckBoxMenuItem menuDir2NameVisible = new JCheckBoxMenuItem(Para
			.getPara().getText("dir2NameVisible"));

	private static LinePopupMenu instance;

	private Manager manager = Manager.getSingleton();

	private LinePopupMenu() {
		super();

		menuInternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set internal"); // Transaction
				if (line != null) {
					line.setInternal(!line.isInternal());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});

		menuPartOfManyToMany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set part of many-to-many"); // Transaction
				if (line != null) {
					line.setPartOfManyToMany(!line.isPartOfManyToMany());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});

		menuLineDirs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineForm dirsForm = new LineForm(line);

				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int x = (screenSize.width - dirsForm.getWidth()) / 2;
				int y = (screenSize.height - dirsForm.getHeight()) / 2;
				dirsForm.setLocation(x, y);
				// dirsForm.setLocation(clickedX, clickedY);
				// (clickedX, clickedY) does not work properly when a diagram is larger 
				// than the visible part of the diagram

				dirsForm.setVisible(true);
			}
		});

		menuDir1Id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set dir 1 id"); // Transaction
				if (line != null) {
					line.setDir12Id(!line.isDir12Id());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});
		menuDir2Id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set dir 2 id"); // Transaction
				if (line != null) {
					line.setDir21Id(!line.isDir21Id());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});

		menuDir1NameVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set dir 1 name visibility"); // Transaction
				if (line != null) {
					line.setDir12NameVisible(!line.isDir12NameVisible());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});
		menuDir2NameVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.startTransaction("set dir 2 name visibility"); // Transaction
				if (line != null) {
					line.setDir21NameVisible(!line.isDir21NameVisible());
				}
				manager.commit(); // Transaction
				// ------------------------------
			}
		});

		this.add(this.menuInternal);
		this.add(this.menuPartOfManyToMany);
		this.addSeparator();
		this.add(this.menuLineDirs);
		this.addSeparator();
		this.add(this.menuDir1Id);
		this.add(this.menuDir2Id);
		this.addSeparator();
		this.add(this.menuDir1NameVisible);
		this.add(this.menuDir2NameVisible);

	}

	public static LinePopupMenu getSingleton(LineModel aLine) {
		if (instance == null) {
			instance = new LinePopupMenu();
		}
		line = aLine;
		return instance;
	}

	public void show(Component origin, LineModel line, int x, int y) {
		if (origin instanceof DiagramSection) {
			if (line.isInternal()) {
				menuInternal.setSelected(true);
			} else {
				menuInternal.setSelected(false);
			}

			if (line.isPartOfManyToMany()) {
				menuPartOfManyToMany.setSelected(true);
			} else {
				menuPartOfManyToMany.setSelected(false);
			}

			if (line.isDir12Id()) {
				menuDir1Id.setSelected(true);
			} else {
				menuDir1Id.setSelected(false);
			}
			if (line.isDir21Id()) {
				menuDir2Id.setSelected(true);
			} else {
				menuDir2Id.setSelected(false);
			}

			if (line.isDir12NameVisible()) {
				menuDir1NameVisible.setSelected(true);
			} else {
				menuDir1NameVisible.setSelected(false);
			}
			if (line.isDir21NameVisible()) {
				menuDir2NameVisible.setSelected(true);
			} else {
				menuDir2NameVisible.setSelected(false);
			}

			super.show(origin, x, y);

			clickedX = x;
			clickedY = y;
		}
	}

}
