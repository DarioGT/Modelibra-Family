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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

	import twoadw.website.invoiceproduct.InvoiceProducts;	
	import twoadw.website.invoicestatus.InvoiceStatuss;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.address.Address;
	import twoadw.website.address.Addresss;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * Invoice generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenInvoice extends Entity<Invoice> {

	private static final long serialVersionUID = 1234213538116L;

	private static Log log = LogFactory.getLog(GenInvoice.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String invoiceNumber;
	
    	    private Boolean paid;
	
    	    private Boolean shipped;
	
    	    private Date date;
	
        
    /* ======= reference properties ======= */
	
    	    private Long addressOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
    	    private InvoiceProducts invoiceProducts;
	
    	    private InvoiceStatuss invoiceStatuss;
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Address address;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoice within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoice(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setInvoiceProducts(new InvoiceProducts((Invoice) this));
    		    	setInvoiceStatuss(new InvoiceStatuss((Invoice) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoice within its parent(s).
	     * 
	        		* @param Address address
			     */
	    public GenInvoice(
	    		    							Address address  
	    			    		    ) {
	    				this(address.getModel());
			// parents
							setAddress(address);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets invoiceNumber.
		 * 
		 * @param invoiceNumber
		 *            invoiceNumber
		 */
		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}
		
		/**
		 * Gets invoiceNumber.
		 * 
		 * @return invoiceNumber
		 */
		public String getInvoiceNumber() {
			return invoiceNumber;
		}  
		
				    		/**
		 * Sets paid.
		 * 
		 * @param paid
		 *            paid
		 */
		public void setPaid(Boolean paid) {
			this.paid = paid;
		}
		
		/**
		 * Gets paid.
		 * 
		 * @return paid
		 */
		public Boolean getPaid() {
			return paid;
		}  
		
							/**
		     * Sets paid.
		     * 
		     * @param paid
		     *            paid
		     */
			public void setPaid(boolean paid) {
				setPaid(new Boolean(paid));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPaid() {
				return getPaid().booleanValue();
			}
			
		    		/**
		 * Sets shipped.
		 * 
		 * @param shipped
		 *            shipped
		 */
		public void setShipped(Boolean shipped) {
			this.shipped = shipped;
		}
		
		/**
		 * Gets shipped.
		 * 
		 * @return shipped
		 */
		public Boolean getShipped() {
			return shipped;
		}  
		
							/**
		     * Sets shipped.
		     * 
		     * @param shipped
		     *            shipped
		     */
			public void setShipped(boolean shipped) {
				setShipped(new Boolean(shipped));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isShipped() {
				return getShipped().booleanValue();
			}
			
		    		/**
		 * Sets date.
		 * 
		 * @param date
		 *            date
		 */
		public void setDate(Date date) {
			this.date = date;
		}
		
		/**
		 * Gets date.
		 * 
		 * @return date
		 */
		public Date getDate() {
			return date;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets addressOid.
		 * 
		 * @param addressOid
		 *            addressOid
		 */
		public void setAddressOid(Long addressOid) {
			this.addressOid = addressOid;
							address = null;
			}
		
		/**
		 * Gets addressOid.
		 * 
		 * @return addressOid
		 */
		public Long getAddressOid() {
			return addressOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets invoiceProducts.
		 * 
		 * @param invoiceProducts
		 *            invoiceProducts
		 */
    	public void setInvoiceProducts(InvoiceProducts invoiceProducts) {
			this.invoiceProducts = invoiceProducts;
			if (invoiceProducts != null) {
				invoiceProducts.setInvoice((Invoice) this);
			}
		}

		/**
		 * Gets invoiceProducts.
		 * 
		 * @return invoiceProducts
		 */
		public InvoiceProducts getInvoiceProducts() {
			return invoiceProducts;
		}
		
	    	/**
		 * Sets invoiceStatuss.
		 * 
		 * @param invoiceStatuss
		 *            invoiceStatuss
		 */
    	public void setInvoiceStatuss(InvoiceStatuss invoiceStatuss) {
			this.invoiceStatuss = invoiceStatuss;
			if (invoiceStatuss != null) {
				invoiceStatuss.setInvoice((Invoice) this);
			}
		}

		/**
		 * Gets invoiceStatuss.
		 * 
		 * @return invoiceStatuss
		 */
		public InvoiceStatuss getInvoiceStatuss() {
			return invoiceStatuss;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets address.
		 * 
		 * @param address
		 *            address
		 */
    	public void setAddress(Address address) {
			this.address = address;
			if (address != null) {
				addressOid = address.getOid().getUniqueNumber();
			} else {
				addressOid = null;
			}
		}

		/**
		 * Gets address.
		 * 
		 * @return address
		 */
		public Address getAddress() {
			if (address == null) {
				Website website = (Website) getModel();
				Addresss addresss = website.getAddresss();
				if (addressOid != null) {
											address = addresss.getAddress(addressOid);
									}
			}
			return address;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}