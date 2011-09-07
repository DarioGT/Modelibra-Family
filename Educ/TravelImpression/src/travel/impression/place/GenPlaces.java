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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import travel.impression.country.Country;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Place generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenPlaces extends Entities<Place> {

	private static final long serialVersionUID = 1192738632290L;

	private static Log log = LogFactory.getLog(GenPlaces.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Country country;

	/* ======= base constructor ======= */

	/**
	 * Constructs places within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPlaces(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs places for the country parent.
	 * 
	 * @param country
	 *            country
	 */
	public GenPlaces(Country country) {
		this(country.getModel());
		// parent
		setCountry(country);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the place with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return place
	 */
	public Place getPlace(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the place with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return place
	 */
	public Place getPlace(Long oidUniqueNumber) {
		return getPlace(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first place whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return place
	 */
	public Place getPlace(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects places whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return places
	 */
	public Places getPlaces(String propertyCode, Object property) {
		return (Places) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets places ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered places
	 */
	public Places getPlaces(String propertyCode, boolean ascending) {
		return (Places) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets places selected by a selector. Returns empty places if there are no
	 * places that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected places
	 */
	public Places getPlaces(ISelector selector) {
		return (Places) selectBySelector(selector);
	}

	/**
	 * Gets places ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered places
	 */
	public Places getPlaces(Comparator comparator, boolean ascending) {
		return (Places) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets name place.
	 * 
	 * @param name
	 *            name
	 * @return name place
	 */
	public Place getNamePlace(String name) {
		PropertySelector propertySelector = new PropertySelector("name");
		propertySelector.defineEqual(name);
		List<Place> list = getPlaces(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets country places.
	 * 
	 * @param country
	 *            country oid unique number
	 * @return country places
	 */
	public Places getCountryPlaces(Long country) {
		PropertySelector propertySelector = new PropertySelector("countryOid");
		propertySelector.defineEqual(country);
		return getPlaces(propertySelector);
	}

	/**
	 * Gets country places.
	 * 
	 * @param country
	 *            country oid
	 * @return country places
	 */
	public Places getCountryPlaces(Oid country) {
		return getCountryPlaces(country.getUniqueNumber());
	}

	/**
	 * Gets country places.
	 * 
	 * @param country
	 *            country
	 * @return country places
	 */
	public Places getCountryPlaces(Country country) {
		return getCountryPlaces(country.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets places ordered by name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered places
	 */
	public Places getPlacesOrderedByName(boolean ascending) {
		return getPlaces("name", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets country.
	 * 
	 * @param country
	 *            country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Gets country.
	 * 
	 * @return country
	 */
	public Country getCountry() {
		return country;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Place place) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(place)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Country country = getCountry();
				if (country == null) {
					Country placeCountry = place.getCountry();
					if (placeCountry != null) {
						if (!placeCountry.getPlaces().contain(place)) {
							placeCountry.getPlaces()
									.setPropagateToSource(false);
							post = placeCountry.getPlaces().add(place);
							placeCountry.getPlaces().setPropagateToSource(true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(Place place) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(place)) {
			Country country = getCountry();
			if (country == null) {
				Country placeCountry = place.getCountry();
				if (placeCountry != null) {
					if (placeCountry.getPlaces().contain(place)) {
						placeCountry.getPlaces().setPropagateToSource(false);
						post = placeCountry.getPlaces().remove(place);
						placeCountry.getPlaces().setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(Place beforePlace, Place afterPlace) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforePlace, afterPlace)) {
			Country country = getCountry();
			if (country == null) {
				Country beforePlaceCountry = beforePlace.getCountry();
				Country afterPlaceCountry = afterPlace.getCountry();
				if (beforePlaceCountry == null && afterPlaceCountry != null) {
					// attach
					if (!afterPlaceCountry.getPlaces().contain(afterPlace)) {
						afterPlaceCountry.getPlaces().setPropagateToSource(
								false);
						post = afterPlaceCountry.getPlaces().add(afterPlace);
						afterPlaceCountry.getPlaces()
								.setPropagateToSource(true);
					}
				} else if (beforePlaceCountry != null
						&& afterPlaceCountry == null) {
					// detach
					if (beforePlaceCountry.getPlaces().contain(beforePlace)) {
						beforePlaceCountry.getPlaces().setPropagateToSource(
								false);
						post = beforePlaceCountry.getPlaces().remove(
								beforePlace);
						beforePlaceCountry.getPlaces().setPropagateToSource(
								true);
					}
				} else if (beforePlaceCountry != null
						&& afterPlaceCountry != null
						&& beforePlaceCountry != afterPlaceCountry) {
					// detach
					if (beforePlaceCountry.getPlaces().contain(beforePlace)) {
						beforePlaceCountry.getPlaces().setPropagateToSource(
								false);
						post = beforePlaceCountry.getPlaces().remove(
								beforePlace);
						beforePlaceCountry.getPlaces().setPropagateToSource(
								true);
					}
					// attach
					if (!afterPlaceCountry.getPlaces().contain(afterPlace)) {
						afterPlaceCountry.getPlaces().setPropagateToSource(
								false);
						post = afterPlaceCountry.getPlaces().add(afterPlace);
						afterPlaceCountry.getPlaces()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates place.
	 * 
	 * @param countryParent
	 *            country parent
	 * @param name
	 *            name
	 * @return place
	 */
	public Place createPlace(Country countryParent, String name) {
		Place place = new Place(getModel());
		place.setCountry(countryParent);
		place.setName(name);
		if (!add(place)) {
			place = null;
		}
		return place;
	}

}