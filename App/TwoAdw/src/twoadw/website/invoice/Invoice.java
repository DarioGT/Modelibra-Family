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

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

import twoadw.website.invoiceproduct.InvoiceProduct;
	import twoadw.website.invoiceproduct.InvoiceProducts;	
	import twoadw.website.invoicestatus.InvoiceStatuss;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.address.Address;
import twoadw.website.address.Addresss;	

/**
 * Invoice specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Invoice extends GenInvoice {

	private static final long serialVersionUID = 1234213538118L;

	private static Log log = LogFactory.getLog(Invoice.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoice within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Invoice(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoice within its parent(s).
	     * 
	        		* @param Address address
			     */
	    public Invoice(
	    		    							Address address  
	    			    		    ) {
			super(
					    									address  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	    public double getTotal() {
			double total = 0;
			for (InvoiceProduct invoiceProduct : getInvoiceProducts()) {
				total += (invoiceProduct.getPrice()) * (Integer.valueOf(invoiceProduct.getQuantity()).intValue()) ;
			}
			return total;
		}
	    
	    public String getItemTotal() {
	    	Integer item = 0;
		    	for (InvoiceProduct invoiceProduct : getInvoiceProducts()) {
		    		item += Integer.valueOf(invoiceProduct.getQuantity()).intValue();
				}
	    	return Integer.toString(item);
	    }
}