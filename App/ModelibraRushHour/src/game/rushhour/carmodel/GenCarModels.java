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

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * CarModel generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenCarModels extends Entities<CarModel> {

	private static final long serialVersionUID = 1174415685657L;

	private static Log log = LogFactory.getLog(GenCarModels.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs carModels within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCarModels(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the carModel with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return carModel
	 */
	public CarModel getCarModel(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the carModel with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return carModel
	 */
	public CarModel getCarModel(Long oidUniqueNumber) {
		return getCarModel(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first carModel whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return carModel
	 */
	public CarModel getCarModel(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects carModels whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return carModels
	 */
	public CarModels getCarModels(String propertyCode, Object property) {
		return (CarModels) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets carModels ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModels(String propertyCode, boolean ascending) {
		return (CarModels) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets carModels selected by a selector. Returns empty carModels if there
	 * are no carModels that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected carModels
	 */
	public CarModels getCarModels(ISelector selector) {
		return (CarModels) selectBySelector(selector);
	}

	/**
	 * Gets carModels ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModels(Comparator comparator, boolean ascending) {
		return (CarModels) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets length carModels.
	 * 
	 * @param length
	 *            length
	 * @return length carModels
	 */
	public CarModels getLengthCarModels(Integer length) {
		PropertySelector propertySelector = new PropertySelector("length");
		propertySelector.defineEqual(length);
		return getCarModels(propertySelector);
	}

	/**
	 * Gets colorName carModels.
	 * 
	 * @param colorName
	 *            colorName
	 * @return colorName carModels
	 */
	public CarModels getColorNameCarModels(String colorName) {
		PropertySelector propertySelector = new PropertySelector("colorName");
		propertySelector.defineEqual(colorName);
		return getCarModels(propertySelector);
	}

	/**
	 * Gets red carModels.
	 * 
	 * @param red
	 *            red
	 * @return red carModels
	 */
	public CarModels getRedCarModels(Integer red) {
		PropertySelector propertySelector = new PropertySelector("red");
		propertySelector.defineEqual(red);
		return getCarModels(propertySelector);
	}

	/**
	 * Gets green carModels.
	 * 
	 * @param green
	 *            green
	 * @return green carModels
	 */
	public CarModels getGreenCarModels(Integer green) {
		PropertySelector propertySelector = new PropertySelector("green");
		propertySelector.defineEqual(green);
		return getCarModels(propertySelector);
	}

	/**
	 * Gets blue carModels.
	 * 
	 * @param blue
	 *            blue
	 * @return blue carModels
	 */
	public CarModels getBlueCarModels(Integer blue) {
		PropertySelector propertySelector = new PropertySelector("blue");
		propertySelector.defineEqual(blue);
		return getCarModels(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets carModels ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByCode(boolean ascending) {
		return getCarModels("code", ascending);
	}

	/**
	 * Gets carModels ordered by length.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByLength(boolean ascending) {
		return getCarModels("length", ascending);
	}

	/**
	 * Gets carModels ordered by colorName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByColorName(boolean ascending) {
		return getCarModels("colorName", ascending);
	}

	/**
	 * Gets carModels ordered by red.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByRed(boolean ascending) {
		return getCarModels("red", ascending);
	}

	/**
	 * Gets carModels ordered by green.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByGreen(boolean ascending) {
		return getCarModels("green", ascending);
	}

	/**
	 * Gets carModels ordered by blue.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered carModels
	 */
	public CarModels getCarModelsOrderedByBlue(boolean ascending) {
		return getCarModels("blue", ascending);
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

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates carModel.
	 * 
	 * @param code
	 *            code
	 * @param length
	 *            length
	 * @param red
	 *            red
	 * @param green
	 *            green
	 * @param blue
	 *            blue
	 * @return carModel
	 */
	public CarModel createCarModel(String code, Integer length, Integer red,
			Integer green, Integer blue) {
		CarModel carModel = new CarModel(getModel());
		carModel.setCode(code);
		carModel.setLength(length);
		carModel.setRed(red);
		carModel.setGreen(green);
		carModel.setBlue(blue);
		if (!add(carModel)) {
			carModel = null;
		}
		return carModel;
	}

}