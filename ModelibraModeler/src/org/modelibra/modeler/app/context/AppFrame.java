package org.modelibra.modeler.app.context;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.app.view.AppSection;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.util.FileDrop;
import org.modelibra.modeler.util.FileHandling;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-08
 */
public class AppFrame extends JFrame {

	static final long serialVersionUID = 7168319479760000030L;

	private AppModel appModel = AppModel.getSingleton();

	private File selectedFile;

	private AppMenu menu = new AppMenu(this);

	private AppSection app = new AppSection(this);

	public AppFrame() {
		super();
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.pack();
	}

	private void init() throws Exception {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		this.setJMenuBar(this.menu);
		this.setTitle(Para.getPara().getText("appTitle") + " --> ?");
		this.getContentPane().add(app);

		// drag and drop files
		new FileDrop(this, new FileDrop.Listener() {
			public void filesDropped(File[] files) {
				for (int i = 0; i < files.length; i++) {
					File file = (File) files[i];
					open(file);
				}
			} // end filesDropped
		}); // end FileDrop.Listener
	}

	public AppSection getApp() {
		return app;
	}

	public void neww() {
		this.close();
		this.saveAs();
	}

	private void open(File file) {
		try {
			BufferedInputStream inputFile = new BufferedInputStream(
					new FileInputStream(file));
			Manager.getSingleton().startTransaction("open file"); // Transaction
			appModel.readFile(inputFile);
			appModel = AppModel.getSingleton();
			app.setModel(appModel);
			appModel.setAlias(file.getAbsolutePath()); // to invoke update on
			// observers
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
			this.setTitle(Para.getPara().getText("appTitle") + " --> "
					+ file.getName());
			inputFile.close();
			History.getSingleton().reset();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void open() {
		this.close();
		String defaultPath = AppModel.getSingleton().getAlias();
		if (defaultPath.equals("?")) {
			Config config = Config.getConfig();
			defaultPath = config.getProperty("anchor");
		}
		selectedFile = FileHandling.getOpenFile(defaultPath);
		if (selectedFile == null) {
			return;
		}
		open(selectedFile);
	}

	public void close() {
		if ((selectedFile != null)
				&& (FileHandling.fileToBeSaved(this, "saveFile", "save"))) {
			this.save();
		}
		Manager.getSingleton().startTransaction("close file"); // Transaction
		appModel.removeAllDiagrams();
		appModel.removeAllTypes();
		appModel.setName(EntityModel.DEFAULT_NAME);

		Manager.getSingleton().commit(); // Transaction
		// ------------------------------
		this.setTitle(Para.getPara().getText("appTitle") + " --> ?");
		app.deinstallObservers();
		app.installObservers();
		History.getSingleton().reset();

		selectedFile = null;
	}

	public void save() {
		if (selectedFile == null) {
			this.saveAs();
		} else {
			try {
				BufferedOutputStream outputFile = new BufferedOutputStream(
						new FileOutputStream(selectedFile));
				appModel.writeFile(outputFile);
				outputFile.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void saveAs() {
		if (selectedFile != null) {
			this.save();
		}
		String defaultPath = AppModel.getSingleton().getAlias();
		selectedFile = FileHandling.getSaveFile(defaultPath);
		if (selectedFile != null) {
			Manager.getSingleton().startTransaction("save file as"); // Transaction
			appModel.setAlias(selectedFile.getAbsolutePath());
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------
			this.setTitle(Para.getPara().getText("appTitle") + " --> "
					+ selectedFile.getName());
			this.save();
			History.getSingleton().reset();
		}
	}

	public void exit() {
		if ((selectedFile != null)
				&& (FileHandling.fileToBeSaved(this, "saveFile", "save"))) {
			this.save();
		}
		this.dispose();
		if (!Start.applet) {
			System.exit(0);
		}
	}

}
