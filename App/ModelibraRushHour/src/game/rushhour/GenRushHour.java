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
package game.rushhour;

import game.rushhour.area.Areas;
import game.rushhour.carmodel.CarModels;
import game.rushhour.parking.Parkings;

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

/**
 * RushHour generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenRushHour extends DomainModel {

	private static final long serialVersionUID = 1174415568899L;

	private Parkings parkings;

	private CarModels carModels;

	private Areas areas;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenRushHour(IDomain domain) {
		super(domain);
		parkings = new Parkings(this);
		carModels = new CarModels(this);
		areas = new Areas(this);
	}

	/**
	 * Gets Parking entities.
	 * 
	 * @return Parking entities
	 */
	public Parkings getParkings() {
		return parkings;
	}

	/**
	 * Gets CarModel entities.
	 * 
	 * @return CarModel entities
	 */
	public CarModels getCarModels() {
		return carModels;
	}

	/**
	 * Gets Area entities.
	 * 
	 * @return Area entities
	 */
	public Areas getAreas() {
		return areas;
	}

}
