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
package twoadw.website.invoicestatus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.status.Status;	
import twoadw.website.status.Statuss;	

/**
 * InvoiceStatus specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceStatuss extends GenInvoiceStatuss {
	
	private static final long serialVersionUID = 1234728303060L;

	private static Log log = LogFactory.getLog(InvoiceStatuss.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceStatuss within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public InvoiceStatuss(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs invoiceStatuss for the invoice parent.
		 * 
		 * @param invoice
		 *            invoice
		 */
		public InvoiceStatuss(Invoice invoice) {
			super(invoice);
		}
	
    	    /**
		 * Constructs invoiceStatuss for the status parent.
		 * 
		 * @param status
		 *            status
		 */
		public InvoiceStatuss(Status status) {
			super(status);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates invoiceStatus.
		 * 
		 * @param invoiceParent
		 *            invoice parent
		 * @param statusParent
		 *            status parent
		 * @return invoiceStatus
		 */
		public InvoiceStatus createInvoiceStatus(Invoice invoiceParent,
				Status statusParent, String note) {
			InvoiceStatus invoiceStatus = new InvoiceStatus(getModel());
			invoiceStatus.setInvoice(invoiceParent);
			invoiceStatus.setStatus(statusParent);
			invoiceStatus.setNote(note);
			if (!add(invoiceStatus)) {
				invoiceStatus = null;
			}
			return invoiceStatus;
		}
}