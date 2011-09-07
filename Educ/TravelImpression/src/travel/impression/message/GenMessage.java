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
package travel.impression.message;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import travel.impression.note.Notes;
import travel.impression.photo.Photos;
import travel.impression.traveler.Traveler;

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Message generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenMessage extends Entity<Message> {

	private static final long serialVersionUID = 1189698214194L;

	private static Log log = LogFactory.getLog(GenMessage.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Date sentDate;

	private String subject;

	private String text;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Traveler traveler;

	/* ======= internal child neighbors ======= */

	private Notes notes;

	private Photos photos;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs message within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMessage(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setNotes(new Notes((Message) this));
		setPhotos(new Photos((Message) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs message within its parent(s).
	 * 
	 * @param Traveler
	 *            traveler
	 */
	public GenMessage(Traveler traveler) {
		this(traveler.getModel());
		// parents
		setTraveler(traveler);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets sentDate.
	 * 
	 * @param sentDate
	 *            sentDate
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * Gets sentDate.
	 * 
	 * @return sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Sets subject.
	 * 
	 * @param subject
	 *            subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets subject.
	 * 
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets text.
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets traveler.
	 * 
	 * @param traveler
	 *            traveler
	 */
	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	/**
	 * Gets traveler.
	 * 
	 * @return traveler
	 */
	public Traveler getTraveler() {
		return traveler;
	}

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets notes.
	 * 
	 * @param notes
	 *            notes
	 */
	public void setNotes(Notes notes) {
		this.notes = notes;
		if (notes != null) {
			notes.setMessage((Message) this);
		}
	}

	/**
	 * Gets notes.
	 * 
	 * @return notes
	 */
	public Notes getNotes() {
		return notes;
	}

	/**
	 * Sets photos.
	 * 
	 * @param photos
	 *            photos
	 */
	public void setPhotos(Photos photos) {
		this.photos = photos;
		if (photos != null) {
			photos.setMessage((Message) this);
		}
	}

	/**
	 * Gets photos.
	 * 
	 * @return photos
	 */
	public Photos getPhotos() {
		return photos;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}