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
package twoadw.website.customer;

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
 * Customer generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenCustomers extends Entities<Customer> {
	
	private static final long serialVersionUID = 1234213586154L;

	private static Log log = LogFactory.getLog(GenCustomers.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs customers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCustomers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the customer with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return customer
	 */
public Customer getCustomer(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the customer with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return customer
	 */
	public Customer getCustomer(Long oidUniqueNumber) {
		return getCustomer(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first customer whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return customer
	 */
	public Customer getCustomer(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects customers whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return customers
	 */
	public Customers getCustomers(String propertyCode, Object property) {
		return (Customers) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets customers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered customers
	 */
	public Customers getCustomers(String propertyCode, boolean ascending) {
		return (Customers) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets customers selected by a selector. Returns empty customers if there are no
	 * customers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected customers
	 */
	public Customers getCustomers(ISelector selector) {
		return (Customers) selectBySelector(selector);
	}
	
	/**
	 * Gets customers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered customers
	 */
	public Customers getCustomers(Comparator comparator, boolean ascending) {
		return (Customers) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets lastName customers.
		 * 
		 * @param lastName 
		 *            lastName
		 * @return lastName customers
		 */
		public Customers getLastNameCustomers(String lastName) {
			PropertySelector propertySelector = new PropertySelector("lastName");
			propertySelector.defineEqual(lastName);
			return getCustomers(propertySelector);
		}
		
	/**
		 * Gets firstName customers.
		 * 
		 * @param firstName 
		 *            firstName
		 * @return firstName customers
		 */
		public Customers getFirstNameCustomers(String firstName) {
			PropertySelector propertySelector = new PropertySelector("firstName");
			propertySelector.defineEqual(firstName);
			return getCustomers(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets codeCustomer customer.
		 * 
		 * @param codeCustomer 
		 *            codeCustomer
		 * @return codeCustomer customer
		 */
		public Customer getCodeCustomerCustomer(String codeCustomer) {
			PropertySelector propertySelector = new PropertySelector("codeCustomer");
						propertySelector.defineEqual(codeCustomer);
			List<Customer> list = getCustomers(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets customers ordered by codeCustomer.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered customers
		 */
		public Customers getCustomersOrderedByCodeCustomer(boolean ascending) {			
			return getCustomers("codeCustomer", ascending);
		}
	
	/**
		 * Gets customers ordered by lastName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered customers
		 */
		public Customers getCustomersOrderedByLastName(boolean ascending) {			
			return getCustomers("lastName", ascending);
		}
	
	/**
		 * Gets customers ordered by firstName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered customers
		 */
		public Customers getCustomersOrderedByFirstName(boolean ascending) {			
			return getCustomers("firstName", ascending);
		}
	
	/**
		 * Gets customers ordered by email.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered customers
		 */
		public Customers getCustomersOrderedByEmail(boolean ascending) {			
			return getCustomers("email", ascending);
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
	 * Creates customer.
	 *
		 * @param codeCustomer codeCustomer 
	 * @param password password 
	 * @param lastName lastName 
	 * @param firstName firstName 
	 * @param email email 
		 * @return customer
	 */
	public Customer createCustomer(
											String codeCustomer,
											String password,
											String lastName,
											String firstName,
											String email 
				) {
		Customer customer = new Customer(getModel());
						customer.setCodeCustomer(codeCustomer);
				customer.setPassword(password);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setEmail(email);
				if (!add(customer)) {
			customer = null;
		}
		return customer;
	}

}