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
package travel.impression.country;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import travel.impression.Impression;
import travel.impression.place.Places;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Country generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenCountry extends Entity<Country> {

	private static final long serialVersionUID = 1189698402632L;

	private static Log log = LogFactory.getLog(GenCountry.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String webLink;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Places places;

	/* ======= base constructor ======= */

	/**
	 * Constructs country within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCountry(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets webLink.
	 * 
	 * @param webLink
	 *            webLink
	 */
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	/**
	 * Gets webLink.
	 * 
	 * @return webLink
	 */
	public String getWebLink() {
		return webLink;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets places.
	 * 
	 * @param places
	 *            places
	 */
	public void setPlaces(Places places) {
		this.places = places;
		if (places != null) {
			places.setCountry((Country) this);
		}
	}

	/**
	 * Gets places.
	 * 
	 * @return places
	 */
	public Places getPlaces() {
		if (places == null) {
			Impression impression = (Impression) getModel();
			Places places = impression.getPlaces();
			setPlaces(places.getCountryPlaces((Country) this));
		}
		return places;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}