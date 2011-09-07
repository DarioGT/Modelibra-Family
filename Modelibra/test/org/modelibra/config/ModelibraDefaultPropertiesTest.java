package org.modelibra.config;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.defaultproperties.CustomConfig;

public class ModelibraDefaultPropertiesTest {

	private static ModelibraProperties modelibraProperties;

	@BeforeClass
	public static void beforeTests() throws Exception {
		modelibraProperties = new ModelibraProperties(CustomConfig.class);
	}

	@Test
	public void getDb4oConfigFileName() {
		String db4oConfigFileName = modelibraProperties.getDb4oConfigFileName();
		assertEquals(db4oConfigFileName,
				ModelibraProperties.DB4O_CONFIG_FILE_DEFAULT_NAME);
	}

	@Test
	public void getClassDirectoryName() {
		String classDirectoryName = modelibraProperties.getClassDirectoryName();
		assertEquals(ModelibraProperties.CLASS_DIRECTORY_DEFAULT_NAME,
				classDirectoryName);
	}

	@Test
	public void getConfigDirectoryName() {
		String configDirectoryName = modelibraProperties
				.getConfigDirectoryName();
		assertEquals(ModelibraProperties.CONFIG_DIRECTORY_DEFAULT_NAME,
				configDirectoryName);
	}

	@Test
	public void getEmailConfigFileName() {
		String emailConfigFileName = modelibraProperties
				.getEmailConfigFileName();
		assertEquals(ModelibraProperties.EMAIL_CONFIG_FILE_DEFAULT_NAME,
				emailConfigFileName);
	}

	@Test
	public void getLogConfigFileName() {
		String logConfigFileName = modelibraProperties.getLogConfigFileName();
		assertEquals(ModelibraProperties.LOG_CONFIG_FILE_DEFAULT_NAME,
				logConfigFileName);
	}

	@Test
	public void getModelibraDomainConfigFileName() {
		String modelibraDomainConfigFileName = modelibraProperties
				.getModelibraDomainConfigFileName();
		assertEquals(
				ModelibraProperties.MODELIBRA_DOMAIN_CONFIG_FILE_DEFAULT_NAME,
				modelibraDomainConfigFileName);
	}

	@Test
	public void getReusableDomainConfigFileName() {
		String reusableDomainConfigFileName = modelibraProperties
				.getReusableDomainConfigFileName();
		assertEquals(
				ModelibraProperties.REUSABLE_DOMAIN_CONFIG_FILE_DEFAULT_NAME,
				reusableDomainConfigFileName);
	}

	@Test
	public void getSpecificDomainConfigFileName() {
		String specificDomainConfigFileName = modelibraProperties
				.getSpecificDomainConfigFileName();
		assertEquals(
				ModelibraProperties.SPECIFIC_DOMAIN_CONFIG_FILE_DEFAULT_NAME,
				specificDomainConfigFileName);
	}

	@Test
	public void getAnchorDirectoryName() {
		String anchorDirectoryName = modelibraProperties
				.getAnchorDirectoryName();
		assertEquals(ModelibraProperties.ANCHOR_DIRECTORY_DEFAULT_NAME,
				anchorDirectoryName);
	}

	@Test
	public void getSourceDirectoryName() {
		String sourceDirectoryName = modelibraProperties
				.getSourceDirectoryName();
		assertEquals(ModelibraProperties.SOURCE_DIRECTORY_DEFAULT_NAME,
				sourceDirectoryName);
	}

	@Test
	public void getTestDirectoryName() {
		String testDirectoryName = modelibraProperties.getTestDirectoryName();
		assertEquals(ModelibraProperties.TEST_DIRECTORY_DEFAULT_NAME,
				testDirectoryName);
	}
}
