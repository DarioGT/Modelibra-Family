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
import org.modelibra.IDomainModel;

import sales.cheesestore.cart.Cart;
import sales.cheesestore.cheese.Cheese;

/**
 * CartCheese specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public class CartCheese extends GenCartCheese {

	private static final long serialVersionUID = 1231169481734L;

	private static Log log = LogFactory.getLog(CartCheese.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs cartCheese within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public CartCheese(IDomainModel model) {
		super(model);
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
	public CartCheese(Cart cart, Cheese cheese) {
		super(cart, cheese);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}