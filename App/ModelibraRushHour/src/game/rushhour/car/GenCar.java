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
package game.rushhour.car;

import game.rushhour.RushHour;
import game.rushhour.carmodel.CarModel;
import game.rushhour.carmodel.CarModels;
import game.rushhour.parking.Parking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Car generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenCar extends Entity<Car> {

	private static final long serialVersionUID = 1174415652949L;

	private static Log log = LogFactory.getLog(GenCar.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String orientation;

	private Integer startRow;

	private Integer startColumn;

	private Integer currentRow;

	private Integer currentColumn;

	/* ======= reference properties ======= */

	private Long carModelOid;

	/* ======= internal parent neighbors ======= */

	private Parking parking;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private CarModel carModel;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs car within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCar(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs car within its parent(s).
	 * 
	 * @param Parking
	 *            parking
	 * @param CarModel
	 *            carModel
	 */
	public GenCar(Parking parking, CarModel carModel) {
		this(carModel.getModel());
		// parents
		setParking(parking);
		setCarModel(carModel);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets orientation.
	 * 
	 * @param orientation
	 *            orientation
	 */
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	/**
	 * Gets orientation.
	 * 
	 * @return orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Sets startRow.
	 * 
	 * @param startRow
	 *            startRow
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * Gets startRow.
	 * 
	 * @return startRow
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * Sets startColumn.
	 * 
	 * @param startColumn
	 *            startColumn
	 */
	public void setStartColumn(Integer startColumn) {
		this.startColumn = startColumn;
	}

	/**
	 * Gets startColumn.
	 * 
	 * @return startColumn
	 */
	public Integer getStartColumn() {
		return startColumn;
	}

	/**
	 * Sets currentRow.
	 * 
	 * @param currentRow
	 *            currentRow
	 */
	public void setCurrentRow(Integer currentRow) {
		this.currentRow = currentRow;
	}

	/**
	 * Gets currentRow.
	 * 
	 * @return currentRow
	 */
	public Integer getCurrentRow() {
		return currentRow;
	}

	/**
	 * Sets currentColumn.
	 * 
	 * @param currentColumn
	 *            currentColumn
	 */
	public void setCurrentColumn(Integer currentColumn) {
		this.currentColumn = currentColumn;
	}

	/**
	 * Gets currentColumn.
	 * 
	 * @return currentColumn
	 */
	public Integer getCurrentColumn() {
		return currentColumn;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets carModelOid.
	 * 
	 * @param carModelOid
	 *            carModelOid
	 */
	public void setCarModelOid(Long carModelOid) {
		this.carModelOid = carModelOid;
		carModel = null;
	}

	/**
	 * Gets carModelOid.
	 * 
	 * @return carModelOid
	 */
	public Long getCarModelOid() {
		return carModelOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets parking.
	 * 
	 * @param parking
	 *            parking
	 */
	public void setParking(Parking parking) {
		this.parking = parking;
	}

	/**
	 * Gets parking.
	 * 
	 * @return parking
	 */
	public Parking getParking() {
		return parking;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets carModel.
	 * 
	 * @param carModel
	 *            carModel
	 */
	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
		if (carModel != null) {
			carModelOid = carModel.getOid().getUniqueNumber();
		} else {
			carModelOid = null;
		}
	}

	/**
	 * Gets carModel.
	 * 
	 * @return carModel
	 */
	public CarModel getCarModel() {
		if (carModel == null) {
			RushHour rushHour = (RushHour) getModel();
			CarModels carModels = rushHour.getCarModels();
			if (carModelOid != null) {
				carModel = carModels.getCarModel(carModelOid);
			}
		}
		return carModel;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}