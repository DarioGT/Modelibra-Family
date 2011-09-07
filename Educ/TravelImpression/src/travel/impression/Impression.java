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
package travel.impression;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;

import travel.impression.message.Message;
import travel.impression.message.Messages;
import travel.impression.traveler.Traveler;
import travel.impression.traveler.Travelers;

/**
 * Impression specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public class Impression extends GenImpression {

	private static final long serialVersionUID = 1190053285159L;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Impression(IDomain domain) {
		super(domain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private static Log log = LogFactory.getLog(Impression.class);

	/**
	 * Gets Message entities.
	 * 
	 * @return Message entities
	 */
	public Messages getMessages() {
		Messages allMessages = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allMessages = new Messages(this);
				allMessages.setPersistent(false);
				allMessages.setPre(false);
				allMessages.setPost(false);
				Travelers travelers = getTravelers();
				for (Traveler traveler : travelers) {
					Messages travelerMessages = traveler.getMessages();
					for (Message message : travelerMessages) {
						allMessages.add(message);
					}
				}
			} catch (Exception e) {
				log.error("Error in Impression.getMessages: " + e.getMessage());
			} finally {
				allMessages.setPersistent(true);
				allMessages.setPre(true);
				allMessages.setPost(true);
			}
		}
		return allMessages;
	}

}
