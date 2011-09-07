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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import travel.impression.traveler.Traveler;

/* ======= import external parent entity and entities classes ======= */

/**
 * Message specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public class Messages extends GenMessages {

	private static final long serialVersionUID = 1189698214197L;

	private static Log log = LogFactory.getLog(Messages.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs messages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Messages(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs messages for the traveler parent.
	 * 
	 * @param traveler
	 *            traveler
	 */
	public Messages(Traveler traveler) {
		super(traveler);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets last message.
	 * 
	 * @return creationDate message
	 */
	public Message getLastMessage() {
		return last();
	}

}