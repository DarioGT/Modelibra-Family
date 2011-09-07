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
 * @version 2007-11-06
 */
public class DmGenerator {

	public static final String DOMAIN_CODE = "DmEduc";

	public static final String DOMAIN_TYPE = "Reusable";

	private DmModelibraGenerator dmModelibraGenerator;

	private DmModelibraWicketGenerator dmModelibraWicketGenerator;

	/**
	 * Creates the dm generator.
	 */
	public DmGenerator() {
		dmModelibraGenerator = new DmModelibraGenerator(DOMAIN_CODE,
				DOMAIN_TYPE);

		DomainConfig domainConfig = dmModelibraGenerator.getDomainConfig();
		String authors = dmModelibraGenerator.getAuthors();
		String sourceDirectoryPath = dmModelibraGenerator
				.getSourceDirectoryPath();

		dmModelibraWicketGenerator = new DmModelibraWicketGenerator(
				domainConfig, authors, sourceDirectoryPath);
	}

	/**
	 * Gets dm Modelibra generator.
	 * 
	 * @return dm Modelibra generator
	 */
	public DmModelibraGenerator getDmModelibraGenerator() {
		return dmModelibraGenerator;
	}

	/**
	 * Gets dm ModelibraWicket generator.
	 * 
	 * @return dm ModelibraWicket generator
	 */
	public DmModelibraWicketGenerator getDmModelibraWicketGenerator() {
		return dmModelibraWicketGenerator;
	}

	public static void main(String[] args) {
		DmGenerator dmGenerator = new DmGenerator();

		// *** Modelibra ***

		// dmGenerator.getDmModelibraGenerator().generate();

		dmGenerator.getDmModelibraGenerator().generateModelibraGenClasses();

		// dmGenerator.getDmModelibraGenerator().generateModelibraPartially();

		// *** ModelibraWicket ***

		// dmGenerator.getDmModelibraWicketGenerator().generate();

		dmGenerator.getDmModelibraWicketGenerator()
				.generateModelibraWicketAppProperties();

		// dmGenerator.getDmModelibraWicketGenerator()
		// .generateModelibraWicketPartially();
	}

}
