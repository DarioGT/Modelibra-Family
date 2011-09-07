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
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */

import twoadw.website.productcategory.ProductCategory;	

/* ======= import external parent entity and entities classes ======= */


/**
 * ProductCategory specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductCategories extends GenProductCategories {
	
	private static final long serialVersionUID = 1234725304097L;

	private static Log log = LogFactory.getLog(ProductCategories.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs productCategories for the productCategory parent.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
		public ProductCategories(ProductCategory productCategory) {
			super(productCategory);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates productCategory.
		 * 
		 * @param productCategoryParent
		 *            productCategory parent
		 * @param categoryNumber
		 *            categoryNumber
		 * @param categoryName
		 *            categoryName
		 * @return productCategory
		 */
		public ProductCategory createProductCategory(
				ProductCategory productCategoryParent, String categoryNumber,
				String categoryName, String description, String imageUrl180x130, Boolean published, EasyDate startDate) {
			ProductCategory productCategory = new ProductCategory(getModel());
			productCategory.setProductCategory(productCategoryParent);
			productCategory.setCategoryNumber(categoryNumber);
			productCategory.setCategoryName(categoryName);
			productCategory.setDescription(description);
			productCategory.setImageUrl180x130(imageUrl180x130);
			productCategory.setPublished(true);
			productCategory.setStartDate(startDate.getDate());
			if (!add(productCategory)) {
				productCategory = null;
			}
			return productCategory;
		}
}