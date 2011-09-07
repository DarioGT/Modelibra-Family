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
package sales.cheesestore.cheese;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import sales.cheesestore.CheeseStore;
import sales.cheesestore.cart.Carts;
import sales.cheesestore.cartcheese.CartCheeses;

/**
 * Cheese generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public abstract class GenCheese extends Entity<Cheese> {

	private static final long serialVersionUID = 1231169456922L;

	private static Log log = LogFactory.getLog(GenCheese.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String description;

	private Double price;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private CartCheeses cartCheeses;

	/* ======= base constructor ======= */

	/**
	 * Constructs cheese within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCheese(IDomainModel model) {
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
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets price.
	 * 
	 * @param price
	 *            price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets price.
	 * 
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

	/**
	 * Sets cartCheeses.
	 * 
	 * @param cartCheeses
	 *            cartCheeses
	 */
	public void setCartCheeses(CartCheeses cartCheeses) {
		this.cartCheeses = cartCheeses;
		if (cartCheeses != null) {
			cartCheeses.setCheese((Cheese) this);
		}
	}

	/**
	 * Gets cartCheeses.
	 * 
	 * @return cartCheeses
	 */
	public CartCheeses getCartCheeses() {
		if (cartCheeses == null) {
			CheeseStore cheeseStore = (CheeseStore) getModel();
			Carts carts = cheeseStore.getCarts();
			setCartCheeses(carts.getCheeseCartCheeses((Cheese) this));
		}
		return cartCheeses;
	}

}