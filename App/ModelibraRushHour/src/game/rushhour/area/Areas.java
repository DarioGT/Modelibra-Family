/*
 * dmLite -- Domain Model Lite
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
package game.rushhour.area;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Area specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-20
 */
public class Areas extends GenAreas {

	private static final long serialVersionUID = 1174415701630L;

	private static Log log = LogFactory.getLog(Areas.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs areas within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Areas(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Area getArea(int number) {
		int count = 0;
		for (Area area : this) {
			if (number == count) {
				return area;
			}
			count++;
		}
		return null;
	}

}