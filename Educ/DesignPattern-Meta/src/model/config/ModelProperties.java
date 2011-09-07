package model.config;

import java.util.Properties;

import model.util.PropertiesLoader;

public class ModelProperties {

	public static final String MODEL_PROPERTIES_FILE_NAME = "model.properties";

	public static final String FILE_PATH_KEY = "file-path";

	private Properties properties;

	public ModelProperties(Class<?> claz) {
		properties = PropertiesLoader.load(claz, MODEL_PROPERTIES_FILE_NAME);
	}

	public String getFilePath() {
		return properties.getProperty(FILE_PATH_KEY);
	}

}
