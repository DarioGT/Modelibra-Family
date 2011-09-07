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
package twoadw.website.assignproductcategory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.productcategory.ProductCategory;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;	
	import twoadw.website.product.Products;	

/**
 * AssignProductCategory specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class AssignProductCategories extends GenAssignProductCategories {
	
	private static final long serialVersionUID = 1237227206433L;

	private static Log log = LogFactory.getLog(AssignProductCategories.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs assignProductCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public AssignProductCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs assignProductCategories for the productCategory parent.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
		public AssignProductCategories(ProductCategory productCategory) {
			super(productCategory);
		}
	
    	    /**
		 * Constructs assignProductCategories for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public AssignProductCategories(Product product) {
			super(product);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}