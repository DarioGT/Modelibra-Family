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
package twoadw.website.status;

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
 * Status generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenStatuss extends Entities<Status> {
	
	private static final long serialVersionUID = 1235990711425L;

	private static Log log = LogFactory.getLog(GenStatuss.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs statuss within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenStatuss(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the status with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return status
	 */
public Status getStatus(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the status with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return status
	 */
	public Status getStatus(Long oidUniqueNumber) {
		return getStatus(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first status whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return status
	 */
	public Status getStatus(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects statuss whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return statuss
	 */
	public Statuss getStatuss(String propertyCode, Object property) {
		return (Statuss) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets statuss ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered statuss
	 */
	public Statuss getStatuss(String propertyCode, boolean ascending) {
		return (Statuss) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets statuss selected by a selector. Returns empty statuss if there are no
	 * statuss that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected statuss
	 */
	public Statuss getStatuss(ISelector selector) {
		return (Statuss) selectBySelector(selector);
	}
	
	/**
	 * Gets statuss ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered statuss
	 */
	public Statuss getStatuss(Comparator comparator, boolean ascending) {
		return (Statuss) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets statusName status.
		 * 
		 * @param statusName 
		 *            statusName
		 * @return statusName status
		 */
		public Status getStatusNameStatus(String statusName) {
			PropertySelector propertySelector = new PropertySelector("statusName");
						propertySelector.defineEqual(statusName);
			List<Status> list = getStatuss(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets statuss ordered by statusName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered statuss
		 */
		public Statuss getStatussOrderedByStatusName(boolean ascending) {			
			return getStatuss("statusName", ascending);
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
	 * Creates status.
	 *
		 * @param statusName statusName 
		 * @return status
	 */
	public Status createStatus(
											String statusName 
				) {
		Status status = new Status(getModel());
						status.setStatusName(statusName);
				if (!add(status)) {
			status = null;
		}
		return status;
	}

}