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
package twoadw.website.question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

	import twoadw.website.answer.Answers;	
	import twoadw.website.qqcategory.QQCategories;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;
	import twoadw.website.product.Products;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Question generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-04-08
 */
public abstract class GenQuestion extends Entity<Question> {

	private static final long serialVersionUID = 1236739001410L;

	private static Log log = LogFactory.getLog(GenQuestion.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String questionText;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
    	    private Long productOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
    	    private Answers answers;
	
    	    private QQCategories qQCategories;
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Product product;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs question within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestion(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setAnswers(new Answers((Question) this));
    		    	setQQCategories(new QQCategories((Question) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs question within its parent(s).
	     * 
	        		* @param Product product
			     */
	    public GenQuestion(
	    		    							Product product  
	    			    		    ) {
	    				this(product.getModel());
			// parents
							setProduct(product);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets questionText.
		 * 
		 * @param questionText
		 *            questionText
		 */
		public void setQuestionText(String questionText) {
			this.questionText = questionText;
		}
		
		/**
		 * Gets questionText.
		 * 
		 * @return questionText
		 */
		public String getQuestionText() {
			return questionText;
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
    
    	/**
		 * Sets productOid.
		 * 
		 * @param productOid
		 *            productOid
		 */
		public void setProductOid(Long productOid) {
			this.productOid = productOid;
							product = null;
			}
		
		/**
		 * Gets productOid.
		 * 
		 * @return productOid
		 */
		public Long getProductOid() {
			return productOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets answers.
		 * 
		 * @param answers
		 *            answers
		 */
    	public void setAnswers(Answers answers) {
			this.answers = answers;
			if (answers != null) {
				answers.setQuestion((Question) this);
			}
		}

		/**
		 * Gets answers.
		 * 
		 * @return answers
		 */
		public Answers getAnswers() {
			return answers;
		}
		
	    	/**
		 * Sets qQCategories.
		 * 
		 * @param qQCategories
		 *            qQCategories
		 */
    	public void setQQCategories(QQCategories qQCategories) {
			this.qQCategories = qQCategories;
			if (qQCategories != null) {
				qQCategories.setQuestion((Question) this);
			}
		}

		/**
		 * Gets qQCategories.
		 * 
		 * @return qQCategories
		 */
		public QQCategories getQQCategories() {
			return qQCategories;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets product.
		 * 
		 * @param product
		 *            product
		 */
    	public void setProduct(Product product) {
			this.product = product;
			if (product != null) {
				productOid = product.getOid().getUniqueNumber();
			} else {
				productOid = null;
			}
		}

		/**
		 * Gets product.
		 * 
		 * @return product
		 */
		public Product getProduct() {
			if (product == null) {
				Website website = (Website) getModel();
				Products products = website.getProducts();
				if (productOid != null) {
											product = products.getProduct(productOid);
									}
			}
			return product;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}