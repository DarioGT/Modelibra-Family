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
package travel.impression.traveler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.CompositeComparator;
import org.modelibra.IDomainModel;
import org.modelibra.PropertyComparator;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Traveler specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public class Travelers extends GenTravelers {

	private static final long serialVersionUID = 1189698214162L;

	private static Log log = LogFactory.getLog(Travelers.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs travelers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Travelers(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets traveler ordered by last name then by first name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered traveler
	 */
	public Travelers getTravelersOrderedByLastFirstName(boolean ascending) {
		CompositeComparator compositePropertyComparator = new CompositeComparator(
				new PropertyComparator("lastName"), new PropertyComparator(
						"firstName"));
		return getTravelers(compositePropertyComparator, ascending);
	}

}