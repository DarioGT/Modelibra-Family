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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	

/* ======= import internal parent entity class ======= */

import twoadw.website.productcategory.ProductCategory;	

/* ======= import internal child entities classes ======= */

	import twoadw.website.productcategory.ProductCategories;	
	import twoadw.website.assignproductcategory.AssignProductCategories;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * ProductCategory generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductCategory extends Entity<ProductCategory> {

	private static final long serialVersionUID = 1234725304094L;

	private static Log log = LogFactory.getLog(GenProductCategory.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String categoryNumber;
	
    	    private String categoryName;
	
    	    private String description;
	
    	    private String imageUrl180x130;
	
    	    private Boolean published;
	
    	    private Date startDate;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private ProductCategory productCategory;	
    
	/* ======= internal child neighbors ======= */
	
    	    private ProductCategories productCategories;
	
    	    private AssignProductCategories assignProductCategories;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setProductCategories(new ProductCategories((ProductCategory) this));
    		    	setAssignProductCategories(new AssignProductCategories((ProductCategory) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productCategory within its parent(s).
	     * 
	        		* @param ProductCategory productCategory
			     */
	    public GenProductCategory(
	    		    							ProductCategory productCategory  
	    			    		    ) {
	    				this(productCategory.getModel());
			// parents
							setProductCategory(productCategory);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets categoryNumber.
		 * 
		 * @param categoryNumber
		 *            categoryNumber
		 */
		public void setCategoryNumber(String categoryNumber) {
			this.categoryNumber = categoryNumber;
		}
		
		/**
		 * Gets categoryNumber.
		 * 
		 * @return categoryNumber
		 */
		public String getCategoryNumber() {
			return categoryNumber;
		}  
		
				    		/**
		 * Sets categoryName.
		 * 
		 * @param categoryName
		 *            categoryName
		 */
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		
		/**
		 * Gets categoryName.
		 * 
		 * @return categoryName
		 */
		public String getCategoryName() {
			return categoryName;
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
		 * Sets imageUrl180x130.
		 * 
		 * @param imageUrl180x130
		 *            imageUrl180x130
		 */
		public void setImageUrl180x130(String imageUrl180x130) {
			this.imageUrl180x130 = imageUrl180x130;
		}
		
		/**
		 * Gets imageUrl180x130.
		 * 
		 * @return imageUrl180x130
		 */
		public String getImageUrl180x130() {
			return imageUrl180x130;
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
			
		    		/**
		 * Sets startDate.
		 * 
		 * @param startDate
		 *            startDate
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		
		/**
		 * Gets startDate.
		 * 
		 * @return startDate
		 */
		public Date getStartDate() {
			return startDate;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
        	/**
		 * Sets productCategory.
		 * 
		 * @param productCategory
		 *            productCategory
		 */
    	public void setProductCategory(ProductCategory productCategory) {
			this.productCategory = productCategory;
		}

		/**
		 * Gets productCategory.
		 * 
		 * @return productCategory
		 */
		public ProductCategory getProductCategory() {
			return productCategory;
		}
		
		
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets productCategories.
		 * 
		 * @param productCategories
		 *            productCategories
		 */
    	public void setProductCategories(ProductCategories productCategories) {
			this.productCategories = productCategories;
			if (productCategories != null) {
				productCategories.setProductCategory((ProductCategory) this);
			}
		}

		/**
		 * Gets productCategories.
		 * 
		 * @return productCategories
		 */
		public ProductCategories getProductCategories() {
			return productCategories;
		}
		
	    	/**
		 * Sets assignProductCategories.
		 * 
		 * @param assignProductCategories
		 *            assignProductCategories
		 */
    	public void setAssignProductCategories(AssignProductCategories assignProductCategories) {
			this.assignProductCategories = assignProductCategories;
			if (assignProductCategories != null) {
				assignProductCategories.setProductCategory((ProductCategory) this);
			}
		}

		/**
		 * Gets assignProductCategories.
		 * 
		 * @return assignProductCategories
		 */
		public AssignProductCategories getAssignProductCategories() {
			return assignProductCategories;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}