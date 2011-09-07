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
package twoadw.website;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.DomainModel;

/* ======= import entry concept entities ======= */

import twoadw.website.product.Products;	
import twoadw.website.invoice.Invoices;	
import twoadw.website.customer.Customers;	
import twoadw.website.productcategory.ProductCategories;	
import twoadw.website.manufacturer.Manufacturers;	
import twoadw.website.status.Statuss;	
import twoadw.website.rebate.Rebates;	
import twoadw.website.questioncategory.QuestionCategories;	
import twoadw.website.question.Questions;	

/* ======= import non entry external child/parent required concept entities ======= */

import twoadw.website.invoiceproduct.InvoiceProducts;	
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.website.invoice.Invoice;
import twoadw.website.address.Addresss;	
import twoadw.website.address.Address;
import twoadw.website.customer.Customer;
import twoadw.website.invoicestatus.InvoiceStatuss;	
import twoadw.website.invoicestatus.InvoiceStatus;
import twoadw.website.invoice.Invoice;
import twoadw.website.productmanufacturer.ProductManufacturers;	
import twoadw.website.productmanufacturer.ProductManufacturer;
import twoadw.website.product.Product;
import twoadw.website.productrebate.ProductRebates;	
import twoadw.website.productrebate.ProductRebate;
import twoadw.website.product.Product;
import twoadw.website.qqcategory.QQCategories;	
import twoadw.website.qqcategory.QQCategory;
import twoadw.website.question.Question;
import twoadw.website.assignproductcategory.AssignProductCategories;	
import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.productcategory.ProductCategory;

/**
 * Website generated model. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenWebsite extends DomainModel {

	private static final long serialVersionUID = 1234213131026L;
	
	private static Log log = LogFactory.getLog(GenWebsite.class);
	
	private Products products;
		
	private Invoices invoices;
		
	private Customers customers;
		
	private ProductCategories productCategories;
		
	private Manufacturers manufacturers;
		
	private Statuss statuss;
		
	private Rebates rebates;
		
	private QuestionCategories questionCategories;
		
	private Questions questions;
		
	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenWebsite(IDomain domain) {
		super(domain);
		products = new Products(this);
		invoices = new Invoices(this);
		customers = new Customers(this);
		productCategories = new ProductCategories(this);
		manufacturers = new Manufacturers(this);
		statuss = new Statuss(this);
		rebates = new Rebates(this);
		questionCategories = new QuestionCategories(this);
		questions = new Questions(this);
	}

	/**
	 * Gets Product entities.
	 * 
	 * @return Product entities
	 */
	public Products getProducts() {
		return products;
	}
	
	/**
	 * Gets Invoice entities.
	 * 
	 * @return Invoice entities
	 */
	public Invoices getInvoices() {
		return invoices;
	}
	
	/**
	 * Gets Customer entities.
	 * 
	 * @return Customer entities
	 */
	public Customers getCustomers() {
		return customers;
	}
	
	/**
	 * Gets ProductCategory entities.
	 * 
	 * @return ProductCategory entities
	 */
	public ProductCategories getProductCategories() {
		return productCategories;
	}
	
	/**
	 * Gets Manufacturer entities.
	 * 
	 * @return Manufacturer entities
	 */
	public Manufacturers getManufacturers() {
		return manufacturers;
	}
	
	/**
	 * Gets Status entities.
	 * 
	 * @return Status entities
	 */
	public Statuss getStatuss() {
		return statuss;
	}
	
	/**
	 * Gets Rebate entities.
	 * 
	 * @return Rebate entities
	 */
	public Rebates getRebates() {
		return rebates;
	}
	
	/**
	 * Gets QuestionCategory entities.
	 * 
	 * @return QuestionCategory entities
	 */
	public QuestionCategories getQuestionCategories() {
		return questionCategories;
	}
	
	/**
	 * Gets Question entities.
	 * 
	 * @return Question entities
	 */
	public Questions getQuestions() {
		return questions;
	}
	


	/**
	 * Gets all InvoiceProduct entities.
	 * 
	 * @return InvoiceProduct entities
	 */
	public InvoiceProducts getInvoiceProducts() {
		InvoiceProducts allInvoiceProducts = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allInvoiceProducts = new InvoiceProducts(this);
				allInvoiceProducts.setPersistent(false);
				allInvoiceProducts.setPre(false);
				allInvoiceProducts.setPost(false);
				Invoices invoices = getInvoices();
				for (Invoice invoice : invoices) {
					InvoiceProducts invoiceInvoiceProducts = invoice.getInvoiceProducts();
					for (InvoiceProduct invoiceProduct : invoiceInvoiceProducts) {
						allInvoiceProducts.add(invoiceProduct);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getInvoiceProducts(): " + e.getMessage());
			} finally {
				allInvoiceProducts.setPersistent(true);
				allInvoiceProducts.setPre(true);
				allInvoiceProducts.setPost(true);
			}			
		}
		return allInvoiceProducts;
	}
	

	/**
	 * Gets all Address entities.
	 * 
	 * @return Address entities
	 */
	public Addresss getAddresss() {
		Addresss allAddresss = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allAddresss = new Addresss(this);
				allAddresss.setPersistent(false);
				allAddresss.setPre(false);
				allAddresss.setPost(false);
				Customers customers = getCustomers();
				for (Customer customer : customers) {
					Addresss customerAddresss = customer.getAddresss();
					for (Address address : customerAddresss) {
						allAddresss.add(address);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getAddresss(): " + e.getMessage());
			} finally {
				allAddresss.setPersistent(true);
				allAddresss.setPre(true);
				allAddresss.setPost(true);
			}			
		}
		return allAddresss;
	}
	

	/**
	 * Gets all InvoiceStatus entities.
	 * 
	 * @return InvoiceStatus entities
	 */
	public InvoiceStatuss getInvoiceStatuss() {
		InvoiceStatuss allInvoiceStatuss = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allInvoiceStatuss = new InvoiceStatuss(this);
				allInvoiceStatuss.setPersistent(false);
				allInvoiceStatuss.setPre(false);
				allInvoiceStatuss.setPost(false);
				Invoices invoices = getInvoices();
				for (Invoice invoice : invoices) {
					InvoiceStatuss invoiceInvoiceStatuss = invoice.getInvoiceStatuss();
					for (InvoiceStatus invoiceStatus : invoiceInvoiceStatuss) {
						allInvoiceStatuss.add(invoiceStatus);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getInvoiceStatuss(): " + e.getMessage());
			} finally {
				allInvoiceStatuss.setPersistent(true);
				allInvoiceStatuss.setPre(true);
				allInvoiceStatuss.setPost(true);
			}			
		}
		return allInvoiceStatuss;
	}
	

	/**
	 * Gets all ProductManufacturer entities.
	 * 
	 * @return ProductManufacturer entities
	 */
	public ProductManufacturers getProductManufacturers() {
		ProductManufacturers allProductManufacturers = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allProductManufacturers = new ProductManufacturers(this);
				allProductManufacturers.setPersistent(false);
				allProductManufacturers.setPre(false);
				allProductManufacturers.setPost(false);
				Products products = getProducts();
				for (Product product : products) {
					ProductManufacturers productProductManufacturers = product.getProductManufacturers();
					for (ProductManufacturer productManufacturer : productProductManufacturers) {
						allProductManufacturers.add(productManufacturer);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getProductManufacturers(): " + e.getMessage());
			} finally {
				allProductManufacturers.setPersistent(true);
				allProductManufacturers.setPre(true);
				allProductManufacturers.setPost(true);
			}			
		}
		return allProductManufacturers;
	}
	

	/**
	 * Gets all ProductRebate entities.
	 * 
	 * @return ProductRebate entities
	 */
	public ProductRebates getProductRebates() {
		ProductRebates allProductRebates = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allProductRebates = new ProductRebates(this);
				allProductRebates.setPersistent(false);
				allProductRebates.setPre(false);
				allProductRebates.setPost(false);
				Products products = getProducts();
				for (Product product : products) {
					ProductRebates productProductRebates = product.getProductRebates();
					for (ProductRebate productRebate : productProductRebates) {
						allProductRebates.add(productRebate);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getProductRebates(): " + e.getMessage());
			} finally {
				allProductRebates.setPersistent(true);
				allProductRebates.setPre(true);
				allProductRebates.setPost(true);
			}			
		}
		return allProductRebates;
	}
	

	/**
	 * Gets all QQCategory entities.
	 * 
	 * @return QQCategory entities
	 */
	public QQCategories getQQCategories() {
		QQCategories allQQCategories = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allQQCategories = new QQCategories(this);
				allQQCategories.setPersistent(false);
				allQQCategories.setPre(false);
				allQQCategories.setPost(false);
				Questions questions = getQuestions();
				for (Question question : questions) {
					QQCategories questionQQCategories = question.getQQCategories();
					for (QQCategory qQCategory : questionQQCategories) {
						allQQCategories.add(qQCategory);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getQQCategories(): " + e.getMessage());
			} finally {
				allQQCategories.setPersistent(true);
				allQQCategories.setPre(true);
				allQQCategories.setPost(true);
			}			
		}
		return allQQCategories;
	}
	

	/**
	 * Gets all AssignProductCategory entities.
	 * 
	 * @return AssignProductCategory entities
	 */
	public AssignProductCategories getAssignProductCategories() {
		AssignProductCategories allAssignProductCategories = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allAssignProductCategories = new AssignProductCategories(this);
				allAssignProductCategories.setPersistent(false);
				allAssignProductCategories.setPre(false);
				allAssignProductCategories.setPost(false);
				ProductCategories productCategories = getProductCategories();
				for (ProductCategory productCategory : productCategories) {
					AssignProductCategories productCategoryAssignProductCategories = productCategory.getAssignProductCategories();
					for (AssignProductCategory assignProductCategory : productCategoryAssignProductCategories) {
						allAssignProductCategories.add(assignProductCategory);
					}
				}	
			} catch (Exception e) {
				log.error("Error in GenWebsite.getAssignProductCategories(): " + e.getMessage());
			} finally {
				allAssignProductCategories.setPersistent(true);
				allAssignProductCategories.setPre(true);
				allAssignProductCategories.setPost(true);
			}			
		}
		return allAssignProductCategories;
	}
	
}
