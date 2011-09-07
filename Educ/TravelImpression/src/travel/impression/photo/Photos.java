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

/* ======= import external parent entity and entities classes ======= */

/**
 * Photo specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-18
 */
public class Photos extends GenPhotos {

	private static final long serialVersionUID = 1189698236080L;

	private static Log log = LogFactory.getLog(Photos.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs photos within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Photos(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs photos for the message parent.
	 * 
	 * @param message
	 *            message
	 */
	public Photos(Message message) {
		super(message);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}