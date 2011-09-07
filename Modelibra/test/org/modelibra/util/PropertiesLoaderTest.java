package org.modelibra.util;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

public class PropertiesLoaderTest {

	@Test
	public void load() throws Exception {
		Properties properties = PropertiesLoader.load(this.getClass(),
				"loaded.properties");
		String property = properties.getProperty("test");
		assertEquals(property, "test");
	}

	@Test
	public void loadFromFileRelativeToPropertiesLoader() throws Exception {
		Properties properties = PropertiesLoader.load(PropertiesLoader.class,
				"TextRes.properties");
		String property = properties.getProperty("replaceFile");
		assertEquals(property, "Replace file?");
	}

	@Test
	public void reload() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("test", "old");
		PropertiesLoader.load(this.getClass(), "loaded.properties", properties);
		String property = properties.getProperty("test");
		assertEquals(property, "test");
	}

	@Test
	public void reloadFromFileRelativeToPropertiesLoader() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("replaceFile", "old");
		PropertiesLoader.load(PropertiesLoader.class, "TextRes.properties",
				properties);
		String property = properties.getProperty("replaceFile");
		assertEquals(property, "Replace file?");
	}
}
