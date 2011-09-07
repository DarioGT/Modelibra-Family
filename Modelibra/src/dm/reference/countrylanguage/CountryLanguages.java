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

import java.util.ArrayList;
import java.util.List;

import org.modelibra.Entities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;

/**
 * Country language entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class CountryLanguages extends Entities<CountryLanguage> {

	private static final long serialVersionUID = 110120111L;

	/**
	 * Constructs country languages within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public CountryLanguages(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Retrieves the country language based on a language code. Null if not
	 * found.
	 * 
	 * @param languageCode
	 *            language code
	 * @return country language
	 */
	public CountryLanguage getCountryLanguage(String languageCode) {
		return (CountryLanguage) retrieveByCode(languageCode);
	}

	/**
	 * Gets a language code given the language name.
	 * 
	 * @param language
	 *            language name
	 * @return language code
	 */
	public String getLanguageCode(String language) {
		String languageCode = null;
		CountryLanguage countryLanguage = (CountryLanguage) retrieveByProperty(
				"language", language);
		if (countryLanguage != null) {
			languageCode = countryLanguage.getCode();
		}
		return languageCode;
	}

	/**
	 * Gets a list of language names.
	 * 
	 * @return list of language names
	 */
	public List<String> getLanguageList() {
		List<String> languageList = new ArrayList<String>();
		synchronized (this) {
			for (IEntity<?> entity : this) {
				CountryLanguage countryLanguage = (CountryLanguage) entity;
				languageList.add(countryLanguage.getLanguage());
			}
		}
		return languageList;
	}

	/**
	 * Creates a country language.
	 * 
	 * @param code
	 *            code
	 * @param language
	 *            language
	 * @return country language
	 */
	public CountryLanguage createCountryLanguage(String code, String language) {
		CountryLanguage countryLanguage = new CountryLanguage(getModel());
		countryLanguage.setCode(code);
		countryLanguage.setLanguage(language);
		if (!add(countryLanguage)) {
			countryLanguage = null;
		}
		return countryLanguage;
	}

}