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

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.IEntity;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import travel.impression.message.Message;
import travel.impression.place.Place;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Note generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenNotes extends Entities<Note> {

	private static final long serialVersionUID = 1189698214191L;

	private static Log log = LogFactory.getLog(GenNotes.class);

	/* ======= internal parent neighbors ======= */

	private Message message;

	/* ======= external parent neighbors ======= */

	private Place place;

	/* ======= base constructor ======= */

	/**
	 * Constructs notes within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenNotes(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs notes for the message parent.
	 * 
	 * @param message
	 *            message
	 */
	public GenNotes(Message message) {
		this(message.getModel());
		// parent
		setMessage(message);
	}

	/**
	 * Constructs notes for the place parent.
	 * 
	 * @param place
	 *            place
	 */
	public GenNotes(Place place) {
		this(place.getModel());
		// parent
		setPlace(place);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the note with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return note
	 */
	public Note getNote(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the note with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return note
	 */
	public Note getNote(Long oidUniqueNumber) {
		return getNote(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first note whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return note
	 */
	public Note getNote(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects notes whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return notes
	 */
	public Notes getNotes(String propertyCode, Object property) {
		return (Notes) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets notes ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered notes
	 */
	public Notes getNotes(String propertyCode, boolean ascending) {
		return (Notes) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets notes selected by a selector. Returns empty notes if there are no
	 * notes that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected notes
	 */
	public Notes getNotes(ISelector selector) {
		return (Notes) selectBySelector(selector);
	}

	/**
	 * Gets notes ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered notes
	 */
	public Notes getNotes(Comparator comparator, boolean ascending) {
		return (Notes) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets impression notes.
	 * 
	 * @param impression
	 *            impression
	 * @return impression notes
	 */
	public Notes getImpressionNotes(String impression) {
		PropertySelector propertySelector = new PropertySelector("impression");
		propertySelector.defineEqual(impression);
		return getNotes(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets notes ordered by impression.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered notes
	 */
	public Notes getNotesOrderedByImpression(boolean ascending) {
		return getNotes("impression", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/**
	 * Gets note based on many-to-many parents.
	 * 
	 * @param Message
	 *            message
	 * @param Place
	 *            place
	 */
	public Note getNote(Message message, Place place) {
		for (IEntity entity : this) {
			Note note = (Note) entity;
			if (note.getMessage() == message && note.getPlace() == place) {
				return note;
			}
		}
		return null;
	}

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

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

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets place.
	 * 
	 * @param place
	 *            place
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
	 * Gets place.
	 * 
	 * @return place
	 */
	public Place getPlace() {
		return place;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	protected boolean postAdd(Note note) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(note)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Message message = getMessage();
				if (message == null) {
					Message noteMessage = note.getMessage();
					if (!noteMessage.getNotes().contain(note)) {
						post = noteMessage.getNotes().add(note);
					}
				}
				Place place = getPlace();
				if (place == null) {
					Place notePlace = note.getPlace();
					if (!notePlace.getNotes().contain(note)) {
						post = notePlace.getNotes().add(note);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post remove propagation ======= */

	protected boolean postRemove(Note note) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(note)) {
			Message message = getMessage();
			if (message == null) {
				Message noteMessage = note.getMessage();
				if (noteMessage.getNotes().contain(note)) {
					post = noteMessage.getNotes().remove(note);
				}
			}
			Place place = getPlace();
			if (place == null) {
				Place notePlace = note.getPlace();
				if (notePlace.getNotes().contain(note)) {
					post = notePlace.getNotes().remove(note);
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post update propagation ======= */

	protected boolean postUpdate(Note beforeNote, Note afterNote) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeNote, afterNote)) {
			Message beforeNoteMessage = beforeNote.getMessage();
			Message afterNoteMessage = afterNote.getMessage();

			if (beforeNoteMessage != afterNoteMessage) {
				post = beforeNoteMessage.getNotes().remove(beforeNote);
				if (post) {
					post = afterNoteMessage.getNotes().add(afterNote);
					if (!post) {
						beforeNoteMessage.getNotes().add(beforeNote);
					}
				}
			}
			Place beforeNotePlace = beforeNote.getPlace();
			Place afterNotePlace = afterNote.getPlace();

			if (beforeNotePlace != afterNotePlace) {
				post = beforeNotePlace.getNotes().remove(beforeNote);
				if (post) {
					post = afterNotePlace.getNotes().add(afterNote);
					if (!post) {
						beforeNotePlace.getNotes().add(beforeNote);
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
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates note.
	 * 
	 * @param messageParent
	 *            message parent
	 * @param placeParent
	 *            place parent
	 * @param impression
	 *            impression
	 * @return note
	 */
	public Note createNote(Message messageParent, Place placeParent,
			String impression) {
		Note note = new Note(getModel());
		note.setMessage(messageParent);
		note.setPlace(placeParent);
		note.setImpression(impression);
		if (!add(note)) {
			note = null;
		}
		return note;
	}

}