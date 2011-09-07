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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	

/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.status.Status;
	import twoadw.website.status.Statuss;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * InvoiceStatus generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-04-08
 */
public abstract class GenInvoiceStatus extends Entity<InvoiceStatus> {

	private static final long serialVersionUID = 1234728303057L;

	private static Log log = LogFactory.getLog(GenInvoiceStatus.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private Date date;
	
    	    private String note;
	
        
    /* ======= reference properties ======= */
	
    	    private Long statusOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Invoice invoice;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private Status status;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceStatus within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoiceStatus(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs invoiceStatus within its parent(s).
	     * 
	        		* @param Invoice invoice
		    		* @param Status status
			     */
	    public GenInvoiceStatus(
	    		    							Invoice invoice,
	    			    		    							Status status  
	    			    		    ) {
	    				this(status.getModel());
			// parents
							setInvoice(invoice);
	    					setStatus(status);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
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
		
				    		/**
		 * Sets note.
		 * 
		 * @param note
		 *            note
		 */
		public void setNote(String note) {
			this.note = note;
		}
		
		/**
		 * Gets note.
		 * 
		 * @return note
		 */
		public String getNote() {
			return note;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets statusOid.
		 * 
		 * @param statusOid
		 *            statusOid
		 */
		public void setStatusOid(Long statusOid) {
			this.statusOid = statusOid;
							status = null;
			}
		
		/**
		 * Gets statusOid.
		 * 
		 * @return statusOid
		 */
		public Long getStatusOid() {
			return statusOid;
		}  
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
        	/**
		 * Sets invoice.
		 * 
		 * @param invoice
		 *            invoice
		 */
    	public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		/**
		 * Gets invoice.
		 * 
		 * @return invoice
		 */
		public Invoice getInvoice() {
			return invoice;
		}
		
		
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
        	/**
		 * Sets status.
		 * 
		 * @param status
		 *            status
		 */
    	public void setStatus(Status status) {
			this.status = status;
			if (status != null) {
				statusOid = status.getOid().getUniqueNumber();
			} else {
				statusOid = null;
			}
		}

		/**
		 * Gets status.
		 * 
		 * @return status
		 */
		public Status getStatus() {
			if (status == null) {
				Website website = (Website) getModel();
				Statuss statuss = website.getStatuss();
				if (statusOid != null) {
											status = statuss.getStatus(statusOid);
									}
			}
			return status;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}