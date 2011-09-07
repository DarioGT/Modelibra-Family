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
package travel.impression.place;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import travel.impression.country.Country;

/**
 * Place specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-18
 */
public class Places extends GenPlaces {

	private static final long serialVersionUID = 1189698214171L;

	private static Log log = LogFactory.getLog(Places.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs places within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Places(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs places for the country parent.
	 * 
	 * @param country
	 *            country
	 */
	public Places(Country country) {
		super(country);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}