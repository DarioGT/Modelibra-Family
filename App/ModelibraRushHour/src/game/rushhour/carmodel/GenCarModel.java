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
package game.rushhour.carmodel;

import game.rushhour.RushHour;
import game.rushhour.car.Cars;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * CarModel generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenCarModel extends Entity<CarModel> {

	private static final long serialVersionUID = 1174415685656L;

	private static Log log = LogFactory.getLog(GenCarModel.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer length;

	private String colorName;

	private Integer red;

	private Integer green;

	private Integer blue;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Cars cars;

	/* ======= base constructor ======= */

	/**
	 * Constructs carModel within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCarModel(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets length.
	 * 
	 * @param length
	 *            length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Gets length.
	 * 
	 * @return length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Sets colorName.
	 * 
	 * @param colorName
	 *            colorName
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	/**
	 * Gets colorName.
	 * 
	 * @return colorName
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * Sets red.
	 * 
	 * @param red
	 *            red
	 */
	public void setRed(Integer red) {
		this.red = red;
	}

	/**
	 * Gets red.
	 * 
	 * @return red
	 */
	public Integer getRed() {
		return red;
	}

	/**
	 * Sets green.
	 * 
	 * @param green
	 *            green
	 */
	public void setGreen(Integer green) {
		this.green = green;
	}

	/**
	 * Gets green.
	 * 
	 * @return green
	 */
	public Integer getGreen() {
		return green;
	}

	/**
	 * Sets blue.
	 * 
	 * @param blue
	 *            blue
	 */
	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	/**
	 * Gets blue.
	 * 
	 * @return blue
	 */
	public Integer getBlue() {
		return blue;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets cars.
	 * 
	 * @param cars
	 *            cars
	 */
	public void setCars(Cars cars) {
		this.cars = cars;
		if (cars != null) {
			cars.setCarModel((CarModel) this);
		}
	}

	/**
	 * Gets cars.
	 * 
	 * @return cars
	 */
	public Cars getCars() {
		if (cars == null) {
			RushHour rushHour = (RushHour) getModel();
			Cars cars = rushHour.getCars();
			setCars(cars.getCarModelCars((CarModel) this));
		}
		return cars;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}