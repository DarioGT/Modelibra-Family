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
package dmeduc;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author unknown
 * @version 2008-11-25
 */
public class DmEducTest {

	private static DmEducTest dmEducTest;

	private DmEduc dmEduc;

	private PersistentDmEduc persistentDmEduc;

	private DmEducTest() {
		super();
		open();
	}

	public static DmEducTest getSingleton() {
		if (dmEducTest == null) {
			dmEducTest = new DmEducTest();
		}
		return dmEducTest;
	}

	private void open() {
		DmEducReusableConfig dmEducConfig = new DmEducReusableConfig();
		DomainConfig domainConfig = dmEducConfig.getReusableDomainConfig();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			modelConfig.setPersistenceRelativePath(modelConfig
					.getPersistenceRelativePath()
					+ DmEducReusableConfig.SEPARATOR
					+ dmEducConfig.getModelibraProperties()
							.getTestDirectoryName());
		}
		dmEduc = new DmEduc(domainConfig);
		persistentDmEduc = new PersistentDmEduc(dmEduc);
	}

	public DmEduc getDmEduc() {
		return dmEduc;
	}

	public void close() {
		if (persistentDmEduc != null) {
			persistentDmEduc.close();
		}
	}

}