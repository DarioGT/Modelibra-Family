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

import java.util.Comparator;
import java.util.List;

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
 * ProductImage generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductImages extends Entities<ProductImage> {
	
	private static final long serialVersionUID = 1234725739869L;

	private static Log log = LogFactory.getLog(GenProductImages.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Product product;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs productImages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductImages(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs productImages for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenProductImages(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the productImage with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return productImage
	 */
public ProductImage getProductImage(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the productImage with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return productImage
	 */
	public ProductImage getProductImage(Long oidUniqueNumber) {
		return getProductImage(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first productImage whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productImage
	 */
	public ProductImage getProductImage(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects productImages whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return productImages
	 */
	public ProductImages getProductImages(String propertyCode, Object property) {
		return (ProductImages) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets productImages ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productImages
	 */
	public ProductImages getProductImages(String propertyCode, boolean ascending) {
		return (ProductImages) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets productImages selected by a selector. Returns empty productImages if there are no
	 * productImages that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected productImages
	 */
	public ProductImages getProductImages(ISelector selector) {
		return (ProductImages) selectBySelector(selector);
	}
	
	/**
	 * Gets productImages ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered productImages
	 */
	public ProductImages getProductImages(Comparator comparator, boolean ascending) {
		return (ProductImages) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name productImage.
		 * 
		 * @param name 
		 *            name
		 * @return name productImage
		 */
		public ProductImage getNameProductImage(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<ProductImage> list = getProductImages(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
		
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
	 * Creates productImage.
	 * 
	 * @param productParent
	 *            product parent
	 * @param name
	 *            name
	 * @return productImage
	 */
	public ProductImage createProductImage(Product productParent, String name) {
		ProductImage productImage = new ProductImage(getModel());
		productImage.setProduct(productParent);
		productImage.setName(name);
		if (!add(productImage)) {
			productImage = null;
		}
		return productImage;
	}

}