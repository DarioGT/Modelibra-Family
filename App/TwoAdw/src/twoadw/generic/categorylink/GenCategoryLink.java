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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.generic.Generic;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.generic.categorylink.CategoryLink;	

/* ======= import internal child entities classes ======= */

	import twoadw.generic.categorylink.CategoryLinks;	
	import twoadw.generic.link.Links;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * CategoryLink generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCategoryLink extends Entity<CategoryLink> {

	private static final long serialVersionUID = 1236708297988L;

	private static Log log = LogFactory.getLog(GenCategoryLink.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String name;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private CategoryLink categoryLink;	
    
	/* ======= internal child neighbors ======= */
	
    	    private CategoryLinks categoryLinks;
	
    	    private Links links;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs categoryLink within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCategoryLink(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setCategoryLinks(new CategoryLinks((CategoryLink) this));
    		    	setLinks(new Links((CategoryLink) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs categoryLink within its parent(s).
	     * 
	        		* @param CategoryLink categoryLink
			     */
	    public GenCategoryLink(
	    		    							CategoryLink categoryLink  
	    			    		    ) {
	    				this(categoryLink.getModel());
			// parents
							setCategoryLink(categoryLink);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets name.
		 * 
		 * @param name
		 *            name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets name.
		 * 
		 * @return name
		 */
		public String getName() {
			return name;
		}  
		
				    		/**
		 * Sets published.
		 * 
		 * @param published
		 *            published
		 */
		public void setPublished(Boolean published) {
			this.published = published;
		}
		
		/**
		 * Gets published.
		 * 
		 * @return published
		 */
		public Boolean getPublished() {
			return published;
		}  
		
							/**
		     * Sets published.
		     * 
		     * @param published
		     *            published
		     */
			public void setPublished(boolean published) {
				setPublished(new Boolean(published));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPublished() {
				return getPublished().booleanValue();
			}
			
		        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
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
		
		
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets categoryLinks.
		 * 
		 * @param categoryLinks
		 *            categoryLinks
		 */
    	public void setCategoryLinks(CategoryLinks categoryLinks) {
			this.categoryLinks = categoryLinks;
			if (categoryLinks != null) {
				categoryLinks.setCategoryLink((CategoryLink) this);
			}
		}

		/**
		 * Gets categoryLinks.
		 * 
		 * @return categoryLinks
		 */
		public CategoryLinks getCategoryLinks() {
			return categoryLinks;
		}
		
	    	/**
		 * Sets links.
		 * 
		 * @param links
		 *            links
		 */
    	public void setLinks(Links links) {
			this.links = links;
			if (links != null) {
				links.setCategoryLink((CategoryLink) this);
			}
		}

		/**
		 * Gets links.
		 * 
		 * @return links
		 */
		public Links getLinks() {
			return links;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}