package org.modelibra.modeler.gen.modelibra.config;

import java.io.File;

import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.util.FileHandling;

public abstract class ModelibraConfigGenerator {

	protected AppModel appModel;

	public ModelibraConfigGenerator(AppModel appModel) {
		this.appModel = appModel;
	}

	/**
	 * Generates the model configuration.
	 */
	public void generateConfig() {
		File file = getConfigFile();
		if (file == null) {
			return;
		}
		generateConfig(file, "template/Config.vm");
	}

	/**
	 * Generates the model minimal configuration.
	 */
	public void generateMinConfig() {
		File file = getConfigFile();
		if (file == null) {
			return;
		}
		generateConfig(file, "template/MinConfig.vm");
	}

	/**
	 * Gets the configuration file.
	 * 
	 * @return configuration file
	 */
	protected File getConfigFile() {
		String defaultPath = AppModel.getSingleton().getAlias();
		File file = FileHandling.getSaveFile(defaultPath);
		return file;
	}

	/**
	 * Generates the configuration.
	 * 
	 * @param configFile
	 *            config file
	 * @param velocityTemplate
	 *            velocity template
	 */
	protected abstract void generateConfig(File configFile,
			String velocityTemplate);

	/**
	 * Informs a user.
	 * 
	 * @param msg
	 *            message
	 */
	protected void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

}
