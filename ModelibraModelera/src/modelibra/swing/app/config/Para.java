package modelibra.swing.app.config;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ImageIcon;

import modelibra.swing.app.StartModelibraDesigner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.PathLocator;
import org.modelibra.util.PropertiesLoader;

/**
 * Utility class.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-07
 */
public class Para {

	private static Log log = LogFactory.getLog(Para.class);

	public static final Color COLOR1 = Color.ORANGE;

	public static final Color COLOR2 = Color.YELLOW;

	public static final Color BORDER_COLOR = Color.BLACK;

	private NatLang lang;

	private PathLocator fileLocator;

	private static Para para;

	/**
	 * Private constructor for Singleton pattern.
	 */
	private Para() {
		lang = new NatLang();
		fileLocator = new PathLocator();
	}

	/**
	 * Gets the singleton object.
	 * 
	 * @return the singleton object
	 */
	public static Para getOne() {
		if (para == null) {
			para = new Para();
		}
		return para;
	}
	
	public NatLang getNatLang() {
		return lang;
	}

	/**
	 * Gets the current locale (language).
	 * 
	 * @return the current locale
	 */
	public Locale getLocale() {
		return lang.getLocale();
	}

	/**
	 * Gets the text based on the given key for the current language.
	 * 
	 * @param key
	 *            key text
	 * @return current language text
	 */
	public String getText(String key) {
		return lang.getText(key);
	}

	/**
	 * Sets the language with the location for text resources.
	 * 
	 * @param language
	 *            language
	 * @param textResources
	 *            text resources
	 */
	public void setNaturalLanguage(String language, String textResources) {
		lang.setNaturalLanguage(language, textResources);
	}

	/**
	 * Gets the file URL given the file local name.
	 * 
	 * @param localName
	 *            a file local name
	 * @return the file URL
	 */
	public URL getUrl(String localName) {
		return fileLocator.getClassBasedUrl(getClass(), localName);
	}

	/**
	 * Gets the file path given the file local name.
	 * 
	 * @param localName
	 *            a file local name
	 * @return the file path
	 */
	public String getPath(String localName) {
		return fileLocator.getClassBasedPath(getClass(), localName);
	}

	/**
	 * Gets the image given the image local name.
	 * 
	 * @param localName
	 *            an image local name
	 * @return the image icon
	 */
	public ImageIcon getImageIcon(String localName) {
		return fileLocator.getImageIcon(getClass(), localName);
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		Properties configurator = PropertiesLoader.load(StartModelibraDesigner.class,
				StartModelibraDesigner.APP_CONFIG_LOCAL_PATH);

		String language = configurator.getProperty("lang");
		String textResources = configurator.getProperty("textResources");

		Para para = Para.getOne();
		para.setNaturalLanguage(language, textResources);

		log.info("Language: " + para.getLocale().getLanguage());
	}

}
