package org.modelibra.modeler.app.context;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.view.form.TypesForm;
import org.modelibra.modeler.gen.Transfer;
import org.modelibra.modeler.gen.modelibra.config.ModelibraDomainConfigGenerator;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.action.HistoryCursorListener;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class AppMenu extends JMenuBar implements HistoryCursorListener {

	static final long serialVersionUID = 7168319479760000040L;

	public static final Point POINT = new Point(40, 40);

	private Para para = Para.getPara();

	private History history = History.getSingleton();

	private JMenu menuFile = new JMenu(para.getText("file"));

	private JMenuItem menuFileNew = new JMenuItem(para.getText("new"));

	private JMenuItem menuFileOpen = new JMenuItem(para.getText("open"));

	private JMenuItem menuFileClose = new JMenuItem(para.getText("close"));

	private JMenuItem menuFileSave = new JMenuItem(para.getText("save"));

	private JMenuItem menuFileSaveAs = new JMenuItem(para.getText("saveAs"));

	private JMenuItem menuFileExit = new JMenuItem(para.getText("exit"));

	private JMenu menuEdit = new JMenu(para.getText("edit"));

	private JMenuItem menuEditUndo = new JMenuItem(para.getText("undo"));

	private JMenuItem menuEditRedo = new JMenuItem(para.getText("redo"));

	private JMenu menuDict = new JMenu(para.getText("dict"));

	private JMenuItem menuDictTypes = new JMenuItem(para.getText("types"));

	private JMenu menuSchema = new JMenu(para.getText("generation"));

	private JMenuItem menuModelibraTypes = new JMenuItem(para
			.getText("genModelibraTypes"));

	private JMenuItem menuJDBCTypes = new JMenuItem(para
			.getText("genJDBCTypes"));

	private JMenuItem menuAccessTypes = new JMenuItem(para
			.getText("genAccessTypes"));

	private JMenuItem menuOracleTypes = new JMenuItem(para
			.getText("genOracleTypes"));

	private JMenuItem menuMySQLTypes = new JMenuItem(para
			.getText("genMySQLTypes"));

	private JMenuItem menuModelibraMinConfig = new JMenuItem(para
			.getText("genModelibraMinConfig"));

	private JMenuItem menuModelibraConfig = new JMenuItem(para
			.getText("genModelibraConfig"));

	private JMenu menuDb = new JMenu(para.getText("db"));

	private JMenuItem menuSql = new JMenuItem(para.getText("sql") + "...");

	private JMenu menuTransfer = new JMenu(para.getText("transfer"));

	private JMenuItem menuTransferImportTypes = new JMenuItem(para
			.getText("importTypes"));

	private JMenuItem menuTransferExportTypes = new JMenuItem(para
			.getText("exportTypes"));

	private JMenuItem menuTransferImportDiagrams = new JMenuItem(para
			.getText("importDiagrams"));

	private JMenuItem menuTransferExportDiagrams = new JMenuItem(para
			.getText("exportDiagrams"));

	private JMenu menuHelp = new JMenu(para.getText("help"));

	private JMenuItem menuHelpAbout = new JMenuItem(para.getText("about"));

	private AppFrame appFrame;

	private AppMenu() {
		super();

		menuFileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.neww();
			}
		});
		menuFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.open();
			}
		});
		menuFileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.close();
			}
		});
		menuFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.save();
			}
		});
		menuFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.saveAs();
			}
		});
		menuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFrame.exit();
			}
		});

		menuEditUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUndo();
			}
		});
		menuEditRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRedo();
			}
		});

		menuDictTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTypes();
			}
		});

		menuModelibraTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes(Config.getConfig().getModelibraTypesUrl());
			}
		});
		menuJDBCTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes(Config.getConfig().getJdbcTypesUrl());
			}
		});
		menuAccessTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes(Config.getConfig().getAccessTypesUrl());
			}
		});
		menuOracleTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes(Config.getConfig().getOracleTypesUrl());
			}
		});
		menuMySQLTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes(Config.getConfig().getMySQLTypesUrl());
			}
		});
		menuSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSourceFrame sqlFrame = new SQLFrame();
				sqlFrame.setLocation(POINT);
				sqlFrame.setVisible(true);
			}
		});
		menuModelibraMinConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelibraDomainConfigGenerator modelibraConfigGenerator = new ModelibraDomainConfigGenerator(
						AppModel.getSingleton());
				modelibraConfigGenerator.generateMinConfig();
			}
		});
		menuModelibraConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelibraDomainConfigGenerator modelibraConfigGenerator = new ModelibraDomainConfigGenerator(
						AppModel.getSingleton());
				modelibraConfigGenerator.generateConfig();
			}
		});

		menuTransferImportTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importTypes();
			}
		});
		menuTransferExportTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.exportTypes();
			}
		});
		menuTransferImportDiagrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importDiagrams();
			}
		});
		menuTransferExportDiagrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.exportDiagrams();
			}
		});

		menuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAbout();
			}
		});

		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));

		menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				ActionEvent.CTRL_MASK));
		menuEditRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));

		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileClose);
		menuFile.addSeparator();
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.addSeparator();
		menuFile.add(menuFileExit);

		menuEditUndo.setEnabled(false);
		menuEditRedo.setEnabled(false);

		menuEdit.add(menuEditUndo);
		menuEdit.add(menuEditRedo);

		menuDict.add(menuDictTypes);

		menuSchema.add(menuModelibraTypes);
		menuSchema.add(menuJDBCTypes);
		menuSchema.add(menuAccessTypes);
		menuSchema.add(menuOracleTypes);
		menuSchema.add(menuMySQLTypes);
		menuSchema.addSeparator();
		menuSchema.add(menuModelibraMinConfig);
		menuSchema.add(menuModelibraConfig);

		menuDb.add(menuSql);

		menuTransfer.add(menuTransferImportTypes);
		menuTransfer.add(menuTransferExportTypes);
		menuTransfer.add(menuTransferImportDiagrams);
		menuTransfer.add(menuTransferExportDiagrams);

		menuHelp.add(menuHelpAbout);

		this.add(menuFile);
		this.add(menuEdit);
		this.add(menuDict);
		this.add(menuSchema);
		this.add(menuDb);
		this.add(menuTransfer);
		this.add(menuHelp);
	}

	public AppMenu(AppFrame aFrame) {
		this();
		appFrame = aFrame;
		history.addHistoryCursorListener(this);
	}

	public AppFrame getAppFrame() {
		return appFrame;
	}

	private void displayAbout() {
		AboutApp child = new AboutApp(appFrame);
		Dimension childSize = child.getPreferredSize();
		Dimension parentSize = appFrame.getSize();
		Point parentLocation = appFrame.getLocation();
		int x = ((parentSize.width - childSize.width) / 2) + parentLocation.x;
		int y = ((parentSize.height - childSize.height) / 2) + parentLocation.y;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		child.setLocation(x, y);
		child.setModal(true);
		child.setVisible(true);
	}

	public static void displayTypes() {
		TypesForm typesForm = new TypesForm();
		typesForm.setLocation(40, 40);
		typesForm.setVisible(true);
	}

	private void doUndo() {
		history.undo();
	}

	private void doRedo() {
		history.redo();
	}

	public void lastReached(EventObject e) {
		// most recent, redo must be disabled:
		menuEditRedo.setEnabled(false);
	}

	public void firstReached(EventObject e) {
		// least recent, undo must be disabled:
		menuEditUndo.setEnabled(false);
	}

	public void inBetween(EventObject e) {
		// both undo and redo are available
		menuEditUndo.setEnabled(true);
		menuEditRedo.setEnabled(true);
	}

}
