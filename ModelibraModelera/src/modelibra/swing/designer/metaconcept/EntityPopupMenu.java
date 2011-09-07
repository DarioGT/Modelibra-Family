package modelibra.swing.designer.metaconcept;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metaneighbor.NeighborsFrame;
import modelibra.swing.designer.metaproperty.PropertiesFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;

/**
 * Entity popup menu.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public class EntityPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(EntityPopupMenu.class);

	private JCheckBoxMenuItem entryMenuItem = new JCheckBoxMenuItem(Para
			.getOne().getText("entry"));
	private JMenuItem propertiesMenuItem = new JMenuItem(Para.getOne().getText(
			"properties")
			+ "...");
	private JMenuItem neighborsMenuItem = new JMenuItem(Para.getOne().getText(
			"neighbors")
			+ "...");

	private static EntityPopupMenu instance;

	private static EntityPanel entityPanel;

	private EntityPopupMenu() {
		entryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetaConcept concept = entityPanel.getMetaConcept();
				MetaConcept conceptCopy = concept.copy();
				conceptCopy.setEntry(!conceptCopy.getEntry());
				Designer designer = ModelibraData.getOne().getDesigner();
				EntitiesAction action = new UpdateAction(designer.getSession(),
						concept.getMetaModel().getMetaConcepts(), concept,
						conceptCopy);
				action.execute();
			}
		});

		propertiesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropertiesFrame propertiesFrame = new PropertiesFrame(
						entityPanel.getMetaConcept());
				int x = entityPanel.getX();
				int y = entityPanel.getY();
				int width = entityPanel.getWidth();
				propertiesFrame.setLocation(x + width, y);
				propertiesFrame.setVisible(true);
			}
		});

		neighborsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NeighborsFrame neighborsFrame = new NeighborsFrame(entityPanel
						.getMetaConcept());
				int x = entityPanel.getX();
				int y = entityPanel.getY();
				int width = entityPanel.getWidth();
				neighborsFrame.setLocation(x + width, y);
				neighborsFrame.setVisible(true);
			}
		});

		add(entryMenuItem);
		addSeparator();
		add(propertiesMenuItem);
		add(neighborsMenuItem);
	}

	public static EntityPopupMenu getOne(EntityPanel entityPanel) {
		EntityPopupMenu.entityPanel = entityPanel;
		if (instance == null) {
			instance = new EntityPopupMenu();
		}
		return instance;
	}

	public void show(Component origin, int x, int y) {
		if (origin instanceof EntityPanel && entityPanel != null) {
			if (entityPanel.getMetaConcept().isEntry()) {
				entryMenuItem.setSelected(true);
			} else {
				entryMenuItem.setSelected(false);
			}
			super.show(origin, x, y);
		}
	}

}
