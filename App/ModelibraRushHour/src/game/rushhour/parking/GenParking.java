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
package game.rushhour.parking;

import game.rushhour.RushHour;
import game.rushhour.area.Area;
import game.rushhour.area.Areas;
import game.rushhour.car.Cars;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Parking generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenParking extends Entity<Parking> {

	private static final long serialVersionUID = 1174415643197L;

	private static Log log = LogFactory.getLog(GenParking.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	/* ======= reference properties ======= */

	private Long areaOid;

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Cars cars;

	/* ======= external parent neighbors ======= */

	private Area area;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs parking within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenParking(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setCars(new Cars((Parking) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs parking within its parent(s).
	 * 
	 * @param Area
	 *            area
	 */
	public GenParking(Area area) {
		this(area.getModel());
		// parents
		setArea(area);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets number.
	 * 
	 * @param number
	 *            number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets number.
	 * 
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets areaOid.
	 * 
	 * @param areaOid
	 *            areaOid
	 */
	public void setAreaOid(Long areaOid) {
		this.areaOid = areaOid;
		area = null;
	}

	/**
	 * Gets areaOid.
	 * 
	 * @return areaOid
	 */
	public Long getAreaOid() {
		return areaOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets cars.
	 * 
	 * @param cars
	 *            cars
	 */
	public void setCars(Cars cars) {
		this.cars = cars;
		if (cars != null) {
			cars.setParking((Parking) this);
		}
	}

	/**
	 * Gets cars.
	 * 
	 * @return cars
	 */
	public Cars getCars() {
		return cars;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets area.
	 * 
	 * @param area
	 *            area
	 */
	public void setArea(Area area) {
		this.area = area;
		if (area != null) {
			areaOid = area.getOid().getUniqueNumber();
		} else {
			areaOid = null;
		}
	}

	/**
	 * Gets area.
	 * 
	 * @return area
	 */
	public Area getArea() {
		if (area == null) {
			RushHour rushHour = (RushHour) getModel();
			Areas areas = rushHour.getAreas();
			if (areaOid != null) {
				area = areas.getArea(areaOid);
			}
		}
		return area;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}