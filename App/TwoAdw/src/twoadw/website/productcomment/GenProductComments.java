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

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import twoadw.website.product.Product;

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * ProductComment generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductComments extends Entities<ProductComment> {
	
	private static final long serialVersionUID = 1234728771939L;

	private static Log log = LogFactory.getLog(GenProductComments.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Product product;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productComments within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductComments(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs productComments for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenProductComments(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the productComment with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return productComment
	 */
public ProductComment getProductComment(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the productComment with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return productComment
	 */
	public ProductComment getProductComment(Long oidUniqueNumber) {
		return getProductComment(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first productComment whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productComment
	 */
	public ProductComment getProductComment(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects productComments whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productComments
	 */
	public ProductComments getProductComments(String propertyCode, Object property) {
		return (ProductComments) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets productComments ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productComments
	 */
	public ProductComments getProductComments(String propertyCode, boolean ascending) {
		return (ProductComments) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets productComments selected by a selector. Returns empty productComments if there are no
	 * productComments that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected productComments
	 */
	public ProductComments getProductComments(ISelector selector) {
		return (ProductComments) selectBySelector(selector);
	}
	
	/**
	 * Gets productComments ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productComments
	 */
	public ProductComments getProductComments(Comparator comparator, boolean ascending) {
		return (ProductComments) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets commentText productComments.
		 * 
		 * @param commentText 
		 *            commentText
		 * @return commentText productComments
		 */
		public ProductComments getCommentTextProductComments(String commentText) {
			PropertySelector propertySelector = new PropertySelector("commentText");
			propertySelector.defineEqual(commentText);
			return getProductComments(propertySelector);
		}
		
	/**
		 * Gets commentTitle productComments.
		 * 
		 * @param commentTitle 
		 *            commentTitle
		 * @return commentTitle productComments
		 */
		public ProductComments getCommentTitleProductComments(String commentTitle) {
			PropertySelector propertySelector = new PropertySelector("commentTitle");
			propertySelector.defineEqual(commentTitle);
			return getProductComments(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets productComments ordered by commentText.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered productComments
		 */
		public ProductComments getProductCommentsOrderedByCommentText(boolean ascending) {			
			return getProductComments("commentText", ascending);
		}
	
	/**
		 * Gets productComments ordered by commentTitle.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered productComments
		 */
		public ProductComments getProductCommentsOrderedByCommentTitle(boolean ascending) {			
			return getProductComments("commentTitle", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets product.
		 * 
		 * @param product
		 *            product
		 */
public void setProduct(Product product) {
			this.product = product;
		}

		/**
		 * Gets product.
		 * 
		 * @return product
		 */
		public Product getProduct() {
			return product;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
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
			String commentText, String commentTitle) {
		ProductComment productComment = new ProductComment(getModel());
		productComment.setProduct(productParent);
		productComment.setCommentText(commentText);
		productComment.setCommentTitle(commentTitle);
		if (!add(productComment)) {
			productComment = null;
		}
		return productComment;
	}

}