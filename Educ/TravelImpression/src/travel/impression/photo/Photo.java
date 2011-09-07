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
import org.modelibra.IDomainModel;

import travel.impression.message.Message;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Photo specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-18
 */
public class Photo extends GenPhoto {

	private static final long serialVersionUID = 1189698236079L;

	private static Log log = LogFactory.getLog(Photo.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs photo within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Photo(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs photo within its parent(s).
	 * 
	 * @param Message
	 *            message
	 */
	public Photo(Message message) {
		super(message);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}