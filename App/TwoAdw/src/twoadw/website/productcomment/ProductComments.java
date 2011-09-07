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
package twoadw.website.productcomment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import external parent entity and entities classes ======= */


/**
 * ProductComment specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class ProductComments extends GenProductComments {
	
	private static final long serialVersionUID = 1234728771941L;

	private static Log log = LogFactory.getLog(ProductComments.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productComments within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ProductComments(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs productComments for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public ProductComments(Product product) {
			super(product);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates productComment.
		 * 
		 * @param productParent
		 *            product parent
		 * @param commentText
		 *            commentText
		 * @param commentTitle
		 *            commentTitle
		 * @return productComment
		 */
		public ProductComment createProductComment(Product productParent,
				String commentText, String commentTitle, EasyDate creationDate, EasyDate modificationDate, Boolean published) {
			ProductComment productComment = new ProductComment(getModel());
			productComment.setProduct(productParent);
			productComment.setCommentText(commentText);
			productComment.setCommentTitle(commentTitle);
			productComment.setCreationDate(creationDate.getDate());
			productComment.setModificationDate(modificationDate.getDate());
			productComment.setPublished(published);
			if (!add(productComment)) {
				productComment = null;
			}
			return productComment;
		}
}