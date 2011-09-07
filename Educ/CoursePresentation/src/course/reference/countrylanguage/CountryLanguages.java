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
package course.reference.countrylanguage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * CountryLanguage specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class CountryLanguages extends GenCountryLanguages {

	private static final long serialVersionUID = 1176561912590L;

	private static Log log = LogFactory.getLog(CountryLanguages.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs countryLanguages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public CountryLanguages(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Retrieves the country language based on a language code. Null if not
	 * found.
	 * 
	 * @param languageCode
	 *            language code
	 * @return country language
	 */
	public CountryLanguage getCountryLanguage(String languageCode) {
		return retrieveByCode(languageCode);
	}

	/**
	 * Retrieves the language code based on a language name. Null if not found.
	 * 
	 * @param language
	 *            language
	 * @return language code
	 */
	public String getLanguageCode(String language) {
		String languageCode = null;
		CountryLanguage countryLanguage = getCountryLanguage("language",
				language);
		if (countryLanguage != null) {
			languageCode = countryLanguage.getCode();
		}
		return languageCode;
	}

	/**
	 * Retrieves a list of language names.
	 * 
	 * @return list of language names
	 */
	public List<String> getLanguageList() {
		List<String> languageList = new ArrayList<String>();
		for (CountryLanguage countryLanguage : this) {
			languageList.add(countryLanguage.getLanguage());
		}
		return languageList;
	}

}