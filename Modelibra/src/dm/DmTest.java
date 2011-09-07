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
package dm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.config.DomainConfig;
import org.modelibra.util.OutTester;

/**
 * Dm tests of several models at the same time, after the models had been tested
 * separately.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-02
 */
public class DmTest {

	private static Log log = LogFactory.getLog(DmTest.class);

	private Dm dm;

	private PersistentDm persistentDm;

	/**
	 * Constructs the dm test.
	 */
	public DmTest() {
		super();
		DmConfig dmConfig = new DmConfig();
		DomainConfig domainConfig = dmConfig.getDomainConfig();
		dm = new Dm(domainConfig);
		persistentDm = new PersistentDm(dm);
	}

	public Dm getDm() {
		return dm;
	}

	public void outputErrors(IEntities entities, String title) {
		entities.getErrors().output(title);
		entities.getErrors().empty();
	}

	public void outputMessage(String message) {
		OutTester.outputText(message);
	}

	public void close() {
		if (persistentDm != null) {
			persistentDm.close();
		}
	}

	private void test01() {
		outputMessage("????????? test01: test explanation ?????????");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		DmTest dmTest = null;
		dmTest = new DmTest();
		dmTest.test();
		dmTest.close();		
	}

}
