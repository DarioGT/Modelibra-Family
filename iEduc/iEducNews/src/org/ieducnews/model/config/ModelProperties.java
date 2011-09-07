package org.ieducnews.model.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ModelProperties {

	public static final String MODEL_PROPERTIES_FILE_NAME = "model.properties";

	public static final String FILE_PATH_KEY = "file-path";

	private Properties properties;

	public ModelProperties(Class<?> claz) {
		properties = load(claz, MODEL_PROPERTIES_FILE_NAME);
	}

	public String getFilePath() {
		return properties.getProperty(FILE_PATH_KEY);
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
	private Properties load(Class<?> referenceClass, String fileName) {
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
