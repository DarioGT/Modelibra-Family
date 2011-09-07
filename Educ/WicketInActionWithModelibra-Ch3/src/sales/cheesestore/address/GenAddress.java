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
package sales.cheesestore.address;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import sales.cheesestore.CheeseStore;
import sales.cheesestore.cart.Carts;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Address generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenAddress extends Entity<Address> {

	private static final long serialVersionUID = 1231169511158L;

	private static Log log = LogFactory.getLog(GenAddress.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String street;

	private String city;

	private String zipcode;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Carts carts;

	/* ======= base constructor ======= */

	/**
	 * Constructs address within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAddress(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets street.
	 * 
	 * @param street
	 *            street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets street.
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets city.
	 * 
	 * @param city
	 *            city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets zipcode.
	 * 
	 * @param zipcode
	 *            zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets zipcode.
	 * 
	 * @return zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets carts.
	 * 
	 * @param carts
	 *            carts
	 */
	public void setCarts(Carts carts) {
		this.carts = carts;
		if (carts != null) {
			carts.setAddress((Address) this);
		}
	}

	/**
	 * Gets carts.
	 * 
	 * @return carts
	 */
	public Carts getCarts() {
		if (carts == null) {
			CheeseStore cheeseStore = (CheeseStore) getModel();
			Carts carts = cheeseStore.getCarts();
			setCarts(carts.getAddressCarts((Address) this));
		}
		return carts;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}