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
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.wicket.gen.DomainWicketGenerator;
import org.modelibra.wicket.gen.ModelibraWicketGenerator;

/**
 * Generates a Wicket application based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-10
 */
public class DmModelibraWicketGenerator {

	private static Log log = LogFactory
			.getLog(DmModelibraWicketGenerator.class);

	private ModelibraWicketGenerator modelibraWicketGenerator;

	private DomainConfig domainConfig;

	/**
	 * Creates the dm ModelibraWicket generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param authors
	 *            authors
	 * @param codeDirectoryPath
	 *            code directory path
	 */
	public DmModelibraWicketGenerator(DomainConfig domainConfig,
			String authors, String codeDirectoryPath) {
		this.domainConfig = domainConfig;
		modelibraWicketGenerator = new ModelibraWicketGenerator(domainConfig,
				authors, codeDirectoryPath);
	}

	/**
	 * Generates ModelibraWicket.
	 */
	public void generate() {
		modelibraWicketGenerator.generate();
	}

	/**
	 * Generates ModelibraWicket, but only application properties.
	 */
	public void generateModelibraWicketAppProperties() {
		modelibraWicketGenerator.getDomainWicketGenerator()
				.generateDomainAppProperties();
	}

	/**
	 * Generates ModelibraWicket, but only partially if comments are used.
	 * Comment those generate methods that you do not need.
	 */
	public void generateModelibraWicketPartially() {
		DomainWicketGenerator domainWicketGenerator = modelibraWicketGenerator
				.getDomainWicketGenerator();

		domainWicketGenerator.generateWebXml();
		domainWicketGenerator.generateStart();
		domainWicketGenerator.generateDomainApp();
		domainWicketGenerator.generateDomainAppProperties();

		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {

			for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
				domainWicketGenerator
						.generateDomainModelConceptUpdateTablePage(conceptConfig);
				domainWicketGenerator
						.generateDomainModelConceptDisplayTablePage(conceptConfig);
				domainWicketGenerator
						.generateDomainModelConceptDisplayListPage(conceptConfig);
				domainWicketGenerator
						.generateDomainModelConceptDisplaySlidePage(conceptConfig);
			}

		}
	}

	/**
	 * Generates model update and display page classes.
	 * 
	 * @param modelCode
	 *            model code
	 */
	public void generateConceptPageClasses(String modelCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		DomainWicketGenerator domainWicketGenerator = modelibraWicketGenerator
				.getDomainWicketGenerator();
		for (ConceptConfig conceptConfig : modelConfig.getConceptsConfig()) {
			domainWicketGenerator
					.generateDomainModelConceptUpdateTablePage(conceptConfig);
			domainWicketGenerator
					.generateDomainModelConceptDisplayTablePage(conceptConfig);
			domainWicketGenerator
					.generateDomainModelConceptDisplayListPage(conceptConfig);
			domainWicketGenerator
					.generateDomainModelConceptDisplaySlidePage(conceptConfig);
		}
	}

	/**
	 * Generates concept update and display page classes.
	 * 
	 * @param modelCode
	 *            model code
	 * @param conceptCode
	 *            concept code
	 */
	public void generateConceptPageClasses(String modelCode, String conceptCode) {
		ModelConfig modelConfig = domainConfig.getModelConfig(modelCode);
		DomainWicketGenerator domainWicketGenerator = modelibraWicketGenerator
				.getDomainWicketGenerator();
		ConceptConfig conceptConfig = modelConfig.getConceptConfig(conceptCode);
		domainWicketGenerator.generateDomainModelConcept(conceptConfig);
	}

}
