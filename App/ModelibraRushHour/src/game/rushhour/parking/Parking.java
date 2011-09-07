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
package game.rushhour.parking;

import game.rushhour.area.Area;
import game.rushhour.car.Car;
import game.rushhour.car.Cars;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/**
 * Parking specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class Parking extends GenParking {

	private static final long serialVersionUID = 1174415643199L;

	private static Log log = LogFactory.getLog(Parking.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs parking within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Parking(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs parking within its parent(s).
	 * 
	 * @param Area
	 *            area
	 */
	public Parking(Area area) {
		super(area);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private Car selectedCar;

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public boolean isSpaceFree(CarSpace space) {
		boolean result = true;
		Cars cars = getCars();
		for (Car car : cars) {
			if (car.inSpace(space)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public void returnCarsToStartSpaces() {
		Cars cars = getCars();
		Car car = cars.first();
		for (int i = 0; i < cars.size(); i++) {
			car.setCurrentSpace(car.getStartSpace());
			car = cars.next(car);
		}
	}

}