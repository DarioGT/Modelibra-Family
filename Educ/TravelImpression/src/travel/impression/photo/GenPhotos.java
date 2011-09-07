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
package travel.impression.photo;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import travel.impression.message.Message;

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Photo generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenPhotos extends Entities<Photo> {

	private static final long serialVersionUID = 1189698236078L;

	private static Log log = LogFactory.getLog(GenPhotos.class);

	/* ======= internal parent neighbors ======= */

	private Message message;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs photos within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPhotos(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs photos for the message parent.
	 * 
	 * @param message
	 *            message
	 */
	public GenPhotos(Message message) {
		this(message.getModel());
		// parent
		setMessage(message);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the photo with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return photo
	 */
	public Photo getPhoto(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the photo with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return photo
	 */
	public Photo getPhoto(Long oidUniqueNumber) {
		return getPhoto(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first photo whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return photo
	 */
	public Photo getPhoto(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects photos whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return photos
	 */
	public Photos getPhotos(String propertyCode, Object property) {
		return (Photos) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets photos ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered photos
	 */
	public Photos getPhotos(String propertyCode, boolean ascending) {
		return (Photos) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets photos selected by a selector. Returns empty photos if there are no
	 * photos that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected photos
	 */
	public Photos getPhotos(ISelector selector) {
		return (Photos) selectBySelector(selector);
	}

	/**
	 * Gets photos ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered photos
	 */
	public Photos getPhotos(Comparator comparator, boolean ascending) {
		return (Photos) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets title photos.
	 * 
	 * @param title
	 *            title
	 * @return title photos
	 */
	public Photos getTitlePhotos(String title) {
		PropertySelector propertySelector = new PropertySelector("title");
		propertySelector.defineEqual(title);
		return getPhotos(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets webLink photo.
	 * 
	 * @param webLink
	 *            webLink
	 * @return webLink photo
	 */
	public Photo getWebLinkPhoto(String webLink) {
		PropertySelector propertySelector = new PropertySelector("webLink");
		propertySelector.defineEqual(webLink);
		List<Photo> list = getPhotos(propertySelector).getList();

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
	 * Gets photos ordered by title.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered photos
	 */
	public Photos getPhotosOrderedByTitle(boolean ascending) {
		return getPhotos("title", ascending);
	}

	/**
	 * Gets photos ordered by webLink.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered photos
	 */
	public Photos getPhotosOrderedByWebLink(boolean ascending) {
		return getPhotos("webLink", ascending);
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
	 * Creates photo.
	 * 
	 * @param messageParent
	 *            message parent
	 * @param title
	 *            title
	 * @param webLink
	 *            webLink
	 * @return photo
	 */
	public Photo createPhoto(Message messageParent, String title, String webLink) {
		Photo photo = new Photo(getModel());
		photo.setMessage(messageParent);
		photo.setTitle(title);
		photo.setWebLink(webLink);
		if (!add(photo)) {
			photo = null;
		}
		return photo;
	}

}