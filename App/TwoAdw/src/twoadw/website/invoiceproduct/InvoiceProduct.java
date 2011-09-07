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
package twoadw.website.invoiceproduct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;
	import twoadw.website.product.Products;	

/**
 * InvoiceProduct specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceProduct extends GenInvoiceProduct {

	private static final long serialVersionUID = 1234213515632L;

	private static Log log = LogFactory.getLog(InvoiceProduct.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceProduct within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public InvoiceProduct(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoiceProduct within its parent(s).
	     * 
	        		* @param Invoice invoice
		    		* @param Product product
			     */
	    public InvoiceProduct(
	    		    							Invoice invoice,
	    			    		    							Product product  
	    			    		    ) {
			super(
					    									invoice,
	    				    			    									product  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}