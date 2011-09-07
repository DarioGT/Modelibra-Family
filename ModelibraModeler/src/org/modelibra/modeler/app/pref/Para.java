package org.modelibra.modeler.app.pref;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-06-29
 */
public class Para {

	public static final String TEXT_RESOURCES = "org.modelibra.modeler.app.pref.StringRes";

	public static final Color BACKGROUND_COLOR = new Color(255, 204, 102);

	private static Para para;

	private static Locale locale;

	private static ResourceBundle resource;

	private Para() {
		super();
		this.setNaturalLanguage("en"); // English is default
	}

	public static Para getPara() {
		if (para == null) {
			para = new Para();
		}
		return para;
	}

	public URL getUrlSiblingToClassFile(String localName) {
		URL url = null;
		try {
			Class claz = this.getClass();
			url = claz.getResource(localName);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " // no URL");
		}
		return url;
	}

	public String getPathSiblingToClassFile(String localName) {
		String path = null;
		URL url = this.getUrlSiblingToClassFile(localName);
		if (url != null) {
			path = url.getFile();
			System.out.println(path);
		}
		return path;
	}

	public URL getUrlChildOfClassesDir(String localName) {
		URL url = null;
		try {
			Class claz = this.getClass();
			ClassLoader classLoader = claz.getClassLoader();
			url = classLoader.getResource(localName);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " // no URL");
		}
		return url;
	}

	public String getPathChildOfClassesDir(String localName) {
		String path = null;
		URL url = this.getUrlChildOfClassesDir(localName);
		if (url != null) {
			path = url.getFile();
			System.out.println(path);
		}
		return path;
	}

	public String getText(String key) {
		String lookFor = null;
		try {
			lookFor = resource.getString(key);
		} catch (MissingResourceException e) {
			System.out.println(e.getMessage() + " // Missing string: " + key);
			lookFor = "Missing string: " + key;
		}
		return lookFor;
	}

	public ImageIcon getImageIconSiblingToClassFile(String anImage) {
		ImageIcon imageIcon = null;
		URL imageUrl = this.getUrlSiblingToClassFile(anImage);
		if (imageUrl != null) {
			imageIcon = new ImageIcon(imageUrl);
		}
		return imageIcon;
	}

	public void setNaturalLanguage(String aLang) {
		try {
			if ((aLang == null) || (aLang.equals("en"))) {
				locale = Locale.ENGLISH;
			} else if (aLang.equals("fr")) {
				locale = Locale.FRENCH;
			} else if (aLang.equals("ba")) {
				locale = new Locale("ba"); // bosnian language
			} else { // other languages are not supported, English used
				locale = Locale.ENGLISH;
			}
			resource = ResourceBundle.getBundle(TEXT_RESOURCES, locale);
		} catch (MissingResourceException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage()
					+ " // Problem with english or french!");
		}
	}

}
