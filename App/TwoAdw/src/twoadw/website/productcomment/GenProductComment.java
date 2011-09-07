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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	
	import java.util.Date;	

/* ======= import internal parent entity class ======= */

import twoadw.website.product.Product;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * ProductComment generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProductComment extends Entity<ProductComment> {

	private static final long serialVersionUID = 1234728771938L;

	private static Log log = LogFactory.getLog(GenProductComment.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String commentText;
	
    	    private String commentTitle;
	
    	    private Date creationDate;
	
    	    private Date modificationDate;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Product product;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs productComment within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProductComment(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs productComment within its parent(s).
	     * 
	        		* @param Product product
			     */
	    public GenProductComment(
	    		    							Product product  
	    			    		    ) {
	    				this(product.getModel());
			// parents
							setProduct(product);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets commentText.
		 * 
		 * @param commentText
		 *            commentText
		 */
		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}
		
		/**
		 * Gets commentText.
		 * 
		 * @return commentText
		 */
		public String getCommentText() {
			return commentText;
		}  
		
				    		/**
		 * Sets commentTitle.
		 * 
		 * @param commentTitle
		 *            commentTitle
		 */
		public void setCommentTitle(String commentTitle) {
			this.commentTitle = commentTitle;
		}
		
		/**
		 * Gets commentTitle.
		 * 
		 * @return commentTitle
		 */
		public String getCommentTitle() {
			return commentTitle;
		}  
		
				    		/**
		 * Sets creationDate.
		 * 
		 * @param creationDate
		 *            creationDate
		 */
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		
		/**
		 * Gets creationDate.
		 * 
		 * @return creationDate
		 */
		public Date getCreationDate() {
			return creationDate;
		}  
		
				    		/**
		 * Sets modificationDate.
		 * 
		 * @param modificationDate
		 *            modificationDate
		 */
		public void setModificationDate(Date modificationDate) {
			this.modificationDate = modificationDate;
		}
		
		/**
		 * Gets modificationDate.
		 * 
		 * @return modificationDate
		 */
		public Date getModificationDate() {
			return modificationDate;
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
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}