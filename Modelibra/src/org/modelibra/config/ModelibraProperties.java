package org.modelibra.config;

import java.util.Properties;

import org.modelibra.util.PropertiesLoader;

/*
 * Modelibra properties determine the names of directories and configuration files important for Modelibra projects. 
 * Default names (convention over configuration) are used if the modelibra.properties file does not 
 * exist in the same directory where the domain configuration class, which inherits 
 * org.modelibra.config.Config class, is located. If the file exits it must have 
 * the modelibra.properties name and it must use the keys that are constants in this class.
 * The names of directories and files may change but those directories and files must exist.
 */
public class ModelibraProperties {

	private static final long serialVersionUID = 1L;

	public static final String MODELIBRA_PROPERTIES_FILE_NAME = "modelibra.properties";

	public static final String DB4O_CONFIG_FILE_KEY = "db4o";

	public static final String DB4O_CONFIG_FILE_DEFAULT_NAME = "db4o-config.xml";

	public static final String CLASS_DIRECTORY_KEY = "class";

	public static final String CLASS_DIRECTORY_DEFAULT_NAME = "classes";

	public static final String CONFIG_DIRECTORY_KEY = "config";

	public static final String CONFIG_DIRECTORY_DEFAULT_NAME = "config";

	public static final String EMAIL_CONFIG_FILE_KEY = "email";

	public static final String EMAIL_CONFIG_FILE_DEFAULT_NAME = "email-config.xml";

	public static final String LOG_CONFIG_FILE_KEY = "log";

	public static final String LOG_CONFIG_FILE_DEFAULT_NAME = "log4j.xml";

	public static final String MODELIBRA_DOMAIN_CONFIG_KEY = "modelibra";

	public static final String MODELIBRA_DOMAIN_CONFIG_FILE_DEFAULT_NAME = "modelibra-domain-config.xml";

	public static final String REUSABLE_DOMAIN_CONFIG_KEY = "reusable";

	public static final String REUSABLE_DOMAIN_CONFIG_FILE_DEFAULT_NAME = "reusable-domain-config.xml";

	public static final String SPECIFIC_DOMAIN_CONFIG_KEY = "specific";

	public static final String SPECIFIC_DOMAIN_CONFIG_FILE_DEFAULT_NAME = "specific-domain-config.xml";

	// For code generation.

	public static final String ANCHOR_DIRECTORY_KEY = "anchor";

	public static final String ANCHOR_DIRECTORY_DEFAULT_NAME = "WEB-INF";

	public static final String SOURCE_DIRECTORY_KEY = "source";

	public static final String SOURCE_DIRECTORY_DEFAULT_NAME = "src";

	public static final String TEST_DIRECTORY_KEY = "test";

	public static final String TEST_DIRECTORY_DEFAULT_NAME = "test";

	private Properties properties;

	private Class<?> referenceClass;

	public ModelibraProperties(Class<?> claz) {
		this(claz, MODELIBRA_PROPERTIES_FILE_NAME);
	}

	public ModelibraProperties(Class<?> claz, String propertiesFileName) {
		referenceClass = claz;
		properties = PropertiesLoader.load(claz, propertiesFileName);
	}

	public ModelibraProperties(Class<?> claz, Properties properties) {
		referenceClass = claz;
		this.properties = properties;
	}

	public Class<?> getReferenceClass() {
		return referenceClass;
	}

	public String getDb4oConfigFileName() {
		return properties.getProperty(DB4O_CONFIG_FILE_KEY,
				DB4O_CONFIG_FILE_DEFAULT_NAME);
	}

	public String getClassDirectoryName() {
		return properties.getProperty(CLASS_DIRECTORY_KEY,
				CLASS_DIRECTORY_DEFAULT_NAME);
	}

	public String getConfigDirectoryName() {
		return properties.getProperty(CONFIG_DIRECTORY_KEY,
				CONFIG_DIRECTORY_DEFAULT_NAME);
	}

	public String getEmailConfigFileName() {
		return properties.getProperty(EMAIL_CONFIG_FILE_KEY,
				EMAIL_CONFIG_FILE_DEFAULT_NAME);
	}

	public String getLogConfigFileName() {
		return properties.getProperty(LOG_CONFIG_FILE_KEY,
				LOG_CONFIG_FILE_DEFAULT_NAME);
	}

	public String getModelibraDomainConfigFileName() {
		return properties.getProperty(MODELIBRA_DOMAIN_CONFIG_KEY,
				MODELIBRA_DOMAIN_CONFIG_FILE_DEFAULT_NAME);
	}

	public String getReusableDomainConfigFileName() {
		return properties.getProperty(REUSABLE_DOMAIN_CONFIG_KEY,
				REUSABLE_DOMAIN_CONFIG_FILE_DEFAULT_NAME);
	}

	public String getSpecificDomainConfigFileName() {
		return properties.getProperty(SPECIFIC_DOMAIN_CONFIG_KEY,
				SPECIFIC_DOMAIN_CONFIG_FILE_DEFAULT_NAME);
	}

	// For code generation.
	public String getAnchorDirectoryName() {
		return properties.getProperty(ANCHOR_DIRECTORY_KEY,
				ANCHOR_DIRECTORY_DEFAULT_NAME);
	}

	public String getSourceDirectoryName() {
		return properties.getProperty(SOURCE_DIRECTORY_KEY,
				SOURCE_DIRECTORY_DEFAULT_NAME);
	}

	public String getTestDirectoryName() {
		return properties.getProperty(TEST_DIRECTORY_KEY,
				TEST_DIRECTORY_DEFAULT_NAME);
	}

}
