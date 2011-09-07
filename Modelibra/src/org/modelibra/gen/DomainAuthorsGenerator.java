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
package org.modelibra.gen;

import java.util.ArrayList;
import java.util.List;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;

/**
 * Code generation is based on the domain configuration. Code is generated in
 * the code directory. Authors are derived from model authors.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-23
 */
public class DomainAuthorsGenerator extends Generator {

	private String authors;

	/**
	 * Constructs code generator.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public DomainAuthorsGenerator(DomainConfig domainConfig) {
		super();
		authors = getModelAuthors(domainConfig);
	}

	/**
	 * Creates a list of unique authors from model authors. Converts the list to
	 * a string of authors separated by comma.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	private String getModelAuthors(DomainConfig domainConfig) {
		String modelAuthors = "";
		List<String> authorList = new ArrayList<String>();
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			String author = modelConfig.getAuthor();
			if (!authorList.contains(author)) {
				authorList.add(author);
			}
		}
		int count = 0;
		int listSize = authorList.size();
		for (String modelAuthor : authorList) {
			if (listSize == ++count) {
				modelAuthors = modelAuthors + modelAuthor;
			} else {
				modelAuthors = modelAuthors + modelAuthor + ", ";
			}
		}
		return modelAuthors;
	}

	/**
	 * Gets authors.
	 * 
	 * @return authors
	 */
	public String getAuthors() {
		return authors;
	}

}
