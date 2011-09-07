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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.DomainConfig;

/**
 * Generates a domain and its Wicket application based on the domain
 * configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public class DmGenerator {

	public static final String DOMAIN_CODE = "Game";

	public static final String DOMAIN_TYPE = "Specific";

	private static Log log = LogFactory.getLog(DmGenerator.class);

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
		try {
			DmGenerator dmGenerator = new DmGenerator();

			// dmGenerator.getDmModelibraGenerator().generate();
			dmGenerator.getDmModelibraGenerator().generateModelibraGenClasses();

			// dmGenerator.getDmModelibraWicketGenerator().generate();
			// dmGenerator.getDmModelibraWicketGenerator()
			// .generateModelibraWicketAppProperties();
		} catch (Exception e) {
			log.error("Error in DmGenerator.main: " + e.getMessage());
		}
	}

}
