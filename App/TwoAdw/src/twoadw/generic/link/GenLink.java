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


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Link generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenLink extends Entity<Link> {

	private static final long serialVersionUID = 1236708354131L;

	private static Log log = LogFactory.getLog(GenLink.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String url;
	
    	    private String name;
	
    	    private String description;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private CategoryLink categoryLink;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs link within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenLink(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs link within its parent(s).
	     * 
	        		* @param CategoryLink categoryLink
			     */
	    public GenLink(
	    		    							CategoryLink categoryLink  
	    			    		    ) {
	    				this(categoryLink.getModel());
			// parents
							setCategoryLink(categoryLink);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets url.
		 * 
		 * @param url
		 *            url
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		
		/**
		 * Gets url.
		 * 
		 * @return url
		 */
		public String getUrl() {
			return url;
		}  
		
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
		 * Sets description.
		 * 
		 * @param description
		 *            description
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * Gets description.
		 * 
		 * @return description
		 */
		public String getDescription() {
			return description;
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
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}