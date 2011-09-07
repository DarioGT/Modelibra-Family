package org.modelibra.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.util.EmailConfig;

/**
 * Tests for org.modelibra.config.Config class. All tests are against that class
 * using properties from org/modelibra/config/modelibra.properties file. Testing
 * other configuration scenarios should be done against ModelibraProperties
 * class.
 * 
 * @author Vedad Kirlic
 * 
 */
public class ConfigTest {

	private static Config config;

	@BeforeClass
	public static void beforeTests() throws Exception {
		config = new Config();
	}

	@Test
	public void getModelibraProperties() throws Exception {
		ModelibraProperties modelibraProperties = config
				.getModelibraProperties();
		assertNotNull(modelibraProperties);
	}

	@Test
	public void getDomainsConfig() throws Exception {
		DomainsConfig domainsConfig = config.getDomainsConfig();
		assertNotNull(domainsConfig);
	}

	@Test
	public void getDomainConfig() throws Exception {
		DomainConfig domainConfig = config.getDomainConfig("Dm", "Modelibra");
		assertNotNull(domainConfig);
	}

	@Test
	public void getEmailConfig() throws Exception {
		EmailConfig emailConfig = config.getEmailConfig();
		assertNotNull(emailConfig);
	}

	@Test
	public void getSpecificDomainConfigFilePath() throws Exception {
		String specificDomainConfigFileName = config.getModelibraProperties()
				.getSpecificDomainConfigFileName();
		String specificDomainConfigFilePath = config
				.getSpecificDomainConfigFilePath();
		String expectedPath = getClassesParentFolderPath() + "/config/"
				+ specificDomainConfigFileName;
		assertEquals(new File(expectedPath), new File(
				specificDomainConfigFilePath));
	}

	@Test
	public void getDb4oConfigFilePath() throws Exception {
		String db4oConfigFilePath = config.getDb4oConfigFilePath();
		String expectedPath = getClassesParentFolderPath()
				+ "/config/db4o-config.xml";
		assertEquals(new File(expectedPath), new File(db4oConfigFilePath));
	}

	@Test
	public void getJdbcConfigFilePath() throws Exception {
		ModelConfig modelConfigStub = new ModelConfig() {
			@Override
			public String getPersistenceConfig() {
				return "jdbc-config.xml";
			}
		};

		String jdbcConfigFilePath = config
				.getJdbcConfigFilePath(modelConfigStub);
		String expectedPath = getClassesParentFolderPath()
				+ "/config/jdbc-config.xml";
		assertEquals(new File(expectedPath), new File(jdbcConfigFilePath));
	}

	@Test
	public void getDataDirectoryPath() throws Exception {
		final String data = "xml/dmeduc/weblink";

		ModelConfig modelConfigStub = new ModelConfig() {
			@Override
			public String getPersistenceRelativePath() {
				return data;
			}
		};

		String dataDirectoryPath = config.getDataDirectoryPath(modelConfigStub);
		String expectedPath = getClassesParentFolderPath() + "/" + data;
		assertEquals(new File(expectedPath), new File(dataDirectoryPath));
	}

	@Test
	public void getTestDirectoryPath() throws Exception {
		String testDirectoryPath = config.getTestDirectoryPath();
		String expectedPath = getClassesParentFolderPath() + "/test";
		assertEquals(new File(expectedPath), new File(testDirectoryPath));
	}

	@Test
	public void getSourceDirectoryPath() throws Exception {
		String sourceDirectoryPath = config.getSourceDirectoryPath();
		String expectedPath = getClassesParentFolderPath() + "/src";
		assertEquals(new File(expectedPath), new File(sourceDirectoryPath));
	}

	@Test
	public void getWebInfDirectoryPath() throws Exception {
		String webInfDirectoryPath = config.getWebInfDirectoryPath();
		String expectedPath = getClassesParentFolderPath() + "/WEB-INF";
		assertEquals(new File(expectedPath), new File(webInfDirectoryPath));
	}

	// Helper methods
	/**
	 * Gets classes parent folder absoulte path. i.e if this project is in
	 * 'C:/workspace/' and classes are compiled into 'classes' folder, then it
	 * returns 'C:/workspace/Modelibra'
	 * 
	 * @return classes folder absolute path
	 */
	private String getClassesParentFolderPath() {
		return removeLastFolderFromPath(getClassesFolderPath());
	}

	/**
	 * Gets classes folder absoulte path. i.e if this project is in
	 * 'C:/workspace/Project' and classes are compiled into 'classes' folder, then it
	 * returns 'C:/workspace/Project/classes/'
	 * 
	 * @return classes folder absolute path
	 */
	private String getClassesFolderPath() {
		URL resource = ConfigTest.class.getResource("");
		String packageAbsolutePath = resource.getPath();

		String packageRelativePath = ConfigTest.class.getPackage().getName()
				.replace('.', '/');

		int index = packageAbsolutePath.lastIndexOf(packageRelativePath);
		return packageAbsolutePath.substring(0, index);
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private String removeLastFolderFromPath(String path) {
		int index = path.lastIndexOf("/");
		if (index == path.length() - 1) {
			return removeLastFolderFromPath(path.substring(0, index));
		}
		return path.substring(0, index);
	}

	@Test
	public void testHelperMethods() throws Exception {
		String path;
		String result;

		path = "C:/workspace/Modelibra/classes";
		result = removeLastFolderFromPath(path);
		assertEquals("C:/workspace/Modelibra", result);

		path = "C:/workspace/Modelibra/classes/";
		result = removeLastFolderFromPath(path);
		assertEquals("C:/workspace/Modelibra", result);
	}
}
