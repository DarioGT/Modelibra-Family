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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.invoice.Invoice;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.status.Status;	
	import twoadw.website.status.Statuss;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * InvoiceStatus generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-04-08
 */
public abstract class GenInvoiceStatuss extends Entities<InvoiceStatus> {
	
	private static final long serialVersionUID = 1234728303058L;

	private static Log log = LogFactory.getLog(GenInvoiceStatuss.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Invoice invoice;	
    
/* ======= external parent neighbors ======= */
	
	private Status status;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs invoiceStatuss within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoiceStatuss(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs invoiceStatuss for the invoice parent.
		 * 
		 * @param invoice
		 *            invoice
		 */
		public GenInvoiceStatuss(Invoice invoice) {
			this(invoice.getModel());
			// parent
			setInvoice(invoice);
		}
	
    	/**
		 * Constructs invoiceStatuss for the status parent.
		 * 
		 * @param status
		 *            status
		 */
		public GenInvoiceStatuss(Status status) {
			this(status.getModel());
			// parent
			setStatus(status);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the invoiceStatus with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return invoiceStatus
	 */
public InvoiceStatus getInvoiceStatus(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the invoiceStatus with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return invoiceStatus
	 */
	public InvoiceStatus getInvoiceStatus(Long oidUniqueNumber) {
		return getInvoiceStatus(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first invoiceStatus whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoiceStatus
	 */
	public InvoiceStatus getInvoiceStatus(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects invoiceStatuss whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoiceStatuss
	 */
	public InvoiceStatuss getInvoiceStatuss(String propertyCode, Object property) {
		return (InvoiceStatuss) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets invoiceStatuss ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoiceStatuss
	 */
	public InvoiceStatuss getInvoiceStatuss(String propertyCode, boolean ascending) {
		return (InvoiceStatuss) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets invoiceStatuss selected by a selector. Returns empty invoiceStatuss if there are no
	 * invoiceStatuss that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected invoiceStatuss
	 */
	public InvoiceStatuss getInvoiceStatuss(ISelector selector) {
		return (InvoiceStatuss) selectBySelector(selector);
	}
	
	/**
	 * Gets invoiceStatuss ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoiceStatuss
	 */
	public InvoiceStatuss getInvoiceStatuss(Comparator comparator, boolean ascending) {
		return (InvoiceStatuss) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets invoiceStatus based on many-to-many parents.
		 * 
				 * @param Invoice invoice
				 * @param Status status
			 */
		public InvoiceStatus getInvoiceStatus(
									Invoice invoice,
										Status status  
						) {
			for (InvoiceStatus invoiceStatus : this) {
				if (
																	invoiceStatus.getInvoice() == invoice &&
																						invoiceStatus.getStatus() == status  
													) {
					return invoiceStatus;
				}
			}
			return null;
		}
		
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
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
		
		
	/* ======= external parent set and get methods ======= */
    
/**
		 * Sets status.
		 * 
		 * @param status
		 *            status
		 */
public void setStatus(Status status) {
			this.status = status;
		}

		/**
		 * Gets status.
		 * 
		 * @return status
		 */
		public Status getStatus() {
			return status;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
				protected boolean postAdd(InvoiceStatus invoiceStatus) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(invoiceStatus)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											Invoice invoice = getInvoice();	
						if (invoice == null) {
							Invoice invoiceStatusInvoice = invoiceStatus.getInvoice();
							if (!invoiceStatusInvoice.getInvoiceStatuss().contain(invoiceStatus)) {
								post = invoiceStatusInvoice.getInvoiceStatuss().add(invoiceStatus);
							}
						}						
											Status status = getStatus();	
						if (status == null) {
							Status invoiceStatusStatus = invoiceStatus.getStatus();
							if (!invoiceStatusStatus.getInvoiceStatuss().contain(invoiceStatus)) {
								post = invoiceStatusStatus.getInvoiceStatuss().add(invoiceStatus);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(InvoiceStatus invoiceStatus) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(invoiceStatus)) {		
									Invoice invoice = getInvoice();	
					if (invoice == null) {
						Invoice invoiceStatusInvoice = invoiceStatus.getInvoice();
						if (invoiceStatusInvoice.getInvoiceStatuss().contain(invoiceStatus)) {
							post = invoiceStatusInvoice.getInvoiceStatuss().remove(invoiceStatus);
						}
					}					
									Status status = getStatus();	
					if (status == null) {
						Status invoiceStatusStatus = invoiceStatus.getStatus();
						if (invoiceStatusStatus.getInvoiceStatuss().contain(invoiceStatus)) {
							post = invoiceStatusStatus.getInvoiceStatuss().remove(invoiceStatus);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(InvoiceStatus beforeInvoiceStatus, InvoiceStatus afterInvoiceStatus) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeInvoiceStatus, afterInvoiceStatus)) {					
									Invoice beforeInvoiceStatusInvoice = beforeInvoiceStatus.getInvoice();
					Invoice afterInvoiceStatusInvoice = afterInvoiceStatus.getInvoice();
						
					if (beforeInvoiceStatusInvoice != afterInvoiceStatusInvoice) {
						post = beforeInvoiceStatusInvoice.getInvoiceStatuss().remove(beforeInvoiceStatus);
						if (post) {
							post = afterInvoiceStatusInvoice.getInvoiceStatuss().add(afterInvoiceStatus);
							if (!post) {
								beforeInvoiceStatusInvoice.getInvoiceStatuss().add(beforeInvoiceStatus);
							}
						}
					}						
									Status beforeInvoiceStatusStatus = beforeInvoiceStatus.getStatus();
					Status afterInvoiceStatusStatus = afterInvoiceStatus.getStatus();
						
					if (beforeInvoiceStatusStatus != afterInvoiceStatusStatus) {
						post = beforeInvoiceStatusStatus.getInvoiceStatuss().remove(beforeInvoiceStatus);
						if (post) {
							post = afterInvoiceStatusStatus.getInvoiceStatuss().add(afterInvoiceStatus);
							if (!post) {
								beforeInvoiceStatusStatus.getInvoiceStatuss().add(beforeInvoiceStatus);
							}
						}
					}						
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates invoiceStatus.
	 *
			 * @param invoiceParent invoice parent
			 * @param statusParent status parent
			 * @return invoiceStatus
	 */
	public InvoiceStatus createInvoiceStatus(
											Invoice invoiceParent, 
																				Status statusParent
										) {
		InvoiceStatus invoiceStatus = new InvoiceStatus(getModel());
					invoiceStatus.setInvoice(invoiceParent);
					invoiceStatus.setStatus(statusParent);
						if (!add(invoiceStatus)) {
			invoiceStatus = null;
		}
		return invoiceStatus;
	}

}