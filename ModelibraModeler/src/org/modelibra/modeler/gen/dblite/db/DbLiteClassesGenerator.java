package org.modelibra.modeler.gen.dblite.db;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.dblite.db.sql.GenSqlDbQueryClass;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.util.FileHandling;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2007-03-02
 */
public class DbLiteClassesGenerator {

	private static File lastDirectory = null;

	private static String lastPackage = null;

	private DiagramModel diagramModel;

	public DbLiteClassesGenerator(DiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}

	public void generateClasses() {
		String srcDirPath = selectDirectory();
		selectPackageAndGenerate(srcDirPath);
	}

	private String selectDirectory() {
		return FileHandling.selectDirectory();
		/*
		 * JFileChooser dirChooser = new JFileChooser();
		 * dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); if
		 * (lastDirectory != null) {
		 * dirChooser.setCurrentDirectory(lastDirectory); }
		 * dirChooser.setToolTipText(Para.getPara().getText("selectDirectory"));
		 * dirChooser.showDialog(null,
		 * Para.getPara().getText("selectDirectory")); File f =
		 * dirChooser.getSelectedFile(); if (f != null) { String dirPath =
		 * f.getPath(); lastDirectory = f; return dirPath; } return null;
		 */
	}

	private void selectPackageAndGenerate(String srcDirPath) {
		if (lastPackage == null) {
			String prefixLow = diagramModel.getAppModel().getPrefix();
			if (prefixLow != null) {
				prefixLow = prefixLow.trim();
			}
			String appNameLow = diagramModel.getAppModel().getName()
					.toLowerCase();
			String diagramNameLow = diagramModel.getName().toLowerCase();
			if (prefixLow == null || prefixLow.equals("?")) {
				lastPackage = appNameLow + "." + diagramNameLow;
			} else {
				lastPackage = prefixLow.toLowerCase() + "." + appNameLow + "."
						+ diagramNameLow;
			}
		}

		final String srcDirPathClone = srcDirPath;
		final JDialog packageFrame = new JDialog();
		packageFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		packageFrame.setTitle(Para.getPara().getText("generation"));

		JLabel label = new JLabel(Para.getPara().getText("selectPackage")
				+ " : ");
		final JTextField tf = new JTextField(lastPackage, 20);
		JButton button = new JButton(Para.getPara().getText("generation"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate(srcDirPathClone, tf.getText());
				lastPackage = tf.getText();
				packageFrame.dispose();
			}
		});

		Container cp = packageFrame.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(label, BorderLayout.WEST);
		cp.add(tf, BorderLayout.CENTER);
		cp.add(button, BorderLayout.EAST);

		packageFrame.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - packageFrame.getWidth()) / 2;
		int y = (screenSize.height - packageFrame.getHeight()) / 2;
		packageFrame.setLocation(x, y);

		packageFrame.setVisible(true);
	}

	private void generate(String srcDirPath, String genPackage) {

		if (srcDirPath != null) {
			Iterator boxes = diagramModel.getBoxes().iterator();
			int count = 0;
			while (boxes.hasNext()) {
				BoxModel box = (BoxModel) boxes.next();
				BoxModelKeysInfo boxInfo = new BoxModelKeysInfo(box);

				try {
					GenDbQueryRecordAPIClass recordClass = new GenDbQueryRecordAPIClass(
							boxInfo, srcDirPath, genPackage);
					if (recordClass.save()) {
						count++;
					}
				} catch (Exception e) {
					System.out.println("//Exception while creating class : "
							+ e.getMessage());
				}
				try {
					GenDbQueryInterface queryInterface = new GenDbQueryInterface(
							boxInfo, srcDirPath, genPackage);
					if (queryInterface.save()) {
						count++;
					}
				} catch (Exception e) {
					System.out.println("//Exception while creating class : "
							+ e.getMessage());
				}
				try {
					GenDbQueryFactoryClass queryClass = new GenDbQueryFactoryClass(
							boxInfo, srcDirPath, genPackage);
					if (queryClass.save()) {
						count++;
					}
				} catch (Exception e) {
					System.out.println("//Exception while creating class : "
							+ e.getMessage());
				}

				try {
					GenSqlDbQueryClass queryClass = new GenSqlDbQueryClass(
							boxInfo, srcDirPath, genPackage);
					if (queryClass.save()) {
						count++;
					}
				} catch (Exception e) {
					System.out.println("//Exception while creating class : "
							+ e.getMessage());
				}
			}
			if (count > 0) {
				informUser(count + " "
						+ Para.getPara().getText("classesCreated"));
			} else {

			}
		} else {
			informUser(Para.getPara().getText("classesNotCreated"));
		}
	}

	private void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

}