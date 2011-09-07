package org.modelibra.modeler.app.context;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.view.form.BoxesForm;
import org.modelibra.modeler.app.view.form.LinesForm;
import org.modelibra.modeler.gen.Oracle;
import org.modelibra.modeler.gen.Transfer;
import org.modelibra.modeler.gen.dblite.db.DbLiteClassesGenerator;
import org.modelibra.modeler.gen.modelibra.config.ModelibraModelConfigGenerator;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.action.HistoryCursorListener;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class DiagramMenu extends JMenuBar implements HistoryCursorListener {

	static final long serialVersionUID = 7168319479760000160L;

	public static final Point POINT = new Point(40, 40);

	private Para para = Para.getPara();

	private History history = History.getSingleton();

	private JMenu menuFile = new JMenu(para.getText("diagramMenu"));

	private JMenuItem menuFileSave = new JMenuItem(para.getText("save"));

	private JMenuItem menuFileSaveImage = new JMenuItem(para
			.getText("saveImage"));

	private JMenuItem menuFileExit = new JMenuItem(para.getText("exit"));

	private JMenu menuEdit = new JMenu(para.getText("edit"));

	private JMenuItem menuEditUndo = new JMenuItem(para.getText("undo"));

	private JMenuItem menuEditRedo = new JMenuItem(para.getText("redo"));

	private JMenuItem menuEditSelectLinesBetweenSelectedBoxes = new JMenuItem(
			para.getText("selectLinesBetweenSelectedBoxes"));

	private JMenuItem menuEditSelectLinesForSelectedBoxes = new JMenuItem(para
			.getText("selectLinesForSelectedBoxes"));

	private JMenuItem menuEditSelect = new JMenuItem(para.getText("selectAll"));

	private JMenuItem menuEditDeselect = new JMenuItem(para
			.getText("deselectAll"));

	private JMenuItem menuEditDelete = new JMenuItem(para
			.getText("deleteSelection"));

	private JMenu menuPres = new JMenu(para.getText("presentation"));

	private JMenuItem menuPresZoomIn = new JMenuItem(para.getText("zoomIn"));

	private JMenuItem menuPresZoomOut = new JMenuItem(para.getText("zoomOut"));

	private JMenuItem menuPresIncreaseWidth = new JMenuItem(para
			.getText("increaseWidth"));

	private JMenuItem menuPresDecreaseWidth = new JMenuItem(para
			.getText("decreaseWidth"));

	private JMenuItem menuPresIncreaseHeight = new JMenuItem(para
			.getText("increaseHeight"));

	private JMenuItem menuPresDecreaseHeight = new JMenuItem(para
			.getText("decreaseHeight"));

	private JMenuItem menuPresIncreaseSize = new JMenuItem(para
			.getText("increaseSize"));

	private JMenuItem menuPresDecreaseSize = new JMenuItem(para
			.getText("decreaseSize"));

	private JMenuItem menuPresHide = new JMenuItem(para
			.getText("hideSelection"));

	private JMenuItem menuPresShow = new JMenuItem(para
			.getText("showSelection"));

	private JMenuItem menuPresColor = new JMenuItem(para
			.getText("colorSelection"));

	private JMenuItem menuPresTile = new JMenuItem(para.getText("tileBoxes"));

	private JMenu menuDict = new JMenu(para.getText("dict"));

	private JMenuItem menuDictBoxes = new JMenuItem(para.getText("boxes"));

	private JMenuItem menuDictLines = new JMenuItem(para.getText("lines"));

	private JMenu menuGen = new JMenu(para.getText("generation"));

	private JMenuItem menuGenJDBCSchema = new JMenuItem(para
			.getText("genJDBCSchema"));

	private JMenuItem menuGenOracleCreateSchemaFile = new JMenuItem(para
			.getText("genOracleCreateSchemaFile"));

	private JMenuItem menuGenOracleDropSchemaFile = new JMenuItem(para
			.getText("genOracleDropSchemaFile"));

	private JMenuItem menuConvertMySql = new JMenuItem(para
			.getText("convertMySQLToInnoDB"));

	private JMenuItem menuGenDbLiteClasses = new JMenuItem(para
			.getText("genDbLiteClasses"));

	private JMenuItem menuReverseDb = new JMenuItem(para.getText("reverseDb"));

	private JMenuItem menuModelibraMinConfig = new JMenuItem(para
			.getText("genModelibraMinConfig"));

	private JMenuItem menuModelibraConfig = new JMenuItem(para
			.getText("genModelibraConfig"));

	private JMenu menuDb = new JMenu(para.getText("db"));

	private JMenuItem menuSql = new JMenuItem(para.getText("sql") + "...");

	private JMenu menuUtil = new JMenu(para.getText("util"));

	private JMenuItem menuUtilCreateBoxes = new JMenuItem(para
			.getText("createBoxes"));

	private JMenuItem menuUtilCreateSequence = new JMenuItem(para
			.getText("createSequence"));

	private JMenuItem menuUtilCreateRules = new JMenuItem(para
			.getText("createRules"));

	private JMenuItem menuUtilCopyItems = new JMenuItem(para
			.getText("copyItems"));

	private JMenu menuTransfer = new JMenu(para.getText("transfer"));

	private JMenuItem menuTransferImportBoxesAndLines = new JMenuItem(para
			.getText("importBoxesAndLines"));

	private JMenuItem menuTransferExportSelection = new JMenuItem(para
			.getText("exportSelection"));

	private JMenuItem menuTransferImportItems = new JMenuItem(para
			.getText("importItems"));

	private JMenuItem menuTransferExportItems = new JMenuItem(para
			.getText("exportItems"));

	private DiagramFrame diagramFrame;

	private DiagramMenu() {
		super();

		menuFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getAppFrame().save();
			}
		});
		menuFileSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.saveImage();
			}
		});
		menuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.exit();
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
		menuEditSelectLinesBetweenSelectedBoxes
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						diagramFrame.getDiagramView()
								.selectLinesBetweenSelectedBoxes();
					}
				});
		menuEditSelectLinesForSelectedBoxes
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						diagramFrame.getDiagramView()
								.selectLinesForSelectedBoxes();
					}
				});
		menuEditSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().selectAll();
			}
		});
		menuEditDeselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().deselectAll();
			}
		});
		menuEditDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().deleteSelection();
			}
		});

		menuPresZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().zoomIn(true);
			}
		});
		menuPresZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().zoomIn(false);
			}
		});
		menuPresIncreaseWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"increase", "width");
			}
		});
		menuPresDecreaseWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"decrease", "width");
			}
		});
		menuPresIncreaseHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"increase", "height");
			}
		});
		menuPresDecreaseHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"decrease", "height");
			}
		});
		menuPresIncreaseSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"increase", "both");
			}
		});
		menuPresDecreaseSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().changeSelectedBoxesSize(
						"decrease", "both");
			}
		});
		menuPresHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().hideSelection();
			}
		});
		menuPresShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().showSelection();
			}
		});
		menuPresTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().tileBoxes();
			}
		});
		menuPresColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().colorSelection();
			}
		});

		menuDictBoxes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayBoxes();
			}
		});
		menuDictLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayLines();
			}
		});

		menuGenJDBCSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSourceFrame schemaFrame = new SchemaFrame(diagramFrame
						.getDiagramModel());
				schemaFrame.setLocation(POINT);
				schemaFrame.setVisible(true);
			}
		});
		menuGenOracleCreateSchemaFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oracle.getSingleton().genCreateSchema(
						diagramFrame.getDiagramModel());
			}
		});
		menuGenOracleDropSchemaFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oracle.getSingleton().genDropSchema(
						diagramFrame.getDiagramModel());
			}
		});
		menuConvertMySql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConvertMySQLToInnoDBFrame convertFrame = new ConvertMySQLToInnoDBFrame();
				convertFrame.setLocation(POINT);
				convertFrame.setVisible(true);

			}
		});
		menuReverseDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSourceFrame reverseDatabaseFrame = new ReverseDatabaseFrame(
						diagramFrame.getDiagramModel());
				reverseDatabaseFrame.setLocation(POINT);
				reverseDatabaseFrame.setVisible(true);
			}
		});
		menuSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSourceFrame sqlFrame = new SQLFrame();
				sqlFrame.setLocation(POINT);
				sqlFrame.setVisible(true);
			}
		});
		menuGenDbLiteClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbLiteClassesGenerator classGenerator = new DbLiteClassesGenerator(
						diagramFrame.getDiagramModel());
				classGenerator.generateClasses();
			}
		});
		menuModelibraMinConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelibraModelConfigGenerator modelibraConfigGenerator = new ModelibraModelConfigGenerator(
						diagramFrame.getDiagramModel());
				modelibraConfigGenerator.generateMinConfig();
			}
		});
		menuModelibraConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelibraModelConfigGenerator modelibraConfigGenerator = new ModelibraModelConfigGenerator(
						diagramFrame.getDiagramModel());
				modelibraConfigGenerator.generateConfig();
			}
		});

		menuUtilCreateBoxes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().createBoxes();
			}
		});
		menuUtilCreateRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().createRules();
			}
		});
		menuUtilCreateSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().createSequence();
			}
		});
		menuUtilCopyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diagramFrame.getDiagramView().copyItemsFromParentToChild();
			}
		});

		menuTransferImportBoxesAndLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importBoxesAndLines(diagramFrame.getDiagramModel());
			}
		});
		menuTransferExportSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.exportSelection(diagramFrame.getDiagramModel());
			}
		});
		menuTransferImportItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.importItems(diagramFrame.getDiagramView()
						.getFirstSelectedBox().getModel());
			}
		});
		menuTransferExportItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer.exportItems(diagramFrame.getDiagramView()
						.getFirstSelectedBox().getModel());
			}
		});

		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));

		menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				ActionEvent.CTRL_MASK));
		menuEditRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));
		menuEditDelete.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));

		menuPresZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		menuPresZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		menuPresIncreaseWidth.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		menuPresIncreaseHeight.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_H, ActionEvent.CTRL_MASK));

		menuFile.add(menuFileSave);
		menuFile.addSeparator();
		menuFile.add(menuFileSaveImage);
		menuFile.addSeparator();
		menuFile.add(menuFileExit);

		menuEditUndo.setEnabled(false);
		menuEditRedo.setEnabled(false);

		menuEdit.add(menuEditUndo);
		menuEdit.add(menuEditRedo);
		menuEdit.addSeparator();
		menuEdit.add(menuEditSelectLinesBetweenSelectedBoxes);
		menuEdit.add(menuEditSelectLinesForSelectedBoxes);
		menuEdit.addSeparator();
		menuEdit.add(menuEditSelect);
		menuEdit.add(menuEditDeselect);
		menuEdit.addSeparator();
		menuEdit.add(menuEditDelete);

		menuPres.add(menuPresZoomIn);
		menuPres.add(menuPresZoomOut);
		menuPres.addSeparator();
		menuPres.add(menuPresIncreaseWidth);
		menuPres.add(menuPresDecreaseWidth);
		menuPres.add(menuPresIncreaseHeight);
		menuPres.add(menuPresDecreaseHeight);
		menuPres.add(menuPresIncreaseSize);
		menuPres.add(menuPresDecreaseSize);
		menuPres.addSeparator();
		menuPres.add(menuPresHide);
		menuPres.add(menuPresShow);
		menuPres.add(menuPresColor);
		menuPres.addSeparator();
		menuPres.add(menuPresTile);

		menuDict.add(menuDictBoxes);
		menuDict.add(menuDictLines);

		menuGen.add(menuGenJDBCSchema);
		menuGen.addSeparator();
		menuGen.add(menuGenOracleCreateSchemaFile);
		menuGen.add(menuGenOracleDropSchemaFile);
		menuGen.addSeparator();
		menuGen.add(menuConvertMySql);
		menuGen.addSeparator();
		menuGen.add(menuGenDbLiteClasses);
		menuGen.addSeparator();
		menuGen.add(menuReverseDb);
		menuGen.addSeparator();
		menuGen.add(menuModelibraMinConfig);
		menuGen.add(menuModelibraConfig);

		menuDb.add(menuSql);

		menuUtil.add(menuUtilCreateBoxes);
		menuUtil.add(menuUtilCreateSequence);
		menuUtil.add(menuUtilCreateRules);
		menuUtil.addSeparator();
		menuUtil.add(menuUtilCopyItems);

		menuTransfer.add(menuTransferImportBoxesAndLines);
		menuTransfer.add(menuTransferExportSelection);
		menuTransfer.add(menuTransferImportItems);
		menuTransfer.add(menuTransferExportItems);

		this.add(menuFile);
		this.add(menuEdit);
		this.add(menuPres);
		this.add(menuDict);
		this.add(menuGen);
		this.add(menuDb);
		this.add(menuUtil);
		this.add(menuTransfer);
	}

	public DiagramMenu(DiagramFrame aFrame) {
		this();
		diagramFrame = aFrame;
		history.addHistoryCursorListener(this);
	}

	public DiagramFrame getDiagramFrame() {
		return diagramFrame;
	}

	private void displayBoxes() {
		BoxesForm boxesForm = new BoxesForm(diagramFrame.getDiagramView()
				.getModel());
		boxesForm.setLocation(80, 0);
		boxesForm.setVisible(true);
	}

	private void displayLines() {
		LinesForm linesForm = new LinesForm(diagramFrame.getDiagramView()
				.getModel());
		linesForm.setLocation(0, 20);
		linesForm.setVisible(true);
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
