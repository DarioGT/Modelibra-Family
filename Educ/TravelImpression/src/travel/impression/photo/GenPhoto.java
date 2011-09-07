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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import travel.impression.message.Message;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Photo generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public abstract class GenPhoto extends Entity<Photo> {

	private static final long serialVersionUID = 1189698236077L;

	private static Log log = LogFactory.getLog(GenPhoto.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String title;

	private String webLink;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Message message;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs photo within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPhoto(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs photo within its parent(s).
	 * 
	 * @param Message
	 *            message
	 */
	public GenPhoto(Message message) {
		this(message.getModel());
		// parents
		setMessage(message);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets title.
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
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

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}