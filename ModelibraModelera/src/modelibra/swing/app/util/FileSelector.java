/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package modelibra.swing.app.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelibra.swing.app.config.NatLang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.util.Log4jConfigurator;

/**
 * Selects a file.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-29
 */
public class FileSelector {

	private static Log log = LogFactory.getLog(FileSelector.class);

	protected File lastOpenedFile = null;

	protected File lastSavedFile = null;

	protected String lastDirectoryPath = null;

	protected String lastFilePath = null;

	/**
	 * Gets the last opened file used.
	 * 
	 * @return last opened file
	 */
	public File getLastOpenedFile() {
		return lastOpenedFile;
	}

	/**
	 * Gets the last saved file used.
	 * 
	 * @return last saved file
	 */
	public File getLastSavedFile() {
		return lastSavedFile;
	}

	/**
	 * Gets the last directory path used.
	 * 
	 * @return the last directory (path)
	 */
	public String getLastDirectoryPath() {
		return lastDirectoryPath;
	}

	/**
	 * Gets the last file path used.
	 * 
	 * @return last file (path)
	 */
	public String getLastFilePath() {
		return lastFilePath;
	}

	/**
	 * Gets the opened file given a file path.
	 * 
	 * @param path
	 *            path
	 * @param lang
	 *            language
	 * @return file
	 */
	public File getOpenFile(String path, NatLang lang) {
		File selectedFile = null;
		File currentFile = null;
		if (path != null && !path.equalsIgnoreCase("?")) {
			currentFile = new File(path);
		} else
			currentFile = lastOpenedFile;

		JFileChooser chooser = new JFileChooser();
		chooser.setLocale(lang.getLocale());
		if (currentFile != null) {
			chooser.setCurrentDirectory(currentFile);
		}
		int returnVal = chooser.showOpenDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
		}
		lastOpenedFile = selectedFile;

		return selectedFile;
	}

	/**
	 * Gets the saved file given a file path.
	 * 
	 * @param path
	 *            path
	 * @param lang
	 *            language
	 * @return file
	 */
	public File getSaveFile(String path, NatLang lang) {
		File selectedFile = null;
		File currentFile = null;
		if (path != null && !path.equalsIgnoreCase("?")) {
			currentFile = new File(path);
		} else
			currentFile = lastSavedFile;

		JFileChooser chooser = new JFileChooser();
		chooser.setLocale(lang.getLocale());
		if (currentFile != null) {
			chooser.setCurrentDirectory(currentFile);
		}
		int returnVal = chooser.showSaveDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile(); // a complete path
			if (selectedFile.exists()) {
				if (!replaceFile(null, lang)) {
					selectedFile = null;
				}
			}
		}
		lastSavedFile = selectedFile;

		return selectedFile;
	}

	/**
	 * Prompts a user to decide if the file should be replaced: yes or no.
	 * 
	 * @param parentFrame
	 *            parent window
	 * @param lang
	 *            language
	 * @return <code>true</code> if a user chooses yes
	 */
	protected boolean replaceFile(JFrame parentFrame, NatLang lang) {
		// Custom button text
		Object[] options = { lang.getText("yes"), lang.getText("no") };
		JOptionPane.setDefaultLocale(lang.getLocale());
		int response = JOptionPane.showOptionDialog(parentFrame, // parent
				// frame
				lang.getText("replaceFile"), // message
				lang.getText("fileExists"), // title
				JOptionPane.YES_NO_OPTION, // option type
				JOptionPane.QUESTION_MESSAGE, // message type
				null, // don't use a custom icon
				options, // the titles of buttons
				options[0]); // default button title

		if (response == JOptionPane.NO_OPTION) {
			return false;
		}

		return true;
	}

	/**
	 * Selects a directory (path).
	 * 
	 * @param lang
	 *            language
	 * @return chosen directory path
	 */
	public String selectDirectory(NatLang lang) {
		JFileChooser dirChooser = new JFileChooser(lastDirectoryPath);
		dirChooser.setLocale(lang.getLocale());
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dirChooser.setToolTipText(lang.getText("selectDirectory"));
		dirChooser.setDialogTitle(lang.getText("selectDirectory"));
		dirChooser.showDialog(null, lang.getText("selectDirectory"));
		File f = dirChooser.getSelectedFile();
		if (f != null) {
			lastDirectoryPath = f.getPath();
			return lastDirectoryPath;
		} else {
			return null;
		}
	}

	/**
	 * Selects a file (path).
	 * 
	 * @param lang
	 *            language
	 * @return chosen file path
	 */
	public String selectFile(NatLang lang) {
		JFileChooser fileChooser = new JFileChooser(lastFilePath);
		fileChooser.setLocale(lang.getLocale());
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setToolTipText(lang.getText("selectFile"));
		fileChooser.setDialogTitle(lang.getText("selectFile"));
		fileChooser.showDialog(null, lang.getText("selectFile"));
		File f = fileChooser.getSelectedFile();
		if (f != null) {
			lastFilePath = f.getPath();
			return lastFilePath;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		NatLang lang = new NatLang();
		lang.setNaturalLanguage("ba");
		FileSelector fileSelector = new FileSelector();

		String dirPath = fileSelector.selectDirectory(lang);
		log.info("Dir: " + dirPath);

		String filePath = fileSelector.selectFile(lang);
		log.info("File: " + filePath);
	}

}
