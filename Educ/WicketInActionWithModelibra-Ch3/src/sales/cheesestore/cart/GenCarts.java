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

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import sales.cheesestore.address.Address;
import sales.cheesestore.cartcheese.CartCheese;
import sales.cheesestore.cartcheese.CartCheeses;
import sales.cheesestore.cheese.Cheese;

/**
 * Cart generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenCarts extends Entities<Cart> {

	private static final long serialVersionUID = 1231169447921L;

	private static Log log = LogFactory.getLog(GenCarts.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Address address;

	/* ======= base constructor ======= */

	/**
	 * Constructs carts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCarts(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs carts for the address parent.
	 * 
	 * @param address
	 *            address
	 */
	public GenCarts(Address address) {
		this(address.getModel());
		// parent
		setAddress(address);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the cart with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return cart
	 */
	public Cart getCart(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the cart with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return cart
	 */
	public Cart getCart(Long oidUniqueNumber) {
		return getCart(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first cart whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cart
	 */
	public Cart getCart(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects carts whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return carts
	 */
	public Carts getCarts(String propertyCode, Object property) {
		return (Carts) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets carts ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered carts
	 */
	public Carts getCarts(String propertyCode, boolean ascending) {
		return (Carts) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets carts selected by a selector. Returns empty carts if there are no
	 * carts that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected carts
	 */
	public Carts getCarts(ISelector selector) {
		return (Carts) selectBySelector(selector);
	}

	/**
	 * Gets carts ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered carts
	 */
	public Carts getCarts(Comparator comparator, boolean ascending) {
		return (Carts) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets address carts.
	 * 
	 * @param address
	 *            address oid unique number
	 * @return address carts
	 */
	public Carts getAddressCarts(Long address) {
		PropertySelector propertySelector = new PropertySelector("addressOid");
		propertySelector.defineEqual(address);
		return getCarts(propertySelector);
	}

	/**
	 * Gets address carts.
	 * 
	 * @param address
	 *            address oid
	 * @return address carts
	 */
	public Carts getAddressCarts(Oid address) {
		return getAddressCarts(address.getUniqueNumber());
	}

	/**
	 * Gets address carts.
	 * 
	 * @param address
	 *            address
	 * @return address carts
	 */
	public Carts getAddressCarts(Address address) {
		return getAddressCarts(address.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets cheese cartCheeses.
	 * 
	 * @return cheese cartCheeses
	 */
	public CartCheeses getCheeseCartCheeses(Cheese cheese) {
		CartCheeses cartCheeses = new CartCheeses(cheese);
		cartCheeses.setPersistent(false);
		for (Cart cart : this) {
			CartCheese cartCheese = cart.getCartCheeses().getCartCheese(cart,
					cheese);
			if (cartCheese != null) {
				cartCheeses.add(cartCheese);
			}
		}
		return cartCheeses;
	}

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets address.
	 * 
	 * @param address
	 *            address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets address.
	 * 
	 * @return address
	 */
	public Address getAddress() {
		return address;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Cart cart) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(cart)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Address address = getAddress();
				if (address == null) {
					Address cartAddress = cart.getAddress();
					if (cartAddress != null) {
						if (!cartAddress.getCarts().contain(cart)) {
							cartAddress.getCarts().setPropagateToSource(false);
							post = cartAddress.getCarts().add(cart);
							cartAddress.getCarts().setPropagateToSource(true);
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

	protected boolean postRemove(Cart cart) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(cart)) {
			Address address = getAddress();
			if (address == null) {
				Address cartAddress = cart.getAddress();
				if (cartAddress != null) {
					if (cartAddress.getCarts().contain(cart)) {
						cartAddress.getCarts().setPropagateToSource(false);
						post = cartAddress.getCarts().remove(cart);
						cartAddress.getCarts().setPropagateToSource(true);
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

	protected boolean postUpdate(Cart beforeCart, Cart afterCart) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeCart, afterCart)) {
			Address address = getAddress();
			if (address == null) {
				Address beforeCartAddress = beforeCart.getAddress();
				Address afterCartAddress = afterCart.getAddress();
				if (beforeCartAddress == null && afterCartAddress != null) {
					// attach
					if (!afterCartAddress.getCarts().contain(afterCart)) {
						afterCartAddress.getCarts().setPropagateToSource(false);
						post = afterCartAddress.getCarts().add(afterCart);
						afterCartAddress.getCarts().setPropagateToSource(true);
					}
				} else if (beforeCartAddress != null
						&& afterCartAddress == null) {
					// detach
					if (beforeCartAddress.getCarts().contain(beforeCart)) {
						beforeCartAddress.getCarts()
								.setPropagateToSource(false);
						post = beforeCartAddress.getCarts().remove(beforeCart);
						beforeCartAddress.getCarts().setPropagateToSource(true);
					}
				} else if (beforeCartAddress != null
						&& afterCartAddress != null
						&& beforeCartAddress != afterCartAddress) {
					// detach
					if (beforeCartAddress.getCarts().contain(beforeCart)) {
						beforeCartAddress.getCarts()
								.setPropagateToSource(false);
						post = beforeCartAddress.getCarts().remove(beforeCart);
						beforeCartAddress.getCarts().setPropagateToSource(true);
					}
					// attach
					if (!afterCartAddress.getCarts().contain(afterCart)) {
						afterCartAddress.getCarts().setPropagateToSource(false);
						post = afterCartAddress.getCarts().add(afterCart);
						afterCartAddress.getCarts().setPropagateToSource(true);
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
	 * Creates cart.
	 * 
	 * @param addressParent
	 *            address parent
	 * @return cart
	 */
	public Cart createCart(Address addressParent) {
		Cart cart = new Cart(getModel());
		cart.setAddress(addressParent);
		if (!add(cart)) {
			cart = null;
		}
		return cart;
	}

}