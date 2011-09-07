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
package twoadw.website.invoice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.address.Address;	
import twoadw.website.address.Addresss;	

/**
 * Invoice specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Invoices extends GenInvoices {
	
	private static final long serialVersionUID = 1234213538119L;

	private static Log log = LogFactory.getLog(Invoices.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoices within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Invoices(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs invoices for the address parent.
		 * 
		 * @param address
		 *            address
		 */
		public Invoices(Address address) {
			super(address);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates invoice.
		 * 
		 * @param addressParent
		 *            address parent
		 * @param invoiceNumber
		 *            invoiceNumber
		 * @return invoice
		 */
		public Invoice createInvoice(Address addressParent, String invoiceNumber, Boolean paid, Boolean shipped, EasyDate date ) {
			Invoice invoice = new Invoice(getModel());
			invoice.setAddress(addressParent);
			invoice.setInvoiceNumber(invoiceNumber);
			invoice.setPaid(paid);
			invoice.setShipped(shipped);
			invoice.setDate(date.getDate());
			if (!add(invoice)) {
				invoice = null;
			}
			return invoice;
		}

}