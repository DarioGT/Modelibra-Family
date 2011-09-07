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

/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;	
import twoadw.website.product.Products;	

/**
 * InvoiceProduct specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InvoiceProducts extends GenInvoiceProducts {
	
	private static final long serialVersionUID = 1234213515633L;

	private static Log log = LogFactory.getLog(InvoiceProducts.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceProducts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public InvoiceProducts(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs invoiceProducts for the invoice parent.
		 * 
		 * @param invoice
		 *            invoice
		 */
		public InvoiceProducts(Invoice invoice) {
			super(invoice);
		}
	
    	    /**
		 * Constructs invoiceProducts for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public InvoiceProducts(Product product) {
			super(product);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates invoiceProduct.
		 * 
		 * @param invoiceParent
		 *            invoice parent
		 * @param productParent
		 *            product parent
		 * @param price
		 *            price
		 * @return invoiceProduct
		 */
		public InvoiceProduct createInvoiceProduct(Invoice invoiceParent,
				Product productParent, Double price, String quantiy) {
			InvoiceProduct invoiceProduct = new InvoiceProduct(getModel());
			invoiceProduct.setInvoice(invoiceParent);
			invoiceProduct.setProduct(productParent);
			invoiceProduct.setPrice(price);
			invoiceProduct.setQuantity(quantiy);
			if (!add(invoiceProduct)) {
				invoiceProduct = null;
			}
			return invoiceProduct;
		}
}