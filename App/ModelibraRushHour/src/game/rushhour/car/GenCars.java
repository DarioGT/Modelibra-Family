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

import game.rushhour.carmodel.CarModel;
import game.rushhour.parking.Parking;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Car generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenCars extends Entities<Car> {

	private static final long serialVersionUID = 1174415652950L;

	private static Log log = LogFactory.getLog(GenCars.class);

	/* ======= internal parent neighbors ======= */

	private Parking parking;

	/* ======= external parent neighbors ======= */

	private CarModel carModel;

	/* ======= base constructor ======= */

	/**
	 * Constructs cars within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCars(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs cars for the parking parent.
	 * 
	 * @param parking
	 *            parking
	 */
	public GenCars(Parking parking) {
		this(parking.getModel());
		// parent
		setParking(parking);
	}

	/**
	 * Constructs cars for the carModel parent.
	 * 
	 * @param carModel
	 *            carModel
	 */
	public GenCars(CarModel carModel) {
		this(carModel.getModel());
		// parent
		setCarModel(carModel);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the car with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return car
	 */
	public Car getCar(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the car with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return car
	 */
	public Car getCar(Long oidUniqueNumber) {
		return getCar(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first car whose property with a property code is equal to a
	 * property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return car
	 */
	public Car getCar(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects cars whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cars
	 */
	public Cars getCars(String propertyCode, Object property) {
		return (Cars) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets cars ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cars
	 */
	public Cars getCars(String propertyCode, boolean ascending) {
		return (Cars) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets cars selected by a selector. Returns empty cars if there are no cars
	 * that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected cars
	 */
	public Cars getCars(ISelector selector) {
		return (Cars) selectBySelector(selector);
	}

	/**
	 * Gets cars ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cars
	 */
	public Cars getCars(Comparator comparator, boolean ascending) {
		return (Cars) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets orientation cars.
	 * 
	 * @param orientation
	 *            orientation
	 * @return orientation cars
	 */
	public Cars getOrientationCars(String orientation) {
		PropertySelector propertySelector = new PropertySelector("orientation");
		propertySelector.defineEqual(orientation);
		return getCars(propertySelector);
	}

	/**
	 * Gets startRow cars.
	 * 
	 * @param startRow
	 *            startRow
	 * @return startRow cars
	 */
	public Cars getStartRowCars(Integer startRow) {
		PropertySelector propertySelector = new PropertySelector("startRow");
		propertySelector.defineEqual(startRow);
		return getCars(propertySelector);
	}

	/**
	 * Gets startColumn cars.
	 * 
	 * @param startColumn
	 *            startColumn
	 * @return startColumn cars
	 */
	public Cars getStartColumnCars(Integer startColumn) {
		PropertySelector propertySelector = new PropertySelector("startColumn");
		propertySelector.defineEqual(startColumn);
		return getCars(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets carModel cars.
	 * 
	 * @param carModel
	 *            carModel oid unique number
	 * @return carModel cars
	 */
	public Cars getCarModelCars(Long carModel) {
		PropertySelector propertySelector = new PropertySelector("carModelOid");
		propertySelector.defineEqual(carModel);
		return getCars(propertySelector);
	}

	/**
	 * Gets carModel cars.
	 * 
	 * @param carModel
	 *            carModel oid
	 * @return carModel cars
	 */
	public Cars getCarModelCars(Oid carModel) {
		return getCarModelCars(carModel.getUniqueNumber());
	}

	/**
	 * Gets carModel cars.
	 * 
	 * @param carModel
	 *            carModel
	 * @return carModel cars
	 */
	public Cars getCarModelCars(CarModel carModel) {
		return getCarModelCars(carModel.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets cars ordered by orientation.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered cars
	 */
	public Cars getCarsOrderedByOrientation(boolean ascending) {
		return getCars("orientation", ascending);
	}

	/**
	 * Gets cars ordered by startRow.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered cars
	 */
	public Cars getCarsOrderedByStartRow(boolean ascending) {
		return getCars("startRow", ascending);
	}

	/**
	 * Gets cars ordered by startColumn.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered cars
	 */
	public Cars getCarsOrderedByStartColumn(boolean ascending) {
		return getCars("startColumn", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

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

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets carModel.
	 * 
	 * @param carModel
	 *            carModel
	 */
	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	/**
	 * Gets carModel.
	 * 
	 * @return carModel
	 */
	public CarModel getCarModel() {
		return carModel;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Car car) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(car)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				CarModel carModel = getCarModel();
				if (carModel == null) {
					CarModel carCarModel = car.getCarModel();
					if (carCarModel != null) {
						if (!carCarModel.getCars().contain(car)) {
							carCarModel.getCars().setPropagateToSource(false);
							post = carCarModel.getCars().add(car);
							carCarModel.getCars().setPropagateToSource(true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(Car car) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(car)) {
			CarModel carModel = getCarModel();
			if (carModel == null) {
				CarModel carCarModel = car.getCarModel();
				if (carCarModel != null) {
					if (carCarModel.getCars().contain(car)) {
						carCarModel.getCars().setPropagateToSource(false);
						post = carCarModel.getCars().remove(car);
						carCarModel.getCars().setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(Car beforeCar, Car afterCar) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeCar, afterCar)) {
			CarModel carModel = getCarModel();
			if (carModel == null) {
				CarModel beforeCarCarModel = beforeCar.getCarModel();
				CarModel afterCarCarModel = afterCar.getCarModel();
				if (beforeCarCarModel == null && afterCarCarModel != null) {
					// attach
					if (!afterCarCarModel.getCars().contain(afterCar)) {
						afterCarCarModel.getCars().setPropagateToSource(false);
						post = afterCarCarModel.getCars().add(afterCar);
						afterCarCarModel.getCars().setPropagateToSource(true);
					}
				} else if (beforeCarCarModel != null
						&& afterCarCarModel == null) {
					// detach
					if (beforeCarCarModel.getCars().contain(beforeCar)) {
						beforeCarCarModel.getCars().setPropagateToSource(false);
						post = beforeCarCarModel.getCars().remove(beforeCar);
						beforeCarCarModel.getCars().setPropagateToSource(true);
					}
				} else if (beforeCarCarModel != null
						&& afterCarCarModel != null
						&& beforeCarCarModel != afterCarCarModel) {
					// detach
					if (beforeCarCarModel.getCars().contain(beforeCar)) {
						beforeCarCarModel.getCars().setPropagateToSource(false);
						post = beforeCarCarModel.getCars().remove(beforeCar);
						beforeCarCarModel.getCars().setPropagateToSource(true);
					}
					// attach
					if (!afterCarCarModel.getCars().contain(afterCar)) {
						afterCarCarModel.getCars().setPropagateToSource(false);
						post = afterCarCarModel.getCars().add(afterCar);
						afterCarCarModel.getCars().setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates car.
	 * 
	 * @param parkingParent
	 *            parking parent
	 * @param carModelParent
	 *            carModel parent
	 * @param startRow
	 *            startRow
	 * @param startColumn
	 *            startColumn
	 * @return car
	 */
	public Car createCar(Parking parkingParent, CarModel carModelParent,
			Integer startRow, Integer startColumn) {
		Car car = new Car(getModel());
		car.setParking(parkingParent);
		car.setCarModel(carModelParent);
		car.setStartRow(startRow);
		car.setStartColumn(startColumn);
		if (!add(car)) {
			car = null;
		}
		return car;
	}

}