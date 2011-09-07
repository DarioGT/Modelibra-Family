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
package twoadw.website.productrebate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.rebate.Rebate;	
	import twoadw.website.rebate.Rebates;	

/**
 * ProductRebate specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductRebates extends GenProductRebates {
	
	private static final long serialVersionUID = 1236704565610L;

	private static Log log = LogFactory.getLog(ProductRebates.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productRebates within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductRebates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs productRebates for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public ProductRebates(Product product) {
			super(product);
		}
	
    	    /**
		 * Constructs productRebates for the rebate parent.
		 * 
		 * @param rebate
		 *            rebate
		 */
		public ProductRebates(Rebate rebate) {
			super(rebate);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}