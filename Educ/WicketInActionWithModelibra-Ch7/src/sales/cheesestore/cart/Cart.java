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
import org.modelibra.IDomainModel;

import sales.cheesestore.address.Address;
import sales.cheesestore.cartcheese.CartCheese;

/**
 * Cart specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public class Cart extends GenCart {

	private static final long serialVersionUID = 1231169447922L;

	private static Log log = LogFactory.getLog(Cart.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs cart within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Cart(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs cart within its parent(s).
	 * 
	 * @param Address
	 *            address
	 */
	public Cart(Address address) {
		super(address);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public double getTotal() {
		double total = 0;
		for (CartCheese cartCheese : getCartCheeses()) {
			total += cartCheese.getCheese().getPrice();
		}
		return total;
	}

}