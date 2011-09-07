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
package medical;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-17
 */
public class MedicalTest {
	
	private static MedicalTest medicalTest;

	private Medical medical;

	private PersistentMedical persistentMedical;
	
	private MedicalTest() {
		super();
		open();
	}
	
	public static MedicalTest getSingleton() {
		if (medicalTest == null) {
			medicalTest = new MedicalTest();
		}
		return medicalTest;
	}
	
	private void open() {
		MedicalConfig medicalConfig = new MedicalConfig();
		DomainConfig domainConfig = medicalConfig.getDomainConfig();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			modelConfig.setPersistenceRelativePath(modelConfig
					.getPersistenceRelativePath()
					+ MedicalConfig.SEPARATOR
					+ medicalConfig.getModelibraProperties().getTestDirectoryName());
		}
		medical = new Medical(domainConfig);
		persistentMedical = new PersistentMedical(medical);
	}

	public Medical getMedical() {
		return medical;
	}
	
	public void close() {
		if (persistentMedical != null) {
			persistentMedical.close();
		}
	}
	
}