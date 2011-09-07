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
package twoadw.website.product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;


/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Product specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Products extends GenProducts {
	
	private static final long serialVersionUID = 1234213171468L;

	private static Log log = LogFactory.getLog(Products.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs products within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Products(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates product.
	 *
		 * @param productNumber productNumber 
	 * @param name name 
		 * @return product
	 */
	//FULL DATA
	public Product createProduct(	
									String productNumber,
									String name,
									String shortDescription,
									String longDescription,
									Double price,
									EasyDate startEasyDate,
									Boolean published,
									Long soldNumber,
									Boolean frontpage
				) {
		Product product = new Product(getModel());
		product.setProductNumber(productNumber);
		product.setName(name);
		product.setShortDescription(shortDescription);
		product.setLongDescription(longDescription);
		product.setPrice(price);
		product.setStartDate(startEasyDate.getDate());
		product.setPublished(published);
		product.setSoldNumber(soldNumber);
		product.setFrontpage(frontpage);
		if (!add(product)) {
		product = null;
		}
		return product;
	}
}