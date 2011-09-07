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

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.status.Status;
	import twoadw.website.status.Statuss;	

/**
 * InvoiceStatus specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceStatus extends GenInvoiceStatus {

	private static final long serialVersionUID = 1234728303059L;

	private static Log log = LogFactory.getLog(InvoiceStatus.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceStatus within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public InvoiceStatus(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoiceStatus within its parent(s).
	     * 
	        		* @param Invoice invoice
		    		* @param Status status
			     */
	    public InvoiceStatus(
	    		    							Invoice invoice,
	    			    		    							Status status  
	    			    		    ) {
			super(
					    									invoice,
	    				    			    									status  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}