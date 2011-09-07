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
package dm.gen;

import org.modelibra.config.DomainConfig;

/**
 * Generates a domain and its Wicket application based on the domain
 * configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-30
 */
public class DmGenerator {

	public static final String DOMAIN_CODE = "Education";

	public static final String DOMAIN_TYPE = "Reusable";

	private DmModelibraGenerator dmModelibraGenerator;

	/**
	 * Creates the dm generator.
	 */
	public DmGenerator() {
		dmModelibraGenerator = new DmModelibraGenerator(DOMAIN_CODE,
				DOMAIN_TYPE);
	}

	/**
	 * Gets dm Modelibra generator.
	 * 
	 * @return dm Modelibra generator
	 */
	public DmModelibraGenerator getDmModelibraGenerator() {
		return dmModelibraGenerator;
	}

	public static void main(String[] args) {
		DmGenerator dmGenerator = new DmGenerator();

		// *** Modelibra ***

		// *** 1. Generate all ***
		dmGenerator.getDmModelibraGenerator().generate();

		// *** 2. Generate new with preserving specific ***
		// dmGenerator.getDmModelibraGenerator().generateModelibraGenClasses();
		// OR
		// *** All for an additional model
		// dmGenerator.getDmModelibraGenerator().generateDomainGenClass();
		// dmGenerator.getDmModelibraGenerator().generateModel("NewModel");
		// OR
		// *** Model changes
		// dmGenerator.getDmModelibraGenerator()
		// .generateModelGenClass("Model");
		// dmGenerator.getDmModelibraGenerator().generateConceptGenClasses(
		// "Model", "UpdatedConcept");
		// dmGenerator.getDmModelibraGenerator().generateConceptGenClasses(
		// "Model", "NewConceptNeighbor");
		// dmGenerator.getDmModelibraGenerator().generateConceptClasses(
		// "Model", "NewEntryConcept");
		// dmGenerator.getDmModelibraGenerator().generateConceptClasses(
		// "Model", "NewConcept");
		// dmGenerator.getDmModelibraGenerator()
		// .generateEntryConceptEmptyXmlDataFile("Model",
		// "NewEntryConcept");

		// *** Optional: If done, be sure to have a backup of
		// specific-domain-config.xml ***
		// dmGenerator.getDmModelibraGenerator()
		// .generateSpecificDomainConfig();

		// *** 3. Generate what you do not want by using comments ***
		// dmGenerator.getDmModelibraGenerator().generateModelibraPartially();
	}

}
