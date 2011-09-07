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
package twoadw.website.productmanufacturer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.manufacturer.Manufacturer;
	import twoadw.website.manufacturer.Manufacturers;	

/**
 * ProductManufacturer specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductManufacturer extends GenProductManufacturer {

	private static final long serialVersionUID = 1234729930224L;

	private static Log log = LogFactory.getLog(ProductManufacturer.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productManufacturer within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductManufacturer(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productManufacturer within its parent(s).
	     * 
	        		* @param Product product
		    		* @param Manufacturer manufacturer
			     */
	    public ProductManufacturer(
	    		    							Product product,
	    			    		    							Manufacturer manufacturer  
	    			    		    ) {
			super(
					    									product,
	    				    			    									manufacturer  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}