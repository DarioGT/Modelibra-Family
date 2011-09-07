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
import org.modelibra.IDomainModel;

import travel.impression.message.Message;
import travel.impression.place.Place;

/**
 * Note specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-18
 */
public class Note extends GenNote {

	private static final long serialVersionUID = 1189698214192L;

	private static Log log = LogFactory.getLog(Note.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs note within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Note(IDomainModel model) {
		super(model);
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
	public Note(Message message, Place place) {
		super(message, place);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}