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

import game.rushhour.area.Area;

import java.util.Comparator;
import java.util.List;

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
 * Parking generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public abstract class GenParkings extends Entities<Parking> {

	private static final long serialVersionUID = 1174415643198L;

	private static Log log = LogFactory.getLog(GenParkings.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Area area;

	/* ======= base constructor ======= */

	/**
	 * Constructs parkings within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenParkings(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs parkings for the area parent.
	 * 
	 * @param area
	 *            area
	 */
	public GenParkings(Area area) {
		this(area.getModel());
		// parent
		setArea(area);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the parking with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return parking
	 */
	public Parking getParking(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the parking with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return parking
	 */
	public Parking getParking(Long oidUniqueNumber) {
		return getParking(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first parking whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return parking
	 */
	public Parking getParking(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects parkings whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return parkings
	 */
	public Parkings getParkings(String propertyCode, Object property) {
		return (Parkings) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets parkings ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered parkings
	 */
	public Parkings getParkings(String propertyCode, boolean ascending) {
		return (Parkings) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets parkings selected by a selector. Returns empty parkings if there are
	 * no parkings that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected parkings
	 */
	public Parkings getParkings(ISelector selector) {
		return (Parkings) selectBySelector(selector);
	}

	/**
	 * Gets parkings ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered parkings
	 */
	public Parkings getParkings(Comparator comparator, boolean ascending) {
		return (Parkings) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets number parking.
	 * 
	 * @param number
	 *            number
	 * @return number parking
	 */
	public Parking getNumberParking(Integer number) {
		PropertySelector propertySelector = new PropertySelector("number");
		propertySelector.defineEqual(number);
		List<Parking> list = getParkings(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets area parkings.
	 * 
	 * @param area
	 *            area oid unique number
	 * @return area parkings
	 */
	public Parkings getAreaParkings(Long area) {
		PropertySelector propertySelector = new PropertySelector("areaOid");
		propertySelector.defineEqual(area);
		return getParkings(propertySelector);
	}

	/**
	 * Gets area parkings.
	 * 
	 * @param area
	 *            area oid
	 * @return area parkings
	 */
	public Parkings getAreaParkings(Oid area) {
		return getAreaParkings(area.getUniqueNumber());
	}

	/**
	 * Gets area parkings.
	 * 
	 * @param area
	 *            area
	 * @return area parkings
	 */
	public Parkings getAreaParkings(Area area) {
		return getAreaParkings(area.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets parkings ordered by number.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered parkings
	 */
	public Parkings getParkingsOrderedByNumber(boolean ascending) {
		return getParkings("number", ascending);
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

	/**
	 * Sets area.
	 * 
	 * @param area
	 *            area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Gets area.
	 * 
	 * @return area
	 */
	public Area getArea() {
		return area;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Parking parking) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(parking)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Area area = getArea();
				if (area == null) {
					Area parkingArea = parking.getArea();
					if (parkingArea != null) {
						if (!parkingArea.getParkings().contain(parking)) {
							parkingArea.getParkings().setPropagateToSource(
									false);
							post = parkingArea.getParkings().add(parking);
							parkingArea.getParkings()
									.setPropagateToSource(true);
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

	protected boolean postRemove(Parking parking) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(parking)) {
			Area area = getArea();
			if (area == null) {
				Area parkingArea = parking.getArea();
				if (parkingArea != null) {
					if (parkingArea.getParkings().contain(parking)) {
						parkingArea.getParkings().setPropagateToSource(false);
						post = parkingArea.getParkings().remove(parking);
						parkingArea.getParkings().setPropagateToSource(true);
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

	protected boolean postUpdate(Parking beforeParking, Parking afterParking) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeParking, afterParking)) {
			Area area = getArea();
			if (area == null) {
				Area beforeParkingArea = beforeParking.getArea();
				Area afterParkingArea = afterParking.getArea();
				if (beforeParkingArea == null && afterParkingArea != null) {
					// attach
					if (!afterParkingArea.getParkings().contain(afterParking)) {
						afterParkingArea.getParkings().setPropagateToSource(
								false);
						post = afterParkingArea.getParkings().add(afterParking);
						afterParkingArea.getParkings().setPropagateToSource(
								true);
					}
				} else if (beforeParkingArea != null
						&& afterParkingArea == null) {
					// detach
					if (beforeParkingArea.getParkings().contain(beforeParking)) {
						beforeParkingArea.getParkings().setPropagateToSource(
								false);
						post = beforeParkingArea.getParkings().remove(
								beforeParking);
						beforeParkingArea.getParkings().setPropagateToSource(
								true);
					}
				} else if (beforeParkingArea != null
						&& afterParkingArea != null
						&& beforeParkingArea != afterParkingArea) {
					// detach
					if (beforeParkingArea.getParkings().contain(beforeParking)) {
						beforeParkingArea.getParkings().setPropagateToSource(
								false);
						post = beforeParkingArea.getParkings().remove(
								beforeParking);
						beforeParkingArea.getParkings().setPropagateToSource(
								true);
					}
					// attach
					if (!afterParkingArea.getParkings().contain(afterParking)) {
						afterParkingArea.getParkings().setPropagateToSource(
								false);
						post = afterParkingArea.getParkings().add(afterParking);
						afterParkingArea.getParkings().setPropagateToSource(
								true);
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
	 * Creates parking.
	 * 
	 * @param areaParent
	 *            area parent
	 * @param number
	 *            number
	 * @return parking
	 */
	public Parking createParking(Area areaParent, Integer number) {
		Parking parking = new Parking(getModel());
		parking.setArea(areaParent);
		parking.setNumber(number);
		if (!add(parking)) {
			parking = null;
		}
		return parking;
	}

}