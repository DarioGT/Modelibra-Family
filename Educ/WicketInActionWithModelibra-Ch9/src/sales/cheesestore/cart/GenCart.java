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
package sales.cheesestore.cart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import sales.cheesestore.CheeseStore;
import sales.cheesestore.address.Address;
import sales.cheesestore.address.Addresses;
import sales.cheesestore.cartcheese.CartCheeses;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Cart generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenCart extends Entity<Cart> {

	private static final long serialVersionUID = 1231169447920L;

	private static Log log = LogFactory.getLog(GenCart.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	/* ======= reference properties ======= */

	private Long addressOid;

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private CartCheeses cartCheeses;

	/* ======= external parent neighbors ======= */

	private Address address;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs cart within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCart(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setCartCheeses(new CartCheeses((Cart) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs cart within its parent(s).
	 * 
	 * @param Address
	 *            address
	 */
	public GenCart(Address address) {
		this(address.getModel());
		// parents
		setAddress(address);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets addressOid.
	 * 
	 * @param addressOid
	 *            addressOid
	 */
	public void setAddressOid(Long addressOid) {
		this.addressOid = addressOid;
		address = null;
	}

	/**
	 * Gets addressOid.
	 * 
	 * @return addressOid
	 */
	public Long getAddressOid() {
		return addressOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets cartCheeses.
	 * 
	 * @param cartCheeses
	 *            cartCheeses
	 */
	public void setCartCheeses(CartCheeses cartCheeses) {
		this.cartCheeses = cartCheeses;
		if (cartCheeses != null) {
			cartCheeses.setCart((Cart) this);
		}
	}

	/**
	 * Gets cartCheeses.
	 * 
	 * @return cartCheeses
	 */
	public CartCheeses getCartCheeses() {
		return cartCheeses;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets address.
	 * 
	 * @param address
	 *            address
	 */
	public void setAddress(Address address) {
		this.address = address;
		if (address != null) {
			addressOid = address.getOid().getUniqueNumber();
		} else {
			addressOid = null;
		}
	}

	/**
	 * Gets address.
	 * 
	 * @return address
	 */
	public Address getAddress() {
		if (address == null) {
			CheeseStore cheeseStore = (CheeseStore) getModel();
			Addresses addresses = cheeseStore.getAddresses();
			if (addressOid != null) {
				address = addresses.getAddress(addressOid);
			}
		}
		return address;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}