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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Customer specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Customers extends GenCustomers {
	
	private static final long serialVersionUID = 1234213586156L;

	private static Log log = LogFactory.getLog(Customers.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs customers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Customers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates customer.
	 * 
	 * @param codeCustomer
	 *            codeCustomer
	 * @param password
	 *            password
	 * @param lastName
	 *            lastName
	 * @param firstName
	 *            firstName
	 * @param email
	 *            email
	 * @return customer
	 */
	public Customer createCustomer(String codeCustomer, String password,
			String lastName, String firstName, String email,
			Boolean receiveEmail) {
		Customer customer = new Customer(getModel());
		customer.setCodeCustomer(codeCustomer);
		customer.setPassword(password);
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setEmail(email);
		customer.setReceiveEmail(receiveEmail);
		if (!add(customer)) {
			customer = null;
		}
		return customer;
	}
	
	/**
	 * Creates customer.
	 * 
	 * @param codeCustomer
	 *            codeCustomer
	 * @param password
	 *            password
	 * @param lastName
	 *            lastName
	 * @param firstName
	 *            firstName
	 * @param email
	 *            email
	 * @return customer
	 */
	public Customer createCustomer(String codeCustomer, String password,
			String lastName, String firstName, String email,
			Boolean receiveEmail, EasyDate startDate, String securityRole) {
		Customer customer = new Customer(getModel());
		customer.setCodeCustomer(codeCustomer);
		customer.setPassword(password);
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setEmail(email);
		customer.setReceiveEmail(receiveEmail);
		customer.setStartDate(startDate.getDate());
		customer.setSecurityRole(securityRole);
		if (!add(customer)) {
			customer = null;
		}
		return customer;
	}
}