package org.modelibra.config.woutxml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.PropertyConfig;

public class WoutXmlConfigTest {

	private static WoutXmlConfig woutXmlConfig;

	@BeforeClass
	public static void beforeTests() throws Exception {
		woutXmlConfig = new WoutXmlConfig();
	}

	@Test
	public void getDomainConfig() throws Exception {
		DomainConfig domainConfig = woutXmlConfig.getDomainConfig();
		assertNotNull(domainConfig);
	}

	@Test
	public void getModelConfig() throws Exception {
		DomainConfig domainConfig = woutXmlConfig.getDomainConfig();
		ModelConfig modelConfig = domainConfig.getModelConfig("WebLink");
		assertNotNull(modelConfig);
	}

	@Test
	public void getConceptConfig() throws Exception {
		DomainConfig domainConfig = woutXmlConfig.getDomainConfig();
		ModelConfig modelConfig = domainConfig.getModelConfig("WebLink");
		ConceptConfig conceptConfig = modelConfig.getConceptConfig("Url");
		assertNotNull(conceptConfig);
	}

	@Test
	public void getPropertyConfig() throws Exception {
		DomainConfig domainConfig = woutXmlConfig.getDomainConfig();
		ModelConfig modelConfig = domainConfig.getModelConfig("WebLink");
		ConceptConfig conceptConfig = modelConfig.getConceptConfig("Url");
		PropertyConfig propertyConfig = conceptConfig.getPropertyConfig("link");
		assertNotNull(propertyConfig);
		assertTrue(propertyConfig.isRequired());
	}

}
