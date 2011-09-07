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
package travel.impression.url;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import travel.impression.place.Place;

/* ======= import external parent entity and entities classes ======= */

/**
 * Url specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public class Urls extends GenUrls {

	private static final long serialVersionUID = 1192739599328L;

	private static Log log = LogFactory.getLog(Urls.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs urls within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Urls(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs urls for the place parent.
	 * 
	 * @param place
	 *            place
	 */
	public Urls(Place place) {
		super(place);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}