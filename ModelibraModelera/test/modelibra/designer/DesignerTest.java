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
package modelibra.designer;

import modelibra.ModelibraTest;
import modelibra.designer.metadomain.MetaDomains;
import modelibra.designer.metatype.MetaTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.util.OutTester;

/**
 * Designer model tests.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class DesignerTest {

	private static Log log = LogFactory.getLog(DesignerTest.class);

	private Designer designer;

	public DesignerTest() {
		super();
		try {
			begin();
		} catch (Exception e) {
			log.error("Error in DesignerTest(): " + e.getMessage());
		}
	}

	private void begin() throws Exception {
		designer = ModelibraTest.getSingleton().getModelibra().getDesigner();
	}

	public Designer getDesigner() {
		return designer;
	}

	private void end() {
		designer.close();
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	private void initDesigner() {
		initMetaDomains();
		initMetaTypes();
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private void initMetaDomains() {
		outputMessage("??????? initMetaDomains: creation of MetaDomains ???????");

		MetaDomains metaDomains = designer.getMetaDomains();

		outputErrors(metaDomains, "MetaDomain Add Errors");
	}

	private void initMetaTypes() {
		outputMessage("??????? initMetaTypes: creation of MetaTypes ???????");

		MetaTypes metaTypes = designer.getMetaTypes();

		outputErrors(metaTypes, "MetaType Add Errors");
	}

	private void test01() {
		outputMessage("??????? test01: test name ???????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		DesignerTest designerTest = null;
		try {
			designerTest = new DesignerTest();
			if (false) {
				designerTest.initDesigner();
			}
			designerTest.test();
		} catch (Exception e) {
			log.error("Error in DesignerTest.main: " + e.getMessage());
		} finally {
			designerTest.end();
		}
	}

}
