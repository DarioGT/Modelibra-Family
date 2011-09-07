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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import twoadw.website.address.Address;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.website.invoiceproduct.InvoiceProducts;
import twoadw.website.invoicestatus.InvoiceStatus;
import twoadw.website.invoicestatus.InvoiceStatuss;
import twoadw.website.product.Product;
import twoadw.website.status.Status;

/**
 * Invoice generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenInvoices extends Entities<Invoice> {

	private static final long serialVersionUID = 1234213538117L;

	private static Log log = LogFactory.getLog(GenInvoices.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Address address;

	/* ======= base constructor ======= */

	/**
	 * Constructs invoices within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInvoices(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs invoices for the address parent.
	 * 
	 * @param address
	 *            address
	 */
	public GenInvoices(Address address) {
		this(address.getModel());
		// parent
		setAddress(address);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the invoice with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return invoice
	 */
	public Invoice getInvoice(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the invoice with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return invoice
	 */
	public Invoice getInvoice(Long oidUniqueNumber) {
		return getInvoice(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first invoice whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoice
	 */
	public Invoice getInvoice(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects invoices whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return invoices
	 */
	public Invoices getInvoices(String propertyCode, Object property) {
		return (Invoices) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets invoices ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoices
	 */
	public Invoices getInvoices(String propertyCode, boolean ascending) {
		return (Invoices) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets invoices selected by a selector. Returns empty invoices if there are
	 * no invoices that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected invoices
	 */
	public Invoices getInvoices(ISelector selector) {
		return (Invoices) selectBySelector(selector);
	}

	/**
	 * Gets invoices ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered invoices
	 */
	public Invoices getInvoices(Comparator comparator, boolean ascending) {
		return (Invoices) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets invoiceNumber invoice.
	 * 
	 * @param invoiceNumber
	 *            invoiceNumber
	 * @return invoiceNumber invoice
	 */
	public Invoice getInvoiceNumberInvoice(String invoiceNumber) {
		PropertySelector propertySelector = new PropertySelector(
				"invoiceNumber");
		propertySelector.defineEqual(invoiceNumber);
		List<Invoice> list = getInvoices(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets address invoices.
	 * 
	 * @param address
	 *            address oid unique number
	 * @return address invoices
	 */
	public Invoices getAddressInvoices(Long address) {
		PropertySelector propertySelector = new PropertySelector("addressOid");
		propertySelector.defineEqual(address);
		return getInvoices(propertySelector);
	}

	/**
	 * Gets address invoices.
	 * 
	 * @param address
	 *            address oid
	 * @return address invoices
	 */
	public Invoices getAddressInvoices(Oid address) {
		return getAddressInvoices(address.getUniqueNumber());
	}

	/**
	 * Gets address invoices.
	 * 
	 * @param address
	 *            address
	 * @return address invoices
	 */
	public Invoices getAddressInvoices(Address address) {
		return getAddressInvoices(address.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets invoices ordered by invoiceNumber.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered invoices
	 */
	public Invoices getInvoicesOrderedByInvoiceNumber(boolean ascending) {
		return getInvoices("invoiceNumber", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets product invoiceProducts.
	 * 
	 * @return product invoiceProducts
	 */
	public InvoiceProducts getProductInvoiceProducts(Product product) {
		InvoiceProducts invoiceProducts = new InvoiceProducts(product);
		invoiceProducts.setPersistent(false);
		for (Invoice invoice : this) {
			InvoiceProduct invoiceProduct = invoice.getInvoiceProducts()
					.getInvoiceProduct(invoice, product);
			if (invoiceProduct != null) {
				invoiceProducts.add(invoiceProduct);
			}
		}
		return invoiceProducts;
	}

	/**
	 * Gets status invoiceStatuss.
	 * 
	 * @return status invoiceStatuss
	 */
	public InvoiceStatuss getStatusInvoiceStatuss(Status status) {
		InvoiceStatuss invoiceStatuss = new InvoiceStatuss(status);
		invoiceStatuss.setPersistent(false);
		for (Invoice invoice : this) {
			InvoiceStatus invoiceStatus = invoice.getInvoiceStatuss()
					.getInvoiceStatus(invoice, status);
			if (invoiceStatus != null) {
				invoiceStatuss.add(invoiceStatus);
			}
		}
		return invoiceStatuss;
	}

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets address.
	 * 
	 * @param address
	 *            address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets address.
	 * 
	 * @return address
	 */
	public Address getAddress() {
		return address;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Invoice invoice) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(invoice)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Address address = getAddress();
				if (address == null) {
					Address invoiceAddress = invoice.getAddress();
					if (invoiceAddress != null) {
						if (!invoiceAddress.getInvoices().contain(invoice)) {
							invoiceAddress.getInvoices().setPropagateToSource(
									false);
							post = invoiceAddress.getInvoices().add(invoice);
							invoiceAddress.getInvoices().setPropagateToSource(
									true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(Invoice invoice) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(invoice)) {
			Address address = getAddress();
			if (address == null) {
				Address invoiceAddress = invoice.getAddress();
				if (invoiceAddress != null) {
					if (invoiceAddress.getInvoices().contain(invoice)) {
						invoiceAddress.getInvoices()
								.setPropagateToSource(false);
						post = invoiceAddress.getInvoices().remove(invoice);
						invoiceAddress.getInvoices().setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(Invoice beforeInvoice, Invoice afterInvoice) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeInvoice, afterInvoice)) {
			Address address = getAddress();
			if (address == null) {
				Address beforeInvoiceAddress = beforeInvoice.getAddress();
				Address afterInvoiceAddress = afterInvoice.getAddress();
				if (beforeInvoiceAddress == null && afterInvoiceAddress != null) {
					// attach
					if (!afterInvoiceAddress.getInvoices()
							.contain(afterInvoice)) {
						afterInvoiceAddress.getInvoices().setPropagateToSource(
								false);
						post = afterInvoiceAddress.getInvoices().add(
								afterInvoice);
						afterInvoiceAddress.getInvoices().setPropagateToSource(
								true);
					}
				} else if (beforeInvoiceAddress != null
						&& afterInvoiceAddress == null) {
					// detach
					if (beforeInvoiceAddress.getInvoices().contain(
							beforeInvoice)) {
						beforeInvoiceAddress.getInvoices()
								.setPropagateToSource(false);
						post = beforeInvoiceAddress.getInvoices().remove(
								beforeInvoice);
						beforeInvoiceAddress.getInvoices()
								.setPropagateToSource(true);
					}
				} else if (beforeInvoiceAddress != null
						&& afterInvoiceAddress != null
						&& beforeInvoiceAddress != afterInvoiceAddress) {
					// detach
					if (beforeInvoiceAddress.getInvoices().contain(
							beforeInvoice)) {
						beforeInvoiceAddress.getInvoices()
								.setPropagateToSource(false);
						post = beforeInvoiceAddress.getInvoices().remove(
								beforeInvoice);
						beforeInvoiceAddress.getInvoices()
								.setPropagateToSource(true);
					}
					// attach
					if (!afterInvoiceAddress.getInvoices()
							.contain(afterInvoice)) {
						afterInvoiceAddress.getInvoices().setPropagateToSource(
								false);
						post = afterInvoiceAddress.getInvoices().add(
								afterInvoice);
						afterInvoiceAddress.getInvoices().setPropagateToSource(
								true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates invoice.
	 * 
	 * @param addressParent
	 *            address parent
	 * @param invoiceNumber
	 *            invoiceNumber
	 * @return invoice
	 */
	public Invoice createInvoice(Address addressParent, String invoiceNumber) {
		Invoice invoice = new Invoice(getModel());
		invoice.setAddress(addressParent);
		invoice.setInvoiceNumber(invoiceNumber);
		if (!add(invoice)) {
			invoice = null;
		}
		return invoice;
	}

}