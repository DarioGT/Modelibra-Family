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
package twoadw.website.rebate;

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


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */

	import twoadw.website.productrebate.ProductRebates;	

/* ======= import external many-to-many internal parent entities classes ======= */

			import twoadw.website.product.Products;
	
/**
 * Rebate generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenRebate extends Entity<Rebate> {

	private static final long serialVersionUID = 1236704376847L;

	private static Log log = LogFactory.getLog(GenRebate.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String rebateName;
	
    	    private String description;
	
    	    private Double rebateValue;
	
    	    private Boolean postalRebate;
	
    	    private Boolean percentRebate;
	
    	    private Long rebatePriority;
	
    	    private Date start;
	
    	    private Date finish;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
    	    private ProductRebates productRebates;
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs rebate within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenRebate(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets rebateName.
		 * 
		 * @param rebateName
		 *            rebateName
		 */
		public void setRebateName(String rebateName) {
			this.rebateName = rebateName;
		}
		
		/**
		 * Gets rebateName.
		 * 
		 * @return rebateName
		 */
		public String getRebateName() {
			return rebateName;
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
		 * Sets rebateValue.
		 * 
		 * @param rebateValue
		 *            rebateValue
		 */
		public void setRebateValue(Double rebateValue) {
			this.rebateValue = rebateValue;
		}
		
		/**
		 * Gets rebateValue.
		 * 
		 * @return rebateValue
		 */
		public Double getRebateValue() {
			return rebateValue;
		}  
		
				    		/**
		 * Sets postalRebate.
		 * 
		 * @param postalRebate
		 *            postalRebate
		 */
		public void setPostalRebate(Boolean postalRebate) {
			this.postalRebate = postalRebate;
		}
		
		/**
		 * Gets postalRebate.
		 * 
		 * @return postalRebate
		 */
		public Boolean getPostalRebate() {
			return postalRebate;
		}  
		
							/**
		     * Sets postalRebate.
		     * 
		     * @param postalRebate
		     *            postalRebate
		     */
			public void setPostalRebate(boolean postalRebate) {
				setPostalRebate(new Boolean(postalRebate));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPostalRebate() {
				return getPostalRebate().booleanValue();
			}
			
		    		/**
		 * Sets percentRebate.
		 * 
		 * @param percentRebate
		 *            percentRebate
		 */
		public void setPercentRebate(Boolean percentRebate) {
			this.percentRebate = percentRebate;
		}
		
		/**
		 * Gets percentRebate.
		 * 
		 * @return percentRebate
		 */
		public Boolean getPercentRebate() {
			return percentRebate;
		}  
		
							/**
		     * Sets percentRebate.
		     * 
		     * @param percentRebate
		     *            percentRebate
		     */
			public void setPercentRebate(boolean percentRebate) {
				setPercentRebate(new Boolean(percentRebate));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPercentRebate() {
				return getPercentRebate().booleanValue();
			}
			
		    		/**
		 * Sets rebatePriority.
		 * 
		 * @param rebatePriority
		 *            rebatePriority
		 */
		public void setRebatePriority(Long rebatePriority) {
			this.rebatePriority = rebatePriority;
		}
		
		/**
		 * Gets rebatePriority.
		 * 
		 * @return rebatePriority
		 */
		public Long getRebatePriority() {
			return rebatePriority;
		}  
		
				    		/**
		 * Sets start.
		 * 
		 * @param start
		 *            start
		 */
		public void setStart(Date start) {
			this.start = start;
		}
		
		/**
		 * Gets start.
		 * 
		 * @return start
		 */
		public Date getStart() {
			return start;
		}  
		
				    		/**
		 * Sets finish.
		 * 
		 * @param finish
		 *            finish
		 */
		public void setFinish(Date finish) {
			this.finish = finish;
		}
		
		/**
		 * Gets finish.
		 * 
		 * @return finish
		 */
		public Date getFinish() {
			return finish;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
    					/**
			 * Sets productRebates.
			 * 
			 * @param productRebates
			 *            productRebates
			 */
	    	public void setProductRebates(ProductRebates productRebates) {
				this.productRebates = productRebates;
				if (productRebates != null) {
					productRebates.setRebate((Rebate) this);
				}
			}
	
			/**
			 * Gets productRebates.
			 * 
			 * @return productRebates
			 */
			public ProductRebates getProductRebates() {
			    if (productRebates == null) {
					Website website = (Website) getModel();
					Products products = website.getProducts();
					setProductRebates(products.getRebateProductRebates((Rebate) this));
				}
				return productRebates;
			}
			
			    
}