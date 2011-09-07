package org.modelibra.modeler.app.context;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.modelibra.modeler.app.view.DiagramSection;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.util.FileHandling;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-11
 */
public class DiagramFrame extends JFrame {

	static final long serialVersionUID = 7168319479760000150L;

	public static final int DIAGRAM_WIDTH = 1000;

	public static final int DIAGRAM_HEIGHT = 720;

	private DiagramMenu diagramMenu = new DiagramMenu(this);

	private DiagramToolBar diagramToolBar = new DiagramToolBar(this);

	private DiagramModel diagramModel;

	private DiagramSection diagramView;

	private AppFrame appFrame;

	public DiagramFrame(DiagramModel aDiagramModel, AppFrame anAppFrame) {
		super();
		diagramModel = aDiagramModel;
		diagramView = new DiagramSection(this, diagramModel);
		appFrame = anAppFrame;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		this.setJMenuBar(this.diagramMenu);

		String diagramExtensionName = "";
		String diagramModelExtensionName = diagramModel.getExtensionName();
		if (diagramModelExtensionName != null) {
			diagramExtensionName = diagramModelExtensionName;
		}
		if (diagramModel.isExtension()) {
			this.setTitle(diagramModel.getName() + " ==> "
					+ diagramExtensionName);
		} else {
			this.setTitle(diagramModel.getName() + " -- "
					+ diagramExtensionName);
		}

		this.setSize(DIAGRAM_WIDTH, DIAGRAM_HEIGHT);
		Container cp = this.getContentPane();
		cp.add(diagramToolBar, BorderLayout.NORTH);
		JScrollPane sp = new JScrollPane(diagramView);
		// when clicking on the scroll corners
		sp.getHorizontalScrollBar().setUnitIncrement(BoxModel.DEFAULT_WIDTH);
		sp.getVerticalScrollBar().setUnitIncrement(BoxModel.DEFAULT_HEIGHT);
		cp.add(sp, BorderLayout.CENTER);
	}

	public DiagramToolBar getDiagramToolBar() {
		return diagramToolBar;
	}

	public DiagramModel getDiagramModel() {
		return diagramModel;
	}

	public DiagramSection getDiagramView() {
		return diagramView;
	}

	public AppFrame getAppFrame() {
		return appFrame;
	}

	public void saveImage() {
		String defaultPath = AppModel.getSingleton().getAlias();
		File selectedFile = FileHandling.getSaveFile(defaultPath);
		if (selectedFile != null) {
			try {
				FileHandling.saveImageToJPEGFile(
						diagramView.getBufferedImage(), selectedFile, 0.80f);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void exit() {
		History.getSingleton().reset();
		diagramView.erase();
		this.dispose();
	}

}
