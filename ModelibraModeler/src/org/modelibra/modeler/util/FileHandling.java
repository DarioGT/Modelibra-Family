package org.modelibra.modeler.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-02
 */
public class FileHandling {

	private static File lastOpenedFile = null;

	private static File lastSavedFile = null;

	private static String lastDirectory = null;

	public static File getOpenFile(String aPath) {
		File selectedFile = null;
		File currentFile = null;
		if (aPath != null && !aPath.equalsIgnoreCase("?")) {
			currentFile = new File(aPath);
		} else
			currentFile = lastOpenedFile;

		JFileChooser chooser = new JFileChooser();
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

	public static File getSaveFile(String aPath) {
		File selectedFile = null;
		File currentFile = null;
		if (aPath != null && !aPath.equalsIgnoreCase("?")) {
			currentFile = new File(aPath);
		} else
			currentFile = lastSavedFile;

		JFileChooser chooser = new JFileChooser();
		if (currentFile != null) {
			chooser.setCurrentDirectory(currentFile);
		}
		int returnVal = chooser.showSaveDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile(); // a complete path
			if (selectedFile.exists()) {
				if (!FileHandling.fileToBeSaved(null, "replaceFile",
						"fileExists")) {
					selectedFile = null;
				}
			}
		}
		lastSavedFile = selectedFile;

		return selectedFile;
	}

	public static boolean fileToBeSaved(JFrame parentFrame, String message,
			String title) {
		// Custom button text
		Object[] options = { Para.getPara().getText("yes"),
				Para.getPara().getText("no") };

		int response = JOptionPane.showOptionDialog(parentFrame, // parent
				// frame
				Para.getPara().getText(message), // message
				Para.getPara().getText(title), // title
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

	public static String selectDirectory() {
		if (lastDirectory == null) {
			if (lastOpenedFile != null) {
				lastDirectory = lastOpenedFile.getPath();
			} else {
				Config config = Config.getConfig();
				lastDirectory = config.getProperty("anchor");
			}
		}
		JFileChooser dirChooser = new JFileChooser(lastDirectory);
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dirChooser.setToolTipText(Para.getPara().getText("selectDirectory"));
		dirChooser.showDialog(null, Para.getPara().getText("selectDirectory"));
		File f = dirChooser.getSelectedFile();
		if (f != null) {
			lastDirectory = f.getPath();
			return lastDirectory;
		} else {
			return null;
		}
	}

	public static void saveImageToJPEGFile(BufferedImage bi, File file,
			float quality) throws IOException {

		FileOutputStream fos = new FileOutputStream(file);
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(bi);
		param.setQuality(quality, false);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos, param);
		encoder.encode(bi);
		fos.close();
	}

	public static void saveImageToJPEGFile14(BufferedImage bi, File file,
			float quality) throws IOException {

		JPEGImageWriteParam param = new JPEGImageWriteParam(null);
		ImageTypeSpecifier type = new ImageTypeSpecifier(bi);
		param.setDestinationType(type);
		ImageOutputStream imgos = ImageIO.createImageOutputStream(file);
		Iterator writers = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) writers.next();
		writer.setOutput(imgos);
		writer.write(bi);
		imgos.close();
	}

}
