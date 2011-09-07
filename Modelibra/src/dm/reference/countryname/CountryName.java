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

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Country name entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-08
 */
public class CountryName extends Entity<CountryName> {

	private static final long serialVersionUID = 110120120L;

	// private static Log log = LogFactory.getLog(CountryName.class);

	private String country;

	/**
	 * Constructs a country code within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public CountryName(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Sets a country name.
	 * 
	 * @param country
	 *            country name
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets a country name.
	 * 
	 * @return country name
	 */
	public String getCountry() {
		return country;
	}

}