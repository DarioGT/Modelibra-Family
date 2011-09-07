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
package sales.cheesestore.cartcheese;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

import sales.cheesestore.cart.Cart;
import sales.cheesestore.cheese.Cheese;

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * CartCheese generated entities. This class should not be changed manually. Use
 * a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenCartCheeses extends Entities<CartCheese> {

	private static final long serialVersionUID = 1231169481733L;

	private static Log log = LogFactory.getLog(GenCartCheeses.class);

	/* ======= internal parent neighbors ======= */

	private Cart cart;

	/* ======= external parent neighbors ======= */

	private Cheese cheese;

	/* ======= base constructor ======= */

	/**
	 * Constructs cartCheeses within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCartCheeses(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs cartCheeses for the cart parent.
	 * 
	 * @param cart
	 *            cart
	 */
	public GenCartCheeses(Cart cart) {
		this(cart.getModel());
		// parent
		setCart(cart);
	}

	/**
	 * Constructs cartCheeses for the cheese parent.
	 * 
	 * @param cheese
	 *            cheese
	 */
	public GenCartCheeses(Cheese cheese) {
		this(cheese.getModel());
		// parent
		setCheese(cheese);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the cartCheese with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return cartCheese
	 */
	public CartCheese getCartCheese(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the cartCheese with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return cartCheese
	 */
	public CartCheese getCartCheese(Long oidUniqueNumber) {
		return getCartCheese(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first cartCheese whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cartCheese
	 */
	public CartCheese getCartCheese(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects cartCheeses whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return cartCheeses
	 */
	public CartCheeses getCartCheeses(String propertyCode, Object property) {
		return (CartCheeses) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets cartCheeses ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cartCheeses
	 */
	public CartCheeses getCartCheeses(String propertyCode, boolean ascending) {
		return (CartCheeses) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets cartCheeses selected by a selector. Returns empty cartCheeses if
	 * there are no cartCheeses that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected cartCheeses
	 */
	public CartCheeses getCartCheeses(ISelector selector) {
		return (CartCheeses) selectBySelector(selector);
	}

	/**
	 * Gets cartCheeses ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered cartCheeses
	 */
	public CartCheeses getCartCheeses(Comparator comparator, boolean ascending) {
		return (CartCheeses) orderByComparator(comparator, ascending);
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

	/* ======= order methods for essential properties ======= */

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/**
	 * Gets cartCheese based on many-to-many parents.
	 * 
	 * @param Cart
	 *            cart
	 * @param Cheese
	 *            cheese
	 */
	public CartCheese getCartCheese(Cart cart, Cheese cheese) {
		for (CartCheese cartCheese : this) {
			if (cartCheese.getCart() == cart
					&& cartCheese.getCheese() == cheese) {
				return cartCheese;
			}
		}
		return null;
	}

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets cart.
	 * 
	 * @param cart
	 *            cart
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * Gets cart.
	 * 
	 * @return cart
	 */
	public Cart getCart() {
		return cart;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets cheese.
	 * 
	 * @param cheese
	 *            cheese
	 */
	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
	}

	/**
	 * Gets cheese.
	 * 
	 * @return cheese
	 */
	public Cheese getCheese() {
		return cheese;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	protected boolean postAdd(CartCheese cartCheese) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(cartCheese)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Cart cart = getCart();
				if (cart == null) {
					Cart cartCheeseCart = cartCheese.getCart();
					if (!cartCheeseCart.getCartCheeses().contain(cartCheese)) {
						post = cartCheeseCart.getCartCheeses().add(cartCheese);
					}
				}
				Cheese cheese = getCheese();
				if (cheese == null) {
					Cheese cartCheeseCheese = cartCheese.getCheese();
					if (!cartCheeseCheese.getCartCheeses().contain(cartCheese)) {
						post = cartCheeseCheese.getCartCheeses()
								.add(cartCheese);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post remove propagation ======= */

	protected boolean postRemove(CartCheese cartCheese) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(cartCheese)) {
			Cart cart = getCart();
			if (cart == null) {
				Cart cartCheeseCart = cartCheese.getCart();
				if (cartCheeseCart.getCartCheeses().contain(cartCheese)) {
					post = cartCheeseCart.getCartCheeses().remove(cartCheese);
				}
			}
			Cheese cheese = getCheese();
			if (cheese == null) {
				Cheese cartCheeseCheese = cartCheese.getCheese();
				if (cartCheeseCheese.getCartCheeses().contain(cartCheese)) {
					post = cartCheeseCheese.getCartCheeses().remove(cartCheese);
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post update propagation ======= */

	protected boolean postUpdate(CartCheese beforeCartCheese,
			CartCheese afterCartCheese) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeCartCheese, afterCartCheese)) {
			Cart beforeCartCheeseCart = beforeCartCheese.getCart();
			Cart afterCartCheeseCart = afterCartCheese.getCart();

			if (beforeCartCheeseCart != afterCartCheeseCart) {
				post = beforeCartCheeseCart.getCartCheeses().remove(
						beforeCartCheese);
				if (post) {
					post = afterCartCheeseCart.getCartCheeses().add(
							afterCartCheese);
					if (!post) {
						beforeCartCheeseCart.getCartCheeses().add(
								beforeCartCheese);
					}
				}
			}
			Cheese beforeCartCheeseCheese = beforeCartCheese.getCheese();
			Cheese afterCartCheeseCheese = afterCartCheese.getCheese();

			if (beforeCartCheeseCheese != afterCartCheeseCheese) {
				post = beforeCartCheeseCheese.getCartCheeses().remove(
						beforeCartCheese);
				if (post) {
					post = afterCartCheeseCheese.getCartCheeses().add(
							afterCartCheese);
					if (!post) {
						beforeCartCheeseCheese.getCartCheeses().add(
								beforeCartCheese);
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
	 * Creates cartCheese.
	 * 
	 * @param cartParent
	 *            cart parent
	 * @param cheeseParent
	 *            cheese parent
	 * @return cartCheese
	 */
	public CartCheese createCartCheese(Cart cartParent, Cheese cheeseParent) {
		CartCheese cartCheese = new CartCheese(getModel());
		cartCheese.setCart(cartParent);
		cartCheese.setCheese(cheeseParent);
		if (!add(cartCheese)) {
			cartCheese = null;
		}
		return cartCheese;
	}

}