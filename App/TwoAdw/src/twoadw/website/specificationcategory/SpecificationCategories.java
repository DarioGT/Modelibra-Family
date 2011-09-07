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
package twoadw.website.specificationcategory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */


/**
 * SpecificationCategory specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class SpecificationCategories extends GenSpecificationCategories {
	
	private static final long serialVersionUID = 1236796091294L;

	private static Log log = LogFactory.getLog(SpecificationCategories.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs specificationCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public SpecificationCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs specificationCategories for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public SpecificationCategories(Product product) {
			super(product);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}