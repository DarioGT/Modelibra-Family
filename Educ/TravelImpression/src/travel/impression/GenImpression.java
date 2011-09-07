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
package travel.impression;

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import travel.impression.country.Countries;
import travel.impression.place.Places;
import travel.impression.traveler.Travelers;

/**
 * Impression generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenImpression extends DomainModel {

	private static final long serialVersionUID = 1190053285158L;

	private Travelers travelers;

	private Countries countries;

	private Places places;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenImpression(IDomain domain) {
		super(domain);
		travelers = new Travelers(this);
		countries = new Countries(this);
		places = new Places(this);
	}

	/**
	 * Gets Traveler entities.
	 * 
	 * @return Traveler entities
	 */
	public Travelers getTravelers() {
		return travelers;
	}

	/**
	 * Gets Country entities.
	 * 
	 * @return Country entities
	 */
	public Countries getCountries() {
		return countries;
	}

	/**
	 * Gets Place entities.
	 * 
	 * @return Place entities
	 */
	public Places getPlaces() {
		return places;
	}

}
