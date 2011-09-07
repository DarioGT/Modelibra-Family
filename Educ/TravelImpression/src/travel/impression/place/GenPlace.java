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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import travel.impression.Impression;
import travel.impression.country.Countries;
import travel.impression.country.Country;
import travel.impression.message.Messages;
import travel.impression.note.Notes;
import travel.impression.url.Urls;

/**
 * Place generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenPlace extends Entity<Place> {

	private static final long serialVersionUID = 1192738632289L;

	private static Log log = LogFactory.getLog(GenPlace.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String city;

	private String webLink;

	private String description;

	/* ======= reference properties ======= */

	private Long countryOid;

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Urls urls;

	/* ======= external parent neighbors ======= */

	private Country country;

	/* ======= external child neighbors ======= */

	private Notes notes;

	/* ======= base constructor ======= */

	/**
	 * Constructs place within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPlace(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setUrls(new Urls((Place) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs place within its parent(s).
	 * 
	 * @param Country
	 *            country
	 */
	public GenPlace(Country country) {
		this(country.getModel());
		// parents
		setCountry(country);
	}

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
	 * Sets city.
	 * 
	 * @param city
	 *            city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
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

	/**
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets countryOid.
	 * 
	 * @param countryOid
	 *            countryOid
	 */
	public void setCountryOid(Long countryOid) {
		this.countryOid = countryOid;
		country = null;
	}

	/**
	 * Gets countryOid.
	 * 
	 * @return countryOid
	 */
	public Long getCountryOid() {
		return countryOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets urls.
	 * 
	 * @param urls
	 *            urls
	 */
	public void setUrls(Urls urls) {
		this.urls = urls;
		if (urls != null) {
			urls.setPlace((Place) this);
		}
	}

	/**
	 * Gets urls.
	 * 
	 * @return urls
	 */
	public Urls getUrls() {
		return urls;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets country.
	 * 
	 * @param country
	 *            country
	 */
	public void setCountry(Country country) {
		this.country = country;
		if (country != null) {
			countryOid = country.getOid().getUniqueNumber();
		} else {
			countryOid = null;
		}
	}

	/**
	 * Gets country.
	 * 
	 * @return country
	 */
	public Country getCountry() {
		if (country == null) {
			Impression impression = (Impression) getModel();
			Countries countries = impression.getCountries();
			if (countryOid != null) {
				country = countries.getCountry(countryOid);
			}
		}
		return country;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

	/**
	 * Sets notes.
	 * 
	 * @param notes
	 *            notes
	 */
	public void setNotes(Notes notes) {
		this.notes = notes;
		if (notes != null) {
			notes.setPlace((Place) this);
		}
	}

	/**
	 * Gets notes.
	 * 
	 * @return notes
	 */
	public Notes getNotes() {
		if (notes == null) {
			Impression impression = (Impression) getModel();
			Messages messages = impression.getMessages();
			setNotes(messages.getPlaceNotes((Place) this));
		}
		return notes;
	}

}