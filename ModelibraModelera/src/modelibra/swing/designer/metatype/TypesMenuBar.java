package modelibra.swing.designer.metatype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import modelibra.swing.app.config.Para;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TypesMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(TypesMenuBar.class);

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

	private TypesFrame typesFrame;

	public TypesMenuBar(final TypesFrame typesFrame) {
		this.typesFrame = typesFrame;
		try {
			menuFileNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.neww();
				}
			});
			menuFileOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.open();
				}
			});
			menuFileClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.close();
				}
			});
			menuFileSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.save();
				}
			});
			menuFileSaveAs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.saveAs();
				}
			});
			menuFileExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typesFrame.exit();
				}
			});

			menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					ActionEvent.CTRL_MASK));

			menuFile.add(menuFileNew);
			menuFile.add(menuFileOpen);
			menuFile.add(menuFileClose);
			menuFile.addSeparator();
			menuFile.add(menuFileSave);
			menuFile.add(menuFileSaveAs);
			menuFile.addSeparator();
			menuFile.add(menuFileExit);

			add(menuFile);

		} catch (Exception e) {
			log.error("Error in TypesMenuBar.constructor: " + e.getMessage());
		}

	}

	public TypesFrame getTypesFrame() {
		return typesFrame;
	}

}
