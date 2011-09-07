package education.swing.app;

import java.util.Properties;

import org.modelibra.config.DomainConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.app.MainFrame;
import org.modelibra.util.NatLang;
import org.modelibra.util.PropertiesLoader;

import education.Education;
import education.EducationConfig;
import education.PersistentEducation;

public class Start {

	public static final String APP_CONFIG_LOCAL_PATH = "Start.properties";

	public static final int MAIN_FRAME_X = 0;
	public static final int MAIN_FRAME_Y = 0;

	public Start() {
		EducationConfig educationConfig = new EducationConfig();
		DomainConfig domainConfig = educationConfig.getDomainConfig();
		Education education = new Education(domainConfig);
		new PersistentEducation(education);

		Properties configurator = PropertiesLoader.load(Start.class,
				APP_CONFIG_LOCAL_PATH);
		String language = configurator.getProperty("lang");
		String textResources = configurator.getProperty("textResources");
		NatLang natLang = new NatLang();
		natLang.setNaturalLanguage(language, textResources);

		App app = new App(education, natLang);
		MainFrame mainFrame = new MainFrame(app);
		mainFrame.setLocation(MAIN_FRAME_X, MAIN_FRAME_Y);
		mainFrame.setVisible(true);
	}

	/**
	 * Application entry (start) point.
	 * 
	 * @param args
	 *            args not used
	 */
	public static void main(String[] args) {
		new Start();
	}

}
