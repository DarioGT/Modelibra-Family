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
package game.rushhour.area;

import game.rushhour.RushHour;
import game.rushhour.parking.Parkings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Area generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenArea extends Entity<Area> {

	private static final long serialVersionUID = 1174415701627L;

	private static Log log = LogFactory.getLog(GenArea.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Parkings parkings;

	/* ======= base constructor ======= */

	/**
	 * Constructs area within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenArea(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets parkings.
	 * 
	 * @param parkings
	 *            parkings
	 */
	public void setParkings(Parkings parkings) {
		this.parkings = parkings;
		if (parkings != null) {
			parkings.setArea((Area) this);
		}
	}

	/**
	 * Gets parkings.
	 * 
	 * @return parkings
	 */
	public Parkings getParkings() {
		if (parkings == null) {
			RushHour rushHour = (RushHour) getModel();
			Parkings parkings = rushHour.getParkings();
			setParkings(parkings.getAreaParkings((Area) this));
		}
		return parkings;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}