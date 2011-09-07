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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import sales.cheesestore.CheeseStore;
import sales.cheesestore.cart.Cart;
import sales.cheesestore.cheese.Cheese;
import sales.cheesestore.cheese.Cheeses;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * CartCheese generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenCartCheese extends Entity<CartCheese> {

	private static final long serialVersionUID = 1231169481732L;

	private static Log log = LogFactory.getLog(GenCartCheese.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	/* ======= reference properties ======= */

	private Long cheeseOid;

	/* ======= internal parent neighbors ======= */

	private Cart cart;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Cheese cheese;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs cartCheese within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCartCheese(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs cartCheese within its parent(s).
	 * 
	 * @param Cart
	 *            cart
	 * @param Cheese
	 *            cheese
	 */
	public GenCartCheese(Cart cart, Cheese cheese) {
		this(cheese.getModel());
		// parents
		setCart(cart);
		setCheese(cheese);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets cheeseOid.
	 * 
	 * @param cheeseOid
	 *            cheeseOid
	 */
	public void setCheeseOid(Long cheeseOid) {
		this.cheeseOid = cheeseOid;
		cheese = null;
	}

	/**
	 * Gets cheeseOid.
	 * 
	 * @return cheeseOid
	 */
	public Long getCheeseOid() {
		return cheeseOid;
	}

	/* ======= derived property abstract get methods ======= */

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

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets cheese.
	 * 
	 * @param cheese
	 *            cheese
	 */
	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
		if (cheese != null) {
			cheeseOid = cheese.getOid().getUniqueNumber();
		} else {
			cheeseOid = null;
		}
	}

	/**
	 * Gets cheese.
	 * 
	 * @return cheese
	 */
	public Cheese getCheese() {
		if (cheese == null) {
			CheeseStore cheeseStore = (CheeseStore) getModel();
			Cheeses cheeses = cheeseStore.getCheeses();
			if (cheeseOid != null) {
				cheese = cheeses.getCheese(cheeseOid);
			}
		}
		return cheese;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}