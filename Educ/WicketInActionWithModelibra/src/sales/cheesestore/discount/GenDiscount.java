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
package sales.cheesestore.discount;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import sales.cheesestore.CheeseStore;

/* ======= import property classes ======= */

	import java.util.Date;	
	import java.util.Date;	

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import sales.cheesestore.cheese.Cheese;
	import sales.cheesestore.cheese.Cheeses;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Discount generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public abstract class GenDiscount extends Entity<Discount> {

	private static final long serialVersionUID = 1237249993765L;

	private static Log log = LogFactory.getLog(GenDiscount.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private Double percentage;
	
    	    private String description;
	
    	    private Date from;
	
    	    private Date until;
	
        
    /* ======= reference properties ======= */
	
    	    private Long cheeseOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Cheese cheese;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs discount within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenDiscount(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs discount within its parent(s).
	     * 
	        		* @param Cheese cheese
			     */
	    public GenDiscount(
	    		    							Cheese cheese  
	    			    		    ) {
	    				this(cheese.getModel());
			// parents
							setCheese(cheese);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets percentage.
		 * 
		 * @param percentage
		 *            percentage
		 */
		public void setPercentage(Double percentage) {
			this.percentage = percentage;
		}
		
		/**
		 * Gets percentage.
		 * 
		 * @return percentage
		 */
		public Double getPercentage() {
			return percentage;
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
		 * Sets from.
		 * 
		 * @param from
		 *            from
		 */
		public void setFrom(Date from) {
			this.from = from;
		}
		
		/**
		 * Gets from.
		 * 
		 * @return from
		 */
		public Date getFrom() {
			return from;
		}  
		
				    		/**
		 * Sets until.
		 * 
		 * @param until
		 *            until
		 */
		public void setUntil(Date until) {
			this.until = until;
		}
		
		/**
		 * Gets until.
		 * 
		 * @return until
		 */
		public Date getUntil() {
			return until;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets cheeseOid.
		 * 
		 * @param cheeseOid
		 *            cheeseOid
		 */
		public void setCheeseOid(Long cheeseOid) {
			this.cheeseOid = cheeseOid;
							cheese = null;
			}
		
		/**
		 * Gets cheeseOid.
		 * 
		 * @return cheeseOid
		 */
		public Long getCheeseOid() {
			return cheeseOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets cheese.
		 * 
		 * @param cheese
		 *            cheese
		 */
    	public void setCheese(Cheese cheese) {
			this.cheese = cheese;
			if (cheese != null) {
				cheeseOid = cheese.getOid().getUniqueNumber();
			} else {
				cheeseOid = null;
			}
		}

		/**
		 * Gets cheese.
		 * 
		 * @return cheese
		 */
		public Cheese getCheese() {
			if (cheese == null) {
				CheeseStore cheeseStore = (CheeseStore) getModel();
				Cheeses cheeses = cheeseStore.getCheeses();
				if (cheeseOid != null) {
											cheese = cheeses.getCheese(cheeseOid);
									}
			}
			return cheese;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}