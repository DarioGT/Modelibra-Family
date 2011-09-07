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


/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Rebate generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenRebates extends Entities<Rebate> {
	
	private static final long serialVersionUID = 1236704376848L;

	private static Log log = LogFactory.getLog(GenRebates.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs rebates within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenRebates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the rebate with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return rebate
	 */
public Rebate getRebate(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the rebate with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return rebate
	 */
	public Rebate getRebate(Long oidUniqueNumber) {
		return getRebate(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first rebate whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return rebate
	 */
	public Rebate getRebate(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects rebates whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return rebates
	 */
	public Rebates getRebates(String propertyCode, Object property) {
		return (Rebates) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets rebates ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered rebates
	 */
	public Rebates getRebates(String propertyCode, boolean ascending) {
		return (Rebates) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets rebates selected by a selector. Returns empty rebates if there are no
	 * rebates that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected rebates
	 */
	public Rebates getRebates(ISelector selector) {
		return (Rebates) selectBySelector(selector);
	}
	
	/**
	 * Gets rebates ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered rebates
	 */
	public Rebates getRebates(Comparator comparator, boolean ascending) {
		return (Rebates) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets rebateName rebate.
		 * 
		 * @param rebateName 
		 *            rebateName
		 * @return rebateName rebate
		 */
		public Rebate getRebateNameRebate(String rebateName) {
			PropertySelector propertySelector = new PropertySelector("rebateName");
						propertySelector.defineEqual(rebateName);
			List<Rebate> list = getRebates(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets rebates ordered by rebateName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered rebates
		 */
		public Rebates getRebatesOrderedByRebateName(boolean ascending) {			
			return getRebates("rebateName", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates rebate.
	 *
		 * @param rebateName rebateName 
		 * @return rebate
	 */
	public Rebate createRebate(
											String rebateName 
				) {
		Rebate rebate = new Rebate(getModel());
						rebate.setRebateName(rebateName);
				if (!add(rebate)) {
			rebate = null;
		}
		return rebate;
	}

}