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
package game.rushhour.car;

import game.rushhour.carmodel.CarModel;
import game.rushhour.parking.Parking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/**
 * Car specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-20
 */
public class Cars extends GenCars {

	private static final long serialVersionUID = 1174415652952L;

	private static Log log = LogFactory.getLog(Cars.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs cars within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Cars(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs cars for the parking parent.
	 * 
	 * @param parking
	 *            parking
	 */
	public Cars(Parking parking) {
		super(parking);
	}

	/**
	 * Constructs cars for the carModel parent.
	 * 
	 * @param carModel
	 *            carModel
	 */
	public Cars(CarModel carModel) {
		super(carModel);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}