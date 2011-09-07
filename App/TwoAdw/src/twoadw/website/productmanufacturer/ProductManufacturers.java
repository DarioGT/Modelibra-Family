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

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.manufacturer.Manufacturer;	
import twoadw.website.manufacturer.Manufacturers;	

/**
 * ProductManufacturer specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductManufacturers extends GenProductManufacturers {
	
	private static final long serialVersionUID = 1234729930225L;

	private static Log log = LogFactory.getLog(ProductManufacturers.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productManufacturers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductManufacturers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs productManufacturers for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public ProductManufacturers(Product product) {
			super(product);
		}
	
    	    /**
		 * Constructs productManufacturers for the manufacturer parent.
		 * 
		 * @param manufacturer
		 *            manufacturer
		 */
		public ProductManufacturers(Manufacturer manufacturer) {
			super(manufacturer);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates productManufacturer.
		 * 
		 * @param productParent
		 *            product parent
		 * @param manufacturerParent
		 *            manufacturer parent
		 * @return productManufacturer
		 */
		public ProductManufacturer createProductManufacturer(Product productParent,
				Manufacturer manufacturerParent, String partNumber) {
			ProductManufacturer productManufacturer = new ProductManufacturer(
					getModel());
			productManufacturer.setProduct(productParent);
			productManufacturer.setManufacturer(manufacturerParent);
			productManufacturer.setPartNumber(partNumber);
			if (!add(productManufacturer)) {
				productManufacturer = null;
			}
			return productManufacturer;
		}
}