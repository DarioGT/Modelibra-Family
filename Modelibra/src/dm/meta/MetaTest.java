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
package dm.meta;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dm.DmTest;
import dm.meta.domain.Domains;

/**
 * Meta model tests.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-13
 */
public class MetaTest extends DmTest {

	private static Log log = LogFactory.getLog(MetaTest.class);

	private Meta meta = getDm().getMeta();

	private void test01() {
		outputMessage("????????? test01: display domains ?????????");
		Domains domains = meta.getDomains();
		domains.output("Dm Configuration Domains");
	}

	private void test() {
		test01();
	}

	public static void main(String[] args) {
		MetaTest modelTest = null;
		try {
			modelTest = new MetaTest();
			modelTest.test();
			modelTest.close();
		} catch (Exception e) {
			log.error("Error in MetaTest.main: " + e.getMessage());
			modelTest.close();
		}
	}

}
