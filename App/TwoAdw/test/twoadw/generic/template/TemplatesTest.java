/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package twoadw.generic.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.type.EasyDate;

import twoadw.TwoadwTest;
import twoadw.reference.securityrole.SecurityRole;

/**
 * JUnit tests for Templates.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class TemplatesTest {

	private static Templates templates;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		templates = TwoadwTest.getSingleton().getTwoadw().getGeneric().getTemplates();
	}

	@Before
	public void beforeTest() throws Exception {
		templates.getErrors().empty();
	}

	@Test
	public void templateCreated() throws Exception {
		Template template01 = templates.createTemplate("Classic0.1", "path");
		Template template02 = templates.createTemplate("Classic1.0", "path");

		assertTrue(templates.contain(template01));
		assertTrue(templates.contain(template02));
		assertTrue(templates.getErrors().isEmpty());
	}
	
	@Test
	public void templateEquality() throws Exception {
		Template template = templates.createTemplate("Classic0.1", "path");
		Template templateCopy = template.copy();
		
		assertEquals(template,templateCopy);
		assertNotSame(template,templateCopy);
		assertTrue(templates.getErrors().isEmpty());
	}
	
	@Test
	public void templateUpdate() throws Exception {
		Template template = templates.createTemplate("Classic0.1", "path");
		Template templateCopy = template.copy();
		templateCopy.setName("bbg0.1");
		templateCopy.setDirectory("dir");
		
		assertTrue(template.equals(templateCopy));
		assertTrue(template.equalOid(templateCopy));
		assertFalse(template.equalProperties(templateCopy));
	
		templates.update(template, templateCopy);
		assertTrue(templates.getErrors().isEmpty());
	}


	
	//Template.id.unique=Template identifier ([name] []) is not unique.
	@Test
	public void idUnique() throws Exception {
		Template template = templates.createTemplate("Classic0.1", "path");
		Template templateNotUnique = templates.createTemplate("Classic0.1", "path");

		assertFalse(templates.contain(templateNotUnique));
		assertNotNull(templates.getErrors()
				.getError("Template.id.unique"));
	}
	
	//Template.name.required=Name is required.
	@Test
	public void nameRequired() throws Exception {
		Template template = templates.createTemplate(null, "path");

		assertFalse(templates.contain(template));
		assertNotNull(templates.getErrors().getError(
				"Template.name.required"));
	}
	
	
	//Template.name.length=Name is longer than 64.
	@Test
	public void nameLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=64) {
			stringlength = stringlength + "1"; 
		}	
		
		Template template = templates.createTemplate(stringlength, "path");

		assertFalse(templates.contain(template));
		assertNotNull(templates.getErrors().getError(
				"Template.name.length"));
	}
	
	//Template.directory.required=Directory is required.
	@Test
	public void directoryRequiredDefault() throws Exception {
		String defaultString = "css-specific/";
		Template template = templates.createTemplate("name", null);

		assertTrue(template.getDirectory().equals(defaultString));
		assertTrue(templates.getErrors().isEmpty());
	}
	
	
	//Template.directory.length=Directory is longer than 128.
	@Test
	public void directoryLength() throws Exception {
		//Data type Code = 128 char
		String stringlength = "";
		while (stringlength.length() <=128) {
			stringlength = stringlength + "1"; 
		}	
		
		Template template = templates.createTemplate("name", stringlength);

		assertFalse(templates.contain(template));
		assertNotNull(templates.getErrors().getError(
				"Template.directory.length"));
	}
	
	@After
	public void afterTest() throws Exception {
		for (Template template : templates.getList()) {
			templates.remove(template);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}