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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */

	import java.util.Date;	
	import java.util.Date;	

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */

	import sales.cheesestore.cheese.Cheese;	
	import sales.cheesestore.cheese.Cheeses;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Discount generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-17
 */
public abstract class GenDiscounts extends Entities<Discount> {
	
	private static final long serialVersionUID = 1237249993766L;

	private static Log log = LogFactory.getLog(GenDiscounts.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	
	private Cheese cheese;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs discounts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenDiscounts(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs discounts for the cheese parent.
		 * 
		 * @param cheese
		 *            cheese
		 */
		public GenDiscounts(Cheese cheese) {
			this(cheese.getModel());
			// parent
			setCheese(cheese);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the discount with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return discount
	 */
public Discount getDiscount(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the discount with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return discount
	 */
	public Discount getDiscount(Long oidUniqueNumber) {
		return getDiscount(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first discount whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return discount
	 */
	public Discount getDiscount(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects discounts whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return discounts
	 */
	public Discounts getDiscounts(String propertyCode, Object property) {
		return (Discounts) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets discounts ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered discounts
	 */
	public Discounts getDiscounts(String propertyCode, boolean ascending) {
		return (Discounts) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets discounts selected by a selector. Returns empty discounts if there are no
	 * discounts that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected discounts
	 */
	public Discounts getDiscounts(ISelector selector) {
		return (Discounts) selectBySelector(selector);
	}
	
	/**
	 * Gets discounts ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered discounts
	 */
	public Discounts getDiscounts(Comparator comparator, boolean ascending) {
		return (Discounts) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets percentage discounts.
		 * 
		 * @param percentage 
		 *            percentage
		 * @return percentage discounts
		 */
		public Discounts getPercentageDiscounts(Double percentage) {
			PropertySelector propertySelector = new PropertySelector("percentage");
			propertySelector.defineEqual(percentage);
			return getDiscounts(propertySelector);
		}
		
	/**
		 * Gets from discounts.
		 * 
		 * @param from 
		 *            from
		 * @return from discounts
		 */
		public Discounts getFromDiscounts(Date from) {
			PropertySelector propertySelector = new PropertySelector("from");
			propertySelector.defineEqual(from);
			return getDiscounts(propertySelector);
		}
		
	/**
		 * Gets until discounts.
		 * 
		 * @param until 
		 *            until
		 * @return until discounts
		 */
		public Discounts getUntilDiscounts(Date until) {
			PropertySelector propertySelector = new PropertySelector("until");
			propertySelector.defineEqual(until);
			return getDiscounts(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
						/**
		 	* Gets cheese discounts.
		 	* 
		 	* @param cheese 
		 	*            cheese oid unique number
		 	* @return cheese discounts
		 	*/
			public Discounts getCheeseDiscounts(Long cheese) {
						PropertySelector propertySelector = new PropertySelector("cheeseOid");
						propertySelector.defineEqual(cheese);
				return getDiscounts(						propertySelector);
			}
			
			/**
		 	* Gets cheese discounts.
		 	* 
		 	* @param cheese 
		 	*            cheese oid
		 	* @return cheese discounts
		 	*/
			public Discounts getCheeseDiscounts(Oid cheese) {
				return getCheeseDiscounts(cheese.getUniqueNumber());
			}
			
			/**
		 	* Gets cheese discounts.
		 	* 
		 	* @param cheese 
		 	*            cheese
		 	* @return cheese discounts
		 	*/
			public Discounts getCheeseDiscounts(Cheese cheese) {
				return getCheeseDiscounts(cheese.getOid());
			}
				
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets discounts ordered by percentage.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered discounts
		 */
		public Discounts getDiscountsOrderedByPercentage(boolean ascending) {			
			return getDiscounts("percentage", ascending);
		}
	
	/**
		 * Gets discounts ordered by from.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered discounts
		 */
		public Discounts getDiscountsOrderedByFrom(boolean ascending) {			
			return getDiscounts("from", ascending);
		}
	
	/**
		 * Gets discounts ordered by until.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered discounts
		 */
		public Discounts getDiscountsOrderedByUntil(boolean ascending) {			
			return getDiscounts("until", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
/**
		 * Sets cheese.
		 * 
		 * @param cheese
		 *            cheese
		 */
public void setCheese(Cheese cheese) {
			this.cheese = cheese;
		}

		/**
		 * Gets cheese.
		 * 
		 * @return cheese
		 */
		public Cheese getCheese() {
			return cheese;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
			protected boolean postAdd(Discount discount) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(discount)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {			
											Cheese cheese = getCheese();
						if (cheese == null) {
							Cheese discountCheese = discount.getCheese();
							if (discountCheese != null) {
								if (!discountCheese.getDiscounts().contain(discount)) {
									discountCheese.getDiscounts().setPropagateToSource(false);
									post = discountCheese.getDiscounts().add(discount);
									discountCheese.getDiscounts().setPropagateToSource(true);
								}
							}
						} 
									}
			} else {
				post = false;
			}
		return post;
	}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
			protected boolean postRemove(Discount discount) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(discount)) {			
									Cheese cheese = getCheese();
					if (cheese == null) {
						Cheese discountCheese = discount.getCheese();
						if (discountCheese != null) {
							if (discountCheese.getDiscounts().contain(discount)) {
								discountCheese.getDiscounts().setPropagateToSource(false);
								post = discountCheese.getDiscounts().remove(discount);
								discountCheese.getDiscounts().setPropagateToSource(true);
							}
						}
					} 
							} else {
				post = false;
			}
		return post;
	}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
			protected boolean postUpdate(Discount beforeDiscount, Discount afterDiscount) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeDiscount, afterDiscount)) {			
									Cheese cheese = getCheese();					
					if (cheese == null) {	
						Cheese beforeDiscountCheese = beforeDiscount.getCheese();
						Cheese afterDiscountCheese = afterDiscount.getCheese();
						if (beforeDiscountCheese == null && afterDiscountCheese != null) {
							// attach
							if (!afterDiscountCheese.getDiscounts().contain(afterDiscount)) {
								afterDiscountCheese.getDiscounts().setPropagateToSource(false);
								post = afterDiscountCheese.getDiscounts().add(afterDiscount);
								afterDiscountCheese.getDiscounts().setPropagateToSource(true);
							}
						} else if (beforeDiscountCheese != null && afterDiscountCheese == null) {
							// detach
							if (beforeDiscountCheese.getDiscounts().contain(beforeDiscount)) {
								beforeDiscountCheese.getDiscounts().setPropagateToSource(false);
								post = beforeDiscountCheese.getDiscounts().remove(beforeDiscount);
								beforeDiscountCheese.getDiscounts().setPropagateToSource(true);
							}
						} else if (beforeDiscountCheese != null && afterDiscountCheese != null
								&& beforeDiscountCheese != afterDiscountCheese) {
							// detach
							if (beforeDiscountCheese.getDiscounts().contain(beforeDiscount)) {
								beforeDiscountCheese.getDiscounts().setPropagateToSource(false);
								post = beforeDiscountCheese.getDiscounts().remove(beforeDiscount);
								beforeDiscountCheese.getDiscounts().setPropagateToSource(true);
							}
							// attach
							if (!afterDiscountCheese.getDiscounts().contain(afterDiscount)) {
								afterDiscountCheese.getDiscounts().setPropagateToSource(false);
								post = afterDiscountCheese.getDiscounts().add(afterDiscount);
								afterDiscountCheese.getDiscounts().setPropagateToSource(true);
							}
						}
					} 
							} else {
				post = false;
			}
		return post;
	}
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates discount.
	 *
			 * @param cheeseParent cheese parent
		 * @param percentage percentage 
	 * @param from from 
	 * @param until until 
		 * @return discount
	 */
	public Discount createDiscount(
																	Cheese cheeseParent,
																	Double percentage,
											Date from,
											Date until 
				) {
		Discount discount = new Discount(getModel());
					discount.setCheese(cheeseParent);
						discount.setPercentage(percentage);
				discount.setFrom(from);
				discount.setUntil(until);
				if (!add(discount)) {
			discount = null;
		}
		return discount;
	}

}