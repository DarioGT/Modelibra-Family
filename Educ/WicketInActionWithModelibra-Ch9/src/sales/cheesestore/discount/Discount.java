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
package sales.cheesestore.discount;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import sales.cheesestore.CheeseStore;

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import sales.cheesestore.cheese.Cheese;
	import sales.cheesestore.cheese.Cheeses;	

/**
 * Discount specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public class Discount extends GenDiscount {

	private static final long serialVersionUID = 1237249993767L;

	private static Log log = LogFactory.getLog(Discount.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs discount within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Discount(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs discount within its parent(s).
	     * 
	        		* @param Cheese cheese
			     */
	    public Discount(
	    		    							Cheese cheese  
	    			    		    ) {
			super(
					    									cheese  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}