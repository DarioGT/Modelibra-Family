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
import org.modelibra.swing.app.gen.ModelibraSwingGenerator;
import org.modelibra.swing.app.gen.StartGenerator;
import org.modelibra.swing.app.gen.TextResGenerator;

/**
 * Generates a Swing application based on the domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-11-18
 */
public class DmModelibraSwingGenerator {

	private ModelibraSwingGenerator modelibraSwingGenerator;

	private boolean minCodeGen;

	/**
	 * Creates the dm ModelibraSwing generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 */
	public DmModelibraSwingGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath) {
		modelibraSwingGenerator = new ModelibraSwingGenerator(domainConfig,
				sourceDirectoryPath);
	}

	/**
	 * Creates the dm ModelibraSwing generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 * @param sourceDirectoryPath
	 *            source directory path
	 * @param minCodeGen
	 *            <code>true</code> if it is a minCodeGen
	 */
	public DmModelibraSwingGenerator(DomainConfig domainConfig,
			String sourceDirectoryPath, boolean minCodeGen) {
		this.minCodeGen = minCodeGen;
		modelibraSwingGenerator = new ModelibraSwingGenerator(domainConfig,
				sourceDirectoryPath, minCodeGen);
	}

	/**
	 * Generates ModelibraSwing.
	 */
	public void generate() {
		modelibraSwingGenerator.generate();
	}

	/**
	 * Generates ModelibraSwing, but only i18n en properties.
	 */
	public void generateModelibraSwingTextResProperties_en() {
		modelibraSwingGenerator.getTextResGenerator()
				.generateTextResProperties_en();
	}

	/**
	 * Generates ModelibraSwing, but only i18n fr properties.
	 */
	public void generateModelibraSwingTextResProperties_fr() {
		modelibraSwingGenerator.getTextResGenerator()
				.generateTextResProperties_fr();
	}

	/**
	 * Generates ModelibraSwing, but only i18n ba properties.
	 */
	public void generateModelibraSwingTextResProperties_ba() {
		modelibraSwingGenerator.getTextResGenerator()
				.generateTextResProperties_ba();
	}

	/**
	 * Generates ModelibraSwing, but only partially if comments are used.
	 * Comment those generate methods that you do not need.
	 */
	public void generateModelibraSwingPartially() {
		StartGenerator startGenerator = modelibraSwingGenerator
				.getStartGenerator();
		startGenerator.generateStart();
		startGenerator.generateStartProperties();

		TextResGenerator textResGenerator = modelibraSwingGenerator
				.getTextResGenerator();
		textResGenerator.generateTextRes();
		textResGenerator.generateTextResProperties();
		textResGenerator.generateTextResProperties_en();
		if (!minCodeGen) {
			textResGenerator.generateTextRes_fr();
			textResGenerator.generateTextResProperties_fr();

			textResGenerator.generateTextRes_ba();
			textResGenerator.generateTextResProperties_ba();
		}
	}

}
