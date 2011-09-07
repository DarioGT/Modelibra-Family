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
package dm.reference.countryname;

import java.util.ArrayList;
import java.util.List;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

/**
 * Country name entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-12
 */
public class CountryNames extends Entities<CountryName> {

	private static final long serialVersionUID = 110120121L;

	/**
	 * Constructs country names within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public CountryNames(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Gets a contry code given the country name.
	 * 
	 * @param country
	 *            country name
	 * @return country code
	 */
	public String getCountryCode(String country) {
		String countryCode = null;
		CountryName countryName = (CountryName) retrieveByProperty("country",
				country);
		if (countryName != null) {
			countryCode = countryName.getCode();
		}
		return countryCode;
	}

	/**
	 * Gets a list of country names.
	 * 
	 * @return list of country names
	 */
	public List<String> getCountryList() {
		List<String> countryList = new ArrayList<String>();
		synchronized (this) {
			for (CountryName countryName : this) {				
				countryList.add(countryName.getCountry());
			}
		}
		return countryList;
	}

	/**
	 * Creates a country language.
	 * 
	 * @param code
	 *            code
	 * @param country
	 *            country
	 * @return country name
	 */
	public CountryName createCountryName(String code, String country) {
		CountryName countryName = new CountryName(getModel());
		countryName.setCode(code);
		countryName.setCountry(country);
		if (!add(countryName)) {
			countryName = null;
		}
		return countryName;
	}

}