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
package game.rushhour;

import game.rushhour.car.Car;
import game.rushhour.car.Cars;
import game.rushhour.parking.Parking;
import game.rushhour.parking.Parkings;

import org.modelibra.IDomain;

/**
 * RushHour specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-05-22
 */
public class RushHour extends GenRushHour {

	private static final long serialVersionUID = 1174415568900L;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public RushHour(IDomain domain) {
		super(domain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private Cars cars;

	/**
	 * Gets cars for all parkings.
	 * 
	 * @return all cars
	 */
	public Cars getCars() {
		if (isInitialized()) {
			if (cars == null) {
				setInitialized(false);
				Cars allCars = new Cars(this);
				Parkings parkings = getParkings();
				for (Parking parking : parkings) {
					Cars cars = parking.getCars();
					for (Car car : cars) {
						allCars.add(car);
					}
				}
				cars = allCars;
				setInitialized(true);
			}
		}
		return cars;
	}

}
