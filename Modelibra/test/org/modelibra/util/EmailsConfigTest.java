package org.modelibra.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmailsConfigTest {

	private static String emailConfigFilePath;

	@BeforeClass
	public static void beforeTests() {		
		emailConfigFilePath = new File(EmailsConfigTest.class.getResource(
		"email-config.xml").getPath()).getAbsolutePath();
	}
	
	@Test
	public void load() throws Exception {
		EmailsConfig emailsConfig = new EmailsConfig(emailConfigFilePath);
		EmailConfig emailConfig = emailsConfig.getFirstEmailConfig();
		String code = emailConfig.getCode();
		assertEquals("vlgiiora", code);
	}

}
