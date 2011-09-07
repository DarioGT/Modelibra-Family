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
package twoadw.website.productcategory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.productcategory.ProductCategory;	

/* ======= import internal child entities classes ======= */

	import twoadw.website.productcategory.ProductCategories;	
	import twoadw.website.assignproductcategory.AssignProductCategories;	

/* ======= import external parent entity and entities classes ======= */


/**
 * ProductCategory specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductCategory extends GenProductCategory {

	private static final long serialVersionUID = 1234725304096L;

	private static Log log = LogFactory.getLog(ProductCategory.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductCategory(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productCategory within its parent(s).
	     * 
	        		* @param ProductCategory productCategory
			     */
	    public ProductCategory(
	    		    							ProductCategory productCategory  
	    			    		    ) {
			super(
					    									productCategory  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}