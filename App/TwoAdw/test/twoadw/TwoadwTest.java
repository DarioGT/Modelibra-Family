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
package twoadw;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class TwoadwTest {
	
	private static TwoadwTest twoadwTest;

	private Twoadw twoadw;

	private PersistentTwoadw persistentTwoadw;
	
	private TwoadwTest() {
		super();
		open();
	}
	
	public static TwoadwTest getSingleton() {
		if (twoadwTest == null) {
			twoadwTest = new TwoadwTest();
		}
		return twoadwTest;
	}
	
	private void open() {
		TwoadwConfig twoadwConfig = new TwoadwConfig();
		DomainConfig domainConfig = twoadwConfig.getDomainConfig();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			modelConfig.setPersistenceRelativePath(modelConfig
					.getPersistenceRelativePath()
					+ TwoadwConfig.SEPARATOR
					+ twoadwConfig.getModelibraProperties().getTestDirectoryName());
		}
		twoadw = new Twoadw(domainConfig);
		persistentTwoadw = new PersistentTwoadw(twoadw);
	}

	public Twoadw getTwoadw() {
		return twoadw;
	}
	
	public void close() {
		if (persistentTwoadw != null) {
			persistentTwoadw.close();
		}
	}
	
}