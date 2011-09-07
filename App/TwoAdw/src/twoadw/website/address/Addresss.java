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
package twoadw.website.address;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.customer.Customer;	

/* ======= import external parent entity and entities classes ======= */


/**
 * Address specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Addresss extends GenAddresss {
	
	private static final long serialVersionUID = 1234726511678L;

	private static Log log = LogFactory.getLog(Addresss.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs addresss within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Addresss(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs addresss for the customer parent.
		 * 
		 * @param customer
		 *            customer
		 */
		public Addresss(Customer customer) {
			super(customer);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates address.
		 * 
		 * @param customerParent
		 *            customer parent
		 * @param street
		 *            street
		 * @param city
		 *            city
		 * @param zipCode
		 *            zipCode
		 * @param state
		 *            state
		 * @param country
		 *            country
		 * @param telephone
		 *            telephone
		 * @return address
		 */
		public Address createAddress(Customer customerParent, String addressName,
				String street, String city, String zipCode, String state,
				String country, String telephone) {
			Address address = new Address(getModel());
			address.setAddressName(addressName);
			address.setCustomer(customerParent);
			address.setStreet(street);
			address.setCity(city);
			address.setZipCode(zipCode);
			address.setState(state);
			address.setCountry(country);
			address.setTelephone(telephone);
			if (!add(address)) {
				address = null;
			}
			return address;
		}
}