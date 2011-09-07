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
package twoadw.generic.link;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import twoadw.generic.categorylink.CategoryLink;

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Link generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenLinks extends Entities<Link> {
	
	private static final long serialVersionUID = 1236708354132L;

	private static Log log = LogFactory.getLog(GenLinks.class);
	
	/* ======= internal parent neighbors ======= */
	
	private CategoryLink categoryLink;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs links within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenLinks(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs links for the categoryLink parent.
		 * 
		 * @param categoryLink
		 *            categoryLink
		 */
		public GenLinks(CategoryLink categoryLink) {
			this(categoryLink.getModel());
			// parent
			setCategoryLink(categoryLink);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the link with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return link
	 */
public Link getLink(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the link with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return link
	 */
	public Link getLink(Long oidUniqueNumber) {
		return getLink(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first link whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return link
	 */
	public Link getLink(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects links whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return links
	 */
	public Links getLinks(String propertyCode, Object property) {
		return (Links) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets links ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered links
	 */
	public Links getLinks(String propertyCode, boolean ascending) {
		return (Links) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets links selected by a selector. Returns empty links if there are no
	 * links that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected links
	 */
	public Links getLinks(ISelector selector) {
		return (Links) selectBySelector(selector);
	}
	
	/**
	 * Gets links ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered links
	 */
	public Links getLinks(Comparator comparator, boolean ascending) {
		return (Links) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets name links.
		 * 
		 * @param name 
		 *            name
		 * @return name links
		 */
		public Links getNameLinks(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
			propertySelector.defineEqual(name);
			return getLinks(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets url link.
		 * 
		 * @param url 
		 *            url
		 * @return url link
		 */
		public Link getUrlLink(String url) {
			PropertySelector propertySelector = new PropertySelector("url");
						propertySelector.defineEqual(url);
			List<Link> list = getLinks(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets links ordered by url.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered links
		 */
		public Links getLinksOrderedByUrl(boolean ascending) {			
			return getLinks("url", ascending);
		}
	
	/**
		 * Gets links ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered links
		 */
		public Links getLinksOrderedByName(boolean ascending) {			
			return getLinks("name", ascending);
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
	 * Creates link.
	 * 
	 * @param categoryLinkParent
	 *            categoryLink parent
	 * @param url
	 *            url
	 * @param name
	 *            name
	 * @return link
	 */
	public Link createLink(CategoryLink categoryLinkParent, String url,
			String name) {
		Link link = new Link(getModel());
		link.setCategoryLink(categoryLinkParent);
		link.setUrl(url);
		link.setName(name);
		if (!add(link)) {
			link = null;
		}
		return link;
	}

}