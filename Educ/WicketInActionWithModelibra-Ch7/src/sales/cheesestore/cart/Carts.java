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

/**
 * Cart specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-01-05
 */
public class Carts extends GenCarts {

	private static final long serialVersionUID = 1231169447923L;

	private static Log log = LogFactory.getLog(Carts.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs carts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Carts(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs carts for the address parent.
	 * 
	 * @param address
	 *            address
	 */
	public Carts(Address address) {
		super(address);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}