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
package sales.cheesestore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.DomainModel;

/* ======= import entry concept entities ======= */

import sales.cheesestore.cart.Carts;	
import sales.cheesestore.cheese.Cheeses;	
import sales.cheesestore.address.Addresses;	
import sales.cheesestore.recipe.Recipes;	
import sales.cheesestore.discount.Discounts;	

/* ======= import non entry external child/parent required concept entities ======= */

import sales.cheesestore.cartcheese.CartCheeses;	
import sales.cheesestore.cartcheese.CartCheese;
import sales.cheesestore.cart.Cart;

/**
 * CheeseStore generated model. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public abstract class GenCheeseStore extends DomainModel {

	private static final long serialVersionUID = 1231169065332L;
	
	private static Log log = LogFactory.getLog(GenCheeseStore.class);
	
	private Carts carts;
		
	private Cheeses cheeses;
		
	private Addresses addresses;
		
	private Recipes recipes;
		
	private Discounts discounts;
		
	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenCheeseStore(IDomain domain) {
		super(domain);
		carts = new Carts(this);
		cheeses = new Cheeses(this);
		addresses = new Addresses(this);
		recipes = new Recipes(this);
		discounts = new Discounts(this);
	}

	/**
	 * Gets Cart entities.
	 * 
	 * @return Cart entities
	 */
	public Carts getCarts() {
		return carts;
	}
	
	/**
	 * Gets Cheese entities.
	 * 
	 * @return Cheese entities
	 */
	public Cheeses getCheeses() {
		return cheeses;
	}
	
	/**
	 * Gets Address entities.
	 * 
	 * @return Address entities
	 */
	public Addresses getAddresses() {
		return addresses;
	}
	
	/**
	 * Gets Recipe entities.
	 * 
	 * @return Recipe entities
	 */
	public Recipes getRecipes() {
		return recipes;
	}
	
	/**
	 * Gets Discount entities.
	 * 
	 * @return Discount entities
	 */
	public Discounts getDiscounts() {
		return discounts;
	}
	


	/**
	 * Gets all CartCheese entities.
	 * 
	 * @return CartCheese entities
	 */
	public CartCheeses getCartCheeses() {
		CartCheeses allCartCheeses = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allCartCheeses = new CartCheeses(this);
				allCartCheeses.setPersistent(false);
				allCartCheeses.setPre(false);
				allCartCheeses.setPost(false);
				Carts carts = getCarts();
				for (Cart cart : carts) {
					CartCheeses cartCartCheeses = cart.getCartCheeses();
					for (CartCheese cartCheese : cartCartCheeses) {
						allCartCheeses.add(cartCheese);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenCheeseStore.getCartCheeses(): " + e.getMessage());
			} finally {
				allCartCheeses.setPersistent(true);
				allCartCheeses.setPre(true);
				allCartCheeses.setPost(true);
			}			
		}
		return allCartCheeses;
	}
	
}
