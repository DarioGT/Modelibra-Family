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
package twoadw.website.productimage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */


/**
 * ProductImage specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductImages extends GenProductImages {
	
	private static final long serialVersionUID = 1234725739871L;

	private static Log log = LogFactory.getLog(ProductImages.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productImages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductImages(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs productImages for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public ProductImages(Product product) {
			super(product);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates productImage.
		 * 
		 * @param productParent
		 *            product parent
		 * @param name
		 *            name
		 * @return productImage
		 */
		public ProductImage createProductImage(Product productParent, String name, String imageUrl96x96,
				String imageUrl180x130, String imageUrlFullSize) {
			ProductImage productImage = new ProductImage(getModel());
			productImage.setProduct(productParent);
			productImage.setName(name);
			productImage.setImageUrl96x96(imageUrl96x96);
			productImage.setImageUrl180x130(imageUrl180x130);
			productImage.setImageUrlFullSize(imageUrlFullSize);
			if (!add(productImage)) {
				productImage = null;
			}
			return productImage;
		}
}