package org.modelibra.config;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.properties.CustomConfig;

public class ModelibraPropertiesTest {

	private static ModelibraProperties modelibraProperties;

	@BeforeClass
	public static void beforeTests() throws Exception {
		modelibraProperties = new ModelibraProperties(CustomConfig.class);
	}

	@Test
	public void getDb4oConfigFileName() {
		String db4oConfigFileName = modelibraProperties.getDb4oConfigFileName();
		assertEquals("db4o.xml", db4oConfigFileName);
	}

	@Test
	public void getClassDirectoryName() {
		String classDirectoryName = modelibraProperties.getClassDirectoryName();
		assertEquals("class", classDirectoryName);
	}

	@Test
	public void getConfigDirectoryName() {
		String configDirectoryName = modelibraProperties
				.getConfigDirectoryName();
		assertEquals("config", configDirectoryName);
	}

	@Test
	public void getEmailConfigFileName() {
		String emailConfigFileName = modelibraProperties
				.getEmailConfigFileName();
		assertEquals("email.xml", emailConfigFileName);
	}

	@Test
	public void getLogConfigFileName() {
		String logConfigFileName = modelibraProperties.getLogConfigFileName();
		assertEquals("log4j.xml", logConfigFileName);
	}

	@Test
	public void getModelibraDomainConfigFileName() {
		String modelibraDomainConfigFileName = modelibraProperties
				.getModelibraDomainConfigFileName();
		assertEquals("modelibra-domain-config.xml",
				modelibraDomainConfigFileName);
	}

	@Test
	public void getReusableDomainConfigFileName() {
		String reusableDomainConfigFileName = modelibraProperties
				.getReusableDomainConfigFileName();
		assertEquals("reusable-domain-config.xml", reusableDomainConfigFileName);
	}

	@Test
	public void getSpecificDomainConfigFileName() {
		String specificDomainConfigFileName = modelibraProperties
				.getSpecificDomainConfigFileName();
		assertEquals("specific-domain-config.xml", specificDomainConfigFileName);
	}

	@Test
	public void getAnchorDirectoryName() {
		String anchorDirectoryName = modelibraProperties
				.getAnchorDirectoryName();
		assertEquals("anchor", anchorDirectoryName);
	}

	@Test
	public void getSourceDirectoryName() {
		String sourceDirectoryName = modelibraProperties
				.getSourceDirectoryName();
		assertEquals("source", sourceDirectoryName);
	}

	@Test
	public void getTestDirectoryName() {
		String testDirectoryName = modelibraProperties.getTestDirectoryName();
		assertEquals("test", testDirectoryName);
	}

}
