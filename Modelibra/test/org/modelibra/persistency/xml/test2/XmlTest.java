package org.modelibra.persistency.xml.test2;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.config.DomainConfig;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.xml.PersistentXml;
import org.modelibra.persistency.xml.Xml;

public class XmlTest {

	private static DomainConfig domainConfig;

	@BeforeClass
	public static void beforeTests() throws Exception {
		XmlConfig xmlConfig = new XmlConfig();
		domainConfig = xmlConfig.getDomainConfig();
	}

	@Test
	public void xmlDomain() throws Exception {
		Xml xmlDomain = new Xml(domainConfig);
		assertNotNull(xmlDomain);
	}

	@Test(expected = PersistencyRuntimeException.class)
	public void persistentXmlDomain() throws Exception {
		Xml xmlDomain = new Xml(domainConfig);
		new PersistentXml(xmlDomain);
	}

}
