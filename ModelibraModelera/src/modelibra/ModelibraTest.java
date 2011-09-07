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
package modelibra;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.config.DomainConfig;
import org.modelibra.util.OutTester;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class ModelibraTest {

	private static Log log = LogFactory.getLog(ModelibraTest.class);

	private static ModelibraTest modelibraTest;

	private Modelibra modelibra;

	private PersistentModelibra persistentModelibra;

	public ModelibraTest() {
		super();
		try {
			begin();
		} catch (Exception e) {
			log.error("Error in ModelibraTest(): " + e.getMessage());
		}
	}

	public static ModelibraTest getSingleton() {
		if (modelibraTest == null) {
			modelibraTest = new ModelibraTest();
		}
		return modelibraTest;
	}

	private void begin() throws Exception {
		ModelibraConfig modelibraConfig = new ModelibraConfig();
		DomainConfig domainConfig = modelibraConfig.getDomainConfig();
		modelibra = new Modelibra(domainConfig);
		persistentModelibra = new PersistentModelibra(modelibra);
	}

	public Modelibra getModelibra() {
		return modelibra;
	}

	private void end() {
		if (persistentModelibra != null) {
			persistentModelibra.close();
		}
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private void test01() {
		outputMessage("????????? test01: test name ?????????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		ModelibraTest modelibraTest = null;
		try {
			modelibraTest = new ModelibraTest();
			modelibraTest.test();
		} catch (Exception e) {
			log.error("Error in ModelibraTest.main: " + e.getMessage());
		} finally {
			modelibraTest.end();
		}
	}

}