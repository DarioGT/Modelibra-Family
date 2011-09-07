package modelibra.swing.app;

import java.util.Properties;

import javax.swing.JApplet;

import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metadomain.DomainsFrame;

import org.modelibra.util.PropertiesLoader;

/**
 * Start application or applet.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-26
 */
public class StartModelibraDesigner extends JApplet {

	public static final String APP_CONFIG_LOCAL_PATH = "StartModelibraDesigner.properties";

	private DomainsFrame domainsFrame;

	/**
	 * Default constructor.
	 */
	public StartModelibraDesigner() {
		super();
	}

	/**
	 * Inits the app or applet. Sets the natural language. Creates the
	 * application main window.
	 */
	public void init() {
		obtainLang();
		domainsFrame = new DomainsFrame();
		domainsFrame.setLocation(0, 0);
	}

	/**
	 * Starts the applet.
	 */
	public void start() {
		domainsFrame.setVisible(true);
	}

	/**
	 * Stops the applet.
	 */
	public void stop() {
		domainsFrame.setVisible(false);
	}

	/**
	 * Disposes the applet resources.
	 */
	public void destroy() {
		domainsFrame.dispose();
	}

	/**
	 * Sets the current natural language based on the start configuration
	 * (Start.properties).
	 */
	private String obtainLang() {
		Properties configurator = PropertiesLoader.load(
				StartModelibraDesigner.class,
				StartModelibraDesigner.APP_CONFIG_LOCAL_PATH);

		String language = configurator.getProperty("lang");
		String textResources = configurator.getProperty("textResources");

		Para.getOne().setNaturalLanguage(language, textResources);

		return language;
	}

	/**
	 * Application (and not applet) entry point.
	 * 
	 * @param args
	 *            args not used
	 */
	public static void main(String[] args) {
		StartModelibraDesigner app = new StartModelibraDesigner();
		app.init();
		app.start();
	}

}
