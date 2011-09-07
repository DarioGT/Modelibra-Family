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
package twoadw.generic.categorylink;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * CategoryLink generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCategoryLinks extends Entities<CategoryLink> {
	
	private static final long serialVersionUID = 1236708297989L;

	private static Log log = LogFactory.getLog(GenCategoryLinks.class);
	
	/* ======= internal parent neighbors ======= */
	
	private CategoryLink categoryLink;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs categoryLinks within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCategoryLinks(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs categoryLinks for the categoryLink parent.
		 * 
		 * @param categoryLink
		 *            categoryLink
		 */
		public GenCategoryLinks(CategoryLink categoryLink) {
			this(categoryLink.getModel());
			// parent
			setCategoryLink(categoryLink);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the categoryLink with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return categoryLink
	 */
public CategoryLink getCategoryLink(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the categoryLink with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return categoryLink
	 */
	public CategoryLink getCategoryLink(Long oidUniqueNumber) {
		return getCategoryLink(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first categoryLink whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return categoryLink
	 */
	public CategoryLink getCategoryLink(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
				/**
	 	 * Retrieves the categoryLink in a reflexive hierarchy using a given oid unique
	 	 * number. Null if not found.
	 	 * 
	 	 * @param oidUniqueNumber
	 	 *            oid unique number
	 	 * @return categoryLink
	 	 */
		public CategoryLink getReflexiveCategoryLink(Long oidUniqueNumber) {
			CategoryLink categoryLink = getCategoryLink(oidUniqueNumber);
			if (categoryLink == null) {
				for (CategoryLink currentCategoryLink : this) {
					categoryLink = currentCategoryLink.getCategoryLinks()
							.getReflexiveCategoryLink(oidUniqueNumber);
					if (categoryLink != null) {
						break;
					}
				}
			}
			return categoryLink;
		}
		
		/**
	 * Selects categoryLinks whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return categoryLinks
	 */
	public CategoryLinks getCategoryLinks(String propertyCode, Object property) {
		return (CategoryLinks) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets categoryLinks ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categoryLinks
	 */
	public CategoryLinks getCategoryLinks(String propertyCode, boolean ascending) {
		return (CategoryLinks) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets categoryLinks selected by a selector. Returns empty categoryLinks if there are no
	 * categoryLinks that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected categoryLinks
	 */
	public CategoryLinks getCategoryLinks(ISelector selector) {
		return (CategoryLinks) selectBySelector(selector);
	}
	
	/**
	 * Gets categoryLinks ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categoryLinks
	 */
	public CategoryLinks getCategoryLinks(Comparator comparator, boolean ascending) {
		return (CategoryLinks) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name categoryLink.
		 * 
		 * @param name 
		 *            name
		 * @return name categoryLink
		 */
		public CategoryLink getNameCategoryLink(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<CategoryLink> list = getCategoryLinks(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets categoryLinks ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered categoryLinks
		 */
		public CategoryLinks getCategoryLinksOrderedByName(boolean ascending) {			
			return getCategoryLinks("name", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets categoryLink.
		 * 
		 * @param categoryLink
		 *            categoryLink
		 */
public void setCategoryLink(CategoryLink categoryLink) {
			this.categoryLink = categoryLink;
		}

		/**
		 * Gets categoryLink.
		 * 
		 * @return categoryLink
		 */
		public CategoryLink getCategoryLink() {
			return categoryLink;
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
	 * Creates categoryLink.
	 * 
	 * @param categoryLinkParent
	 *            categoryLink parent
	 * @param name
	 *            name
	 * @return categoryLink
	 */
	public CategoryLink createCategoryLink(CategoryLink categoryLinkParent,
			String name) {
		CategoryLink categoryLink = new CategoryLink(getModel());
		categoryLink.setCategoryLink(categoryLinkParent);
		categoryLink.setName(name);
		if (!add(categoryLink)) {
			categoryLink = null;
		}
		return categoryLink;
	}

}