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
import game.rushhour.parking.CarSpace;
import game.rushhour.parking.Parking;

import java.awt.Color;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/**
 * Car specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class Car extends GenCar {

	private static final long serialVersionUID = 1174415652951L;

	private static Log log = LogFactory.getLog(Car.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs car within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Car(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs car within its parent(s).
	 * 
	 * @param ParkingBoard
	 *            parking
	 * @param CarModel
	 *            carModel
	 */
	public Car(Parking parking, CarModel carModel) {
		super(parking, carModel);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Color getColor() {
		return getCarModel().getColor();
	}

	public int getLength() {
		return getCarModel().getLength().intValue();
	}

	public void setStartSpace(int row, int column) {
		setStartRow(row);
		setStartColumn(column);
	}

	public void setStartSpace(CarSpace space) {
		setStartSpace(space.getRow(), space.getColumn());
	}

	public CarSpace getStartSpace() {
		return new CarSpace(getStartRow(), getStartColumn());
	}

	public void setCurrentSpace(int row, int column) {
		setCurrentRow(row);
		setCurrentColumn(column);
	}

	public void setCurrentSpace(CarSpace space) {
		setCurrentSpace(space.getRow(), space.getColumn());
	}

	public CarSpace getCurrentSpace() {
		return new CarSpace(getCurrentRow(), getCurrentColumn());
	}

	public CarSpace getRearPlusOneSpace() {
		CarSpace space = null;
		if (getOrientation().equals("Horizontal")) {
			space = getCurrentSpace().getFurtherHorizontalSpace(getLength());
		} else if (getOrientation().equals("Vertical")) {
			space = getCurrentSpace().getFurtherVerticalSpace(getLength());
		}
		return space;
	}

	public CarSpace getHeadMinusOneSpace() {
		CarSpace space = null;
		if (getOrientation().equals("Horizontal")) {
			space = getCurrentSpace().getFurtherHorizontalSpace(-1);
		} else if (getOrientation().equals("Vertical")) {
			space = getCurrentSpace().getFurtherVerticalSpace(-1);
		}
		return space;
	}

	public CarSpace[] getCarSpaces() {
		int carLength = getLength();
		CarSpace space = null;
		CarSpace[] carSpaces = new CarSpace[carLength];
		space = getCurrentSpace();
		carSpaces[0] = space;
		if (getOrientation().equals("Horizontal")) {
			for (int i = 1; i < carLength; i++) {
				space = space.getFurtherHorizontalSpace(1);
				carSpaces[i] = space;
			}
		} else if (getOrientation().equals("Vertical")) {
			for (int i = 1; i < carLength; i++) {
				space = space.getFurtherVerticalSpace(1);
				carSpaces[i] = space;
			}
		}
		return carSpaces;
	}

	public boolean inRow(int row) {
		boolean result = false;
		CarSpace[] carSpaces = this.getCarSpaces();
		for (int i = 0; i < carSpaces.length; i++) {
			if (carSpaces[i].inRow(row)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean inColumn(int column) {
		boolean result = false;
		CarSpace[] carSpaces = this.getCarSpaces();
		for (int i = 0; i < carSpaces.length; i++) {
			if (carSpaces[i].inColumn(column)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean inSpace(CarSpace space) {
		return (inRow(space.getRow())) & (inColumn(space.getColumn()));
	}

}