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
package dm.reference.countrylanguage;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Country language entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class CountryLanguage extends Entity<CountryLanguage> {

	private static final long serialVersionUID = 110120110L;

	// private static Log log = LogFactory.getLog(CountryLanguage.class);

	private String language;

	/**
	 * Constructs a country language within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public CountryLanguage(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Sets a language name.
	 * 
	 * @param language
	 *            language name
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets a languag name.
	 * 
	 * @return language name
	 */
	public String getLanguage() {
		return language;
	}

}