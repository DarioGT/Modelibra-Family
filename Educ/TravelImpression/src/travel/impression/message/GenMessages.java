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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.IEntity;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import travel.impression.note.Note;
import travel.impression.note.Notes;
import travel.impression.place.Place;
import travel.impression.traveler.Traveler;

/**
 * Message generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenMessages extends Entities<Message> {

	private static final long serialVersionUID = 1189698214195L;

	private static Log log = LogFactory.getLog(GenMessages.class);

	/* ======= internal parent neighbors ======= */

	private Traveler traveler;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs messages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMessages(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs messages for the traveler parent.
	 * 
	 * @param traveler
	 *            traveler
	 */
	public GenMessages(Traveler traveler) {
		this(traveler.getModel());
		// parent
		setTraveler(traveler);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the message with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return message
	 */
	public Message getMessage(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the message with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return message
	 */
	public Message getMessage(Long oidUniqueNumber) {
		return getMessage(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first message whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return message
	 */
	public Message getMessage(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects messages whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return messages
	 */
	public Messages getMessages(String propertyCode, Object property) {
		return (Messages) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets messages ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered messages
	 */
	public Messages getMessages(String propertyCode, boolean ascending) {
		return (Messages) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets messages selected by a selector. Returns empty messages if there are
	 * no messages that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected messages
	 */
	public Messages getMessages(ISelector selector) {
		return (Messages) selectBySelector(selector);
	}

	/**
	 * Gets messages ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered messages
	 */
	public Messages getMessages(Comparator comparator, boolean ascending) {
		return (Messages) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets subject messages.
	 * 
	 * @param subject
	 *            subject
	 * @return subject messages
	 */
	public Messages getSubjectMessages(String subject) {
		PropertySelector propertySelector = new PropertySelector("subject");
		propertySelector.defineEqual(subject);
		return getMessages(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets sentDate message.
	 * 
	 * @param sentDate
	 *            sentDate
	 * @return sentDate message
	 */
	public Message getSentDateMessage(Date sentDate) {
		PropertySelector propertySelector = new PropertySelector("sentDate");
		propertySelector.defineEqual(sentDate);
		List<Message> list = getMessages(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets messages ordered by sentDate.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered messages
	 */
	public Messages getMessagesOrderedBySentDate(boolean ascending) {
		return getMessages("sentDate", ascending);
	}

	/**
	 * Gets messages ordered by subject.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered messages
	 */
	public Messages getMessagesOrderedBySubject(boolean ascending) {
		return getMessages("subject", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets place notes.
	 * 
	 * @return place notes
	 */
	public Notes getPlaceNotes(Place place) {
		Notes notes = new Notes(place);
		for (IEntity entity : this) {
			Message message = (Message) entity;
			Note note = message.getNotes().getNote(message, place);
			if (note != null) {
				notes.add(note);
			}
		}
		return notes;
	}

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

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

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
	 * Creates message.
	 * 
	 * @param travelerParent
	 *            traveler parent
	 * @param subject
	 *            subject
	 * @param text
	 *            text
	 * @return message
	 */
	public Message createMessage(Traveler travelerParent, String subject,
			String text) {
		Message message = new Message(getModel());
		message.setTraveler(travelerParent);
		message.setSubject(subject);
		message.setText(text);
		if (!add(message)) {
			message = null;
		}
		return message;
	}

}