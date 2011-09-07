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
package travel.impression.note;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import travel.impression.Impression;
import travel.impression.message.Message;
import travel.impression.place.Place;
import travel.impression.place.Places;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Note generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenNote extends Entity<Note> {

	private static final long serialVersionUID = 1189698214190L;

	private static Log log = LogFactory.getLog(GenNote.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String impression;

	private String webLink;

	/* ======= reference properties ======= */

	private Long placeOid;

	/* ======= internal parent neighbors ======= */

	private Message message;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Place place;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs note within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenNote(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs note within its parent(s).
	 * 
	 * @param Message
	 *            message
	 * @param Place
	 *            place
	 */
	public GenNote(Message message, Place place) {
		this(place.getModel());
		// parents
		setMessage(message);
		setPlace(place);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets impression.
	 * 
	 * @param impression
	 *            impression
	 */
	public void setImpression(String impression) {
		this.impression = impression;
	}

	/**
	 * Gets impression.
	 * 
	 * @return impression
	 */
	public String getImpression() {
		return impression;
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

	/**
	 * Sets placeOid.
	 * 
	 * @param placeOid
	 *            placeOid
	 */
	public void setPlaceOid(Long placeOid) {
		this.placeOid = placeOid;
		place = null;
	}

	/**
	 * Gets placeOid.
	 * 
	 * @return placeOid
	 */
	public Long getPlaceOid() {
		return placeOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets message.
	 * 
	 * @param message
	 *            message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Gets message.
	 * 
	 * @return message
	 */
	public Message getMessage() {
		return message;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets place.
	 * 
	 * @param place
	 *            place
	 */
	public void setPlace(Place place) {
		this.place = place;
		if (place != null) {
			placeOid = place.getOid().getUniqueNumber();
		} else {
			placeOid = null;
		}
	}

	/**
	 * Gets place.
	 * 
	 * @return place
	 */
	public Place getPlace() {
		if (place == null) {
			Impression impression = (Impression) getModel();
			Places places = impression.getPlaces();
			if (placeOid != null) {
				place = places.getPlace(placeOid);
			}
		}
		return place;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}