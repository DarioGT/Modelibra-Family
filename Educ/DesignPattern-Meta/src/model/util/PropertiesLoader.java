package model.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Utility class for loading properties from files.
 */
public class PropertiesLoader {

	/**
	 * Constructor private to disable instantiation of the class.
	 * 
	 */
	private PropertiesLoader() {
	}

	/**
	 * Loads properties from the properties file located relative to a given
	 * reference class location.
	 * 
	 * @param referenceClass
	 *            reference class
	 * @param fileName
	 *            properties file name
	 * @return properties
	 */
	public static Properties load(Class<?> referenceClass, String fileName) {
		Properties properties = new Properties();
		URL configUrl = referenceClass.getResource(fileName);
		if (configUrl != null) {
			try {
				InputStream inputStream = configUrl.openStream();
				properties.clear();
				properties.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return properties;
	}

}
