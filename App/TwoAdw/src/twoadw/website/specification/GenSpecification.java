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
package twoadw.website.specification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.specificationcategory.SpecificationCategory;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Specification generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenSpecification extends Entity<Specification> {

	private static final long serialVersionUID = 1236796162010L;

	private static Log log = LogFactory.getLog(GenSpecification.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String title;
	
    	    private String detail;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private SpecificationCategory specificationCategory;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs specification within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSpecification(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs specification within its parent(s).
	     * 
	        		* @param SpecificationCategory specificationCategory
			     */
	    public GenSpecification(
	    		    							SpecificationCategory specificationCategory  
	    			    		    ) {
	    				this(specificationCategory.getModel());
			// parents
							setSpecificationCategory(specificationCategory);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets title.
		 * 
		 * @param title
		 *            title
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * Gets title.
		 * 
		 * @return title
		 */
		public String getTitle() {
			return title;
		}  
		
				    		/**
		 * Sets detail.
		 * 
		 * @param detail
		 *            detail
		 */
		public void setDetail(String detail) {
			this.detail = detail;
		}
		
		/**
		 * Gets detail.
		 * 
		 * @return detail
		 */
		public String getDetail() {
			return detail;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
        	/**
		 * Sets specificationCategory.
		 * 
		 * @param specificationCategory
		 *            specificationCategory
		 */
    	public void setSpecificationCategory(SpecificationCategory specificationCategory) {
			this.specificationCategory = specificationCategory;
		}

		/**
		 * Gets specificationCategory.
		 * 
		 * @return specificationCategory
		 */
		public SpecificationCategory getSpecificationCategory() {
			return specificationCategory;
		}
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}